/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadExternoControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.entidade.CadExterno;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadSetor;
import com.softbean.sindmepaOs.entidade.Endereco;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Desenv
 */
@Named(value = "cadExternoBean")
@SessionScoped
public class CadExternoBean implements Serializable {

    /**
     * Creates a new instance of CadExternoBean
     */
    @Inject
    CadExternoControle cadExternoControle;

    @Inject
    CadOsControle cadOsControle;

    List<Map<String, Object>> comboSetor;
    List<Map<String, Object>> comboCategoria;
    List<Map<String, Object>> comboTipoPag;
    List<Map<String, Object>> comboPagInstituicao;

    //Objeto
    CadExterno cadExterno;
    Endereco enderecoObj;
    CadOs cadOs;
    CadSetor objSetor;

    //VARIÁVEIS DE ARMAZENAMENTO PESSOAL
    Integer categoria, setor, pagamento, instituicao, idEndereco;
    String nomeExt, rg, cpf, crm, espec, email, agencia, banco, cc, numMatricula;
    char sexo;
    Date dtNascimento;

    //VARIAVEIS DE ENDEREÇO
    String endereco, complemento, cep, numeroEnd, bairro, cidade, estado;
    //VARIAVEIS DE CONTATO
    String telComerc, celular, whatsapp;
    boolean liAceito;

    //variáveis view
    String sindicaliza, abriOsExt;

    //rendered
    String tipoPag, anuidadeResidente, debMenCartCred, debCCorrente, debCCheque;

    public CadExternoBean() {
    }

    public void salvarExterno() {
        if (getPagamento() == 13) {//"13 - DÉBITO MENSAL NO CARTÃO DE CRÉDITO"
            System.out.println("Entrou no If salvarExterno() - 13 ");
            salvaPagDebMenCartCred();
        } else if (getPagamento() == 10) {//"10 - DÉBITO EM CONTA CORRENTE"
            System.out.println("Entrou no If salvarExterno() - 10 ");
            salvaPagDebCCorrente();
        } else if (getPagamento() == 11) {//"11 - DÉBITO EM CONTRA-CHEQUE"
            System.out.println("Entrou no If salvarExterno() - 11 ");
            salvaPagDebCCheque();
        }
    }

    public void salvaPagAnuidade() {
        //precisa de anexo
    }

    public void salvaPagDebMenCartCred() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            //Informações de Endereço
            setEnderecoObj(null);
            setEnderecoObj(new Endereco());
            getEnderecoObj().setEndereco(getEndereco());
            //FALTA O COMPLEMENTO AQUI
            getEnderecoObj().setCepEnd(getCep());
            getEnderecoObj().setNmEnd(getNumeroEnd());
            getEnderecoObj().setBairroEnd(getBairro());
            getEnderecoObj().setCidEnd(getCidade());
            getEnderecoObj().setEstEnd("PARA");
            //INFORMAÇÕES DE CONTATO
            getEnderecoObj().setTelComEnd(getTelComerc());
            getEnderecoObj().setCelEnd(getCelular());
            getEnderecoObj().setWtpEnd(getWhatsapp());

