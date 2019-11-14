/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadTarefaControle;
import com.softbean.sindmepaOs.entidade.CadTarefa;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author admin
 */
@Named(value = "cadTarefaBean")
@SessionScoped
public class CadTarefaBean implements Serializable {

    /**
     * Creates a new instance of CadTarefaBean
     */
    public CadTarefaBean() {
    }

    @Inject
    CadTarefaControle tarefaControle;
    @Inject
    LoginBean loginBean;
    @Inject
    CadAnaliseBean analiseBean;

    CadTarefa ObjCadTarefa;

    public void buscarEncaminharTarefa(String os, Integer seq) {
        try {
            setObjCadTarefa(null)/*limpar variavel*/;
            setObjCadTarefa(tarefaControle.buscarTarefaControle(os, seq));
        } catch (Exception e) {
            System.out.println("Erro no método buscarEncaminharTarefa (Tarefa) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void encaminharTarefa() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObjCadTarefa().setDtUltAtuTarefa(new Date());
            getObjCadTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjCadTarefa().setSitTarefa("02");

            if (tarefaControle.alterarTarefaControle(getObjCadTarefa())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Tarefa Encaminhado Para Atendimento com Sucesso."));
                context.execute("PF('dlConfirm').hide()");
                analiseBean.pesquisarTarefa();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Erro ao encaminhar Tarefa Para Atendimento."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método encaminharTarefa " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cancelarTarefa() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObjCadTarefa().setDtUltAtuTarefa(new Date());
            getObjCadTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjCadTarefa().setSitTarefa("05");
            getObjCadTarefa().setDtFechaTarefa(new Date());
            if (tarefaControle.alterarTarefaControle(getObjCadTarefa())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Tarefa Cancelado com Sucesso."));
                context.execute("PF('dlCancelTarefa').hide()");
                analiseBean.pesquisarTarefa();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Cancelar Tarefa."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método cancelarTarefa " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterarTarefa() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObjCadTarefa().setDtUltAtuTarefa(new Date());
            getObjCadTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            if (tarefaControle.alterarTarefaControle(getObjCadTarefa())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Alteração da Tarefa Realizado com Sucesso."));
                context.execute("PF('dlAltTarefa').hide()");
                analiseBean.pesquisarTarefa();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Alterar Tarefa."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método alterar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public CadTarefa getObjCadTarefa() {
        if (ObjCadTarefa == null) {
            ObjCadTarefa = new CadTarefa();
        }
        return ObjCadTarefa;
    }

    public void setObjCadTarefa(CadTarefa ObjCadTarefa) {
        this.ObjCadTarefa = ObjCadTarefa;
    }

}
