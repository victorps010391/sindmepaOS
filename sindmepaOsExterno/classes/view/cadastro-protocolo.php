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
        <title>SindmepaProtocol - Gerenciamento de Protocolos</title>

        <!--CSS BOOTSTRAP-->
        <link href="../../css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        <div class="card  w-75 p-3" style="margin-left: auto;margin-right: auto">

            <?php
            $cadOs = new CadOsFachada();
            $cadCategoria = new CadCartegoriaFachada();
            ?>            
            <div class="card-header"><h4>Cadastro de Protocolo</h4></div>
            <br/>
            <div class="form-group row">
                <div class="col-sm-4">
                    <label for="ex2">Selecione a categoria desejada: </label>
                    <select class="form-control" name="categoria" id="categoria" onchange="cadastrarProtocolo()()">
                        <option value="SELECIONE"></option>
                        <?php foreach ($cadCategoria->listaCategoria() as $key => $value) { ?>
                            <option value="<?php echo $value->id_categoria; ?>">                                    
                                <?php echo $value->desc_categoria; ?>
                            </option>
                        <?php } ?> 
                    </select>
                </div>
            </div>    
            <br/>
            <div id="cadastrarcategoria" style="display: none;">
                <?php include './newPHPClass.php';?>
            </div>     

            <div id="outra" style="display: none;">
                <?php include './protocolo.php';?>
            </div> 
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/custom-campos.js"></script>
    <script>
    function cadastrarProtocolo() {
    var categoria = document.getElementById("categoria").value;

    if (categoria === '0') {
        document.getElementById("cadastrarcategoria").style.display = "block";
        document.getElementById("outra").style.display = "none";
    } else {
        document.getElementById("cadastrarcategoria").style.display = "none";
        document.getElementById("outra").style.display = "block";
    }
}

    </script>
</body>
</html>