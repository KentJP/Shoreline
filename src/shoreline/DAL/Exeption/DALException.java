/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL.Exeption;

/**
 *
 * @author Frederik Tubæk
 */
public class DALException extends Exception 
{

    /**
     *
     * @param message
     * @param cause
     */
    public DALException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    /**
     *
     * @param message
     */
    public DALException(String message)
    {
        super(message);
    }
    
}
