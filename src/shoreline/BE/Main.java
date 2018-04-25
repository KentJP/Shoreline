/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BE;

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
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/MainWindow.fxml"));
        
        Scene scene = new Scene(root,400,400);
        
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add("/GUI/res/Mainwindow.css");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Shoreline login");
        
       
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
    }
    
}
