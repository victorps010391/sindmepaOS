/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadSetor;
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
    List<Map<String, Object>> sitCategListaPesq;
    List<Map<String, Object>> setorResponsListaPesq;
    List<Map<String, Object>> colabResponsPesq;
    List<Map<String, Object>> sitOsListaPesq;

    public void pesquisar() {
        try {
            setGridPesquisa(osControle.gridPrincipal(getNrOs(), getCategOs(), getSetRespon(), getColabRespon(), getSitOs()));
        } catch (Exception e) {
            System.out.println("Erro no método pesquisar (OS)");
            e.printStackTrace();
        }
    }

    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObCadOs(new CadOs());
            getObCadOs().setCategOs(getCategCad());
            getObCadOs().setDtAbertOs(new Date());
            getObCadOs().setDtFechaOs(null);
            getObCadOs().setDtUltAtuOs(new Date());
            getObCadOs().setFuncAbertOs(999);
            getObCadOs().setFuncResponOs(999);
            getObCadOs().setFuncUltAtuOs(999);
            getObCadOs().setHistOs(getHist());
            getObCadOs().setNrOs(getNrOsCad());
            getObCadOs().setObsOs(getObs());
            setCadSetorObj(osControle.buscarSetor(getSetResponCad()));
            getObCadOs().setSetorAbertOs(getCadSetorObj());
            getObCadOs().setSetorResponOs(getCadSetorObj());
            getObCadOs().setSitOs("01");
            getObCadOs().setTipEnvioOs("I");

            if (osControle.salvarOsControle(getObCadOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Cadastro do Protocolo: " + getNrOsCad() + " Realizado com Sucesso."));
                context.execute("PF('dlCadOs').hide()");
                limparCadastro();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Cadastrar Protocolo: " + getNrOsCad() + "."));
                limparCadastro();
            }
        } catch (Exception e) {
            System.out.println("Erro o método salvar() (OS)");
            e.printStackTrace();
        }
    }

    public void novaOs() {
        try {
            limparCadastro();
            setNrOsCad(osControle.retornaNrOs());
        } catch (Exception e) {
            System.err.println("Erro no metodo novaOs " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void retornaPrioridade() {
        try {
            setPriorCad(osControle.retornaPrioridade(getCategCad()));
        } catch (Exception e) {
            System.err.println("Erro no metodo novaOs " + e.getMessage());
            e.printStackTrace();
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

}
