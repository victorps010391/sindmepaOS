/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.fachada.CadExternoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Desenv
 */
@Named(value = "cadExternoControle")
@SessionScoped
public class CadExternoControle implements Serializable {

    /**
     * Creates a new instance of CadExternoControle
     */
    
    @Inject
    CadExternoFacade cadExternoFacade;
    
    public CadExternoControle() {
    }
    
    public List<Map<String, Object>> listarSetor() {
        return cadExternoFacade.listarSetor();
    }
    
    public List<Map<String, Object>> listarCategoria() {
        return cadExternoFacade.listarCategoria();
    }
}
