<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadNota
 *
 * @author cdi_vsilva
 */
require_once '../DB.php';

abstract class CadNota extends DB {

    protected $tabela;
    
    public $nrOs;
    public $serialNota;
    public $histNota;
    public $dtRegiNota;
    public $funcRegiNota;
    public $dtUltAtuNota;
    public $funcUltAtuNota;
    public $invalidaNota;
    
    function getNrOs() {
        return $this->nrOs;
    }

    function getSerialNota() {
        return $this->serialNota;
    }

    function getHistNota() {
        return $this->histNota;
    }

    function getDtRegiNota() {
        return $this->dtRegiNota;
    }

    function getFuncRegiNota() {
        return $this->funcRegiNota;
    }

    function getDtUltAtuNota() {
        return $this->dtUltAtuNota;
    }

    function getFuncUltAtuNota() {
        return $this->funcUltAtuNota;
    }

    function getInvalidaNota() {
        return $this->invalidaNota;
    }

    function setNrOs($nrOs) {
        $this->nrOs = $nrOs;
    }

    function setSerialNota($serialNota) {
        $this->serialNota = $serialNota;
    }

    function setHistNota($histNota) {
        $this->histNota = $histNota;
    }

    function setDtRegiNota($dtRegiNota) {
        $this->dtRegiNota = $dtRegiNota;
    }

    function setFuncRegiNota($funcRegiNota) {
        $this->funcRegiNota = $funcRegiNota;
    }

    function setDtUltAtuNota($dtUltAtuNota) {
        $this->dtUltAtuNota = $dtUltAtuNota;
    }

    function setFuncUltAtuNota($funcUltAtuNota) {
        $this->funcUltAtuNota = $funcUltAtuNota;
    }

    function setInvalidaNota($invalidaNota) {
        $this->invalidaNota = $invalidaNota;
    }

}
