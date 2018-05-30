/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import shoreline.GUI.Model.Model;
import shoreline.GUI.Model.Exception.ExceptionDisplay;
import shoreline.GUI.Model.Exception.GUIException;

/**
 * FXML Controller class
 *
 * @author Kent Juul
 */
public class LoginViewController implements Initializable
{

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private ImageView imageView;
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXTextField userTxtField;
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Label loginErrorLbl;    
  
    
    private Model model;

    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try 
        {
            model = new Model();
        } catch (GUIException ex) 
        {
            ExceptionDisplay.displayException(ex);
        }
        
        loginErrorLbl.setVisible(false);
        imageView.setImage(new Image(getClass().getResourceAsStream("/shoreline/res/shoreline.png")));
        
  
    }
    
    
    
    
    @FXML
    private void submitAction(ActionEvent event) throws IOException, SQLException
    {
        try 
        {
            String loginInfo = userTxtField.getText();
            
            if(model.validateLogin(loginInfo))
            {
                Parent root = FXMLLoader.load(getClass().getResource("/shoreline/GUI/View/MainView.fxml"));
                
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/shoreline/res/MainView.css");
                
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Shoreline conversion");
                
                Stage closeStage = (Stage) submitBtn.getScene().getWindow();
                closeStage.close();
            }
            else
            {
                loginErrorLbl.setVisible(true);
            }
        } catch (GUIException ex) 
        {
            ExceptionDisplay.displayException(ex);
        }
    }    
}
