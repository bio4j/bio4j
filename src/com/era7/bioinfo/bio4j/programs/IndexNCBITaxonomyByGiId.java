/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4jmodel.util.Bio4jManager;
import com.era7.bioinfo.bio4jmodel.util.NodeRetriever;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class IndexNCBITaxonomyByGiId implements Executable {

    private static final Logger logger = Logger.getLogger("IndexNCBITaxonomyByGiId");
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
                    + "1. Tax-id <--> Gi-id table file \n");
        } else {

            Bio4jManager manager = null;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("IndexNCBITaxonomyByGiId.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                File file = new File(args[0]);

                logger.log(Level.INFO, "creating manager...");
                manager = new Bio4jManager(CommonData.DATABASE_FOLDER);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                
                Index<Node> ncbiTaxonGiIdIndex = manager.getNCBITaxonGiIdIndex();
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;
                int lineCounter = 0;
                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\t");

                    int giId = Integer.parseInt(columns[0]);
                    int taxId = Integer.parseInt(columns[1]);

                    NCBITaxonNode nCBITaxonNode = nodeRetriever.getNCBITaxonByTaxId(String.valueOf(taxId));
                    ncbiTaxonGiIdIndex.add(nCBITaxonNode.getNode(), NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, giId);

                    lineCounter++;
                    if (lineCounter % 100000 == 0) {
                        System.out.println("lineCounter = " + lineCounter);
                    }
                }
                reader.close();
              


            } catch (Exception e) {
                Logger.getLogger(ImportNCBITaxonomy.class.getName()).log(Level.SEVERE, null, e);
            } finally {

                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                manager.shutDown();
            }



        }
    }
}
