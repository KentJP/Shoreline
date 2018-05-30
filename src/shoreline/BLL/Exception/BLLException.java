/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.Exception;



/**
 *
 * @author Frederik Tubæk
 */
public class BLLException extends Exception 
{

    /**
     * Wraps an exception from BLL with a message and a cause.
     * @param message
     * @param cause
     */
    public BLLException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Wraps an exception from BLL with message.
     * @param message
     */
    public BLLException(String message) 
    {
        super(message);
    }
    
    
    
}
