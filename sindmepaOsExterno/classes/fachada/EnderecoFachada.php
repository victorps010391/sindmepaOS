<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once '../entidade/Endereco.php';

class EnderecoFachada extends Endereco {
    
  protected $tabela = 'endereco';
    
  public function insertEndereco() {        
        $sql = "INSERT INTO endereco(
                        endereco, cep_end, nm_end, bairro_end, cid_end, est_end, 
                        tel_com_end, cel_end, wtp_end)
                VALUES (:endereco, :cepEnd, :nmEnd, :bairroEnd, :cidEnd, :estEnd, 
                        :telComEnd, :celEnd, :wtpEnd);";
        $stm = DB::prepare($sql);
        $stm->bindParam(':endereco', $this->nomeEnd);
        $stm->bindParam(':cepEnd', $this->cepEnd);
        $stm->bindParam(':nmEnd', $this->nmEnd);
        $stm->bindParam(':bairroEnd', $this->bairroEnd);
        $stm->bindParam(':cidEnd', $this->cidEnd);
        $stm->bindParam(':estEnd', $this->estEnd);
        $stm->bindParam(':telComEnd', $this->telComEnd);
        $stm->bindParam(':celEnd', $this->celEnd);
        $stm->bindParam(':wtpEnd', $this->wtpEnd);
        return $stm->execute();
    }
}
