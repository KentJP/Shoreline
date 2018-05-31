/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.Convert;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import shoreline.BE.ActionLog;
import shoreline.BE.ConversionTask;
import shoreline.BLL.Exception.BLLException;
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
    private LogManager logmanager; 
    private List<HashMap> extractedData;
    
    private Date todaysDate = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        
    /**
     * This is the constructor of the class.
     * @param conversionTask
     * @param dir
     * @throws shoreline.BLL.Exception.BLLException
     */
    public ConvertRunnable(ConversionTask conversionTask, String dir) throws BLLException
    {
        this.conversionTask = conversionTask;
        this.dir = dir;
        chooseReader(conversionTask.getFilePath());
        try
        {
            logmanager = new LogManager();
            extractedData = fileReader.extractData(conversionTask);
        }catch (BLLException ex)
        {
            throw ex;
        }
        
        
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
            
            if(fileReader != null)
            {              
                JSONObject root = new JSONObject();
              
                int rowIndex = 1;
                for (HashMap<String, String> listOfValues : extractedData) 
                { 
                    
                    JSONObject jsonRow = new JSONObject();
                    JSONObject planning = new JSONObject();
                    
                    for (Map.Entry<String, String> entry : listOfValues.entrySet()) 
                    {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        
                        
                        
                        if(key.equals("latestFinishDate") || key.equals("earliestStartDate") || key.equals("latestStartDate") || key.equals("estimatedTime"))
                        {
                            planning.put(key, value);
                        }else
                        {
                            jsonRow.put(key, value);
                        }                           
                    }
                    
                    jsonRow.put("planning", planning);
                    jsonRow.put("createdOn", dateFormat.format(todaysDate));
                               
                    root.put("JSON Object: " + rowIndex  , jsonRow);
                    rowIndex++;
            
                }
                try 
                {
                    FileWriter fileWriter = new FileWriter(fileDir);
            
                    fileWriter.write(root.toString(4));
                    fileWriter.close();
     
                    System.out.println("ended conversion on " + conversionTask.getName());
                    
                    conversionTask.changeStatusConverted();
                    
                    
                    ActionLog a = new ActionLog("Successfully converted Task: " + conversionTask.getName());
                    logmanager.logAction(a);
                    
                } catch (IOException ex) 
                {
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
    private void chooseReader(String absolutePath) throws BLLException 
    {
        if(absolutePath.endsWith("xlsx"))
        {
            fileReader = new XLSXReader();
        } else if(absolutePath.endsWith("csv"))
        {
            fileReader = new CSVReader();
        }
        else
        {            
            ActionLog a = new ActionLog("Failed to convert " + conversionTask.getName() + ", due to unsurpported filetype");
            logmanager.logAction(a);
            throw new BLLException("Could not convert task " + conversionTask.getName() + ", due to an unsurpported filetype");

        }
        
    }
    
    
}
