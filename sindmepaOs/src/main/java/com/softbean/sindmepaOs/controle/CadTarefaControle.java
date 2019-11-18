/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadTarefa;
import com.softbean.sindmepaOs.entidade.CadTarefaPK;
import com.softbean.sindmepaOs.fachada.CadTarefaFacade;
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
@Named(value = "cadTarefaControle")
@SessionScoped
public class CadTarefaControle implements Serializable {

    /**
     * Creates a new instance of CadTarefaControle
     */
    public CadTarefaControle() {
    }

    @Inject
    CadTarefaFacade tarefaFacade;

    public List<Map<String, Object>> listarSetorTarefa() {
        return tarefaFacade.listarSetorTarefa();
    }

    public Boolean salvarTarefaControle(CadTarefa obj, CadTarefaPK objPk) {
        try {
            if (obj.getCadTarefaPK() == null) {
                obj.setCadTarefaPK(objPk);
                tarefaFacade.create(obj);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método salvarTarefaControle " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Integer retornaSeqTarefa(Integer os) {
        return tarefaFacade.retornaSeqTarefa(os);
    }

    public List<Map<String, Object>> gridTarefa(Integer nrOs, Integer cdFunc) {
        return tarefaFacade.gridTarefa(nrOs, cdFunc);
    }

    public List<Map<String, Object>> gridTarefaAtendimento(String nrOs, Integer cdSetor) {
        return tarefaFacade.gridTarefaAtendimento(nrOs, cdSetor);
    }

    public CadTarefa buscarTarefaControle(String os, Integer seq) {
        return tarefaFacade.retornaTarefa(os, seq);
    }

    public Boolean alterarTarefaControle(CadTarefa obj) {
        try {
            tarefaFacade.edit(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método alterarTarefaControle " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Map<String, Object>> verTarefa(String nrOs, Integer seq){
        return tarefaFacade.verTarefa(nrOs, seq);
    }

}
