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
                $enviaEmail = new EnviaEmail();

                if (isset($_POST['enviar'])) {

                    $cpfPostado = $_POST['cpf'];

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
                        $cadOs->setFuncAbertOs($valorID);
                        //print_r($cadOs->getFuncAbertOs());
                        $cadOs->setHistOs($_POST['historico']);
                        $cadOs->setSitOs('02');
                        $cadOs->setTipoEnvioOs('E');

                        $cadOs->insertCadOs();
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

                        $enviaEmail->enviarEmail();
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
