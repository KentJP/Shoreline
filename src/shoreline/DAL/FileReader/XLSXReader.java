/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.DAL.FileReader;

import java.io.File;
import java.util.List;

/**
 *
 * @author frederik
 */
public class XLSXReader implements StrategyFileReader{

    @Override
    public List<String> readFile(File file) 
    {
        System.out.println("INSERT FROM DATACONVERTER");
        return null;
    }
    
}
