/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.DirectoryWatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import shoreline.BE.MappingDesign;
import shoreline.BLL.Convert.ConvertManager;

/**
 *
 * @author Frederik Tubæk
 */
public class WatchManager {
    
    private static final ExecutorService executor = Executors.newFixedThreadPool(20, new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });

    
    
    /**
     * Create a thread that watches if there is any changes in this new directory. 
     * @param dir
     * @param name
     * @param selectedMap 
     */
    public void createDirectoryWatcher(String dir, String name, MappingDesign selectedMap) 
    {
        WatchRunnable wr = new WatchRunnable(dir, name, selectedMap);
        executor.submit(wr);
    }
    
    
    
    
    
}
