<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once '../DB.php';

abstract class CadDetalhe extends DB {
    
  
  public $codItem;
  public $codValor;
  public $dtReg;
  public $usuReg;
  public $dtUltAtu;
  public $usuUltAtu;
  public $descDetalhe;
  
  function getCodItem() {
      return $this->codItem;
  }

  function getCodValor() {
      return $this->codValor;
  }

  function getDtReg() {
      return $this->dtReg;
  }

  function getUsuReg() {
      return $this->usuReg;
  }

  function getDtUltAtu() {
      return $this->dtUltAtu;
  }

  function getUsuUltAtu() {
      return $this->usuUltAtu;
  }

  function getDescDetalhe() {
      return $this->descDetalhe;
  }

  function setCodItem($codItem) {
      $this->codItem = $codItem;
  }

  function setCodValor($codValor) {
      $this->codValor = $codValor;
  }

  function setDtReg($dtReg) {
      $this->dtReg = $dtReg;
  }

  function setUsuReg($usuReg) {
      $this->usuReg = $usuReg;
  }

  function setDtUltAtu($dtUltAtu) {
      $this->dtUltAtu = $dtUltAtu;
  }

  function setUsuUltAtu($usuUltAtu) {
      $this->usuUltAtu = $usuUltAtu;
  }

  function setDescDetalhe($descDetalhe) {
      $this->descDetalhe = $descDetalhe;
  }


        
}

