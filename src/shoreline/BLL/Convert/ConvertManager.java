/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.Convert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.BLL.Exception.BLLException;
import shoreline.BLL.StrategyFileReader.CSVReader;

import shoreline.BLL.StrategyFileReader.StrategyFileReader;
import shoreline.BLL.StrategyFileReader.XLSXReader;
import shoreline.DAL.ConvertDAO;
import shoreline.DAL.Exeption.DALException;
/**
 *
 * @author Kent Juul
 */
public class ConvertManager
{
    private static ConvertManager convertmanager;
    private static boolean isInstansiated = false;

    private static final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });
    
    private StrategyFileReader fileReader;
    private ConvertDAO convertdao;
    
    
    private ConvertManager() throws BLLException
    {
        try
        {
            this.convertdao = new ConvertDAO();
                
        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Gets instance - singleton.
     * @return an instance of convertmanager - singleton.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public static ConvertManager getInstance() throws BLLException
    {
        if(!isInstansiated)
        {
            try 
            {
                convertmanager = new ConvertManager();
                isInstansiated = true;
                
            } catch (BLLException ex) 
            {
                throw ex;
            }
        }
        else
        {
            return convertmanager;            
        }
        return null;
    }
    
    
    
  /**
   * Reading properties of a selected file
   * @param selectedFile
   * @return Properties of a selected file
     * @throws shoreline.BLL.Exception.BLLException
   */
    public List<Configuration> readProperties(File selectedFile) throws BLLException 
    {
        String path = selectedFile.getAbsolutePath();
        String fileExtension = FilenameUtils.getExtension(path);
       
        chooseReader(selectedFile.getAbsolutePath());
        
        if(fileReader != null)
        {
            return fileReader.readProperties(selectedFile);
        }
        
        return null;
    }
    
    /**
     * Starts a thread that starts Converting the selected task to JSON.
     * @param conversionTask
     * @param dir
     * @return boolean
     * @throws shoreline.BLL.Exception.BLLException
     */
    public boolean convertToJSON(ConversionTask conversionTask, String dir) throws BLLException
    {
        
        try 
        {
            executor.submit(new ConvertRunnable(conversionTask, dir));    
            return true;
            
        } catch (BLLException ex) 
        {
            throw ex;
        }
    }

    /**
     * Saves the task and its configuration to the database.
     * @param taskName
     * @param filePath
     * @param mapConfig 
     * @throws shoreline.BLL.Exception.BLLException 
     */
    public void saveTask(String taskName, String filePath, List<Configuration> mapConfig) throws BLLException 
    {
        try 
        {
            ConversionTask conversionTask = new ConversionTask(taskName, filePath, mapConfig);
            convertdao.saveTask(conversionTask);
            
        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
        
    }

    /**
     * Gets a list of all the tasks from the database.
     * @return Returns a list of all tasks from the database.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<ConversionTask> getAllTasks() throws BLLException 
    {
        try 
        {
            return convertdao.getAllTasks();
            
        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    /**
     * Chooses the right reader for the specific file.
     * Can work with:
     * - xlsx
     * - csv
     * @param absolutePath 
     */
    private void chooseReader(String absolutePath) 
    {
        if(absolutePath.endsWith("xlsx"))
        {
            fileReader = new XLSXReader();
        } else if (absolutePath.endsWith("csv"))
        {
            fileReader = new CSVReader();
        }
    }
    
    /**
     * Updating the status of CoversionTask in the database.
     * Either "Completed" or "Failed".
     * @param updatedTask 
     * @throws shoreline.BLL.Exception.BLLException 
     */
    public void updateTaskStatus(ConversionTask updatedTask) throws BLLException 
    {
            try 
            {
                convertdao.updateTaskStatus(updatedTask);
            } catch (DALException ex) 
            {
                throw new BLLException(ex.getMessage(), ex);
            }
    }

    /**
     * Saves the map configuration and design to the database. 
     * @param mc
     * @throws shoreline.BLL.Exception.BLLException
     */
    public void saveMapConfig(MappingDesign mc) throws BLLException 
    {
            try 
            {
                convertdao.saveMapConfig(mc);
            } catch (DALException ex) 
            {
                throw new BLLException(ex.getMessage(), ex);
            }
    }

    /**
     * Get all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<MappingDesign> getAllMapDesigns() throws BLLException 
    {
            try
            {
                return convertdao.getAllMapDesigns();
            } catch (DALException ex) 
            {
                throw new BLLException(ex.getMessage(), ex);
            }
    }





  
}

        