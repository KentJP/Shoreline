/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL;

import java.sql.SQLException;
import shoreline.BE.User;
import shoreline.DAL.UserDAO;

/**
 *
 * @author peder
 */
public class UserManager {
    
    private final UserDAO userdao = new UserDAO();

    public User login(String userName) throws SQLException {
        
        return userdao.login(userName);
                
      
        
        
    }
    
}
