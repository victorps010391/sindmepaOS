/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadExternoControle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Desenv
 */
@Named(value = "cadExternoBean")
@SessionScoped
public class CadExternoBean implements Serializable {

    /**
     * Creates a new instance of CadExternoBean
     */
    
    @Inject
    CadExternoControle cadExternoControle;
    
    List<Map<String, Object>> comboSetor;
    List<Map<String, Object>> comboCategoria;
    
    //VARIÁVEIS DE ARMAZENAMENTO
    Integer categoria;
    Integer setor;
    boolean liAceito;
    
    String sindicaliza, abriOsExt;
    
    public CadExternoBean() {
    }
    
    public void validador(){
        setAbriOsExt("false");
        setSindicaliza("false");
        
        if(getCategoria() == 7){
            setSindicaliza("true");
        }else{
            setAbriOsExt("true");
        }
    }
    
    public List<Map<String, Object>> comboCategoriaView(){
        try {
            setComboCategoria(cadExternoControle.listarCategoria());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }
        
        return getComboCategoria();
    }
    
    public List<Map<String, Object>> comboSetorView(){
        try {
            setComboSetor(cadExternoControle.listarSetor());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }
        
        return getComboSetor();
    }

    public CadExternoControle getCadExternoControle() {
        return cadExternoControle;
    }

    public void setCadExternoControle(CadExternoControle cadExternoControle) {
        this.cadExternoControle = cadExternoControle;
    }

    public List<Map<String, Object>> getComboSetor() {
        return comboSetor;
    }

    public void setComboSetor(List<Map<String, Object>> comboSetor) {
        this.comboSetor = comboSetor;
    }

    public List<Map<String, Object>> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(List<Map<String, Object>> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public String getSindicaliza() {
        return sindicaliza;
    }

    public void setSindicaliza(String sindicaliza) {
        this.sindicaliza = sindicaliza;
    }

    public String getAbriOsExt() {
        return abriOsExt;
    }

    public void setAbriOsExt(String abriOsExt) {
        this.abriOsExt = abriOsExt;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor) {
        this.setor = setor;
    }
    
    public String onFlowProcess(FlowEvent event) {
//        if(skip) {
//            skip = false;   //reset in case user goes back
//            return "confirm";
//        }
//        else {
//            return event.getNewStep();
//        }
        return event.getNewStep();
    }

    public boolean isLiAceito() {
        return liAceito;
    }

    public void setLiAceito(boolean liAceito) {
        this.liAceito = liAceito;
    }
    
}
