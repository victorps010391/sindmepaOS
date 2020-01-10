/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadSetor;
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
public class CadSetorFacade extends AbstractFacade<CadSetor> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadSetorFacade() {
        super(CadSetor.class);
    }

    public List<Map<String, Object>> gridPrincipal(String desc, Integer cod, String sit) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cd_setor as codigo                                                                                                     ");
        sql.append("        ,nm_setor as nome                                                                                                      ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITSE' and cod_valor_detalhe = sit_setor) as situacao ");
        sql.append(" from cad_setor                                                                                                                ");
        sql.append(" where 1 = 1													           ");
        if (!desc.equals("")) {
            sql.append(" and nm_setor like'%").append(desc.toUpperCase()).append("%'");
        }
        if (cod != null) {
            sql.append(" and cd_setor = ").append(cod);
        }
        if (sit != null) {
            sql.append(" and sit_setor = '").append(sit).append("'");
        }
        sql.append(" order by nm_setor ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("codigo", array[0]);
                map.put("nome", array[1]);
                map.put("situacao", array[2]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridPrincipal");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> listarSetorAll() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cd_setor as codigo                   ");
        sql.append("        ,upper(nm_setor) as nome             ");
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
            System.out.println("ERRO no método listarCategAll");
            e.printStackTrace();
        }
        return resultMaps;
    }

}
