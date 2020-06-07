/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.List;
import BLL.Rafti;
/**
 *
 * @author Asdren
 */
public class RaftiRepository extends EntMngClass implements RaftiInterface {
     @Override
    public void create(Rafti p) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(p);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Rafti p) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    /**
     *
     * @param p
     * @throws LibraryException
     */
    @Override
    public void delete(Rafti p) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.detach(p);
            p=em.merge(p);
            em.remove(p);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public List<Rafti> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Rafti.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Rafti findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
