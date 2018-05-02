/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author frederik
 */
public class XLSXReader implements StrategyFileReader
{
    
    private int rowLength;
    private DataFormatter formatter;
    private FileInputStream fis;
    private XSSFWorkbook wb;
    private  XSSFSheet sheet;
    
   
    @Override
    public HashMap<String, Integer> readProperties(File file) 
    {
        try 
        {
            formatter = new DataFormatter();
            fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
            
            sheet = wb.getSheetAt(0);
            
            rowLength = sheet.getLastRowNum() +1;
            
            
            int cellCounter = 0;
            
            
            HashMap<String, Integer> headerList = new HashMap<>();
            
            
            while(sheet.getRow(0).getCell(cellCounter) != null)
            {
                int dublicateCounter = 0;
                String cellValue = sheet.getRow(0).getCell(cellCounter).getStringCellValue();
                
                if(!headerList.containsKey(cellValue))
                {
                    headerList.put(cellValue, cellCounter);
                    
                } else
                {
                    boolean done = false;
                    
                    while(!done)
                    {
                        if(!headerList.containsKey(cellValue + ++dublicateCounter))
                        {
                            headerList.put(cellValue + dublicateCounter, cellCounter);
                            done = true;
                        }
                    }
                }
                cellCounter++;
            }
            return headerList;
            
            
        } catch (IOException ex) 
        {
            Logger.getLogger(XLSXReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<HashMap> extractData(HashMap<String, Integer> properties) 
    {
        
            List<HashMap> listProperties = new ArrayList<>();
                    
            for (int i = 1; i < rowLength; i++) 
            {
                
                HashMap<String,String> rowValue = new HashMap<>();
                        
                for (String propertyValue : properties.keySet()) 
                {
        
                    Cell cell = sheet.getRow(i).getCell(properties.get(propertyValue));
                    String cellValue = formatter.formatCellValue(cell);
                    
                    rowValue.put(propertyValue, cellValue);
                }
                listProperties.add(rowValue);
                
            }
            
            return listProperties;
    }
    
}
