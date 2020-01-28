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
        <title>SindmepaProtocol</title>

        <!--CSS BOOTSTRAP-->
        <link href="../../css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>

        <div class="card  w-75 p-3" style="margin-left: auto;margin-right: auto">
            <?php
            $cadOs = new CadOsFachada();

            $protocolo = (isset($_GET['protocolo'])) ? $_GET['protocolo'] : '';

            if (empty($protocolo)):

                echo '<div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>SindmepaProtocol informa: </strong> informe o número do protocolo.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>';

            else:
                $consulta = $cadOs->pesquisar($protocolo);
            endif;
            ?>

            <div class="card-header">
                Featured
            </div>
            <br/>
            <form action="" method="get">
                <div class="col-sm-4">
                    <label for="protocolo">Protocolo:</label>
                    <input name="protocolo" id="protocolo" type="text" class="form-control"  placeholder="Nr. do Protocolo"> 
                    <br/> 
                    <button type="submit" class="btn btn-success">Pesquisar</button> 
                    <button type="button" class="btn btn-success">Novo Protocolo</button>
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
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($consulta as $consulta): ?>
                            <tr>
                                <td><?= $consulta->os ?></td>                                        
                                <td><?= $consulta->categoria ?></td>                           
                                <td><?= $consulta->data_hora_abert ?></td>                           
                                <td><?= $consulta->sit ?></td>        
                            </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            <?php else: ?>
                <h1 class="text-center text-primary">Sem registros.</h1>
            <?php endif; ?>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="../../js/bootstrap.min.js"></script>

    </body>
</html>
