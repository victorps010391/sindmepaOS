/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadSetor;
import com.softbean.sindmepaOs.fachada.CadDetalheFacade;
import com.softbean.sindmepaOs.fachada.CadSetorFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
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
    @Inject
    CadDetalheFacade cadDetalheFacade;

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

    public Boolean alterarSetorControle(CadSetor obj) {
        try {
            cadSetorFacade.edit(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método salvarSetorControle");
            e.printStackTrace();
            return false;
        }
    }

    public CadSetor buscarSetor(Integer cod) {
        return cadSetorFacade.find(cod);
    }

    public List<Map<String, Object>> gridPrincipal(String desc, Integer cod, String sit) {
        return cadSetorFacade.gridPrincipal(desc, cod, sit);
    }

    public List<Map<String, Object>> listarSituacaoSetor() {
        return cadDetalheFacade.listarSituacaoSetor();
    }

}
