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
@Table(name = "pagesat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagesat.findAll", query = "SELECT p FROM Pagesat p"),
    @NamedQuery(name = "Pagesat.findByPagesatID", query = "SELECT p FROM Pagesat p WHERE p.pagesatID = :pagesatID"),
    @NamedQuery(name = "Pagesat.findByDataPageses", query = "SELECT p FROM Pagesat p WHERE p.dataPageses = :dataPageses"),
    @NamedQuery(name = "Pagesat.findByDataSkadimit", query = "SELECT p FROM Pagesat p WHERE p.dataSkadimit = :dataSkadimit"),
    @NamedQuery(name = "Pagesat.findByShumaPageses", query = "SELECT p FROM Pagesat p WHERE p.shumaPageses = :shumaPageses"),
    @NamedQuery(name = "Pagesat.findByAktiv", query = "SELECT p FROM Pagesat p WHERE p.aktiv = :aktiv")})
public class Pagesat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Pagesat_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer pagesatID;
    @Basic(optional = false)
    @Column(name = "DataPageses")
    @Temporal(TemporalType.DATE)
    private Date dataPageses;
    @Basic(optional = false)
    @Column(name = "DataSkadimit")
    @Temporal(TemporalType.DATE)
    private Date dataSkadimit;
    @Basic(optional = false)
    @Column(name = "ShumaPageses")
    private int shumaPageses;
    @Basic(optional = false)
    @Column(name = "Aktiv")
    private boolean aktiv;
    @JoinColumn(name = "Klientet_ID", referencedColumnName = "Klientet_ID")
    @ManyToOne(optional = false)
    private Klientet klientetID;

    public Pagesat() {
    }

    public Pagesat(Integer pagesatID) {
        this.pagesatID = pagesatID;
    }

    public Pagesat(Integer pagesatID, Date dataPageses, Date dataSkadimit, int shumaPageses, boolean aktiv) {
        this.pagesatID = pagesatID;
        this.dataPageses = dataPageses;
        this.dataSkadimit = dataSkadimit;
        this.shumaPageses = shumaPageses;
        this.aktiv = aktiv;
    }

    public Integer getPagesatID() {
        return pagesatID;
    }

    public void setPagesatID(Integer pagesatID) {
        this.pagesatID = pagesatID;
    }

    public Date getDataPageses() {
        return dataPageses;
    }

    public void setDataPageses(Date dataPageses) {
        this.dataPageses = dataPageses;
    }

    public Date getDataSkadimit() {
        return dataSkadimit;
    }

    public void setDataSkadimit(Date dataSkadimit) {
        this.dataSkadimit = dataSkadimit;
    }

    public Integer getShumaPageses() {
        return shumaPageses;
    }

    public void setShumaPageses(int shumaPageses) {
        this.shumaPageses = shumaPageses;
    }

    public Boolean getAktiv() {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagesatID != null ? pagesatID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagesat)) {
            return false;
        }
        Pagesat other = (Pagesat) object;
        if ((this.pagesatID == null && other.pagesatID != null) || (this.pagesatID != null && !this.pagesatID.equals(other.pagesatID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  pagesatID + " "+klientetID.getEmri()+" "+klientetID.getMbiemri();
    }
    
}
