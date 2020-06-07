/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Rezervimet;
import java.util.List;

/**
 *
 * @author Asdren
 */
public interface RezervimetInterface {
    void create(Rezervimet r) throws LibraryException;
    void edit(Rezervimet r) throws LibraryException;
    void delete(Rezervimet r)throws LibraryException;
    List<Rezervimet> findAll() throws LibraryException;
    Rezervimet findByID(Integer ID)throws LibraryException;
}
