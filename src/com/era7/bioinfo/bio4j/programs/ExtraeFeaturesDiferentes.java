/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jdom.Element;

/**
 *
 * @author ppareja
 */
public class ExtraeFeaturesDiferentes {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {

        if (args.length != 1) {
            System.out.println("El programa espera un parametro: \n"
                    + "1. Nombre del archivo xml a importar \n");
        } else {
            File inFile = new File(args[0]);


            Map<String, String> featureTypesMap = new HashMap<String, String>();

            BufferedWriter outBuff = new BufferedWriter(new FileWriter(new File("features.xml")));

            outBuff.write("<features>\n");
           
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            String line = null;
            StringBuilder entryStBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("<entry")) {

                    while (!line.trim().startsWith("</entry>")) {
                        entryStBuilder.append(line);
                        line = reader.readLine();
                    }
                    //linea final del organism
                    entryStBuilder.append(line);
                    //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
                    XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                    entryStBuilder.delete(0, entryStBuilder.length());

                    List<Element> featuresList = entryXMLElem.asJDomElement().getChildren("feature");
                    for (Element element : featuresList) {
                        if(featureTypesMap.get(element.getAttributeValue("type")) == null){
                            XMLElement feature = new XMLElement(element);
                            System.out.println("feature = " + feature);
                            featureTypesMap.put(element.getAttributeValue("type"), feature.toString());
                        }
                    }

                }
            }


            Set<String> keys = featureTypesMap.keySet();
            for (String key : keys) {
                outBuff.write(featureTypesMap.get(key));
            }

            outBuff.write("</features>\n");
            outBuff.close();
            System.out.println("Features file created successfully!");

        }
    }
}
