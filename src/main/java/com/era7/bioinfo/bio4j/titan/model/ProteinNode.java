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
    
    @Override
    public String[] getMycoClapReferences(){
        return getRefs(MYCOCLAP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getNextBioReferences(){
        return getRefs(NEXTBIO_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getNextProtReferences(){
        return getRefs(NEXTPROT_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getOGPReferences(){
        return getRefs(OGP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getOMAReferences(){
        return getRefs(OMA_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getOrphanetReferences(){
        return getRefs(ORPHANET_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getOrthoDBReferences(){
        return getRefs(ORTHODB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPantherReferences(){
        return getRefs(PANTHER_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPathwayInteractionDBReferences(){
        return getRefs(PATHWAY_INTERACTION_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPatricReferences(){
        return getRefs(PATRIC_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPaxDBReferences(){
        return getRefs(PAXDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPDBReferences(){
        return getRefs(PDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPDBJReferences(){
        return getRefs(PDBJ_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPDBSumReferences(){
        return getRefs(PDBSUM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPeptideAtlasReferences(){
        return getRefs(PEPTIDE_ATLAS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPeroxibaseReferences(){
        return getRefs(PEROXIBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPharmGKBReferences(){
        return getRefs(PHARMGKB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPhci2DPageReferences(){
        return getRefs(PHCI_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPhosphositeReferences(){
        return getRefs(PHOSPHOSITE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPhosSiteReferences(){
        return getRefs(PHOS_SITE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPhylomeDBReferences(){
        return getRefs(PHYLOME_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPIRSFReferences(){
        return getRefs(PIRSF_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPmapCutDBReferences(){
        return getRefs(PMAP_CUTDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPMMA2DPageReferences(){
        return getRefs(PMMA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPombaseReferences(){
        return getRefs(POMBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPptaseDBReferences(){
        return getRefs(PPTASEDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPrideReferences(){
        return getRefs(PRIDE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPrintsReferences(){
        return getRefs(PRINTS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getProdomReferences(){
        return getRefs(PRODOM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPromexReferences(){
        return getRefs(PROMEX_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPrositeReferences(){
        return getRefs(PROSITE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getProtClustDBReferences(){
        return getRefs(PROT_CLUST_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getProteinModelPortalReferences(){
        return getRefs(PROTEIN_MODEL_PORTAL_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getProtonetReferences(){
        return getRefs(PROTONET_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getPseudoCapReferences(){
        return getRefs(PSEUDO_CAP_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getRatHeart2DPageReferences(){
        return getRefs(RAT_HEART_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getRCSBPDBReferences(){
        return getRefs(RCSB_PDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getRebaseReferences(){
        return getRefs(REBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getReproduction2DPageReferences(){
        return getRefs(REPRODUCTION_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getRGDReferences(){
        return getRefs(RGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getRougeReferences(){
        return getRefs(ROUGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSBKBReferences(){
        return getRefs(SBKB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSGDReferences(){
        return getRefs(SGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSiena2DPageReferences(){
        return getRefs(SIENA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSmartReferences(){
        return getRefs(SMART_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSMRReferences(){
        return getRefs(SMR_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSourceReferences(){
        return getRefs(SOURCE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getStringReferences(){
        return getRefs(STRING_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSupfamReferences(){
        return getRefs(SUPFAM_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getSwiss2DPageReferences(){
        return getRefs(SWISS_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getTairReferences(){
        return getRefs(TAIR_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getTCDBReferences(){
        return getRefs(TCDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getTIGRFAMSReferences(){
        return getRefs(TIGRFAMS_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getTuberculistReferences(){
        return getRefs(TUBERCULIST_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getUCD2DPageReferences(){
        return getRefs(UCD_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getUCSCPageReferences(){
        return getRefs(UCSC_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getUnipathwayReferences(){
        return getRefs(UNIPATHWAY_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getVectorBaseReferences(){
        return getRefs(VECTOR_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getWorld2DPageReferences(){
        return getRefs(WORLD_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getWormBaseReferences(){
        return getRefs(WORM_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getXenBaseReferences(){
        return getRefs(XEN_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public String[] getZfinReferences(){
        return getRefs(ZFIN_REFERENCES_PROPERTY);
    }
    
    
    
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
    
    @Override
    public void setPIRReferences(String[] list){
        setRefs(list, PIR_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setKeggReferences(String[] list){
        setRefs(list, KEGG_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setArrayExpressReferences(String[] list){
        setRefs(list, ARRAY_EXPRESS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setUniGeneReferences(String[] list){
        setRefs(list, UNIGENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDBaseEcoliReferences(String[] list){
        setRefs(list, DBASE_ECOLI_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAarhusGhent2DPageReferences(String[] list){
        setRefs(list, AARHUS_GHENT_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAGDReferences(String[] list){
        setRefs(list, AGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAllergomeReferences(String[] list){
        setRefs(list, ALLERGOME_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAnu2DPageReferences(String[] list){
        setRefs(list, ANU_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setArachnoServerReferences(String[] list){
        setRefs(list, ARACHNOSERVER_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBGeeReferences(String[] list){
        setRefs(list, BGEE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBindingDBReferences(String[] list){
        setRefs(list, BINDING_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBiocycReferences(String[] list){
        setRefs(list, BIOCYC_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBrendaReferences(String[] list){
        setRefs(list, BRENDA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCazyReferences(String[] list){
        setRefs(list, CAZY_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCGDReferences(String[] list){
        setRefs(list, CGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCHEmblReferences(String[] list){
        setRefs(list, CHEMBL_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCleanexReferences(String[] list){
        setRefs(list, CLEANEX_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCompluYeast2DPageReferences(String[] list){
        setRefs(list, COMPLUYEAST_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setConoServerReferences(String[] list){
        setRefs(list, CONOSERVER_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCornea2DPageReferences(String[] list){
        setRefs(list, CORNEA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCTDReferences(String[] list){
        setRefs(list, CTD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCYGDReferences(String[] list){
        setRefs(list, CYGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDBSNPReferences(String[] list){
        setRefs(list, DBSNP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDDBJReferences(String[] list){
        setRefs(list, DDBJ_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDictyBaseReferences(String[] list){
        setRefs(list, DICTY_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDIPReferences(String[] list){
        setRefs(list, DIP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDisprotReferences(String[] list){
        setRefs(list, DISPROT_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDMDMReferences(String[] list){
        setRefs(list, DMDM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDnasuReferences(String[] list){
        setRefs(list, DNASU_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDosacCobs2DPageReferences(String[] list){
        setRefs(list, DOSAC_COBS_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDrugbankReferences(String[] list){
        setRefs(list, DRUGBANK_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEchoBaseReferences(String[] list){
        setRefs(list, ECHOBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEcogeneReferences(String[] list){
        setRefs(list, ECOGENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEggnogReferences(String[] list){
        setRefs(list, EGGNOG_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEnsemblBacteriaReferences(String[] list){
        setRefs(list, ENSEMBL_BACTERIA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEnsemblFungiReferences(String[] list){
        setRefs(list, ENSEMBL_FUNGI_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEnsemblMetazoaReferences(String[] list){
        setRefs(list, ENSEMBL_METAZOA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEnsemblProtistsReferences(String[] list){
        setRefs(list, ENSEMBL_PROTISTS_REFERENCES_PROPERTY);
    }
        
    @Override
    public void setEuhcvDBReferences(String[] list){
        setRefs(list, EUHCVDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEupathDBReferences(String[] list){
        setRefs(list, EUPATHDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setEvolutionaryTraceReferences(String[] list){
        setRefs(list, EVOLUTIONARY_TRACE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setFlyBaseReferences(String[] list){
        setRefs(list, FLYBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGenAtlasReferences(String[] list){
        setRefs(list, GENATLAS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGenBankReferences(String[] list){
        setRefs(list, GENBANK_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGene3DReferences(String[] list){
        setRefs(list, GENE3D_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGeneCardsReferences(String[] list){
        setRefs(list, GENECARDS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGeneFarmReferences(String[] list){
        setRefs(list, GENEFARM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGeneIDReferences(String[] list){
        setRefs(list, GENEID_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGeneTreeReferences(String[] list){
        setRefs(list, GENETREE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGenevestigatorReferences(String[] list){
        setRefs(list, GENEVESTIGATOR_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGenolistReferences(String[] list){
        setRefs(list, GENOLIST_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGenomeReviewsReferences(String[] list){
        setRefs(list, GENOME_REVIEWS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGenomeRNAIReferences(String[] list){
        setRefs(list, GENOME_RNAI_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGermOnlineReferences(String[] list){
        setRefs(list, GERMONLINE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGlycoSuiteDBReferences(String[] list){
        setRefs(list, GLYCOSUITEDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGPCRDBReferences(String[] list){
        setRefs(list, GPCRDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setGrameneReferences(String[] list){
        setRefs(list, GRAMENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHINVDBReferences(String[] list){
        setRefs(list, HINVDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHamapReferences(String[] list){
        setRefs(list, HAMAP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHGNCReferences(String[] list){
        setRefs(list, HGNC_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHogenomReferences(String[] list){
        setRefs(list, HOGENOM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHovergenReferences(String[] list){
        setRefs(list, HOVERGEN_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHpaReferences(String[] list){
        setRefs(list, HPA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHsspReferences(String[] list){
        setRefs(list, HSSP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setHugeReferences(String[] list){
        setRefs(list, HUGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setIMGTReferences(String[] list){
        setRefs(list, IMGT_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setINPARANOIDReferences(String[] list){
        setRefs(list, INPARANOID_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setIntactReferences(String[] list){
        setRefs(list, INTACT_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setIPIReferences(String[] list){
        setRefs(list, IPI_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setKOReferences(String[] list){
        setRefs(list, KO_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setLegioListReferences(String[] list){
        setRefs(list, LEGIO_LIST_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setLepromaReferences(String[] list){
        setRefs(list, LEPROMA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMaizeGDBReferences(String[] list){
        setRefs(list, MAIZE_GDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMeropsReferences(String[] list){
        setRefs(list, MEROPS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMGIReferences(String[] list){
        setRefs(list, MGI_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMicadoReferences(String[] list){
        setRefs(list, MICADO_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMIMReferences(String[] list){
        setRefs(list, MIM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMINTReferences(String[] list){
        setRefs(list, MINT_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setModbaseReferences(String[] list){
        setRefs(list, MODBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setMycoclapReferences(String[] list){
        setRefs(list, MYCOCLAP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setNextbioReferences(String[] list){
        setRefs(list, NEXTBIO_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setNextprotReferences(String[] list){
        setRefs(list, NEXTPROT_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setOGPReferences(String[] list){
        setRefs(list, OGP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setOMAReferences(String[] list){
        setRefs(list, OMA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setOrphanetReferences(String[] list){
        setRefs(list, ORPHANET_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setOrthoDBReferences(String[] list){
        setRefs(list, ORTHODB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPantherReferences(String[] list){
        setRefs(list, PANTHER_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPathwayInteractionDBReferences(String[] list){
        setRefs(list, PATHWAY_INTERACTION_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPaxDBReferences(String[] list){
        setRefs(list, PAXDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPDBReferences(String[] list){
        setRefs(list, PDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPDBJReferences(String[] list){
        setRefs(list, PDBJ_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPDBSumReferences(String[] list){
        setRefs(list, PDBSUM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPeptideAtlasReferences(String[] list){
        setRefs(list, PEPTIDE_ATLAS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPeroxibaseReferences(String[] list){
        setRefs(list, PEROXIBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPharmGKBReferences(String[] list){
        setRefs(list, PHARMGKB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPhci2DPageReferences(String[] list){
        setRefs(list, PHCI_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPhosphositeReferences(String[] list){
        setRefs(list, PHOSPHOSITE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPhosSiteReferences(String[] list){
        setRefs(list, PHOS_SITE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPhylomeDBReferences(String[] list){
        setRefs(list, PHYLOME_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPIRSFReferences(String[] list){
        setRefs(list, PIRSF_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPmapCutDBReferences(String[] list){
        setRefs(list, PMAP_CUTDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPMMA2DPageReferences(String[] list){
        setRefs(list, PMMA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPomBaseReferences(String[] list){
        setRefs(list, POMBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPPTaseDBReferences(String[] list){
        setRefs(list, PPTASEDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPrideReferences(String[] list){
        setRefs(list, PRIDE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPrintsReferences(String[] list){
        setRefs(list, PRINTS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setProdomReferences(String[] list){
        setRefs(list, PRODOM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPromexReferences(String[] list){
        setRefs(list, PROMEX_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPrositeReferences(String[] list){
        setRefs(list, PROSITE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setProtClustDBReferences(String[] list){
        setRefs(list, PROT_CLUST_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setProteinModelPortalReferences(String[] list){
        setRefs(list, PROTEIN_MODEL_PORTAL_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setProtonetReferences(String[] list){
        setRefs(list, PROTONET_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setPseudoCapReferences(String[] list){
        setRefs(list, PSEUDO_CAP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setRatHeart2DPageReferences(String[] list){
        setRefs(list, RAT_HEART_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setRCSBPDBReferences(String[] list){
        setRefs(list, RCSB_PDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setRebaseReferences(String[] list){
        setRefs(list, REBASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setReproduction2DPageReferences(String[] list){
        setRefs(list, REPRODUCTION_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setRGDReferences(String[] list){
        setRefs(list, RGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setRougeReferences(String[] list){
        setRefs(list, ROUGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSBKBReferences(String[] list){
        setRefs(list, SBKB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSGDReferences(String[] list){
        setRefs(list, SGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSiena2DPageReferences(String[] list){
        setRefs(list, SIENA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSmartReferences(String[] list){
        setRefs(list, SMART_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSMRReferences(String[] list){
        setRefs(list, SMR_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSourceReferences(String[] list){
        setRefs(list, SOURCE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setStringReferences(String[] list){
        setRefs(list, STRING_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSupfamReferences(String[] list){
        setRefs(list, SUPFAM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setSwiss2DPageReferences(String[] list){
        setRefs(list, SWISS_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setTairReferences(String[] list){
        setRefs(list, TAIR_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setTCDBReferences(String[] list){
        setRefs(list, TCDB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setTIGRFAMSReferences(String[] list){
        setRefs(list, TIGRFAMS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setTuberculistReferences(String[] list){
        setRefs(list, TUBERCULIST_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setUCD2DPageReferences(String[] list){
        setRefs(list, UCD_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setUCSCReferences(String[] list){
        setRefs(list, UCSC_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setUnipathwayReferences(String[] list){
        setRefs(list, UNIPATHWAY_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setVectorBaseReferences(String[] list){
        setRefs(list, VECTOR_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setWorld2DPageReferences(String[] list){
        setRefs(list, WORLD_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setWormBaseReferences(String[] list){
        setRefs(list, WORM_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setXenBaseReferences(String[] list){
        setRefs(list, XEN_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setZFINReferences(String[] list){
        setRefs(list, ZFIN_REFERENCES_PROPERTY);
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
