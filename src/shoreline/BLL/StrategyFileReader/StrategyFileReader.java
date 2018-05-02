/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author frederik
 */
public interface StrategyFileReader 
{
    
    public HashMap<String, Integer> readProperties(File file);
    
    public List<HashMap> extractData(HashMap<String, Integer> properties);
            
    
    
    
}
