/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.fachada.CadDetalheFacade;
import com.softbean.sindmepaOs.fachada.CadOsFacade;
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
@Named(value = "cadAnaliseControle")
@SessionScoped
public class CadAnaliseControle implements Serializable {

    /**
     * Creates a new instance of CadAnaliseControle
     */
    public CadAnaliseControle() {
    }

    @Inject
    CadDetalheFacade detalheFacade;
    @Inject
    CadOsFacade osFacade;

    public List<Map<String, Object>> listarPriorAnaliseOs() {
        return detalheFacade.listarPriorAnaliseOs();
    }

    public List<Map<String, Object>> listarSitAnaliseOs() {
        return detalheFacade.listarSitAnaliseOs();
    }

    public List<Map<String, Object>> gridAnalise01(Integer nrOs, String prior, String sit) {
        return osFacade.gridAnalise01(nrOs, prior, sit);
    }
}
