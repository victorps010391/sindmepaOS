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