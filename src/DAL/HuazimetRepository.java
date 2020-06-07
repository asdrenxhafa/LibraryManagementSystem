/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Huazimet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Asdren
 */
public class HuazimetRepository extends EntMngClass implements HuazimetInterface {

    @Override
    public void create(Huazimet a) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(a);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Huazimet a) throws LibraryException {
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
    public void delete(Huazimet a) throws LibraryException {
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
    public List<Huazimet> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Huazimet.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Huazimet findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<Huazimet> findByDataKthimit() throws LibraryException {
        try{
            List <Huazimet>asd = em.createNamedQuery("Huazimet.findAll").getResultList();
            List <Huazimet>ret = new ArrayList<Huazimet>();
            for(Huazimet h : asd){
                if(h.getDataKthimit()==null){
                    ret.add(h);
                }
            }
            return ret;
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }
    
}
