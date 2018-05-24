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
     *
     * @param name
     * @param mapConfig
     */
    public MappingDesign(String name, List<Configuration> mapConfig)
    {
        this.name = name;
        this.mapConfig = mapConfig;
    }

    /**
     *
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
     * Gets Map Configuration
     * @return Returns a List of Map Configurations. 
     */
    public List<Configuration> getMapConfig() {
        return mapConfig;
    }

    /**
     * Converts to a name to String.
     * @return Returns Name.
     */
    @Override
    public String toString() {
        return name;
    }
    
    
   
    
    
    
}
