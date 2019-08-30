package com.softbean.sindmepaOs.entidade;

import com.softbean.sindmepaOs.entidade.CadFuncionarioPK;
import com.softbean.sindmepaOs.entidade.CadSetor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-30T13:58:10")
@StaticMetamodel(CadFuncionario.class)
public class CadFuncionario_ { 

    public static volatile SingularAttribute<CadFuncionario, CadFuncionarioPK> cadFuncionarioPK;
    public static volatile SingularAttribute<CadFuncionario, String> nmFunc;
    public static volatile SingularAttribute<CadFuncionario, CadSetor> setorFunc;
    public static volatile SingularAttribute<CadFuncionario, Date> dtNascFunc;

}