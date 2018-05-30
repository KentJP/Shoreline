/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.DAL.DataBaseConnector;
import shoreline.DAL.Exeption.DALException;

/**
 *
 * @author Frederik Tubæk
 */
public class ConvertDAO {
    
     
    DataBaseConnector dbconnector;
          
    /**
     * This is the constructor of this class.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public ConvertDAO() throws DALException 
    {
        try 
        {
            dbconnector = new DataBaseConnector();
        } 
        catch (IOException ex) 
        {
            throw new DALException("Could not connect to the database", ex);
        }
    }

    /**
     * Saves the task and its configuration to the database
     * @param conversionTask 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    public void saveTask(ConversionTask conversionTask) throws DALException 
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
             throw new DALException("Could not connect to the database", ex);
        }

    }

    /**
     * Gets a list of all the tasks in the database.
     * @return Returns a list of all the tasks from the database.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public List<ConversionTask> getAllTasks() throws DALException 
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
                String status = rs.getString("status");
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
                
                ConversionTask task = new ConversionTask(id, name, filePath, status, configList);
                taskList.add(task);
            }
          return taskList;          
            
            
        } catch (SQLException ex) 
        {
            throw new DALException("Could not connect to the database", ex);
        }
   
    }

    /**
     * Updating the status of CoversionTask in the database.
     * @param updatedTask 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    public void updateTaskStatus(ConversionTask updatedTask) throws DALException 
    {
         try(Connection con = dbconnector.getConnection())
         {
             String sql = "UPDATE ConversionTask SET status = ? WHERE id = ?";
             
             PreparedStatement statement = con.prepareStatement(sql);
             
             statement.setString(1, updatedTask.getStatus());
             statement.setInt(2, updatedTask.getId());
             
             statement.execute();
             
             
             
         } catch (SQLException ex) 
         {
            throw new DALException("Could not connect to the database", ex);
        }
        
    }

    /**
     * Saves the map configuration and design to the database. 
     * @param mc
     * @throws shoreline.DAL.Exeption.DALException
     */
    public void saveMapConfig(MappingDesign mc) throws DALException 
    {
         try(Connection con = dbconnector.getConnection())
         {            
            String sqlStatement = "INSERT INTO MappingDesign VALUES(?)";
         
        
            PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, mc.getName());
            

            int mapID = 0;
            
            if(statement.executeUpdate() == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next(); 
                mapID = rs.getInt(1);
            }
           
             for (Configuration configuration : mc.getMapConfig()) 
             {
                String sql = "INSERT INTO MapConfiguration VALUES(?, ?, ?, ?)"; 
                
                PreparedStatement statement2 = con.prepareStatement(sql);
                
                statement2.setInt(1, mapID);
                statement2.setInt(2, configuration.getIndex());
                statement2.setString(3, configuration.getOldValue());
                statement2.setString(4, configuration.getNewValue());
                
                statement2.execute();
                
             }
             
         } catch (SQLException ex) 
         {
            throw new DALException("Could not connect to the database", ex);
        }
    }

    /**
     * Get all map designs and configurations from the database.
     * @return Returns a List of MappingDesigns and configurations from the database.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public List<MappingDesign> getAllMapDesigns() throws DALException 
    {
        try(Connection con = dbconnector.getConnection())
        {
            String sql = "SELECT * FROM MappingDesign";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            List<MappingDesign> mapList = new ArrayList<>();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                List<Configuration> configList = new ArrayList<>();
                
                String sql2 = "SELECT * FROM MapConfiguration WHERE mapId = ?";
                
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
                
                MappingDesign md = new MappingDesign(id, name, configList);
                mapList.add(md);
            }
          return mapList;          
            
            
        } catch (SQLException ex) 
        {
            throw new DALException("Could not connect to the database", ex);
        }
    }

}
