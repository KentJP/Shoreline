/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.GUI.Model;

import java.io.File;
import shoreline.BLL.ConvertManager;

/**
 *
 * @author frederik
 */
public class Model {
    
    private ConvertManager cm = new ConvertManager();

    public void identifyFile(File selectedFile) 
    {
        cm.identifyFile(selectedFile);
    }
    
    
}
