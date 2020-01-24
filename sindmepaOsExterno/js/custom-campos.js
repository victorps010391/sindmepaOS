function habilitaCampos(par){
      div = document.getElementById('televisao');
	  divRadio = document.getElementById('radio');
	  divWeb = document.getElementById('web');
	  divImpresso = document.getElementById('impresso');
	  divConteudo = document.getElementById('conteudo');
	  
     if(par == 'televisao'){
            div.style.display = 'block';
			divRadio.style.display = 'none';  
			divWeb.style.display = 'none';  
			divImpresso.style.display = 'none';  
			divConteudo.style.display = 'none';  
     } else if(par == 'radio')     {
            div.style.display = 'none';
			divRadio.style.display = 'block';  
			divWeb.style.display = 'none';  
			divImpresso.style.display = 'none';  
			divConteudo.style.display = 'none';                              
     } else if(par == 'web')    {
            div.style.display = 'none';
			divRadio.style.display = 'none';  
			divWeb.style.display = 'block';  
			divImpresso.style.display = 'none';  
			divConteudo.style.display = 'none';    
	} else if(par == 'impresso')    {
            div.style.display = 'none';
			divRadio.style.display = 'none';  
			divWeb.style.display = 'none';  
			divImpresso.style.display = 'block';  
			divConteudo.style.display = 'none';    
	} else if(par == 'conteudo')    {
            div.style.display = 'none';
			divRadio.style.display = 'none';  
			divWeb.style.display = 'none';  
			divImpresso.style.display = 'none';  
			divConteudo.style.display = 'block';
	}
}                
