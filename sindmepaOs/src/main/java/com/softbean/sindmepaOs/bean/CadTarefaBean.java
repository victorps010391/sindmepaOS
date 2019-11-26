/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadNotaControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.controle.CadTarefaControle;
import com.softbean.sindmepaOs.entidade.CadNota;
import com.softbean.sindmepaOs.entidade.CadNotaPK;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadTarefa;
import com.softbean.sindmepaOs.entidade.CadTarefaPK;
import com.softbean.sindmepaOs.entidade.CadTarefaVer;
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
    CadOsControle osControle;
    @Inject
    CadNotaControle notaControle;
    @Inject
    LoginBean loginBean;
    @Inject
    CadAnaliseBean analiseBean;

    CadTarefa ObjCadTarefa;
    CadTarefaPK ObjCadTarefaPk;
//    CadTarefa ObjSalvTarefa;
//    CadTarefaPK ObjSalvTarefaPk;
    CadOs ObjCadOs;
    CadNota cadNotaObj;
    CadNotaPK cadNotaObjPK;
    CadTarefaVer objVerTa;

    List<Map<String, Object>> ObjVerTarefa;
    List<Map<String, Object>> listarTarefa;
    List<Map<String, Object>> notaAnalise;
    List<Map<String, Object>> listarFinalizacao;
    List<Map<String, Object>> ObjVer;

    Integer cdSetor;
    String hisTarefa;
    String obsTarefa;
    String hisNota;
    String sitFinal;
    String descFinal;

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
                context.update(":frmDlConfirmTarefa :gridTarefa :gridNota");
            } else {
                limparCadastroNota();
                limparCadastroTarefa();
                pesquisarTarefa();
                context.update(":frmAnaliseTarefa :gridTarefa :gridNota");
                ret = "analisetarefa";
            }
        } catch (Exception e) {
            System.out.println("Erro no método analiseTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return ret;
    }

    public void visualizar(String os, Integer seq) {
        try {
            setObjVer(null);//limpar variavel
            setObjVerTa(null);//limpar variavel
            setObjVerTa(new CadTarefaVer());

            setObjVer(tarefaControle.verTarefa(os, seq));
            for (Map<String, Object> elemento : getObjVer()) {
                getObjVerTa().setDataHoraAbert((String) elemento.get("data_hora_abert"));
                getObjVerTa().setDataHoraFecha((String) elemento.get("data_hora_fecha"));
                getObjVerTa().setHistFechaTarefa((String) elemento.get("hist_fecha_tarefa"));
                getObjVerTa().setHistTarefa((String) elemento.get("hist_tarefa"));
                getObjVerTa().setNomeRespon((String) elemento.get("nome_respon"));
                getObjVerTa().setNomeAbert((String) elemento.get("nome_abert"));
                getObjVerTa().setNomeSetor((String) elemento.get("nome_setor"));
                getObjVerTa().setNomeSetorRespon((String) elemento.get("nome_setor_respon"));
                getObjVerTa().setNrOsTarefa((String) elemento.get("nr_os_tarefa"));
                getObjVerTa().setNrTarefa((String) elemento.get("nr_tarefa"));
                getObjVerTa().setObsTarefa((String) elemento.get("obs_tarefa"));
                getObjVerTa().setPrioridade((String) elemento.get("prioridade"));
                getObjVerTa().setSitTarefa((String) elemento.get("sit_tarefa"));
                getObjVerTa().setSeqTarefa((Integer) elemento.get("seq_tarefa"));
            }
        } catch (Exception e) {
            System.out.println("Erro no método visualizar (Tarefa) " + e.getMessage());
            e.printStackTrace();
        }
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
                pesquisarTarefa();
                limparCadastroNota();
                limparCadastroTarefa();
                context.update(":frmAnaliseTarefa :gridTarefa :gridNota");
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

    public void encaminharTarefaOs() {
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
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao encaminhar Tarefa Para Atendimento."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método encaminharTarefa " + e.getMessage());
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
                pesquisarTarefa();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Erro ao encaminhar Tarefa Para Atendimento."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método encaminharTarefa " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvarTarefaAnalise() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        setObjCadTarefa(null)/*limpar variavel*/;
        try {
            if (getObjCadTarefa().getCadTarefaPK() == null) {
                setObjCadTarefaPk(new CadTarefaPK());
                getObjCadTarefaPk().setNrOsTarefa(getVtarefaOs());
                getObjCadTarefaPk().setSeqTarefa(tarefaControle.retornaSeqTarefa(Integer.parseInt(getVtarefaOs())));

                setObjCadTarefa(new CadTarefa());
                getObjCadTarefa().setDtAbertTarefa(new Date());
                getObjCadTarefa().setFuncAbertTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                getObjCadTarefa().setFuncResponTarefa(999);
                getObjCadTarefa().setHistTarefa(getHisTarefa());
                getObjCadTarefa().setObsTarefa(getObsTarefa());
                getObjCadTarefa().setSetorAbertTarefa(loginBean.getUsuario().getSetorFunc().getCdSetor());
                getObjCadTarefa().setSetorResponTarefa(getCdSetor());
                getObjCadTarefa().setSitTarefa("01");
                getObjCadTarefa().setDtUltAtuTarefa(new Date());
                getObjCadTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                setObjCadOs(null);
                setObjCadOs(osControle.buscarOsControle(Integer.parseInt(getVtarefaOs())));
                getObjCadOs().setSitOs("04");
                getObjCadOs().setDtUltAtuOs(new Date());
                getObjCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                if (osControle.alterarOsControle(getObjCadOs())) {
                    if (tarefaControle.salvarTarefaControle(getObjCadTarefa(), getObjCadTarefaPk())) {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro de Tarefa Realizado com Sucesso."));
                        context.execute("PF('dlCadTarTarefa').hide()");
                        pesquisarTarefa();
                        limparCadastroTarefa();
                        limparCadastroNota();
                    } else {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Tarefa."));
                    }
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Tarefa."));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no método salvarTarefaAnalise Tarefa " + e.getMessage());
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
                if (osControle.validarFinalizacao(Integer.parseInt(getVtarefaOs())) == 0) {
                    setObjCadOs(null);
                    setObjCadOs(osControle.buscarOsControle(Integer.parseInt(getVtarefaOs())));
                    getObjCadOs().setSitOs("03");
                    getObjCadOs().setDtUltAtuOs(new Date());
                    getObjCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                    osControle.alterarOsControle(getObjCadOs());
                }
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Tarefa Cancelado com Sucesso."));
                context.execute("PF('dlCancelTarefa').hide()");
                pesquisarTarefa();
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
                pesquisarTarefa();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Alterar Tarefa."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método alterar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvarNotaAnalise() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            if (getCadNotaObj().getCadNotaPK() == null) {
                setCadNotaObjPK(new CadNotaPK());
                getCadNotaObjPK().setNrOsNota(Integer.parseInt(getVtarefaOs()));
                getCadNotaObjPK().setSerialNota(notaControle.retornaSeqNota(Integer.parseInt(getVtarefaOs())));

                setCadNotaObj(new CadNota());
                getCadNotaObj().setHistNota(getHisNota());
                getCadNotaObj().setDtRegiNota(new Date());
                getCadNotaObj().setFuncRegiNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                getCadNotaObj().setDtUltAtuNota(new Date());
                getCadNotaObj().setFuncUltAtuNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                if (notaControle.salvarNotaControle(getCadNotaObj(), getCadNotaObjPK())) {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro de Nota Realizado com Sucesso."));
                    context.update("@form :frmAnaliseTarefa");
                    context.execute("PF('dlCadNotaAnalise').hide()");
                    pesquisarNota();
                    limparCadastroNota();
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Nota."));
                    context.update("@form :frmAnaliseTarefa");
                }
            }
        } catch (Exception e) {
        }
    }

    public void finalizarAnalise() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjCadTarefa(null)/*limpar variavel*/;
            setObjCadTarefa(tarefaControle.buscarTarefaControle(getVtarefaOs(), getVtarefaCdCategoria()));
            getObjCadTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjCadTarefa().setDtUltAtuTarefa(new Date());
            getObjCadTarefa().setFuncResponTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjCadTarefa().setSitTarefa(getSitFinal());
            getObjCadTarefa().setHistFechaTarefa(getDescFinal());
            getObjCadTarefa().setDtFechaTarefa(new Date());
            if (tarefaControle.alterarTarefaControle(getObjCadTarefa())) {
                if (osControle.validarFinalizacao(Integer.parseInt(getVtarefaOs())) == 0) {
                    setObjCadOs(null);
                    setObjCadOs(osControle.buscarOsControle(Integer.parseInt(getVtarefaOs())));
                    getObjCadOs().setSitOs("03");
                    getObjCadOs().setDtUltAtuOs(new Date());
                    getObjCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                    osControle.alterarOsControle(getObjCadOs());
                }
                limparFinalização();
                context.execute("PF('dlResolvOs').hide()");
                visualizar(getObjCadTarefa().getCadTarefaPK().getNrOsTarefa(), getObjCadTarefa().getCadTarefaPK().getSeqTarefa());
                analiseTarefa(getObjCadTarefa().getCadTarefaPK().getNrOsTarefa(), getObjCadTarefa().getCadTarefaPK().getSeqTarefa());
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Tarefa finalizada com sucesso."));
            } else {
                limparFinalização();
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao finalizar tarefa."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método finalizarAnalise " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisarNota() {
        try {
            setNotaAnalise(null);
            setNotaAnalise(notaControle.gridSecundario(Integer.parseInt(getVtarefaOs())));
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarNota (TarefaBean)" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisarTarefa() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            setListarTarefa(null);
            setListarTarefa(tarefaControle.gridTarefa(Integer.parseInt(getVtarefaOs()), loginBean.getUsuario().getCadFuncionarioPK().getCdFunc()));
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarTarefa (TarefaBean)" + e.getMessage());
            e.printStackTrace();
        }
    }

//    public void pesquisarTarefaOs() {
//        RequestContext context = RequestContext.getCurrentInstance();
//        try {
//            tarefaControle.setListarTarefa(null);
//            setListarTarefa(tarefaControle.gridTarefa(analiseBean.getVos(), loginBean.getUsuario().getCadFuncionarioPK().getCdFunc()));
//            context.update(":frmAnaliseOs :gridNota :gridTarefa");
//        } catch (Exception e) {
//            System.out.println("Erro no método pesquisarTarefaOs (TarefaBean)" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    public void limparFinalização() {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            if (tarefaControle.validarFinalizacaoTarefa(getVtarefaOs(), loginBean.getUsuario().getCadFuncionarioPK().getCdFunc()) == 0) {
                setListarFinalizacao(null);
                setSitFinal(null);
                setDescFinal(null);
                context.execute("PF('dlResolvOs').show()");
                context.update(":frmDlResolvOs");
            } else {
                context.execute("PF('dlValida').show()");
            }
        } catch (Exception e) {
            System.out.println("Erro no método limparFinalização (Tarefa) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void limparCadastroNota() {
        setCadNotaObj(null);
        setCadNotaObjPK(null);
        setHisNota(null);
    }

    public void limparCadastroTarefa() {
        setObjCadTarefa(null);
        setObjCadTarefaPk(null);
        setHisTarefa(null);
        setObsTarefa(null);
        setCdSetor(null);
    }

    public String renderizarFinalizaçãoTarefa() {
        if ("06".equals(getVtarefaCdSit()) || "07".equals(getVtarefaCdSit())) {
            return "true";
        } else {
            return "false";
        }
    }

    public List<Map<String, Object>> listarFinalizacao() {
        try {
            setListarFinalizacao(tarefaControle.listarSitFinalizacaoTarefa());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarFinalizacao " + e.getMessage());
        }
        return getListarFinalizacao();
    }

    public CadTarefaVer getObjVerTa() {
        return objVerTa;
    }

    public void setObjVerTa(CadTarefaVer objVerTa) {
        this.objVerTa = objVerTa;
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

    public CadTarefa getObjCadTarefa() {
        if (ObjCadTarefa == null) {
            ObjCadTarefa = new CadTarefa();
        }
        return ObjCadTarefa;
    }

    public void setObjCadTarefa(CadTarefa ObjCadTarefa) {
        this.ObjCadTarefa = ObjCadTarefa;
    }

//    public CadTarefa getObjSalvTarefa() {
//        if (ObjSalvTarefa == null) {
//            ObjSalvTarefa = new CadTarefa();
//        }
//        return ObjSalvTarefa;
//    }
//
//    public void setObjSalvTarefa(CadTarefa ObjSalvTarefa) {
//        this.ObjSalvTarefa = ObjSalvTarefa;
//    }
//
//    public CadTarefaPK getObjSalvTarefaPk() {
//        if (ObjSalvTarefaPk == null) {
//            ObjSalvTarefaPk = new CadTarefaPK();
//        }
//        return ObjSalvTarefaPk;
//    }
//
//    public void setObjSalvTarefaPk(CadTarefaPK ObjSalvTarefaPk) {
//        this.ObjSalvTarefaPk = ObjSalvTarefaPk;
//    }
    public CadTarefaPK getObjCadTarefaPk() {
        if (ObjCadTarefaPk == null) {
            ObjCadTarefaPk = new CadTarefaPK();
        }
        return ObjCadTarefaPk;
    }

    public CadOs getObjCadOs() {
        if (ObjCadOs == null) {
            ObjCadOs = new CadOs();
        }
        return ObjCadOs;
    }

    public void setObjCadOs(CadOs ObjCadOs) {
        this.ObjCadOs = ObjCadOs;
    }

    public void setObjCadTarefaPk(CadTarefaPK ObjCadTarefaPk) {
        this.ObjCadTarefaPk = ObjCadTarefaPk;
    }

    public List<Map<String, Object>> getNotaAnalise() {
        return notaAnalise;
    }

    public void setNotaAnalise(List<Map<String, Object>> notaAnalise) {
        this.notaAnalise = notaAnalise;
    }

    public List<Map<String, Object>> getObjVer() {
        return ObjVer;
    }

    public void setObjVer(List<Map<String, Object>> ObjVer) {
        this.ObjVer = ObjVer;
    }

    public List<Map<String, Object>> getObjVerTarefa() {
        return ObjVerTarefa;
    }

    public void setObjVerTarefa(List<Map<String, Object>> ObjVerTarefa) {
        this.ObjVerTarefa = ObjVerTarefa;
    }

    public List<Map<String, Object>> getListarTarefa() {
        return listarTarefa;
    }

    public void setListarTarefa(List<Map<String, Object>> listarTarefa) {
        this.listarTarefa = listarTarefa;
    }

    public List<Map<String, Object>> getListarFinalizacao() {
        return listarFinalizacao;
    }

    public void setListarFinalizacao(List<Map<String, Object>> listarFinalizacao) {
        this.listarFinalizacao = listarFinalizacao;
    }

    public String getSitFinal() {
        return sitFinal;
    }

    public void setSitFinal(String sitFinal) {
        this.sitFinal = sitFinal;
    }

    public String getDescFinal() {
        return descFinal;
    }

    public void setDescFinal(String descFinal) {
        this.descFinal = descFinal;
    }

    public String getHisNota() {
        return hisNota;
    }

    public void setHisNota(String hisNota) {
        this.hisNota = hisNota;
    }

    public Integer getCdSetor() {
        return cdSetor;
    }

    public void setCdSetor(Integer cdSetor) {
        this.cdSetor = cdSetor;
    }

    public String getHisTarefa() {
        return hisTarefa;
    }

    public void setHisTarefa(String hisTarefa) {
        this.hisTarefa = hisTarefa;
    }

    public String getObsTarefa() {
        return obsTarefa;
    }

    public void setObsTarefa(String obsTarefa) {
        this.obsTarefa = obsTarefa;
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
