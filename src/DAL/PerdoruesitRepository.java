/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Klientet;
import BLL.Perdoruesit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Asdren
 */
public class PerdoruesitRepository extends EntMngClass implements PerdoruesitInterface{
    
    @Override
    public void create(Perdoruesit a) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(a);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Perdoruesit a) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    /**
     *
     * @param a
     * @throws LibraryException
     */
    @Override
    public void delete(Perdoruesit a) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.detach(a);
            a=em.merge(a);
            em.remove(a);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public List<Perdoruesit> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Perdoruesit.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Perdoruesit findByID(Integer ID) throws LibraryException {
        try {
            Query query = em.createQuery("SELECT p FROM Perdoruesit p WHERE p.perdoruesitID = :abc");
            query.setParameter("abc", ID);
            return (Perdoruesit) query.getSingleResult();
        } catch (Exception e) {
            throw new LibraryException("Msg! \n" + e.getMessage());
        }
    }

    @Override
    public Perdoruesit loginByUsernameAndPassword(String u, String p) throws LibraryException {
        try {
            Query query = em.createQuery("SELECT p FROM Perdoruesit p WHERE p.email = :us AND p.password=:psw");
            query.setParameter("us", u);
            query.setParameter("psw", p);
            Perdoruesit per = (Perdoruesit) query.getSingleResult();
            return per;
            }catch (Exception e) {
            throw new LibraryException("Perdoruesi nuk egziston apo keni gabuar passwordin");
        }
    }

    @Override
    public Perdoruesit findByOnline() throws LibraryException {
       try{
           List<Perdoruesit> per= em.createNamedQuery("Perdoruesit.findAll").getResultList();
           Perdoruesit res = new Perdoruesit();
           for(Perdoruesit p: per){
               if(p.getOnline()==true){
                   res=p;
               }
           }
           return res;
           
       }catch(Exception e){
           throw new LibraryException("Asnje perdorues nuk eshte online");
       }
    }
    
    public List<Perdoruesit> findByAdmin() throws LibraryException {
       try{
           List<Perdoruesit> per= em.createNamedQuery("Perdoruesit.findAll").getResultList();
           List<Perdoruesit> res = new ArrayList<Perdoruesit>();
           for(Perdoruesit p: per){
               if(p.getRoliID().getRoliID()==1){
                   res.add(p);
               }
           }
           return res;
           
       }catch(Exception e){
           throw new LibraryException("Asnje perdorues nuk eshte admin");
       }
    }
    
    
    
    
    public boolean findExists(Perdoruesit aut)throws LibraryException{
        try{
            List<Perdoruesit> libra = em.createNamedQuery("Perdoruesit.findAll").getResultList();
            
            boolean res=false;
            
            for(Perdoruesit a : libra){
                if(aut.getEmail().equals(a.getEmail()) || aut.getFoto().equals(a.getFoto())){
                    res=true;
                }
            }
            
            return res;
            
        }catch(Exception e){
            
        }
        return false;
    }
    
    
}
