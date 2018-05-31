/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model.Exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author frederik
 */
public class ExceptionDisplay 
{
    
    /**
     * Shows an error message to the user.
     * @param ex
     */
    public static void displayException(GUIException ex)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An Error has Occurred");
        alert.setContentText(ex.getMessage());

        alert.showAndWait();
    }
    
}
