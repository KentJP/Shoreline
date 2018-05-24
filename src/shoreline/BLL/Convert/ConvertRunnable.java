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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import shoreline.BE.ActionLog;
import shoreline.BE.ConversionTask;
import shoreline.BLL.LogManager;
import shoreline.BLL.StrategyFileReader.CSVReader;
import shoreline.BLL.StrategyFileReader.StrategyFileReader;
import shoreline.BLL.StrategyFileReader.XLSXReader;

/**
 *
 * @author Frederik Tubæk
 */
public class ConvertRunnable implements Runnable{

    private ConversionTask conversionTask;
    private String dir;
    private StrategyFileReader fileReader;
    private LogManager logmanager = new LogManager();
    
    /**
     * This is the constructor of the class.
     * @param conversionTask
     * @param dir
     */
    public ConvertRunnable(ConversionTask conversionTask, String dir)
    {
        this.conversionTask = conversionTask;
        this.dir = dir;
    }
    
    /**
     * Extracting the data from the selectedfile
     * Mapping the data
     * Creating a JSON file
     * Writing the mapped data into the JSON file
     */   
    @Override
    public void run() 
    {

            
            System.out.println("started conversion on " + conversionTask.getName());
            
            
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
            
                    root.put("JSON Object: " + rowIndex  , jsonRow);
                    rowIndex++;
            
                }
                try 
                {
                    FileWriter fileWriter = new FileWriter(fileDir);
            
                    fileWriter.write(root.toString(4));
                    fileWriter.close();
     
                    System.out.println("ended conversion on " + conversionTask.getName());
                    
                    ActionLog a = new ActionLog("Successfully converted Task: " + conversionTask.getName());
                    logmanager.logAction(a);
                    
                } catch (IOException ex) 
                {
                    Logger.getLogger(ConvertRunnable.class.getName()).log(Level.SEVERE, null, ex);
                    
                    ActionLog a = new ActionLog("Failed conversion on Task: " + conversionTask.getName());
                    logmanager.logAction(a);
                }
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
        } else if(absolutePath.endsWith("csv"))
        {
            fileReader = new CSVReader();
        }
    }
    
}
