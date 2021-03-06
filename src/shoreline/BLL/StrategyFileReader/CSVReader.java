/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BLL.Exception.BLLException;

/**
 *
 * @author frederik
 */
public class CSVReader implements StrategyFileReader {

    /**
     * Reads the headers from a CSV file 
     * @param file
     * @return Returns a list of headers
     * @throws shoreline.BLL.Exception.BLLException
     */
    @Override
    public List<Configuration> readProperties(File file) throws BLLException {

        String filePath = file.getAbsolutePath();
        List<Configuration> configList = new ArrayList<>();
        String line = "";
        BufferedReader br = null;

        try 
        {
            br = new BufferedReader(new FileReader(filePath));

            line = br.readLine();
            {
                String[] headerRow = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                
                for (int i = 0; i < headerRow.length; i++) 
                {
                    String header = headerRow[i].replaceAll("\"", "");
                    int index = i;

                    Configuration c = new Configuration(index, header);
                    
                    configList.add(c);

                }
            }

        } catch (FileNotFoundException ex) 
        {
            throw new BLLException("Could not find the file " + file.getName(), ex);
            
        } catch (IOException ex) 
        {
            throw new BLLException("Failed to read from file " + file.getName(), ex);
        }

        return configList;

    }

    /**
     * Extracting data from a file and mapping it in a hashmap.
     * @param task
     * @return Returns a hashmap of extracted data
     * @throws shoreline.BLL.Exception.BLLException
     */
    @Override
    public List<HashMap> extractData(ConversionTask task) throws BLLException {
        List<HashMap> extractedData = new ArrayList<>();

        List<Configuration> configList = task.getConfigurations();

        File inProcessFile = new File(task.getFilePath());
        String filePath = task.getFilePath();
        String line = "";
        String skipHeaders = "";
        BufferedReader br = null;
        boolean succesFullRead = false;

        if (inProcessFile.exists()) 
        {
            while (!succesFullRead) 
            {
                try 
                {
                    br = new BufferedReader(new FileReader(filePath));
                    succesFullRead = true;
                    skipHeaders = br.readLine();

                    while ((line = br.readLine()) != null) 
                    {
                        HashMap<String, String> rowData = new HashMap<>();
                        String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                        
                        for (Configuration config : configList) 
                        {
                            String cellValue = row[config.getIndex()];
                            String newHeaderValue = config.getNewValue();

                            rowData.put(newHeaderValue, cellValue.replaceAll("\"", ""));
                        }
                        extractedData.add(rowData);
                        line = "";
                    }

                } catch (FileNotFoundException ex) 
                {
                    try 
                    {
                        Thread.sleep(50);
                        
                    } catch (InterruptedException ex1) 
                    {
                        throw new BLLException("The conversion process has ended due to an error please try again", ex1);
                    }
                } catch (IOException ex) 
                {
                    throw new BLLException("Failed to read from file in task " + task.getName(), ex);
                }
            }

        }else
        {
            throw new BLLException("Could not find the original file " + task.getName());
        }

        return extractedData;
    }

}
