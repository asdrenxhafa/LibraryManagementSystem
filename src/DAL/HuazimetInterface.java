/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Huazimet;
import java.util.List;

/**
 *
 * @author Asdren
 */
public interface HuazimetInterface {
    void create(Huazimet a) throws LibraryException;
    void edit(Huazimet a) throws LibraryException;
    void delete(Huazimet a)throws LibraryException;
    List<Huazimet> findAll() throws LibraryException;
    Huazimet findByID(Integer ID)throws LibraryException;
}
