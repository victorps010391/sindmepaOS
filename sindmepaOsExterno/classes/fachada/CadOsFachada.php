<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadOsFachada
 *
 * @author DELL
 */
require_once '../entidade/CadOs.php';

class CadOsFachada extends CadOs {

    protected $tabela = 'cad_os';

    public function pesquisar($os) {
        $sql = "select nr_os as os 
                       ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' 
                        and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  
                       ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria 
                       ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor 
                       ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert 
                       ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha 
                       ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit                 
                from $this->tabela where nr_os = $os";
        $stm = DB::prepare($sql);
        $stm->execute();
        return $stm->fetchAll();
    }

    public function detalhes($os) {
        $sql = "select nr_os                                                                                                    as os 													   
                       ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                          
                        and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade 
                       ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os))                         as categoria                          
                       ,(select nm_setor from cad_setor where cd_setor = setor_respon_os)                                       as setor_responsavel                                
                       ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS')                             as data_hora_abert                        
                       ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS')                             as data_hora_fecha                        
                       ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os)  as sit         
                       ,sit_os                                                                                                  as cd_sit                                                                                                      
                       ,upper(hist_os)                                                                                          as historico                                                                                           
                       ,upper(obs_os)                                                                                           as observacao                                                                                           
                       ,(select nm_setor from cad_setor where cd_setor = setor_abert_os)                                        as setor_abertura                                    
                       ,setor_abert_os                                                                                          as cod_setor_abert                                                                                     
                       ,categ_os                                                                                                as cod_categoria                                                                                             
                       ,(case when setor_abert_os = 0                                                                                         
                              then (select nome_ext from cad_externo where id_ext = func_abert_os)                                            
                              else (select nm_func from cad_funcionario where cd_func = func_abert_os)                                        
                              end)                                                                                              as func_abert                                                                                              
                       ,upper(desc_finalizacao_os)                                                                              as desc_finalizacao                                                                        
                       ,(select nm_func from cad_funcionario where cd_func = func_respon_os)                                    as func_respon                                   
                       ,(select nm_func from cad_funcionario where cd_func = func_finali_os)                                    as func_finali                                   
                       ,(case when tipo_envio_os = 'I' then 'INTERNO' ELSE 'EXTERNO' END)                                       as tipo_envio                                       
                       ,func_abert_os                                                                                           as func_abert_os               
                from $this->tabela where nr_os = $os";
        $stm = DB::prepare($sql);
        $stm->execute();
        return $stm->fetchAll();
    }

}
