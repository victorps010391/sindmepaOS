<?php
	spl_autoload_register(function($classe){
		include '../fachada/' . $classe . '.php';
	});
        
//        require_once ('../fachada/CadExternoFachada.php');
//        require_once ('../fachada/EnderecoFachada.php');
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
	.panel-default>.panel-heading {
    color: #333;
    background-color: #2ecc71;
	}
  </style>
</head>
<body>
 
<div class="container">
  <h3>Associe-se</h2>
  <div class="panel panel-default">
    <div class="panel-heading"><h4>Cadastro de Associado</h4></div>
    <?php
        $endereco = new EnderecoFachada();
        $cadExterno = new CadExternoFachada();
		
		if(isset($_POST['cadastrar'])){
                    
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
                        $cadExterno->setCdTipPag('13');       
                        $cadExterno->setIdEnd($endereco->getEnderecoId());
                        
                        $cadExterno->insertCadExterno();        
                        
		}
    ?>
    <form method="POST">
    
		<div class="panel-body">
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
                    <button type="submit" class="btn btn-default" name="cadastrar">Cadastrar</button>
		</div><!-- fim panel-body-->
  </form><!-- fim form -->
  </div> <!-- fim panel panel-default -->
</div>

</body>
</html>
