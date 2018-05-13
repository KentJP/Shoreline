/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.hsmf.datatypes.PropertyValue;
import shoreline.BE.ActionLog;
import shoreline.GUI.Model.Model;

/**
 * FXML Controller class
 *
 * @author Frederik Tubæk
 */
public class LogViewController implements Initializable {

    @FXML
    private TableView<ActionLog> actionLogTableView;
    @FXML
    private TableColumn<ActionLog, String> firstNameColumn;
    @FXML
    private TableColumn<ActionLog, String> lastNameColumn;
    @FXML
    private TableColumn<ActionLog, String> emailColumn;
    @FXML
    private TableColumn<ActionLog, String> dateColumn;
    @FXML
    private TableColumn<ActionLog, String> timeColumn;
    @FXML
    private TableColumn<ActionLog, String> actionColumn;
    
    private Model model = new Model();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        actionLogTableView.getItems().setAll(model.getActionLogList());
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory("fname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory("lname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
        actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
                
        
      
    }    
    
}
