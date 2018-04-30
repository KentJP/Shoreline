/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Kent Juul
 */
public class Main extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/shoreline/GUI/View/MainWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        //scene.getStylesheets().add("/shoreline/res/Mainwindow.css");
        stage.show();
        stage.setTitle("Shoreline login");
        
         
        
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
