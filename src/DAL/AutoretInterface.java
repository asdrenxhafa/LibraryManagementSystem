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
public interface AutoretInterface {
    void create(Autoret a) throws LibraryException;
    void edit(Autoret a) throws LibraryException;
    void delete(Autoret a)throws LibraryException;
    List<Autoret> findAll() throws LibraryException;
    Autoret findByID(Integer ID)throws LibraryException;
}
