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
public class UserManager 
{
    
    private UserDAO userdao = new UserDAO();

    /**
     * Validate wheater or not the Email is in the Database.
     * @param loginInfo
     * @return true if the email is in the database - else return false.
     */
    public boolean validateLogin(String loginInfo) 
    {
        return userdao.validateLogin(loginInfo);
    }
    
      
        
        
    
    
}
