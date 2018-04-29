/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL;

import java.io.File;
import shoreline.DAL.FileReader.FileIdentifier;

/**
 *
 * @author Kent Juul
 */
public class ConvertManager
{
    private FileIdentifier fi = new FileIdentifier();

    public void identifyFile(File selectedFile) 
    {
        fi.identifyFile(selectedFile);
    }
    
}
