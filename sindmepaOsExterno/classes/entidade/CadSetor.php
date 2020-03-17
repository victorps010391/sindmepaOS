<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadSetor
 *
 * @author Desenv
 */

require_once '../DB.php';

class CadSetor extends DB{
  
  public $nm_setor;
  public $sit_setor;
  public $usu_setor;
  
  function getNm_setor() {
      return $this->nm_setor;
  }

  function getSit_setor() {
      return $this->sit_setor;
  }

  function getUsu_setor() {
      return $this->usu_setor;
  }

  function setNm_setor($nm_setor) {
      $this->nm_setor = $nm_setor;
  }

  function setSit_setor($sit_setor) {
      $this->sit_setor = $sit_setor;
  }

  function setUsu_setor($usu_setor) {
      $this->usu_setor = $usu_setor;
  }


}
