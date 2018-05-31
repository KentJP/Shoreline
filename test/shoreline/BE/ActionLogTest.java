/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Frederik Tubæk
 */
public class ActionLogTest {
    
    private Date todaysDate = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); 
    
    private Date currentTime = new Date();
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
    private ActionLog testLog = new ActionLog("Test Log");
    
    public ActionLogTest() 
    {
        
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
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
     * Test of getId method, of class ActionLog.
     */

    /**
     * Test of getFname method, of class ActionLog.
     */
    @Test
    public void testGetFname() 
    {
        String expResult ="Unidentified First name";
        String result = testLog.getFname();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getLname method, of class ActionLog.
     */
    @Test
    public void testGetLname() 
    {
        String expResult = "Unidentified Last name";
        String reuslt = testLog.getLname();
        
        assertEquals(expResult, reuslt);

    }

    /**
     * Test of getEmail method, of class ActionLog.
     */
    @Test
    public void testGetEmail() 
    {
        String expResult = "Unidentified Email";
        String result = testLog.getEmail();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getDate method, of class ActionLog.
     */
    @Test
    public void testGetDate() {
        ActionLog instance = new ActionLog("Test getDate");
        
        String expResult = dateFormat.format(todaysDate);
        String result = instance.getDate();
        
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class ActionLog.
     */
    @Test
    public void testGetTime() {
        ActionLog instance = new ActionLog("Test getTime");
        
        String expResult = timeFormat.format(currentTime);
        String result = instance.getTime();
        
        
        assertEquals(expResult, result);
    }

    
}
