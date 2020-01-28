<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of testeFachada
 *
 * @author DELL
 */
require_once '../entidade/CadExterno.php';
require_once '../entidade/Endereco.php';

class CadExternoFachada extends CadExterno {

    protected $tabela = 'cad_externo';
	public $cadExternoId;
    
    public function findUnit($id) {
        $sql = "select * from  $this->tabela where id = :id";
        $stm = DB::prepare($sql);
        $stm->bindParam(':id', $id);
        $stm->execute();
        return $stm->fetch();
    }

    public function findAll() {
        $sql = "select * from  $this->tabela";
        $stm = DB::prepare($sql);
        $stm->execute();
        return $stm->fetchAll();
    }

    public function insertCadExterno() {        
        $sql = "INSERT INTO cad_externo(
                        nome_ext, rg_ext, cpf_ext, sexo_ext, data_nasc_ext, crm_ext, 
                        esp_ext, email, cd_tip_pag_ext, id_end_ext, ag_ext, bc_ext, cc_ext, nr_mat_ext, cd_inst_ext, tipo_pes_ext)
                VALUES (:nome, :rg, :cpf, :sexo, :dtNascimento, :crm, :especialidade, 
                        :email, :tipoPagamento, :idEnd, :ag, :bc, :cc, :nrMat, :cdInst, :tipoPessoa);
            ";
        $stm = DB::prepare($sql);
        $stm->bindParam(':nome', $this->nome);
        $stm->bindParam(':rg', $this->rg);
        $stm->bindParam(':cpf', $this->cpf);
        $stm->bindParam(':sexo', $this->sexo);
        $stm->bindParam(':dtNascimento', $this->dataNasc);
        $stm->bindParam(':crm', $this->crm);
        $stm->bindParam(':especialidade', $this->esp);
        $stm->bindParam(':email', $this->email);
        $stm->bindParam(':tipoPagamento', $this->cdTipPag);
        $stm->bindParam(':idEnd', $this->idEnd);
        $stm->bindParam(':ag', $this->ag);
        $stm->bindParam(':bc', $this->bc);
        $stm->bindParam(':cc', $this->cc);
        $stm->bindParam(':nrMat', $this->nrMat);
        $stm->bindParam(':cdInst', $this->cdInstituicao);
        $stm->bindParam(':tipoPessoa', $this->tipoPssoa);
        $stm->execute();
        $this->cadExternoId = DB::lastInsertId();
    }   

    public function update($id) {
        $sql = "UPDATE $this->tabela SET nome=:nome WHERE id = :id";
        $stm = DB::prepare($sql);
        $stm->bindParam(':id', $id);
        $stm->bindParam(':nome', $this->nome);
        return $stm->execute();
    }

    public function delete($id) {
        $sql = "DELETE FROM $this->tabela WHERE id = :id";
        $stm = DB::prepare($sql);
        $stm->bindParam(':id', $id);
        return $stm->execute();
    }
	
	function getCadExternoId() {
        return $this->cadExternoId;
    }

}
