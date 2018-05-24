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

    /**
     *
     * @param oldValue
     */
    public Configuration(String oldValue) 
    {
        this.oldValue = oldValue;
    }
    
    /**
     *
     * @param index
     * @param oldValue
     */
    public Configuration(int index, String oldValue)
    {
        this.index = index;
        this.oldValue = oldValue;
    }
    
    /**
     *
     * @param index
     * @param oldValue
     * @param newValue
     */
    public Configuration(int index, String oldValue, String newValue)
    {
        this.index = index;
        this.oldValue = oldValue;
        this.newValue = newValue;
        
    }
    
    
    
    
    /**
     * Gets Index.
     * @return Returns Index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets Index.
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets Old Value.
     * @return Returns Old Value.
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * Sets Old Value.
     * @param oldValue 
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * Gets New Value.
     * @return Returns New Value.
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * Sets New Value.
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
     * Removes "\" and "/" from the name of the static value.
     */
    public void removeStaticIdentifier()
    {
        int nameLength = oldValue.length();
        
        String newOldValue = oldValue.substring(1, nameLength-1);
        oldValue = newOldValue;
        
    }
    
}
