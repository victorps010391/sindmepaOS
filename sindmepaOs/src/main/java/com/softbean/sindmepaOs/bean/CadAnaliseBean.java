/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadAnaliseControle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

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

    Integer nrOs;
    String ocultarFieldset;
    String priorPesq;
    String sitPesq;
    List<Map<String, Object>> grid01;
    List<Map<String, Object>> priorListaPesq;
    List<Map<String, Object>> sitListaPesq;

    public void pesquisar() {
        try {
            setGrid01(analiseControle.gridAnalise01(getNrOs(), getPriorPesq(), getSitPesq()));
            System.out.println("::::::::::::: fieldset01 antes " + getOcultarFieldset());
            if (!getGrid01().isEmpty()) {
                setOcultarFieldset("false");
            }
            System.out.println("::::::::::::: fieldset01 depois " + getOcultarFieldset());
        } catch (Exception e) {
            System.out.println("erro no método pesquisar(Analise) " + e.getMessage());
            e.printStackTrace();
        }
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

    public List<Map<String, Object>> listarSitOs() {
        try {
            setSitListaPesq(analiseControle.listarSitAnaliseOs());
        } catch (Exception e) {
            System.out.println("Erro no método listarSitOs " + e.getMessage());
            e.printStackTrace();
        }
        return getSitListaPesq();
    }

    public Integer getNrOs() {
        return nrOs;
    }

    public void setNrOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public String getOcultarFieldset() {
        if (ocultarFieldset == null) {
            ocultarFieldset = "true";
        }
        return ocultarFieldset;
    }

    public void setOcultarFieldset(String ocultarFieldset) {
        this.ocultarFieldset = ocultarFieldset;
    }

    public String getPriorPesq() {
        return priorPesq;
    }

    public void setPriorPesq(String priorPesq) {
        this.priorPesq = priorPesq;
    }

    public String getSitPesq() {
        return sitPesq;
    }

    public void setSitPesq(String sitPesq) {
        this.sitPesq = sitPesq;
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

    public List<Map<String, Object>> getSitListaPesq() {
        return sitListaPesq;
    }

    public void setSitListaPesq(List<Map<String, Object>> sitListaPesq) {
        this.sitListaPesq = sitListaPesq;
    }

}
