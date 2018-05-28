/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BLL.Exception.BLLException;

/**
 *
 * @author Frederik Tubæk
 */
public class CSVReaderTest {
    
    private ConversionTask testTask;
    private File csvTestFile = new File("test/shoreline/testDocuments/CSV_test.csv");
    
    
    
    public CSVReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
        CSVReader reader = new CSVReader();
        
        
        try 
        {
            List<Configuration> configurationList = configurationList = reader.readProperties(csvTestFile);
            
            for (Configuration configuration : configurationList) 
            {
                configuration.setNewValue("New Value test");
            }

            testTask = new ConversionTask(1, "Test Task", csvTestFile.getAbsolutePath(), "Ready to Convert", configurationList);
            
        } catch (BLLException ex) 
        {
            fail("Failed due to connection error");
        }
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readProperties method, of class CSVReader.
     */
    @Test
    public void testReadProperties() throws Exception 
    {
        System.out.println("readProperties");
        
        CSVReader instance = new CSVReader();
        
        List<Configuration> result = instance.readProperties(csvTestFile);
        
        for (Configuration configuration : result) 
        {
            if(configuration.getIndex() > 10 || configuration.getIndex() < 0)
            {
                fail("Index incement failed");
            }
            
        }
        
        assertTrue(result.size() == 10);
        
        
     
    }

    /**
     * Test of extractData method, of class CSVReader.
     */
    @Test
    public void testExtractData() throws Exception 
    {
        ConversionTask task = testTask;
        
        CSVReader instance = new CSVReader();
        
        List<HashMap> result = instance.extractData(task);
        
        assertTrue(result.size() == 5);     
        
        int rowLengthStandard = result.get(0).size();
        
        for (HashMap hashMap : result) 
        {
            if(hashMap.size() != rowLengthStandard)
            {
                fail("Not all rows are same length");
            }
            
        }
                
    }
    
}
