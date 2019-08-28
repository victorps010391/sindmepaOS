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
public class CadAnexosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_anexo")
    private int idAnexo;
    @Basic(optional = false)
    @Column(name = "seq_anexo")
    private int seqAnexo;

    public CadAnexosPK() {
    }

    public CadAnexosPK(int idAnexo, int seqAnexo) {
        this.idAnexo = idAnexo;
        this.seqAnexo = seqAnexo;
    }

    public int getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(int idAnexo) {
        this.idAnexo = idAnexo;
    }

    public int getSeqAnexo() {
        return seqAnexo;
    }

    public void setSeqAnexo(int seqAnexo) {
        this.seqAnexo = seqAnexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idAnexo;
        hash += (int) seqAnexo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadAnexosPK)) {
            return false;
        }
        CadAnexosPK other = (CadAnexosPK) object;
        if (this.idAnexo != other.idAnexo) {
            return false;
        }
        if (this.seqAnexo != other.seqAnexo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadAnexosPK[ idAnexo=" + idAnexo + ", seqAnexo=" + seqAnexo + " ]";
    }
    
}
