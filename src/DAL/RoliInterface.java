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
public interface RoliInterface {
    List<Roli> findAll() throws LibraryException;
    Roli findByID(Integer ID)throws LibraryException;
}
