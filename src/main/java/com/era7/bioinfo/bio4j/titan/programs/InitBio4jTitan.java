/*
 * Copyright (C) 2010-2013  "Bio4j"
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
package com.era7.bioinfo.bio4j.titan.programs;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductInitiationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductPromoterRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductRibosomalFrameshiftingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductSplicingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinErroneousGeneModelPredictionRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinErroneousInitiationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinErroneousTerminationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinErroneousTranslationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinFrameshiftRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.ProteinMiscellaneousDiscrepancyRel;
import com.era7.bioinfo.bio4j.model.enums.UniprotDBXref;
import com.era7.bioinfo.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import com.era7.lib.bioinfo.bioinfoutil.Executable;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class InitBio4jTitan implements Executable {

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
            System.out.println("This program expects the following parameters:\n"
                    + "1. Bio4j DB folder \n");
        } else {


            String folder = args[0];

            //----config---
            Configuration conf = new BaseConfiguration();
            conf.setProperty("storage.directory", folder);
            conf.setProperty("storage.backend", "local");

            System.out.println("Creating DB...");

            Bio4jManager manager = new Bio4jManager(conf);
            TitanGraph graph = manager.getGraph();


            System.out.println("Creating indices...");
            createIndices(graph);
            
            System.out.println("Creating non functiontal keys...");
            createNonFunctionalKeys(graph);    
            
            System.out.println("Creating utility nodes...");
            createAlternativeProductNodes(manager);
            createSequenceCautionNodes(manager);
            
            System.out.println("Shutting down manager...");
            graph.shutdown();            
            
            System.out.println("Done! :)");

        }
    }
    
    private static void createAlternativeProductNodes(Bio4jManager manager){
        
        //--Alternative product INITIATION----
        AlternativeProductNode initiationNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        initiationNode.setName(AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Alternative product PROMOTER----
        AlternativeProductNode promoterNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        promoterNode.setName(AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Alternative product RIBOSOMAL FRAMESHIFTING----
        AlternativeProductNode ribosomalFrameshiftingNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        ribosomalFrameshiftingNode.setName(AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Alternative product SPLICING----
        AlternativeProductNode splicingNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        splicingNode.setName(AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
    }
    
    private static void createSequenceCautionNodes(Bio4jManager manager){
        
        //--Sequence caution ERRONEOUS GENE MODEL PREDICTION----
        SequenceCautionNode proteinErrGeneModelPredNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrGeneModelPredNode.setName(ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution ERRONEOUS INITIATION----
        SequenceCautionNode proteinErrInitiationNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrInitiationNode.setName(ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution ERRONEOUS TRANSLATION----
        SequenceCautionNode proteinErrTranslationNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrTranslationNode.setName(ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution ERRONEOUS TERMINATION----
        SequenceCautionNode proteinErrTerminationNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrTerminationNode.setName(ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution FRAMESHIFT----
        SequenceCautionNode proteinFrameshiftNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinFrameshiftNode.setName(ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution MISCELLANEOUS DISCREPANCY----
        SequenceCautionNode proteinMiscDiscrepancyNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinMiscDiscrepancyNode.setName(ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
    }
    
    private static void createNonFunctionalKeys(TitanGraph graph){
        
        //---GO TERM---       
        graph.makeType().name(GoTermNode.ALTERNATIVE_IDS_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        //---PROTEIN---
        graph.makeType().name(ProteinNode.ALTERNATIVE_ACCESSIONS_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.UNIGENE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.EMBL.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.REFSEQ.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENE_NAMES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ENSEMBL_PLANTS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ALLERGOME.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ARACHNO_SERVER.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.BGEE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.BINDING_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.BIOCYC.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.BRENDA.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CAZY.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CGD.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CHEMBL.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CLEANEX.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.COMPLUYEAST_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CONO_SERVER.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CTD.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.CYGD.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DBSNP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DDBJ.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DICTY_BASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DIP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DISPROT.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DMDM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DNASU.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DOSAC_COBS_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.DRUG_BANK.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ECHO_BASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ECO_GENE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.EGGNOG.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ENSEMBL_BACTERIA.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ENSEMBL_FUNGI.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ENSEMBL_METAZOA.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ENSEMBL_PROTISTS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.EUHCV_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.EUPATH_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.EVOLUTIONARY_TRACE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.FLYBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENATLAS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENBANK.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENE3D.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENECARDS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENEFARM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENEID.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENETREE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENEVESTIGATOR.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENOLIST.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENOME_REVIEWS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GENOME_RNAI.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GERMONLINE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GLYCO_SUITE_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GPCR_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.GRAMENE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HINV_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HAMAP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HGNC.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HOGENOM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HOVERGEN.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HPA.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HSSP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.HUGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.IMGT.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.INPARANOID.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.INTACT.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.IPI.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.KO.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.KEGG.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.LEGIOLIST.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.LEPROMA.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MAIZEGD_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MEROPS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MGI.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MICADO.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MIM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MINT.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MODBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.MYCOCLAP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.NEXTBIO.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.NEXTPROT.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.OGP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.OMA.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ORPHANET.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ORTHO_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PANTHER.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PATHWAY_INTERACTION_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PATRIC.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PAXDB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PDB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PDBJ.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PDBSUM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PEPTIDE_ATLAS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PEROXIBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PHARM_GKB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PHOSPHOSITE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PHOS_SITE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PHYLOME_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PIR.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PIRSF.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PMAP_CUT_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.POMBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PPTASE_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PRIDE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PRINTS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PRODOM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PROMEX.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PROSITE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PROT_CLUST_DB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PROTEIN_MODEL_PORTAL.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PROTONET.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.PSEUDOCAP.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.RCSBPDB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.REBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.REPRODUCTION_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.RGD.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ROUGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SBKB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SGD.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SMART.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SMR.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SOURCE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.STRING.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SUPFAM.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.SWISS_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.TAIR.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.TCDB.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.TIGRFAMS.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.TUBERCULIST.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.UCD_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.UCSC.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.UNIPATHWAY.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.VECTORBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.WORLD_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.WORMBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.XENBASE.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(UniprotDBXref.ZFIN.getProteinReferencePropertyName()).dataType(String.class).indexed().makePropertyKey();
        
        
        //---NCBI TAXON---                
        graph.makeType().name(NCBITaxonNode.GI_IDS_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(NCBITaxonNode.OLD_TAX_IDS_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        
    }

    private static void createIndices(TitanGraph graph) {

        //------------------------------------------------------------------------
        //--------------------------creating indices------------------------------

        //---NODE TYPE---
        graph.createKeyIndex(BasicEntity.NODE_TYPE_PROPERTY, Vertex.class);

        //---ARTICLE----
        graph.createKeyIndex(ArticleNode.TITLE_PROPERTY, Vertex.class);
        graph.createKeyIndex(ArticleNode.DOI_ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(ArticleNode.MEDLINE_ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(ArticleNode.PUBMED_ID_PROPERTY, Vertex.class);

        //---BOOK----
        graph.createKeyIndex(BookNode.NAME_PROPERTY, Vertex.class);

        //---DB----
        graph.createKeyIndex(DBNode.NAME_PROPERTY, Vertex.class);

        //---JOURNAL---
        graph.createKeyIndex(JournalNode.NAME_PROPERTY, Vertex.class);

        //---ONLINE ARTICLE---
        graph.createKeyIndex(OnlineArticleNode.TITLE_PROPERTY, Vertex.class);

        //---ONLINE JOURNAL---
        graph.createKeyIndex(OnlineJournalNode.NAME_PROPERTY, Vertex.class);

        //---PATENT---
        graph.createKeyIndex(PatentNode.NUMBER_PROPERTY, Vertex.class);

        //---PUBLISHER---
        graph.createKeyIndex(PublisherNode.NAME_PROPERTY, Vertex.class);

        //---SUBMISSION---
        graph.createKeyIndex(SubmissionNode.TITLE_PROPERTY, Vertex.class);

        //---THESIS----
        graph.createKeyIndex(ThesisNode.TITLE_PROPERTY, Vertex.class);

        //---NCBI TAXON--
        graph.createKeyIndex(NCBITaxonNode.TAX_ID_PROPERTY, Vertex.class);
        //gi index is temporarily missing

        //---REACTOME TERM---
        graph.createKeyIndex(ReactomeTermNode.ID_PROPERTY, Vertex.class);

        //---GENOME ELEMENT---
        graph.createKeyIndex(GenomeElementNode.VERSION_PROPERTY, Vertex.class);

        //---ALTERNATIVE PRODUCT---
        graph.createKeyIndex(AlternativeProductNode.NAME_PROPERTY, Vertex.class);

        //---CITY---
        graph.createKeyIndex(CityNode.NAME_PROPERTY, Vertex.class);

        //---COMMENT TYPE---
        graph.createKeyIndex(CommentTypeNode.NAME_PROPERTY, Vertex.class);

        //---CONSORTIUM---
        graph.createKeyIndex(ConsortiumNode.NAME_PROPERTY, Vertex.class);

        //---COUNTRY----
        graph.createKeyIndex(CountryNode.NAME_PROPERTY, Vertex.class);

        //---DATASET---
        graph.createKeyIndex(DatasetNode.NAME_PROPERTY, Vertex.class);

        //---ENZYME---            
        graph.createKeyIndex(EnzymeNode.ID_PROPERTY, Vertex.class);

        //---FEATURE TYPE--
        graph.createKeyIndex(FeatureTypeNode.NAME_PROPERTY, Vertex.class);

        //---GO TERM----
        graph.createKeyIndex(GoTermNode.ID_PROPERTY, Vertex.class);

        //---INSTITUTE---
        graph.createKeyIndex(InstituteNode.NAME_PROPERTY, Vertex.class);

        //---INTERPRO---
        graph.createKeyIndex(InterproNode.ID_PROPERTY, Vertex.class);

        //---ISOFORM---
        graph.createKeyIndex(IsoformNode.ID_PROPERTY, Vertex.class);

        //---KEYWORD---
        graph.createKeyIndex(KeywordNode.ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(KeywordNode.NAME_PROPERTY, Vertex.class);

        //---ORGANISM---
        graph.createKeyIndex(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(OrganismNode.SCIENTIFIC_NAME_PROPERTY, Vertex.class);

        //---PERSON---
        graph.createKeyIndex(PersonNode.NAME_PROPERTY, Vertex.class); //This index was fulltext with Neo4j

        //---PFAM---
        graph.createKeyIndex(PfamNode.ID_PROPERTY, Vertex.class);

        //---PROTEIN---
        //Ensmbl plants index is missing here
        //Gene names fulltext index is missing here
        graph.createKeyIndex(ProteinNode.ACCESSION_PROPERTY, Vertex.class);
        graph.createKeyIndex(ProteinNode.FULL_NAME_PROPERTY, Vertex.class); //This index was fulltext with Neo4j

        //---SEQUENCE CAUTION---
        graph.createKeyIndex(SequenceCautionNode.NAME_PROPERTY, Vertex.class);

        //---SUBCELLULAR LOCATION---
        graph.createKeyIndex(SubcellularLocationNode.NAME_PROPERTY, Vertex.class);

        //---TAXON---
        graph.createKeyIndex(TaxonNode.NAME_PROPERTY, Vertex.class);       
        

    }
}
