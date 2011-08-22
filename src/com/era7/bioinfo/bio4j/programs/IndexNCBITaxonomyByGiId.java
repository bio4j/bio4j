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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
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

        if (args.length != 2) {
            System.out.println("This program expects two parameters: \n"
                    + "1. Tax-id <--> Gi-id table file \n"
                    + "2. Bio4j DB folder");
        } else {

            Bio4jManager manager = null;
            Transaction txn = null;
           
            //-------writer for storing incorrect gene identifiers-taxon id pairs----
            BufferedWriter outBufferedWriter = null;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("IndexNCBITaxonomyByGiId.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                File file = new File(args[0]);
                
                outBufferedWriter = new BufferedWriter(new FileWriter(new File("incorrectGiTaxIdPairs.txt")));
                
                logger.log(Level.INFO, "creating manager...");
                manager = new Bio4jManager(args[1]);
                NodeRetriever nodeRetriever = new NodeRetriever(manager);
                
                txn = manager.beginTransaction();
                
                Index<Node> ncbiTaxonGiIdIndex = manager.getNCBITaxonGiIdIndex();
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;
                int lineCounter = 0;
                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\t");

                    int giId = Integer.parseInt(columns[0]);
                    int taxId = Integer.parseInt(columns[1]);                  
                    
                    NCBITaxonNode nCBITaxonNode = nodeRetriever.getNCBITaxonByTaxId(String.valueOf(taxId));
                    
                    if(nCBITaxonNode != null){
                        ncbiTaxonGiIdIndex.add(nCBITaxonNode.getNode(), NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, giId);                        
                    }else{
//                        System.out.println("NCBI taxon node is null ... :(");
//                        System.out.println("giId = " + giId);
//                        System.out.println("taxId = " + taxId);
                        
                        outBufferedWriter.write(giId + "\t" + taxId + "\n");
                    } 

                    lineCounter++;
                    
                    if (lineCounter % 100000 == 0) {
                        txn.success();
                        txn.finish();
                        System.out.println("lineCounter = " + lineCounter);
                        
                        outBufferedWriter.flush();
                        
                        txn = manager.beginTransaction();
                    }
                }
                reader.close();             

                txn.success();
                
                outBufferedWriter.close();

            } catch (Exception e) {
                Logger.getLogger(ImportNCBITaxonomy.class.getName()).log(Level.SEVERE, null, e);
                txn.failure();
            } finally {
                
                //finishing las transaction
                txn.finish();

                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                manager.shutDown();
            }



        }
    }
}
