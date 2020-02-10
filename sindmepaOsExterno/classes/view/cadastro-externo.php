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
                // Inclui o arquivo class.phpmailer.php localizado na mesma pasta do arquivo php 
                // Inicia a classe PHPMailer 
                include "../fachada/phpmailer/PHPMailerAutoload.php";
                $mail = new PHPMailer();

                if (isset($_POST['enviar'])) {

                    $cpfPostado = $_POST['cpfOutros'];

                    $verificaCPF = $cadExterno->findCPF($cpfPostado);

                    if (empty($verificaCPF)) {
                        echo '<div class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>SindmepaProtocol informa: </strong> Você ainda não é associado, por favor associe-se para poder abrir protocolos.</br>
                                    Entre em contato conosco através do nº XXXX-XXXX para saber mais informações.
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                  </div>';
                    } else {

                        $prot = $cadOs->carregaNumOs();
                        $valorID = $cadExterno->carregaIdExterno($cpfPostado);

                        $cadOs->setNrOs($prot);
                        $cadOs->setCategOs($_POST['catOs']);
                        $cadOs->setSetorResponOs($_POST['setor']);
                        $cadOs->setFuncResponOs('999');
                        $cadOs->setSetorAbertOs('0');
                        $cadOs->setFuncAbertOs($valorID->id_ext);
                        //print_r($cadOs->getFuncAbertOs());
                        $cadOs->setHistOs($_POST['historico']);
                        $cadOs->setSitOs('02');
                        $cadOs->setTipoEnvioOs('E');

                        $cadOs->insertCadOs();

                        $mail->IsSMTP();
                        //$mail->Host = 'sindmepa.org.br'; //"";  //h38.servidorhh.com
                        $mail->Host = 'smtp.gmail.com'; //"";  //h38.servidorhh.com
                        $mail->Port = 587; // 587 ou 465
                        $mail->SMTPAuth = true;
                        $mail->Username = 'victorps91@gmail.com'; 
                        $mail->Password = 'victor@1106';
                        // Configurações de compatibilidade para autenticação em TLS 
                        $mail->SMTPOptions = array('ssl' => array('verify_peer' => false, 'verify_peer_name' => false, 'allow_self_signed' => true));
                        $mail->From = "raphaelaraujo075@gmail.com";
                        $mail->FromName = "SINDMEPA";
                        $mail->AddAddress($valorID->email);
                        $mail->IsHTML(true);
// Charset (opcional) 
                        $mail->CharSet = 'UTF-8';
// Assunto da mensagem 
                        $mail->Subject = "Protocolo Aberto";

                        $det = $cadOs->detalhes($prot);

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
                            echo '<div class="alert alert-success" role="alert">
                                    <strong>SindmepaProtocol informa: </strong> Seu protocolo foi aberto com sucesso. Verifique sua caixa de e-mail e veja o número do protocolo aberto.</br>
                                    Entre em contato conosco através do nº XXXX-XXXX para saber mais informações.
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                  </div>';
                        } else {
                            echo "Houve um erro enviando o email: " . $mail->ErrorInfo;
                        }
                    }
                }

                if (isset($_POST['cadastrar'])) {

                    $verificaCPF = $cadExterno->findCPF($_POST['cpf']);


                    if (!empty($verificaCPF)) {
                        echo '<div class="alert alert-warning alert-dismissible fade show" role="alert">
                                    <strong>SindmepaProtocol informa: </strong> Você já possui protocolo aberto para Associação.</br>
                                    Entre em contato conosco através do nº XXXX-XXXX para saber mais informações.
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                  </div>';
                    } else {

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
                        //var_dump($_POST);
                        //var_dump($cadExterno);


                        $prot = $cadOs->carregaNumOs();

                        $cadOs->setNrOs($prot);
                        $cadOs->setCategOs($_POST['catOs']);
                        $cadOs->setSetorResponOs('4');
                        $cadOs->setFuncResponOs('999');
                        $cadOs->setSetorAbertOs('0');
                        $cadOs->setFuncAbertOs($cadExterno->getCadExternoId());
                        $cadOs->setHistOs('Externo');
                        $cadOs->setSitOs('02');
                        $cadOs->setTipoEnvioOs('E');

                        $cadOs->insertCadOs();


                        $mail->IsSMTP();
                        //$mail->Host = 'sindmepa.org.br'; //"";  //h38.servidorhh.com
                        $mail->Host = 'smtp.gmail.com'; //"";  //h38.servidorhh.com
                        $mail->Port = 587; // 587 ou 465
                        $mail->SMTPAuth = true;
                        $mail->Username = 'victorps91@gmail.com'; //''; 
                        $mail->Password = 'victor@1106'; //'victor@1106';
                        // Configurações de compatibilidade para autenticação em TLS 
                        $mail->SMTPOptions = array('ssl' => array('verify_peer' => false, 'verify_peer_name' => false, 'allow_self_signed' => true));
                        //$mail->From = "sindmepa@sindmepa.org.br";
                        $mail->From = "raphaelaraujo075@gmail.com";
                        $mail->FromName = "SINDMEPA";
                        $mail->AddAddress($_POST['email']);
                        $mail->IsHTML(true);
// Charset (opcional) 
                        $mail->CharSet = 'UTF-8';
// Assunto da mensagem 
                        $mail->Subject = "Protocolo Aberto";

                        $det = $cadOs->detalhes($prot);

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
                            echo '<div class="alert alert-success" role="alert">
                                    <strong>SindmepaProtocol informa: </strong> Seu protocolo foi aberto com sucesso. Verifique sua caixa de e-mail e veja o número do protocolo aberto.</br>
                                    Entre em contato conosco através do nº XXXX-XXXX para saber mais informações.
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                  </div>';
                        } else {
                            echo "Houve um erro enviando o email: " . $mail->ErrorInfo;
                        }
                    }
                }
                ?>
                <form method="POST">

                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <label for="ex2">Selecione a categoria desejada: </label>
                                <select class="form-control" name="catOs" id="cat" onchange="cadastrarcategoria()">
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
                    <!-- ASSOCIE-SE FORM -->
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
