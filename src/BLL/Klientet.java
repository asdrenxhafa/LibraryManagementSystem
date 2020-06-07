/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asdren
 */
@Entity
@Table(name = "Klientet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klientet.findAll", query = "SELECT k FROM Klientet k"),
    @NamedQuery(name = "Klientet.findByKlientetID", query = "SELECT k FROM Klientet k WHERE k.klientetID = :klientetID"),
    @NamedQuery(name = "Klientet.findByEmri", query = "SELECT k FROM Klientet k WHERE k.emri = :emri"),
    @NamedQuery(name = "Klientet.findByMbiemri", query = "SELECT k FROM Klientet k WHERE k.mbiemri = :mbiemri"),
    @NamedQuery(name = "Klientet.findByEmail", query = "SELECT k FROM Klientet k WHERE k.email = :email"),
    @NamedQuery(name = "Klientet.findByNumriTel", query = "SELECT k FROM Klientet k WHERE k.numriTel = :numriTel"),
    @NamedQuery(name = "Klientet.findByAktiv", query = "SELECT k FROM Klientet k WHERE k.aktiv = :aktiv")})
public class Klientet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Klientet_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer klientetID;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "NumriTel")
    private String numriTel;
    @Basic(optional = false)
    @Column(name = "Aktiv")
    private boolean aktiv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klientetID")
    private Collection<Rezervimet> rezervimetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klientetID")
    private Collection<Huazimet> huazimetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klientetID")
    private Collection<Pagesat> pagesatCollection;

    public Klientet() {
    }

    public Klientet(Integer klientetID) {
        this.klientetID = klientetID;
    }

    public Klientet(Integer klientetID, String emri, String mbiemri, String email, String numriTel, boolean aktiv) {
        this.klientetID = klientetID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.numriTel = numriTel;
        this.aktiv = aktiv;
    }

    public Integer getKlientetID() {
        return klientetID;
    }

    public void setKlientetID(Integer klientetID) {
        this.klientetID = klientetID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumriTel() {
        return numriTel;
    }

    public void setNumriTel(String numriTel) {
        this.numriTel = numriTel;
    }

    public boolean getAktiv() {
        return aktiv;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    @XmlTransient
    public Collection<Rezervimet> getRezervimetCollection() {
        return rezervimetCollection;
    }

    public void setRezervimetCollection(Collection<Rezervimet> rezervimetCollection) {
        this.rezervimetCollection = rezervimetCollection;
    }

    @XmlTransient
    public Collection<Huazimet> getHuazimetCollection() {
        return huazimetCollection;
    }

    public void setHuazimetCollection(Collection<Huazimet> huazimetCollection) {
        this.huazimetCollection = huazimetCollection;
    }

    @XmlTransient
    public Collection<Pagesat> getPagesatCollection() {
        return pagesatCollection;
    }

    public void setPagesatCollection(Collection<Pagesat> pagesatCollection) {
        this.pagesatCollection = pagesatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klientetID != null ? klientetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klientet)) {
            return false;
        }
        Klientet other = (Klientet) object;
        if ((this.klientetID == null && other.klientetID != null) || (this.klientetID != null && !this.klientetID.equals(other.klientetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   klientetID + " "+emri+" "+mbiemri;
    }
    
}
