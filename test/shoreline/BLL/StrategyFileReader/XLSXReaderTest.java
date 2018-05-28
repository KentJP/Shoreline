/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.StrategyFileReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
public class XLSXReaderTest {
    
    private ConversionTask testTask;
    private File xlsxTestFile = new File("test/shoreline/testDocuments/XLSX_test.xlsx");
    
    
    public XLSXReaderTest() 
    {
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
        XLSXReader reader = new XLSXReader();
        
        
        try 
        {
            List<Configuration> configurationList = configurationList = reader.readProperties(xlsxTestFile);
            
            for (Configuration configuration : configurationList) 
            {
                configuration.setNewValue("New Value test");
            }

            testTask = new ConversionTask(1, "Test Task", xlsxTestFile.getAbsolutePath(), "Ready to Convert", configurationList);
            
        } catch (BLLException ex) 
        {
            fail("Failed due to connection error");
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readProperties method, of class XLSXReader.
     */
    @Test
    public void testReadProperties() throws Exception {
        System.out.println("readProperties");
        
        XLSXReader instance = new XLSXReader();
        
        List<Configuration> result = instance.readProperties(xlsxTestFile);
        
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
     * Test of extractData method, of class XLSXReader.
     */
    @Test
    public void testExtractData() throws Exception 
    {
        ConversionTask task = testTask;
        
        XLSXReader instance = new XLSXReader();
        
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
