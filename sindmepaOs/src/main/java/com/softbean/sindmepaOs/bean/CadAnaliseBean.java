/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadAnaliseControle;
import com.softbean.sindmepaOs.controle.CadExternoControle;
import com.softbean.sindmepaOs.controle.CadNotaControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.controle.CadTarefaControle;
import com.softbean.sindmepaOs.entidade.CadExterno;
import com.softbean.sindmepaOs.entidade.CadNota;
import com.softbean.sindmepaOs.entidade.CadNotaPK;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadTarefa;
import com.softbean.sindmepaOs.entidade.CadTarefaPK;
import com.softbean.sindmepaOs.util.MailUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author admin
 */
@Named(value = "cadAnaliseBean")
@SessionScoped
public class CadAnaliseBean implements Serializable {

    /**
     * Creates a new instance of CadAnalise
     */
    public CadAnaliseBean() {
    }

    @Inject
    CadAnaliseControle analiseControle;
    @Inject
    CadOsControle osControle;
    @Inject
    CadNotaControle notaControle;
    @Inject
    CadTarefaControle tarefaControle;
    @Inject
    LoginBean loginBean;
    @Inject
    CadExternoControle externoControle;
    @Inject
    MailUtil mailUtil;

    CadOs objOs;
    CadNota cadNotaObj;
    CadNotaPK cadNotaObjPK;
    CadTarefa objTarefa;
    CadTarefaPK objTarefaPk;
    CadExterno cadExternoObj;

    Integer nrOs;
    String priorPesq;
    List<Map<String, Object>> grid01;
    List<Map<String, Object>> grid02;
    List<Map<String, Object>> grid03;
    List<Map<String, Object>> gridTarefa;
    List<Map<String, Object>> priorListaPesq;
    List<Map<String, Object>> verOs;
    List<Map<String, Object>> verDadosSindicais;
    List<Map<String, Object>> listarFinalizacao;
    List<Map<String, Object>> notaAnalise;
    List<Map<String, Object>> tarefaAnalise;
    List<Map<String, Object>> setorTarefa;

    Integer Vos, VfuncAbert;
    String Vprioridade;
    String Vcategoria;
    String Vsetor_responsavel;
    String Vdata_hora_abert;
    String Vdata_hora_fecha;
    String Vsit;
    String Vcd_sit;
    String Vhistorico;
    String Vobservacao;
    String Vsetor_abertura;
    Integer Vcod_setor_abertura;
    Integer Vcod_categoria;
    String Vfunc_abert;
    String Vdesc_final;
    String sitFinal;
    String descFinal;
    String descNotaAnalise;
    Integer cdSetorTarefa;
    String descTarefa;
    String descObsTarefa;

    String Snome_ext;
    String Srg_ext;
    String Scpf_ext;
    String Scrm_ext;
    String Sesp_ext;
    String Sdt_nasc_ext;
    String Ssexo_ext;
    String Sendereco;
    String Scep_end;
    String Snm_end;
    String Sbairro_end;
    String Scid_end;
    String Stel_com_end;
    String Scel_end;
    String Swtp_end;
    String Semail;

