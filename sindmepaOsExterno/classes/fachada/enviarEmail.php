<?php   
 
// Inclui o arquivo class.phpmailer.php localizado na mesma pasta do arquivo php 
include "phpmailer/PHPMailerAutoload.php"; 
require_once '../view/cadastro-externo.php';
// Inicia a classe PHPMailer 
$mail = new PHPMailer(); 
$mail->IsSMTP(); 
$mail->Host = 'sindmepa.org.br';//"";  //h38.servidorhh.com
$mail->Port = 587; // 587 ou 465
$mail->SMTPAuth = true; 
$mail->Username = 'sindmepa';//''; 
$mail->Password = 'H0moS@piensCasado';//'victor@1106';
// Configurações de compatibilidade para autenticação em TLS 
$mail->SMTPOptions = array( 'ssl' => array( 'verify_peer' => false, 'verify_peer_name' => false, 'allow_self_signed' => true ) ); 
$mail->From = "sindmepa@sindmepa.org.br"; 
$mail->FromName = "SINDMEPA";
$mail->AddAddress($_POST['email']);
$mail->IsHTML(true);
// Charset (opcional) 
$mail->CharSet = 'UTF-8'; 
// Assunto da mensagem 
$mail->Subject = "Protocolo Aberto"; 
 
// Corpo do email 
$mail->Body = '"<p style="font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;">SindmepaProtocol informa,<br />' .
"Seu protocolo foi enviado para atendimento com sucesso para nossa central com as seguintes informações: <br /><br />" .
"<strong>Número do protocolo: </strong>' " . 
"<br />" .
"<strong>Categoria: </strong>" . $cadOs->getCategOs();
"<br />" .        
"<strong>Setor Responsável: </strong>" .
"<br />" .        
"<strong>Prioridade: </strong>" .
"<br />" .
"<strong>Solicitação: </strong>" .
"<br />" .
"<strong>Data de Abertura: </strong>" .
"<br />" .
"<br /><br />" .
"<i>Email Enviado automaticamente pelo sistema" . 
"<br />" .
"Data:" . 

"<br />" .
"Softbean ©" .
"</i></p>"        
;
// Opcional: Anexos 
// $mail->AddAttachment("/home/usuario/public_html/documento.pdf", "documento.pdf"); 
// Envia o e-mail 
$enviado = $mail->Send(); 
// Exibe uma mensagem de resultado 
if ($enviado) 
{ 
    header("Location: http://localhost/sindmepaOsExterno/classes/view/cadastro-externo.php");
    echo "Seu email foi enviado com sucesso!";
    
} else { 
    echo "Houve um erro enviando o email: ".$mail->ErrorInfo; 
} 
 
 
?>