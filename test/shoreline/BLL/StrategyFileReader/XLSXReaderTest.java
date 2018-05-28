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

/**
 *
 * @author Frederik Tubæk
 */
public class XLSXReaderTest {
    
    public XLSXReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
        File file = null;
        XLSXReader instance = new XLSXReader();
        List<Configuration> expResult = null;
        List<Configuration> result = instance.readProperties(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractData method, of class XLSXReader.
     */
    @Test
    public void testExtractData() throws Exception {
        System.out.println("extractData");
        ConversionTask task = null;
        XLSXReader instance = new XLSXReader();
        List<HashMap> expResult = null;
        List<HashMap> result = instance.extractData(task);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
