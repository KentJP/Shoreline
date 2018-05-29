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

    public Configuration(String oldValue) 
    {
        this.oldValue = oldValue;
    }

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
    
    
    
    
    /**
     * Retrieves Index.
     * @return Returns Index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Defines Index.
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Retrieves Old Value.
     * @return Returns Old Value.
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * Defines Old Value.
     * @param oldValue 
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * Retrieves New Value.
     * @return Returns New Value.
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * Defines New Value.
     * @param newValue 
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
    
    /**
     * Checks if the Value is a static Value.
     * @return Returns true if its a static value 
     * Else false.
     */
    public boolean isStaticValue()            
    {
        if(oldValue.startsWith("\"") && oldValue.endsWith("\""))
        {
            return true;
            
        } else
        {
            return false;
        }
    }
    
    /**
     * A static value contains \" before and after its name.
     * This method removes "\" and "/" from the name of a static value.
     */
    public String removeStaticIdentifier()
    {
        int nameLength = oldValue.length();
        
        String newOldValue = oldValue.replaceAll("\"", "");
        return newOldValue;
        
    }
    
}
