<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of CadSetorFachada
 *
 * @author Desenv
 */

require_once '../entidade/CadSetor.php';

class CadSetorFachada extends CadSetor{
    
    protected $tabela = 'cad_setor';
    
    public function listaSetor() {
        try {
            $sql = "select cd_setor, nm_setor from  $this->tabela where usu_setor in ('E', 'A') ; ";
            $stm = DB::prepare($sql);
            $stm->execute();
            return $stm->fetchAll();
        } catch (Exception $e){
            echo 'Exceção capturada: '.  $e->getMessage(). "\n";
        }
    }
}
