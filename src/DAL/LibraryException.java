/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Asdren
 */
public class LibraryException extends Exception{
    public LibraryException(String msg){
        super(msg);
    }
    
    @Override
    public String toString(){
        return this.getLocalizedMessage();
    }
}
