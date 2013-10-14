/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.neo4j.programs;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.bioinfo.bioinfoutil.genbank.GBCommon;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Uploads the sequences of the genome elements found in RefSeq complete release
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class UploadRefSeqSequencesToS3 implements Executable {

    private static final Logger logger = Logger.getLogger("UploadRefSeqSequencesToS3");
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


        if (args.length != 1) {
            System.out.println("This program expects one parameter: \n"
                    + "1. Folder name with all the .gbk files \n");
        } else {

            File currentFolder = new File(args[0]);

            File[] files = currentFolder.listFiles();


            try {
                // This block configures the logger with handler and formatter
                fh = new FileHandler("UploadRefSeqSequencesToS3.log", false);

                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                //--------creating amazon s3 client--------------
                AmazonS3Client amazonS3Client = new AmazonS3Client(new PropertiesCredentials(new File("AwsCredentials.properties")));
                Owner bucketOwner = amazonS3Client.getBucketAcl(CommonData.REFSEQ_BUCKET_NAME).getOwner();
                //--------------------------------


                for (File file : files) {
                    if (file.getName().endsWith(".gbff")) {

                        logger.log(Level.INFO, ("file: " + file.getName()));

                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line = null;

                        while ((line = reader.readLine()) != null) {

                            //this is the first line where the locus is
                            //String accessionSt = "";
                            //String definitionSt = "";
                            String versionSt = "";
                            //String commentSt = "";
                            StringBuilder seqStBuilder = new StringBuilder();

                            boolean originFound = false;

                            //Now I get all the lines till I reach the string '//'
                            do {
                                boolean readLineFlag = true;

                                if (line.startsWith(GBCommon.LOCUS_STR)) {
                                    // do nothing right now
                                } else if (line.startsWith(GBCommon.VERSION_STR)) {

                                    versionSt = line.split(GBCommon.VERSION_STR)[1].trim().split(" ")[0];

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

                                //-----saving sequence in S3------------
                                byte[] byteArray = seqStBuilder.toString().getBytes();
                                ByteArrayInputStream bs = new ByteArrayInputStream(byteArray);
                                ObjectMetadata objectMetadata = new ObjectMetadata();
                                objectMetadata.setContentLength(byteArray.length);
                                logger.log(Level.INFO, ("uploading sequence of genome element: " + versionSt + " ..."));
                                amazonS3Client.putObject(CommonData.REFSEQ_BUCKET_NAME, versionSt + ".txt", bs, objectMetadata);
                                AccessControlList accessControlList = new AccessControlList();
                                accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);
                                accessControlList.setOwner(bucketOwner);
                                amazonS3Client.setObjectAcl(CommonData.REFSEQ_BUCKET_NAME, versionSt + ".txt", accessControlList);
                                logger.log(Level.INFO, ("done!"));
                                //--------------------------------------


                                logger.log(Level.INFO, (versionSt + " sequence uploaded!"));

                            }

                        }

                    }
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            } finally {

                // closing logger file handler
                fh.close();

            }
        }

    }
}
