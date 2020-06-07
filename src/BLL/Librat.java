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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "librat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Librat.findAll", query = "SELECT l FROM Librat l"),
    @NamedQuery(name = "Librat.findByLibratID", query = "SELECT l FROM Librat l WHERE l.libratID = :libratID"),
    @NamedQuery(name = "Librat.findByTitulli", query = "SELECT l FROM Librat l WHERE l.titulli = :titulli"),
    @NamedQuery(name = "Librat.findByELire", query = "SELECT l FROM Librat l WHERE l.eLire = :eLire")})
public class Librat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Librat_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer libratID;
    @Basic(optional = false)
    @Column(name = "Titulli")
    private String titulli;
    @Basic(optional = false)
    @Column(name = "ELire")
    private boolean eLire;
    
    @JoinTable(name = "aut_lib",joinColumns = {
        @JoinColumn(name = "Librat_ID", referencedColumnName = "Librat_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "Autoret_ID", referencedColumnName = "Autoret_ID")})
    @ManyToMany( fetch=FetchType.EAGER )
    private Collection<Autoret> autoretCollection;
    @JoinColumn(name = "Rafti_ID", referencedColumnName = "Rafti_ID")
    @ManyToOne(optional = false)
    private Rafti raftiID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "libratID")
    private Collection<Rezervimet> rezervimetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "libratID")
    private Collection<Huazimet> huazimetCollection;

    public Librat() {
    }

    public Librat(Integer libratID) {
        this.libratID = libratID;
    }

    public Librat(Integer libratID, String titulli, boolean eLire) {
        this.libratID = libratID;
        this.titulli = titulli;
        this.eLire = eLire;
    }

    public Integer getLibratID() {
        return libratID;
    }

    public void setLibratID(Integer libratID) {
        this.libratID = libratID;
    }

    public String getTitulli() {
        return titulli;
    }

    public void setTitulli(String titulli) {
        this.titulli = titulli;
    }

    public boolean getELire() {
        return eLire;
    }

    public void setELire(boolean eLire) {
        this.eLire = eLire;
    }

    @XmlTransient
    public Collection<Autoret> getAutoretCollection() {
        return autoretCollection;
    }

    public void setAutoretCollection(Collection<Autoret> autoretCollection) {
        this.autoretCollection = autoretCollection;
    }

    public Rafti getRaftiID() {
        return raftiID;
    }

    public void setRaftiID(Rafti raftiID) {
        this.raftiID = raftiID;
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
    
    public void addAutoret(Autoret autor) {
        autoretCollection.add(autor);
        autor.getLibratCollection().add(this);
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (libratID != null ? libratID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Librat)) {
            return false;
        }
        Librat other = (Librat) object;
        if ((this.libratID == null && other.libratID != null) || (this.libratID != null && !this.libratID.equals(other.libratID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  libratID + " "+titulli;
    }
    
}
