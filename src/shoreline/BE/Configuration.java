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
public class Configuration implements Serializable{
    
    private int index;
    private String oldValue;
    private String newValue;
    
    public Configuration(int index, String oldValue)
    {
        this.index = index;
        this.oldValue = oldValue;
    }
    
    public Configuration(int index, String oldValue, String newValue)
    {
        this.index = index;
        this.oldValue = oldValue;
        this.newValue = newValue;
        
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    
    
}
