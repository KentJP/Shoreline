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
    
    
    public void saveTask(ConversionTask conversionTask) throws DALException;
    
    public List<ConversionTask> getAllTasks() throws DALException;
    
    public void updateTaskStatus(ConversionTask updatedTask) throws DALException;
    
    public void saveMapConfig(MappingDesign mc) throws DALException;
    
    public List<MappingDesign> getAllMapDesigns() throws DALException;
    
    public void logAction(ActionLog log) throws DALException;
    
    public List<ActionLog> getAllActionLogs() throws DALException;
    
    public boolean validateLogin(String loginInfo) throws DALException;
    
    
    
}
