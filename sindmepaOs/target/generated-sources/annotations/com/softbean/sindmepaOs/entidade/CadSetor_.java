package com.softbean.sindmepaOs.entidade;

import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.entidade.CadOs;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-28T23:29:53")
@StaticMetamodel(CadSetor.class)
public class CadSetor_ { 

    public static volatile ListAttribute<CadSetor, CadFuncionario> cadFuncionarioList;
    public static volatile ListAttribute<CadSetor, CadOs> cadOsList;
    public static volatile SingularAttribute<CadSetor, String> nmSetor;
    public static volatile SingularAttribute<CadSetor, Integer> cdSetor;
    public static volatile ListAttribute<CadSetor, CadOs> cadOsList1;
    public static volatile SingularAttribute<CadSetor, String> sitSetor;

}