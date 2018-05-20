/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shoreline.BE.ActionLog;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.BE.User;
import shoreline.BLL.Convert.ConvertManager;
import shoreline.BLL.DirectoryWatcher.WatchManager;
import shoreline.BLL.LogManager;
import shoreline.BLL.UserManager;

/**
 *
 * @author frederik
 */
public class Model {
    
    
    private UserManager usermanager = new UserManager();
    private LogManager logmanager = new LogManager();
    private ConvertManager convertmanager = ConvertManager.getInstance();
    private WatchManager watchmanager = new WatchManager();
    
    private ObservableList<Configuration> headerValues;
    private ObservableList<Configuration> inputHeaderValues;
    private ObservableList<String> outputHeaderValues;
    
    private ObservableList<ActionLog> actionLogList;
    
    private ObservableList<ConversionTask> taskList;

    public Model() 
    {
        this.actionLogList = FXCollections.observableArrayList();
        this.outputHeaderValues = FXCollections.observableArrayList("siteName",
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
        this.headerValues = FXCollections.observableArrayList();
        this.inputHeaderValues = FXCollections.observableArrayList();
        this.taskList = FXCollections.observableArrayList();
    }
    
    
    

    public void readProperties(File selectedFile) 
    {
        headerValues.setAll(convertmanager.readProperties(selectedFile));     
    }

    public ObservableList<Configuration> getCurrentHeaderValues() 
    {
        return headerValues;
    }
    
    public ObservableList<String> getCurrentOutputHeaderValues()
    {
        return outputHeaderValues;     
    }
    
    public ObservableList<Configuration> getCurrentInputHeaderVaules()
    {
        return inputHeaderValues;
    }
    
    public ObservableList<ActionLog> getActionLogList()
    {
        actionLogList.setAll(logmanager.getAllActionLogs());
        return actionLogList;
    }
    
    public ObservableList<ConversionTask> getAllTasks() 
    {   
        taskList.setAll(convertmanager.getAllTasks());
        return taskList;
    }


    public void moveInputUp(Configuration selectedItem) 
    {
        int index = inputHeaderValues.indexOf(selectedItem);
        int nextIndex = index - 1; 
        
        if(nextIndex > -1)
        {
             Collections.swap(inputHeaderValues, index, nextIndex);     
        }        
    }
    
    public void moveInputDown(Configuration selectedItem) 
    {
        int index = inputHeaderValues.indexOf(selectedItem);
        int nextIndex = index + 1; 
        
        if(nextIndex < inputHeaderValues.size())
        {
             Collections.swap(inputHeaderValues, index, nextIndex);     
        }
    }

    public void addInput(Configuration selectedHeader) 
    {
        inputHeaderValues.add(selectedHeader);
        headerValues.remove(selectedHeader);
    }

    public void removeInput(Configuration selectedItem) 
    {
        inputHeaderValues.remove(selectedItem);
        headerValues.add(selectedItem);
    }


    public boolean validateLogin(String loginInfo) 
    {
        return usermanager.validateLogin(loginInfo);
    }
    
    public void logAciton(ActionLog log)
    {
        logmanager.logAction(log);
    }

    public void saveTask(String taskName, String filePath) 
    {
        List<Configuration> mapConfig = new ArrayList<>();
        
        for (String outputValue : outputHeaderValues) 
        {
            int index = outputHeaderValues.indexOf(outputValue);
            
            Configuration con  = inputHeaderValues.get(index);
            
            con.setNewValue(outputValue);
            
            mapConfig.add(con); 
        }
        
        convertmanager.saveTask(taskName, filePath, mapConfig);
    }

    public void clearInput() 
    {
        inputHeaderValues.clear();
    }

    public void clearImport() 
    {
        headerValues.clear();
    }

    public void convertToJSON(ConversionTask selectedTask, String dir) 
    {
        int taskIndex = taskList.indexOf(selectedTask);
        ConversionTask updatedTask = taskList.get(taskIndex);
        updatedTask.changeSatusConverting();
        
        taskList.set(taskIndex, updatedTask);
        convertmanager.updateTaskStatus(updatedTask);
        
        if(convertmanager.convertToJSON(selectedTask, dir))
        {
            selectedTask.changeStatusConverted();
            taskList.set(taskIndex, selectedTask);
            convertmanager.updateTaskStatus(selectedTask);
        }
        else
        {
            selectedTask.changeStatusFailed();
            taskList.set(taskIndex, selectedTask);
            convertmanager.updateTaskStatus(selectedTask);
        }
        
    }

    public void saveMapConfig(String mapConfigName) 
    {
        List<Configuration> mapConfig = new ArrayList<>();
        
        for (String outputValue : outputHeaderValues) 
        {
            int index = outputHeaderValues.indexOf(outputValue);
            
            Configuration con  = inputHeaderValues.get(index);
            
            con.setNewValue(outputValue);
            
            mapConfig.add(con); 
            
        }
        MappingDesign mc = new MappingDesign(mapConfigName, mapConfig);
        
        convertmanager.saveMapConfig(mc);
    }

    public List<MappingDesign> getAllMapDesigns() 
    {
       return convertmanager.getAllMapDesigns();
    }

    public void createDirectoryWatcher(String dir, String name, MappingDesign selectedMap) 
    {
        watchmanager.createDirectoryWatcher(dir, name, selectedMap);
    }





 
    
    
    
    
}
