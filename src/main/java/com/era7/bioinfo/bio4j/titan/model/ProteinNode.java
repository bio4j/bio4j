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
package com.era7.bioinfo.bio4j.titan.model;

import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinNode extends com.era7.bioinfo.bio4j.blueprints.model.nodes.ProteinNode{
    
    public ProteinNode(Vertex v){
        super(v);
    }
    
    
    //----------------------------GETTERS-----------------------------------
    //---------------------------------------------------------------------
    
    @Override
    public String[] getEMBLReferences(){
        return getRefs(EMBL_REFERENCES_PROPERTY);        
    }
    
    @Override
    public String[] getEnsemblPlantsReferences(){
        return getRefs(ENSEMBL_PLANTS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getRefseqReferences(){
        return getRefs(REFSEQ_REFERENCES_PROPERTY);
    }

    @Override
    public String[] getAlternativeAcessions(){
        return getRefs(ALTERNATIVE_ACCESSIONS_PROPERTY);
    }
    
    @Override
    public String[] getEnsemblReferences(){
        return getRefs(ENSEMBL_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPIRReferences(){
        return getRefs(PIR_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getKeggReferences(){
        return getRefs(KEGG_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getArrayExpressReferences(){
        return getRefs(ARRAY_EXPRESS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getUniGeneReferences(){
        return getRefs(UNIGENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDBaseEColiReferences(){
        return getRefs(DBASE_ECOLI_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getAarhusGhent2DPageReferences(){
        return getRefs(AARHUS_GHENT_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getAGDReferences(){
        return getRefs(AGD_REFERENCES_PROPERTY);
    }
   
    @Override
    public String[] getANU2DPageReferences(){
        return getRefs(ANU_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getArachnoServerReferences(){
        return getRefs(ARACHNOSERVER_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getBGeeReferences(){
        return getRefs(BGEE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getBindingDBReferences(){
        return getRefs(BINDING_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getBioCycReferences(){
        return getRefs(BIOCYC_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getBrendaReferences(){
        return getRefs(BRENDA_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCazyReferences(){
        return getRefs(CAZY_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCGDReferences(){
        return getRefs(CGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCHEMBLReferences(){
        return getRefs(CHEMBL_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCleanexReferences(){
        return getRefs(CLEANEX_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCompluyeast2DPageReferences(){
        return getRefs(COMPLUYEAST_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getConoServerReferences(){
        return getRefs(CONOSERVER_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCornea2DPageReferences(){
        return getRefs(CORNEA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCTDReferences(){
        return getRefs(CTD_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getCYGDReferences(){
        return getRefs(CYGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDBSNPReferences(){
        return getRefs(DBSNP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDDBJReferences(){
        return getRefs(DDBJ_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDictyBaseReferences(){
        return getRefs(DICTY_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDIPReferences(){
        return getRefs(DIP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDisprotReferences(){
        return getRefs(DISPROT_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDMDMReferences(){
        return getRefs(DMDM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDnasuReferences(){
        return getRefs(DNASU_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDosacCobs2DPageReferences(){
        return getRefs(DOSAC_COBS_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getDrugBankReferences(){
        return getRefs(DRUGBANK_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEchoBaseReferences(){
        return getRefs(ECHOBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEcoGeneReferences(){
        return getRefs(ECOGENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEggnogReferences(){
        return getRefs(EGGNOG_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEnsemblBacteriaReferences(){
        return getRefs(ENSEMBL_BACTERIA_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEnsemblFungiReferences(){
        return getRefs(ENSEMBL_FUNGI_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEnsemblMetazoaReferences(){
        return getRefs(ENSEMBL_METAZOA_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEnsemblProtistsReferences(){
        return getRefs(ENSEMBL_PROTISTS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEUHCVDBReferences(){
        return getRefs(EUHCVDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEUPathDBReferences(){
        return getRefs(EUPATHDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getEvolutionaryTraceReferences(){
        return getRefs(EVOLUTIONARY_TRACE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getFlybaseReferences(){
        return getRefs(FLYBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenAtlasReferences(){
        return getRefs(GENATLAS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenbankReferences(){
        return getRefs(GENBANK_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGene3DReferences(){
        return getRefs(GENE3D_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenecardsReferences(){
        return getRefs(GENECARDS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenefarmReferences(){
        return getRefs(GENEFARM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGeneIDReferences(){
        return getRefs(GENEID_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGeneTreeReferences(){
        return getRefs(GENETREE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenevestigatorReferences(){
        return getRefs(GENEVESTIGATOR_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenolistReferences(){
        return getRefs(GENOLIST_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenomeReviewsReferences(){
        return getRefs(GENOME_REVIEWS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGenomeRNAIReferences(){
        return getRefs(GENOME_RNAI_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGermOnlineReferences(){
        return getRefs(GERMONLINE_REFERENCES_PROPERTY);
    }    
    
    @Override
    public String[] getGlycoSuiteDBReferences(){
        return getRefs(GLYCOSUITEDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGPCRDBReferences(){
        return getRefs(GPCRDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getGrameneReferences(){
        return getRefs(GRAMENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHINVDBReferences(){
        return getRefs(HINVDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHamapReferences(){
        return getRefs(HAMAP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHGNCReferences(){
        return getRefs(HGNC_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHogenomReferences(){
        return getRefs(HOGENOM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHovergenReferences(){
        return getRefs(HOVERGEN_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHPAReferences(){
        return getRefs(HPA_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHSSPReferences(){
        return getRefs(HSSP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getHugeReferences(){
        return getRefs(HUGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getIMGTReferences(){
        return getRefs(IMGT_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getInparanoidReferences(){
        return getRefs(INPARANOID_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getIntactReferences(){
        return getRefs(INTACT_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getIPIReferences(){
        return getRefs(IPI_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getKOReferences(){
        return getRefs(KO_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getLegioListReferences(){
        return getRefs(LEGIO_LIST_REFERENCES_PROPERTY);
    }    
    
    @Override
    public String[] getLepromaReferences(){
        return getRefs(LEPROMA_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getMaizeGDBReferences(){
        return getRefs(MAIZE_GDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getMeropsReferences(){
        return getRefs(MEROPS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getMGIReferences(){
        return getRefs(MGI_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getMicadoReferences(){
        return getRefs(MICADO_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getMIMReferences(){
        return getRefs(MIM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getMintReferences(){
        return getRefs(MINT_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getModbaseReferences(){
        return getRefs(MODBASE_REFERENCES_PROPERTY);
    }
    
    
//    public static final String MYCOCLAP_REFERENCES_PROPERTY = "protein_mycoclap_references";
//    public static final String NEXTBIO_REFERENCES_PROPERTY = "protein_nextbio_references";
//    public static final String NEXTPROT_REFERENCES_PROPERTY = "protein_nextprot_references";
//    public static final String OGP_REFERENCES_PROPERTY = "protein_ogp_references";
//    public static final String OMA_REFERENCES_PROPERTY = "protein_oma_references";
//    public static final String ORPHANET_REFERENCES_PROPERTY = "protein_orphanet_references";
//    public static final String ORTHODB_REFERENCES_PROPERTY = "protein_orthodb_references";
//    public static final String PANTHER_REFERENCES_PROPERTY = "protein_panther_references";
//    public static final String PATHWAY_INTERACTION_DB_REFERENCES_PROPERTY = "protein_pathway_interaction_db_references";
//    public static final String PATRIC_REFERENCES_PROPERTY = "protein_patric_references";
//    public static final String PAXDB_REFERENCES_PROPERTY = "protein_paxdb_references";
//    public static final String PDB_REFERENCES_PROPERTY = "protein_pdb_references";
//    public static final String PDBJ_REFERENCES_PROPERTY = "protein_pdbj_references";
//    public static final String PDBSUM_REFERENCES_PROPERTY = "protein_pdbsum_references";
//    public static final String PEPTIDE_ATLAS_REFERENCES_PROPERTY = "protein_peptide_atlas_references";
//    public static final String PHARMGKB_REFERENCES_PROPERTY = "protein_pharmgkb_references";
//    public static final String PHCI_2DPAGE_REFERENCES_PROPERTY = "protein_phci_2dpage_references";
//    public static final String PHOSPHOSITE_REFERENCES_PROPERTY = "protein_phosphosite_references";
//    public static final String PHOS_SITE_REFERENCES_PROPERTY = "protein_phos_site_references";
//    public static final String PHYLOME_DB_REFERENCES_PROPERTY = "protein_phylome_db_references";
//    public static final String PIRSF_REFERENCES_PROPERTY = "protein_pirsf_references";
//    public static final String PMAP_CUTDB_REFERENCES_PROPERTY = "protein_pmap_cutdb_references";
//    public static final String PMMA_2DPAGE_REFERENCES_PROPERTY = "protein_pmma_2dpage_references";
//    public static final String POMBASE_REFERENCES_PROPERTY = "protein_pombase_references";
//    public static final String PPTASEDB_REFERENCES_PROPERTY = "protein_pptasedb_references";
//    public static final String PRIDE_REFERENCES_PROPERTY = "protein_pride_references";
//    public static final String PRINTS_REFERENCES_PROPERTY = "protein_prints_references";
//    public static final String PRODOM_REFERENCES_PROPERTY = "protein_prodom_references";
//    public static final String PROMEX_REFERENCES_PROPERTY = "protein_promex_references";
//    public static final String PROSITE_REFERENCES_PROPERTY = "protein_prosite_references";
//    public static final String PROT_CLUST_DB_REFERENCES_PROPERTY = "protein_prot_clust_db_references";
//    public static final String PROTEIN_MODEL_PORTAL_REFERENCES_PROPERTY = "protein_model_portal_references";
//    public static final String PROTONET_REFERENCES_PROPERTY = "protein_protonet_references";
//    public static final String PSEUDO_CAP_REFERENCES_PROPERTY = "protein_pseudo_cap_references";
//    public static final String RAT_HEART_2DPAGE_REFERENCES_PROPERTY = "protein_rat_heart_2dpage_references";
//    public static final String RCSB_PDB_REFERENCES_PROPERTY = "protein_rcsb_pdb_references";
//    public static final String REBASE_REFERENCES_PROPERTY = "protein_rebase_references";
//    public static final String REPRODUCTION_2DPAGE_REFERENCES_PROPERTY = "protein_reproduction_2dpage_references";
//    public static final String RGD_REFERENCES_PROPERTY = "protein_rgd_references";
//    public static final String ROUGE_REFERENCES_PROPERTY = "protein_rouge_references";
//    public static final String SBKB_REFERENCES_PROPERTY = "protein_sbkb_references";
//    public static final String SGD_REFERENCES_PROPERTY = "protein_sgd_references";
//    public static final String SIENA_2DPAGE_REFERENCES_PROPERTY = "protein_siena_2dpage_references";
//    public static final String SMART_REFERENCES_PROPERTY = "protein_smart_references";
//    public static final String SMR_REFERENCES_PROPERTY = "protein_smr_references";
//    public static final String SOURCE_REFERENCES_PROPERTY = "protein_source_references";
//    public static final String STRING_REFERENCES_PROPERTY = "protein_string_references";
//    public static final String SUPFAM_REFERENCES_PROPERTY = "protein_supfam_references";
//    public static final String SWISS_2DPAGE_REFERENCES_PROPERTY = "protein_swiss_2dpage_references";
//    public static final String TAIR_REFERENCES_PROPERTY = "protein_tair_references";
//    public static final String TCDB_REFERENCES_PROPERTY = "protein_tcb_references";
//    public static final String TIGRFAMS_REFERENCES_PROPERTY = "protein_tigrfams_references";
//    public static final String TUBERCULIST_REFERENCES_PROPERTY = "protein_tuberculist_references";
//    public static final String UCD_2DPAGE_REFERENCES_PROPERTY = "protein_ucd_2dpage_references";
//    public static final String UCSC_REFERENCES_PROPERTY = "protein_ucsc_references";
//    public static final String VECTOR_BASE_REFERENCES_PROPERTY = "protein_vector_base_references";
//    public static final String WORLD_2DPAGE_REFERENCES_PROPERTY = "protein_world_2dpage_references";
//    public static final String WORM_BASE_REFERENCES_PROPERTY = "protein_worm_base_references";
//    public static final String XEN_BASE_REFERENCES_PROPERTY = "protein_xen_base_references";
//    public static final String ZFIN_REFERENCES_PROPERTY = "protein_zfin_references";
    
    //----------------------------SETTERS-----------------------------------
    //---------------------------------------------------------------------
    
    @Override
    public void setEMBLreferences(String[] list){
        setRefs(list, EMBL_REFERENCES_PROPERTY);        
    }
    
    @Override
    public void setEnsemblPlantsReferences(String[] list){
        setRefs(list, ENSEMBL_PLANTS_REFERENCES_PROPERTY);
    }

    @Override
    public void setRefseqReferences(String[] list){
        setRefs(list, REFSEQ_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAlternativeAccessions(String[] list){
        setRefs(list, ALTERNATIVE_ACCESSIONS_PROPERTY);
    }
    
    private String[] getRefs(String propertyName){
        String[] result;
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(propertyName).iterator();
        List<String> tempList = new LinkedList<String>();
        while(iterator.hasNext()){
            tempList.add((String)iterator.next().getAttribute());
        }
        result = tempList.toArray(new String[tempList.size()]);
        return result;
    }
    
    private void setRefs(String[] list, String propertyName){
        TitanVertex tempVertex = (TitanVertex) vertex;
        //first we have to delete any previous properties
        tempVertex.removeProperty(propertyName);
        for (String string : list) {
            tempVertex.addProperty(propertyName, string);
        }  
    }
    
}
