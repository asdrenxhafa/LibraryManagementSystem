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
@Table(name = "rezervimet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rezervimet.findAll", query = "SELECT r FROM Rezervimet r"),
    @NamedQuery(name = "Rezervimet.findByRezervimetID", query = "SELECT r FROM Rezervimet r WHERE r.rezervimetID = :rezervimetID"),
    @NamedQuery(name = "Rezervimet.findByDRez", query = "SELECT r FROM Rezervimet r WHERE r.dRez = :dRez"),
    @NamedQuery(name = "Rezervimet.findByAktiv", query = "SELECT r FROM Rezervimet r WHERE r.aktiv = :aktiv")})
public class Rezervimet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Rezervimet_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer rezervimetID;
    @Basic(optional = false)
    @Column(name = "DRez")
    @Temporal(TemporalType.DATE)
    private Date dRez;
    @Basic(optional = false)
    @Column(name = "Aktiv")
    private boolean aktiv;
    @JoinColumn(name = "Klientet_ID", referencedColumnName = "Klientet_ID")
    @ManyToOne(optional = false)
    private Klientet klientetID;
    @JoinColumn(name = "Librat_ID", referencedColumnName = "Librat_ID")
    @ManyToOne(optional = false)
    private Librat libratID;

    public Rezervimet() {
    }

    public Rezervimet(Integer rezervimetID) {
        this.rezervimetID = rezervimetID;
    }

    public Rezervimet(Integer rezervimetID, Date dRez, boolean aktiv) {
        this.rezervimetID = rezervimetID;
        this.dRez = dRez;
        this.aktiv = aktiv;
    }

    public Integer getRezervimetID() {
        return rezervimetID;
    }

    public void setRezervimetID(Integer rezervimetID) {
        this.rezervimetID = rezervimetID;
    }

    public Date getDRez() {
        return dRez;
    }

    public void setDRez(Date dRez) {
        this.dRez = dRez;
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
        hash += (rezervimetID != null ? rezervimetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervimet)) {
            return false;
        }
        Rezervimet other = (Rezervimet) object;
        if ((this.rezervimetID == null && other.rezervimetID != null) || (this.rezervimetID != null && !this.rezervimetID.equals(other.rezervimetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  rezervimetID +" "+ klientetID.getEmri()+" - "+libratID.getTitulli();
    }
    
}
