<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadCategoria
 *
 * @author Raphael
 */

require_once '../DB.php';

class CadCategoria extends DB{
  
  public $descCategoria;
  public $codPriorCategoria;
  public $dtUltAtuCategoria;
  public $usuUltAtuCategoria;
  public $dtRegCategoria;
  public $usuRegCateg;
  public $usuCategoria;
  
  function getDescCategoria() {
      return $this->descCategoria;
  }

  function getCodPriorCategoria() {
      return $this->codPriorCategoria;
  }

  function getDtUltAtuCategoria() {
      return $this->dtUltAtuCategoria;
  }

  function getUsuUltAtuCategoria() {
      return $this->usuUltAtuCategoria;
  }

  function getDtRegCategoria() {
      return $this->dtRegCategoria;
  }

  function getUsuRegCateg() {
      return $this->usuRegCateg;
  }

  function getUsuCategoria() {
      return $this->usuCategoria;
  }

  function setDescCategoria($descCategoria) {
      $this->descCategoria = $descCategoria;
  }

  function setCodPriorCategoria($codPriorCategoria) {
      $this->codPriorCategoria = $codPriorCategoria;
  }

  function setDtUltAtuCategoria($dtUltAtuCategoria) {
      $this->dtUltAtuCategoria = $dtUltAtuCategoria;
  }

  function setUsuUltAtuCategoria($usuUltAtuCategoria) {
      $this->usuUltAtuCategoria = $usuUltAtuCategoria;
  }

  function setDtRegCategoria($dtRegCategoria) {
      $this->dtRegCategoria = $dtRegCategoria;
  }

  function setUsuRegCateg($usuRegCateg) {
      $this->usuRegCateg = $usuRegCateg;
  }

  function setUsuCategoria($usuCategoria) {
      $this->usuCategoria = $usuCategoria;
  }


}
