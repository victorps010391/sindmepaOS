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

    public List<Map<String, Object>> gridTarefa(Integer nrOs) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os_tarefa||'.'||cast(seq_tarefa as character varying(1)) as nr_tarefa                                                  ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_tarefa) as nm_setor_respon                                 ");
        sql.append("        ,TO_CHAR(dt_abert_tarefa, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_tarefa, 'HH24:MI:SS') as data_hora_abert                   ");
        sql.append("        ,TO_CHAR(dt_fecha_tarefa, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_tarefa, 'HH24:MI:SS') as data_hora_fecha                   ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITTA' and cod_valor_detalhe = sit_tarefa) as sit_tarefa ");
        sql.append("        ,sit_tarefa as cd_sit_tarefa                                                                                              ");
        sql.append("        ,nr_os_tarefa                                                                                                             ");
        sql.append("        ,seq_tarefa                                                                                                               ");
        sql.append(" from cad_tarefa where nr_os_tarefa = '").append(nrOs.toString()).append("'");
        sql.append(" order by dt_abert_tarefa desc ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("nr_tarefa", array[0]);
                map.put("nm_setor_respon", array[1]);
                map.put("data_hora_abert", array[2]);
                map.put("data_hora_fecha", array[3]);
                map.put("sit_tarefa", array[4]);
                map.put("cd_sit_tarefa", array[5]);
                map.put("nr_os_tarefa", array[6]);
                map.put("seq_tarefa", array[7]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> gridTarefaAtendimento(Integer nrOs, Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os_tarefa||'.'||cast(seq_tarefa as character varying(1)) as nr_tarefa                                                  ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_abert_tarefa) as nm_abert_respon                                  ");
        sql.append("        ,TO_CHAR(dt_abert_tarefa, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_tarefa, 'HH24:MI:SS') as data_hora_abert                   ");
        sql.append("        ,TO_CHAR(dt_fecha_tarefa, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_tarefa, 'HH24:MI:SS') as data_hora_fecha                   ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITTA' and cod_valor_detalhe = sit_tarefa) as sit_tarefa ");
        sql.append("        ,sit_tarefa as cd_sit_tarefa                                                                                              ");
        sql.append("        ,nr_os_tarefa                                                                                                             ");
        sql.append("        ,seq_tarefa                                                                                                               ");
        sql.append("        ,(select nm_func from cad_funcionario where cd_func = func_abert_tarefa) ||'/'||                                          ");
        sql.append("         (select nm_setor from cad_setor where cd_setor = setor_abert_tarefa) as nome_setor                                       ");
        sql.append(" from cad_tarefa where setor_respon_tarefa = ").append(cdSetor);
        sql.append(" and sit_tarefa not in ('01','05') ");

        if (nrOs != null) {
            sql.append(" and nr_os_tarefa = ").append(nrOs);
        }

        sql.append(" order by dt_abert_tarefa desc ");
        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("nr_tarefa", array[0]);
                map.put("nm_abert_respon", array[1]);
                map.put("data_hora_abert", array[2]);
                map.put("data_hora_fecha", array[3]);
                map.put("sit_tarefa", array[4]);
                map.put("cd_sit_tarefa", array[5]);
                map.put("nr_os_tarefa", array[6]);
                map.put("seq_tarefa", array[7]);
                map.put("nome_setor", array[8]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public CadTarefa retornaTarefa(String os, Integer seq) {
        CadTarefa tarefa = null;
        StringBuilder sql = new StringBuilder();

        try {
            sql.append(" SELECT c FROM CadTarefa c WHERE c.cadTarefaPK.nrOsTarefa = :nrOsTarefa ");
            sql.append(" AND c.cadTarefaPK.seqTarefa = :seqTarefa ");

            Query createQuery = em.createQuery(sql.toString());
            createQuery.setParameter("nrOsTarefa", os);
            createQuery.setParameter("seqTarefa", seq);

            tarefa = (CadTarefa) createQuery.getSingleResult();

        } catch (Exception e) {
            System.out.println("Erro no metodo retornaTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return tarefa;
    }

}
