/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadNota;
import com.softbean.sindmepaOs.entidade.CadNotaPK;
import com.softbean.sindmepaOs.fachada.CadNotaFacade;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author Victor
 */
@Named(value = "cadNotaControle")
@Dependent
public class CadNotaControle {

    /**
     * Creates a new instance of CadNotaControle
     */
    public CadNotaControle() {
    }

    @Inject
    CadNotaFacade notaFacade;

    public Boolean salvarNotaControle(CadNota obj, CadNotaPK objPk) {
        try {
            if (obj.getCadNotaPK() == null) {
                obj.setCadNotaPK(objPk);
                notaFacade.create(obj);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método salvarNotaControle "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
