package com.softbean.sindmepaOs.entidade;

import com.softbean.sindmepaOs.entidade.CadOsPK;
import com.softbean.sindmepaOs.entidade.CadSetor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-28T23:29:53")
@StaticMetamodel(CadOs.class)
public class CadOs_ { 

    public static volatile SingularAttribute<CadOs, CadOsPK> cadOsPK;
    public static volatile SingularAttribute<CadOs, Date> dtAbertOs;
    public static volatile SingularAttribute<CadOs, Integer> funcResponOs;
    public static volatile SingularAttribute<CadOs, String> histOs;
    public static volatile SingularAttribute<CadOs, Date> dtFechaOs;
    public static volatile SingularAttribute<CadOs, String> sitOs;
    public static volatile SingularAttribute<CadOs, CadSetor> setorOs;
    public static volatile SingularAttribute<CadOs, Integer> funcAbertOs;
    public static volatile SingularAttribute<CadOs, Integer> funcUltAtuOs;
    public static volatile SingularAttribute<CadOs, Date> dtUltAtuOs;
    public static volatile SingularAttribute<CadOs, String> obsOs;
    public static volatile SingularAttribute<CadOs, CadSetor> setorResponOs;

}