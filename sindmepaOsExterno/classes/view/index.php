<?php
spl_autoload_register(function($classe) {
    include '../fachada/' . $classe . '.php'; 
});
?>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SINDMEPA - Gerenciamento de Protocolos</title>

        <!--CSS BOOTSTRAP-->
        <link href="../../css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>

        <div class="card  w-75 p-3" style="margin-left: auto;margin-right: auto">
            <?php
            $cadOs = new CadOsFachada();
            $cadNota = new CadNotaFachada();

            $protocolo = (isset($_POST['protocolo'])) ? $_POST['protocolo'] : '';

            if (empty($protocolo)):

                echo '<div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>SindmepaProtocol informa: </strong> para pesquisar digite o número do protocolo.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>';

            else:
                $consulta = $cadOs->pesquisar($protocolo);
                $consultaNota = $cadNota->pesquisa($protocolo);
                $detalhe = $cadOs->detalhes($protocolo);
            endif;
            ?>

            <div class="card-header">
                <strong>CONSULTA DE PROTOCOLOS ABERTOS</strong>
            </div>
            <br/>
            <form action="" method="post">
                <div class="col-sm-4">
                    <label for="protocolo">Protocolo:</label>
                    <input name="protocolo" id="protocolo" type="text" class="form-control"  placeholder="Nr. do Protocolo"> 
                    <br/> 
                    <button type="submit" class="btn btn-success">Pesquisar</button>                     
                    <a class="btn btn-success" href="cadastro-externo.php" role="button">Novo Protocolo</a>
                </div>
            </form>          
            <br/>  
            <?php if (!empty($consulta)): ?>

                <table class="table table-striped">
                    <thead>
                        <tr>                            
                            <th scope="col">Nr. do Protocolo</th>                      
                            <th scope="col">Categoria</th>                        
                            <th scope="col">Data e Hora de Abertura</th>                        
                            <th scope="col">Situação</th>
                            <th scope="col">Ações</th> 
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($consulta as $consulta): ?>
                            <tr>
                                <td><?= $consulta->os ?></td>                                        
                                <td><?= $consulta->categoria ?></td>                           
                                <td><?= $consulta->data_hora_abert ?></td>                           
                                <td><?= $consulta->sit ?></td>                                  
                                <td><button type="submit" 
                                            class="btn btn-primary"  
                                            data-toggle="modal" 
                                            data-target="#infoOs" 
                                            onclick="">Detalhes</button> 
                                    <input name="nrOs" id="nrOs" type="hidden" class="form-control"  value="<?= $consulta->os ?>"> 
                                    <!--                                    <button type="submit" 
                                                                                class="btn btn-primary"  
                                                                                data-toggle="modal" 
                                                                                data-target="#cadNotaModal" 
                                                                                onclick="">Criar Nota</button> 
                                                                        <input name="nrOsNota" id="nrOsNota" type="hidden" class="form-control"  value="< ?= $consulta->os ?>"> </td>  -->
                            </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>

            <?php else: ?>
                <h5 class="text-center text-primary">Sem registros.</h5>
            <?php endif; ?>

            <? php if (!empty($consultaNota)): ?>
