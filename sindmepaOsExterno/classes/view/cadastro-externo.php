<?php
spl_autoload_register(function($classe) {
    include '../fachada/' . $classe . '.php';
});
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../../css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="../../js/bootstrap.min.js"></script>
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
            <h3>Associe-se</h3>
            <div class="card card-default">
                <div class="card-header"><h4>Cadastro de Associado</h4></div>
                <?php
                $endereco = new EnderecoFachada();
                $cadExterno = new CadExternoFachada();
                $cadDetalhe = new CadDetalheFachada();
                $cadOs = new CadOsFachada();
                $cadCategoria = new CadCartegoriaFachada();
                $cadSetor = new CadSetorFachada();

                if (isset($_POST['cadastrar']) || isset($_POST['enviar'])) {

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

                    $cadOs->setSetorResponOs('4');
                    $cadOs->setFuncResponOs('999');
                    $cadOs->setFuncAbertOs($cadExterno->getCadExternoId());
                    $cadOs->setSitOs('02');
                    $cadOs->setTipoEnvioOs('E');
                    $cadOs->setCategOs($_POST['catOs']);
                    var_dump($_POST);
                    $cadOs->setSetorAbertOs($_POST['setor']);

                    if (empty($cadOs->getSetorAbertOs())) {
                        $cadOs->setSetorAbertOs(0);
                        echo 'SETOR:' . $cadOs->getSetorAbertOs() . '</br>';
                    } else {
                        $cadOs->setSetorAbertOs($_POST['setor']);
                    }

                    if ($cadOs->getCategOs() == 0) {
                        $cadOs->setHistOs('Pedido de Associação');
                        echo 'Historico: ' . $cadOs->getHistOs() . '</br>';
                        echo 'Categoria: ' . $cadOs->getCategOs();
                    } else {
                        $cadOs->setHistOs($_POST['historico']);
                        echo 'Historico: ' . $cadOs->getHistOs();
                    }

                    if (empty($cadOs->getObsOs())) {
                        $cadOs->setObsOs('');
                    };

                    $cadOs->insertCadOs();
                }
                ?>
                <form method="POST">

                    <div class="card-body">
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <label for="ex2">Selecione a categoria desejada: </label>
                                <select class="form-control" name="catOs" id="catOs" onchange="cadastrarcategoria()">
                                    <option></option>
                                    <?php foreach ($cadCategoria->listaCategoria() as $key => $value) { ?>
                                        <option value="<?php echo $value->id_categoria; ?>">
                                            <?php echo $value->desc_categoria; ?>
                                        </option>
                                    <?php } ?> 
                                </select>
                            </div>
                        </div>    
                    </div>

                    <!-- form do ASSOCIE-SE -->
                    <div id="cadastrarcategoria" style="display: none;">
                        <?php include './formAssocieSe.php'; ?>
                    </div>
                    <!-- OUTRO FORM -->
                    <div id="outra" style="display: none;">
                        <?php include './formOutros.php'; ?>
                    </div>
                </form><!-- fim form -->
            </div>
        </div>

    </body>
    <script src="../../js/custom-campos.js"></script>
    <script>
                                    function cadastrarcategoria() {
                                        var categoria = document.getElementById("catOs").value;

                                        if (categoria === '0') {
                                            document.getElementById("cadastrarcategoria").style.display = "block";
                                            document.getElementById("outra").style.display = "none";
                                        } else {
                                            document.getElementById("cadastrarcategoria").style.display = "none";
                                            document.getElementById("outra").style.display = "block";
                                        }
                                    }
    </script>
</html>
