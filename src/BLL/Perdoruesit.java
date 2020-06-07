/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asdren
 */
@Entity
@Table(name = "Perdoruesit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perdoruesit.findAll", query = "SELECT p FROM Perdoruesit p"),
    @NamedQuery(name = "Perdoruesit.findByPerdoruesitID", query = "SELECT p FROM Perdoruesit p WHERE p.perdoruesitID = :perdoruesitID"),
    @NamedQuery(name = "Perdoruesit.findByEmri", query = "SELECT p FROM Perdoruesit p WHERE p.emri = :emri"),
    @NamedQuery(name = "Perdoruesit.findByMbiemri", query = "SELECT p FROM Perdoruesit p WHERE p.mbiemri = :mbiemri"),
    @NamedQuery(name = "Perdoruesit.findByEmail", query = "SELECT p FROM Perdoruesit p WHERE p.email = :email"),
    @NamedQuery(name = "Perdoruesit.findByPassword", query = "SELECT p FROM Perdoruesit p WHERE p.password = :password"),
    @NamedQuery(name = "Perdoruesit.findByFoto", query = "SELECT p FROM Perdoruesit p WHERE p.foto = :foto"),
    @NamedQuery(name = "Perdoruesit.findByOnline", query = "SELECT p FROM Perdoruesit p WHERE p.online = :online")})
public class Perdoruesit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Perdoruesit_ID")
    
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq" , sequenceName = "INV_SEQ", allocationSize = 1)
    
    private Integer perdoruesitID;
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
    @Column(name = "Password")
    private String password;
    @Column(name = "Foto")
    private String foto;
    @Basic(optional = false)
    @Column(name = "Online")
    private boolean online;
    @JoinColumn(name = "Roli_ID", referencedColumnName = "Roli_ID")
    @ManyToOne(optional = false)
    private Roli roliID;

    public Perdoruesit() {
    }

    public Perdoruesit(Integer perdoruesitID) {
        this.perdoruesitID = perdoruesitID;
    }

    public Perdoruesit(Integer perdoruesitID, String emri, String mbiemri, String email, String password, boolean online) {
        this.perdoruesitID = perdoruesitID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.password = password;
        this.online = online;
    }

    public Integer getPerdoruesitID() {
        return perdoruesitID;
    }

    public void setPerdoruesitID(Integer perdoruesitID) {
        this.perdoruesitID = perdoruesitID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Roli getRoliID() {
        return roliID;
    }

    public void setRoliID(Roli roliID) {
        this.roliID = roliID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perdoruesitID != null ? perdoruesitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perdoruesit)) {
            return false;
        }
        Perdoruesit other = (Perdoruesit) object;
        if ((this.perdoruesitID == null && other.perdoruesitID != null) || (this.perdoruesitID != null && !this.perdoruesitID.equals(other.perdoruesitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return perdoruesitID + " "+emri+" "+mbiemri;
    }
    
}
