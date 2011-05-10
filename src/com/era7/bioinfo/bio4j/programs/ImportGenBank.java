/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfo.bioinfoutil.genbank.GBCommon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilters;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportGenBank implements Executable {

    public static final String BASE_FOLDER = "refseq/release/complete/";
        
    private static final Logger logger = Logger.getLogger("ImportGenBank");
    private static FileHandler fh;

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {

        
        File currentFolder = new File(".");
        
        File[] files = currentFolder.listFiles();
        
        for (File file : files) {
            if(file.getName().endsWith(".")){
                
                try{
                    
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;
                    
                    while((line = reader.readLine()) != null){
                        
                        StringBuilder stBuilder = new StringBuilder();
                        //Now I get all the lines till I reach the string '//'
                        do{
                            stBuilder.append((line + "\n"));
                            line = reader.readLine();
                            
                        }while(line != null && !line.startsWith(GBCommon.LAST_LINE_STR));
                        
                        
                        
                    }
                   
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                
            }
        }


    }
    
    private static void ftpStuff(){
        try {

            FTPClient ftp = new FTPClient();
            ftp.connect("ftp.ncbi.nih.gov");

            System.out.println(ftp.getReplyString());

            ftp.login("anonymous", "asdfjkjd83djsdf@gmail.com");

            System.out.println("before list files...");

            //ftp.li

            FTPFile[] files = ftp.listFiles(BASE_FOLDER);

            System.out.println(files.length);

            for (FTPFile file : files) {

                if (file.getName().endsWith(".gbff.gz")) {

                    StringWriter writer = null;
                    String charset = "ASCII";

                    GZIPInputStream inputStream = new GZIPInputStream(ftp.retrieveFileStream(BASE_FOLDER + "/" + file.getName()));

                    System.out.println("ftp.getControlEncoding() = " + ftp.getControlEncoding());

                    Reader decoder = new InputStreamReader(inputStream, charset);
                    BufferedReader buffered = new BufferedReader(decoder);

                    String line = null;

                    while ((line = buffered.readLine()) != null) {
                        System.out.println("line = " + line);
                    }

                    System.exit(0);
                }
            }



        } catch (Exception ex) {
            Logger.getLogger(ImportGenBank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
