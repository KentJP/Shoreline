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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import shoreline.BE.ActionLog;
import shoreline.BE.ConversionTask;
import shoreline.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author Frederik Tubæk
 */
public class TaskViewController implements Initializable {

    private Model model = new Model();
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    
    @FXML
    private TableView<ConversionTask> TaskTableView;
    @FXML
    private TableColumn<ConversionTask, String> taskNameColumn;
    @FXML
    private TableColumn<ConversionTask, String> statusColumn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        TaskTableView.getItems().setAll(model.getAllTasks());
        taskNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        TaskTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        configuringDirectoryChooser(directoryChooser);
    }    

    @FXML
    private void convertEvent(ActionEvent event) 
    {    
        File file = directoryChooser.showDialog(null);
        if(file != null)
        {
            String dir = file.getAbsolutePath();
            
            ConversionTask selectedTask = TaskTableView.getSelectionModel().getSelectedItem();
            
            model.convertToJSON(selectedTask, dir);
            ActionLog al = new ActionLog("Converted task: " + selectedTask.getName());
            model.logAciton(al);
        }
        
    }
    
        private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for DirectoryChooser
        directoryChooser.setTitle("Select Some Directories");
 
        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
    
}
