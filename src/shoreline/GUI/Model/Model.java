/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shoreline.BE.ActionLog;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.BLL.Convert.ConvertManager;
import shoreline.BLL.DirectoryWatcher.WatchManager;
import shoreline.BLL.Exception.BLLException;
import shoreline.BLL.LogManager;
import shoreline.BLL.UserManager;
import shoreline.GUI.Model.Exception.GUIException;

/**
 *
 * @author frederik
 */
public class Model
{

    private UserManager usermanager;
    private LogManager logmanager;
    private ConvertManager convertmanager;
    private WatchManager watchmanager = new WatchManager();

    private ObservableList<Configuration> headerValues;
    private ObservableList<Configuration> inputHeaderValues;
    private ObservableList<String> outputHeaderValues;

    private ObservableList<ActionLog> actionLogList;

    private ObservableList<ConversionTask> taskList;

    /**
     * This is the constructor of this class.
     * It contains several observableArrayLists.
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public Model() throws GUIException
    {
        try {
            this.convertmanager = ConvertManager.getInstance();
            this.logmanager = new LogManager();
            this.usermanager = new UserManager();
            this.actionLogList = FXCollections.observableArrayList();
            this.outputHeaderValues = FXCollections.observableArrayList(
                    "siteName",
                    "assetSerialNumber",
                    "type",
                    "externalWorkOrderId",
                    "systemStatus",
                    "userStatus",
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
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Reads properties of a selected file
     *
     * @param selectedFile
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public void readProperties(File selectedFile) throws GUIException
    {
        try 
        {
            headerValues.setAll(convertmanager.readProperties(selectedFile));
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Retrives an observableList with the current header values.
     *
     * @return Returns an ObservableList with the header values.
     */
    public ObservableList<Configuration> getCurrentHeaderValues()
    {
        return headerValues;
    }

    /**
     * Retrives an ObservableList with predefined values
     *
     * @return Returns an ObservableList with predefined values.
     */
    public ObservableList<String> getCurrentOutputHeaderValues()
    {
        return outputHeaderValues;
    }

    /**
     * Retrives an observableList with the current input header values.
     *
     * @return Returns an ObservableList with the input header values.
     */
    public ObservableList<Configuration> getCurrentInputHeaderVaules()
    {
        return inputHeaderValues;
    }

    /**
     * Retrives an ObservableList with actions that the users has made.
     *
     * @return Returns a list of actions that the users has made.
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public ObservableList<ActionLog> getActionLogList() throws GUIException
    {
        try {
            actionLogList.setAll(logmanager.getAllActionLogs());
            return actionLogList;
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Retrives an ObservableList with all the task that have been saved.
     *
     * @return Returns a list of the saved tasks.
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public ObservableList<ConversionTask> getAllTasks() throws GUIException
    {
        try 
        {
            taskList.setAll(convertmanager.getAllTasks());
            return taskList;
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Moves up a header value on the listview contains input header values.
     *
     * @param selectedItem
     */
    public void moveInputUp(Configuration selectedItem)
    {
        int index = inputHeaderValues.indexOf(selectedItem);
        int nextIndex = index - 1;

        if (nextIndex > -1)
        {
            Collections.swap(inputHeaderValues, index, nextIndex);
        }
    }

    /**
     * Moves down a header value on the listview contains input header values.
     *
     * @param selectedItem
     */
    public void moveInputDown(Configuration selectedItem)
    {
        int index = inputHeaderValues.indexOf(selectedItem);
        int nextIndex = index + 1;

        if (nextIndex < inputHeaderValues.size())
        {
            Collections.swap(inputHeaderValues, index, nextIndex);
        }
    }

    /**
     * Moves a header value from headervaluesList to inputHeaderValuesList.
     *
     * @param selectedHeader
     */
    public void addInput(Configuration selectedHeader)
    {
        inputHeaderValues.add(selectedHeader);
        headerValues.remove(selectedHeader);
    }

