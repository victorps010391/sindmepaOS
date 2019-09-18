/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.fachada.CadFuncionarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Raphael
 */
@Named(value = "cadFuncionarioControle")
@SessionScoped
public class CadFuncionarioControle implements Serializable {

    /**
     * Creates a new instance of CadFuncionarioControle
     */
    public CadFuncionarioControle() {
    }
    @Inject
    CadFuncionarioFacade funcionarioFacade;

    public Boolean salvarSetorControle(CadFuncionario obj) {
        try {
            funcionarioFacade.create(obj);
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarSetorControle");
            e.printStackTrace();
            return false;
        }
    }
}
