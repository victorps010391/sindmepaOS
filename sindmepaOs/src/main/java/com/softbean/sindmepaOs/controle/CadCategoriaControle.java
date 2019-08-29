/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadCategoria;
import com.softbean.sindmepaOs.fachada.CadCategoriaFacade;
import com.softbean.sindmepaOs.fachada.CadDetalheFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author cdi_vsilva
 */
@Named(value = "cadCategoriaControle")
@SessionScoped
public class CadCategoriaControle implements Serializable {

    /**
     * Creates a new instance of CadCategoriaControle
     */
    public CadCategoriaControle() {
    }

    @Inject
    CadCategoriaFacade cadCategoriaFacade;
    @Inject
    CadDetalheFacade cadDetalheFacade;

    public List<Map<String, Object>> gridPrincipal(String desc, Integer cod, String tipo) {
        return cadCategoriaFacade.gridPrincipal(desc, cod, tipo);
    }

    public List<Map<String, Object>> listarTipoCateg() {
        return cadDetalheFacade.listarTipoCateg();
    }

    public CadCategoria buscarCategoria(Integer cod) {
        return cadCategoriaFacade.find(cod);
    }
}
