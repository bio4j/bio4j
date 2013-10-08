/*
 * Copyright (C) 2010-2011  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.era7.bioinfo.bio4j.neo4j.programs;

import com.era7.bioinfo.bio4j.neo4j.model.nodes.AlternativeProductNode;
import com.era7.bioinfo.bio4j.neo4j.model.nodes.SequenceCautionNode;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.aproducts.AlternativeProductInitiationRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.aproducts.AlternativeProductPromoterRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.aproducts.AlternativeProductRibosomalFrameshiftingRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.aproducts.AlternativeProductSplicingRel;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.neo4j.model.relationships.sc.*;
import com.era7.bioinfo.bio4j.neo4j.model.util.Bio4jManager;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.unsafe.batchinsert.LuceneBatchInserterIndexProvider;
import org.neo4j.unsafe.batchinsert.*;

/**
 * Inits Bio4j DB and stores basic/general nodes and relationships
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class InitBio4jDB implements Executable {
    
    //--------indexing API constans-----
    private static String PROVIDER_ST = "provider";
    private static String EXACT_ST = "exact";
    private static String FULL_TEXT_ST = "fulltext";
    private static String LUCENE_ST = "lucene";
    private static String TYPE_ST = "type";
    //-----------------------------------
    

    private static final Logger logger = Logger.getLogger("InitBio4jDB");
    private static FileHandler fh;
    public static ErroneousGeneModelPredictionRel erroneousGeneModelPredictionRel = new ErroneousGeneModelPredictionRel(null);
    public static ErroneousInitiationRel erroneousInitiationRel = new ErroneousInitiationRel(null);
    public static ErroneousTerminationRel erroneousTerminationRel = new ErroneousTerminationRel(null);
    public static ErroneousTranslationRel erroneousTranslationRel = new ErroneousTranslationRel(null);
    public static FrameshiftRel frameshiftRel = new FrameshiftRel(null);
    public static MiscellaneousDiscrepancyRel miscellaneousDiscrepancyRel = new MiscellaneousDiscrepancyRel(null);
    public static AlternativeProductInitiationRel alternativeProductInitiationRel = new AlternativeProductInitiationRel(null);
    public static AlternativeProductPromoterRel alternativeProductPromoterRel = new AlternativeProductPromoterRel(null);
    public static AlternativeProductSplicingRel alternativeProductSplicingRel = new AlternativeProductSplicingRel(null);
    public static AlternativeProductRibosomalFrameshiftingRel alternativeProductRibosomalFrameshiftingRel = new AlternativeProductRibosomalFrameshiftingRel(null);
    
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
            System.out.println("This program expects the following parameters:\n"
                    + "1. Bio4j DB folder \n"
                    + "2. Batch inserter .properties file name");
        } else {
            BatchInserter inserter = null;
            BatchInserterIndexProvider indexProvider = null;

            Map<String, Object> alternativeProductProperties = new HashMap<>();
            Map<String, Object> sequenceCautionProperties = new HashMap<>();

            long alternativeProductInitiationId;
            long alternativeProductPromoterId;
            long alternativeProductSplicingId;
            long alternativeProductRibosomalFrameshiftingId;
            long seqCautionErroneousInitiationId;
            long seqCautionErroneousTranslationId;
            long seqCautionFrameshiftId;
            long seqCautionErroneousTerminationId;
            long seqCautionMiscellaneousDiscrepancyId;
            long seqCautionErroneousGeneModelPredictionId;

            try {

                // This block configure the logger with handler and formatter
                fh = new FileHandler("InitBio4jDB.log", false);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                
                // create the batch inserter
                inserter = BatchInserters.inserter(args[0], MapUtil.load(new File(args[1])));
                
                // create the batch index service
                indexProvider = new LuceneBatchInserterIndexProvider(inserter);

                BatchInserterIndex nodeTypeIndex = indexProvider.nodeIndex(Bio4jManager.NODE_TYPE_INDEX_NAME,
                                                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
                BatchInserterIndex mainNodesIndex = indexProvider.nodeIndex(Bio4jManager.MAIN_NODES_INDEX_NAME,
                                                        MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));

                //----------------------------------------------------------------------------------------------------------------
                //A few relationships/nodes which
                //must be initialized first
                //------------------ALTERNATIVE PRODUCTS--------------------
                alternativeProductProperties.put(AlternativeProductNode.NODE_TYPE_PROPERTY, AlternativeProductNode.NODE_TYPE);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductInitiationId = inserter.createNode(alternativeProductProperties);                
                nodeTypeIndex.add(alternativeProductInitiationId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, AlternativeProductNode.NODE_TYPE));
                mainNodesIndex.add(alternativeProductInitiationId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_INITIATION));

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductPromoterId = inserter.createNode(alternativeProductProperties);
                nodeTypeIndex.add(alternativeProductPromoterId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, AlternativeProductNode.NODE_TYPE));
                mainNodesIndex.add(alternativeProductPromoterId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_PROMOTER));

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductSplicingId = inserter.createNode(alternativeProductProperties);
                nodeTypeIndex.add(alternativeProductSplicingId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, AlternativeProductNode.NODE_TYPE));
                mainNodesIndex.add(alternativeProductSplicingId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_SPLICING));

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductRibosomalFrameshiftingId = inserter.createNode(alternativeProductProperties);                
                nodeTypeIndex.add(alternativeProductRibosomalFrameshiftingId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, AlternativeProductNode.NODE_TYPE));
                mainNodesIndex.add(alternativeProductRibosomalFrameshiftingId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.ALTERNATIVE_PRODUCT_RIBOSOMAL_FRAMESHIFTING));

                //---------------------SEQUENCE CAUTION------------------------
                sequenceCautionProperties.put(SequenceCautionNode.NODE_TYPE_PROPERTY, SequenceCautionNode.NODE_TYPE);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousInitiationId = inserter.createNode(sequenceCautionProperties);
                nodeTypeIndex.add(seqCautionErroneousInitiationId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SequenceCautionNode.NODE_TYPE));
                mainNodesIndex.add(seqCautionErroneousInitiationId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_INITIATION));

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousTranslationId = inserter.createNode(sequenceCautionProperties);
                nodeTypeIndex.add(seqCautionErroneousTranslationId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SequenceCautionNode.NODE_TYPE));
                mainNodesIndex.add(seqCautionErroneousTranslationId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_TRANSLATION));

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionFrameshiftId = inserter.createNode(sequenceCautionProperties);
                nodeTypeIndex.add(seqCautionFrameshiftId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SequenceCautionNode.NODE_TYPE));
                mainNodesIndex.add(seqCautionFrameshiftId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_FRAMESHIFT));

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousTerminationId = inserter.createNode(sequenceCautionProperties);
                nodeTypeIndex.add(seqCautionErroneousTerminationId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SequenceCautionNode.NODE_TYPE));
                mainNodesIndex.add(seqCautionErroneousTerminationId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_TERMINATION));

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionMiscellaneousDiscrepancyId = inserter.createNode(sequenceCautionProperties);
                nodeTypeIndex.add(seqCautionMiscellaneousDiscrepancyId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SequenceCautionNode.NODE_TYPE));
                mainNodesIndex.add(seqCautionMiscellaneousDiscrepancyId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_MISCELLANEOUS_DISCREPANCY));

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousGeneModelPredictionId = inserter.createNode(sequenceCautionProperties);
                nodeTypeIndex.add(seqCautionErroneousGeneModelPredictionId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SequenceCautionNode.NODE_TYPE));
                mainNodesIndex.add(seqCautionErroneousGeneModelPredictionId, MapUtil.map(Bio4jManager.MAIN_NODES_INDEX_NAME, Bio4jManager.SEQUENCE_CAUTION_ERRONEOUS_GENE_MODEL_PREDICTION));
                //---------------------------------------------------------------------------------------------------------------

            } catch (Exception e) {
                
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            }finally {

                try {

                    // shutdown, makes sure all changes are written to disk
                    indexProvider.shutdown();
                    inserter.shutdown();
                    

                    //closing logger file handler
                    fh.close();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage());
                    StackTraceElement[] trace = e.getStackTrace();
                    for (StackTraceElement stackTraceElement : trace) {
                        logger.log(Level.SEVERE, stackTraceElement.toString());
                    }
                    //closing logger file handler
                    fh.close();
                }


            }


        }

    }
    
}
