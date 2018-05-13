/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shoreline.BE.CurrentUser;
import shoreline.BE.User;

/**
 *
 * @author peder
 */
public class UserDAO 
{
    
    private DataBaseConnector dbConnector;
    
    
    public UserDAO()
    {
        try 
        {
            dbConnector = new DataBaseConnector();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public boolean validateLogin(String loginInfo) 
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
                   
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    
}
