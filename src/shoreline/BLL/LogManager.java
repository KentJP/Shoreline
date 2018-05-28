/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shoreline.BE.ActionLog;
import shoreline.BLL.Exception.BLLException;
import shoreline.DAL.Exeption.DALException;
import shoreline.DAL.Facade.DalFacade;
import shoreline.DAL.Facade.DalFacadeDistributor;

/**
 *
 * @author Frederik Tubæk
 */
public class LogManager {
    
    private DalFacade dalfacade;

    /**
     *
     * @throws BLLException
     */
    public LogManager() throws BLLException 
    {        
        try 
        {
            dalfacade = new DalFacadeDistributor();
        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }
    /**
     * Logs actions in the database 
     * @param log
     */ 
    public void logAction(ActionLog log)
    {
        try 
        {
            dalfacade.logAction(log);
            
        } catch (DALException ex) 
        {
            Logger.getLogger(LogManager.class.getName()).log(Level.INFO, null, ex);
        } 
    }

    /**
     * Get a List with actions that the users has made.
     * @return Returns a list of actions that the users has made.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<ActionLog> getAllActionLogs() throws BLLException 
    {
        try {
            return dalfacade.getAllActionLogs();
            
        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }


    
}
