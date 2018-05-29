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
import shoreline.DAL.DAO.ConvertDAO;
import shoreline.DAL.DAO.LogDAO;
import shoreline.DAL.DAO.UserDAO;
import shoreline.DAL.Exeption.DALException;

/**
 *
 * @author Frederik Tubæk
 */
public class DalFacadeDistributor implements DalFacade
{
    
    ConvertDAO convertdao;
    LogDAO logdao;
    UserDAO userdao;
    
    /**
     * This is the constructor of this class.
     * @throws DALException
     */
    public DalFacadeDistributor() throws DALException
    {
        convertdao = new ConvertDAO();
        logdao = new LogDAO();
        userdao = new UserDAO();
    }

    /**
     * Saves the task and its configuration to the database
     * @param conversionTask
     * @throws DALException
     */
    @Override
    public void saveTask(ConversionTask conversionTask) throws DALException 
    {
        convertdao.saveTask(conversionTask);
    }

    /**
     * Gets a list of all the tasks in the database.
     * @return Returns a list of all the tasks from the database.
     * @throws shoreline.DAL.Exeption.DALException
     */
    @Override
    public List<ConversionTask> getAllTasks() throws DALException 
    {
        return convertdao.getAllTasks();
    }

    /**
     * Updating the status of CoversionTask in the database.
     * @param updatedTask 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    @Override
    public void updateTaskStatus(ConversionTask updatedTask) throws DALException 
    {
        convertdao.updateTaskStatus(updatedTask);
    }

    /**
     * Saves the map configuration and design to the database. 
     * @param mc
     * @throws shoreline.DAL.Exeption.DALException
     */
    @Override
    public void saveMapConfig(MappingDesign mc) throws DALException 
    {
        convertdao.saveMapConfig(mc);
    }

    /**
     * Get all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     * @throws shoreline.DAL.Exeption.DALException
     */
    @Override
    public List<MappingDesign> getAllMapDesigns() throws DALException 
    {
        return convertdao.getAllMapDesigns();
    }

    /**
     * Logs actions in the database
     * @param log 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    @Override
    public void logAction(ActionLog log) throws DALException 
    {
        logdao.logAction(log);
    }

    /**
     * Draw all actions made from the database.
     * @return Returns a list of actions that the users has made.
     * @throws shoreline.DAL.Exeption.DALException
     */
    @Override
    public List<ActionLog> getAllActionLogs() throws DALException
    {
        return logdao.getAllActionLogs();
    }

    /**
     * Validate wheater or not the Email is in the Database.
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     * @throws shoreline.DAL.Exeption.DALException
     */
    @Override
    public boolean validateLogin(String loginInfo) throws DALException 
    {
        return userdao.validateLogin(loginInfo);
    }
    
    
            
    
}
