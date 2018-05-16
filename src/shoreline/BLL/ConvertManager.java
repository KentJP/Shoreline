/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
    
    
    public void convertToJSON(ConversionTask conversionTask, String dir)
    {
        String fileDir = dir + "\\" + conversionTask.getName().trim()+ ".JSON";
        System.out.println(fileDir);
        chooseReader(conversionTask.getFilePath());
        if(fileReader != null)
        {
            List<HashMap> extractedData = fileReader.extractData(conversionTask);
        
            JSONObject root = new JSONObject();
              
            int rowIndex = 1;
            for (HashMap listOfValues : extractedData) 
            { 
                JSONObject jsonRow = new JSONObject(listOfValues);
            
                root.put("Exel Object " + rowIndex  , jsonRow);
                rowIndex++;
            
            }
            try 
            {
                FileWriter file = new FileWriter(fileDir);
            
                file.write(root.toString(4));
                file.flush();
            } 
            catch (IOException ex) 
            {
            Logger.getLogger(ConvertManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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




  
}

        