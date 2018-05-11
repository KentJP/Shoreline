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

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }
    
    
}
