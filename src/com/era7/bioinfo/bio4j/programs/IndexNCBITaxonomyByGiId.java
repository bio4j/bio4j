/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4jmodel.nodes.ncbi.NCBITaxonNode;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.index.BatchInserterIndex;
import org.neo4j.graphdb.index.BatchInserterIndexProvider;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.impl.lucene.LuceneBatchInserterIndexProvider;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

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

        if (args.length != 3) {
            System.out.println("This program expects three parameters: \n"
                    + "1. Tax-id <--> Gi-id table file \n"
                    + "2. Bio4j DB folder \n"
                    + "3. Properties file name");
        } else {

            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;
            BatchInserterIndex giIndex = null;
            BatchInserterIndex taxonIndex = null;
           
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
                
                // create the batch inserter
                inserter = new BatchInserterImpl(args[1], BatchInserterImpl.loadProperties(args[2]));
                

                // create the batch index service
                indexProvider =  new LuceneBatchInserterIndexProvider( inserter );
                Map<String,String> indexProps = MapUtil.stringMap( "provider", "lucene", "type", "exact" );
                
                giIndex = indexProvider.nodeIndex( NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, indexProps);
                taxonIndex = indexProvider.nodeIndex( NCBITaxonNode.NCBI_TAXON_ID_INDEX, indexProps);
                
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = null;
                int lineCounter = 0;
                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split("\t");

                    int giId = Integer.parseInt(columns[0]);
                    int taxId = Integer.parseInt(columns[1]);                  
                    
                    Long nCBITaxonNodeId = taxonIndex.get(NCBITaxonNode.NCBI_TAXON_ID_INDEX,String.valueOf(taxId)).getSingle();
                    
                    if(nCBITaxonNodeId != null){
                        giIndex.add(nCBITaxonNodeId, MapUtil.map(NCBITaxonNode.NCBI_TAXON_GI_ID_INDEX, giId));                        
                    }else{
//                        System.out.println("NCBI taxon node is null ... :(");
//                        System.out.println("giId = " + giId);
//                        System.out.println("taxId = " + taxId);
                        
                        outBufferedWriter.write(giId + "\t" + taxId + "\n");
                    } 

                    lineCounter++;
                    
                    if (lineCounter % 100000 == 0) {
                        System.out.println("lineCounter = " + lineCounter);                        
                        outBufferedWriter.flush();
                    }
                }
                reader.close();          
                
                outBufferedWriter.close();

            } catch (Exception e) {
                Logger.getLogger(ImportNCBITaxonomy.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                
                //closing logger file handler
                fh.close();
                logger.log(Level.INFO, "Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                indexProvider.shutdown();
                inserter.shutdown();
            }



        }
    }
}
