/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "cad_setor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadSetor.findAll", query = "SELECT c FROM CadSetor c"),
    @NamedQuery(name = "CadSetor.findByCdSetor", query = "SELECT c FROM CadSetor c WHERE c.cdSetor = :cdSetor"),
    @NamedQuery(name = "CadSetor.findByNmSetor", query = "SELECT c FROM CadSetor c WHERE c.nmSetor = :nmSetor"),
    @NamedQuery(name = "CadSetor.findBySitSetor", query = "SELECT c FROM CadSetor c WHERE c.sitSetor = :sitSetor")})
public class CadSetor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_setor")
    private Integer cdSetor;
    @Size(max = 50)
    @Column(name = "nm_setor")
    private String nmSetor;
    @Size(max = 2)
    @Column(name = "sit_setor")
    private String sitSetor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "setorResponOs")
    private List<CadOs> cadOsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "setorAbertOs")
    private List<CadOs> cadOsList1;

    public CadSetor() {
    }

    public CadSetor(Integer cdSetor) {
        this.cdSetor = cdSetor;
    }

    public Integer getCdSetor() {
        return cdSetor;
    }

    public void setCdSetor(Integer cdSetor) {
        this.cdSetor = cdSetor;
    }

    public String getNmSetor() {
        return nmSetor;
    }

    public void setNmSetor(String nmSetor) {
        this.nmSetor = nmSetor;
    }

    public String getSitSetor() {
        return sitSetor;
    }

    public void setSitSetor(String sitSetor) {
        this.sitSetor = sitSetor;
    }

    @XmlTransient
    public List<CadOs> getCadOsList() {
        return cadOsList;
    }

    public void setCadOsList(List<CadOs> cadOsList) {
        this.cadOsList = cadOsList;
    }

    @XmlTransient
    public List<CadOs> getCadOsList1() {
        return cadOsList1;
    }

    public void setCadOsList1(List<CadOs> cadOsList1) {
        this.cadOsList1 = cadOsList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdSetor != null ? cdSetor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadSetor)) {
            return false;
        }
        CadSetor other = (CadSetor) object;
        if ((this.cdSetor == null && other.cdSetor != null) || (this.cdSetor != null && !this.cdSetor.equals(other.cdSetor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadSetor[ cdSetor=" + cdSetor + " ]";
    }
    
}
