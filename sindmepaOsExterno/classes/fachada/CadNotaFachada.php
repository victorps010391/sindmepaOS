<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadNotaFachada
 *
 * @author cdi_vsilva
 */
require_once '../entidade/CadNota.php';

class CadNotaFachada extends CadNota {

    protected $tabela = 'cad_nota';
    
    public function pesquisa($os){
        $sql = "select nr_os_nota as os 
                ,hist_nota as historico 
                ,TO_CHAR(dt_regi_nota, 'DD/MM/YYYY')||' '||TO_CHAR(dt_regi_nota, 'HH24:MI:SS') as data_hora_regi 
                ,TO_CHAR(dt_ult_atu_nota, 'DD/MM/YYYY')||' '||TO_CHAR(dt_ult_atu_nota, 'HH24:MI:SS') as data_ultima_atualizacao 
                ,serial_nota as serial 
                ,cast(nr_os_nota as character varying)||' / '||cast(serial_nota as character varying) as os_nota 
                ,case when invalida_nota = 'S' then 'CANCELADA' else 'ATIVA' end as situacao_nota 
         from $this->tabela 
         where nr_os_nota = $os 
         order by dt_ult_atu_nota desc ";
    }

    public function insertCadNota() {
        $sql = "INSERT INTO cad_nota(nr_os_nota, 
                                     serial_nota, 
                                     hist_nota, 
                                     dt_regi_nota, 
                                     func_regi_nota, 
                                     dt_ult_atu_nota, 
                                     func_ult_atu_nota, 
                                     invalida_nota)
                             VALUES (:nrOsnota, 
                                     :serialNota, 
                                     :histNota, 
                                     current_timestamp, ´
                                     :funcRegiNota, 
                                     current_timestamp, 
                                     :funcUltAtuNota, 
                                     :invalidaNota); ";
        $stm = DB::prepare($sql);
        //$stm->bindParam(':nrOs', $this->nrOs);
        $stm->bindParam(':nrOsnota', $this->nrOs);
        $stm->bindParam(':serialNota', $this->serialNota);
        $stm->bindParam(':histNota', $this->histNota);
        $stm->bindParam(':funcRegiNota', $this->funcRegiNota);
        $stm->bindParam(':funcUltAtuNota', $this->funcUltAtuNota);
        $stm->bindParam(':invalidaNota', $this->invalidaNota);
        return $stm->execute();
    }

    public function seqNota($os) {
        $sql = "SELECT retorna_seq($os);";
        $stm = DB::prepare($sql);        
        return $stm->execute();
    }

}
