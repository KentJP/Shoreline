/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BE;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Frederik Tubæk
 */
public class ActionLog implements Serializable
{
    
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String date;
    private String time;
    private String action;
    
    
    private Date todaysDate = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
  
    
    private Date currentTime = new Date();
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
    
    public ActionLog(String action)
    {
        this.fname = CurrentUser.getfName();
        this.lname = CurrentUser.getlName();
        this.email = CurrentUser.getEmail();
        this.date = dateFormat.format(todaysDate);
        this.time = timeFormat.format(currentTime);
        this.action = action;     
    }
    
    public ActionLog(int id, String fname, String lname, String email, String date, String time, String action)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.date = date;
        this.time = time;
        this.action = action;
        
    }

    public int getId() {
  
            return id;
        
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }
    
}
