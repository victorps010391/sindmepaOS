/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadOs;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Map<String, Object>> verDadosSindicais(Integer id) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select upper(nome_ext) as nome_ext                                                ");
        sql.append("        ,rg_ext                                                                    ");
        sql.append("        ,cpf_ext                                                                   ");
        sql.append("        ,crm_ext                                                                   ");
        sql.append("        ,upper(esp_ext) as esp_ext                                                 ");
        sql.append("        ,TO_CHAR(data_nasc_ext, 'DD/MM/YYYY') as dt_nasc_ext                       ");
        sql.append("        ,case when sexo_ext = 'M' then 'MASCULINO' else 'FEMININO' end as sexo_ext ");
        sql.append("        ,upper(endereco) as endereco                                               ");
        sql.append("        ,cep_end                                                                   ");
        sql.append("        ,nm_end                                                                    ");
        sql.append("        ,upper(bairro_end) as bairro_end                                           ");
        sql.append("        ,upper(cid_end) as cid_end                                                 ");
        sql.append("        ,tel_com_end                                                               ");
        sql.append("        ,cel_end                                                                   ");
        sql.append("        ,wtp_end                                                                   ");
        sql.append("        ,email                                                                     ");
        sql.append(" from cad_externo inner join endereco on (id_end_ext = id_end)                     ");
        sql.append(" where id_ext = ").append(id);

        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("nome_ext", array[0]);
                map.put("rg_ext", array[1]);
                map.put("cpf_ext", array[2]);
                map.put("crm_ext", array[3]);
                map.put("esp_ext", array[4]);
                map.put("dt_nasc_ext", array[5]);
                map.put("sexo_ext", array[6]);
                map.put("endereco", array[7]);
                map.put("cep_end", array[8]);
                map.put("nm_end", array[9]);
                map.put("bairro_end", array[10]);
                map.put("cid_end", array[11]);
                map.put("tel_com_end", array[12]);
                map.put("cel_end", array[13]);
                map.put("wtp_end", array[14]);
                map.put("email", array[15]);                
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo verDadosSindicais " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> verOs(Integer nrOs) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os as os 													   ");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                          ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade ");
        sql.append("        ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria                          ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor_responsavel                                ");
        sql.append("        ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert                        ");
        sql.append("        ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha                        ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit         ");
        sql.append("        ,sit_os as cd_sit                                                                                                      ");
        sql.append("        ,upper(hist_os) as historico                                                                                           ");
        sql.append("        ,upper(obs_os) as observacao                                                                                           ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_abert_os) as setor_abertura                                    ");
        sql.append("        ,setor_abert_os as cod_setor_abert                                                                                     ");
        sql.append("        ,categ_os as cod_categoria                                                                                             ");
        sql.append("        ,(case when setor_abert_os = 0                                                                                         ");
        sql.append("               then (select nome_ext from cad_externo where id_ext = func_abert_os)                                            ");
        sql.append("               else (select nm_func from cad_funcionario where cd_func = func_abert_os)                                        ");
        sql.append("               end) as func_abert                                                                                              ");
        sql.append("        ,upper(desc_finalizacao_os) as desc_finalizacao                                                                        ");
        sql.append("        ,(select nm_func from cad_funcionario where cd_func = func_respon_os) as func_respon                                   ");
        sql.append("        ,(select nm_func from cad_funcionario where cd_func = func_finali_os) as func_finali                                   ");
        sql.append("        ,(case when tipo_envio_os = 'I' then 'INTERNO' ELSE 'EXTERNO' END) as tipo_envio                                       ");
        sql.append("        ,func_abert_os                                                                                                         ");
        sql.append(" from cad_os                                                                                                                   ");
        sql.append(" where nr_os = ").append(nrOs);

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
                map.put("setor_responsavel", array[3]);
                map.put("data_hora_abert", array[4]);
                map.put("data_hora_fecha", array[5]);
                map.put("sit", array[6]);
                map.put("cd_sit", array[7]);
                map.put("historico", array[8]);
                map.put("observacao", array[9]);
                map.put("setor_abertura", array[10]);
                map.put("cod_setor_abert", array[11]);
                map.put("cod_categoria", array[12]);
                map.put("func_abert", array[13]);
                map.put("desc_finalizacao", array[14]);
                map.put("func_respon", array[15]);
                map.put("func_finali", array[16]);
                map.put("tipo_envio", array[17]);
                map.put("func_abert_os", array[18]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo verOs " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> gridAnalise01(Integer nrOs, String prior, Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os as os 																											");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                           ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  ");
        sql.append("        ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria                           ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor                                             ");
        sql.append("        ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert                         ");
        sql.append("        ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha                         ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit          ");
        sql.append("        ,sit_os as cd_sit                                                                                                       ");
        sql.append(" from cad_os                                                                                                                    ");
        sql.append(" where 1=1                                                                                                                      ");
        sql.append(" and sit_os = '02'                                                                                                              ");
        sql.append(" and setor_respon_os = ").append(cdSetor);

        if (nrOs != null) {
            sql.append(" and nr_os = ").append(nrOs);
        }
        if (prior != null) {
            sql.append(" and (select cod_valor_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' ");
            sql.append(" and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os)) = '").append(prior).append("'");
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
                map.put("cd_sit", array[7]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo gridAnalise01 ");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> gridAnalise02(Integer nrOs, String prior, Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os as os 																											");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                           ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  ");
        sql.append("        ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria                           ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor                                             ");
        sql.append("        ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert                         ");
        sql.append("        ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha                         ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit          ");
        sql.append("        ,sit_os as cd_sit                                                                                                       ");
        sql.append(" from cad_os                                                                                                                    ");
        sql.append(" where 1=1                                                                                                                      ");
        sql.append(" and sit_os in ('03','04')                                                                                                      ");
        sql.append(" and setor_respon_os = ").append(cdSetor);

        if (nrOs != null) {
            sql.append(" and nr_os = ").append(nrOs);
        }
        if (prior != null) {
            sql.append(" and (select cod_valor_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' ");
            sql.append(" and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os)) = '").append(prior).append("'");
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
                map.put("cd_sit", array[7]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo gridAnalise02 ");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> gridAnalise03(Integer nrOs, String prior, Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select nr_os as os 																											");
        sql.append("        ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                           ");
        sql.append("         and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  ");
        sql.append("        ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria                           ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor                                             ");
        sql.append("        ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert                         ");
        sql.append("        ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha                         ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit          ");
        sql.append("        ,sit_os as cd_sit                                                                                                       ");
        sql.append(" from cad_os                                                                                                                    ");
        sql.append(" where 1=1                                                                                                                      ");
        sql.append(" and sit_os in ('05','07')                                                                                                      ");
        sql.append(" and setor_respon_os = ").append(cdSetor);

        if (nrOs != null) {
            sql.append(" and nr_os = ").append(nrOs);
        }
        if (prior != null) {
            sql.append(" and (select cod_valor_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' ");
            sql.append(" and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os)) = '").append(prior).append("'");
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
                map.put("cd_sit", array[7]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo gridAnalise04 ");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> gridPrincipal(Integer nrOs, Integer codCateg, Integer codSetor,
            Integer codFuncRespon, String sit, Integer usuSetor,
            Date dtIni, Date dtFim, Date dtIniFecha, Date dtFimFecha) {

        java.sql.Date dtIniSql = dtIni == null ? null : new java.sql.Date(dtIni.getTime());
        java.sql.Date dtFimSql = dtFim == null ? null : new java.sql.Date(dtFim.getTime());

        java.sql.Date dtIniSqlFecha = dtIniFecha == null ? null : new java.sql.Date(dtIniFecha.getTime());
        java.sql.Date dtFimSqlFecha = dtFimFecha == null ? null : new java.sql.Date(dtFimFecha.getTime());

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
        sql.append("        ,sit_os as cd_sit ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_abert_os) as setor_abert ");
        sql.append(" from cad_os ");
        sql.append(" where 1=1 ");
        sql.append(" and setor_abert_os <> ").append(usuSetor);

        if (usuSetor != 7) {
            sql.append(" and setor_abert_os = ").append(usuSetor);
        }

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

        if (dtIni != null && dtFim != null) {
            sql.append(" and dt_abert_os between ").append("'").append(dtIniSql).append("' and '").append(dtFimSql).append("'");
        }
        if (dtIni != null && dtFim == null) {
            sql.append(" and dt_abert_os between ").append("'").append(dtIniSql).append("' and ").append("(select max(dt_abert_os) from cad_os)");
        }
        if (dtIni == null && dtFim != null) {
            sql.append(" and dt_abert_os between ").append(" '1990-01-01 00:00:00' ").append(" AND '").append(dtFimSql).append("'");
        }

        if (dtIniFecha != null && dtFimFecha != null) {
            sql.append(" and dt_fecha_os between ").append("'").append(dtIniSqlFecha).append("' and '").append(dtFimSqlFecha).append("'");
        }
        if (dtIniFecha != null && dtFimFecha == null) {
            sql.append(" and dt_fecha_os between ").append("'").append(dtIniSqlFecha).append("' and ").append("(select max(dt_fecha_os) from cad_os)");
        }
        if (dtIniFecha == null && dtFimFecha != null) {
            sql.append(" and dt_fecha_os between ").append(" '1990-01-01 00:00:00' ").append(" and '").append(dtFimSqlFecha).append("'");
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
                map.put("cd_sit", array[7]);
                map.put("setor_abert", array[8]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo gridPrincipal (OS)");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> gridPrincipalOs(Integer nrOs, Integer codCateg, Integer codSetor,
            Integer codFuncRespon, String sit, Integer usuSetor,
            Date dtIni, Date dtFim, Date dtIniFecha, Date dtFimFecha) {

        java.sql.Date dtIniSql = dtIni == null ? null : new java.sql.Date(dtIni.getTime());
        java.sql.Date dtFimSql = dtFim == null ? null : new java.sql.Date(dtFim.getTime());

        java.sql.Date dtIniSqlFecha = dtIniFecha == null ? null : new java.sql.Date(dtIniFecha.getTime());
        java.sql.Date dtFimSqlFecha = dtFimFecha == null ? null : new java.sql.Date(dtFimFecha.getTime());

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
        sql.append("        ,sit_os as cd_sit ");
        sql.append(" from cad_os ");
        sql.append(" where 1=1 ");
        sql.append(" and setor_abert_os = ").append(usuSetor);

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

        if (dtIni != null && dtFim != null) {
            sql.append(" and dt_abert_os between ").append("'").append(dtIniSql).append("' and '").append(dtFimSql).append("'");
        }
        if (dtIni != null && dtFim == null) {
            sql.append(" and dt_abert_os between ").append("'").append(dtIniSql).append("' and ").append("(select max(dt_abert_os) from cad_os)");
        }
        if (dtIni == null && dtFim != null) {
            sql.append(" and dt_abert_os between ").append(" '1990-01-01 00:00:00' ").append(" AND '").append(dtFimSql).append("'");
        }

        if (dtIniFecha != null && dtFimFecha != null) {
            sql.append(" and dt_fecha_os between ").append("'").append(dtIniSqlFecha).append("' and '").append(dtFimSqlFecha).append("'");
        }
        if (dtIniFecha != null && dtFimFecha == null) {
            sql.append(" and dt_fecha_os between ").append("'").append(dtIniSqlFecha).append("' and ").append("(select max(dt_fecha_os) from cad_os)");
        }
        if (dtIniFecha == null && dtFimFecha != null) {
            sql.append(" and dt_fecha_os between ").append(" '1990-01-01 00:00:00' ").append(" and '").append(dtFimSqlFecha).append("'");
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
                map.put("cd_sit", array[7]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo gridPrincipal (OS)");
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
            System.out.println("ERRO no mÃ©todo listarCategPesq");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> listarSetorPesq() {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select setor_respon_os as codigo                                                  ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as nome ");
        sql.append(" from cad_os                                                                       ");
        sql.append(" group by setor_respon_os                                                          ");
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
            System.out.println("ERRO no mÃ©todo listarSetorPesq");
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
            System.out.println("ERRO no mÃ©todo listarSetorPesq");
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> usuDashboard(Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) as qtd ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as desc ");
        sql.append(" from cad_os ");
        sql.append(" where setor_respon_os = ").append(cdSetor);
        sql.append(" and sit_os <> '01' ");
        sql.append(" group by sit_os ");
        sql.append(" order by sit_os ");

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
            System.out.println("ERRO no mÃ©todo usuDashboard " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public List<Map<String, Object>> usuDiretorDashboard(Integer cdSetor) {
        List<Object[]> resultArrays;
        List<Map<String, Object>> resultMaps = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) as qtd ");
        sql.append("        ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as desc ");
        sql.append("        ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setRespon ");
        sql.append(" from cad_os ");
        sql.append(" where sit_os <> '01' ");
        sql.append(" and setor_respon_os <> ").append(cdSetor);
        sql.append(" group by sit_os,setor_respon_os ");
        sql.append(" order by sit_os,setor_respon_os ");

        try {
            Query createQuery = em.createNativeQuery(sql.toString());
            resultArrays = createQuery.getResultList();
            resultMaps = new ArrayList<>();
            Map<String, Object> map;
            for (Object[] array : resultArrays) {
                map = new HashMap<>();
                map.put("qtd", array[0]);
                map.put("desc", array[1]);
                map.put("setRespon", array[2]);
                resultMaps.add(map);
            }
        } catch (Exception e) {
            System.out.println("ERRO no mÃ©todo usuDashboard " + e.getMessage());
            e.printStackTrace();
        }
        return resultMaps;
    }

    public Integer validarFinalizacao(Integer os) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select cast(count(*) as integer) as qtd from cad_os ");
        sql.append(" where exists (select * from cad_tarefa where nr_os = cast(nr_os_tarefa as integer) and sit_tarefa in ('01','02','03','04')) ");
        sql.append(" and nr_os = ").append(os);

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo validarFinalizacao " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
