/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shoreline.BE.ActionLog;
import shoreline.BE.Configuration;
import shoreline.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author frederik
 */
public class MainViewController implements Initializable {

    
    private Model model = new Model();
    private File currentFile;

    private String selectedHeader;
    
    @FXML
    private ListView<Configuration> importListView;
    @FXML
    private ListView<Configuration> inputListView;
    @FXML
    private ListView<String> outputListView;
    @FXML
    private AnchorPane naviPane;
    @FXML
    private AnchorPane importPane;
    @FXML
    private JFXButton importDataBtn;
    @FXML
    private JFXButton viewLogBtn;
    @FXML
    private JFXButton viewTaskBtn;
    @FXML
    private JFXButton addBtn;
    @FXML
    private JFXButton moveDownBtn;
    @FXML
    private JFXButton moveUpBtn;
    @FXML
    private JFXButton saveTaskBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setListViewCellFactory();

        importListView.setItems(model.getCurrentHeaderValues());
        inputListView.setItems(model.getCurrentInputHeaderVaules());
        outputListView.setItems(model.getCurrentOutputHeaderValues());


        
       
    
   
    }    

    @FXML
    private void chooseFileEvent(ActionEvent event) 
    {
        FileChooser fc = new FileChooser();
        currentFile = fc.showOpenDialog(null);
        
        if(currentFile != null)
        {
            model.readProperties(currentFile);
            
        }
    }

    @FXML
    private void addInput(ActionEvent event) 
    {
       
        if(importListView.getSelectionModel().getSelectedItem() != null)
        {
            Configuration selectedHeader = importListView.getSelectionModel().getSelectedItem();
            
            model.addInput(selectedHeader);
        }
    }


    private void removeInput(ActionEvent event) 
    {
        Configuration selectedItem = inputListView.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null)
        {
            model.removeInput(selectedItem);
        }
    }
    
    
    @FXML
    private void moveInputUp(ActionEvent event) 
    {
        Configuration selectedItem = inputListView.getSelectionModel().getSelectedItem();
        model.moveInputUp(selectedItem);
        inputListView.getSelectionModel().select(selectedItem);        
    }

    @FXML
    private void moveInputDown(ActionEvent event) 
    {
        Configuration selectedItem = inputListView.getSelectionModel().getSelectedItem();
        model.moveInputDown(selectedItem);
        inputListView.getSelectionModel().select(selectedItem);

    }
    
    @FXML
    private void saveTaskEvent(ActionEvent event) 
    {
        if(inputListView.getItems().size() == outputListView.getItems().size())
        {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Task Name");
            dialog.setHeaderText("Please Enter a name for your Task");
            dialog.setContentText("Task name: ");
            dialog.setGraphic(null);
        
            Optional<String> result = dialog.showAndWait();
        
            if(result.isPresent())
            {
                model.saveTask(result.get(),currentFile.getAbsolutePath()); 
                ActionLog a = new ActionLog("saved Task: " + result.get());
                model.logAciton(a);
          
                model.clearInput();
                model.clearImport();
            }
            
        }else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Insufficient amount of mappings");
            alert.setHeaderText(null);
            alert.setContentText("Please map every Output header to a given Import header");

            alert.showAndWait();
            
        }
        
        
    }

    @FXML
    private void viewLogEvent(ActionEvent event) 
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/shoreline/GUI/View/LogView.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/shoreline/res/Logview.css");
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Shoreline login");
        } catch (IOException ex) 
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setListViewCellFactory() 
    {
        importListView.setCellFactory(param -> new ListCell<Configuration>() {
            @Override
            protected void updateItem(Configuration item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getOldValue()== null) {
                    setText(null);
                } else {
                    setText(item.getOldValue());
                }
            }
        });    
        inputListView.setCellFactory(param -> new ListCell<Configuration>() {
            @Override
            protected void updateItem(Configuration item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getOldValue()== null) {
                    setText(null);
                } else {
                    setText(item.getOldValue());
                }
            }
        });    
    }

    @FXML
    private void viewTasksEvent(ActionEvent event) 
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/shoreline/GUI/View/TaskView.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/shoreline/res/TaskView.css");
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Shoreline login");
            
        } catch (IOException ex) 
        {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }



    
    
    
}
