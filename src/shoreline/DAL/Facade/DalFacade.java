/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL.Facade;

import java.util.List;
import shoreline.BE.ActionLog;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.DAL.Exeption.DALException;

/**
 *
 * @author Frederik Tubæk
 */
public interface DalFacade {
    
    /**
     * Saves the task and its configuration to the database
     * @param conversionTask
     * @throws DALException
     */
    public void saveTask(ConversionTask conversionTask) throws DALException;
    
    /**
     * Gets a list of all the tasks in the database.
     * @return Returns a list of all the tasks from the database.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public List<ConversionTask> getAllTasks() throws DALException;
    
    /**
     * Updating the status of CoversionTask in the database.
     * @param updatedTask 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    public void updateTaskStatus(ConversionTask updatedTask) throws DALException;
    
    /**
     * Saves the map configuration and design to the database. 
     * @param mc
     * @throws shoreline.DAL.Exeption.DALException
     */
    public void saveMapConfig(MappingDesign mc) throws DALException;
    
    /**
     * Get all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public List<MappingDesign> getAllMapDesigns() throws DALException;
    
    /**
     * Logs actions in the database
     * @param log 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    public void logAction(ActionLog log) throws DALException;
    
    /**
     * Draw all actions made from the database.
     * @return Returns a list of actions that the users has made.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public List<ActionLog> getAllActionLogs() throws DALException;
    
    /**
     * Validate wheater or not the Email is in the Database.
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public boolean validateLogin(String loginInfo) throws DALException;
    
    
    
}
