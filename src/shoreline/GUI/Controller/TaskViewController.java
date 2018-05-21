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
        TaskTableView.setItems(model.getAllTasks());
        taskNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory("status"));
        TaskTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
       // setupStatusColumnColor();
        configuringDirectoryChooser(directoryChooser);
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
                    String dir = file.getAbsolutePath();
            
                    model.convertToJSON(selectedTask, dir);
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
     
            
            
            ActionLog al = new ActionLog("Converted task: " + selectedTask.getName());
            model.logAciton(al);    
    }
    
        private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
       
        directoryChooser.setTitle("Select a Directorie");
 
       
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    private void setupStatusColumnColor() 
    {
        statusColumn.setCellFactory(column -> {
            return new TableCell<ConversionTask, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    setText(empty ? "" : getItem().toString());
                    setGraphic(null);

                    TableRow<ConversionTask> currentRow = getTableRow();

                   if (!isEmpty()) 
                   {
                    
                     if(item.equals("Converted"))
                        currentRow.setStyle("-fx-background-color:lightgreen");
                    else if(item.equals("Failed to convert"))
                        currentRow.setStyle("-fx-background-color:lightred");
                    
                }
            }
        };
    });              
    }
    
}
