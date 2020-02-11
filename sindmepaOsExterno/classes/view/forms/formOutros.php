<div class="card-header"><h4>Informações do Protocolo</h4></div>
<div class="form-group row">
    <div class="card-body">
        <div class="form-group row">
            <div class="col-sm-3">
                <label for="sel1">Setor responsável: </label>
                <select class="form-control" id="sel1" name="setor">
                    <option></option>
                    <?php foreach ($cadSetor->listaSetor() as $key => $value) { ?>
                        <option value="<?php echo $value->cd_setor; ?>"><?php echo $value->nm_setor; ?></option>
                    <?php } ?> 
                </select>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-4">
                <label for="cpfOutros">CPF</label>
                <input class="form-control" id="cpfOutros" type="text" name="cpfOutros">
            </div> 
        </div> 
        <div class="form-group row">
            <div class="col-sm-6">
                <label for="ex1">Solicitação:</label>
                <textarea class="form-control" id="ex1" type="text" name="historico"></textarea>
            </div>
            <div class="col-sm-6">
                <label for="ex2">Observação:</label>
                <textarea class="form-control" id="ex2" type="text" name="observacao"></textarea>
            </div>
        </div>
        <button type="submit" class="btn btn-success" name="enviar">Enviar</button>
    </div><!-- fim card-body-->

</div> <!-- fim card-default -->