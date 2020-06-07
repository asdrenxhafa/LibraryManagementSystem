/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asdren
 */
@Entity
@Table(name = "autoret")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autoret.findAll", query = "SELECT a FROM Autoret a"),
    @NamedQuery(name = "Autoret.findByAutoretID", query = "SELECT a FROM Autoret a WHERE a.autoretID = :autoretID"),
    @NamedQuery(name = "Autoret.findByEmri", query = "SELECT a FROM Autoret a WHERE a.emri = :emri"),
    @NamedQuery(name = "Autoret.findByMbiemri", query = "SELECT a FROM Autoret a WHERE a.mbiemri = :mbiemri")})
public class Autoret implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Autoret_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer autoretID;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Mbiemri")
    private String mbiemri;
    @ManyToMany(mappedBy = "autoretCollection")
    private Collection<Librat> libratCollection;

    public Autoret() {
    }

    public Autoret(Integer autoretID) {
        this.autoretID = autoretID;
    }

    public Autoret(Integer autoretID, String emri, String mbiemri) {
        this.autoretID = autoretID;
        this.emri = emri;
        this.mbiemri = mbiemri;
    }

    public Integer getAutoretID() {
        return autoretID;
    }

    public void setAutoretID(Integer autoretID) {
        this.autoretID = autoretID;
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

    @XmlTransient
    public Collection<Librat> getLibratCollection() {
        return libratCollection;
    }

    public void setLibratCollection(Collection<Librat> libratCollection) {
        this.libratCollection = libratCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoretID != null ? autoretID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autoret)) {
            return false;
        }
        Autoret other = (Autoret) object;
        if ((this.autoretID == null && other.autoretID != null) || (this.autoretID != null && !this.autoretID.equals(other.autoretID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   autoretID + " "+emri+ " "+mbiemri;
    }
    
}
