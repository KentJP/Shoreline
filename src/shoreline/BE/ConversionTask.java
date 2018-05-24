/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BE;

import java.util.List;

/**
 *
 * @author Frederik Tubæk
 */
public class ConversionTask {
    
    private int id;
    private String name;
    private String filePath;
    private String status;
    
    private List<Configuration> configurations;
    
    
    public ConversionTask(String name, String filePath, List<Configuration> configurations)
    {
        this.name = name;
        this.filePath = filePath;
        this.configurations = configurations;
        this.status = "Ready to Convert";
        
    }
    
    public ConversionTask(int id, String name, String filePath, String status, List<Configuration> configurations)
    {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.configurations = configurations;
        this.status = status;        
    }

    /**
     * Gets ID.
     * @return Returns ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets Name.
     * @return Returns Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets FilePath.
     * @return Returns FilePath.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets Status.
     * @return Returns Status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets a List of Configurations.
     * @return Returns the list of configurations.
     */
    public List<Configuration> getConfigurations() {
        return configurations;
    }
    
    /**
     * Changes Status to "Converted".
     */
    public void changeStatusConverted()
    {
        this.status = "Converted";
    }
    
    /**
     * Changes status to "Failed to convert".
     */
    public void changeStatusFailed()
    {
        this.status = "Failed to convert";
    }
    
    /**
     * Changes status to "Converting...".
     */
    public void changeSatusConverting()
    {
        this.status = "Converting...";
    }
    
}
