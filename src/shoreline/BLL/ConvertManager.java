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

import shoreline.BLL.StrategyFileReader.StrategyFileReader;
import shoreline.BLL.StrategyFileReader.XLSXReader;
/**
 *
 * @author Kent Juul
 */
public class ConvertManager
{
    private StrategyFileReader fileReader;
    private List<HashMap> listProperties;
    private String filePath = "JSONrunner.json";
  
    public List<HashMap> identifyFile(File selectedFile) 
    {
        String path = selectedFile.getAbsolutePath();
        String fileExtension = FilenameUtils.getExtension(path);
        
        if(fileExtension.equals("xlsx"))
        {
            fileReader = new XLSXReader();
        } 
        
        if(fileReader != null)
        {
            return fileReader.readFile(selectedFile);
        }
        
        return null;
    }
    
    
    public void convertToJSON()
    {
        JSONObject root = new JSONObject();
              
        int rowIndex = 1;
        for (HashMap listProperty : listProperties) 
        { 
            JSONObject jsonRow = new JSONObject(listProperty);
            
            root.put("Exel Object " + rowIndex  , jsonRow);
            rowIndex++;
            
        }

        try 
        {
            FileWriter file = new FileWriter(filePath);
            
            file.write(root.toString(4));
            file.flush();
          
            
            
            
        } catch (IOException ex) 
        {
            Logger.getLogger(ConvertManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        
        
        
        
        
 
        
        
    }
    
}

        