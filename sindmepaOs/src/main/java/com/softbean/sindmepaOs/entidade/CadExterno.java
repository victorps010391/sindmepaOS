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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cad_externo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadExterno.findAll", query = "SELECT c FROM CadExterno c"),
    @NamedQuery(name = "CadExterno.findByIdExt", query = "SELECT c FROM CadExterno c WHERE c.idExt = :idExt"),
    @NamedQuery(name = "CadExterno.findByNmExt", query = "SELECT c FROM CadExterno c WHERE c.nmExt = :nmExt"),
    @NamedQuery(name = "CadExterno.findByRgExt", query = "SELECT c FROM CadExterno c WHERE c.rgExt = :rgExt"),
    @NamedQuery(name = "CadExterno.findByCpfExt", query = "SELECT c FROM CadExterno c WHERE c.cpfExt = :cpfExt"),
    @NamedQuery(name = "CadExterno.findBySexoExt", query = "SELECT c FROM CadExterno c WHERE c.sexoExt = :sexoExt"),
    @NamedQuery(name = "CadExterno.findByDtNascExt", query = "SELECT c FROM CadExterno c WHERE c.dtNascExt = :dtNascExt"),
    @NamedQuery(name = "CadExterno.findByEmailExt", query = "SELECT c FROM CadExterno c WHERE c.emailExt = :emailExt"),
    @NamedQuery(name = "CadExterno.findByNumCrm", query = "SELECT c FROM CadExterno c WHERE c.numCrm = :numCrm"),
    @NamedQuery(name = "CadExterno.findByEspExt", query = "SELECT c FROM CadExterno c WHERE c.espExt = :espExt")})
public class CadExterno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ext")
    private Integer idExt;
    @Size(max = 512)
    @Column(name = "nm_ext")
    private String nmExt;
    @Size(max = 11)
    @Column(name = "rg_ext")
    private String rgExt;
    @Size(max = 11)
    @Column(name = "cpf_ext")
    private String cpfExt;
    @Column(name = "sexo_ext")
    private Character sexoExt;
    @Column(name = "dt_nasc_ext")
    @Temporal(TemporalType.DATE)
    private Date dtNascExt;
    @Size(max = 512)
    @Column(name = "email_ext")
    private String emailExt;
    @Size(max = 11)
    @Column(name = "num_crm")
    private String numCrm;
    @Size(max = 512)
    @Column(name = "esp_ext")
    private String espExt;
    @JoinColumn(name = "id_end", referencedColumnName = "id_end")
    @ManyToOne
    private Endereco idEnd;

    public CadExterno() {
    }

    public CadExterno(Integer idExt) {
        this.idExt = idExt;
    }

    public Integer getIdExt() {
        return idExt;
    }

    public void setIdExt(Integer idExt) {
        this.idExt = idExt;
    }

    public String getNmExt() {
        return nmExt;
    }

    public void setNmExt(String nmExt) {
        this.nmExt = nmExt;
    }

    public String getRgExt() {
        return rgExt;
    }

    public void setRgExt(String rgExt) {
        this.rgExt = rgExt;
    }

    public String getCpfExt() {
        return cpfExt;
    }

    public void setCpfExt(String cpfExt) {
        this.cpfExt = cpfExt;
    }

    public Character getSexoExt() {
        return sexoExt;
    }

    public void setSexoExt(Character sexoExt) {
        this.sexoExt = sexoExt;
    }

    public Date getDtNascExt() {
        return dtNascExt;
    }

    public void setDtNascExt(Date dtNascExt) {
        this.dtNascExt = dtNascExt;
    }

    public String getEmailExt() {
        return emailExt;
    }

    public void setEmailExt(String emailExt) {
        this.emailExt = emailExt;
    }

    public String getNumCrm() {
        return numCrm;
    }

    public void setNumCrm(String numCrm) {
        this.numCrm = numCrm;
    }

    public String getEspExt() {
        return espExt;
    }

    public void setEspExt(String espExt) {
        this.espExt = espExt;
    }

    public Endereco getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(Endereco idEnd) {
        this.idEnd = idEnd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExt != null ? idExt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadExterno)) {
            return false;
        }
        CadExterno other = (CadExterno) object;
        if ((this.idExt == null && other.idExt != null) || (this.idExt != null && !this.idExt.equals(other.idExt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadExterno[ idExt=" + idExt + " ]";
    }
    
}
