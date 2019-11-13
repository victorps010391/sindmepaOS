/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadNotaControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadSetor;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author admin
 */
@Named(value = "cadOsBean")
@SessionScoped
public class CadOsBean implements Serializable {

    /**
     * Creates a new instance of CadOsBean
     */
    public CadOsBean() {
    }

    @Inject
    CadOsControle osControle;
    @Inject
    CadNotaControle notaControle;
    @Inject
    MailUtil mailUtil;
    @Inject
    LoginBean loginBean;

    String priorAlt;
    Integer setAlt;

//  VARIAVEIS DE CADASTRO
    CadOs obCadOs;
    CadSetor cadSetorObj;

    Integer nrOsCad;
    String priorCad;
    Integer categCad;
    Integer setResponCad;
    Integer colabResponCad;
    String hist;
    String obs;

    List<Map<String, Object>> sitCategListaCad;
    List<Map<String, Object>> setorResponsListaCad;
    List<Map<String, Object>> colabResponsCad;

//  VARIAVEIS DE PESQUISA
    Integer nrOs;
    Integer categOs;
    Integer setRespon;
    Integer colabRespon;
    String sitOs;

    List<Map<String, Object>> gridPesquisa;
    List<Map<String, Object>> gridSecundario;
    List<Map<String, Object>> sitCategListaPesq;
    List<Map<String, Object>> setorResponsListaPesq;
    List<Map<String, Object>> colabResponsPesq;
    List<Map<String, Object>> sitOsListaPesq;

