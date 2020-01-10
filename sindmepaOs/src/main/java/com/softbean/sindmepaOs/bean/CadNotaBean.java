/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadNotaControle;
import com.softbean.sindmepaOs.entidade.CadNota;
import com.softbean.sindmepaOs.entidade.CadNotaPK;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;
/**
 *
 * @author Victor
 */
@Named(value = "cadNotaBean")
@SessionScoped
public class CadNotaBean implements Serializable {

    /**
     * Creates a new instance of CadNotaBean
     */
    public CadNotaBean() {
    }

    @Inject
    CadNotaControle notaControle;
    @Inject
    CadOsBean osBean;
    @Inject
    LoginBean loginBean;

    CadNota cadNotaObj;
    CadNotaPK cadNotaObjPK;

    String hisCad;
    Integer osCad;

    public void buscarNota(Integer os, Integer serial) {
        try {
            setCadNotaObj(null);
            setCadNotaObj(notaControle.buscar(os, serial));
        } catch (Exception e) {
            System.out.println("Erro no método buscarNota " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterar() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();

        try {
            getCadNotaObj().setDtUltAtuNota(new Date());
            getCadNotaObj().setFuncUltAtuNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            if (notaControle.alterarNotaControle(getCadNotaObj())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Nota Alterada com Sucesso."));
                context.executeScript("PF('dlAltNota').hide()");
                osBean.pesquisarNota(getCadNotaObj().getCadNotaPK().getNrOsNota());
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Alterar Nota."));
            }
        } catch (Exception e) {
            System.out.println("Erro no Método alterar " + e.getMessage());
        }
    }

    public void excluir() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();

        try {
            getCadNotaObj().setDtUltAtuNota(new Date());
            getCadNotaObj().setFuncUltAtuNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getCadNotaObj().setInvalidaNota("S");
            if (notaControle.excluirNotaControle(getCadNotaObj())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Nota Excluida com Sucesso."));
                context.executeScript("PF('dlExcNota').hide()");
                osBean.pesquisarNota(getCadNotaObj().getCadNotaPK().getNrOsNota());
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Excluir Nota."));
            }
        } catch (Exception e) {
            System.out.println("Erro no Método excluir " + e.getMessage());
        }
    }

    public void buscaNrOS(Integer cod) {
        setOsCad(cod);
    }

    public void salvar() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            if (getCadNotaObj().getCadNotaPK() == null) {
                setCadNotaObjPK(new CadNotaPK());
                getCadNotaObjPK().setNrOsNota(getOsCad());
                getCadNotaObjPK().setSerialNota(notaControle.retornaSeqNota(getOsCad()));

                setCadNotaObj(new CadNota());
                getCadNotaObj().setHistNota(getHisCad());
                getCadNotaObj().setDtRegiNota(new Date());
                getCadNotaObj().setFuncRegiNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                getCadNotaObj().setDtUltAtuNota(new Date());
                getCadNotaObj().setFuncUltAtuNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                if (notaControle.salvarNotaControle(getCadNotaObj(), getCadNotaObjPK())) {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro de Nota Realizado com Sucesso."));
                    context.executeScript("PF('dlCadNota').hide()");
                    osBean.pesquisarNota(getOsCad());
                    limparCadastro();
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Nota."));
                }
            }
        } catch (Exception e) {
        }
    }

    public void limparCadastro() {
        setCadNotaObj(null);
        setCadNotaObjPK(null);
        setHisCad(null);
        setOsCad(null);
    }

    public CadNota getCadNotaObj() {
        if (cadNotaObj == null) {
            cadNotaObj = new CadNota();
        }
        return cadNotaObj;
    }

    public void setCadNotaObj(CadNota cadNotaObj) {
        this.cadNotaObj = cadNotaObj;
    }

    public CadNotaPK getCadNotaObjPK() {
        if (cadNotaObjPK == null) {
            cadNotaObjPK = new CadNotaPK();
        }
        return cadNotaObjPK;
    }

    public void setCadNotaObjPK(CadNotaPK cadNotaObjPK) {
        this.cadNotaObjPK = cadNotaObjPK;
    }

    public String getHisCad() {
        return hisCad;
    }

    public void setHisCad(String hisCad) {
        this.hisCad = hisCad;
    }

    public Integer getOsCad() {
        return osCad;
    }

    public void setOsCad(Integer osCad) {
        this.osCad = osCad;
    }

}
