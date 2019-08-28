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
 * @author Victor
 */
@Embeddable
public class CadOsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_os")
    private int idOs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nr_os")
    private int nrOs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "categ_os")
    private String categOs;

    public CadOsPK() {
    }

    public CadOsPK(int idOs, int nrOs, String categOs) {
        this.idOs = idOs;
        this.nrOs = nrOs;
        this.categOs = categOs;
    }

    public int getIdOs() {
        return idOs;
    }

    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }

    public int getNrOs() {
        return nrOs;
    }

    public void setNrOs(int nrOs) {
        this.nrOs = nrOs;
    }

    public String getCategOs() {
        return categOs;
    }

    public void setCategOs(String categOs) {
        this.categOs = categOs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOs;
        hash += (int) nrOs;
        hash += (categOs != null ? categOs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadOsPK)) {
            return false;
        }
        CadOsPK other = (CadOsPK) object;
        if (this.idOs != other.idOs) {
            return false;
        }
        if (this.nrOs != other.nrOs) {
            return false;
        }
        if ((this.categOs == null && other.categOs != null) || (this.categOs != null && !this.categOs.equals(other.categOs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadOsPK[ idOs=" + idOs + ", nrOs=" + nrOs + ", categOs=" + categOs + " ]";
    }
    
}
