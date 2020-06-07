/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Autoret;
import java.util.List;

/**
 *
 * @author Asdren
 */
public class AutoretRepository extends EntMngClass implements AutoretInterface {
    @Override
    public void create(Autoret a) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(a);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Autoret a) throws LibraryException {
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
    public void delete(Autoret a) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public List<Autoret> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Autoret.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Autoret findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean findExists(Autoret aut)throws LibraryException{
        try{
            List<Autoret> libra = em.createNamedQuery("Autoret.findAll").getResultList();
            
            boolean res=false;
            
            for(Autoret a : libra){
                if(aut.getEmri().equals(a.getEmri()) && aut.getMbiemri().equals(a.getMbiemri())){
                    res=true;
                }
            }
            
            return res;
            
        }catch(Exception e){
            
        }
        return false;
    }
    
    
}
