/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.Exception;

import shoreline.DAL.Exeption.DALException;

/**
 *
 * @author Frederik Tubæk
 */
public class BLLException extends Exception 
{
    public BLLException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BLLException(String message) 
    {
        super(message);
    }
    
    
    
}
