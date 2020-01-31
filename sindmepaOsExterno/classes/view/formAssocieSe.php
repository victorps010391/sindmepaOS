
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

    </div> <!-- fim card-default -->
