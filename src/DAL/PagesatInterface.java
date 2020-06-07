/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Pagesat;
import java.util.List;

/**
 *
 * @author Asdren
 */
public interface PagesatInterface {
    void create(Pagesat a) throws LibraryException;
    void edit(Pagesat a) throws LibraryException;
    void delete(Pagesat a)throws LibraryException;
    List<Pagesat> findAll() throws LibraryException;
    Pagesat findByID(Integer ID)throws LibraryException;
}
