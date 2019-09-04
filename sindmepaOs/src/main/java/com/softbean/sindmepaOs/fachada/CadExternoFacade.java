/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadExterno;
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
 * @author Desenv
 */
@Stateless
public class CadExternoFacade extends AbstractFacade<CadExterno> {
    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadExternoFacade() {
        super(CadExterno.class);
    }
    
    public List<Map<String, Object>> listarSetor() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cd_setor as codigo, nm_setor as setor from cad_setor         ");
        sql.append("       where usu_setor = 'E' ");
        
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("codigo", array[0]);
                map.put("setor", array[1]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método listarSetor()");
            e.printStackTrace();
        }
        return resultMaps;
    }
    
    public List<Map<String, Object>> listarCategoria() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select desc_categoria as categoria, id_categoria as codigo from cad_categoria \n" +
                   " where usu_categoria = 'E'        ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("categoria", array[0]);
                map.put("codigo", array[1]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método listarCategoria()");
            e.printStackTrace();
        }
        return resultMaps;
    }
    
    public List<Map<String, Object>> tipoPag() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select id_detalhe as codigo, desc_detalhe as detalhe from cad_detalhe\n" +
                   " where cod_item_detalhe = 'TIPAG' ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("codigo", array[0]);
                map.put("detalhe", array[1]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método tipoPag()");
            e.printStackTrace();
        }
        return resultMaps;
    }
    
    public List<Map<String, Object>> pagInstituicao() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select id_detalhe as codigo, desc_detalhe as detalhe from cad_detalhe\n" +
                   " where cod_item_detalhe = 'PAGIN' ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("codigo", array[0]);
                map.put("detalhe", array[1]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método tipoPag()");
            e.printStackTrace();
        }
        return resultMaps;
    }
    
}