    /**
     * Removes a header value from inputHeaderValuesList and add it to
     * headerValueList.
     *
     * @param selectedItem
     */
    public void removeInput(Configuration selectedItem)
    {
        if (!selectedItem.isStaticValue())
        {
            inputHeaderValues.remove(selectedItem);
            headerValues.add(selectedItem);
        } else
        {
            inputHeaderValues.remove(selectedItem);
        }
    }

    /**
     * Validate whether or not the Email is in the Database.
     *
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public boolean validateLogin(String loginInfo) throws GUIException
    {
        try 
        {
            return usermanager.validateLogin(loginInfo);
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Logs actions in the database
     *
     * @param log
     */
    public void logAciton(ActionLog log)
    {
        logmanager.logAction(log);
    }

    /**
     * Saves a task to an ArrayList so that its convertable for later.
     *
     * @param taskName
     * @param filePath
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public void saveTask(String taskName, String filePath) throws GUIException
    {
        try 
        {
            List<Configuration> mapConfig = new ArrayList<>();
            
            for (String outputValue : outputHeaderValues)
            {
                int index = outputHeaderValues.indexOf(outputValue);
                
                Configuration con = inputHeaderValues.get(index);
                
                con.setNewValue(outputValue);
                
                mapConfig.add(con);
            }
            
            convertmanager.saveTask(taskName, filePath, mapConfig);
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Clears all input.
     */
    public void clearInput()
    {
        inputHeaderValues.clear();
    }

    /**
     * Clears all import.
     */
    public void clearImport()
    {
        headerValues.clear();
    }
    
    /**
     * This method converts a selectedTask to a JSON file
     * @param selectedTask
     * @param dir 
     * @throws shoreline.GUI.Model.Exception.GUIException 
     */
    public void convertToJSON(ConversionTask selectedTask, String dir) throws GUIException
    {
        try {
            int taskIndex = taskList.indexOf(selectedTask);
            ConversionTask updatedTask = taskList.get(taskIndex);
            updatedTask.changeSatusConverting();
            
            taskList.set(taskIndex, updatedTask);
            convertmanager.updateTaskStatus(updatedTask);
            
            if (convertmanager.convertToJSON(selectedTask, dir))
            {
                
                selectedTask.changeStatusConverted();
                taskList.set(taskIndex, selectedTask);
                convertmanager.updateTaskStatus(selectedTask);
            } else
            {
                selectedTask.changeStatusFailed();
                taskList.set(taskIndex, selectedTask);
                convertmanager.updateTaskStatus(selectedTask);
            }
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }

    }

    /**
     * Saves the map configuration to an ArrayList so that its convertable for later.
     * @param mapConfigName 
     * @throws shoreline.GUI.Model.Exception.GUIException 
     */
    public void saveMapConfig(String mapConfigName) throws GUIException
    {
        try {
            List<Configuration> mapConfig = new ArrayList<>();
            
            for (String outputValue : outputHeaderValues)
            {
                int index = outputHeaderValues.indexOf(outputValue);
                
                Configuration con = inputHeaderValues.get(index);
                
                con.setNewValue(outputValue);
                
                mapConfig.add(con);
                
            }
            MappingDesign mc = new MappingDesign(mapConfigName, mapConfig);
            
            convertmanager.saveMapConfig(mc);
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Retrives all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     * @throws shoreline.GUI.Model.Exception.GUIException
     */
    public List<MappingDesign> getAllMapDesigns() throws GUIException
    {
        try {
            return convertmanager.getAllMapDesigns();
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Create a thread that watches if there is any changes in this new directory. 
     * @param dir
     * @param name
     * @param selectedMap 
     * @throws shoreline.GUI.Model.Exception.GUIException 
     */
    public void createDirectoryWatcher(String dir, String name, MappingDesign selectedMap) throws GUIException
    {
        try {
            watchmanager.createDirectoryWatcher(dir, name, selectedMap);
        } catch (BLLException ex) 
        {
            throw new GUIException(ex.getMessage(), ex);
        }
    }

    /**
     * Add a new static value to the inputHeaderValuesList.
     * @param staticValueConfig 
     */
    public void addStaticValue(Configuration staticValueConfig)
    {
        inputHeaderValues.add(staticValueConfig);
    }

}
