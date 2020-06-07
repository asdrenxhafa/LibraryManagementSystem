/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Klientet;
import BLL.Pagesat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asdren
 */
public class PagesatRepository extends EntMngClass implements PagesatInterface{
    
    @Override
    public void create(Pagesat a) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(a);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Pagesat a) throws LibraryException {
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
    public void delete(Pagesat a) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public List<Pagesat> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Pagesat.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Pagesat findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<Klientet> findByKlient() throws LibraryException {
        try{
            List<Pagesat> pagesa= em.createNamedQuery("Pagesat.findAll").getResultList();
            List<Klientet> kli = new ArrayList<Klientet>();
            
            for(Pagesat p : pagesa){
                if(p.getAktiv()==true){
                    kli.add(p.getKlientetID());
                }
            }
            
            return kli;
            
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }
    
}
