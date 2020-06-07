/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Klientet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asdren
 */
public interface KlientetInterface {
    void create(Klientet h) throws LibraryException;
    void edit(Klientet h) throws LibraryException;
    void delete(Klientet h)throws LibraryException;
    List<Klientet> findAll() throws LibraryException;
    Klientet findByID(Integer ID)throws LibraryException;
}
