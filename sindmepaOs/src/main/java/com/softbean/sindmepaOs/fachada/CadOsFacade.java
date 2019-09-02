/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadOs;
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
public class CadOsFacade extends AbstractFacade<CadOs> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadOsFacade() {
        super(CadOs.class);
    }

    public List<Map<String, Object>> gridPrincipal(Integer nrOs, Integer codCateg, Integer codSetor, Integer codFuncRespon, String sit) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os as os ");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  ");
        sql.append("        ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor ");
        sql.append("        ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert ");
        sql.append("        ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit ");
        sql.append(" from cad_os ");
        sql.append(" where 1=1 ");

        if (nrOs != null) {
            sql.append(" and nr_os = ").append(nrOs);
        }
        if (codCateg != null) {
            sql.append(" and categ_os = ").append(codCateg);
        }
        if (codSetor != null) {
            sql.append(" and setor_respon_os = ").append(codSetor);
        }
        if (codFuncRespon != null) {
            sql.append(" and func_respon_os = ").append(codFuncRespon);
        }
        if (sit != null) {
            sql.append(" and sit_os = '").append(sit).append("'");
        }
        sql.append(" order by dt_abert_os desc ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("os", array[0]);
                map.put("prioridade", array[1]);
                map.put("categoria", array[2]);
                map.put("setor", array[3]);
                map.put("data_hora_abert", array[4]);
                map.put("data_hora_fecha", array[5]);
                map.put("sit", array[6]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridPrincipal (OS)");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public Integer retornaNrOs() {
        StringBuilder sql = new StringBuilder();

        sql.append(" select retorna_novo_nr_os() ");

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaNrOs " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<Map<String, Object>> listarCategPesq() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select categ_os as codigo ");
        sql.append("        ,(select desc_categoria from cad_categoria where id_categoria = categ_os) as nome ");
        sql.append(" from cad_os ");
        sql.append(" group by categ_os ");
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
            System.out.println("ERRO no método listarCategPesq");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> listarSetorPesq() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select setor_respon_os as codigo ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as nome ");
        sql.append(" from cad_os ");
        sql.append(" group by setor_respon_os ");
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

    public List<Map<String, Object>> listarSituaPesq() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select sit_os as codigo ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) ");
        sql.append(" from cad_os ");
        sql.append(" group by sit_os ");
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
}
