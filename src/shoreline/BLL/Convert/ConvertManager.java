/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.Convert;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.apache.commons.io.FilenameUtils;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.BLL.Exception.BLLException;
import shoreline.BLL.StrategyFileReader.CSVReader;

import shoreline.BLL.StrategyFileReader.StrategyFileReader;
import shoreline.BLL.StrategyFileReader.XLSXReader;
import shoreline.DAL.Exeption.DALException;
import shoreline.DAL.Facade.DalFacade;
import shoreline.DAL.Facade.DalFacadeDistributor;
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
    private DalFacade dalfacade;
    
    
    /**
     * This is the constructor of this class.
     * Beaware that this constructor is private.
     * @throws BLLException 
     */
    private ConvertManager() throws BLLException
    {
        try
        {
            this.dalfacade = new DalFacadeDistributor();
                
        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }
    
   
    /*
     * Gets instance - singleton.
     * @return an instance of convertmanager - singleton.

     * Singleton - This class can only be instantiated once.
     * 
     * @return Returns a single instatiated object of this class.
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
   * Reads properties of a selected file into a list.
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
            ConvertRunnable cr = new ConvertRunnable(conversionTask, dir);
            executor.submit(cr);
         
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
            dalfacade.saveTask(conversionTask);
            

        } catch (DALException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
           
        }
        
    }

    /**
     * Retrieves a list of all the tasks from the database.
     * @return Returns a list of all tasks from the database.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<ConversionTask> getAllTasks() throws BLLException 
    {
        try 
        {
            return dalfacade.getAllTasks();
            
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
                dalfacade.updateTaskStatus(updatedTask);
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
                dalfacade.saveMapConfig(mc);
            } catch (DALException ex) 
            {
                throw new BLLException(ex.getMessage(), ex);
            }
    }

    /**
     * Retrieves all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<MappingDesign> getAllMapDesigns() throws BLLException 
    {
            try
            {
                return dalfacade.getAllMapDesigns();
            } catch (DALException ex) 
            {
                throw new BLLException(ex.getMessage(), ex);
            }
    }





  
}

        