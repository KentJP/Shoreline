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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;

import shoreline.BLL.StrategyFileReader.StrategyFileReader;
import shoreline.BLL.StrategyFileReader.XLSXReader;
import shoreline.DAL.ConvertDAO;
/**
 *
 * @author Kent Juul
 */
public class ConvertManager
{
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    
    private StrategyFileReader fileReader;
    private ConvertDAO convertdao = new ConvertDAO();
  
    public List<Configuration> readProperties(File selectedFile) 
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
    
    
    public boolean convertToJSON(ConversionTask conversionTask, String dir)
    {
        
        executor.submit(new ConvertRunnable(conversionTask, dir));
  
        return false;
    }

    
    public void saveTask(String taskName, String filePath, List<Configuration> mapConfig) 
    {
        ConversionTask conversionTask = new ConversionTask(taskName, filePath, mapConfig);
        convertdao.saveTask(conversionTask);
        
    }

    public List<ConversionTask> getAllTasks() 
    {
        return convertdao.getAllTasks();
    }

    private void chooseReader(String absolutePath) 
    {
        if(absolutePath.endsWith("xlsx"))
        {
            fileReader = new XLSXReader();
        }
    }

    public void updateTaskStatus(ConversionTask updatedTask) 
    {
        convertdao.updateTaskStatus(updatedTask);
    }




  
}

        