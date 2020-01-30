<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadCartegoriaFachada
 *
 * @author Raphael
 */

require_once '../entidade/CadCategoria.php';

class CadCartegoriaFachada extends CadCategoria{
    
    protected $tabela = 'cad_categoria';
    
    public function listaCategoria() {
        try {
            $sql = "select id_categoria, desc_categoria from  $this->tabela where usu_categoria = 'E'; ";
            $stm = DB::prepare($sql);
            $stm->execute();
            return $stm->fetchAll();
        } catch (Exception $e){
            echo 'Exceção capturada: '.  $e->getMessage(). "\n";
        }
    }
}
