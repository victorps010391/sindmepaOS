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

                    <!-- form do ASSOCIE-SE -->
                    <div id="cadastrarcategoria" style="display: none;">
<<<<<<< HEAD
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <table class="table table-striped">
                                    <tr>
                                        <td>
                                            <input type="radio" name="categoria" value="01" onclick="habilitaCampos('televisao');" >Débito em conta corrente &nbsp;
                                            <input type="radio" name="categoria" value="02" onclick="habilitaCampos('radio');" >Débito em contra-cheque &nbsp;                 
                                            <input type="radio" name="categoria" value="04" onclick="habilitaCampos('web');" >Débito mensal no cartão de crédito &nbsp;
                                            <input type="radio" name="categoria" value="03" onclick="habilitaCampos('impresso');" >Anuidade Residente &nbsp;
                                        </td>                
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <div id="impresso" class="divs">
                                    <input type="file" onchange="(verificaExtensao(this), ValidateSize(this))" id="upload" name="arquivo" accept="application/pdf"> - até 2 MB e PDF
                                </div>
                                <!-- Débito em conta corrente -->    
                                <div id="televisao" class="divs">
                                    <div class="card-body">
                                        <div class="form-group row">
                                            <div class="col-sm-4">
                                                <label for="ex1">Agência</label>
                                                <input class="form-control" id="ex1" type="text" name="ag">
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="ex2">Banco</label>
                                                <select class="form-control" id="sel1" name="bc">
                                                    <?php foreach ($cadDetalhe->listaTipoBanco() as $key => $value) { ?>
                                                        <option value="<?php echo $value->cod_valor_detalhe; ?>">
                                                            <?php echo $value->desc_detalhe; ?>
                                                        </option>
                                                    <?php } ?> 
                                                </select>
                                            </div>
                                            <div class="col-sm-4">
                                                <label for="ex2">Conta Corrente</label>
                                                <input class="form-control" id="ex2" type="text" name="cc">
                                            </div>
                                        </div>    
                                    </div>
                                </div>
                                <!-- Débito em contra-cheque -->
                                <div id="radio" class="divs">
                                    <div class="form-group row">
                                        <div class="col-sm-3">
                                            <label for="sel1">Instituição a ser debitada</label>
                                            <select class="form-control" id="sel1" name="instituicao">
                                                <?php foreach ($cadDetalhe->listaInstituicao() as $key => $value) { ?>
                                                    <option value="<?php echo $value->cod_valor_detalhe; ?>"><?php echo $value->desc_detalhe; ?></option>
                                                <?php } ?> 
                                            </select>
                                        </div>
                                        <div class="col-sm-4">
                                            <label for="ex2">Número de Matrícula</label>
                                            <input class="form-control" id="ex2" type="text" name="nrMat">
                                        </div>
                                    </div>
                                    <div id="web" class="divs">

                                    </div>
                                </div> <!-- fim contra-cheque -->
                            </div>

                            <div class="card-body">
                                <div class="form-group row">
                                    <div class="col-sm-6">
                                        <label for="ex3">Nome completo</label>
                                        <input class="form-control" id="ex3" type="text" name="nome">
                                    </div>
                                </div>
                                <div class="form-group row">	
                                    <div class="col-sm-6">
                                        <label for="ex1">Endereço</label>
                                        <input class="form-control" id="ex1" type="text" name="endereco">
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="ex2">Complemento</label>
                                        <input class="form-control" id="ex2" type="text" name="">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-2">
                                        <label for="ex1">CEP</label>
                                        <input class="form-control" id="ex1" type="text" name="cep">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Número</label>
                                        <input class="form-control" id="ex1" type="text" name="numero">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Bairro</label>
                                        <input class="form-control" id="ex1" type="text" name="bairro">
                                    </div>
                                    <div class="col-sm-1">
                                        <label for="ex1">Estado</label>
                                        <input class="form-control" id="ex1" type="text" name="estado">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Cidade</label>
                                        <input class="form-control" id="ex1" type="text" name="cidade">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-3">
                                        <label for="ex2">RG</label>
                                        <input class="form-control" id="ex2" type="text" name="rg">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">CPF</label>
                                        <input class="form-control" id="ex1" type="text" name="cpf">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">E-mail</label>
                                        <input class="form-control" id="ex1" type="text" name="email">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Número do CRM</label>
                                        <input class="form-control" id="ex1" type="text" name="crm">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-3">
                                        <label for="sel1">Sexo:</label>
                                        <select class="form-control" id="sel1" name="sexo">
                                            <option value="M">Masculino</option>
                                            <option value="F">Feminino</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Telefone comercial</label>
                                        <input class="form-control" id="ex1" type="text" name="telComercial">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Celular</label>
                                        <input class="form-control" id="ex1" type="text" name="celular">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Whatsapp</label>
                                        <input class="form-control" id="ex1" type="text" name="whatsapp">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-3">
                                        <label for="ex1">Data de Nascimento</label>
                                        <input class="form-control" id="ex1" type="date" name="dtNascimento">
                                    </div>
                                    <div class="col-sm-3">
                                        <label for="ex1">Especialidade</label>
                                        <input class="form-control" id="ex1" type="text" name="especialidade">
                                    </div>
                                    <div class="col-sm-6">
                                        <label for="ex1">Como você chegou ao sindicato?</label></br>
                                        <label class="radio-inline"><input type="radio" name="optradio" checked>TV</label>
                                        <label class="radio-inline"><input type="radio" name="optradio">Mídias Sociais</label>
                                        <label class="radio-inline"><input type="radio" name="optradio">Silvana Batista</label>
                                        <label class="radio-inline"><input type="radio" name="optradio" checked>Indicação do colega</label>
                                        <label class="radio-inline"><input type="radio" name="optradio">Outro</label>

                                    </div>
                                </div>
                                <button type="submit" class="btn btn-secondary" name="cadastrar">Cadastrar</button>
                            </div><!-- fim card-body-->
                            </form><!-- fim form -->
                        </div> <!-- fim card-default -->
                    </div><!-- FIM FORM ASSOCIE-SE -->
                    <!-- OUTRO FORM -->
                    <div id="outra" style="display: none;">
                        Outra
                    </div> 
=======
                        <?php include './forms/formAssocieSe.php'; ?>
                    </div>
                    <!-- OUTRO FORM -->
                    <div id="outra" style="display: none;">
                        <?php include './forms/formOutros.php'; ?>
                    </div>
                </form><!-- fim form -->
>>>>>>> c2178bbde4136dace9e1bce5fbb7a30f181deaa2
            </div>
        </div>

    </body>
    <script src="../../js/custom-campos.js"></script>
</html>
