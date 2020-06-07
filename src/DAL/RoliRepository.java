/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Roli;
import java.util.List;

/**
 *
 * @author Asdren
 */
public class RoliRepository extends EntMngClass implements RoliInterface{

    @Override
    public List<Roli> findAll() throws LibraryException {
        try{
            return em.createNamedQuery("Roli.findAll").getResultList();
        }catch(Exception e){
            throw new LibraryException("Msg \n"+e.getMessage());
        }
    }

    @Override
    public Roli findByID(Integer ID) throws LibraryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
