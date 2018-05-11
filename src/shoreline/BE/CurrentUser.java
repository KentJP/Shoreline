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
    
    
    private Boolean isConstructed;
    
    private int id;
    private String email;
    private String fName;
    private String lName;
    
    
    public CurrentUser(int id, String email, String fName, String lName)
    {
        if(isConstructed != true)
        {
            this.id = id;
            this.email = email;
            this.fName = fName;
            this.lName = lName;        
            
            isConstructed = true;
        } 
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
