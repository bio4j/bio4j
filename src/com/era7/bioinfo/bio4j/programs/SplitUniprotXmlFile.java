/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class SplitUniprotXmlFile {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("This program expects two parameters: \n"
                    + "1. Uniprot xml input file\n"
                    + "2. Number of entries per resulting part file\n");
        } else {

            int numberOfEntries = Integer.parseInt(args[1]);
            int currentFile = 1;
            int currentEntry = 1;

            File inFile = new File(args[0]);

            String prefixOutFile = args[0].split("\\.")[0];

            try {

                BufferedWriter outBuff = new BufferedWriter(new FileWriter(new File(prefixOutFile + currentFile + ".xml")));
                outBuff.write("<entries>\n");

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                        if(currentEntry % numberOfEntries == 0){
                            outBuff.write("</entries>\n");
                            outBuff.close();
                            System.out.println("Closing file...");
                            currentFile++;
                            outBuff = new BufferedWriter(new FileWriter(new File(prefixOutFile + currentFile + ".xml")));
                        }

                        outBuff.write((line + "\n"));

                        while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                            line = reader.readLine();
                            outBuff.write((line + "\n"));
                        }

                        currentEntry++;

                        if(currentEntry % 10000 == 0){
                            System.out.println(currentEntry + " already...");
                        }
                    }
                }

                outBuff.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
