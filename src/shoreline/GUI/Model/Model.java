/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shoreline.BLL.ConvertManager;

/**
 *
 * @author frederik
 */
public class Model {
    
    private ConvertManager cm = new ConvertManager();
    private HashMap<String, Integer> currentSheetInput;
    private ObservableList<String> headerValues = FXCollections.observableArrayList();
    private ObservableList<String> inputHeaderValues = FXCollections.observableArrayList();
    private ObservableList<String> outputHeaderValues = FXCollections.observableArrayList();
    

    public void readProperties(File selectedFile) 
    {
        currentSheetInput = cm.readProperties(selectedFile);
        
        for (String key : currentSheetInput.keySet()) 
        {
            headerValues.add(key);
        }
        
        outputHeaderValues.addAll(
            "siteName",
                "assetSerialNumber",
                "type",
                "externalWorkOrderId",
                "systemStatus",
                "userStatus",
                "createdOn",
                "createdBy",
                "name",
                "priority",
                "status",
                "latestFinishDate",
                "earliestStartDate",
                "latestStartDate",
                "estimatedTime");
                
    
        
    }

    public ObservableList<String> getCurrentHeaderValues() 
    {
        return headerValues;
    }
    
    public ObservableList<String> getCurrentOutputHeaderValues()
    {
        return outputHeaderValues;     
    }
    
    public ObservableList<String> getCurrentInputHeaderVaules()
    {
        return inputHeaderValues;
    }
    
    public void addHardValue(String hardValue)
    {
        inputHeaderValues.add(hardValue);
    }
    
    public void moveInputUp(String selectedItem) 
    {
        int index = inputHeaderValues.indexOf(selectedItem);
        int nextIndex = index - 1; 
        
        if(nextIndex > -1)
        {
             Collections.swap(inputHeaderValues, index, nextIndex);     
        }        
    }
    
    public void moveInputDown(String selectedItem) 
    {
        int index = inputHeaderValues.indexOf(selectedItem);
        int nextIndex = index + 1; 
        
        if(nextIndex < inputHeaderValues.size())
        {
             Collections.swap(inputHeaderValues, index, nextIndex);     
        }
    }

    public void addInput(String selectedHeader) 
    {
        inputHeaderValues.add(selectedHeader);
        headerValues.remove(selectedHeader);
    }

    public void removeInput(String selectedItem) 
    {
        inputHeaderValues.remove(selectedItem);
        headerValues.add(selectedItem);
    }

    public void extractData() 
    {
        HashMap<String, Integer> configuretProperties = new HashMap<>();
        
        for (String outputHeaderValue : outputHeaderValues) 
        {
            int index = outputHeaderValues.indexOf(outputHeaderValue);
            
            String correspondingInput; 
            
                        
            
            if(index > inputHeaderValues.size())
            {
                correspondingInput = "";
            }
            else
            {
                correspondingInput = inputHeaderValues.get(index);
            }
                            
            int value  = currentSheetInput.get(correspondingInput);
            
            configuretProperties.put(outputHeaderValue, value);
        }
        
        cm.convertToJSON(configuretProperties);
            
        
        
    }
    
    
    private Boolean isHardValue(String value)
    {
        if(value.startsWith("\"") && value.endsWith("\""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


 
    
    
    
    
}
