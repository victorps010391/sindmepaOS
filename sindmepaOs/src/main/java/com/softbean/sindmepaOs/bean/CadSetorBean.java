/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadSetorControle;
import com.softbean.sindmepaOs.entidade.CadSetor;
import com.softbean.sindmepaOs.util.RelatorioUtilBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jrimum.bopepo.view.BoletoViewer;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Victor
 */
@Named(value = "cadSetorBean")
@SessionScoped
public class CadSetorBean implements Serializable {

    /**
     * Creates a new instance of CadSetorBean
     */
    public CadSetorBean() {
    }

    @Inject
    CadSetorControle setorControle;
    @Inject
    RelatorioUtilBean relatorioUtilBean;

    CadSetor objSetor;

    String nomeSetor;
    String sitSetor = "01";
    String sitSetorAlt;
    String nomeSetorPesq;
    String sitSetorPesq;
    Integer codSetorPesq;

    List<Map<String, Object>> gridPesquisa;
    List<Map<String, Object>> sitSetorLista;

    public void boleto() {
        PrimeFaces context = PrimeFaces.current();
        byte[] pdfAsBytes = null;
        try {
            relatorioUtilBean.setExtensaoArquivoExportado("pdf");
            relatorioUtilBean.setNomeArquivo("Relatório");

            BoletoViewer boletoViewer = setorControle.estudoGerarBoleto();
            pdfAsBytes = boletoViewer.getPdfAsByteArray();

            if (pdfAsBytes != null) {
                relatorioUtilBean.setRelatorioByte(pdfAsBytes);
                context.executeScript("abrirPgRelatorio()");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADM Monobloco informa:", "Não Há Infomações Para Gerar O Relatório"));
            }
        } catch (Exception e) {
            System.out.println("ERRO no método boleto()");
            e.printStackTrace();
        }
    }

    public void gerarRelatorio() {
        PrimeFaces context = PrimeFaces.current();
        byte[] jasperPrint = null;
        try {
            relatorioUtilBean.setExtensaoArquivoExportado("pdf");
            relatorioUtilBean.setNomeArquivo("Relatório");

            jasperPrint = setorControle.gerarRelatorio();
            if (jasperPrint != null) {
                relatorioUtilBean.setRelatorioByte(jasperPrint);
                context.executeScript("abrirPgRelatorio()");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol informa:", "Não Há Infomações Para Gerar O Relatório"));

            }
        } catch (Exception e) {
            System.out.println("ERRO no método gerarRelatorio()");
            e.printStackTrace();
        }
    }

    public void pesquisa() {
        try {
            setGridPesquisa(setorControle.gridPrincipal(getNomeSetorPesq(), getCodSetorPesq(), getSitSetorPesq()));
        } catch (Exception e) {
            System.out.println("Erro no método Pesquisa (Setor)");
            e.printStackTrace();
        }
    }

    public void salvarSetorBean() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjSetor(new CadSetor());
            getObjSetor().setNmSetor(getNomeSetor().toUpperCase());
            getObjSetor().setSitSetor(getSitSetor());

            if (setorControle.salvarSetorControle(getObjSetor())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Cadastro do Setor Realizado com Sucesso."));
                context.executeScript("PF('dlCadSetor').hide()");
                limparCadastro();
                setGridPesquisa(setorControle.gridPrincipal(getObjSetor().getNmSetor(), getObjSetor().getCdSetor(), getObjSetor().getSitSetor()));
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Cadastrar Setor."));
                limparCadastro();
            }
        } catch (Exception e) {
            System.out.println("Erro metódo salvarSetorBean");
            e.printStackTrace();
        }
    }

    public void buscarSetorBean(Integer cod) {
        try {
            setObjSetor(setorControle.buscarSetor(cod));
            setSitSetorAlt(getObjSetor().getSitSetor());
        } catch (Exception e) {
            System.out.println("Erro no método buscarSetorBean");
            e.printStackTrace();
        }
    }

    public void alterarSetorBean() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObjSetor().setSitSetor(getSitSetorAlt());
            if (setorControle.alterarSetorControle(getObjSetor())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Setor Alterado com Sucesso."));
                context.executeScript("PF('dlAltSetor').hide()");
                setGridPesquisa(setorControle.gridPrincipal(getObjSetor().getNmSetor(), getObjSetor().getCdSetor(), getObjSetor().getSitSetor()));
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Tentar Alterar Setor."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método alterarSetorBean");
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> listarSituacaoSetor() {
        try {
            setSitSetorLista(setorControle.listarSituacaoSetor());
        } catch (Exception e) {
            System.out.println("Erro no método listarSituacaoSetor");
            e.printStackTrace();
        }
        return getSitSetorLista();
    }

    public void limparCadastro() {
        setNomeSetor(null);
        setSitSetor("01");
    }

    public void limparPesquisa() {
        setNomeSetorPesq(null);
        setSitSetorPesq(null);
        setCodSetorPesq(null);
        setGridPesquisa(null);
    }

    public CadSetor getObjSetor() {
        if (objSetor == null) {
            objSetor = new CadSetor();
        }
        return objSetor;
    }

    public void setObjSetor(CadSetor objSetor) {
        this.objSetor = objSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getSitSetor() {
        return sitSetor;
    }

    public void setSitSetor(String sitSetor) {
        this.sitSetor = sitSetor;
    }

    public String getSitSetorAlt() {
        return sitSetorAlt;
    }

    public void setSitSetorAlt(String sitSetorAlt) {
        this.sitSetorAlt = sitSetorAlt;
    }

    public String getNomeSetorPesq() {
        return nomeSetorPesq;
    }

    public void setNomeSetorPesq(String nomeSetorPesq) {
        this.nomeSetorPesq = nomeSetorPesq;
    }

    public String getSitSetorPesq() {
        return sitSetorPesq;
    }

    public void setSitSetorPesq(String sitSetorPesq) {
        this.sitSetorPesq = sitSetorPesq;
    }

    public Integer getCodSetorPesq() {
        return codSetorPesq;
    }

    public void setCodSetorPesq(Integer codSetorPesq) {
        this.codSetorPesq = codSetorPesq;
    }

    public List<Map<String, Object>> getGridPesquisa() {
        return gridPesquisa;
    }

    public void setGridPesquisa(List<Map<String, Object>> gridPesquisa) {
        this.gridPesquisa = gridPesquisa;
    }

    public List<Map<String, Object>> getSitSetorLista() {
        return sitSetorLista;
    }

    public void setSitSetorLista(List<Map<String, Object>> sitSetorLista) {
        this.sitSetorLista = sitSetorLista;
    }

}
