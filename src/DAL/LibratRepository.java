/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Autoret;
import BLL.Librat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Asdren
 */
public class LibratRepository extends EntMngClass implements LibratInterface{
    @Override
    public void create(Librat l) throws LibraryException {
         try{
             em.getTransaction().begin();
             em.persist(l);
             em.getTransaction().commit();
         }catch(Exception e){
             throw new LibraryException("Msg \n"+e.getMessage());
         }
    }

    @Override
    public void edit(Librat l) throws LibraryException {
        try{
            em.getTransaction().begin();
            em.merge(l);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \nKKKKKK"+e.getMessage());
        }
    }

    /**
     *
     * @param l
     * @throws LibraryException
     */
    @Override
    public void delete(Librat l) throws LibraryException {
        try{
            em.getTransaction().begin();
            
            //Remove the references of this book by authors
            l.getAutoretCollection().forEach(Autoret -> {
                    Autoret.getLibratCollection().remove(l);
                });
            
            em.remove(l);
            em.getTransaction().commit();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public List<Librat> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Librat.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Librat findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public List<Librat> findELire() throws LibraryException {
        try{
            List<Librat>librat = em.createNamedQuery("Librat.findAll").getResultList();
            List<Librat> res = new ArrayList<Librat>();
            
            for(Librat l : librat){
                if(l.getELire()){
                    res.add(l);
                }
            }
            
            return res;
            
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }
    
    
    
    public boolean findExists(Librat aut)throws LibraryException{
        try{
            List<Librat> libra = em.createNamedQuery("Librat.findAll").getResultList();
            
            boolean res=false;
            
            for(Librat a : libra){
                if(aut.getTitulli().equals(a.getTitulli())){
                    res=true;
                }
            }
            
            return res;
            
        }catch(Exception e){
            
        }
        return false;
    }
    
    public boolean findExistsAutori(Autoret aut,Librat l)throws LibraryException{
        try{
            List<Librat> libra = em.createNamedQuery("Librat.findAll").getResultList();
            
            boolean res=false;
            
            for(Autoret a : l.getAutoretCollection()){
                if(aut.equals(a)){
                    return true;
                }
            }
            return res;
            
        }catch(Exception e){
            
        }
        return false;
    }
    
}