    public String voltarCadAnalise() {
        try {
            PrimeFaces context = PrimeFaces.current();
            setGrid01(analiseControle.gridAnalise01(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGrid02(analiseControle.gridAnalise02(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGrid03(analiseControle.gridAnalise03(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGridTarefa(tarefaControle.gridTarefaAtendimento(getNrOs() != null ? getNrOs().toString() : null, loginBean.getUsuario().getSetorFunc().getCdSetor()));
            context.ajax().update(":frmCadAnalise");
            return "cadanalise";

        } catch (Exception e) {
            System.out.println("Erro no método voltarCadAnalise " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    public void analise(Integer os) {
        try {
            setVerOs(null);
            setVerOs(analiseControle.verOs(os));
            for (Map<String, Object> elemento : getVerOs()) {
                setVcategoria((String) elemento.get("categoria"));
                setVos((Integer) elemento.get("os"));
                setVprioridade((String) elemento.get("prioridade"));
                setVsetor_responsavel((String) elemento.get("setor_responsavel"));
                setVdata_hora_abert((String) elemento.get("data_hora_abert"));
                setVdata_hora_fecha((String) elemento.get("data_hora_fecha"));
                setVsit((String) elemento.get("sit"));
                setVcd_sit((String) elemento.get("cd_sit"));
                setVhistorico((String) elemento.get("historico"));
                setVobservacao((String) elemento.get("observacao"));
                setVsetor_abertura((String) elemento.get("setor_abertura"));
                setVcod_setor_abertura((Integer) elemento.get("cod_setor_abert"));
                setVcod_categoria((Integer) elemento.get("cod_categoria"));
                setVfunc_abert((String) elemento.get("func_abert"));
                setVdesc_final((String) elemento.get("desc_finalizacao"));
                setVfuncAbert((Integer) elemento.get("func_abert_os"));
            }
            setCadExternoObj(externoControle.retornaExt(getVfuncAbert()));
            limparCadastroNota();
            limparCadastroTarefa();
        } catch (Exception e) {
            System.out.println("Erro no método analise " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String iniciarAnalise() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjOs(null);
            setObjOs(osControle.buscarOsControle(getVos()));
            getObjOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjOs().setDtUltAtuOs(new Date());
            getObjOs().setFuncResponOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjOs().setSitOs("03");
            if (osControle.alterarOsControle(getObjOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Análise do protocolo: " + getObjOs().getNrOs() + " Iniciada com Sucesso."));
                context.executeScript("PF('dlConfirm').hide()");
                setNotaAnalise(null);
                setTarefaAnalise(null);
                setNotaAnalise(notaControle.gridSecundario(getVos()));
                setTarefaAnalise(tarefaControle.gridTarefa(getVos(), loginBean.getUsuario().getCadFuncionarioPK().getCdFunc()));
                disparaEmailInicioAtendimento(getCadExternoObj().getEmail());
                context.ajax().update(":frmAnaliseOs :gridNota :gridTarefa");
                return "analise";
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao iniciar Análise do protocolo: " + getObjOs().getNrOs() + "."));
                return "";
            }
        } catch (Exception e) {
            System.out.println("Erro no método iniciarAnalise " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    public Boolean disparaEmailInicioAtendimento(String destinatario) {
        try {
            String assunto = "ATENDIMENTO DE PROTOCOLO INICIADO";

            SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder corpoEmailAbertura = new StringBuilder();
            corpoEmailAbertura.append("<p style='font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;'>SindmepaProtocol informa,<br />");
            corpoEmailAbertura.append("Foi iniciado, na data de hoje, o atendimento do seu protocolo com as seguintes especificações: <br /><br />");
            corpoEmailAbertura.append("<strong>Número do protocolo: </strong>");
            corpoEmailAbertura.append(getVos());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Categoria: </strong>");
            corpoEmailAbertura.append(getVcategoria());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Setor Responsável: </strong>");
            corpoEmailAbertura.append(getVsetor_responsavel());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Reponsável Pelo Atendimento: </strong>");
            corpoEmailAbertura.append(loginBean.getUsuario().getNmFunc());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Prioridade: </strong>");
            corpoEmailAbertura.append(getVprioridade());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Solicitação: </strong>");
            corpoEmailAbertura.append(getVhistorico());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Data de Abertura: </strong>");
            corpoEmailAbertura.append(getVdata_hora_abert());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<br /><br />");
            corpoEmailAbertura.append("<i>Email Enviado automaticamente pelo sistema ");
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Data: ");
            corpoEmailAbertura.append(formate.format(new Date()));
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Softbean ©");
            corpoEmailAbertura.append("</i></p>");

            mailUtil.enviar(assunto, destinatario, corpoEmailAbertura.toString());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String analiseIniciada(Integer os) {
        PrimeFaces context = PrimeFaces.current();
        try {
            setVerOs(null);
            setVerOs(analiseControle.verOs(os));
            for (Map<String, Object> elemento : getVerOs()) {
                setVcategoria((String) elemento.get("categoria"));
                setVos((Integer) elemento.get("os"));
                setVprioridade((String) elemento.get("prioridade"));
                setVsetor_responsavel((String) elemento.get("setor_responsavel"));
                setVdata_hora_abert((String) elemento.get("data_hora_abert"));
                setVdata_hora_fecha((String) elemento.get("data_hora_fecha"));
                setVsit((String) elemento.get("sit"));
                setVcd_sit((String) elemento.get("cd_sit"));
                setVhistorico((String) elemento.get("historico"));
                setVobservacao((String) elemento.get("observacao"));
                setVsetor_abertura((String) elemento.get("setor_abertura"));
                setVcod_setor_abertura((Integer) elemento.get("cod_setor_abert"));
                setVcod_categoria((Integer) elemento.get("cod_categoria"));
                setVfunc_abert((String) elemento.get("func_abert"));
                setVdesc_final((String) elemento.get("desc_finalizacao"));
                setVfuncAbert((Integer) elemento.get("func_abert_os"));
            }
            setNotaAnalise(null);
            setTarefaAnalise(null);
            setNotaAnalise(notaControle.gridSecundario(getVos()));
            setTarefaAnalise(tarefaControle.gridTarefa(getVos(), loginBean.getUsuario().getCadFuncionarioPK().getCdFunc()));
            limparCadastroNota();
            limparCadastroTarefa();
            context.ajax().update(":frmAnaliseOs :gridNota :gridTarefa");
            return "analise";

        } catch (Exception e) {
            System.out.println("Erro no método analiseIniciada " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    public void carregaDadosSindicais() {
        try {
            setVerDadosSindicais(null);
            setVerDadosSindicais(osControle.verDadosSindicais(getVfuncAbert()));
            for (Map<String, Object> elemento : getVerDadosSindicais()) {

                setSnome_ext((String) elemento.get("nome_ext"));
                setSrg_ext((String) elemento.get("rg_ext"));
                setScpf_ext((String) elemento.get("cpf_ext"));
                setScrm_ext((String) elemento.get("crm_ext"));
                setSesp_ext((String) elemento.get("esp_ext"));
                setSdt_nasc_ext((String) elemento.get("dt_nasc_ext"));
                setSsexo_ext((String) elemento.get("sexo_ext"));
                setSendereco((String) elemento.get("endereco"));
                setScep_end((String) elemento.get("cep_end"));
                setSnm_end((String) elemento.get("nm_end"));
                setSbairro_end((String) elemento.get("bairro_end"));
                setScid_end((String) elemento.get("cid_end"));
                setStel_com_end((String) elemento.get("tel_com_end"));
                setScel_end((String) elemento.get("cel_end"));
                setSwtp_end((String) elemento.get("wtp_end"));
                setSemail((String) elemento.get("email"));
            }

        } catch (Exception e) {
            System.out.println("Erro no método carregaDadosSindicais " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void finalizarAnalise() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjOs(null);
            setObjOs(osControle.buscarOsControle(getVos()));
            getObjOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjOs().setDtUltAtuOs(new Date());
            getObjOs().setFuncFinaliOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObjOs().setDtFechaOs(new Date());
            getObjOs().setDescFinalizacaoOs(getDescFinal());
            getObjOs().setSitOs(getSitFinal());
            if (osControle.alterarOsControle(getObjOs())) {
                context.executeScript("PF('dlResolvOs').hide()");
                analise(getObjOs().getNrOs());
                disparaEmailAtendimentoFinalizado(getCadExternoObj().getEmail(), getDescFinal());
                limparFinalização();
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Protocolo: " + getObjOs().getNrOs() + " finalizado com sucesso."));
            } else {
                limparFinalização();
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao finalizar protocolo: " + getObjOs().getNrOs() + "."));
            }

        } catch (Exception e) {
            System.out.println("Erro no método finalizarAnalise " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Boolean disparaEmailAtendimentoFinalizado(String destinatario, String resolucao) {
        try {
            String assunto = "ATENDIMENTO DE PROTOCOLO FINALIZADO";

            SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder corpoEmailAbertura = new StringBuilder();
            corpoEmailAbertura.append("<p style='font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;'>SindmepaProtocol informa,<br />");
            corpoEmailAbertura.append("Seu protocolo foi finalizado hoje com as seguintes especificações: <br /><br />");
            corpoEmailAbertura.append("<strong>Número do protocolo: </strong>");
            corpoEmailAbertura.append(getVos());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Categoria: </strong>");
            corpoEmailAbertura.append(getVcategoria());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Setor Responsável: </strong>");
            corpoEmailAbertura.append(getVsetor_responsavel());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Reponsável Pelo Atendimento: </strong>");
            corpoEmailAbertura.append(loginBean.getUsuario().getNmFunc());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Prioridade: </strong>");
            corpoEmailAbertura.append(getVprioridade());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Solicitação: </strong>");
            corpoEmailAbertura.append(getVhistorico());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Data de Abertura: </strong>");
            corpoEmailAbertura.append(getVdata_hora_abert());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<br /><br />");
            corpoEmailAbertura.append("<strong>DADOS DA RESOLUÇÃO: </strong><br />");
            corpoEmailAbertura.append("<strong>Resolução: </strong>");
            corpoEmailAbertura.append(resolucao);
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<br /><br /><br />");
            corpoEmailAbertura.append("<i>Email Enviado automaticamente pelo sistema ");
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Data: ");
            corpoEmailAbertura.append(formate.format(new Date()));
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Softbean ©");
            corpoEmailAbertura.append("</i></p>");

            mailUtil.enviar(assunto, destinatario, corpoEmailAbertura.toString());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void pesquisar() {
        try {
            setGrid01(analiseControle.gridAnalise01(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGrid02(analiseControle.gridAnalise02(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGrid03(analiseControle.gridAnalise03(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGridTarefa(tarefaControle.gridTarefaAtendimento(getNrOs() != null ? getNrOs().toString() : null, loginBean.getUsuario().getSetorFunc().getCdSetor()));
        } catch (Exception e) {
            System.out.println("erro no método pesquisar(Analise) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisarMenu() {
        PrimeFaces context = PrimeFaces.current();
        try {
            setGrid01(analiseControle.gridAnalise01(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGrid02(analiseControle.gridAnalise02(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGrid03(analiseControle.gridAnalise03(getNrOs(), getPriorPesq(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGridTarefa(tarefaControle.gridTarefaAtendimento(getNrOs() != null ? getNrOs().toString() : null, loginBean.getUsuario().getSetorFunc().getCdSetor()));
            context.ajax().update(":frmCadAnalise");

        } catch (Exception e) {
            System.out.println("erro no método pesquisar(Analise) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisarNota() {
        PrimeFaces context = PrimeFaces.current();
        try {
            setNotaAnalise(null);
            setNotaAnalise(notaControle.gridSecundario(getVos()));
            context.ajax().update(":frmAnaliseOs :gridNota :gridTarefa");
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarNota " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisarTarefa() {
        PrimeFaces context = PrimeFaces.current();
        try {
            setTarefaAnalise(null);
            setTarefaAnalise(tarefaControle.gridTarefa(getVos(), loginBean.getUsuario().getCadFuncionarioPK().getCdFunc()));
            context.ajax().update(":frmAnaliseOs :gridNota :gridTarefa");
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarTarefa " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> listarSetorTarefa() {
        try {
            setSetorTarefa(tarefaControle.listarSetorTarefa(loginBean.getUsuario().getSetorFunc().getCdSetor()));
        } catch (Exception e) {
            System.out.println("Erro no metodo listarSetorTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return getSetorTarefa();
    }

    public List<Map<String, Object>> listarPriorOs() {
        try {
            setPriorListaPesq(analiseControle.listarPriorAnaliseOs());
        } catch (Exception e) {
            System.out.println("Erro no método listarPriorOs " + e.getMessage());
            e.printStackTrace();
        }
        return getPriorListaPesq();
    }

    public String dadosSindicais() {
        if (getVcod_categoria() == 0 && getVcod_setor_abertura() == 0) {
            return "true";
        } else {
            return "false";
        }
    }

    public String renderizarFinalização() {
        if ("05".equals(getVcd_sit()) || "07".equals(getVcd_sit())) {
            return "true";
        } else {
            return "false";
        }
    }

    public void salvarTarefaAnalise() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            if (getObjTarefa().getCadTarefaPK() == null) {
                setObjTarefaPk(new CadTarefaPK());
                getObjTarefaPk().setNrOsTarefa(getVos().toString());
                getObjTarefaPk().setSeqTarefa(tarefaControle.retornaSeqTarefa(getVos()));

                setObjTarefa(new CadTarefa());
                getObjTarefa().setDtAbertTarefa(new Date());
                getObjTarefa().setFuncAbertTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                getObjTarefa().setFuncResponTarefa(999);
                getObjTarefa().setHistTarefa(getDescTarefa());
                getObjTarefa().setObsTarefa(getDescObsTarefa());
                getObjTarefa().setSetorAbertTarefa(loginBean.getUsuario().getSetorFunc().getCdSetor());
                getObjTarefa().setSetorResponTarefa(getCdSetorTarefa());
                getObjTarefa().setSitTarefa("01");
                getObjTarefa().setDtUltAtuTarefa(new Date());
                getObjTarefa().setFuncUltAtuTarefa(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                setObjOs(null);
                setObjOs(osControle.buscarOsControle(getVos()));
                getObjOs().setSitOs("04");
                getObjOs().setDtUltAtuOs(new Date());
                getObjOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                if (osControle.alterarOsControle(getObjOs())) {
                    if (tarefaControle.salvarTarefaControle(getObjTarefa(), getObjTarefaPk())) {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro de Tarefa Realizado com Sucesso."));
                        context.executeScript("PF('dlCadTar').hide()");
                        pesquisarTarefa();
                        limparCadastroTarefa();
                    } else {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Tarefa."));
                    }
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Tarefa."));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no método salvarTarefaAnalise " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvarNotaAnalise() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            if (getCadNotaObj().getCadNotaPK() == null) {
                setCadNotaObjPK(new CadNotaPK());
                getCadNotaObjPK().setNrOsNota(getVos());
                getCadNotaObjPK().setSerialNota(notaControle.retornaSeqNota(getVos()));

                setCadNotaObj(new CadNota());
                getCadNotaObj().setHistNota(getDescNotaAnalise());
                getCadNotaObj().setDtRegiNota(new Date());
                getCadNotaObj().setFuncRegiNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                getCadNotaObj().setDtUltAtuNota(new Date());
                getCadNotaObj().setFuncUltAtuNota(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                if (notaControle.salvarNotaControle(getCadNotaObj(), getCadNotaObjPK())) {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro de Nota Realizado com Sucesso."));
                    context.ajax().update(":frmAnaliseOs");
                    context.executeScript("PF('dlCadNotaAnalise').hide()");
                    pesquisarNota();
                    limparCadastroNota();
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Realizar Cadastro de Nota."));
                    context.ajax().update("@form :frmAnaliseOs");
                }
            }
        } catch (Exception e) {
        }
    }

    public void limparPesquisa() {
        setPriorListaPesq(null);
        setPriorPesq(null);
        setNrOs(null);
        setGrid01(null);
        setGrid02(null);
        setGrid03(null);
        setGridTarefa(null);
    }

    public void limparFinalização() {
        PrimeFaces context = PrimeFaces.current();
        try {
            if (osControle.validarFinalizacao(getVos()) == 0) {
                setListarFinalizacao(null);
                setSitFinal(null);
                setDescFinal(null);
                context.executeScript("PF('dlResolvOs').show()");
                context.ajax().update(":frmDlResolvOs");
            } else {
                context.executeScript("PF('dlValida').show()");
            }
        } catch (Exception e) {
            System.out.println("Erro no método limparFinalização (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void limparCadastroNota() {
        setCadNotaObj(null);
        setCadNotaObjPK(null);
        setDescNotaAnalise(null);
    }

    public void limparCadastroTarefa() {
        setObjTarefa(null);
        setObjTarefaPk(null);
        setDescTarefa(null);
        setDescObsTarefa(null);
        setCdSetorTarefa(null);
    }

    public List<Map<String, Object>> listarFinalizacao() {
        try {
            setListarFinalizacao(analiseControle.listarSitFinalizacaoOs());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarFinalizacao " + e.getMessage());
        }
        return getListarFinalizacao();
    }

    public CadOs getObjOs() {
        if (objOs == null) {
            objOs = new CadOs();
        }
        return objOs;
    }

    public void setObjOs(CadOs objOs) {
        this.objOs = objOs;
    }

    public String getDescTarefa() {
        return descTarefa;
    }

    public void setDescTarefa(String descTarefa) {
        this.descTarefa = descTarefa;
    }

    public String getDescObsTarefa() {
        return descObsTarefa;
    }

    public void setDescObsTarefa(String descObsTarefa) {
        this.descObsTarefa = descObsTarefa;
    }

    public Integer getCdSetorTarefa() {
        return cdSetorTarefa;
    }

    public void setCdSetorTarefa(Integer cdSetorTarefa) {
        this.cdSetorTarefa = cdSetorTarefa;
    }

    public String getDescNotaAnalise() {
        return descNotaAnalise;
    }

    public void setDescNotaAnalise(String descNotaAnalise) {
        this.descNotaAnalise = descNotaAnalise;
    }

    public String getDescFinal() {
        return descFinal;
    }

    public void setDescFinal(String descFinal) {
        this.descFinal = descFinal;
    }

    public String getSitFinal() {
        return sitFinal;
    }

    public void setSitFinal(String sitFinal) {
        this.sitFinal = sitFinal;
    }

    public Integer getNrOs() {
        return nrOs;
    }

    public void setNrOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public String getPriorPesq() {
        return priorPesq;
    }

    public void setPriorPesq(String priorPesq) {
        this.priorPesq = priorPesq;
    }

    public List<Map<String, Object>> getVerDadosSindicais() {
        return verDadosSindicais;
    }

    public void setVerDadosSindicais(List<Map<String, Object>> verDadosSindicais) {
        this.verDadosSindicais = verDadosSindicais;
    }

    public List<Map<String, Object>> getSetorTarefa() {
        return setorTarefa;
    }

    public void setSetorTarefa(List<Map<String, Object>> setorTarefa) {
        this.setorTarefa = setorTarefa;
    }

    public List<Map<String, Object>> getTarefaAnalise() {
        return tarefaAnalise;
    }

    public void setTarefaAnalise(List<Map<String, Object>> tarefaAnalise) {
        this.tarefaAnalise = tarefaAnalise;
    }

    public List<Map<String, Object>> getNotaAnalise() {
        return notaAnalise;
    }

    public void setNotaAnalise(List<Map<String, Object>> notaAnalise) {
        this.notaAnalise = notaAnalise;
    }

    public List<Map<String, Object>> getListarFinalizacao() {
        return listarFinalizacao;
    }

    public void setListarFinalizacao(List<Map<String, Object>> listarFinalizacao) {
        this.listarFinalizacao = listarFinalizacao;
    }

    public List<Map<String, Object>> getGridTarefa() {
        return gridTarefa;
    }

    public void setGridTarefa(List<Map<String, Object>> gridTarefa) {
        this.gridTarefa = gridTarefa;
    }

    public List<Map<String, Object>> getGrid03() {
        return grid03;
    }

    public void setGrid03(List<Map<String, Object>> grid03) {
        this.grid03 = grid03;
    }

    public List<Map<String, Object>> getGrid02() {
        return grid02;
    }

    public void setGrid02(List<Map<String, Object>> grid02) {
        this.grid02 = grid02;
    }

    public List<Map<String, Object>> getGrid01() {
        return grid01;
    }

    public void setGrid01(List<Map<String, Object>> grid01) {
        this.grid01 = grid01;
    }

    public List<Map<String, Object>> getPriorListaPesq() {
        return priorListaPesq;
    }

    public void setPriorListaPesq(List<Map<String, Object>> priorListaPesq) {
        this.priorListaPesq = priorListaPesq;
    }

    public List<Map<String, Object>> getVerOs() {
        return verOs;
    }

    public String getVdesc_final() {
        return Vdesc_final;
    }

    public void setVdesc_final(String Vdesc_final) {
        this.Vdesc_final = Vdesc_final;
    }

    public void setVerOs(List<Map<String, Object>> verOs) {
        this.verOs = verOs;
    }

    public Integer getVfuncAbert() {
        return VfuncAbert;
    }

    public void setVfuncAbert(Integer VfuncAbert) {
        this.VfuncAbert = VfuncAbert;
    }

    public Integer getVos() {
        return Vos;
    }

    public void setVos(Integer Vos) {
        this.Vos = Vos;
    }

    public String getVprioridade() {
        return Vprioridade;
    }

    public void setVprioridade(String Vprioridade) {
        this.Vprioridade = Vprioridade;
    }

    public String getVcategoria() {
        return Vcategoria;
    }

    public void setVcategoria(String Vcategoria) {
        this.Vcategoria = Vcategoria;
    }

    public String getVsetor_responsavel() {
        return Vsetor_responsavel;
    }

    public void setVsetor_responsavel(String Vsetor_responsavel) {
        this.Vsetor_responsavel = Vsetor_responsavel;
    }

    public String getVdata_hora_abert() {
        return Vdata_hora_abert;
    }

    public void setVdata_hora_abert(String Vdata_hora_abert) {
        this.Vdata_hora_abert = Vdata_hora_abert;
    }

    public String getVdata_hora_fecha() {
        return Vdata_hora_fecha;
    }

    public void setVdata_hora_fecha(String Vdata_hora_fecha) {
        this.Vdata_hora_fecha = Vdata_hora_fecha;
    }

    public String getVsit() {
        return Vsit;
    }

    public void setVsit(String Vsit) {
        this.Vsit = Vsit;
    }

    public String getVcd_sit() {
        return Vcd_sit;
    }

    public void setVcd_sit(String Vcd_sit) {
        this.Vcd_sit = Vcd_sit;
    }

    public String getVhistorico() {
        return Vhistorico;
    }

    public void setVhistorico(String Vhistorico) {
        this.Vhistorico = Vhistorico;
    }

    public String getVobservacao() {
        return Vobservacao;
    }

    public void setVobservacao(String Vobservacao) {
        this.Vobservacao = Vobservacao;
    }

    public String getVsetor_abertura() {
        return Vsetor_abertura;
    }

    public void setVsetor_abertura(String Vsetor_abertura) {
        this.Vsetor_abertura = Vsetor_abertura;
    }

    public Integer getVcod_setor_abertura() {
        return Vcod_setor_abertura;
    }

    public void setVcod_setor_abertura(Integer Vcod_setor_abertura) {
        this.Vcod_setor_abertura = Vcod_setor_abertura;
    }

    public Integer getVcod_categoria() {
        return Vcod_categoria;
    }

    public void setVcod_categoria(Integer Vcod_categoria) {
        this.Vcod_categoria = Vcod_categoria;
    }

    public String getVfunc_abert() {
        return Vfunc_abert;
    }

    public void setVfunc_abert(String Vfunc_abert) {
        this.Vfunc_abert = Vfunc_abert;
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

    public CadTarefa getObjTarefa() {
        if (objTarefa == null) {
            objTarefa = new CadTarefa();
        }
        return objTarefa;
    }

    public void setObjTarefa(CadTarefa objTarefa) {
        this.objTarefa = objTarefa;
    }

    public CadTarefaPK getObjTarefaPk() {
        if (objTarefaPk == null) {
            objTarefaPk = new CadTarefaPK();
        }
        return objTarefaPk;
    }

    public void setObjTarefaPk(CadTarefaPK objTarefaPk) {
        this.objTarefaPk = objTarefaPk;
    }

    public CadExterno getCadExternoObj() {
        if (cadExternoObj == null) {
            cadExternoObj = new CadExterno();
        }
        return cadExternoObj;
    }

    public void setCadExternoObj(CadExterno cadExternoObj) {
        this.cadExternoObj = cadExternoObj;
    }

    public String getSnome_ext() {
        return Snome_ext;
    }

    public void setSnome_ext(String Snome_ext) {
        this.Snome_ext = Snome_ext;
    }

    public String getSrg_ext() {
        return Srg_ext;
    }

    public void setSrg_ext(String Srg_ext) {
        this.Srg_ext = Srg_ext;
    }

    public String getScpf_ext() {
        return Scpf_ext;
    }

    public void setScpf_ext(String Scpf_ext) {
        this.Scpf_ext = Scpf_ext;
    }

    public String getScrm_ext() {
        return Scrm_ext;
    }

    public void setScrm_ext(String Scrm_ext) {
        this.Scrm_ext = Scrm_ext;
    }

    public String getSesp_ext() {
        return Sesp_ext;
    }

    public void setSesp_ext(String Sesp_ext) {
        this.Sesp_ext = Sesp_ext;
    }

    public String getSdt_nasc_ext() {
        return Sdt_nasc_ext;
    }

    public void setSdt_nasc_ext(String Sdt_nasc_ext) {
        this.Sdt_nasc_ext = Sdt_nasc_ext;
    }

    public String getSsexo_ext() {
        return Ssexo_ext;
    }

    public void setSsexo_ext(String Ssexo_ext) {
        this.Ssexo_ext = Ssexo_ext;
    }

    public String getSendereco() {
        return Sendereco;
    }

    public void setSendereco(String Sendereco) {
        this.Sendereco = Sendereco;
    }

    public String getScep_end() {
        return Scep_end;
    }

    public void setScep_end(String Scep_end) {
        this.Scep_end = Scep_end;
    }

    public String getSnm_end() {
        return Snm_end;
    }

    public void setSnm_end(String Snm_end) {
        this.Snm_end = Snm_end;
    }

    public String getSbairro_end() {
        return Sbairro_end;
    }

    public void setSbairro_end(String Sbairro_end) {
        this.Sbairro_end = Sbairro_end;
    }

    public String getScid_end() {
        return Scid_end;
    }

    public void setScid_end(String Scid_end) {
        this.Scid_end = Scid_end;
    }

    public String getStel_com_end() {
        return Stel_com_end;
    }

    public void setStel_com_end(String Stel_com_end) {
        this.Stel_com_end = Stel_com_end;
    }

    public String getScel_end() {
        return Scel_end;
    }

    public void setScel_end(String Scel_end) {
        this.Scel_end = Scel_end;
    }

    public String getSwtp_end() {
        return Swtp_end;
    }

    public void setSwtp_end(String Swtp_end) {
        this.Swtp_end = Swtp_end;
    }

    public String getSemail() {
        return Semail;
    }

    public void setSemail(String Semail) {
        this.Semail = Semail;
    }

}
