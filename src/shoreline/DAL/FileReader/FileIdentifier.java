/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL.FileReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Kent Juul
 */
public class FileIdentifier
{
    
    private StrategyFileReader sfr;
    
    public void identifyFile(File file)
    {
        String fileType = FilenameUtils.getExtension(file.getPath());
        
        if(fileType.equals("xlsx"))
        {
            sfr = new XLSXReader();
        }
        
        if(sfr != null)
        {
            sfr.readFile(file);
        }
     
    }
    
}
