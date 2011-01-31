/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.IsoformNode;
import com.era7.bioinfo.bioinfoneo4j.Neo4jManager;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.graphdb.Transaction;
import org.neo4j.index.IndexService;

/**
 *
 * @author ppareja
 */
public class ImportIsoformSequences implements Executable {

    private static final Logger logger = Logger.getLogger("MyLog");
    private static FileHandler fh;
    public static final String PROPERTIES_FILE_NAME = "batchInserter.properties";

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
            System.out.println("El programa espera un parametro: \n"
                    + "1. Nombre del archivo fasta con todas las isoformas \n");
        } else {
            File inFile = new File(args[0]);

            String isoformIdSt = null;
            Transaction txn = null;
            Neo4jManager manager = null;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("ImporIsoformSeqsFile.log", true);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                System.out.println("creating manager...");
                manager = Neo4jManager.getNeo4JManager(CommonData.DATABASE_FOLDER);
                System.out.println("creating index manager...");
                IndexService indexService = manager.getIndexService();
                txn = manager.beginTransaction();

                //------------------nodes properties maps-----------------------------------
                Map<String, Object> goProperties = new HashMap<String, Object>();
                //--------------------------------------------------------------------------

                Map<String, ArrayList<String>> termParentsMap = new HashMap<String, ArrayList<String>>();

                int contador = 1;
                int limitForTransaction = 100;

                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder seqStBuilder = new StringBuilder();



                System.out.println("updating isoform data....");

                //-----first I create all the elements whitout their relationships-------------

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith(">")) {

                        String[] columns = line.split("\\|");
                        isoformIdSt = columns[1];
                        String isoformNameSt = columns[2].split("OS=")[0].trim();

                        //sequence read line
                        line = reader.readLine();

                        while (!line.trim().startsWith(">")) {
                            seqStBuilder.append(line);
                            line = reader.readLine();
                        }

                        String sequence = seqStBuilder.toString();
                        seqStBuilder.delete(0, seqStBuilder.length());

                        IsoformNode node = new IsoformNode(indexService.getSingleNode(IsoformNode.ISOFORM_ID_INDEX, isoformIdSt));
                        node.setSequence(sequence);
                        node.setName(isoformNameSt);
                    }

                    contador++;
                    if ((contador % limitForTransaction) == 0) {
                        txn.success();
                        txn.finish();
                    }
                }

                if(contador % limitForTransaction != 0){
                    txn.success();
                    txn.finish();
                }

                reader.close();

                System.out.println("Done! :)");    

            } catch (Exception e) {
                txn.failure();
                txn.finish();
                logger.log(Level.INFO, ("Exception retrieving isoform " + isoformIdSt));
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            }finally{                
                System.out.println("Closing up inserter and index service....");
                // shutdown, makes sure all changes are written to disk
                manager.shutDown();
            }
        }
    }
}
