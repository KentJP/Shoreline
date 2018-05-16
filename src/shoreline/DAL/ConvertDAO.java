/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;

/**
 *
 * @author Frederik Tubæk
 */
public class ConvertDAO {
    
     
    DataBaseConnector dbconnector;
     
    private boolean wasSuccessfull;
     
     
     
     

    public ConvertDAO() 
    {
        try 
        {
            dbconnector = new DataBaseConnector();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveTask(ConversionTask conversionTask) 
    {
        try(Connection con = dbconnector.getConnection())
        {
            String sqlStatement = "INSERT INTO ConversionTask VALUES(?, ?, ?)";
        
            PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, conversionTask.getName());
            statement.setString(2, conversionTask.getFilePath());
            statement.setString(3, conversionTask.getStatus());

            int taskID = 0;
            if(statement.executeUpdate() == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next(); 
                taskID = rs.getInt(1);
            }
            
            for (Configuration configuration : conversionTask.getConfigurations()) {
                
            
            String sqlStatement2 = "INSERT INTO Configuration VALUES(?, ?, ?, ?)";
                
                PreparedStatement statement2 = con.prepareStatement(sqlStatement2);
                
                statement2.setInt(1, taskID);
                statement2.setInt(2, configuration.getIndex());
                statement2.setString(3, configuration.getOldValue());
                statement2.setString(4, configuration.getNewValue());
                
                statement2.execute();
            }
        }    
        catch (SQLException ex) 
        {
             Logger.getLogger(ConvertDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<ConversionTask> getAllTasks() 
    {
        try(Connection con = dbconnector.getConnection())
        {
            String sql = "SELECT * FROM ConversionTask";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            List<ConversionTask> taskList = new ArrayList<>();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String filePath = rs.getString("filePath");
                List<Configuration> configList = new ArrayList<>();
                
                String sql2 = "SELECT * FROM Configuration WHERE taskId = ?";
                
                PreparedStatement statement = con.prepareStatement(sql2);
                
                statement.setInt(1, id);
                
                ResultSet configRS = statement.executeQuery();
                
                while(configRS.next())
                {
                    int index = configRS.getInt("propertyIndex");
                    String oldValue = configRS.getString("oldValue");
                    String newValue = configRS.getString("newValue");
                    
                    Configuration c = new Configuration(index, oldValue, newValue);
                    
                    configList.add(c);
                }
                
                ConversionTask task = new ConversionTask(id, name, filePath, configList);
                taskList.add(task);
            }
          return taskList;          
            
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(ConvertDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;    
    }


}
