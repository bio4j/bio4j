/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.programs;

import com.era7.bioinfo.bio4jmodel.nodes.FeatureTypeNode;
import com.era7.bioinfo.bio4jmodel.nodes.ProteinNode;
import com.era7.bioinfo.bio4jmodel.relationships.features.*;
import com.era7.bioinfo.bio4j.CommonData;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.era7.lib.era7xmlapi.model.XMLElement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jdom.Element;
import org.neo4j.index.lucene.LuceneIndexBatchInserter;
import org.neo4j.index.lucene.LuceneIndexBatchInserterImpl;
import org.neo4j.kernel.impl.batchinsert.BatchInserter;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

/**
 *
 * @author ppareja
 */
public class ImportFeatures implements Executable{

    private static final Logger logger = Logger.getLogger("ImportFeatures");
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
                    + "1. Uniprot xml filename \n");
        } else {
            File inFile = new File(args[0]);

            //boolean transactionDone = true;

            BatchInserter inserter = null;
            LuceneIndexBatchInserter indexService = null;
            String accessionSt = "";

            try {

                // This block configures the logger with handler and formatter
                fh = new FileHandler("ImportFeatures" + args[0].split("\\.")[0] + ".log", false);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                logger.addHandler(fh);
                logger.setLevel(Level.ALL);

                // create the batch inserter
                inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));

                // create the batch index service
                indexService = new LuceneIndexBatchInserterImpl(inserter);

                //------------------nodes properties maps-----------------------------------
                Map<String, Object> featureTypeProperties = new HashMap<String, Object>();
                //---------------------------------------------------------------------

                //-------------------relationships properties maps--------------------------
                Map<String, Object> featureProperties = new HashMap<String, Object>();
                //----------------------------------------------------------------------------

                //--------------------------------relationships------------------------------------------
                ActiveSiteFeatureRel activeSiteFeatureRel = new ActiveSiteFeatureRel(null);
                BindingSiteFeatureRel bindingSiteFeatureRel = new BindingSiteFeatureRel(null);
                CalciumBindingRegionFeatureRel calciumBindingRegionFeatureRel = new CalciumBindingRegionFeatureRel(null);
                ChainFeatureRel chainFeatureRel = new ChainFeatureRel(null);
                CoiledCoilRegionFeatureRel coiledCoilRegionFeatureRel = new CoiledCoilRegionFeatureRel(null);
                CompositionallyBiasedRegionFeatureRel compositionallyBiasedRegionFeatureRel = new CompositionallyBiasedRegionFeatureRel(null);
                CrossLinkFeatureRel crossLinkFeatureRel = new CrossLinkFeatureRel(null);
                DisulfideBondFeatureRel disulfideBondFeatureRel = new DisulfideBondFeatureRel(null);
                DnaBindingRegionFeatureRel dnaBindingRegionFeatureRel = new DnaBindingRegionFeatureRel(null);
                DomainFeatureRel domainFeatureRel = new DomainFeatureRel(null);
                GlycosylationSiteFeatureRel glycosylationSiteFeatureRel = new GlycosylationSiteFeatureRel(null);
                HelixFeatureRel helixFeatureRel = new HelixFeatureRel(null);
                InitiatorMethionineFeatureRel initiatorMethionineFeatureRel = new InitiatorMethionineFeatureRel(null);
                IntramembraneRegionFeatureRel intramembraneRegionFeatureRel = new IntramembraneRegionFeatureRel(null);
                LipidMoietyBindingRegionFeatureRel lipidMoietyBindingRegionFeatureRel = new LipidMoietyBindingRegionFeatureRel(null);
                MetalIonBindingSiteFeatureRel metalIonBindingSiteFeatureRel = new MetalIonBindingSiteFeatureRel(null);
                ModifiedResidueFeatureRel modifiedResidueFeatureRel = new ModifiedResidueFeatureRel(null);
                MutagenesisSiteFeatureRel mutagenesisSiteFeatureRel = new MutagenesisSiteFeatureRel(null);
                NonConsecutiveResiduesFeatureRel nonConsecutiveResiduesFeatureRel = new NonConsecutiveResiduesFeatureRel(null);
                NonStandardAminoAcidFeatureRel nonStandardAminoAcidFeatureRel = new NonStandardAminoAcidFeatureRel(null);
                NonTerminalResidueFeatureRel nonTerminalResidueFeatureRel = new NonTerminalResidueFeatureRel(null);
                NucleotidePhosphateBindingRegionFeatureRel nucleotidePhosphateBindingRegionFeatureRel = new NucleotidePhosphateBindingRegionFeatureRel(null);
                PeptideFeatureRel peptideFeatureRel = new PeptideFeatureRel(null);
                PropeptideFeatureRel propeptideFeatureRel = new PropeptideFeatureRel(null);
                RegionOfInterestFeatureRel regionOfInterestFeatureRel = new RegionOfInterestFeatureRel(null);
                RepeatFeatureRel repeatFeatureRel = new RepeatFeatureRel(null);
                SequenceConflictFeatureRel sequenceConflictFeatureRel = new SequenceConflictFeatureRel(null);
                SequenceVariantFeatureRel sequenceVariantFeatureRel = new SequenceVariantFeatureRel(null);
                ShortSequenceMotifFeatureRel shortSequenceMotifFeatureRel = new ShortSequenceMotifFeatureRel(null);
                SignalPeptideFeatureRel signalPeptideFeatureRel = new SignalPeptideFeatureRel(null);
                SiteFeatureRel siteFeatureRel = new SiteFeatureRel(null);
                SpliceVariantFeatureRel spliceVariantFeatureRel = new SpliceVariantFeatureRel(null);
                StrandFeatureRel strandFeatureRel = new StrandFeatureRel(null);
                TopologicalDomainFeatureRel topologicalDomainFeatureRel = new TopologicalDomainFeatureRel(null);
                TransitPeptideFeatureRel transitPeptideFeatureRel = new TransitPeptideFeatureRel(null);
                TransmembraneRegionFeatureRel transmembraneRegionFeatureRel = new TransmembraneRegionFeatureRel(null);
                TurnFeatureRel turnFeatureRel = new TurnFeatureRel(null);
                UnsureResidueFeatureRel unsureResidueFeatureRel = new UnsureResidueFeatureRel(null);
                ZincFingerRegionFeatureRel zincFingerRegionFeatureRel = new ZincFingerRegionFeatureRel(null);
                //------------------------------------------------------------------------------------------------


                BufferedReader reader = new BufferedReader(new FileReader(inFile));
                String line = null;
                StringBuilder entryStBuilder = new StringBuilder();


                int contador = 1;
                int limitForPrintingOut = 10000;
                int limitForClosingBatchInserter = 100000;

                while ((line = reader.readLine()) != null) {
                    if (line.trim().startsWith("<" + CommonData.ENTRY_TAG_NAME)) {

                        while (!line.trim().startsWith("</" + CommonData.ENTRY_TAG_NAME + ">")) {
                            entryStBuilder.append(line);
                            line = reader.readLine();
                        }
                        //linea final del organism
                        entryStBuilder.append(line);
                        //System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
                        XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
                        entryStBuilder.delete(0, entryStBuilder.length());

                        accessionSt = entryXMLElem.asJDomElement().getChildText(CommonData.ENTRY_ACCESSION_TAG_NAME);

                        long currentProteinId = indexService.getSingleNode(ProteinNode.PROTEIN_ACCESSION_INDEX, accessionSt);

                        //--------------------------------features----------------------------------------------------
                        List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(CommonData.FEATURE_TAG_NAME);
                        for (Element featureElem : featuresList) {
                            String featureTypeSt = featureElem.getAttributeValue(CommonData.FEATURE_TYPE_ATTRIBUTE);
                            long featureTypeNodeId = indexService.getSingleNode(FeatureTypeNode.FEATURE_TYPE_NAME_INDEX, featureTypeSt);
                            if (featureTypeNodeId < 0) {
                                featureTypeProperties.put(FeatureTypeNode.NAME_PROPERTY, featureTypeSt);
                                featureTypeNodeId = inserter.createNode(featureTypeProperties);
                                indexService.index(featureTypeNodeId, FeatureTypeNode.FEATURE_TYPE_NAME_INDEX, featureTypeSt);
                            }

                            String featureDescSt = featureElem.getAttributeValue(CommonData.FEATURE_DESCRIPTION_ATTRIBUTE);
                            if (featureDescSt == null) {
                                featureDescSt = "";
                            }
                            String featureIdSt = featureElem.getAttributeValue(CommonData.FEATURE_ID_ATTRIBUTE);
                            if (featureIdSt == null) {
                                featureIdSt = "";
                            }
                            String featureStatusSt = featureElem.getAttributeValue(CommonData.STATUS_ATTRIBUTE);
                            if (featureStatusSt == null) {
                                featureStatusSt = "";
                            }
                            String featureEvidenceSt = featureElem.getAttributeValue(CommonData.EVIDENCE_ATTRIBUTE);
                            if (featureEvidenceSt == null) {
                                featureEvidenceSt = "";
                            }
                            
                            Element locationElem = featureElem.getChild(CommonData.FEATURE_LOCATION_TAG_NAME);
                            Element positionElem = locationElem.getChild(CommonData.FEATURE_POSITION_TAG_NAME);
                            String beginFeatureSt = "";
                            String endFeatureSt = "";
                            if (positionElem != null) {
                                beginFeatureSt = positionElem.getAttributeValue(CommonData.FEATURE_POSITION_POSITION_ATTRIBUTE);
                                endFeatureSt = beginFeatureSt;
                            }else{
                                beginFeatureSt = locationElem.getChild(CommonData.FEATURE_LOCATION_BEGIN_TAG_NAME).getAttributeValue(CommonData.FEATURE_LOCATION_POSITION_ATTRIBUTE);
                                endFeatureSt = locationElem.getChild(CommonData.FEATURE_LOCATION_END_TAG_NAME).getAttributeValue(CommonData.FEATURE_LOCATION_POSITION_ATTRIBUTE);
                            }

                            if(beginFeatureSt == null){
                                beginFeatureSt = "";
                            }
                            if(endFeatureSt == null){
                                endFeatureSt = "";
                            }

                            String originalSt = featureElem.getChildText(CommonData.FEATURE_ORIGINAL_TAG_NAME);
                            String variationSt = featureElem.getChildText(CommonData.FEATURE_VARIATION_TAG_NAME);
                            if (originalSt == null) {
                                originalSt = "";
                            }
                            if (variationSt == null) {
                                variationSt = "";
                            }
                            String featureRefSt = featureElem.getAttributeValue(CommonData.FEATURE_REF_ATTRIBUTE);
                            if (featureRefSt == null) {
                                featureRefSt = "";
                            }

                            featureProperties.put(BasicFeatureRel.DESCRIPTION_PROPERTY, featureDescSt);
                            featureProperties.put(BasicFeatureRel.ID_PROPERTY, featureIdSt);
                            featureProperties.put(BasicFeatureRel.EVIDENCE_PROPERTY, featureEvidenceSt);
                            featureProperties.put(BasicFeatureRel.STATUS_PROPERTY, featureStatusSt);
                            featureProperties.put(BasicFeatureRel.BEGIN_PROPERTY, beginFeatureSt);
                            featureProperties.put(BasicFeatureRel.END_PROPERTY, endFeatureSt);
                            featureProperties.put(BasicFeatureRel.ORIGINAL_PROPERTY, originalSt);
                            featureProperties.put(BasicFeatureRel.VARIATION_PROPERTY, variationSt);
                            featureProperties.put(BasicFeatureRel.REF_PROPERTY, featureRefSt);


                            if (featureTypeSt.equals(ActiveSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, activeSiteFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(BindingSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, bindingSiteFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(CrossLinkFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, crossLinkFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(GlycosylationSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, glycosylationSiteFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(InitiatorMethionineFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, initiatorMethionineFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(LipidMoietyBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, lipidMoietyBindingRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(MetalIonBindingSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, metalIonBindingSiteFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(ModifiedResidueFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, modifiedResidueFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(NonStandardAminoAcidFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, nonStandardAminoAcidFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(NonTerminalResidueFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, nonTerminalResidueFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(PeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, peptideFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(UnsureResidueFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, unsureResidueFeatureRel, featureProperties);
                            }else if (featureTypeSt.equals(MutagenesisSiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, mutagenesisSiteFeatureRel, featureProperties);
                            }else if (featureTypeSt.equals(SequenceVariantFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, sequenceVariantFeatureRel, featureProperties);
                            }else if (featureTypeSt.equals(CalciumBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, calciumBindingRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(ChainFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, chainFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(CoiledCoilRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, coiledCoilRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(CompositionallyBiasedRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, compositionallyBiasedRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(DisulfideBondFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, disulfideBondFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(DnaBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, dnaBindingRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(DomainFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, domainFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(HelixFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, helixFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(IntramembraneRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, intramembraneRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(NonConsecutiveResiduesFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, nonConsecutiveResiduesFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(NucleotidePhosphateBindingRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, nucleotidePhosphateBindingRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(PropeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, propeptideFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(RegionOfInterestFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, regionOfInterestFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(RepeatFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, repeatFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(ShortSequenceMotifFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, shortSequenceMotifFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(SignalPeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, signalPeptideFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(SpliceVariantFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, spliceVariantFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(StrandFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, strandFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(TopologicalDomainFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, topologicalDomainFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(TransitPeptideFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, transitPeptideFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(TransmembraneRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, transmembraneRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(ZincFingerRegionFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, zincFingerRegionFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(SiteFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)) {
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, siteFeatureRel, featureProperties);
                            } else if (featureTypeSt.equals(TurnFeatureRel.UNIPROT_ATTRIBUTE_TYPE_VALUE)){
                                inserter.createRelationship(currentProteinId, featureTypeNodeId, turnFeatureRel, featureProperties);
                            }

                            inserter.createRelationship(currentProteinId, featureTypeNodeId, sequenceConflictFeatureRel, featureProperties);

                        }
                        //---------------------------------------------------------------------------------------


                        contador++;
                        if ((contador % limitForPrintingOut) == 0) {
                            logger.log(Level.INFO, (contador + " proteins updated with features!!"));
                        }

                        if((contador % limitForClosingBatchInserter) == 0){
                            inserter.shutdown();
                            indexService.shutdown();
                            // create the batch inserter
                            inserter = new BatchInserterImpl(CommonData.DATABASE_FOLDER, BatchInserterImpl.loadProperties(CommonData.PROPERTIES_FILE_NAME));
                            // create the batch index service
                            indexService = new LuceneIndexBatchInserterImpl(inserter);
                        }

                    }
                }


            } catch (Exception e) {
                logger.log(Level.SEVERE, ("Exception retrieving protein " + accessionSt));
                logger.log(Level.SEVERE, e.getMessage());
                StackTraceElement[] trace = e.getStackTrace();
                for (StackTraceElement stackTraceElement : trace) {
                    logger.log(Level.SEVERE, stackTraceElement.toString());
                }
            }
            finally{
                //closing logger file handler
                fh.close();
                // shutdown, makes sure all changes are written to disk
                inserter.shutdown();
                indexService.shutdown();
            }
        }

    }
}
