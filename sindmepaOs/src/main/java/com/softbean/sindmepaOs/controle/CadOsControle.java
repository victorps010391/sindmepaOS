/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.bean.LoginBean;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.CadSetor;
import com.softbean.sindmepaOs.fachada.CadCategoriaFacade;
import com.softbean.sindmepaOs.fachada.CadOsFacade;
import com.softbean.sindmepaOs.fachada.CadSetorFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author admin
 */
@Named(value = "cadOsContorle")
@SessionScoped
public class CadOsControle implements Serializable {

    /**
     * Creates a new instance of CadOsContorle
     */
    public CadOsControle() {
    }

    @Inject
    CadOsFacade osFacade;
    @Inject
    CadSetorFacade setorFacade;
    @Inject
    CadCategoriaFacade categoriaFacade;

    public Boolean salvarOsControle(CadOs obj) {
        try {
            osFacade.create(obj);
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarOsControle");
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> usuDashboard(Integer cdSetor) {
        return osFacade.usuDashboard(cdSetor);
    }

    public List<Map<String, Object>> usuDiretorDashboard(Integer cdSetor) {
        return osFacade.usuDiretorDashboard(cdSetor);
    }

    public CadOs buscarOsControle(Integer cod) {
        return osFacade.find(cod);
    }

    public Boolean alterarOsControle(CadOs obj) {
        try {
            osFacade.edit(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método alterarOsControle " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Integer retornaNrOs() {
        return osFacade.retornaNrOs();
    }

    public List<Map<String, Object>> listarSetorAll() {
        return setorFacade.listarSetorAll();
    }

    public List<Map<String, Object>> listarCategAll() {
        return categoriaFacade.listarCategAll();
    }

    public String retornaPrioridade(Integer cod) {
        return categoriaFacade.retornaPrioridade(cod);
    }

    public CadSetor buscarSetor(Integer cod) {
        return setorFacade.find(cod);
    }

    public List<Map<String, Object>> listarCategPesq() {
        return osFacade.listarCategPesq();
    }

    public List<Map<String, Object>> listarSetorPesq() {
        return osFacade.listarSetorPesq();
    }

    public List<Map<String, Object>> listarSituaPesq() {
        return osFacade.listarSituaPesq();
    }

    public List<Map<String, Object>> gridPrincipal(Integer nrOs, Integer codCateg, Integer codSetor,
            Integer codFuncRespon, String sit, Integer usuSetor,
            Date dtIni, Date dtFim, Date dtIniFecha, Date dtFimFecha) {
        return osFacade.gridPrincipal(nrOs, codCateg, codSetor, codFuncRespon, sit, usuSetor, dtIni, dtFim, dtIniFecha, dtFimFecha);
    }

    public List<Map<String, Object>> gridPrincipalOs(Integer nrOs, Integer codCateg, Integer codSetor,
            Integer codFuncRespon, String sit, Integer usuSetor,
            Date dtIni, Date dtFim, Date dtIniFecha, Date dtFimFecha) {
        return osFacade.gridPrincipalOs(nrOs, codCateg, codSetor, codFuncRespon, sit, usuSetor, dtIni, dtFim, dtIniFecha, dtFimFecha);
    }

    public Integer validarFinalizacao(Integer os) {
        return osFacade.validarFinalizacao(os);
    }

    public List<Map<String, Object>> verDadosSindicais(Integer id) {
        return osFacade.verDadosSindicais(id);
    }
}
