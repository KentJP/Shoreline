/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import shoreline.BE.ActionLog;
import shoreline.BE.ConversionTask;
import shoreline.GUI.Model.Exception.ExceptionDisplay;
import shoreline.GUI.Model.Exception.GUIException;
import shoreline.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author Frederik Tubæk
 */
public class TaskViewController implements Initializable {

    private Model model;
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    
    @FXML
    private TableView<ConversionTask> TaskTableView;
    @FXML
    private TableColumn<ConversionTask, String> taskNameColumn;
    @FXML
    private TableColumn<ConversionTask, String> statusColumn;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            model = new Model();
            TaskTableView.setItems(model.getAllTasks());
            taskNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
            statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
            TaskTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            
            configuringDirectoryChooser(directoryChooser);
        } catch (GUIException ex) 
        {
            ExceptionDisplay.displayException(ex);
        }
    }    

    @FXML
    private void convertEvent(ActionEvent event) 
    {    

        ConversionTask selectedTask = TaskTableView.getSelectionModel().getSelectedItem();
            
        if(selectedTask.getStatus().equals("Ready to Convert"))
            {
                File file = directoryChooser.showDialog(null);
                if(file != null)
                {
                    try 
                    {
                        String dir = file.getAbsolutePath();
                        
                        model.convertToJSON(selectedTask, dir);
                    } catch (GUIException ex) 
                    {
                        ExceptionDisplay.displayException(ex);
                    }
                }
            }
            else
            {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Cannot convert Task");
                alert.setHeaderText(null);
                alert.setContentText("This task is not \"Ready to Convert\"");

                alert.showAndWait();
            }
     
            
            
            ActionLog al = new ActionLog("Started manual conversion on Task : " + selectedTask.getName());
            model.logAciton(al);    
    }
    
        private void configuringDirectoryChooser(DirectoryChooser directoryChooser) 
        {
            directoryChooser.setTitle("Select a Directorie");
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
}



