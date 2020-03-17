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
require_once '../DB.php';

abstract class CadExterno extends DB {

    
    protected $tabela;
    
    public $id;
    public $nome;
    public $rg;
    public $cpf;
    public $sexo;
    public $dataNasc;
    public $crm;
    public $esp;
    public $tipoPssoa;
    public $email;
    public $idEnd; //chave estrangeira Endereço
    public $dtCad;
    public $dtUltAtu;
    public $cdTipPag;
    public $ag;
    public $bc;
    public $cc;
    public $nrMat;
    public $cdInstituicao;
	
        
//    function __construct($nome,$rg,$cpf,$sexo,$dataNasc,$crm,$esp,$tipoPssoa,
//                          $email,$dtCad,$dtUltAtu,$cdTipPag,$ag,$bc,$cc,$nrMat,
//                           $cdInstituicaoEndereco, $idEnd) {
//		$this->nome = $nome;
//                $this->rg = $rg;
//                $this->cpf = $cpf;
//                $this->sexo = $sexo;
//                $this->dataNasc = $dataNasc;
//                $this->crm = $crm;
//                $this->esp = $esp;
//                $this->tipoPssoa = $tipoPssoa;
//                $this->email = $email;
//                $this->dtCad = $dtCad;
//                $this->dtUltAtu = $dtUltAtu;
//                $this->cdTipPag = $cdTipPag;
//                $this->ag = $ag;
//                $this->bc = $bc;
//                $this->nrMat = $nrMat;
//                $this->cdInstituicao = $cdInstituicaoEndereco;
//                $this->idEnd = $idEnd;
//                
//    }
    
    
    
    function getNome() {
        return $this->nome;
    }

    function getRg() {
        return $this->rg;
    }

    function getCpf() {
        return $this->cpf;
    }

    function getSexo() {
        return $this->sexo;
    }

    function getDataNasc() {
        return $this->dataNasc;
    }

    function getCrm() {
        return $this->crm;
    }

    function getEsp() {
        return $this->esp;
    }

    function getTipoPssoa() {
        return $this->tipoPssoa;
    }

    function getEmail() {
        return $this->email;
    }

    function getIdEnd() {
        return $this->idEnd;
    }

    function getDtCad() {
        return $this->dtCad;
    }

    function getDtUltAtu() {
        return $this->dtUltAtu;
    }

    function getCdTipPag() {
        return $this->cdTipPag;
    }

    function getAg() {
        return $this->ag;
    }

    function getBc() {
        return $this->bc;
    }

    function getCc() {
        return $this->cc;
    }

    function getNrMat() {
        return $this->nrMat;
    }

    function getCdInstituicao() {
        return $this->cdInstituicao;
    }

    function setNome($nome) {
        $this->nome = $nome;
    }

    function setRg($rg) {
        $this->rg = $rg;
    }

    function setCpf($cpf) {
        $this->cpf = $cpf;
    }

    function setSexo($sexo) {
        $this->sexo = $sexo;
    }

    function setDataNasc($dataNasc) {
        $this->dataNasc = $dataNasc;
    }

    function setCrm($crm) {
        $this->crm = $crm;
    }

    function setEsp($esp) {
        $this->esp = $esp;
    }

    function setTipoPssoa($tipoPssoa) {
        $this->tipoPssoa = $tipoPssoa;
    }

    function setEmail($email) {
        $this->email = $email;
    }

    function setIdEnd($idEnd) {
        $this->idEnd = $idEnd;
    }

    function setDtCad($dtCad) {
        $this->dtCad = $dtCad;
    }

    function setDtUltAtu($dtUltAtu) {
        $this->dtUltAtu = $dtUltAtu;
    }

    function setCdTipPag($cdTipPag) {
        $this->cdTipPag = $cdTipPag;
    }

    function setAg($ag) {
        $this->ag = $ag;
    }

    function setBc($bc) {
        $this->bc = $bc;
    }

    function setCc($cc) {
        $this->cc = $cc;
    }

    function setNrMat($nrMat) {
        $this->nrMat = $nrMat;
    }

    function setCdInstituicao($cdInstituicao) {
        $this->cdInstituicao = $cdInstituicao;
    }



}
