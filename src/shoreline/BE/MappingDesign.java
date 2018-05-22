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
    
    
    public MappingDesign(String name, List<Configuration> mapConfig)
    {
        this.name = name;
        this.mapConfig = mapConfig;
    }

    public MappingDesign(int id, String name, List<Configuration> mapConfig) 
    {
        this.id = id;
        this.name = name;
        this.mapConfig = mapConfig;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Configuration> getMapConfig() {
        return mapConfig;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
   
    
    
    
}
