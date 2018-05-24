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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;

/**
 *
 * @author frederik
 */
public class XLSXReader implements StrategyFileReader {

    /**
     *
     * @param file
     * @return
     */
    @Override
    public List<Configuration> readProperties(File file) {
        try {
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

        } catch (IOException ex) {
            Logger.getLogger(XLSXReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param task
     * @return
     */
    @Override
    public List<HashMap> extractData(ConversionTask task){
        try {
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
                    } catch (FileNotFoundException fnf) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(XLSXReader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }else
            {
            }
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet sheet = wb.getSheetAt(0);

            int rowLength = sheet.getLastRowNum() + 1;

            List<HashMap> listProperties = new ArrayList<>();

            for (int i = 1; i < rowLength; i++) {

                HashMap<String, String> rowValue = new HashMap<>();

                for (Configuration config : task.getConfigurations()) {
                    if (!config.isStaticValue()) {
                        Cell cell = sheet.getRow(i).getCell(config.getIndex());
                        String cellValue = formatter.formatCellValue(cell);

                        rowValue.put(config.getNewValue(), cellValue);
                    } else {
                        rowValue.put(config.getNewValue(), config.getOldValue());
                    }
                }

                listProperties.add(rowValue);

            }
            return listProperties;
        } catch (IOException ex) {
            Logger.getLogger(XLSXReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
