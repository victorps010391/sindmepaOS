<?php
spl_autoload_register(function($classe) {
    include '../fachada/' . $classe . '.php';
});
?>
<?php
$cadOs = new CadOsFachada();
$cadCategoria = new CadCartegoriaFachada();

?>

<div class="col-sm-4">
    <label for="ex2">Selecione a categoria desejada: </label>
    <select class="form-control" name="categoria" id="categoria" onchange="cadastrarcategoria()">
        <option></option>
        <?php foreach ($cadCategoria->listaCategoria() as $key => $value) { ?>
            <option value="<?php echo $value->id_categoria; ?>">
                <?php echo $value->desc_categoria; ?>
            </option>
        <?php } ?> 
    </select>
    <?php echo 'Categoria: ' . $value->id_categoria;?>
</div>