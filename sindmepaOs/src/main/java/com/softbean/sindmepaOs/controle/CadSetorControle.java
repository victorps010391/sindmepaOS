/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadSetor;
import com.softbean.sindmepaOs.fachada.CadSetorFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Victor
 */
@Named(value = "cadSetorControle")
@SessionScoped
public class CadSetorControle implements Serializable {

    /**
     * Creates a new instance of CadSetorControle
     */
    public CadSetorControle() {
    }

    @Inject
    CadSetorFacade cadSetorFacade;

    public Boolean salvarSetorControle(CadSetor obj) {
        try {
            cadSetorFacade.create(obj);
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarSetorControle");
            e.printStackTrace();
            return false;
        }
    }

}
