package com.softbean.sindmepaOs.entidade;

import com.softbean.sindmepaOs.entidade.CadTarefaPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-28T23:29:53")
@StaticMetamodel(CadTarefa.class)
public class CadTarefa_ { 

    public static volatile SingularAttribute<CadTarefa, Integer> setorAbertTarefa;
    public static volatile SingularAttribute<CadTarefa, Integer> setorResponTarefa;
    public static volatile SingularAttribute<CadTarefa, String> obsTarefa;
    public static volatile SingularAttribute<CadTarefa, Date> dtFechaTarefa;
    public static volatile SingularAttribute<CadTarefa, Date> dtAbertTarefa;
    public static volatile SingularAttribute<CadTarefa, String> histTarefa;
    public static volatile SingularAttribute<CadTarefa, CadTarefaPK> cadTarefaPK;
    public static volatile SingularAttribute<CadTarefa, Integer> funcAbertTarefa;
    public static volatile SingularAttribute<CadTarefa, Integer> funcResponTarefa;

}