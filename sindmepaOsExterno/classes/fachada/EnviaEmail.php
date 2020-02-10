<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of EnviaEmail
 *
 * @author Desenv
 */
include "../fachada/phpmailer/PHPMailerAutoload.php";
require_once '../fachada/CadOsFachada.php';

class EnviaEmail {

    public $prot, $det;
    
    public function enviarEmail() {
        $cadOs = new CadOsFachada();
                
       // $this->det = $cadOs->detalhes($this->prot);
        // Inclui o arquivo class.phpmailer.php localizado na mesma pasta do arquivo php 
        // Inicia a classe PHPMailer 
        $mail = new PHPMailer();
        $mail->IsSMTP();
        $mail->Host = 'sindmepa.org.br'; //"";  //h38.servidorhh.com
        $mail->Port = 587; // 587 ou 465
        $mail->SMTPAuth = true;
        $mail->Username = 'sindmepa'; //''; 
        $mail->Password = 'H0moS@piensCasado'; //'victor@1106';
        // Configurações de compatibilidade para autenticação em TLS 
        $mail->SMTPOptions = array('ssl' => array('verify_peer' => false, 'verify_peer_name' => false, 'allow_self_signed' => true));
        $mail->From = "sindmepa@sindmepa.org.br";
        $mail->FromName = "SINDMEPA";
        $mail->AddAddress($_POST['email']);
        $mail->IsHTML(true);
// Charset (opcional) 
        $mail->CharSet = 'UTF-8';
// Assunto da mensagem 
        $mail->Subject = "Protocolo Aberto";
        
       $this->det = $cadOs->detalhes($this->prot);

// Corpo do email 
        foreach ($det as $det) {

            $mail->Body = '<p style="font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;">SindmepaProtocol informa,<br />' .
                    "Seu protocolo foi enviado para atendimento com sucesso para nossa central com as seguintes informações: <br /><br />" .
                    "<strong>Número do protocolo: </strong> " . $det->os .
                    "<br />" .
                    "<strong>Categoria: </strong>" . $det->categoria .
                    "<br />" .
                    "<strong>Setor Responsável: </strong>" . $det->setor_responsavel .
                    "<br />" .
                    "<strong>Prioridade: </strong>" . $det->prioridade .
                    "<br />" .
                    "<strong>Solicitação: </strong>" . $det->historico .
                    "<br />" .
                    "<strong>Data de Abertura: </strong>" . $det->data_hora_abert .
                    "<br />" .
                    "<br /><br />" .
                    "<i>Email Enviado automaticamente pelo sistema" .
                    "<br />" .
                    "Data:" . $det->data_hora_abert .
                    "<br />" .
                    "Softbean ©" .
                    "</i></p>"
            ;
        }
// Opcional: Anexos 
// $mail->AddAttachment("/home/usuario/public_html/documento.pdf", "documento.pdf"); 
// Envia o e-mail 
        $enviado = $mail->Send();
// Exibe uma mensagem de resultado 
        if ($enviado) {
            //header("Location: http://localhost/sindmepaOsExterno/classes/view/cadastro-externo.php");
            echo "Seu email foi enviado com sucesso!";
        } else {
            echo "Houve um erro enviando o email: " . $mail->ErrorInfo;
        }
    }

}
