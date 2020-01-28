<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadOs
 *
 * @author DELL
 */
require_once '../DB.php';

abstract class CadOs extends DB {

    protected $tabela;
    
    public $nrOs;
    public $categOs;
    public $setorResponOs;
    public $funcResponOs;
    public $setorAbertOs;
    public $funcAbertOs;
    public $histOs;
    public $obsOs;
    public $sitOs;
    public $tipoEnvioOs;
    public $dtAbertOs;
    public $dtFechaOs;
    public $dtUltAtuOs;
    public $funcUltAtuOs;
    public $descFinalizacaoOs;
    public $funcFinaliOs;

    function getNrOs() {
        return $this->nrOs;
    }

    function getCategOs() {
        return $this->categOs;
    }

    function getSetorResponOs() {
        return $this->setorResponOs;
    }

    function getFuncResponOs() {
        return $this->funcResponOs;
    }

    function getSetorAbertOs() {
        return $this->setorAbertOs;
    }

    function getFuncAbertOs() {
        return $this->funcAbertOs;
    }

    function getHistOs() {
        return $this->histOs;
    }

    function getObsOs() {
        return $this->obsOs;
    }

    function getSitOs() {
        return $this->sitOs;
    }

    function getTipoEnvioOs() {
        return $this->tipoEnvioOs;
    }

    function getDtAbertOs() {
        return $this->dtAbertOs;
    }

    function getDtFechaOs() {
        return $this->dtFechaOs;
    }

    function getDtUltAtuOs() {
        return $this->dtUltAtuOs;
    }

    function getFuncUltAtuOs() {
        return $this->funcUltAtuOs;
    }

    function getDescFinalizacaoOs() {
        return $this->descFinalizacaoOs;
    }

    function getFuncFinaliOs() {
        return $this->funcFinaliOs;
    }

    function setNrOs($nrOs) {
        $this->nrOs = $nrOs;
    }

    function setCategOs($categOs) {
        $this->categOs = $categOs;
    }

    function setSetorResponOs($setorResponOs) {
        $this->setorResponOs = $setorResponOs;
    }

    function setFuncResponOs($funcResponOs) {
        $this->funcResponOs = $funcResponOs;
    }

    function setSetorAbertOs($setorAbertOs) {
        $this->setorAbertOs = $setorAbertOs;
    }

    function setFuncAbertOs($funcAbertOs) {
        $this->funcAbertOs = $funcAbertOs;
    }

    function setHistOs($histOs) {
        $this->histOs = $histOs;
    }

    function setObsOs($obsOs) {
        $this->obsOs = $obsOs;
    }

    function setSitOs($sitOs) {
        $this->sitOs = $sitOs;
    }

    function setTipoEnvioOs($tipoEnvioOs) {
        $this->tipoEnvioOs = $tipoEnvioOs;
    }

    function setDtAbertOs($dtAbertOs) {
        $this->dtAbertOs = $dtAbertOs;
    }

    function setDtFechaOs($dtFechaOs) {
        $this->dtFechaOs = $dtFechaOs;
    }

    function setDtUltAtuOs($dtUltAtuOs) {
        $this->dtUltAtuOs = $dtUltAtuOs;
    }

    function setFuncUltAtuOs($funcUltAtuOs) {
        $this->funcUltAtuOs = $funcUltAtuOs;
    }

    function setDescFinalizacaoOs($descFinalizacaoOs) {
        $this->descFinalizacaoOs = $descFinalizacaoOs;
    }

    function setFuncFinaliOs($funcFinaliOs) {
        $this->funcFinaliOs = $funcFinaliOs;
    }

}
