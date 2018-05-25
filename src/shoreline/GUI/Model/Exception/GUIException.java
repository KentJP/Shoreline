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
     *
     * @param message
     * @param cause
     */
    public GUIException(String message, Throwable cause) 
    {
        super(message, cause);
    }
    
    /**
     *
     * @param message
     */
    public GUIException(String message) 
    {
        super(message);
    }
}
