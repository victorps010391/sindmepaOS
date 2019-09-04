/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadNota;
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
 * @author Victor
 */
@Stateless
public class CadNotaFacade extends AbstractFacade<CadNota> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadNotaFacade() {
        super(CadNota.class);
    }

    public List<Map<String, Object>> gridSecundario(Integer nrOs) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os_nota as os ");
        sql.append("        ,hist_nota as historico ");
        sql.append("        ,TO_CHAR(dt_regi_nota, 'DD/MM/YYYY')||' '||TO_CHAR(dt_regi_nota, 'HH24:MI:SS') as data_hora_regi ");
        sql.append("        ,TO_CHAR(dt_ult_atu_nota, 'DD/MM/YYYY')||' '||TO_CHAR(dt_ult_atu_nota, 'HH24:MI:SS') as data_ultima_atualizacao ");
        sql.append(" from cad_nota ");
        sql.append(" where nr_os_nota = ").append(nrOs);
        sql.append(" order by dt_regi_nota desc ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("os", array[0]);
                map.put("historico", array[1]);
                map.put("data_hora_regi", array[2]);
                map.put("data_ultima_atualizacao", array[3]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridSecundario (OS)");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public Integer retornaSeqNota(Integer os) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select retorna_seq(").append(os).append(")");

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaSeqNota " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
