/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model.Exception;

import shoreline.BLL.Exception.BLLException;

/**
 *
 * @author Frederik Tubæk
 */
public class GUIException extends BLLException {

    /**
     * Wraps an exception from GUI with a message and a cause.
     * @param message
     * @param cause
     */
    public GUIException(String message, Throwable cause) 
    {
        super(message, cause);
    }
    
    /**
     * Wraps an exception from GUI with message.
     * @param message
     */
    public GUIException(String message) 
    {
        super(message);
    }
}
