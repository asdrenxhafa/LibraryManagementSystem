/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Librat;
import java.util.List;

/**
 *
 * @author Asdren
 */
public interface LibratInterface {
    void create(Librat l) throws LibraryException;
    void edit(Librat l) throws LibraryException;
    void delete(Librat l)throws LibraryException;
    List<Librat> findAll() throws LibraryException;
    Librat findByID(Integer ID)throws LibraryException;
}
