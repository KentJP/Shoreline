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
public class User implements Serializable{
    
    private final int id;
    private String email;
    private String fName;
    private String lName;
    
    
    public User(int id, String email, String fName, String lName)
    {
        this.id = id;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        
    }

    /**
     * Gets ID.
     * @return Returns ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets Email.
     * @return Returns Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets First Name.
     * @return Returns First Name.
     */
    public String getfName() {
        return fName;
    }

    /**
     * Gets Last Name.
     * @return Returns Last Name.
     */
    public String getlName() {
        return lName;
    }
    
    
}
