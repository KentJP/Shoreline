/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<String> importDataTableView;
    @FXML
    private TableColumn<String, String> importDataTableColumn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        
        importDataTableView.setItems(model.getCurrentHeaderValues());
        importDataTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }

    private void displayImportData() 
    {

        
    }
    
    
    
    
}
