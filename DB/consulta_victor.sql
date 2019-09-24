select nr_os as os 																											   
       ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                           
        and cod_valor_detalhe = (select cod_prior_categoria from cad_categoria where id_categoria = categ_os))) as prioridade  
       ,upper((select desc_categoria from cad_categoria where id_categoria = categ_os)) as categoria                           
       ,(select nm_setor from cad_setor where cd_setor = setor_respon_os) as setor                                             
       ,TO_CHAR(dt_abert_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_os, 'HH24:MI:SS') as data_hora_abert                         
       ,TO_CHAR(dt_fecha_os, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_os, 'HH24:MI:SS') as data_hora_fecha                         
       ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITOS' and cod_valor_detalhe = sit_os) as sit          
       ,sit_os as cd_sit 
       ,'PRT' as cd_origem                                                                                                      
from cad_os                                                                                                                    
where 1=1                                                                                                                      
and sit_os = '02'
union all
select cast(nr_os_tarefa as integer) as os
       ,upper((select desc_detalhe from cad_detalhe where cod_item_detalhe = 'PRIOR'                                           
        and cod_valor_detalhe = (select cod_prior_categoria 
        from cad_categoria where id_categoria = (select categ_os from cad_os where nr_os = cast(nr_os_tarefa as integer))))) as prioridade
       ,upper((select desc_categoria from cad_categoria 
        where id_categoria = (select categ_os from cad_os where nr_os = cast(nr_os_tarefa as integer)))) as categoria
       ,(select nm_setor from cad_setor where cd_setor = setor_respon_tarefa) as setor
       ,TO_CHAR(dt_abert_tarefa, 'DD/MM/YYYY')||' '||TO_CHAR(dt_abert_tarefa, 'HH24:MI:SS') as data_hora_abert
       ,TO_CHAR(dt_fecha_tarefa, 'DD/MM/YYYY')||' '||TO_CHAR(dt_fecha_tarefa, 'HH24:MI:SS') as data_hora_fecha
       ,(select desc_detalhe from cad_detalhe where cod_item_detalhe = 'SITTA' and cod_valor_detalhe = sit_tarefa) as sit
       ,sit_tarefa as cd_sit
       ,'TAR' as cd_origem
from cad_tarefa
where 1=1
and sit_tarefa = '01' 





