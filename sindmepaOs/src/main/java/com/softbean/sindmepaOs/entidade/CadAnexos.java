/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "cad_anexos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadAnexos.findAll", query = "SELECT c FROM CadAnexos c"),
    @NamedQuery(name = "CadAnexos.findByCodOsAnexo", query = "SELECT c FROM CadAnexos c WHERE c.cadAnexosPK.codOsAnexo = :codOsAnexo"),
    @NamedQuery(name = "CadAnexos.findBySeqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.cadAnexosPK.seqAnexo = :seqAnexo"),
    @NamedQuery(name = "CadAnexos.findByExtArqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.extArqAnexo = :extArqAnexo"),
    @NamedQuery(name = "CadAnexos.findByNmArqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.nmArqAnexo = :nmArqAnexo"),
    @NamedQuery(name = "CadAnexos.findByPagArqAnexo", query = "SELECT c FROM CadAnexos c WHERE c.pagArqAnexo = :pagArqAnexo"),
    @NamedQuery(name = "CadAnexos.findByDtRegiAnexo", query = "SELECT c FROM CadAnexos c WHERE c.dtRegiAnexo = :dtRegiAnexo"),
    @NamedQuery(name = "CadAnexos.findByFuncRegiAnexo", query = "SELECT c FROM CadAnexos c WHERE c.funcRegiAnexo = :funcRegiAnexo"),
    @NamedQuery(name = "CadAnexos.findByDtUltAtuAnexo", query = "SELECT c FROM CadAnexos c WHERE c.dtUltAtuAnexo = :dtUltAtuAnexo"),
    @NamedQuery(name = "CadAnexos.findByFuncUltAtuAnexo", query = "SELECT c FROM CadAnexos c WHERE c.funcUltAtuAnexo = :funcUltAtuAnexo")})
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
    @Size(max = 2147483647)
    @Column(name = "pag_arq_anexo")
    private String pagArqAnexo;
    @Column(name = "dt_regi_anexo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtRegiAnexo;
    @Column(name = "func_regi_anexo")
    private Integer funcRegiAnexo;
    @Column(name = "dt_ult_atu_anexo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAtuAnexo;
    @Column(name = "func_ult_atu_anexo")
    private Integer funcUltAtuAnexo;

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

    public CadAnexos(int codOsAnexo, int seqAnexo) {
        this.cadAnexosPK = new CadAnexosPK(codOsAnexo, seqAnexo);
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

    public String getPagArqAnexo() {
        return pagArqAnexo;
    }

    public void setPagArqAnexo(String pagArqAnexo) {
        this.pagArqAnexo = pagArqAnexo;
    }

    public Date getDtRegiAnexo() {
        return dtRegiAnexo;
    }

    public void setDtRegiAnexo(Date dtRegiAnexo) {
        this.dtRegiAnexo = dtRegiAnexo;
    }

    public Integer getFuncRegiAnexo() {
        return funcRegiAnexo;
    }

    public void setFuncRegiAnexo(Integer funcRegiAnexo) {
        this.funcRegiAnexo = funcRegiAnexo;
    }

    public Date getDtUltAtuAnexo() {
        return dtUltAtuAnexo;
    }

    public void setDtUltAtuAnexo(Date dtUltAtuAnexo) {
        this.dtUltAtuAnexo = dtUltAtuAnexo;
    }

    public Integer getFuncUltAtuAnexo() {
        return funcUltAtuAnexo;
    }

    public void setFuncUltAtuAnexo(Integer funcUltAtuAnexo) {
        this.funcUltAtuAnexo = funcUltAtuAnexo;
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
