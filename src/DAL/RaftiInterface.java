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
public interface RaftiInterface {
    void create(Rafti r) throws LibraryException;
    void edit(Rafti r) throws LibraryException;
    void delete(Rafti r)throws LibraryException;
    List<Rafti> findAll() throws LibraryException;
    Rafti findByID(Integer ID)throws LibraryException;
}
