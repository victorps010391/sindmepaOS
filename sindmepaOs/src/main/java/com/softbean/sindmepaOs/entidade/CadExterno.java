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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desenv
 */
@Entity
@Table(name = "cad_externo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadExterno.findAll", query = "SELECT c FROM CadExterno c"),
    @NamedQuery(name = "CadExterno.findByIdExt", query = "SELECT c FROM CadExterno c WHERE c.idExt = :idExt"),
    @NamedQuery(name = "CadExterno.findByNomeExt", query = "SELECT c FROM CadExterno c WHERE c.nomeExt = :nomeExt"),
    @NamedQuery(name = "CadExterno.findByRgExt", query = "SELECT c FROM CadExterno c WHERE c.rgExt = :rgExt"),
    @NamedQuery(name = "CadExterno.findByCpfExt", query = "SELECT c FROM CadExterno c WHERE c.cpfExt = :cpfExt"),
    @NamedQuery(name = "CadExterno.findBySexoExt", query = "SELECT c FROM CadExterno c WHERE c.sexoExt = :sexoExt"),
    @NamedQuery(name = "CadExterno.findByDataNascExt", query = "SELECT c FROM CadExterno c WHERE c.dataNascExt = :dataNascExt"),
    @NamedQuery(name = "CadExterno.findByCrmExt", query = "SELECT c FROM CadExterno c WHERE c.crmExt = :crmExt"),
    @NamedQuery(name = "CadExterno.findByEspExt", query = "SELECT c FROM CadExterno c WHERE c.espExt = :espExt"),
    @NamedQuery(name = "CadExterno.findByTipoPesExt", query = "SELECT c FROM CadExterno c WHERE c.tipoPesExt = :tipoPesExt"),
    @NamedQuery(name = "CadExterno.findByEmail", query = "SELECT c FROM CadExterno c WHERE c.email = :email"),
    @NamedQuery(name = "CadExterno.findByDtCadExt", query = "SELECT c FROM CadExterno c WHERE c.dtCadExt = :dtCadExt"),
    @NamedQuery(name = "CadExterno.findByDtUltAtuExt", query = "SELECT c FROM CadExterno c WHERE c.dtUltAtuExt = :dtUltAtuExt"),
    @NamedQuery(name = "CadExterno.findByCdTipPagExt", query = "SELECT c FROM CadExterno c WHERE c.cdTipPagExt = :cdTipPagExt"),
    @NamedQuery(name = "CadExterno.findByAgExt", query = "SELECT c FROM CadExterno c WHERE c.agExt = :agExt"),
    @NamedQuery(name = "CadExterno.findByBcExt", query = "SELECT c FROM CadExterno c WHERE c.bcExt = :bcExt"),
    @NamedQuery(name = "CadExterno.findByCcExt", query = "SELECT c FROM CadExterno c WHERE c.ccExt = :ccExt"),
    @NamedQuery(name = "CadExterno.findByNrMatExt", query = "SELECT c FROM CadExterno c WHERE c.nrMatExt = :nrMatExt"),
    @NamedQuery(name = "CadExterno.findByCdInstExt", query = "SELECT c FROM CadExterno c WHERE c.cdInstExt = :cdInstExt")})
public class CadExterno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ext")
    private Integer idExt;
    @Size(max = 512)
    @Column(name = "nome_ext")
    private String nomeExt;
    @Size(max = 12)
    @Column(name = "rg_ext")
    private String rgExt;
    @Size(max = 11)
    @Column(name = "cpf_ext")
    private String cpfExt;
    @Column(name = "sexo_ext")
    private Character sexoExt;
    @Column(name = "data_nasc_ext")
    @Temporal(TemporalType.DATE)
    private Date dataNascExt;
    @Size(max = 10)
    @Column(name = "crm_ext")
    private String crmExt;
    @Size(max = 512)
    @Column(name = "esp_ext")
    private String espExt;
    @Column(name = "tipo_pes_ext")
    private Character tipoPesExt;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 512)
    @Column(name = "email")
    private String email;
    @Column(name = "dt_cad_ext")
    @Temporal(TemporalType.DATE)
    private Date dtCadExt;
    @Column(name = "dt_ult_atu_ext")
    @Temporal(TemporalType.DATE)
    private Date dtUltAtuExt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_tip_pag_ext")
    private int cdTipPagExt;
    @Size(max = 2147483647)
    @Column(name = "ag_ext")
    private String agExt;
    @Size(max = 2147483647)
    @Column(name = "bc_ext")
    private String bcExt;
    @Size(max = 2147483647)
    @Column(name = "cc_ext")
    private String ccExt;
    @Size(max = 2147483647)
    @Column(name = "nr_mat_ext")
    private String nrMatExt;
    @Column(name = "cd_inst_ext")
    private Integer cdInstExt;
    @JoinColumn(name = "id_end_ext", referencedColumnName = "id_end")
    @ManyToOne
    private Endereco idEndExt;

    public CadExterno() {
    }

    public CadExterno(Integer idExt) {
        this.idExt = idExt;
    }

    public CadExterno(Integer idExt, int cdTipPagExt) {
        this.idExt = idExt;
        this.cdTipPagExt = cdTipPagExt;
    }

    public Integer getIdExt() {
        return idExt;
    }

    public void setIdExt(Integer idExt) {
        this.idExt = idExt;
    }

    public String getNomeExt() {
        return nomeExt;
    }

    public void setNomeExt(String nomeExt) {
        this.nomeExt = nomeExt;
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

    public Date getDataNascExt() {
        return dataNascExt;
    }

    public void setDataNascExt(Date dataNascExt) {
        this.dataNascExt = dataNascExt;
    }

    public String getCrmExt() {
        return crmExt;
    }

    public void setCrmExt(String crmExt) {
        this.crmExt = crmExt;
    }

    public String getEspExt() {
        return espExt;
    }

    public void setEspExt(String espExt) {
        this.espExt = espExt;
    }

    public Character getTipoPesExt() {
        return tipoPesExt;
    }

    public void setTipoPesExt(Character tipoPesExt) {
        this.tipoPesExt = tipoPesExt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtCadExt() {
        return dtCadExt;
    }

    public void setDtCadExt(Date dtCadExt) {
        this.dtCadExt = dtCadExt;
    }

    public Date getDtUltAtuExt() {
        return dtUltAtuExt;
    }

    public void setDtUltAtuExt(Date dtUltAtuExt) {
        this.dtUltAtuExt = dtUltAtuExt;
    }

    public int getCdTipPagExt() {
        return cdTipPagExt;
    }

    public void setCdTipPagExt(int cdTipPagExt) {
        this.cdTipPagExt = cdTipPagExt;
    }

    public String getAgExt() {
        return agExt;
    }

    public void setAgExt(String agExt) {
        this.agExt = agExt;
    }

    public String getBcExt() {
        return bcExt;
    }

    public void setBcExt(String bcExt) {
        this.bcExt = bcExt;
    }

    public String getCcExt() {
        return ccExt;
    }

    public void setCcExt(String ccExt) {
        this.ccExt = ccExt;
    }

    public String getNrMatExt() {
        return nrMatExt;
    }

    public void setNrMatExt(String nrMatExt) {
        this.nrMatExt = nrMatExt;
    }

    public Integer getCdInstExt() {
        return cdInstExt;
    }

    public void setCdInstExt(Integer cdInstExt) {
        this.cdInstExt = cdInstExt;
    }

    public Endereco getIdEndExt() {
        return idEndExt;
    }

    public void setIdEndExt(Endereco idEndExt) {
        this.idEndExt = idEndExt;
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
