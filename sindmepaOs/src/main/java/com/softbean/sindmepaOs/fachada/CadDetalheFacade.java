/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadDetalhe;
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
 * @author cdi_vsilva
 */
@Stateless
public class CadDetalheFacade extends AbstractFacade<CadDetalhe> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadDetalheFacade() {
        super(CadDetalhe.class);
    }

    public List<Map<String, Object>> listarSituacaoSetor() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cod_valor_detalhe as codigo ");
        sql.append("        ,desc_detalhe as nome       ");
        sql.append(" from cad_detalhe                   ");
        sql.append(" where cod_item_detalhe = 'SITSE'   ");
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
            System.out.println("ERRO no método listarSituacaoSetor");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> listarTipoCateg() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cod_valor_detalhe as codigo  ");
        sql.append("        ,upper(desc_detalhe) as nome ");
        sql.append(" from cad_detalhe                    ");
        sql.append(" where cod_item_detalhe = 'PRIOR'    ");
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
            System.out.println("ERRO no método listarTipoCateg");
            e.printStackTrace();
        }
        return resultMaps;
    }

}
