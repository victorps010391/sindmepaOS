/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raphael
 */
@Entity
@Table(name = "cad_funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadFuncionario.findAll", query = "SELECT c FROM CadFuncionario c"),
    @NamedQuery(name = "CadFuncionario.findByCpfFunc", query = "SELECT c FROM CadFuncionario c WHERE c.cadFuncionarioPK.cpfFunc = :cpfFunc"),
    @NamedQuery(name = "CadFuncionario.findByCdFunc", query = "SELECT c FROM CadFuncionario c WHERE c.cadFuncionarioPK.cdFunc = :cdFunc"),
    @NamedQuery(name = "CadFuncionario.findByNmFunc", query = "SELECT c FROM CadFuncionario c WHERE c.nmFunc = :nmFunc"),
    @NamedQuery(name = "CadFuncionario.findByDtNascFunc", query = "SELECT c FROM CadFuncionario c WHERE c.dtNascFunc = :dtNascFunc")})
public class CadFuncionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CadFuncionarioPK cadFuncionarioPK;
    @Size(max = 100)
    @Column(name = "nm_func")
    private String nmFunc;
    @Column(name = "dt_nasc_func")
    @Temporal(TemporalType.DATE)
    private Date dtNascFunc;

    public CadFuncionario() {
    }

    public CadFuncionario(CadFuncionarioPK cadFuncionarioPK) {
        this.cadFuncionarioPK = cadFuncionarioPK;
    }

    public CadFuncionario(String cpfFunc, int cdFunc) {
        this.cadFuncionarioPK = new CadFuncionarioPK(cpfFunc, cdFunc);
    }

    public CadFuncionarioPK getCadFuncionarioPK() {
        return cadFuncionarioPK;
    }

    public void setCadFuncionarioPK(CadFuncionarioPK cadFuncionarioPK) {
        this.cadFuncionarioPK = cadFuncionarioPK;
    }

    public String getNmFunc() {
        return nmFunc;
    }

    public void setNmFunc(String nmFunc) {
        this.nmFunc = nmFunc;
    }

    public Date getDtNascFunc() {
        return dtNascFunc;
    }

    public void setDtNascFunc(Date dtNascFunc) {
        this.dtNascFunc = dtNascFunc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadFuncionarioPK != null ? cadFuncionarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFuncionario)) {
            return false;
        }
        CadFuncionario other = (CadFuncionario) object;
        if ((this.cadFuncionarioPK == null && other.cadFuncionarioPK != null) || (this.cadFuncionarioPK != null && !this.cadFuncionarioPK.equals(other.cadFuncionarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadFuncionario[ cadFuncionarioPK=" + cadFuncionarioPK + " ]";
    }
    
}
