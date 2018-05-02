/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model;

import java.io.File;
import java.util.ArrayList;
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
    private List<HashMap> currentSheetInput;
    private ObservableList<String> headerValues = FXCollections.observableArrayList();
    
    private ObservableList<String> outputHeaderValues = FXCollections.observableArrayList();
    

    public void identifyFile(File selectedFile) 
    {
        currentSheetInput = cm.identifyFile(selectedFile);
        
        HashMap<String, String> temp = currentSheetInput.get(1);
        
        for (String key : temp.keySet()) 
        {
            headerValues.add(key);
        }
        
        headerValues.add("reeeeeee");
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
    
    
}
