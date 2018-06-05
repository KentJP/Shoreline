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
import shoreline.BLL.Exception.BLLException;

/**
 *
 * @author frederik
 */
public interface StrategyFileReader 
{
    
    /**
     * Reads the headers from a selected file 
     * @param file
     * @return Returns a list of headers
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<Configuration> readProperties(File file) throws BLLException;
    
    /**
     * Extracting data from a file and mapping it in a hashmap.
     * @param task
     * @return Returns a hashmap of extracted data
     * @throws shoreline.BLL.Exception.BLLException
     */
    public List<HashMap> extractData(ConversionTask task) throws BLLException;
            
    
    
    
}
