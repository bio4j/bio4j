/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilters;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ImportGenBank implements Executable {

    public static final String BASE_FOLDER = "refseq/release/complete/";
    //public static final String BACTERIA_FOLDER_NAME = "Bacteria";
    //public static final String EUKARYOTES_FOLDER_NAME = "Eukaryotes";

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
            
            System.out.println(files.length);

//            for (FTPFile file : files) {
//
//                if (file.getName().endsWith(".gbff.gz")) {
////                    
////                    System.out.println(file.getRawListing());
////                    System.exit(-1);
//                    GZIPInputStream inputStream = new GZIPInputStream(ftp.retrieveFileStream(BASE_FOLDER + "/" + file.getName()));
//                    //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//                    byte[] buf = new byte[65536];
//                    int len;
//                    while ((len = inputStream.read(buf)) > 0) {
//                        byte[] tempBuf = new byte[len];
//                        for (int i = 0; i < len; i++) {
//                            tempBuf[i] = buf[i];
//                        }
//                        System.out.println(new String(buf));
//                    }
//                    //System.out.println(br.readLine());
//                }
//            }



        } catch (Exception ex) {
            Logger.getLogger(ImportGenBank.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
