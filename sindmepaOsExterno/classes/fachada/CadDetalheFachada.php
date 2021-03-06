<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once '../entidade/CadDetalhe.php';

class CadDetalheFachada extends CadDetalhe {
    
    protected $tabela = 'cad_detalhe';
    
    public function listaInstituicao() {
        try {
            $sql = "select cod_valor_detalhe, desc_detalhe from  $this->tabela where cod_item_detalhe = 'PAGIN'; ";
            $stm = DB::prepare($sql);
            $stm->execute();
            return $stm->fetchAll();
        } catch (Exception $e){
            echo 'Exceção capturada: '.  $e->getMessage(). "\n";
        }
    }
    
    public function listaTipoBanco() {
        try {
            $sql = "select cod_valor_detalhe, desc_detalhe from $this->tabela where cod_item_detalhe = 'TIPBC';";
            $stm = DB::prepare($sql);
            $stm->execute();
            return $stm->fetchAll();
        } catch (Exception $e){
            echo 'Exceção capturada: '.  $e->getMessage(). "\n";
        }    
    }
}