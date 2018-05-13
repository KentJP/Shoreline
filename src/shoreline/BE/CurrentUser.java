/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BE;

import java.io.Serializable;

/**
 *
 * @author frederik
 */
public class CurrentUser {
    
    
    private static CurrentUser currentUser = new CurrentUser();
    
    private static int id;
    private static String email;
    private static String fName;
    private static String lName;
    
    private CurrentUser(){}
    
    public static void setCurrentUser(int id, String email, String fName, String lName)
    {
        CurrentUser.id = id;
        CurrentUser.email = email;
        CurrentUser.fName = fName;
        CurrentUser.lName = lName;
    }
    

    public static int getId() {
        return id;
    }

    public static String getEmail() {
        return email;
    }

    public static String getfName() {
        return fName;
    }

    public static String getlName() {
        return lName;
    }
    
    public static CurrentUser getInstance()
    {
        return currentUser;
    }
    
    

    
}
