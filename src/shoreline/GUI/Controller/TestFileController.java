/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML
    private ListView<String> InputDataList;
    @FXML
    private ListView<String> OutputDataList;
    
    private String selectedHeader;
    @FXML
    private JFXTextField hardValueTxtField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    

    @FXML
    private void chooseFileEvent(ActionEvent event) 
    {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile != null)
        {
            model.identifyFile(selectedFile);
            
        }
        
        displayImportData();
        
    }
    
    private void displayConvertConfig()
    {

    }

    private void displayImportData() 
    {
        importDataList.getItems().setAll(model.getCurrentHeaderValues());
        OutputDataList.getItems().setAll(model.getCurrentOutputHeaderValues());
        
    }

    @FXML
    private void startDrag(MouseEvent event) 
    {
        selectedHeader = importDataList.getSelectionModel().getSelectedItem();
        System.out.println(selectedHeader);
    }

    @FXML
    private void endDrop(MouseDragEvent event) 
    {
        InputDataList.getItems().add(selectedHeader);
    }

    @FXML
    private void addImportData(ActionEvent event) 
    {
        if(!importDataList.getSelectionModel().getSelectedItem().isEmpty())
        {
            String selectedHeader = importDataList.getSelectionModel().getSelectedItem();
            
            InputDataList.getItems().add(selectedHeader);
        }
    }

    @FXML
    private void addHardValue(ActionEvent event) 
    {
        String hardValue = "\"" + hardValueTxtField.getText() +    "\"";
        InputDataList.getItems().add(hardValue);
        hardValueTxtField.clear();
        
    }

    @FXML
    private void moveInputUp(ActionEvent event) {
    }

    @FXML
    private void moveInputDown(ActionEvent event) {
    }


    
    
    
}
