/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Perdoruesit;
import java.util.List;

/**
 *
 * @author Asdren
 */
public interface PerdoruesitInterface {
    void create(Perdoruesit p) throws LibraryException;
    void edit(Perdoruesit p) throws LibraryException;
    void delete(Perdoruesit p) throws LibraryException;
    List<Perdoruesit> findAll() throws LibraryException;
    Perdoruesit findByID(Integer ID) throws LibraryException;
    Perdoruesit findByOnline() throws LibraryException;
    Perdoruesit loginByUsernameAndPassword(String u, String p) throws LibraryException;
}

