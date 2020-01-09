/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadAnexos;
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
public class CadAnexosFacade extends AbstractFacade<CadAnexos> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadAnexosFacade() {
        super(CadAnexos.class);
    }

    public List<CadAnexos> gridPrincipal(String nome, Integer cod, Integer os) {
        List<CadAnexos> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT c FROM CadAnexos c WHERE 1=1 ");
        try {
            if (cod != null) {
                sql.append(" AND c.cadAnexosPK.seqAnexo = :seqAnexo ");
            }
            if (!nome.equals("")) {
                sql.append(" AND LOWER(c.nmArqAnexo) LIKE :nmArqAnexo ");
            }
            sql.append(" AND c.cadAnexosPK.codOsAnexo = :codOsAnexo ");

            System.out.println("sql:" + sql.toString());
            System.out.println("::::: OS :::::: " + os);
            Query query = em.createQuery(sql.toString());

            if (cod != null) {
                query.setParameter("seqAnexo", cod);
            }
            if (!nome.equals("")) {
                query.setParameter("nmArqAnexo", "%" + nome.toLowerCase() + "%");
            }
            query.setParameter("codOsAnexo", os);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Map<String, Object>> InfoAnexo(Integer nrOs) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT cod_os_anexo ");
        sql.append("        ,seq_anexo ");
        sql.append("        ,nm_arq_anexo ");
        sql.append("        ,TO_CHAR(dt_regi_anexo, 'DD/MM/YYYY')||' '||TO_CHAR(dt_regi_anexo, 'HH24:MI:SS') as dt_regi_anexo ");
        sql.append("        ,(case when (select setor_func from cad_funcionario where cd_func = func_regi_anexo) = 0 ");
        sql.append("         then (select nome_ext from cad_externo where id_ext = func_regi_anexo) ");
        sql.append("         else (select nm_func from cad_funcionario where cd_func = func_regi_anexo) ");
        sql.append("         end) as func_abert ");
        sql.append(" from cad_anexos where cod_os_anexo = ").append(nrOs);
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("cod_os_anexo", array[0]);
                map.put("seq_anexo", array[1]);
                map.put("nm_arq_anexo", array[2]);
                map.put("dt_regi_anexo", array[3]);
                map.put("func_abert", array[4]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método InfoAnexo");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public Integer retornaSeqAnexo(Integer cod) {
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT retorna_seq_anexo(").append(cod).append(");");

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaSeqAnexo " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
