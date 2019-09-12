select distinct cod_valor_detalhe as codigo
                ,upper(desc_detalhe) as nome      
from cad_categoria,cad_detalhe,cad_os 
where cod_prior_categoria = cod_valor_detalhe 
and categ_os = id_categoria 
and cod_item_detalhe = 'PRIOR'




select distinct sit_os as codigo
                ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as nome  
from cad_os where sit_os not in ('01','06') 




select * from cad_detalhe where cod_item_detalhe = 'SITOS'