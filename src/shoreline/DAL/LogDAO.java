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
import shoreline.BE.ActionLog;
import shoreline.DAL.Exeption.DALException;

/**
 *
 * @author Frederik Tubæk
 */
public class LogDAO {

    DataBaseConnector dbconnector;
    
    /**
     * This is the constructor of this class.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public LogDAO() throws DALException
    {
        try 
        {
            dbconnector = new DataBaseConnector();
        } 
        catch (IOException ex) 
        {
            throw new DALException(ex.getMessage(), ex);
        }
    }
    
    
    
    /**
     * Logs actions in the database
     * @param log 
     * @throws shoreline.DAL.Exeption.DALException 
     */
    public void logAction(ActionLog log) throws DALException 
    {
        try(Connection con = dbconnector.getConnection())
        {
            String sql = "INSERT INTO ActionLog VALUES(?, ?, ?, ?, ?, ?)";
            
            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setString(1, log.getFname());
            statement.setString(2, log.getLname());
            statement.setString(3, log.getEmail());
            statement.setString(4, log.getDate());
            statement.setString(5, log.getTime());
            statement.setString(6, log.getAction());
            
            statement.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            throw new DALException(ex.getMessage(), ex);
        }
    }

     /**
     * Draw all actions made from the database.
     * @return Returns a list of actions that the users has made.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public List<ActionLog> getAllActionLogs() throws DALException 
    {
        try(Connection con = dbconnector.getConnection())
        {
            String sql = "SELECT * FROM ActionLog";
            
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);
            
            List<ActionLog> actionLogList = new ArrayList<>();
            
            while(rs.next())
            {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String action = rs.getString("action");
                
                ActionLog al = new ActionLog(id, fname, lname, email, date, time, action);
                
                actionLogList.add(al);
            }
            return actionLogList;
            
        } 
        catch (SQLException ex) 
        {
            throw new DALException(ex.getMessage(), ex);
        }

    }
    
}