<!--                <table class="table table-striped">
                    <thead>
                        <tr>                            
                            <th scope="col">Nr. Nota</th>                      
                            <th scope="col">Data e Hora de Registro</th>                        
                            <th scope="col">Nota</th>                                                  
                        </tr>
                    </thead>
                    <tbody>
                        <? php foreach ($consultaNota as $consultaNota): ?>
                            <tr>
                                <td><? = $consultaNota->os_nota ?></td>                                        
                                <td><? = $consultaNota->data_hora_regi ?></td>                           
                                <td><? = $consultaNota->historico ?></td>                                                                                                                                     
                            </tr>
                        <? php endforeach; ?>
                    </tbody>
                </table>-->
            <? php else: ?>
            <!--<h5 class="text-center text-primary">Sem notas cadastradas.</h5>-->
            <? php endif; ?>
        </div>        
        <div class="modal fade" id="infoOs" tabindex="-1" role="dialog" aria-labelledby="TituloModalLongoExemplo" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="width: 700px;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="TituloModalLongoExemplo">Informações do Protocolo</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <?php foreach ($detalhe as $detalhe): ?>
                        <div class="modal-body">
                            <form>
                                <div class="alert alert-primary" role="alert">
                                    <strong>DADOS DO PROTOCOLO</strong>
                                </div>
                                <div class="form-group row">
                                    <label for="staticProto" class="col-sm-2 col-form-label"><strong>Protocolo: </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?php echo $detalhe->os ?>" type="text" readonly class="form-control-plaintext" id="staticProto" name="staticProto">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticCateg" class="col-sm-2 col-form-label"><strong>Categoria: </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->categoria ?>" type="text" readonly class="form-control-plaintext" id="staticCateg" name="staticCateg">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticStatus" class="col-sm-2 col-form-label"><strong>Status:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->sit ?>" type="text" readonly class="form-control-plaintext" id="staticStatus" name="staticStatus">
                                    </div>
                                </div>                                                                                         

                                <div class="alert alert-primary" role="alert">
                                    <strong>DADOS DA ABERTURA</strong>
                                </div>                                
                                <div class="form-group row">
                                    <label for="staticDtAbert" class="col-sm-3 col-form-label"><strong>Data de Abertura:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->data_hora_abert ?>" type="text" readonly class="form-control-plaintext" id="staticDtAbert" name="staticDtAbert">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticFuncAbert" class="col-sm-3 col-form-label"><strong>Aberto por:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->func_abert ?>" type="text" readonly class="form-control-plaintext" id="staticFuncAbert" name="staticFuncAbert">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticHist" class="col-sm-3 col-form-label"><strong>Solicitação:  </strong> </label>
                                    <div class="col-sm-10">                                        
                                        <textarea class="form-control" id="staticHist" name="staticHist" readonly="true" rows="3"><?= $detalhe->historico ?></textarea>
                                    </div>
                                </div> 

                                <div class="alert alert-primary" role="alert">
                                    <strong>DADOS DA RESOLUÇÃO</strong>
                                </div> 

                                <div class="form-group row">
                                    <label for="staticStRespon" class="col-sm-3 col-form-label"><strong>Setor Responsável:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->setor_responsavel ?>" type="text" readonly class="form-control-plaintext" id="staticStRespon" name="staticStRespon">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticFuncRespon" class="col-sm-3 col-form-label"><strong>Colaborador Responsável:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->func_respon ?>" type="text" readonly class="form-control-plaintext" id="staticFuncRespon" name="staticFuncRespon">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticFuncResolu" class="col-sm-3 col-form-label"><strong>Resolvido por:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->func_finali ?>" type="text" readonly class="form-control-plaintext" id="staticFuncResolu" name="staticFuncResolu">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticDtResolu" class="col-sm-3 col-form-label"><strong>Data da Resolução:  </strong> </label>
                                    <div class="col-sm-10">
                                        <input value="<?= $detalhe->data_hora_fecha ?>" type="text" readonly class="form-control-plaintext" id="staticDtResolu" name="staticDtResolu">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="staticResol" class="col-sm-3 col-form-label"><strong>Resolução:  </strong> </label>
                                    <div class="col-sm-10">                                        
                                        <textarea class="form-control" id="staticResol" name="staticResol" readonly="true" rows="3"><?= $detalhe->desc_finalizacao ?></textarea>
                                    </div>
                                </div> 
                            </form>
                        </div>
                    <?php endforeach; ?>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>       

        <!--        <div class="modal fade" id="cadNotaModal" tabindex="-1" role="dialog" aria-labelledby="TituloModalLongoExemplo" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="TituloModalLongoExemplo"><strong>Cadastrar Nota</strong></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>                   
                            <div class="modal-body">
                                <form>                            
                                    <div class="form-group row">
                                        <label for="protoId" class="col-sm-2 col-form-label"><strong>Protocolo: </strong> </label>
                                        <div class="col-sm-10">
                                            <input id="protoId" name="protoId" value="< ?= $protocolo ?>" type="text" readonly class="form-control-plaintext">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="solNota" class="col-sm-3 col-form-label"><strong>Nota:  </strong> </label>
                                        <div class="col-sm-10">                                        
                                            <textarea name="solNota" id="solNota" class="form-control"  name="staticHist" rows="3" ></textarea>
                                        </div>
                                    </div> 
                                </form>
                            </div>                     
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success" data-dismiss="modal">Salvar</button>
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>  -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="../../js/bootstrap.min.js"></script>
    </body>
</html>
