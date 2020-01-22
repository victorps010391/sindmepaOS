<?php

function __autoload($classe) {
    require_once 'classes/fachada/' . $classe . '.php';
}
?>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>teste</title>

        <!--CSS BOOTSTRAP-->
        <link href="css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>

        <div style="margin: 100px 0; text-align: center">

            <?php
            $cadastro = new testeFachada();

            if (isset($_POST['cadastrar'])):

                $nome = $_POST['nome'];

                $cadastro->setNome($nome);

                if ($cadastro->insert()) {
                    echo ' <div class="alert alert-success alert-dismissible fade show" role="alert">
                                <strong>SindmepaProtocol informa: </strong> Cadastro Realizado com Sucesso.
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                           </div>';
                } else {
                    echo '<div class="alert alert-danger alert-dismissible fade show" role="alert">
                               <strong>SindmepaProtocol informa: </strong> Erro ao Cadastrar.
                               <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                               </button>
                          </div>';
                    
                }
            endif
            ?>

            <legend>Teste de Cadastro</legend>
            <form class="form-inline" method="post">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">@</span>
                    </div>
                    <input name="nome" type="text" class="form-control" placeholder="nome" aria-label="nome" aria-describedby="basic-addon1">
                    <input name="cadastrar" type="submit" class="btn btn-success" value="Cadastrar">
                </div>                
            </form>

        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </body>
</html>
