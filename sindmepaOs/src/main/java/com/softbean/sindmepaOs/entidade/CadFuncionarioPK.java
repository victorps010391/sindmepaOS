/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Raphael
 */
@Embeddable
public class CadFuncionarioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "cpf_func")
    private String cpfFunc;
    @Basic(optional = false)
    @Column(name = "cd_func")
    private int cdFunc;

    public CadFuncionarioPK() {
    }

    public CadFuncionarioPK(String cpfFunc, int cdFunc) {
        this.cpfFunc = cpfFunc;
        this.cdFunc = cdFunc;
    }

    public String getCpfFunc() {
        return cpfFunc;
    }

    public void setCpfFunc(String cpfFunc) {
        this.cpfFunc = cpfFunc;
    }

    public int getCdFunc() {
        return cdFunc;
    }

    public void setCdFunc(int cdFunc) {
        this.cdFunc = cdFunc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpfFunc != null ? cpfFunc.hashCode() : 0);
        hash += (int) cdFunc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFuncionarioPK)) {
            return false;
        }
        CadFuncionarioPK other = (CadFuncionarioPK) object;
        if ((this.cpfFunc == null && other.cpfFunc != null) || (this.cpfFunc != null && !this.cpfFunc.equals(other.cpfFunc))) {
            return false;
        }
        if (this.cdFunc != other.cdFunc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadFuncionarioPK[ cpfFunc=" + cpfFunc + ", cdFunc=" + cdFunc + " ]";
    }
    
}
