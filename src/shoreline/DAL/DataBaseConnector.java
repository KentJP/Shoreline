/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author Kent Juul
 */
public class DataBaseConnector
{
 

    private SQLServerDataSource dataSource;

    /**
     * Connecting to the database
     * @throws IOException
     */
    public DataBaseConnector() throws IOException
    {
       
        dataSource = new SQLServerDataSource();

        dataSource.setServerName("EASV-DB2");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("Shoreline_LiquidGold");
        dataSource.setUser("CS2017A_5_java");
        dataSource.setPassword("javajava");
        
    }
        
    /**
     * This method gets the connection with the database
     * @return 
     * @returns dataSource from DataBaseConnector
     * @throws SQLServerException 
     */    
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
          
    
    
    
    
}
