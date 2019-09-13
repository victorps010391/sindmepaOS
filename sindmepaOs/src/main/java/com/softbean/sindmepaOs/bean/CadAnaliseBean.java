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
    String priorPesq;
    List<Map<String, Object>> grid01;
    List<Map<String, Object>> grid02;
    List<Map<String, Object>> grid03;    
    List<Map<String, Object>> priorListaPesq;

    public void pesquisar() {
        try {
            setGrid01(analiseControle.gridAnalise01(getNrOs(), getPriorPesq()));
            setGrid02(analiseControle.gridAnalise02(getNrOs(), getPriorPesq()));
            setGrid03(analiseControle.gridAnalise03(getNrOs(), getPriorPesq()));            
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
    
    public void limparPesquisa(){
        setPriorListaPesq(null);
        setNrOs(null);
        setGrid01(null);
        setGrid02(null);
        setGrid03(null);        
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
}
