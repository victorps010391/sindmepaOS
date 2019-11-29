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
 * @author admin
 */
@Embeddable
public class CadAnexosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_os_anexo")
    private int codOsAnexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seq_anexo")
    private int seqAnexo;

    public CadAnexosPK() {
    }

    public CadAnexosPK(int codOsAnexo, int seqAnexo) {
        this.codOsAnexo = codOsAnexo;
        this.seqAnexo = seqAnexo;
    }

    public int getCodOsAnexo() {
        return codOsAnexo;
    }

    public void setCodOsAnexo(int codOsAnexo) {
        this.codOsAnexo = codOsAnexo;
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
        hash += (int) codOsAnexo;
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
        if (this.codOsAnexo != other.codOsAnexo) {
            return false;
        }
        if (this.seqAnexo != other.seqAnexo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadAnexosPK[ codOsAnexo=" + codOsAnexo + ", seqAnexo=" + seqAnexo + " ]";
    }
    
}
