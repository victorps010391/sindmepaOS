/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadAnaliseControle;
import com.softbean.sindmepaOs.controle.CadNotaControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadOsVer;
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
import org.primefaces.PrimeFaces;

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
    CadAnaliseControle analiseControle;
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
    CadOsVer objVerOs;

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
    List<Map<String, Object>> osVer;

//  VARIAVEIS DE PESQUISA
    Integer nrOs;
    Integer categOs;
    Integer setRespon;
    Integer colabRespon;
    String sitOs;
    Date iniAbert, fimAbert, iniFecha, fimFecha;
    Date iniAbertOs, fimAbertOs, iniFechaOs, fimFechaOs;

    List<Map<String, Object>> gridPesquisa;
    List<Map<String, Object>> gridPesquisaOs;
    List<Map<String, Object>> gridSecundario;
    List<Map<String, Object>> sitCategListaPesq;
    List<Map<String, Object>> setorResponsListaPesq;
    List<Map<String, Object>> colabResponsPesq;
    List<Map<String, Object>> sitOsListaPesq;

    public void pesquisar() {
        try {
            setGridPesquisa(osControle.gridPrincipal(getNrOs(), getCategOs(), getSetRespon(), getColabRespon(), getSitOs(),
                    loginBean.getUsuario().getSetorFunc().getCdSetor(), getIniAbert(), getFimAbert(), getIniFecha(), getFimFecha()));
            setGridSecundario(null);
        } catch (Exception e) {
            System.out.println("Erro no método pesquisar (OS)");
            e.printStackTrace();
        }
    }

    public void pesquisarOs() {
        try {
            setGridPesquisaOs(osControle.gridPrincipalOs(getNrOs(), getCategOs(), getSetRespon(), getColabRespon(), getSitOs(),
                    loginBean.getUsuario().getSetorFunc().getCdSetor(), getIniAbertOs(), getFimAbertOs(), getIniFechaOs(), getFimFechaOs()));
            setGridSecundario(null);
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarOs (OS)");
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
        PrimeFaces context = PrimeFaces.current();
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
            getObCadOs().setSetorAbertOs(loginBean.getUsuario().getSetorFunc());
            getObCadOs().setSetorResponOs(getCadSetorObj());
            getObCadOs().setSitOs("01");
            getObCadOs().setTipEnvioOs("I");

            if (osControle.salvarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro do Protocolo: " + getObCadOs().getNrOs() + " Realizado com Sucesso."));
                context.executeScript("PF('dlCadOs').hide();");
                setGridPesquisaOs(osControle.gridPrincipalOs(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(),
                        getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor(),
                        null, null, null, null));
                limparCadastro();
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
            setOsVer(null);//limpar variavel
            setObjVerOs(null);//limpar variavel
            setObjVerOs(new CadOsVer());

            setOsVer(analiseControle.verOs(cod));
            for (Map<String, Object> elemento : getOsVer()) {
                getObjVerOs().setCategoria((String) elemento.get("categoria"));
                getObjVerOs().setDataHoraAbert((String) elemento.get("data_hora_abert"));
                getObjVerOs().setDataHoraFecha((String) elemento.get("data_hora_fecha"));
                getObjVerOs().setDescFinalizacao((String) elemento.get("desc_finalizacao"));
                getObjVerOs().setFuncAbert((String) elemento.get("func_abert"));
                getObjVerOs().setFuncFinali((String) elemento.get("func_finali"));
                getObjVerOs().setFuncRespon((String) elemento.get("func_respon"));
                getObjVerOs().setHistorico((String) elemento.get("historico"));
                getObjVerOs().setObservacao((String) elemento.get("observacao"));
                getObjVerOs().setOs((Integer) elemento.get("os"));
                getObjVerOs().setPrioridade((String) elemento.get("prioridade"));
                getObjVerOs().setSetorAbertura((String) elemento.get("setor_abertura"));
                getObjVerOs().setSetorResponsavel((String) elemento.get("setor_responsavel"));
                getObjVerOs().setSit((String) elemento.get("sit"));
                getObjVerOs().setTipoEnvio((String) elemento.get("tipo_envio"));
            }
        } catch (Exception e) {
            System.out.println("Erro no método visualizar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterar() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setCadSetorObj(osControle.buscarSetor(getSetAlt()));
            getObCadOs().setSetorResponOs(getCadSetorObj());
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            if (osControle.alterarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Alteração do Protocolo: " + getObCadOs().getNrOs() + " Realizado com Sucesso."));
                context.executeScript("PF('dlAltOs').hide()");
                setGridPesquisaOs(osControle.gridPrincipalOs(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(),
                        getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor(),
                        null, null, null, null));
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Alterar Protocolo: " + getObCadOs().getNrOs() + "."));
            }
        } catch (Exception e) {
            System.out.println("Erro no método alterar (OS) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void cancelar() {
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObCadOs().setSitOs("06");
            getObCadOs().setDtFechaOs(new Date());
            if (osControle.alterarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Protocolo: " + getObCadOs().getNrOs() + " Cancelado com Sucesso."));
                context.executeScript("PF('dlCancelOs').hide()");
                setGridPesquisaOs(osControle.gridPrincipalOs(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(),
                        getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor(),
                        null, null, null, null));
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
        PrimeFaces context = PrimeFaces.current();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncUltAtuOs(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObCadOs().setSitOs("02");

            if (osControle.alterarOsControle(getObCadOs())) {
                visualizar(getObCadOs().getNrOs());
                if (disparaEmailabertura(getObjVerOs(), loginBean.getUsuario().getEmailFunc())) {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Protocolo: " + getObCadOs().getNrOs() + " Encaminhado Para Atendimento com Sucesso."));
                    context.executeScript("PF('dlConfirm').hide()");
                    setGridPesquisaOs(osControle.gridPrincipalOs(getObCadOs().getNrOs(), getObCadOs().getCategOs(), getSetAlt(),
                            getColabRespon(), getObCadOs().getSitOs(), loginBean.getUsuario().getSetorFunc().getCdSetor(),
                           null, null, null, null));
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

    public Boolean disparaEmailabertura(CadOsVer obj, String destinatario) {
        try {
            String assunto = "ABERTURA DE PROTOCOLO";

            SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder corpoEmailAbertura = new StringBuilder();
            corpoEmailAbertura.append("<p style='font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;'>SindmepaProtocol informa,<br />");
            corpoEmailAbertura.append("Seu protocolo foi enviado para atendimento com sucesso para nossa central com as seguintes informações: <br /><br />");
            corpoEmailAbertura.append("<strong>Número do protocolo: </strong>");
            corpoEmailAbertura.append(obj.getOs());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Categoria: </strong>");
            corpoEmailAbertura.append(obj.getCategoria());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Setor Responsável: </strong>");
            corpoEmailAbertura.append(obj.getSetorResponsavel());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Prioridade: </strong>");
            corpoEmailAbertura.append(obj.getPrioridade());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Solicitação: </strong>");
            corpoEmailAbertura.append(obj.getHistorico());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>Data de Abertura: </strong>");
            corpoEmailAbertura.append(obj.getDataHoraAbert());
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
        setGridPesquisaOs(null);
        setGridSecundario(null);
        setNrOs(null);
        setIniAbert(null);
        setIniFecha(null);
        setFimAbert(null);
        setFimFecha(null);

        setIniAbertOs(null);
        setIniFechaOs(null);
        setFimAbertOs(null);
        setFimFechaOs(null);
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

    public List<Map<String, Object>> getOsVer() {
        return osVer;
    }

    public void setOsVer(List<Map<String, Object>> osVer) {
        this.osVer = osVer;
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

    public List<Map<String, Object>> getGridPesquisaOs() {
        return gridPesquisaOs;
    }

    public void setGridPesquisaOs(List<Map<String, Object>> gridPesquisaOs) {
        this.gridPesquisaOs = gridPesquisaOs;
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

    public CadOsVer getObjVerOs() {
        return objVerOs;
    }

    public void setObjVerOs(CadOsVer objVerOs) {
        this.objVerOs = objVerOs;
    }

    public Date getIniAbert() {
        return iniAbert;
    }

    public void setIniAbert(Date iniAbert) {
        this.iniAbert = iniAbert;
    }

    public Date getFimAbert() {
        return fimAbert;
    }

    public void setFimAbert(Date fimAbert) {
        this.fimAbert = fimAbert;
    }

    public Date getIniFecha() {
        return iniFecha;
    }

    public void setIniFecha(Date iniFecha) {
        this.iniFecha = iniFecha;
    }

    public Date getFimFecha() {
        return fimFecha;
    }

    public void setFimFecha(Date fimFecha) {
        this.fimFecha = fimFecha;
    }

    public Date getIniAbertOs() {
        return iniAbertOs;
    }

    public void setIniAbertOs(Date iniAbertOs) {
        this.iniAbertOs = iniAbertOs;
    }

    public Date getFimAbertOs() {
        return fimAbertOs;
    }

    public void setFimAbertOs(Date fimAbertOs) {
        this.fimAbertOs = fimAbertOs;
    }

    public Date getIniFechaOs() {
        return iniFechaOs;
    }

    public void setIniFechaOs(Date iniFechaOs) {
        this.iniFechaOs = iniFechaOs;
    }

    public Date getFimFechaOs() {
        return fimFechaOs;
    }

    public void setFimFechaOs(Date fimFechaOs) {
        this.fimFechaOs = fimFechaOs;
    }

}
