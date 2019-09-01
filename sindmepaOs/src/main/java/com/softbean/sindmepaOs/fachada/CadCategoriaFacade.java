/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadCategoria;
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
public class CadCategoriaFacade extends AbstractFacade<CadCategoria> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadCategoriaFacade() {
        super(CadCategoria.class);
    }

    public List<Map<String, Object>> gridPrincipal(String desc, Integer cod, String tipo) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select id_categoria as codigo                                                                                                       ");
        sql.append("        ,upper(desc_categoria) as categoria                                                                                          ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' and cod_valor_detalhe = cod_prior_categoria) as tipo ");
        sql.append("        ,to_char(dt_reg_categoria,'DD/MM/YYYY') as data_registro                                                                     ");
        sql.append(" from cad_categoria                                                                                                                  ");
        sql.append(" where 1 = 1                                                                                                                         ");
        if (!desc.equals("")) {
            sql.append(" and upper(desc_categoria) like'%").append(desc.toUpperCase()).append("%'");
        }
        if (cod != null) {
            sql.append(" and id_categoria = ").append(cod);
        }
        if (tipo != null) {
            sql.append(" and cod_prior_categoria = '").append(tipo).append("'");
        }
        sql.append(" order by id_categoria ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("codigo", array[0]);
                map.put("categoria", array[1]);
                map.put("tipo", array[2]);
                map.put("data_registro", array[3]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridPrincipal");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> listarCategAll() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select id_categoria as codigo         ");
        sql.append("        ,upper(desc_categoria) as nome ");
        sql.append(" from cad_categoria                    ");
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

    public String retornaPrioridade(Integer cod) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' and cod_valor_detalhe = cod_prior_categoria)) as prioridade "); 
        sql.append(" from cad_categoria where id_categoria = ").append(cod);

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (String) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaPrioridade " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
