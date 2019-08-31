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
 * @author Raphael
 */
@Entity
@Table(name = "cad_academico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadAcademico.findAll", query = "SELECT c FROM CadAcademico c"),
    @NamedQuery(name = "CadAcademico.findByIdAcademico", query = "SELECT c FROM CadAcademico c WHERE c.idAcademico = :idAcademico"),
    @NamedQuery(name = "CadAcademico.findByNomeAcad", query = "SELECT c FROM CadAcademico c WHERE c.nomeAcad = :nomeAcad"),
    @NamedQuery(name = "CadAcademico.findByDtNascAcad", query = "SELECT c FROM CadAcademico c WHERE c.dtNascAcad = :dtNascAcad"),
    @NamedQuery(name = "CadAcademico.findByCepAcad", query = "SELECT c FROM CadAcademico c WHERE c.cepAcad = :cepAcad"),
    @NamedQuery(name = "CadAcademico.findBySexoAcad", query = "SELECT c FROM CadAcademico c WHERE c.sexoAcad = :sexoAcad"),
    @NamedQuery(name = "CadAcademico.findByEndAcad", query = "SELECT c FROM CadAcademico c WHERE c.endAcad = :endAcad"),
    @NamedQuery(name = "CadAcademico.findByNumAcad", query = "SELECT c FROM CadAcademico c WHERE c.numAcad = :numAcad"),
    @NamedQuery(name = "CadAcademico.findByCelAcad", query = "SELECT c FROM CadAcademico c WHERE c.celAcad = :celAcad"),
    @NamedQuery(name = "CadAcademico.findByCompAcad", query = "SELECT c FROM CadAcademico c WHERE c.compAcad = :compAcad"),
    @NamedQuery(name = "CadAcademico.findByTelComAcad", query = "SELECT c FROM CadAcademico c WHERE c.telComAcad = :telComAcad"),
    @NamedQuery(name = "CadAcademico.findByCidadeAcad", query = "SELECT c FROM CadAcademico c WHERE c.cidadeAcad = :cidadeAcad"),
    @NamedQuery(name = "CadAcademico.findByWhatsappAcad", query = "SELECT c FROM CadAcademico c WHERE c.whatsappAcad = :whatsappAcad"),
    @NamedQuery(name = "CadAcademico.findByBairroAcad", query = "SELECT c FROM CadAcademico c WHERE c.bairroAcad = :bairroAcad"),
    @NamedQuery(name = "CadAcademico.findByEmailAcad", query = "SELECT c FROM CadAcademico c WHERE c.emailAcad = :emailAcad"),
    @NamedQuery(name = "CadAcademico.findByRgAcad", query = "SELECT c FROM CadAcademico c WHERE c.rgAcad = :rgAcad"),
    @NamedQuery(name = "CadAcademico.findByUniFacAcad", query = "SELECT c FROM CadAcademico c WHERE c.uniFacAcad = :uniFacAcad"),
    @NamedQuery(name = "CadAcademico.findByCpfAcad", query = "SELECT c FROM CadAcademico c WHERE c.cpfAcad = :cpfAcad"),
    @NamedQuery(name = "CadAcademico.findByAnoIngAcad", query = "SELECT c FROM CadAcademico c WHERE c.anoIngAcad = :anoIngAcad"),
    @NamedQuery(name = "CadAcademico.findByAnoConcAcad", query = "SELECT c FROM CadAcademico c WHERE c.anoConcAcad = :anoConcAcad"),
    @NamedQuery(name = "CadAcademico.findByAnexoDecAcad", query = "SELECT c FROM CadAcademico c WHERE c.anexoDecAcad = :anexoDecAcad"),
    @NamedQuery(name = "CadAcademico.findByAnexoFtAcad", query = "SELECT c FROM CadAcademico c WHERE c.anexoFtAcad = :anexoFtAcad")})
