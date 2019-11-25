package com.reliableplugins.loaderclasssender;

import com.reliableplugins.loaderclasssender.loader.LoadClass;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

public class Database
{
    private Set<File> loaders;

    public Database()
    {
        File folder = new File("builds");
        this.loadFiles(folder.listFiles());
    }

    public void loadFiles(File[] files)
    {
        // If no files
        if(files == null || files.length == 0)
        {
            System.out.println("[WARN] No files in database");
            return;
        }
        byte[] fileBytes;

        for(File file : files)
        {
            try
            {
                fileBytes = Files.readAllBytes(file.toPath());
                LoadClass.getBuildId("Loader", fileBytes);
            }
            catch(Exception e)
            {
                e.printStackTrace(System.out);
                continue;
            }
            this.loaders.add(file);
        }
    }
}
