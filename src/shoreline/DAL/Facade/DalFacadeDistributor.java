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
    
    public DalFacadeDistributor() throws DALException
    {
        convertdao = new ConvertDAO();
        logdao = new LogDAO();
        userdao = new UserDAO();
    }

    @Override
    public void saveTask(ConversionTask conversionTask) throws DALException 
    {
        convertdao.saveTask(conversionTask);
    }

    @Override
    public List<ConversionTask> getAllTasks() throws DALException 
    {
        return convertdao.getAllTasks();
    }

    @Override
    public void updateTaskStatus(ConversionTask updatedTask) throws DALException 
    {
        convertdao.updateTaskStatus(updatedTask);
    }

    @Override
    public void saveMapConfig(MappingDesign mc) throws DALException 
    {
        convertdao.saveMapConfig(mc);
    }

    @Override
    public List<MappingDesign> getAllMapDesigns() throws DALException 
    {
        return convertdao.getAllMapDesigns();
    }

    @Override
    public void logAction(ActionLog log) throws DALException 
    {
        logdao.logAction(log);
    }

    @Override
    public List<ActionLog> getAllActionLogs() throws DALException
    {
        return logdao.getAllActionLogs();
    }

    @Override
    public boolean validateLogin(String loginInfo) throws DALException 
    {
        return userdao.validateLogin(loginInfo);
    }
    
    
            
    
}
