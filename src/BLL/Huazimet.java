/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asdren
 */
@Entity
@Table(name = "huazimet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Huazimet.findAll", query = "SELECT h FROM Huazimet h"),
    @NamedQuery(name = "Huazimet.findByHuazimetID", query = "SELECT h FROM Huazimet h WHERE h.huazimetID = :huazimetID"),
    @NamedQuery(name = "Huazimet.findByDataMarrjes", query = "SELECT h FROM Huazimet h WHERE h.dataMarrjes = :dataMarrjes"),
    @NamedQuery(name = "Huazimet.findByDataPritjes", query = "SELECT h FROM Huazimet h WHERE h.dataPritjes = :dataPritjes"),
    @NamedQuery(name = "Huazimet.findByDataKthimit", query = "SELECT h FROM Huazimet h WHERE h.dataKthimit = :dataKthimit"),
    @NamedQuery(name = "Huazimet.findByAktiv", query = "SELECT h FROM Huazimet h WHERE h.aktiv = :aktiv")})
public class Huazimet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Huazimet_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer huazimetID;
    @Basic(optional = false)
    @Column(name = "DataMarrjes")
    @Temporal(TemporalType.DATE)
    private Date dataMarrjes;
    @Basic(optional = false)
    @Column(name = "DataPritjes")
    @Temporal(TemporalType.DATE)
    private Date dataPritjes;
    @Column(name = "DataKthimit")
    @Temporal(TemporalType.DATE)
    private Date dataKthimit;
    @Basic(optional = false)
    @Column(name = "Aktiv")
    private boolean aktiv;
    @JoinColumn(name = "Klientet_ID", referencedColumnName = "Klientet_ID")
    @ManyToOne(optional = false)
    private Klientet klientetID;
    @JoinColumn(name = "Librat_ID", referencedColumnName = "Librat_ID")
    @ManyToOne(optional = false)
    private Librat libratID;

    public Huazimet() {
    }

    public Huazimet(Integer huazimetID) {
        this.huazimetID = huazimetID;
    }

    public Huazimet(Integer huazimetID, Date dataMarrjes, Date dataPritjes, boolean aktiv) {
        this.huazimetID = huazimetID;
        this.dataMarrjes = dataMarrjes;
        this.dataPritjes = dataPritjes;
        this.aktiv = aktiv;
    }

    public Integer getHuazimetID() {
        return huazimetID;
    }

    public void setHuazimetID(Integer huazimetID) {
        this.huazimetID = huazimetID;
    }

    public Date getDataMarrjes() {
        return dataMarrjes;
    }

    public void setDataMarrjes(Date dataMarrjes) {
        this.dataMarrjes = dataMarrjes;
    }

    public Date getDataPritjes() {
        return dataPritjes;
    }

    public void setDataPritjes(Date dataPritjes) {
        this.dataPritjes = dataPritjes;
    }

    public Date getDataKthimit() {
        return dataKthimit;
    }

    public void setDataKthimit(Date dataKthimit) {
        this.dataKthimit = dataKthimit;
    }

    public boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public Klientet getKlientetID() {
        return klientetID;
    }

    public void setKlientetID(Klientet klientetID) {
        this.klientetID = klientetID;
    }

    public Librat getLibratID() {
        return libratID;
    }

    public void setLibratID(Librat libratID) {
        this.libratID = libratID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (huazimetID != null ? huazimetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Huazimet)) {
            return false;
        }
        Huazimet other = (Huazimet) object;
        if ((this.huazimetID == null && other.huazimetID != null) || (this.huazimetID != null && !this.huazimetID.equals(other.huazimetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  huazimetID + " "+ klientetID.getEmri()+" - "+libratID.getTitulli();
    }
    
}
