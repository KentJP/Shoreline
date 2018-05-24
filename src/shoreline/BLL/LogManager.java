/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL;

import java.util.List;
import shoreline.BE.ActionLog;
import shoreline.DAL.LogDAO;

/**
 *
 * @author Frederik Tubæk
 */
public class LogManager {
    
    private LogDAO logdao = new LogDAO();

    /**
     * Logs actions in the database
     * @param log 
     */
    public void logAction(ActionLog log) 
    {
        logdao.logAction(log);
    }

    /**
     * Get a List with actions that the users has made.
     * @return Returns a list of actions that the users has made.
     */
    public List<ActionLog> getAllActionLogs() 
    {
        return logdao.getAllActionLogs();
    }


    
}
