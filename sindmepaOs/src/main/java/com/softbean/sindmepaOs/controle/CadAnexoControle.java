/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadAnexos;
import com.softbean.sindmepaOs.entidade.CadAnexosPK;
import com.softbean.sindmepaOs.fachada.CadAnexosFacade;
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
@Named(value = "cadAnexoControle")
@SessionScoped
public class CadAnexoControle implements Serializable {

    /**
     * Creates a new instance of CadAnexoControle
     */
    public CadAnexoControle() {
    }

    @Inject
    CadAnexosFacade anexosFacade;

    public Boolean salvarControle(CadAnexos obj, CadAnexosPK objPk) {
        try {
            if (obj.getCadAnexosPK() == null) {
                obj.setCadAnexosPK(objPk);
                anexosFacade.create(obj);
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarControle (classe Anexos)");
            e.printStackTrace();
            return false;
        }
    }

    public List<CadAnexos> gridPrincipal(String nome, Integer cod, Integer os) {
        return anexosFacade.gridPrincipal(nome, cod, os);
    }

    public Boolean excluirControle(CadAnexos obj) {
        try {
            if (obj != null) {
                anexosFacade.remove(obj);
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método excluirControle (classe anexos)");
            e.printStackTrace();
            return false;
        }
    }
    
    public Integer retornaSeqAnexo(Integer cod){
        return anexosFacade.retornaSeqAnexo(cod);
    }
    
     public List<Map<String, Object>> InfoAnexo(Integer nrOs){
         return anexosFacade.InfoAnexo(nrOs);
     }

}
