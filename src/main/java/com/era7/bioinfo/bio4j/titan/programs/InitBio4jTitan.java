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
        graph.makeType().name(ProteinNode.UNIGENE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.EMBL_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.REFSEQ_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENE_NAMES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ENSEMBL_PLANTS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DBASE_ECOLI_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.AARHUS_GHENT_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.AGD_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ALLERGOME_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ANU_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ARACHNOSERVER_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.BGEE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.BINDING_DB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.BIOCYC_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.BRENDA_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CAZY_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CGD_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CHEMBL_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CLEANEX_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.COMPLUYEAST_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CONOSERVER_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CORNEA_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CTD_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.CYGD_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DBSNP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DDBJ_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DICTY_BASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DIP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DISPROT_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DMDM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DNASU_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DOSAC_COBS_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.DRUGBANK_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ECHOBASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ECOGENE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.EGGNOG_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ENSEMBL_BACTERIA_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ENSEMBL_FUNGI_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ENSEMBL_METAZOA_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ENSEMBL_PROTISTS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.EUHCVDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.EUPATHDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.EVOLUTIONARY_TRACE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.FLYBASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENATLAS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENBANK_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENE3D_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENECARDS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENEFARM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENEID_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENETREE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENEVESTIGATOR_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENOLIST_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENOME_REVIEWS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GENOME_RNAI_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GERMONLINE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GLYCOSUITEDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GPCRDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.GRAMENE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HINVDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HAMAP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HGNC_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HOGENOM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HOVERGEN_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HPA_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HSSP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.HUGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.IMGT_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.INPARANOID_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.INTACT_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.IPI_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.KO_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.KEGG_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.LEGIO_LIST_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.LEPROMA_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MAIZE_GDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MEROPS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MGI_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MICADO_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MIM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MINT_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MODBASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.MYCOCLAP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.NEXTBIO_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.NEXTPROT_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.OGP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.OMA_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ORPHANET_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ORTHODB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PANTHER_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PATHWAY_INTERACTION_DB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PATRIC_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PAXDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PDBJ_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PDBSUM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PEPTIDE_ATLAS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PEROXIBASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PHARMGKB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PHCI_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PHOSPHOSITE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PHOS_SITE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PHYLOME_DB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PIR_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PIRSF_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PMAP_CUTDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PMMA_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.POMBASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PPTASEDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PRIDE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PRINTS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PRODOM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PROMEX_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PROSITE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PROT_CLUST_DB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PROTEIN_MODEL_PORTAL_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PROTONET_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.PSEUDO_CAP_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.RAT_HEART_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.RCSB_PDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.REBASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.REPRODUCTION_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.RGD_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ROUGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SBKB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SGD_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SIENA_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SMART_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SMR_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SOURCE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.STRING_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SUPFAM_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.SWISS_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.TAIR_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.TCDB_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.TIGRFAMS_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.TUBERCULIST_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.UCD_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.UCSC_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.UNIPATHWAY_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.VECTOR_BASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.WORLD_2DPAGE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.WORM_BASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.XEN_BASE_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        graph.makeType().name(ProteinNode.ZFIN_REFERENCES_PROPERTY).dataType(String.class).indexed().makePropertyKey();
        
        
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
