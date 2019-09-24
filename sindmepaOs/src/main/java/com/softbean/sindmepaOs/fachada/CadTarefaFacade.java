/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadTarefa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless
public class CadTarefaFacade extends AbstractFacade<CadTarefa> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadTarefaFacade() {
        super(CadTarefa.class);
    }

    public List<Map<String, Object>> listarSetorTarefa() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cd_setor as codigo                   ");
        sql.append("        ,nm_setor as nome                    ");
        sql.append(" from cad_setor where usu_setor in ('I','A') ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("codigo", array[0]);
                map.put("nome", array[1]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método listarSetorPesq");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public Integer retornaSeqTarefa(Integer os) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select retorna_seq_tarefa(").append(os).append(")");

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaSeqTarefa " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
