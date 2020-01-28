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

    public function findAll($itens_por_pagina) {
        $sql = "select nr_os as os 
                       ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR' 
                        and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  
                       ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria 
                       ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor 
                       ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert 
                       ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha 
                       ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit                 
                from $this->tabela 
                limit $itens_por_pagina";
        $stm = DB::prepare($sql);
        $stm->execute();
        return $stm->fetchAll();
    }

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

}
