/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfo.bioinfoutil.Pair;
import com.era7.lib.bioinfo.bioinfoutil.genbank.GBCommon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

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
            if (file.getName().endsWith(".gbff")) {

                try {

                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line = null;

                    while ((line = reader.readLine()) != null) {

                        //this is the first line where the locus is
                        String accessionSt = "";
                        String definitionSt = "";
                        String versionSt = "";
                        String commentSt = "";
                        StringBuilder seqStBuilder = new StringBuilder();

                        ArrayList<String> cdsList = new ArrayList<String>();
                        ArrayList<String> geneList = new ArrayList<String>();
                        ArrayList<String> miscRnaList = new ArrayList<String>();
                        ArrayList<String> mRnaList = new ArrayList<String>();
                        ArrayList<String> ncRnaList = new ArrayList<String>();
                        ArrayList<String> rRnaList = new ArrayList<String>();
                        ArrayList<String> tmRnaList = new ArrayList<String>();
                        ArrayList<String> tRnaList = new ArrayList<String>();

                        boolean originFound = false;

                        //Now I get all the lines till I reach the string '//'
                        do {
                            boolean readLineFlag = true;

                            if (line.startsWith(GBCommon.LOCUS_STR)) {
                                // do nothing right now
                            } else if (line.startsWith(GBCommon.ACCESSION_STR)) {

                                accessionSt = line.split(GBCommon.ACCESSION_STR)[1].trim();

                            } else if (line.startsWith(GBCommon.VERSION_STR)) {

                                versionSt = line.split(GBCommon.VERSION_STR)[1].trim().split(" ")[0];

                            } else if (line.startsWith(GBCommon.DEFINITION_STR)) {

                                definitionSt += line.split(GBCommon.DEFINITION_STR)[1].trim();
                                do {
                                    line = reader.readLine();
                                    if (line.startsWith(" ")) {
                                        definitionSt += line.trim();
                                    }
                                } while (line.startsWith(" "));
                                readLineFlag = false;

                            } else if (line.startsWith(GBCommon.COMMENT_STR)) {

                                commentSt += line.split(GBCommon.COMMENT_STR)[1].trim();
                                do {
                                    line = reader.readLine();
                                    if (line.startsWith(" ")) {
                                        commentSt += "\n" + line.trim();
                                    }
                                } while (line.startsWith(" "));
                                readLineFlag = false;

                            } else if (line.startsWith(GBCommon.FEATURES_STR)) {


                                do {
                                    line = reader.readLine();

                                    if (line.trim().startsWith(GBCommon.CDS_STR)) {
                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.CDS_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        cdsList.add(positionsSt);

                                    } else if (line.trim().startsWith(GBCommon.GENE_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.GENE_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        geneList.add(positionsSt);

                                    }else if (line.trim().startsWith(GBCommon.MISC_RNA_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.MISC_RNA_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        miscRnaList.add(positionsSt);

                                    }else if (line.trim().startsWith(GBCommon.TM_RNA_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.TM_RNA_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        tmRnaList.add(positionsSt);

                                    }else if (line.trim().startsWith(GBCommon.R_RNA_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.R_RNA_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        rRnaList.add(positionsSt);

                                    }else if (line.trim().startsWith(GBCommon.M_RNA_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.M_RNA_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        mRnaList.add(positionsSt);

                                    }else if (line.trim().startsWith(GBCommon.NC_RNA_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.NC_RNA_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        ncRnaList.add(positionsSt);

                                    }else if (line.trim().startsWith(GBCommon.T_RNA_STR)) {

                                        String positionsSt = "";
                                        positionsSt += line.trim().split(GBCommon.T_RNA_STR)[1].trim();

                                        line = reader.readLine();

                                        while (!line.trim().startsWith("/")) {
                                            positionsSt += line.trim();
                                            line = reader.readLine();
                                        }

                                        tRnaList.add(positionsSt);

                                    }

                                } while (line.startsWith(" "));
                                readLineFlag = false;

                            } else if (line.startsWith(GBCommon.ORIGIN_STR)) {
                                //sequence

                                originFound = true;

                                do {
                                    line = reader.readLine();
                                    String[] tempArray = line.trim().split(" ");
                                    for (int i = 1; i < tempArray.length; i++) {
                                        seqStBuilder.append(tempArray[i]);
                                    }

                                } while (line.startsWith(" "));
                                readLineFlag = false;
                            }

                            if (readLineFlag) {
                                line = reader.readLine();
                            }



                        } while (line != null && !line.startsWith(GBCommon.LAST_LINE_STR));




                        //-----we only save the data when the sequence is found------------
                        if (originFound) {


                            System.out.println("accessionSt = " + accessionSt);
                            System.out.println("versionSt = " + versionSt);
                            System.out.println("definitionSt = " + definitionSt);
                            System.out.println("commentSt = " + commentSt);
                            System.out.println("sequence.length = " + seqStBuilder.toString().length());

                            System.out.println("geneList = " + geneList);
                            System.out.println("cdsList = " + cdsList);
                            System.out.println("miscRnaList = " + miscRnaList);
                            System.out.println("mRnaList = " + mRnaList);
                            System.out.println("ncRnaList = " + ncRnaList);
                            System.out.println("rRnaList = " + rRnaList);
                            System.out.println("tmRnaList = " + tmRnaList);
                            System.out.println("tRnaList = " + tRnaList);
                            

                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }


    }

    private static void ftpStuff() {
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
