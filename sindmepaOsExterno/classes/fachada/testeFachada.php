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
require_once 'classes/entidade/testeEntidade.php';

class testeFachada extends testeEntidade {

    protected $tabela = 'teste_php';
    
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

    public function insert() {        
        $sql = "INSERT INTO $this->tabela (nome) VALUES (:nome)";
        $stm = DB::prepare($sql);
        $stm->bindParam(':nome', $this->nome);
        return $stm->execute();
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

}
