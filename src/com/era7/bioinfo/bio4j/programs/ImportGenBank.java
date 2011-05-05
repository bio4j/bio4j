/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilters;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportGenBank implements Executable {
    
    
    public static final String BASE_FOLDER = "genbank/genomes/";
    public static final String BACTERIA_FOLDER_NAME = "Bacteria";
    public static final String EUKARYOTES_FOLDER_NAME = "Eukaryotes";
    
    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }
    
        
    public static void main(String[] args) {
        
        try {
            
            FTPClient ftp = new FTPClient();
            
            ftp.connect("ftp.ncbi.nih.gov");            
            
            System.out.println(ftp.getReplyString());    
            
            ftp.login("anonymous", "asdfjkaljsdf@gmail.com");
            
            
            FTPFile[] files = ftp.listFiles(BASE_FOLDER);
            
            for (FTPFile fTPFile : files) {
                                
                if(fTPFile.getName().equals(BACTERIA_FOLDER_NAME)){
                    
                    System.out.println("RETRIEVING BACTERIAL GENOMES....");
                    retrieveGenomes(ftp, fTPFile, BASE_FOLDER);                    
                    System.out.println("DONE WITH BACTERIAS! :)");
                    
                }else if(fTPFile.getName().equals(EUKARYOTES_FOLDER_NAME)){
                    
                    System.out.println("RETRIEVING EUKARYOTES GENOMES....");
                    retrieveGenomes(ftp, fTPFile, BASE_FOLDER);                    
                    System.out.println("DONE WITH EUKARYOTES! :)");
                    
                }
            }
            
            
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(ImportGenBank.class.getName()).log(Level.SEVERE, null, ex);
        } 
                
        
    }
    
    private static void retrieveGenomes(FTPClient ftp,
                                        FTPFile file,
                                        String currentFolder) throws IOException{
        
        if(file.isDirectory()){
            
            System.out.println("Accessing files of FOLDER: " + file.getName());
            
            currentFolder += "/" + file.getName();
                        
            for (FTPFile tempFile : ftp.listFiles(currentFolder)) {                
                retrieveGenomes(ftp, tempFile, currentFolder);
            }
            
        }else{
            if(file.getName().endsWith(".gbk")){
                System.out.println("Genome reached! " + file.getName());
            }            
        }
        
    }

        
    
}
