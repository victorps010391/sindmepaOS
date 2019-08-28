/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "cad_anexos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadAnexos.findAll", query = "SELECT c FROM CadAnexos c")
    , @NamedQuery(name = "CadAnexos.findByIdAnexo", query = "SELECT c FROM CadAnexos c WHERE c.cadAnexosPK.idAnexo = :idAnexo")
    , @NamedQuery(name = "CadAnexos.findBySeqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.cadAnexosPK.seqAnexo = :seqAnexo")
    , @NamedQuery(name = "CadAnexos.findByExtArqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.extArqAnexo = :extArqAnexo")
    , @NamedQuery(name = "CadAnexos.findByNmArqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.nmArqAnexo = :nmArqAnexo")})
public class CadAnexos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CadAnexosPK cadAnexosPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "blob_arq_anexo")
    private byte[] blobArqAnexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ext_arq_anexo")
    private String extArqAnexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nm_arq_anexo")
    private String nmArqAnexo;

    public CadAnexos() {
    }

    public CadAnexos(CadAnexosPK cadAnexosPK) {
        this.cadAnexosPK = cadAnexosPK;
    }

    public CadAnexos(CadAnexosPK cadAnexosPK, byte[] blobArqAnexo, String extArqAnexo, String nmArqAnexo) {
        this.cadAnexosPK = cadAnexosPK;
        this.blobArqAnexo = blobArqAnexo;
        this.extArqAnexo = extArqAnexo;
        this.nmArqAnexo = nmArqAnexo;
    }

    public CadAnexos(int idAnexo, int seqAnexo) {
        this.cadAnexosPK = new CadAnexosPK(idAnexo, seqAnexo);
    }

    public CadAnexosPK getCadAnexosPK() {
        return cadAnexosPK;
    }

    public void setCadAnexosPK(CadAnexosPK cadAnexosPK) {
        this.cadAnexosPK = cadAnexosPK;
    }

    public byte[] getBlobArqAnexo() {
        return blobArqAnexo;
    }

    public void setBlobArqAnexo(byte[] blobArqAnexo) {
        this.blobArqAnexo = blobArqAnexo;
    }

    public String getExtArqAnexo() {
        return extArqAnexo;
    }

    public void setExtArqAnexo(String extArqAnexo) {
        this.extArqAnexo = extArqAnexo;
    }

    public String getNmArqAnexo() {
        return nmArqAnexo;
    }

    public void setNmArqAnexo(String nmArqAnexo) {
        this.nmArqAnexo = nmArqAnexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadAnexosPK != null ? cadAnexosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadAnexos)) {
            return false;
        }
        CadAnexos other = (CadAnexos) object;
        if ((this.cadAnexosPK == null && other.cadAnexosPK != null) || (this.cadAnexosPK != null && !this.cadAnexosPK.equals(other.cadAnexosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadAnexos[ cadAnexosPK=" + cadAnexosPK + " ]";
    }
    
}
