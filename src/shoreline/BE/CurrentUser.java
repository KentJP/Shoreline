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
    
    /**
     *
     * @param id
     * @param email
     * @param fName
     * @param lName
     */
    public static void setCurrentUser(int id, String email, String fName, String lName)
    {
        CurrentUser.id = id;
        CurrentUser.email = email;
        CurrentUser.fName = fName;
        CurrentUser.lName = lName;
    }
    

    /**
     * Gets ID.
     * @return Returns ID. 
     */
    public static int getId() {
        return id;
    }

    /**
     * Gets Email.
     * @return Returns Email.
     */
    public static String getEmail() {
        return email;
    }

    /**
     * Gets First Name.
     * @return Returns First Name.
     */
    public static String getfName() {
        return fName;
    }

    /**
     * Gets Last Name.
     * @return Returns Last Name.
     */
    public static String getlName() {
        return lName;
    }
    
    /**
     * Gets Instance.
     * @return Returns current user.
     */
    public static CurrentUser getInstance()
    {
        return currentUser;
    }
    
    

    
}