    public void pesquisar() {
        try {
            setGridPesquisa(osControle.gridPrincipal(getNrOs(), getCategOs(), getSetRespon(), getColabRespon(), getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setGridSecundario(null);
        } catch (Exception e) {
            System.out.println("Erro no método pesquisar (OS)");
            e.printStackTrace();
        }
    }

    public void pesquisarNota(Integer os) {
        try {
            setGridSecundario(notaControle.gridSecundario(os));
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarNota " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObCadOs(null)/*limpar variavel*/;
            setObCadOs(new CadOs());
            getObCadOs().setCategOs(getCategCad());
            getObCadOs().setDtAbertOs(new Date());
            getObCadOs().setDtFechaOs(null);
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncAbertOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObCadOs().setFuncResponOs(999);
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObCadOs().setHistOs(getHist());
            getObCadOs().setNrOs(osControle.retornaNrOs());
            getObCadOs().setObsOs(getObs());
            setCadSetorObj(null)/*limpar variavel*/;
            setCadSetorObj(osControle.buscarSetor(getSetResponCad()));
            getObCadOs().setSetorAbertOs(getCadSetorObj());
            getObCadOs().setSetorResponOs(getCadSetorObj());
            getObCadOs().setSitOs("01");
            getObCadOs().setTipEnvioOs("I");

            if (osControle.salvarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro do Protocolo: " + getObCadOs().getNrOs() + " Realizado com Sucesso."));
                context.execute("PF('dlCadOs').hide()");
                limparCadastro();
                setGridPesquisa(osControle.gridPrincipal(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(), getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Cadastrar Protocolo: " + getObCadOs().getNrOs() + "."));
                limparCadastro();
            }
        } catch (Exception e) {
            System.out.println("Erro o método salvar() (OS)");
            e.printStackTrace();
        }
    }

    public void buscar(Integer cod) {
        try {
            setObCadOs(null)/*limpar variavel*/;
            setObCadOs(osControle.buscarOsControle(cod));
            setCadSetorObj(null)/*limpar variavel*/;
            setCadSetorObj(osControle.buscarSetor(getObCadOs().getSetorResponOs().getCdSetor()));
            setSetAlt(getCadSetorObj().getCdSetor());
            setPriorAlt(osControle.retornaPrioridade(getObCadOs().getCategOs()));
        } catch (Exception e) {
            System.out.println("Erro no método buscar (Os) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void visualizar(Integer cod) {
        try {
            setObCadOs(null)/*limpar variavel*/;
            setObCadOs(osControle.buscarOsControle(cod));
            setCadSetorObj(null)/*limpar variavel*/;
            setCadSetorObj(osControle.buscarSetor(getObCadOs().getSetorResponOs().getCdSetor()));
            setPriorAlt(null)/*limpar variavel*/;
            setPriorAlt(osControle.retornaPrioridade(getObCadOs().getCategOs()));
        } catch (Exception e) {
            System.out.println("Erro no método visualizar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterar() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setCadSetorObj(osControle.buscarSetor(getSetAlt()));
            getObCadOs().setSetorResponOs(getCadSetorObj());
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            if (osControle.alterarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Alteração do Protocolo: " + getObCadOs().getNrOs() + " Realizado com Sucesso."));
                context.execute("PF('dlAltOs').hide()");
                setGridPesquisa(osControle.gridPrincipal(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(), getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Alterar Protocolo: " + getObCadOs().getNrOs() + "."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método alterar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cancelar() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObCadOs().setSitOs("06");
            getObCadOs().setDtFechaOs(new Date());
            if (osControle.alterarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Protocolo: " + getObCadOs().getNrOs() + " Cancelado com Sucesso."));
                context.execute("PF('dlCancelOs').hide()");
                setGridPesquisa(osControle.gridPrincipal(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(), getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Cancelar Protocolo: " + getObCadOs().getNrOs() + "."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método cancelar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void buscarEncaminhar(Integer cod) {
        try {
            setObCadOs(null)/*limpar variavel*/;
            setObCadOs(osControle.buscarOsControle(cod));
        } catch (Exception e) {
            System.out.println("Erro no método buscarEncaminhar (Os) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void encaminhar() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObCadOs().setSitOs("02");

            if (osControle.alterarOsControle(getObCadOs())) {
                if (disparaEmailabertura(getObCadOs())) {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Protocolo: " + getObCadOs().getNrOs() + " Encaminhado Para Atendimento com Sucesso."));
                    context.execute("PF('dlConfirm').hide()");
                    setGridPesquisa(osControle.gridPrincipal(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getObCadOs().getSetorResponOs().getCdSetor(), getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor()));
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Erro ao encaminhar Protocolo: " + getObCadOs().getNrOs() + " Para Atendimento."));
                }
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Erro ao encaminhar Protocolo: " + getObCadOs().getNrOs() + " Para Atendimento."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método encaminhar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }
    //    public void novaOs() {
    //        try {
    //            limparCadastro();
    //            setNrOsCad(osControle.retornaNrOs());
    //        } catch (Exception e) {
    //            System.err.println("Erro no metodo novaOs " + e.getMessage());
    //            e.printStackTrace();
    //        }
    //    }

    public void retornaPrioridade() {
        try {
            setPriorCad(osControle.retornaPrioridade(getCategCad()));
        } catch (Exception e) {
            System.err.println("Erro no retornaPrioridade novaOs " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void retornaPrioridadeAlt() {
        try {
            setPriorAlt(osControle.retornaPrioridade(getObCadOs().getCategOs()));
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaPrioridadeAlt " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Boolean disparaEmailabertura(CadOs obj) {
        try {
            String assunto = "Abertura de Protocolo";
            String destinatario = "victorps91@gmail.com";

            SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder corpoEmailAbertura = new StringBuilder();
            corpoEmailAbertura.append("<p style='font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;'>SINDMEPA informa,<br />");
            corpoEmailAbertura.append("Seu protocolo foi enviado para atendimento com sucesso para nossa central com as seguintes informações: <br /><br />");
            corpoEmailAbertura.append("<strong>Número do protocolo: </strong>");
            corpoEmailAbertura.append(obj.getNrOs());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Solicitação: </strong>");
            corpoEmailAbertura.append(obj.getHistOs());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Data de Abertura: </strong>");
            corpoEmailAbertura.append(formate.format(obj.getDtAbertOs()));
            corpoEmailAbertura.append("<br />");
//            corpoEmailAbertura.append("<strong>Descrição da Ocorrência: </strong>");
//            corpoEmailAbertura.append(registroOcorrencia.getTroDsOcor());
//            corpoEmailAbertura.append("<br />");
//            corpoEmailAbertura.append("<strong>Local: </strong>");
//            corpoEmailAbertura.append(orgao);
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

    public void limparCadastro() {
        setCategCad(null);
        setColabResponCad(null);
        setHist(null);
        setNrOsCad(null);
        setObs(null);
        setPriorCad(null);
        setSetResponCad(null);
        setSetorResponsListaCad(null);
        setSitCategListaCad(null);
        setColabResponCad(null);
    }

    public void limparPesquisa() {
        setSitOsListaPesq(null);
        setSetorResponsListaPesq(null);
        setSitCategListaPesq(null);
        setGridPesquisa(null);
        setGridSecundario(null);
        setNrOs(null);
    }

    public List<Map<String, Object>> listarSituaPesq() {
        try {
            setSitOsListaPesq(osControle.listarSituaPesq());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarSituaPesq");
        }
        return getSitOsListaPesq();
    }

    public List<Map<String, Object>> listarSetorPesq() {
        try {
            setSetorResponsListaPesq(osControle.listarSetorPesq());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarSetorPesq");
        }
        return getSetorResponsListaPesq();
    }

    public List<Map<String, Object>> listarCategPesq() {
        try {
            setSitCategListaPesq(osControle.listarCategPesq());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarSetorPesq");
        }
        return getSitCategListaPesq();
    }

    public List<Map<String, Object>> listarSetorCad() {
        try {
            setSetorResponsListaCad(osControle.listarSetorAll());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarSetorCad");
        }
        return getSetorResponsListaCad();
    }

    public List<Map<String, Object>> listarCategCad() {
        try {
            setSitCategListaCad(osControle.listarCategAll());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarCategCad");
        }
        return getSitCategListaCad();
    }

    public CadOs getObCadOs() {
        if (obCadOs == null) {
            obCadOs = new CadOs();
        }
        return obCadOs;
    }

    public void setObCadOs(CadOs obCadOs) {
        this.obCadOs = obCadOs;
    }

    public CadSetor getCadSetorObj() {
        if (cadSetorObj == null) {
            cadSetorObj = new CadSetor();
        }
        return cadSetorObj;
    }

    public void setCadSetorObj(CadSetor cadSetorObj) {
        this.cadSetorObj = cadSetorObj;
    }

    public Integer getNrOs() {
        return nrOs;
    }

    public void setNrOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public Integer getCategOs() {
        return categOs;
    }

    public void setCategOs(Integer categOs) {
        this.categOs = categOs;
    }

    public Integer getSetRespon() {
        return setRespon;
    }

    public void setSetRespon(Integer setRespon) {
        this.setRespon = setRespon;
    }

    public Integer getColabRespon() {
        return colabRespon;
    }

    public void setColabRespon(Integer colabRespon) {
        this.colabRespon = colabRespon;
    }

    public String getSitOs() {
        return sitOs;
    }

    public void setSitOs(String sitOs) {
        this.sitOs = sitOs;
    }

    public List<Map<String, Object>> getGridPesquisa() {
        return gridPesquisa;
    }

    public void setGridPesquisa(List<Map<String, Object>> gridPesquisa) {
        this.gridPesquisa = gridPesquisa;
    }

    public List<Map<String, Object>> getGridSecundario() {
        return gridSecundario;
    }

    public void setGridSecundario(List<Map<String, Object>> gridSecundario) {
        this.gridSecundario = gridSecundario;
    }

    public List<Map<String, Object>> getSitCategListaPesq() {
        return sitCategListaPesq;
    }

    public void setSitCategListaPesq(List<Map<String, Object>> sitCategListaPesq) {
        this.sitCategListaPesq = sitCategListaPesq;
    }

    public List<Map<String, Object>> getSetorResponsListaPesq() {
        return setorResponsListaPesq;
    }

    public void setSetorResponsListaPesq(List<Map<String, Object>> setorResponsListaPesq) {
        this.setorResponsListaPesq = setorResponsListaPesq;
    }

    public List<Map<String, Object>> getColabResponsPesq() {
        return colabResponsPesq;
    }

    public void setColabResponsPesq(List<Map<String, Object>> colabResponsPesq) {
        this.colabResponsPesq = colabResponsPesq;
    }

    public List<Map<String, Object>> getSitOsListaPesq() {
        return sitOsListaPesq;
    }

    public void setSitOsListaPesq(List<Map<String, Object>> sitOsListaPesq) {
        this.sitOsListaPesq = sitOsListaPesq;
    }

    public Integer getNrOsCad() {
        return nrOsCad;
    }

    public void setNrOsCad(Integer nrOsCad) {
        this.nrOsCad = nrOsCad;
    }

    public String getPriorCad() {
        return priorCad;
    }

    public void setPriorCad(String priorCad) {
        this.priorCad = priorCad;
    }

    public Integer getCategCad() {
        return categCad;
    }

    public void setCategCad(Integer categCad) {
        this.categCad = categCad;
    }

    public Integer getSetResponCad() {
        return setResponCad;
    }

    public void setSetResponCad(Integer setResponCad) {
        this.setResponCad = setResponCad;
    }

    public Integer getColabResponCad() {
        return colabResponCad;
    }

    public void setColabResponCad(Integer colabResponCad) {
        this.colabResponCad = colabResponCad;
    }

    public List<Map<String, Object>> getSitCategListaCad() {
        return sitCategListaCad;
    }

    public void setSitCategListaCad(List<Map<String, Object>> sitCategListaCad) {
        this.sitCategListaCad = sitCategListaCad;
    }

    public List<Map<String, Object>> getSetorResponsListaCad() {
        return setorResponsListaCad;
    }

    public void setSetorResponsListaCad(List<Map<String, Object>> setorResponsListaCad) {
        this.setorResponsListaCad = setorResponsListaCad;
    }

    public List<Map<String, Object>> getColabResponsCad() {
        return colabResponsCad;
    }

    public void setColabResponsCad(List<Map<String, Object>> colabResponsCad) {
        this.colabResponsCad = colabResponsCad;
    }

    public String getHist() {
        return hist;
    }

    public void setHist(String hist) {
        this.hist = hist;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getPriorAlt() {
        return priorAlt;
    }

    public void setPriorAlt(String priorAlt) {
        this.priorAlt = priorAlt;
    }

    public Integer getSetAlt() {
        return setAlt;
    }

    public void setSetAlt(Integer setAlt) {
        this.setAlt = setAlt;
    }

}
