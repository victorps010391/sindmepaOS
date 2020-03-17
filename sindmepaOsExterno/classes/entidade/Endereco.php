<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once '../DB.php';

abstract class Endereco extends DB {

    
    protected $tabela;

    public $idEnd;
    public $nomeEnd;
    public $cepEnd;
    public $nmEnd;
    public $bairroEnd;
    public $cidEnd;
    public $estEnd;
    public $telComEnd;
    public $celEnd;
    public $wtpEnd;

    function getIdEnd() {
        return $this->idEnd;
    }
    
    function getNomeEnd() {
        return $this->nomeEnd;
    }

    function setNomeEnd($nomeEnd) {
        $this->nomeEnd = $nomeEnd;
    }
    
    function getCepEnd() {
        return $this->cepEnd;
    }

    function getNmEnd() {
        return $this->nmEnd;
    }

    function getBairroEnd() {
        return $this->bairroEnd;
    }

    function getCidEnd() {
        return $this->cidEnd;
    }

    function getEstEnd() {
        return $this->estEnd;
    }

    function getTelComEnd() {
        return $this->telComEnd;
    }

    function getCelEnd() {
        return $this->celEnd;
    }

    function getWtpEnd() {
        return $this->wtpEnd;
    }

    function setIdEnd($idEnd) {
        $this->idEnd = $idEnd;
    }

    function setCepEnd($cepEnd) {
        $this->cepEnd = $cepEnd;
    }

    function setNmEnd($nmEnd) {
        $this->nmEnd = $nmEnd;
    }

    function setBairroEnd($bairroEnd) {
        $this->bairroEnd = $bairroEnd;
    }

    function setCidEnd($cidEnd) {
        $this->cidEnd = $cidEnd;
    }

    function setEstEnd($estEnd) {
        $this->estEnd = $estEnd;
    }

    function setTelComEnd($telComEnd) {
        $this->telComEnd = $telComEnd;
    }

    function setCelEnd($celEnd) {
        $this->celEnd = $celEnd;
    }

    function setWtpEnd($wtpEnd) {
        $this->wtpEnd = $wtpEnd;
    }
}
