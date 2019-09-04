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

/**
 *
 * @author Victor
 */
@Embeddable
public class CadNotaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nr_os_nota")
    private int nrOsNota;
    @Basic(optional = false)
    @Column(name = "serial_nota")
    private int serialNota;

    public CadNotaPK() {
    }

    public CadNotaPK(int nrOsNota, int serialNota) {
        this.nrOsNota = nrOsNota;
        this.serialNota = serialNota;
    }

    public int getNrOsNota() {
        return nrOsNota;
    }

    public void setNrOsNota(int nrOsNota) {
        this.nrOsNota = nrOsNota;
    }

    public int getSerialNota() {
        return serialNota;
    }

    public void setSerialNota(int serialNota) {
        this.serialNota = serialNota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nrOsNota;
        hash += (int) serialNota;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadNotaPK)) {
            return false;
        }
        CadNotaPK other = (CadNotaPK) object;
        if (this.nrOsNota != other.nrOsNota) {
            return false;
        }
        if (this.serialNota != other.serialNota) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadNotaPK[ nrOsNota=" + nrOsNota + ", serialNota=" + serialNota + " ]";
    }
    
}
