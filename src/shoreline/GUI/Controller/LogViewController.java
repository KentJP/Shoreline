/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.apache.poi.hsmf.datatypes.PropertyValue;
import shoreline.BE.ActionLog;
import shoreline.GUI.Model.Exception.ExceptionDisplay;
import shoreline.GUI.Model.Exception.GUIException;
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
    
    private Model model;
    @FXML
    private JFXTextField searchTxtField;

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
            actionLogTableView.getItems().setAll(model.getActionLogList());
            
            firstNameColumn.setCellValueFactory(new PropertyValueFactory("fname"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory("lname"));
            emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
            dateColumn.setCellValueFactory(new PropertyValueFactory("date"));
            timeColumn.setCellValueFactory(new PropertyValueFactory("time"));
            actionColumn.setCellValueFactory(new PropertyValueFactory("action"));
            dateColumn.setSortType(TableColumn.SortType.DESCENDING);
            actionLogTableView.getSortOrder().add(dateColumn);
            
          
            
           
            
        } catch (GUIException ex)
        {
            ExceptionDisplay.displayException(ex);        
        }
      
    }    

    @FXML
    private void searchEvent(KeyEvent event) 
    {
        FilteredList filter = new FilteredList(actionLogTableView.getItems(), e -> true);
        searchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate((Predicate<? super ActionLog>) (ActionLog log) -> 
            {
                if (newValue.isEmpty() || newValue == null) 
                {
                    return true;
                } else if (log.getFname().toLowerCase().contains(newValue.toLowerCase()) ||
                            log.getLname().toLowerCase().contains(newValue.toLowerCase()) ||
                            log.getEmail().toLowerCase().contains(newValue.toLowerCase()) ||
                            log.getDate().toLowerCase().contains(newValue.toLowerCase()) ||
                            log.getTime().toLowerCase().contains(newValue.toLowerCase()) ||
                            log.getAction().toLowerCase().contains(newValue.toLowerCase()))
                {
                    return true;    
                }

                return false;
            });
            SortedList sort = new SortedList(filter);
            sort.comparatorProperty().bind(actionLogTableView.comparatorProperty());

            actionLogTableView.setItems(sort);
        });

    }
    
}
