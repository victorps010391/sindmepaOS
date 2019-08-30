/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.entidade.CadOs;
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

    CadOs obCadOs;

    Integer nrOs;
    String categOs;
    Integer setRespon;
    Integer colabRespon;
    String sitOs;

    List<Map<String, Object>> gridPesquisa;
    List<Map<String, Object>> sitCategListaPesq;
    List<Map<String, Object>> sitSetResponsListaOs;
    List<Map<String, Object>> sitColabResponsOs;
    List<Map<String, Object>> sitOsLista;

    public CadOs getObCadOs() {
        if (obCadOs == null) {
            obCadOs = new CadOs();
        }
        return obCadOs;
    }

    public void setObCadOs(CadOs obCadOs) {
        this.obCadOs = obCadOs;
    }

    public Integer getNrOs() {
        return nrOs;
    }

    public void setNrOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public String getCategOs() {
        return categOs;
    }

    public void setCategOs(String categOs) {
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

    public List<Map<String, Object>> getSitSetResponsListaOs() {
        return sitSetResponsListaOs;
    }

    public void setSitSetResponsListaOs(List<Map<String, Object>> sitSetResponsListaOs) {
        this.sitSetResponsListaOs = sitSetResponsListaOs;
    }

    public List<Map<String, Object>> getSitColabResponsOs() {
        return sitColabResponsOs;
    }

    public void setSitColabResponsOs(List<Map<String, Object>> sitColabResponsOs) {
        this.sitColabResponsOs = sitColabResponsOs;
    }

    public List<Map<String, Object>> getSitOsLista() {
        return sitOsLista;
    }

    public void setSitOsLista(List<Map<String, Object>> sitOsLista) {
        this.sitOsLista = sitOsLista;
    }

}
