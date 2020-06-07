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
@Table(name = "rafti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rafti.findAll", query = "SELECT r FROM Rafti r"),
    @NamedQuery(name = "Rafti.findByRaftiID", query = "SELECT r FROM Rafti r WHERE r.raftiID = :raftiID"),
    @NamedQuery(name = "Rafti.findByRreshti", query = "SELECT r FROM Rafti r WHERE r.rreshti = :rreshti")})
public class Rafti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Rafti_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer raftiID;
    @Basic(optional = false)
    @Column(name = "Rreshti")
    private int rreshti;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raftiID")
    private Collection<Librat> libratCollection;

    public Rafti() {
    }

    public Rafti(Integer raftiID) {
        this.raftiID = raftiID;
    }

    public Rafti(Integer raftiID, int rreshti) {
        this.raftiID = raftiID;
        this.rreshti = rreshti;
    }

    public Integer getRaftiID() {
        return raftiID;
    }

    public void setRaftiID(Integer raftiID) {
        this.raftiID = raftiID;
    }

    public int getRreshti() {
        return rreshti;
    }

    public void setRreshti(int rreshti) {
        this.rreshti = rreshti;
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
        hash += (raftiID != null ? raftiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rafti)) {
            return false;
        }
        Rafti other = (Rafti) object;
        if ((this.raftiID == null && other.raftiID != null) || (this.raftiID != null && !this.raftiID.equals(other.raftiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return raftiID + " rreshti: "+rreshti;
    }
    
}
