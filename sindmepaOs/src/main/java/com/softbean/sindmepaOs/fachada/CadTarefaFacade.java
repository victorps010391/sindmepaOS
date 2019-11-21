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

    public List<Map<String, Object>> listarSetorTarefa(Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cd_setor as codigo                   ");
        sql.append("        ,nm_setor as nome                    ");
        sql.append(" from cad_setor where usu_setor in ('I','A') ");
        sql.append(" and cd_setor <> ").append(cdSetor);
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

    public List<Map<String, Object>> gridTarefa(Integer nrOs, Integer cdFunc) {
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
        sql.append(" and func_abert_tarefa = ").append(cdFunc);
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

    public List<Map<String, Object>> gridTarefaAtendimento(String nrOs, Integer cdSetor) {
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
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_abert_tarefa) as nome_setor                                       ");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = (select categ_os from cad_os where nr_os = cast(nr_os_tarefa as integer))))) as prioridade ");
        sql.append("        ,(select nm_func from cad_funcionario where cd_func = func_abert_tarefa) as nome_abert                                    ");

        sql.append(" from cad_tarefa where setor_respon_tarefa = ").append(cdSetor);
        sql.append(" and sit_tarefa not in ('01','05') ");

        if (nrOs != null) {
            sql.append(" and nr_os_tarefa = cast (").append(nrOs).append(" as character varying(80000))");
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
                map.put("prioridade", array[9]);
                map.put("nome_abert", array[10]);
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

    public List<Map<String, Object>> verTarefa(String nrOs, Integer seq) {
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
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_abert_tarefa) as nome_setor                                       ");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = (select categ_os from cad_os where nr_os = cast(nr_os_tarefa as integer))))) as prioridade ");
        sql.append("        ,(select nm_func from cad_funcionario where cd_func = func_abert_tarefa) as nome_abert                                    ");
        sql.append("        ,hist_tarefa                                                                                                              ");
        sql.append("        ,obs_tarefa                                                                                                               ");
        sql.append("        ,hist_fecha_tarefa                                                                                                        ");
        sql.append(" from cad_tarefa where nr_os_tarefa = cast (").append(nrOs).append(" as character varying(80000))");
        sql.append(" and seq_tarefa = ").append(seq);
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
                map.put("prioridade", array[9]);
                map.put("nome_abert", array[10]);
                map.put("hist_tarefa", array[11]);
                map.put("obs_tarefa", array[12]);
                map.put("hist_fecha_tarefa", array[13]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método gridTarefa " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> listarSitFinalizacaoTarefa() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select cod_valor_detalhe as codigo ");
        sql.append("        ,desc_detalhe as nome ");
        sql.append(" from cad_detalhe ");
        sql.append(" where cod_item_detalhe = 'SITTA' ");
        sql.append(" and cod_valor_detalhe in ('06','07') ");
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
            System.out.println("ERRO no método listarSitAnaliseOs");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> usuDashboardTarefa(Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) as qtd ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITTA' and cod_valor_detalhe = sit_tarefa) as desc ");
        sql.append(" from cad_tarefa ");
        sql.append(" where setor_respon_tarefa = ").append(cdSetor);
        sql.append(" and sit_tarefa <> '01' ");
        sql.append(" group by sit_tarefa ");
        sql.append(" order by sit_tarefa ");

        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("qtd", array[0]);
                map.put("desc", array[1]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no método usuDashboard " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public Integer validarFinalizacaoTarefa(String os, Integer cdFunc) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select cast(count(*) as integer) ");
        sql.append(" from cad_tarefa ");
        sql.append(" where nr_os_tarefa = '").append(os).append("'");
        sql.append(" and func_abert_tarefa = ").append(cdFunc);
        sql.append(" and sit_tarefa in ('01','02','03') ");
        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo validarFinalizacaoTarefa " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