            if (cadExternoControle.salvarEnderecoExt(getEnderecoObj())) {
                setCadExterno(null); //limpa variável
                setCadExterno(new CadExterno());
                getCadExterno().setIdEndExt(getEnderecoObj());
                getCadExterno().setCdTipPagExt(getPagamento());
                //Dados pessoais
                getCadExterno().setNomeExt(getNomeExt());
                getCadExterno().setTipoPesExt('E'); //tipoPessoaExterna
                getCadExterno().setRgExt(getRg());
                getCadExterno().setCpfExt(getCpf());
                getCadExterno().setCrmExt(getCrm());
                getCadExterno().setEspExt(getEspec());
                getCadExterno().setDataNascExt(getDtNascimento());
                getCadExterno().setSexoExt(getSexo());
                getCadExterno().setEmail(getEmail());
                System.out.println(" ========= ANTES DO IF ========");
                if (cadExternoControle.salvarUsuarioExt(getCadExterno())) {
                    System.out.println("== Entrou no If salvaPagDebMenCartCred() ==");
                    setCadOs(null)/*limpar variavel*/;
                    setCadOs(new CadOs());
                    getCadOs().setNrOs(cadOsControle.retornaNrOs());
                    getCadOs().setCategOs(getCategoria());
                    setObjSetor(null);
                    setObjSetor(cadOsControle.buscarSetor(0));
                    getCadOs().setSetorAbertOs(getObjSetor());
                    getCadOs().setSetorResponOs(getObjSetor());
                    getCadOs().setDtAbertOs(new Date());
                    getCadOs().setDtFechaOs(null);
                    getCadOs().setDtUltAtuOs(new Date());
                    getCadOs().setFuncAbertOs(getCadExterno().getIdExt());
                    getCadOs().setFuncResponOs(999);
                    getCadOs().setFuncUltAtuOs(999);
                    getCadOs().setHistOs("Protocolo enviado via atendimento externo");
                    getCadOs().setObsOs(null);
                    getCadOs().setSitOs("02");
                    getCadOs().setTipEnvioOs("E");
                    System.out.println(" ========= ANTES DO IF getCadOs() ========");
                    if(cadExternoControle.salvarOsExt(getCadOs())){
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Externo Informa:", "Cadastro do Protocolo Externo: " + getCadOs().getNrOs() + " Realizado com Sucesso."));
                        System.out.println("== SALVOU, FDP!");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Erro o método salvaPagDebMenCartCred()");
            e.printStackTrace();
        }
    }

    public void salvaPagDebCCorrente() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            //Informações de Endereço
            setEnderecoObj(null);
            setEnderecoObj(new Endereco());
            getEnderecoObj().setEndereco(getEndereco());
            //FALTA O COMPLEMENTO AQUI
            getEnderecoObj().setCepEnd(getCep());
            getEnderecoObj().setNmEnd(getNumeroEnd());
            getEnderecoObj().setBairroEnd(getBairro());
            getEnderecoObj().setCidEnd(getCidade());
            getEnderecoObj().setEstEnd("PARA");
            //INFORMAÇÕES DE CONTATO
            getEnderecoObj().setTelComEnd(getTelComerc());
            getEnderecoObj().setCelEnd(getCelular());
            getEnderecoObj().setWtpEnd(getWhatsapp());

            if (cadExternoControle.salvarEnderecoExt(getEnderecoObj())) {
                setCadExterno(null); //limpa variável
                setCadExterno(new CadExterno());
                getCadExterno().setIdEndExt(getEnderecoObj());
                getCadExterno().setCdTipPagExt(getPagamento());
                //Dados pessoais
                getCadExterno().setTipoPesExt('E'); //tipoPessoaExterna
                getCadExterno().setRgExt(getRg());
                getCadExterno().setCpfExt(getCpf());
                getCadExterno().setCrmExt(getCrm());
                getCadExterno().setEspExt(getEspec());
                getCadExterno().setDataNascExt(getDtNascimento());
                getCadExterno().setSexoExt(getSexo());
                getCadExterno().setEmail(getEmail());
                //DADOS BANCÁRIOS
                getCadExterno().setAgExt(getAgencia());
                getCadExterno().setBcExt(getBanco());
                getCadExterno().setCcExt(getCc());
                System.out.println(" ========= ANTES DO IF salvaPagDebCCorrente() ========");
                if (cadExternoControle.salvarUsuarioExt(getCadExterno())) {
                    System.out.println("== Entrou no If salvaPagDebCCorrente() ==");
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Externo Informa:", "Cadastro do Protocolo Externo: NUMERO_DA_OS Realizado com Sucesso."));
                    System.out.println("== SALVOU, FDP!");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro o método salvaPagDebMenCartCred()");
            e.printStackTrace();
        }
    }

    public void salvaPagDebCCheque() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            //Informações de Endereço
            setEnderecoObj(null);
            setEnderecoObj(new Endereco());
            getEnderecoObj().setEndereco(getEndereco());
            //FALTA O COMPLEMENTO AQUI
            getEnderecoObj().setCepEnd(getCep());
            getEnderecoObj().setNmEnd(getNumeroEnd());
            getEnderecoObj().setBairroEnd(getBairro());
            getEnderecoObj().setCidEnd(getCidade());
            getEnderecoObj().setEstEnd("PARA");
            //INFORMAÇÕES DE CONTATO
            getEnderecoObj().setTelComEnd(getTelComerc());
            getEnderecoObj().setCelEnd(getCelular());
            getEnderecoObj().setWtpEnd(getWhatsapp());

            if (cadExternoControle.salvarEnderecoExt(getEnderecoObj())) {
                setCadExterno(null); //limpa variável
                setCadExterno(new CadExterno());
                getCadExterno().setIdEndExt(getEnderecoObj());
                getCadExterno().setCdTipPagExt(getPagamento());
                //Dados pessoais
                getCadExterno().setTipoPesExt('E'); //tipoPessoaExterna
                getCadExterno().setRgExt(getRg());
                getCadExterno().setCpfExt(getCpf());
                getCadExterno().setCrmExt(getCrm());
                getCadExterno().setEspExt(getEspec());
                getCadExterno().setDataNascExt(getDtNascimento());
                getCadExterno().setSexoExt(getSexo());
                getCadExterno().setEmail(getEmail());
                //DADOS BANCÁRIOS CONTRA-CHEQUE
                getCadExterno().setNrMatExt(getNumMatricula());
                getCadExterno().setCdInstExt(getInstituicao());
                System.out.println(" ========= ANTES DO IF ========");
                if (cadExternoControle.salvarUsuarioExt(getCadExterno())) {
                    System.out.println("== Entrou no If salvaPagDebCCheque() ==");

                    getCadOs().setNrOs(cadOsControle.retornaNrOs());
                    getCadOs().setCategOs(getCategoria());
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Externo Informa:", "Cadastro do Protocolo Externo: NUMERO_DA_OS Realizado com Sucesso."));
                    System.out.println("== SALVOU, FDP!");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro o método salvaPagDebCCheque()");
            e.printStackTrace();
        }
    }

    public void salvaOsExterna() {

    }

    public void validador() {
        setAbriOsExt("false");
        setSindicaliza("false");

        if (getCategoria() == 7) {
            setSindicaliza("true");
        } else {
            setAbriOsExt("true");
        }
    }

    public void validaDadoBancario() {
        setAnuidadeResidente("false");
        setDebMenCartCred("false");
        setDebCCorrente("false");
        setDebCCheque("false");

        if (getPagamento() == 12) {
            //"12 - ANUIDADE RESIDENTE"
            setAnuidadeResidente("true");
        } else if (getPagamento() == 13) {
            //"13 - DÉBITO MENSAL NO CARTÃO DE CRÉDITO"
            setDebMenCartCred("true");
        } else if (getPagamento() == 10) {
            //"10 - DÉBITO EM CONTA CORRENTE"
            setDebCCorrente("true");
        } else if (getPagamento() == 11) {
            //"11 - DÉBITO EM CONTRA-CHEQUE"
            setDebCCheque("true");
        }

    }

    public List<Map<String, Object>> comboCategoriaView() {
        try {
            setComboCategoria(cadExternoControle.listarCategoria());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }

        return getComboCategoria();
    }

    public List<Map<String, Object>> comboSetorView() {
        try {
            setComboSetor(cadExternoControle.listarSetor());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }

        return getComboSetor();
    }

    public List<Map<String, Object>> comboTipoPagView() {
        try {
            setComboTipoPag(cadExternoControle.tipoPag());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }

        return getComboTipoPag();
    }

    public List<Map<String, Object>> comboPagInstituicaoView() {
        try {
            setComboPagInstituicao(cadExternoControle.pagInstituicao());
        } catch (Exception e) {
            System.out.println("Erro no método comboPagInstituicaoView()");
        }

        return getComboPagInstituicao();
    }

    public CadExternoControle getCadExternoControle() {
        return cadExternoControle;
    }

    public void setCadExternoControle(CadExternoControle cadExternoControle) {
        this.cadExternoControle = cadExternoControle;
    }

    public List<Map<String, Object>> getComboSetor() {
        return comboSetor;
    }

    public void setComboSetor(List<Map<String, Object>> comboSetor) {
        this.comboSetor = comboSetor;
    }

    public List<Map<String, Object>> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(List<Map<String, Object>> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public String getSindicaliza() {
        return sindicaliza;
    }

    public void setSindicaliza(String sindicaliza) {
        this.sindicaliza = sindicaliza;
    }

    public String getAbriOsExt() {
        return abriOsExt;
    }

    public void setAbriOsExt(String abriOsExt) {
        this.abriOsExt = abriOsExt;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor) {
        this.setor = setor;
    }

    public String onFlowProcess(FlowEvent event) {
//        if(skip) {
//            skip = false;   //reset in case user goes back
//            return "confirm";
//        }
//        else {
//            return event.getNewStep();
//        }
        return event.getNewStep();
    }

    public boolean isLiAceito() {
        return liAceito;
    }

    public void setLiAceito(boolean liAceito) {
        this.liAceito = liAceito;
    }

    public List<Map<String, Object>> getComboTipoPag() {
        return comboTipoPag;
    }

    public void setComboTipoPag(List<Map<String, Object>> comboTipoPag) {
        this.comboTipoPag = comboTipoPag;
    }

    public Integer getPagamento() {
        return pagamento;
    }

    public void setPagamento(Integer pagamento) {
        this.pagamento = pagamento;
    }

    public String getTipoPag() {
        return tipoPag;
    }

    public void setTipoPag(String tipoPag) {
        this.tipoPag = tipoPag;
    }

    public String getAnuidadeResidente() {
        return anuidadeResidente;
    }

    public void setAnuidadeResidente(String anuidadeResidente) {
        this.anuidadeResidente = anuidadeResidente;
    }

    public String getDebMenCartCred() {
        return debMenCartCred;
    }

    public void setDebMenCartCred(String debMenCartCred) {
        this.debMenCartCred = debMenCartCred;
    }

    public String getDebCCorrente() {
        return debCCorrente;
    }

    public void setDebCCorrente(String debCCorrente) {
        this.debCCorrente = debCCorrente;
    }

    public String getDebCCheque() {
        return debCCheque;
    }

    public void setDebCCheque(String debCCheque) {
        this.debCCheque = debCCheque;
    }

    public List<Map<String, Object>> getComboPagInstituicao() {
        return comboPagInstituicao;
    }

    public void setComboPagInstituicao(List<Map<String, Object>> comboPagInstituicao) {
        this.comboPagInstituicao = comboPagInstituicao;
    }

    public Integer getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Integer instituicao) {
        this.instituicao = instituicao;
    }

    public CadExterno getCadExterno() {
        return cadExterno;
    }

    public void setCadExterno(CadExterno cadExterno) {
        this.cadExterno = cadExterno;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNomeExt() {
        return nomeExt;
    }

    public void setNomeExt(String nomeExt) {
        this.nomeExt = nomeExt;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Endereco getEnderecoObj() {
        return enderecoObj;
    }

    public void setEnderecoObj(Endereco enderecoObj) {
        this.enderecoObj = enderecoObj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumeroEnd() {
        return numeroEnd;
    }

    public void setNumeroEnd(String numeroEnd) {
        this.numeroEnd = numeroEnd;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelComerc() {
        return telComerc;
    }

    public void setTelComerc(String telComerc) {
        this.telComerc = telComerc;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public CadOsControle getCadOsControle() {
        return cadOsControle;
    }

    public void setCadOsControle(CadOsControle cadOsControle) {
        this.cadOsControle = cadOsControle;
    }

    public CadOs getCadOs() {
        return cadOs;
    }

    public void setCadOs(CadOs cadOs) {
        this.cadOs = cadOs;
    }

    public CadSetor getObjSetor() {
        return objSetor;
    }

    public void setObjSetor(CadSetor objSetor) {
        this.objSetor = objSetor;
    }
}
