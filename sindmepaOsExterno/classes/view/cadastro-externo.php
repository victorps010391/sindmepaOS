<?php
spl_autoload_register(function($classe) {
    include '../fachada/' . $classe . '.php';
});
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>SINDMEPA - Gerenciamento de Protocolos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../../css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="../../js/bootstrap.min.js"></script>
        <script src="../../js/custom-campos.js"></script>
        <style>
            .card-default>.card-header {
                color: #333;
                background-color: #2ecc71;
            }

            /* ESCONDE TODAS AS DIVS */
            .divs{
                display:none;
            }

        </style>
    </head>
    <body>

        <div class="container">
            <h3>Cadastro de protocolo</h3>
            <div class="card card-default">                
                <?php
                $endereco = new EnderecoFachada();
                $cadExterno = new CadExternoFachada();
                $cadDetalhe = new CadDetalheFachada();
                $cadOs = new CadOsFachada();
                $cadCategoria = new CadCartegoriaFachada();
                $cadSetor = new CadSetorFachada();

                if (isset($_POST['cadastrar'])) {

                    $nomeEndereco = $_POST['endereco'];
                    $cep = $_POST['cep'];
                    $numero = $_POST['numero'];
                    $bairro = $_POST['bairro'];
                    $estado = $_POST['estado'];
                    $cidade = $_POST['cidade'];
                    $telComercial = $_POST['telComercial'];
                    $celular = $_POST['celular'];
                    $whatsapp = $_POST['whatsapp'];

                    $endereco->setNomeEnd($nomeEndereco);
                    $endereco->setCepEnd($cep);
                    $endereco->setNmEnd($numero);
                    $endereco->setBairroEnd($bairro);
                    $endereco->setEstEnd($estado);
                    $endereco->setCidEnd($cidade);
                    $endereco->setTelComEnd($telComercial);
                    $endereco->setCelEnd($celular);
                    $endereco->setWtpEnd($whatsapp);

                    $endereco->insertEndereco();

                    $cadExterno->setNome($_POST['nome']);
                    $cadExterno->setRg($_POST['rg']);
                    $cadExterno->setCpf($_POST['cpf']);
                    $cadExterno->setSexo($_POST['sexo']);
                    $cadExterno->setDataNasc($_POST['dtNascimento']);
                    $cadExterno->setCrm($_POST['crm']);
                    $cadExterno->setEsp($_POST['especialidade']);
                    $cadExterno->setEmail($_POST['email']);
                    $cadExterno->setCdTipPag($_POST['categoria']);
                    $cadExterno->setIdEnd($endereco->getEnderecoId());
                    $cadExterno->setTipoPssoa('E');
                    $cadExterno->setAg($_POST['ag']);
                    $cadExterno->setBc($_POST['bc']);
                    $cadExterno->setCc($_POST['cc']);
                    $cadExterno->setCdInstituicao($_POST['instituicao']);
                    $cadExterno->setNrMat($_POST['nrMat']);

                    $cadExterno->insertCadExterno();

                    $cadOs->setCategOs($_POST['categoria']);
                    $cadOs->setSetorResponOs('4');
                    $cadOs->setFuncResponOs('999');
                    $cadOs->setSetorAbertOs('0');
                    $cadOs->setFuncAbertOs($cadExterno->getCadExternoId());
                    $cadOs->setHistOs('Externo');
                    $cadOs->setSitOs('02');
                    $cadOs->setTipoEnvioOs('E');

                    $cadOs->insertCadOs();

                    // Inclui o arquivo class.phpmailer.php localizado na mesma pasta do arquivo php 
                    include "../fachada/phpmailer/PHPMailerAutoload.php";
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

// Corpo do email 
                    $mail->Body = '<p style="font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;">SindmepaProtocol informa,<br />' .
                            "Seu protocolo foi enviado para atendimento com sucesso para nossa central com as seguintes informações: <br /><br />" .
                            "<strong>Número do protocolo: </strong>' " .
                            "<br />" .
                            "<strong>Categoria: </strong>" . $cadOs->getCategOs() .
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
                    if ($enviado) {
                        header("Location: http://localhost/sindmepaOsExterno/classes/view/cadastro-externo.php");
                        echo "Seu email foi enviado com sucesso!";
                    } else {
                        echo "Houve um erro enviando o email: " . $mail->ErrorInfo;
                    }
                }
                ?>
                <form method="POST">

                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <label for="ex2">Selecione a categoria desejada: </label>
                                <select class="form-control" name="categoria" id="categoria" onchange="cadastrarcategoria()">
                                    <option value="SELECIONE"></option>
<?php foreach ($cadCategoria->listaCategoria() as $key => $value) { ?>
                                        <option value="<?php echo $value->id_categoria; ?>">
    <?php echo $value->desc_categoria; ?>
                                        </option>
                                    <?php } ?> 
                                </select>
                            </div>
                        </div>    
                    </div>
                    <!-- OUTRO FORM -->
                    <div id="cadastrarcategoria" style="display: none;">
<?php include './forms/formAssocieSe.php'; ?>
                    </div>
                    <!-- OUTRO FORM -->
                    <div id="outra" style="display: none;">
                        <?php include './forms/formOutros.php'; ?>
                    </div>
                </form><!-- fim form -->

            </div>
        </div>

    </body>
    <script src="../../js/custom-campos.js"></script>

</html>
