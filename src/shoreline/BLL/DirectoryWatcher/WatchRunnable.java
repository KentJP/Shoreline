/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoreline.BLL.DirectoryWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import shoreline.BE.ActionLog;
import shoreline.BE.Configuration;
import shoreline.BE.ConversionTask;
import shoreline.BE.MappingDesign;
import shoreline.BLL.Convert.ConvertManager;
import shoreline.BLL.LogManager;

/**
 *
 * @author Frederik Tubæk
 */
public class WatchRunnable implements Runnable
{
    private ConvertManager convertmanager = ConvertManager.getInstance();
    private LogManager logmanager = new LogManager();
    private WatchService watcher;

    private String name;
    private String dir;
    private MappingDesign md;
    
    private String[] subFolders = {"Input", "Output",};
    
    
    
    
    public WatchRunnable(String dir, String name, MappingDesign md)
    {
        try 
        {
            this.dir = dir;
            this.md = md;
            this.name = name;
            this.watcher = FileSystems.getDefault().newWatchService();
            
        } catch (IOException ex) 
        {
            Logger.getLogger(WatchRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() 
    {
        File directory = new File(dir + "\\" +name);
        
        if(!directory.exists())
        {
            try 
            {
                directory.mkdir();
                
                for (String subFolder : subFolders)
                {
                    File subDirectory = new File(directory.getAbsolutePath() + "\\" + subFolder);
                    
                    if(!subDirectory.exists())
                    {
                        subDirectory.mkdir();
                    }
                }
                
                Path path = Paths.get(directory.getAbsolutePath() + "\\" + "Input");
                path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
                
                while (true) 
                {
                    WatchKey key;
                    try 
                    {
                        // wait for a key to be available
                        key = watcher.take();
                    } catch (InterruptedException ex) 
                    {
                        return;
                    }
 
                    for (WatchEvent<?> event : key.pollEvents()) 
                    {
                        // get event type
                        WatchEvent.Kind<?> kind = event.kind();
 
                        // get file name
                        @SuppressWarnings("unchecked")
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path fileName = ev.context();
 
                        System.out.println(kind.name() + ": " + fileName);
 
                        if (kind == StandardWatchEventKinds.OVERFLOW) 
                        {
                          continue;
                        } else if (kind == StandardWatchEventKinds.ENTRY_CREATE) 
                        {
                           
                            String taskFilePath = directory.getAbsolutePath() + "\\" + "Input" + "\\" + fileName; 
                            String taskName = FilenameUtils.removeExtension(fileName.toString());
                            List<Configuration> taskConfig = md.getMapConfig();
                            
                            ConversionTask ct = new ConversionTask(taskName, taskFilePath, taskConfig);
                            ActionLog a = new ActionLog("Started Automatic Conversion on Task : " + ct.getName());
                            logmanager.logAction(a);
                            convertmanager.convertToJSON(ct, directory.getAbsolutePath() + "\\" + "Output");
                            
                            
                            
                            
                            
            
 
                        } 
                    }  
                    key.reset();
                }
            } catch (IOException ex) 
            {
                Logger.getLogger(WatchRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    
}
