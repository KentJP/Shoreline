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
    
    /**
     *
     * @param action
     */
    public ActionLog(String action)
    {
        this.fname = CurrentUser.getfName();
        this.lname = CurrentUser.getlName();
        this.email = CurrentUser.getEmail();
        this.date = dateFormat.format(todaysDate);
        this.time = timeFormat.format(currentTime);
        this.action = action;     
    }
    
    /**
     *
     * @param id
     * @param fname
     * @param lname
     * @param email
     * @param date
     * @param time
     * @param action
     */
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

    /**
     * Gets ID.
     * @return Returns ID.
     */
    public int getId() {
  
            return id;
        
    }

    /**
     * Gets First Name.
     * @return Return First Name.
     */
    public String getFname() {
        return fname;
    }

    /**
     * Gets Last Name.
     * @return Returns Last Name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Gets Email.
     * @return Returns Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets Date.
     * @return Returns Date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets Time.
     * @return Returns Time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets Action.
     * @return Returns Action.
     */
    public String getAction() {
        return action;
    }
    
}
