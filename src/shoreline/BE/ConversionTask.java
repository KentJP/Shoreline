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
    
    public ConversionTask(int id, String name, String filePath, List<Configuration> configurations)
    {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.configurations = configurations;
        this.status = "Ready to Convert";        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getStatus() {
        return status;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }
    
    public void changeStatusConverted()
    {
        this.status = "Converted";
    }
    
    public void changeStatusFailed()
    {
        this.status = "Failed to convert";
    }
    
    public void changeSatusConverting()
    {
        this.status = "Converting...";
    }
    
}
