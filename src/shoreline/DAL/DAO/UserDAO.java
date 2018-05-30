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
import shoreline.BE.CurrentUser;
import shoreline.DAL.DataBaseConnector;
import shoreline.DAL.Exeption.DALException;

/**
 *
 * @author Daniel
 */
public class UserDAO 
{
    
    private DataBaseConnector dbConnector;
    
    /**
     * This is the constructor of this class.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public UserDAO() throws DALException
    {
        try 
        {
            dbConnector = new DataBaseConnector();
        } 
        catch (IOException ex) 
        {
            throw new DALException("Could not connect to the database", ex);
        }
    }
    
    
    /**
     * Validate wheater or not the Email is in the Database.
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     * @throws shoreline.DAL.Exeption.DALException
     */
    public boolean validateLogin(String loginInfo) throws DALException 
    {
        try(Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Users WHERE email = ?";
            
            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setString(1, loginInfo);
            
            
            ResultSet rs = statement.executeQuery();
           
            if(rs.next())
            {
                int id = rs.getInt("userId");
                String email = rs.getString("email");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                
                CurrentUser.setCurrentUser(id, email, fName, lName);
                
                return true;
            }
            else 
            {
                return false;
            }
                   
            
            
        } catch (SQLException ex) 
        {
            throw new DALException("Could not connect to the database", ex);
        }
    }


    
}
