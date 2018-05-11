/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shoreline.BE.User;

/**
 *
 * @author peder
 */
public class UserDAO {
    
    private DataBaseConnector dbConnector;

    public User login(String userName) throws SQLException {
        
        try {
            Connection con = dbConnector.getConnection();
            String sql = "SELECT * FROM Users WHERE username = ?";
            
             PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1, userName);
             
              ResultSet rs = statement.executeQuery();
                while(rs.next())
            {
                int id = rs.getInt("id");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String email = rs.getString("email");
               
                
                User user = new User(id, fname, lname, email);
                return user;
            }
            
                    } catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
}
