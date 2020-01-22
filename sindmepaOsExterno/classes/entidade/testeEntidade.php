<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of testeEntidade
 *
 * @author DELL
 */
require_once 'classes/DB.php';

abstract class testeEntidade extends DB {

    protected $tabela;
    public $nome;

    function getNome() {
        return $this->nome;
    }

    function setNome($nome): void {
        $this->nome = $nome;
    }

}
