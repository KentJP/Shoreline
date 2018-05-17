/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.Convert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import shoreline.BE.ConversionTask;
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
    
    
    public ConvertRunnable(ConversionTask conversionTask, String dir)
    {
        this.conversionTask = conversionTask;
        this.dir = dir;
    }
    
    
    @Override
    public void run() 
    {
        try
        {
            System.out.println("started conversion on " + conversionTask.getName());
            Thread.sleep(20000);
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
                    System.out.println("ended conversion on " + conversionTask.getName());
                    
                } catch (IOException ex) 
                {
                    Logger.getLogger(ConvertRunnable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch(InterruptedException e)
        {
            
        }
        
    
    }
    
    
    private void chooseReader(String absolutePath) 
    {
        if(absolutePath.endsWith("xlsx"))
        {
            fileReader = new XLSXReader();
        }
    }
    
}
