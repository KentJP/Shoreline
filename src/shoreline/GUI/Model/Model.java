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
public class Model
{

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

    /**
     * Reading properties of a selected file
     *
     * @param selectedFile
     * @return Properties of a selected file
     */
    public void readProperties(File selectedFile)
    {
        headerValues.setAll(convertmanager.readProperties(selectedFile));
    }

    /**
     * Get an observableList with the current header values.
     *
     * @return Returns an ObservableList with the header values.
     */
    public ObservableList<Configuration> getCurrentHeaderValues()
    {
        return headerValues;
    }

    /**
     * Get an ObservableList with predefined values
     *
     * @return Returns an ObservableList with predefined values.
     */
    public ObservableList<String> getCurrentOutputHeaderValues()
    {
        return outputHeaderValues;
    }

    /**
     * Get an observableList with the current input header values.
     *
     * @return Returns an ObservableList with the input header values.
     */
    public ObservableList<Configuration> getCurrentInputHeaderVaules()
    {
        return inputHeaderValues;
    }

    /**
     * Get an ObservableList with actions that the users has made.
     *
     * @return Returns a list of actions that the users has made.
     */
    public ObservableList<ActionLog> getActionLogList()
    {
        actionLogList.setAll(logmanager.getAllActionLogs());
        return actionLogList;
    }

    /**
     * Get an ObservableList with all the task that have been saved.
     *
     * @return Returns a list of the saved tasks.
     */
    public ObservableList<ConversionTask> getAllTasks()
    {
        taskList.setAll(convertmanager.getAllTasks());
        return taskList;
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
     * Validate wheater or not the Email is in the Database.
     *
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     */
    public boolean validateLogin(String loginInfo)
    {
        return usermanager.validateLogin(loginInfo);
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
     * Saves a task to an ArrayList
     *
     * @param taskName
     * @param filePath
     */
    public void saveTask(String taskName, String filePath)
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
     * Converts a selectedTask to JSON
     * @param selectedTask
     * @param dir 
     */
    public void convertToJSON(ConversionTask selectedTask, String dir)
    {
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

    }

    /**
     * Saves the map configuration to an ArrayList.
     * @param mapConfigName 
     */
    public void saveMapConfig(String mapConfigName)
    {
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
    }

    /**
     * Get all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     */
    public List<MappingDesign> getAllMapDesigns()
    {
        return convertmanager.getAllMapDesigns();
    }
    
    /**
     * Create a thread that watches if there is any changes in this new directory. 
     * @param dir
     * @param name
     * @param selectedMap 
     */
    public void createDirectoryWatcher(String dir, String name, MappingDesign selectedMap)
    {
        watchmanager.createDirectoryWatcher(dir, name, selectedMap);
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
