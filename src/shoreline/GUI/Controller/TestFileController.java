/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shoreline.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author frederik
 */
public class TestFileController implements Initializable {

    
    private Model model = new Model();
    private List<HashMap> sheetInput = new ArrayList<>();
    @FXML
    private ListView<String> importDataList;

    
    private String selectedHeader;
    @FXML
    private JFXTextField hardValueTxtField;
    @FXML
    private ListView<String> inputDataList;
    @FXML
    private ListView<String> outputDataList;
    @FXML
    private JFXButton moveInputDownBtn;
    @FXML
    private JFXButton moveInputUpBtn;
    @FXML
    private JFXButton selectImportBtn;
    @FXML
    private JFXButton convertBtn;
    @FXML
    private JFXButton logBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        importDataList.setItems(model.getCurrentHeaderValues());
        inputDataList.setItems(model.getCurrentInputHeaderVaules());
        outputDataList.setItems(model.getCurrentOutputHeaderValues());
        
    
   
    }    

    @FXML
    private void chooseFileEvent(ActionEvent event) 
    {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile != null)
        {
            model.readProperties(selectedFile);
            
        }
        
        
    }

    @FXML
    private void addInput(ActionEvent event) 
    {
        if(!importDataList.getSelectionModel().getSelectedItem().isEmpty())
        {
            String selectedHeader = importDataList.getSelectionModel().getSelectedItem();
            
            model.addInput(selectedHeader);
        }
    }

    @FXML
    private void addHardValue(ActionEvent event) 
    {
        if(inputDataList != null)
        {
            String hardValue = "\"" + hardValueTxtField.getText() +    "\"";
            model.addHardValue(hardValue);
            hardValueTxtField.clear();
        }
        
    }

    @FXML
    private void removeInput(ActionEvent event) 
    {
        String selectedItem = inputDataList.getSelectionModel().getSelectedItem();
        
        if(selectedItem != null)
        {
            model.removeInput(selectedItem);
        }
    }
    
    
    @FXML
    private void moveInputUp(ActionEvent event) 
    {
        String selectedItem = inputDataList.getSelectionModel().getSelectedItem();
        model.moveInputUp(selectedItem);
        inputDataList.getSelectionModel().select(selectedItem);        
    }

    @FXML
    private void moveInputDown(ActionEvent event) 
    {
        String selectedItem = inputDataList.getSelectionModel().getSelectedItem();
        model.moveInputDown(selectedItem);
        inputDataList.getSelectionModel().select(selectedItem);
   
        
     
        
    }

    @FXML
    private void convertData(ActionEvent event) 
    { 
        model.extractData();
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
            Logger.getLogger(TestFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    
    
    
}
