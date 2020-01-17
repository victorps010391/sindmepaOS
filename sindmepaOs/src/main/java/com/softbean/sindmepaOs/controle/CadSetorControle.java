/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadSetor;
import com.softbean.sindmepaOs.fachada.CadDetalheFacade;
import com.softbean.sindmepaOs.fachada.CadSetorFacade;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.exemplo.Exemplos;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Banco;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

/**
 *
 * @author Victor
 */
@Named(value = "cadSetorControle")
@SessionScoped
public class CadSetorControle implements Serializable {

    /**
     * Creates a new instance of CadSetorControle
     */
    public CadSetorControle() {
    }

    @Inject
    CadSetorFacade cadSetorFacade;
    @Inject
    CadDetalheFacade cadDetalheFacade;
    @Inject
    RelatorioControle relatorioControle;

    public byte[] gerarRelatorio() {
        byte[] jasperViewer = null;
        try {
            InputStream stream = this.getClass().getResourceAsStream("Blank_A4.jasper");
            StringBuilder sql = new StringBuilder();
            Map<String, Object> map = new HashMap<String, Object>();
            jasperViewer = relatorioControle.geraRelatorioInputStr(sql.toString(), "teste", map, stream);
        } catch (Exception e) {
            System.out.println("erro ao gerarRelatorio");
            e.printStackTrace();
        }
        return jasperViewer;
    }

    public BoletoViewer estudoGerarBoleto() {
        /*
                 * INFORMANDO DADOS SOBRE O CEDENTE (EMISSOR DO BOLETO).
         */
        Cedente cedente = new Cedente("Sofbean", "00.000.208/0001-00");

        /*
                 * INFORMANDO DADOS SOBRE O SACADO.
         */
        Sacado sacado = new Sacado("JavaDeveloper Pronto Para Férias", "222.222.222-22");

        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.RN);
        enderecoSac.setLocalidade("Natal");
        enderecoSac.setCep(new CEP("59064-120"));
        enderecoSac.setBairro("Grande Centro");
        enderecoSac.setLogradouro("Rua poeta dos programas");
        enderecoSac.setNumero("1");
        sacado.addEndereco(enderecoSac);

        Banco banco = new Banco();
        banco.setImgLogo(new ImageIcon("/resources/imagesResources/sindmepaLogo.PNG").getImage());

        /*
                 * INFORMANDO OS DADOS SOBRE O TÍTULO.               
         */
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_SICREDI.create());
        contaBancaria.setAgencia(new Agencia(165));
        contaBancaria.setNumeroDaConta(new NumeroDaConta(623));
        //contaBancaria.setCarteira(new Carteira(1));
        contaBancaria.setCarteira(new Carteira(1, TipoDeCobranca.SEM_REGISTRO));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("12345");
        titulo.setNossoNumero("07200003");
        titulo.setDigitoDoNossoNumero("1");
        titulo.setParametrosBancarios(new ParametrosBancariosMap("PostoDaAgencia", 02));
        titulo.setValor(BigDecimal.valueOf(0.23));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Titulo.Aceite.A);
        titulo.setDesconto(new BigDecimal(0.05));
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);

        /*
                 * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(titulo);
        boleto.addTextosExtras("txtRsCodBanco", "748-X"); 
        boleto.addTextosExtras("txtFcCodBanco", "748-X");
        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em "
                + "qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
                + "cobrado não é o esperado, aproveite o DESCONTÃO!");
        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");

        /*
                 * GERANDO O BOLETO BANCÁRIO.
         */
        // Instanciando um objeto "BoletoViewer", classe responsável pela
        // geração do boleto bancário.
        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        return boletoViewer;

    }

    public Boolean salvarSetorControle(CadSetor obj) {
        try {
            cadSetorFacade.create(obj);
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarSetorControle");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean alterarSetorControle(CadSetor obj) {
        try {
            cadSetorFacade.edit(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método salvarSetorControle");
            e.printStackTrace();
            return false;
        }
    }

    public CadSetor buscarSetor(Integer cod) {
        return cadSetorFacade.find(cod);
    }

    public List<Map<String, Object>> gridPrincipal(String desc, Integer cod, String sit) {
        return cadSetorFacade.gridPrincipal(desc, cod, sit);
    }

    public List<Map<String, Object>> listarSituacaoSetor() {
        return cadDetalheFacade.listarSituacaoSetor();
    }

}
