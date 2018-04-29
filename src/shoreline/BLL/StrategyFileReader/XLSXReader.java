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
    
    private HashMap<String, Integer> headerList = new HashMap<>();
    
    private List<HashMap> listProperties = new ArrayList<>();

    @Override
    public List<HashMap> readFile(File file)
    {
    
        try 
        {
            DataFormatter formatter = new DataFormatter();
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            
            XSSFSheet sheet = wb.getSheetAt(0);
            
            int rowLength = sheet.getLastRowNum() +1;
            
            
            int cellCounter = 0;
            
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
            
            
            for (int i = 1; i < rowLength; i++) 
            {
                
                HashMap<String,String> rowValue = new HashMap<>();
                        
                for (String propertyValue : headerList.keySet()) 
                {
        
                    Cell cell = sheet.getRow(i).getCell(headerList.get(propertyValue));
                    String cellValue = formatter.formatCellValue(cell);
                    
                    rowValue.put(propertyValue, cellValue);
                }
                listProperties.add(rowValue);
                
            }
            
        
            
            
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(XLSXReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProperties;
        

    }
    
}
