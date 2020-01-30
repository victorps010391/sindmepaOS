function cadastrarcategoria() {
    var categoria = document.getElementById("categoria").value;

    if (categoria === '0') {
        document.getElementById("cadastrarcategoria").style.display = "block";
		document.getElementById("outra").style.display = "none";
    } else {
        document.getElementById("cadastrarcategoria").style.display = "none";
		document.getElementById("outra").style.display = "block";
    }
}

function habilitaCampos(par) {
    div = document.getElementById('televisao');
    divRadio = document.getElementById('radio');
    divWeb = document.getElementById('web');
    divImpresso = document.getElementById('impresso');

    if (par === 'televisao') {
        div.style.display = 'block';
        divRadio.style.display = 'none';
        divWeb.style.display = 'none';
        divImpresso.style.display = 'none';

    } else if (par === 'radio') {
        div.style.display = 'none';
        divRadio.style.display = 'block';
        divWeb.style.display = 'none';
        divImpresso.style.display = 'none';

    } else if (par === 'web') {
        div.style.display = 'none';
        divRadio.style.display = 'none';
        divWeb.style.display = 'block';
        divImpresso.style.display = 'none';

    } else if (par === 'impresso') {
        div.style.display = 'none';
        divRadio.style.display = 'none';
        divWeb.style.display = 'none';
        divImpresso.style.display = 'block';

    }
}

function ValidateSize(file) {
    var FileSize = file.files[0].size / 1024 / 1024; // in MB
    if (FileSize > 2) {
        alert('Arquivo maior que o permitido: 2 MB');
        document.getElementById('upload').value = '';
        // $(file).val(''); //for clearing with Jquery
    } else {

    }
}

function verificaExtensao($input) {
    var extPermitidas = ['pdf'];
    var extArquivo = $input.value.split('.').pop();

    if (typeof extPermitidas.find(function (ext) {
        return extArquivo == ext;
    }) == 'undefined') {
        alert('Extensão "' + extArquivo + '" não permitida!');
        document.getElementById('upload').value = '';
        $('input').val("");
    } else {
        //alert('Extensão aceita!');
    }
}

function verifica(value) {
    var input = document.getElementById("input");
    var varImpresso = document.getElementById("impresso");
    var varTelevisao = document.getElementById("televisao");
    var varRadio = document.getElementById("radio");
    var varWeb = document.getElementById("web");
    var varConteudo = document.getElementById("conteudo");

    if (value === 'impresso') {
        varImpresso.disabled = false;
        varTelevisao.disabled = true;
        varRadio.disabled = true;
        varWeb.disabled = true;
        varConteudo.disabled = true;
    } else if (value === 'televisao') {
        varImpresso.disabled = true;
        varTelevisao.disabled = false;
        varRadio.disabled = true;
        varWeb.disabled = true;
        varConteudo.disabled = true;
    } else if (value === 'radio') {
        varImpresso.disabled = true;
        varTelevisao.disabled = true;
        varRadio.disabled = false;
        varWeb.disabled = true;
        varConteudo.disabled = true;
    } else if (value === 'web') {
        varImpresso.disabled = true;
        varTelevisao.disabled = true;
        varRadio.disabled = true;
        varWeb.disabled = false;
        varConteudo.disabled = true;
    } else if (value === 'conteudo') {
        varImpresso.disabled = true;
        varTelevisao.disabled = true;
        varRadio.disabled = true;
        varWeb.disabled = true;
        varConteudo.disabled = false;
    } else if (value === 1) {
        varImpresso.disabled = true;
        varTelevisao.disabled = true;
        varRadio.disabled = true;
        varWeb.disabled = true;
        varConteudo.disabled = true;
    }
}
;



function showDiv(element) {
    hideAllDiv();
    $(element).show();
}

function hideAllDiv() {
    $('.divs').hide();
}