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
public class MappingDesign {
    
    private int id;
    private String name;
    private List<Configuration> mapConfig;
    
    /**
     * This is a constructor of this class.
     * @param name
     * @param mapConfig
     */
    public MappingDesign(String name, List<Configuration> mapConfig)
    {
        this.name = name;
        this.mapConfig = mapConfig;
    }

    /**
     * This is a constructor of this class.
     * @param id
     * @param name
     * @param mapConfig
     */
    public MappingDesign(int id, String name, List<Configuration> mapConfig) 
    {
        this.id = id;
        this.name = name;
        this.mapConfig = mapConfig;
    }

    /**
     * Retrieves ID.
     * @return Returns ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves Name.
     * @return Returns Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves a List of Map Configurations
     * @return Returns a List of Map Configurations. 
     */
    public List<Configuration> getMapConfig() {
        return mapConfig;
    }

    /**
     * Converts a name to String.
     * @return Returns Name.
     */
    @Override
    public String toString() {
        return name;
    }
    
    
   
    
    
    
}
