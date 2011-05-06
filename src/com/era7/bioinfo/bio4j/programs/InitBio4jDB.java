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
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4j.CommonData;
import com.era7.bioinfo.bio4jmodel.nodes.AlternativeProductNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinSelfInteractionsNode;
import com.era7.bioinfo.bio4jmodel.nodes.SequenceCautionNode;
import com.era7.bioinfo.bio4jmodel.relationships.aproducts.*;
import com.era7.bioinfo.bio4jmodel.relationships.protein.*;
import com.era7.bioinfo.bio4jmodel.relationships.sc.*;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class InitBio4jDB implements Executable {

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
    public static ProteinSelfInteractionsRel proteinSelfInteractionsRel = new ProteinSelfInteractionsRel(null);

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {

        if (args.length != 0) {
            System.out.println("This program does not expect any parameter\n");
        } else {
            BatchInserter inserter = null;
            //LuceneIndexBatchInserter indexService = null;

            Map<String, Object> alternativeProductProperties = new HashMap<String, Object>();
            Map<String, Object> sequenceCautionProperties = new HashMap<String, Object>();
            Map<String, Object> proteinSelfInteractionsProperties = new HashMap<String, Object>();

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
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                // create the batch index service
                //indexService = new LuceneIndexBatchInserterImpl(inserter);

                //----------------------------------------------------------------------------------------------------------------
                //A few relationships/nodes which
                //must be initialized first
                //------------------ALTERNATIVE PRODUCTS--------------------
                alternativeProductProperties.put(AlternativeProductNode.NODE_TYPE_PROPERTY, AlternativeProductNode.NODE_TYPE);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductInitiationId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductInitiationId, alternativeProductInitiationRel, null);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductPromoterId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductPromoterId, alternativeProductPromoterRel, null);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductSplicingId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductSplicingId, alternativeProductSplicingRel, null);

                alternativeProductProperties.put(AlternativeProductNode.NAME_PROPERTY, AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                alternativeProductRibosomalFrameshiftingId = inserter.createNode(alternativeProductProperties);
                inserter.createRelationship(inserter.getReferenceNode(), alternativeProductRibosomalFrameshiftingId, alternativeProductRibosomalFrameshiftingRel, null);

                //---------------------SEQUENCE CAUTION------------------------
                sequenceCautionProperties.put(SequenceCautionNode.NODE_TYPE_PROPERTY, SequenceCautionNode.NODE_TYPE);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousInitiationId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousInitiationId, erroneousInitiationRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousTranslationId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousTranslationId, erroneousTranslationRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionFrameshiftId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionFrameshiftId, frameshiftRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousTerminationId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousTerminationId, erroneousTerminationRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionMiscellaneousDiscrepancyId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionMiscellaneousDiscrepancyId, miscellaneousDiscrepancyRel, null);

                sequenceCautionProperties.put(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
                seqCautionErroneousGeneModelPredictionId = inserter.createNode(sequenceCautionProperties);
                inserter.createRelationship(inserter.getReferenceNode(), seqCautionErroneousGeneModelPredictionId, erroneousGeneModelPredictionRel, null);
                //---------------------------------------------------------------------------------------------------------------

                //Node and relationship that will lead to protein self interactions 

                proteinSelfInteractionsProperties.put(ProteinSelfInteractionsNode.NODE_TYPE_PROPERTY, ProteinSelfInteractionsNode.NODE_TYPE);
                long proteinSelfInteractionsNodeId = inserter.createNode(proteinSelfInteractionsProperties);
                inserter.createRelationship(inserter.getReferenceNode(), proteinSelfInteractionsNodeId,
                        proteinSelfInteractionsRel, null);
                //----------------------------------------------------------
                
            } catch (Exception e) {
                
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            }finally {

                try {

                    // shutdown, makes sure all changes are written to disk
                    inserter.shutdown();
                    //indexService.shutdown();

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
