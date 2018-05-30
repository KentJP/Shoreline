/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BLL.Exception.BLLException;

/**
 *
 * @author frederik
 */
public class XLSXReader implements StrategyFileReader {

    /**
     * Reads the headers from a XLSX file 
     * @param file
     * @return Returns a list of headers
     * @throws shoreline.BLL.Exception.BLLException
     */
    @Override
    public List<Configuration> readProperties(File file) throws BLLException {
        try 
        {
            DataFormatter formatter = new DataFormatter();
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet sheet = wb.getSheetAt(0);

            int cellCounter = 0;

            List<Configuration> configurationList = new ArrayList<>();

            while (sheet.getRow(0).getCell(cellCounter) != null) {
                String cellValue = sheet.getRow(0).getCell(cellCounter).getStringCellValue();

                Configuration c = new Configuration(cellCounter, cellValue);
                configurationList.add(c);
                cellCounter++;
            }
            return configurationList;

        } catch (IOException ex) 
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    /**
     * Extracting data from a file and mapping it in a hashmap.
     * @param task
     * @return Returns a hashmap of extracted data
     * @throws shoreline.BLL.Exception.BLLException
     */
    @Override
    public List<HashMap> extractData(ConversionTask task) throws BLLException{
        try 
        {
            DataFormatter formatter = new DataFormatter();
            boolean succesFullRead = false;
            File inProcessFile = new File(task.getFilePath());
            FileInputStream fis = null;
            
            
            if(inProcessFile.exists())
            {
                while (!succesFullRead) {

                    try {
                        fis = new FileInputStream(task.getFilePath());
                        succesFullRead=true;
                    } catch (FileNotFoundException fnf) 
                    {
                        try 
                        {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            throw new BLLException("The conversion process has ended due to an error please try again", ex);
                        }
                    }
                }
            }else
            {
                throw new BLLException("Could not find the original file for " + task.getName());
            }
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet sheet = wb.getSheetAt(0);

            int rowLength = sheet.getLastRowNum() + 1;

            List<HashMap> listProperties = new ArrayList<>();

            for (int i = 1; i < rowLength; i++) 
            {

                HashMap<String, String> rowValue = new HashMap<>();

                for (Configuration config : task.getConfigurations()) 
                {
                    if (!config.isStaticValue()) 
                    {
                        Cell cell = sheet.getRow(i).getCell(config.getIndex());
                        String cellValue = formatter.formatCellValue(cell);

                        rowValue.put(config.getNewValue(), cellValue);
                    } else 
                    {
                        config.removeStaticIdentifier();
                        rowValue.put(config.getNewValue(), config.removeStaticIdentifier());
                    }
                }

                listProperties.add(rowValue);

            }
            return listProperties;
        } catch (IOException ex) 
        {
            throw new BLLException("Failed to read from file in task " + task.getName(), ex);
        }
      
    }

}
