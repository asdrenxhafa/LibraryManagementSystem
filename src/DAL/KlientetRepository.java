/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Klientet;
import java.util.List;

/**
 *
 * @author Asdren
 */
public class KlientetRepository extends EntMngClass implements KlientetInterface {
    @Override
    public void create(Klientet h) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(h);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Klientet h) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.merge(h);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    /**
     *
     * @param h
     * @throws LibraryException
     */
    @Override
    public void delete(Klientet h) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.remove(h);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public List<Klientet> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Klientet.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Klientet findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean findExists(Klientet aut)throws LibraryException{
        try{
            List<Klientet> libra = em.createNamedQuery("Klientet.findAll").getResultList();
            
            boolean res=false;
            
            for(Klientet a : libra){
                if(aut.getEmail().equals(a.getEmail()) || aut.getNumriTel().equals(a.getNumriTel())){
                    res=true;
                }
            }
            
            return res;
            
        }catch(Exception e){
            
        }
        return false;
    }
    
}
