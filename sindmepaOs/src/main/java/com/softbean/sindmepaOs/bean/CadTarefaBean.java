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
import java.util.List;
import java.util.Map;
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
    List<Map<String, Object>> ObjVerTarefa;

    String VtarefaOs;
    String VtarefaPrioridade;
    String VtarefaCategoria;
    String VtarefaSetorResponsavel;
    String VtarefaDataHoraAbert;
    String VtarefaDataHoraFecha;
    String VtarefaSit;
    String VtarefaCdSit;
    String VtarefaHistorico;
    String VtarefaObservacao;
    String VtarefasSetorAbertura;
    Integer VtarefaCdSetorAbertura;
    Integer VtarefaCdCategoria;
    String VtarefaFuncAbert;
    String VtarefaDescFinal;

    public String analiseTarefa(String os, Integer seq) {
        RequestContext context = RequestContext.getCurrentInstance();
        String ret = null;
        try {
            setObjVerTarefa(null);
            setObjVerTarefa(tarefaControle.verTarefa(os, seq));
            for (Map<String, Object> elemento : getObjVerTarefa()) {

                setVtarefaOs((String) elemento.get("nr_os_tarefa"));
                setVtarefaCategoria((String) elemento.get("nr_tarefa"));
                setVtarefaFuncAbert((String) elemento.get("nome_abert"));
                setVtarefasSetorAbertura((String) elemento.get("nm_abert_respon"));
                setVtarefaDataHoraAbert((String) elemento.get("data_hora_abert"));
                setVtarefaDataHoraFecha((String) elemento.get("data_hora_fecha"));
                setVtarefaSit((String) elemento.get("sit_tarefa"));
                setVtarefaCdSit((String) elemento.get("cd_sit_tarefa"));
                setVtarefaCdCategoria((Integer) elemento.get("seq_tarefa"));
                setVtarefaPrioridade((String) elemento.get("prioridade"));
                setVtarefaHistorico((String) elemento.get("hist_tarefa"));
                setVtarefaObservacao((String) elemento.get("obs_tarefa"));
                setVtarefaDescFinal((String) elemento.get("hist_fecha_tarefa"));
            }
            if (getVtarefaCdSit().equals("02")) {
                context.execute("PF('dlConfirmTarefa').show()");
                context.update(":frmDlConfirmTarefa");
            } else {
                context.update(":frmAnaliseTarefa");
                ret = "analisetarefa";
            }
        } catch (Exception e) {
            System.out.println("Erro no método analiseTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }

    public String iniciarAnaliseTarefa() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjCadTarefa(null)/*limpar variavel*/;
            setObjCadTarefa(tarefaControle.buscarTarefaControle(getVtarefaOs(), getVtarefaCdCategoria()));
            getObjCadTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjCadTarefa().setDtUltAtuTarefa(new Date());
            getObjCadTarefa().setFuncResponTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjCadTarefa().setSitTarefa("03");
            if (tarefaControle.alterarTarefaControle(getObjCadTarefa())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Análise da tarefa Iniciada com Sucesso."));
                context.execute("PF('dlConfirmTarefa').hide()");
                context.update(":frmAnaliseTarefa");
                return "analisetarefa";
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao iniciar Análise da tarefa."));
                return "";
            }
        } catch (Exception e) {
            System.out.println("Erro no método iniciarAnaliseTarefa " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

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

    public String renderizarFinalizaçãoTarefa() {
        if ("06".equals(getVtarefaCdSit()) || "07".equals(getVtarefaCdSit())) {
            return "true";
        } else {
            return "false";
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

    public List<Map<String, Object>> getObjVerTarefa() {
        return ObjVerTarefa;
    }

    public void setObjVerTarefa(List<Map<String, Object>> ObjVerTarefa) {
        this.ObjVerTarefa = ObjVerTarefa;
    }

    public String getVtarefaOs() {
        return VtarefaOs;
    }

    public void setVtarefaOs(String VtarefaOs) {
        this.VtarefaOs = VtarefaOs;
    }

    public String getVtarefaPrioridade() {
        return VtarefaPrioridade;
    }

    public void setVtarefaPrioridade(String VtarefaPrioridade) {
        this.VtarefaPrioridade = VtarefaPrioridade;
    }

    public String getVtarefaCategoria() {
        return VtarefaCategoria;
    }

    public void setVtarefaCategoria(String VtarefaCategoria) {
        this.VtarefaCategoria = VtarefaCategoria;
    }

    public String getVtarefaSetorResponsavel() {
        return VtarefaSetorResponsavel;
    }

    public void setVtarefaSetorResponsavel(String VtarefaSetorResponsavel) {
        this.VtarefaSetorResponsavel = VtarefaSetorResponsavel;
    }

    public String getVtarefaDataHoraAbert() {
        return VtarefaDataHoraAbert;
    }

    public void setVtarefaDataHoraAbert(String VtarefaDataHoraAbert) {
        this.VtarefaDataHoraAbert = VtarefaDataHoraAbert;
    }

    public String getVtarefaDataHoraFecha() {
        return VtarefaDataHoraFecha;
    }

    public void setVtarefaDataHoraFecha(String VtarefaDataHoraFecha) {
        this.VtarefaDataHoraFecha = VtarefaDataHoraFecha;
    }

    public String getVtarefaSit() {
        return VtarefaSit;
    }

    public void setVtarefaSit(String VtarefaSit) {
        this.VtarefaSit = VtarefaSit;
    }

    public String getVtarefaCdSit() {
        return VtarefaCdSit;
    }

    public void setVtarefaCdSit(String VtarefaCdSit) {
        this.VtarefaCdSit = VtarefaCdSit;
    }

    public String getVtarefaHistorico() {
        return VtarefaHistorico;
    }

    public void setVtarefaHistorico(String VtarefaHistorico) {
        this.VtarefaHistorico = VtarefaHistorico;
    }

    public String getVtarefaObservacao() {
        return VtarefaObservacao;
    }

    public void setVtarefaObservacao(String VtarefaObservacao) {
        this.VtarefaObservacao = VtarefaObservacao;
    }

    public String getVtarefasSetorAbertura() {
        return VtarefasSetorAbertura;
    }

    public void setVtarefasSetorAbertura(String VtarefasSetorAbertura) {
        this.VtarefasSetorAbertura = VtarefasSetorAbertura;
    }

    public Integer getVtarefaCdSetorAbertura() {
        return VtarefaCdSetorAbertura;
    }

    public void setVtarefaCdSetorAbertura(Integer VtarefaCdSetorAbertura) {
        this.VtarefaCdSetorAbertura = VtarefaCdSetorAbertura;
    }

    public Integer getVtarefaCdCategoria() {
        return VtarefaCdCategoria;
    }

    public void setVtarefaCdCategoria(Integer VtarefaCdCategoria) {
        this.VtarefaCdCategoria = VtarefaCdCategoria;
    }

    public String getVtarefaFuncAbert() {
        return VtarefaFuncAbert;
    }

    public void setVtarefaFuncAbert(String VtarefaFuncAbert) {
        this.VtarefaFuncAbert = VtarefaFuncAbert;
    }

    public String getVtarefaDescFinal() {
        return VtarefaDescFinal;
    }

    public void setVtarefaDescFinal(String VtarefaDescFinal) {
        this.VtarefaDescFinal = VtarefaDescFinal;
    }

}
