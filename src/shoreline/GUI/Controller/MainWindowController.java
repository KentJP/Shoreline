/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kent Juul
 */
public class MainWindowController implements Initializable
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
    private void submitAction(ActionEvent event)
    {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       imageView.setImage(new Image(getClass().getResourceAsStream("/res/shoreline.png")));
    }
    
}
