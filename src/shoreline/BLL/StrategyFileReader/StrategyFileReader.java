/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;

/**
 *
 * @author frederik
 */
public interface StrategyFileReader 
{
    
    public List<Configuration> readProperties(File file);
    
    public List<HashMap> extractData(ConversionTask task);
            
    
    
    
}