public class CadAcademico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_academico")
    private Integer idAcademico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nome_acad")
    private String nomeAcad;
    @Column(name = "dt_nasc_acad")
    @Temporal(TemporalType.DATE)
    private Date dtNascAcad;
    @Size(max = 8)
    @Column(name = "cep_acad")
    private String cepAcad;
    @Column(name = "sexo_acad")
    private Character sexoAcad;
    @Size(max = 512)
    @Column(name = "end_acad")
    private String endAcad;
    @Column(name = "num_acad")
    private Integer numAcad;
    @Column(name = "cel_acad")
    private Integer celAcad;
    @Size(max = 512)
    @Column(name = "comp_acad")
    private String compAcad;
    @Size(max = 12)
    @Column(name = "tel_com_acad")
    private String telComAcad;
    @Size(max = 512)
    @Column(name = "cidade_acad")
    private String cidadeAcad;
    @Size(max = 12)
    @Column(name = "whatsapp_acad")
    private String whatsappAcad;
    @Size(max = 512)
    @Column(name = "bairro_acad")
    private String bairroAcad;
    @Size(max = 512)
    @Column(name = "email_acad")
    private String emailAcad;
    @Size(max = 512)
    @Column(name = "rg_acad")
    private String rgAcad;
    @Size(max = 512)
    @Column(name = "uni_fac_acad")
    private String uniFacAcad;
    @Size(max = 11)
    @Column(name = "cpf_acad")
    private String cpfAcad;
    @Column(name = "ano_ing_acad")
    @Temporal(TemporalType.DATE)
    private Date anoIngAcad;
    @Column(name = "ano_conc_acad")
    @Temporal(TemporalType.DATE)
    private Date anoConcAcad;
    @Size(max = 2147483647)
    @Column(name = "anexo_dec_acad")
    private String anexoDecAcad;
    @Size(max = 2147483647)
    @Column(name = "anexo_ft_acad")
    private String anexoFtAcad;

    public CadAcademico() {
    }

    public CadAcademico(Integer idAcademico) {
        this.idAcademico = idAcademico;
    }

    public CadAcademico(Integer idAcademico, String nomeAcad) {
        this.idAcademico = idAcademico;
        this.nomeAcad = nomeAcad;
    }

    public Integer getIdAcademico() {
        return idAcademico;
    }

    public void setIdAcademico(Integer idAcademico) {
        this.idAcademico = idAcademico;
    }

    public String getNomeAcad() {
        return nomeAcad;
    }

    public void setNomeAcad(String nomeAcad) {
        this.nomeAcad = nomeAcad;
    }

    public Date getDtNascAcad() {
        return dtNascAcad;
    }

    public void setDtNascAcad(Date dtNascAcad) {
        this.dtNascAcad = dtNascAcad;
    }

    public String getCepAcad() {
        return cepAcad;
    }

    public void setCepAcad(String cepAcad) {
        this.cepAcad = cepAcad;
    }

    public Character getSexoAcad() {
        return sexoAcad;
    }

    public void setSexoAcad(Character sexoAcad) {
        this.sexoAcad = sexoAcad;
    }

    public String getEndAcad() {
        return endAcad;
    }

    public void setEndAcad(String endAcad) {
        this.endAcad = endAcad;
    }

    public Integer getNumAcad() {
        return numAcad;
    }

    public void setNumAcad(Integer numAcad) {
        this.numAcad = numAcad;
    }

    public Integer getCelAcad() {
        return celAcad;
    }

    public void setCelAcad(Integer celAcad) {
        this.celAcad = celAcad;
    }

    public String getCompAcad() {
        return compAcad;
    }

    public void setCompAcad(String compAcad) {
        this.compAcad = compAcad;
    }

    public String getTelComAcad() {
        return telComAcad;
    }

    public void setTelComAcad(String telComAcad) {
        this.telComAcad = telComAcad;
    }

    public String getCidadeAcad() {
        return cidadeAcad;
    }

    public void setCidadeAcad(String cidadeAcad) {
        this.cidadeAcad = cidadeAcad;
    }

    public String getWhatsappAcad() {
        return whatsappAcad;
    }

    public void setWhatsappAcad(String whatsappAcad) {
        this.whatsappAcad = whatsappAcad;
    }

    public String getBairroAcad() {
        return bairroAcad;
    }

    public void setBairroAcad(String bairroAcad) {
        this.bairroAcad = bairroAcad;
    }

    public String getEmailAcad() {
        return emailAcad;
    }

    public void setEmailAcad(String emailAcad) {
        this.emailAcad = emailAcad;
    }

    public String getRgAcad() {
        return rgAcad;
    }

    public void setRgAcad(String rgAcad) {
        this.rgAcad = rgAcad;
    }

    public String getUniFacAcad() {
        return uniFacAcad;
    }

    public void setUniFacAcad(String uniFacAcad) {
        this.uniFacAcad = uniFacAcad;
    }

    public String getCpfAcad() {
        return cpfAcad;
    }

    public void setCpfAcad(String cpfAcad) {
        this.cpfAcad = cpfAcad;
    }

    public Date getAnoIngAcad() {
        return anoIngAcad;
    }

    public void setAnoIngAcad(Date anoIngAcad) {
        this.anoIngAcad = anoIngAcad;
    }

    public Date getAnoConcAcad() {
        return anoConcAcad;
    }

    public void setAnoConcAcad(Date anoConcAcad) {
        this.anoConcAcad = anoConcAcad;
    }

    public String getAnexoDecAcad() {
        return anexoDecAcad;
    }

    public void setAnexoDecAcad(String anexoDecAcad) {
        this.anexoDecAcad = anexoDecAcad;
    }

    public String getAnexoFtAcad() {
        return anexoFtAcad;
    }

    public void setAnexoFtAcad(String anexoFtAcad) {
        this.anexoFtAcad = anexoFtAcad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcademico != null ? idAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadAcademico)) {
            return false;
        }
        CadAcademico other = (CadAcademico) object;
        if ((this.idAcademico == null && other.idAcademico != null) || (this.idAcademico != null && !this.idAcademico.equals(other.idAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadAcademico[ idAcademico=" + idAcademico + " ]";
    }
    
}
