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

import com.era7.bioinfo.bio4j.model.enums.UniprotDBXref;
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
    public String[] getGeneNames(){
        return getRefs(GENE_NAMES_PROPERTY);        
    }
    
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
    public void setGeneNames(String[] list){
        setReference(list, GENE_NAMES_PROPERTY);        
    }
    
    @Override
    public void setEMBLreferences(String[] list){
        setReference(list, EMBL_REFERENCES_PROPERTY);        
    }
    
    @Override
    public void setEnsemblPlantsReferences(String[] list){
        setReference(list, ENSEMBL_PLANTS_REFERENCES_PROPERTY);
    }

    @Override
    public void setReferenceeqReferences(String[] list){
        setReference(list, REFSEQ_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAlternativeAccessions(String[] list){
        setReference(list, ALTERNATIVE_ACCESSIONS_PROPERTY);
    }
    
    @Override
    public void setPIRReferences(String[] list){
        setReference(list, PIR_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setKeggReferences(String[] list){
        setReference(list, KEGG_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setArrayExpressReferences(String[] list){
        setReference(list, ARRAY_EXPRESS_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setUniGeneReferences(String[] list){
        setReference(list, UNIGENE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDBaseEcoliReferences(String[] list){
        setReference(list, DBASE_ECOLI_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAarhusGhent2DPageReferences(String[] list){
        setReference(list, AARHUS_GHENT_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAGDReferences(String[] list){
        setReference(list, AGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAllergomeReferences(String[] list){
        setReference(list, ALLERGOME_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setAnu2DPageReferences(String[] list){
        setReference(list, ANU_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setArachnoServerReferences(String[] list){
        setReference(list, ARACHNOSERVER_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBGeeReferences(String[] list){
        setReference(list, BGEE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBindingDBReferences(String[] list){
        setReference(list, BINDING_DB_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBiocycReferences(String[] list){
        setReference(list, BIOCYC_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setBrendaReferences(String[] list){
        setReference(list, BRENDA_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCazyReferences(String[] list){
        setReference(list, CAZY_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCGDReferences(String[] list){
        setReference(list, CGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCHEmblReferences(String[] list){
        setReference(list, CHEMBL_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCleanexReferences(String[] list){
        setReference(list, CLEANEX_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCompluYeast2DPageReferences(String[] list){
        setReference(list, COMPLUYEAST_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setConoServerReferences(String[] list){
        setReference(list, CONOSERVER_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCornea2DPageReferences(String[] list){
        setReference(list, CORNEA_2DPAGE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCTDReferences(String[] list){
        setReference(list, CTD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setCYGDReferences(String[] list){
        setReference(list, CYGD_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDBSNPReferences(String[] list){
        setReference(list, DBSNP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDDBJReferences(String[] list){
        setReference(list, DDBJ_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDictyBaseReferences(String[] list){
        setReference(list, DICTY_BASE_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDIPReferences(String[] list){
        setReference(list, DIP_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDisprotReferences(String[] list){
        setReference(list, DISPROT_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDMDMReferences(String[] list){
        setReference(list, DMDM_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDnasuReferences(String[] list){
        setReference(list, DNASU_REFERENCES_PROPERTY);
    }
    
    @Override
    public void setDosacCobs2DPageReferences(String[] list){        setReference(UniprotDBXref.DOSAC_COBS_2D_PAGE, list);    }
    public void setDrugbankReferences(String[] list){        setReference(UniprotDBXref.DRUG_BANK, list);    }
    public void setEchoBaseReferences(String[] list){        setReference(UniprotDBXref.ECHO_BASE, list);    }
    public void setEcogeneReferences(String[] list){        setReference(UniprotDBXref.ECO_GENE, list);    }
    public void setEggnogReferences(String[] list){        setReference(UniprotDBXref.EGGNOG, list);    }
    public void setEnsemblBacteriaReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_BACTERIA, list);    }
    public void setEnsemblFungiReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_FUNGI, list);    }
    public void setEnsemblMetazoaReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_METAZOA, list);    }
    public void setEnsemblProtistsReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_PROTISTS, list);    }
    public void setEuhcvDBReferences(String[] list){        setReference(UniprotDBXref.EUHCV_DB, list);    }
    public void setEupathDBReferences(String[] list){        setReference(UniprotDBXref.EUPATH_DB, list);    }
    public void setEvolutionaryTraceReferences(String[] list){        setReference(UniprotDBXref.EVOLUTIONARY_TRACE, list);    }
    public void setFlyBaseReferences(String[] list){        setReference(UniprotDBXref.FLYBASE, list);    }
    public void setGenAtlasReferences(String[] list){        setReference(UniprotDBXref.GENATLAS, list);    }
    public void setGenBankReferences(String[] list){        setReference(UniprotDBXref.GENBANK, list);    }
    public void setGene3DReferences(String[] list){        setReference(UniprotDBXref.GENE3D, list);    }
    public void setGeneCardsReferences(String[] list){        setReference(UniprotDBXref.GENECARDS, list);    }
    public void setGeneFarmReferences(String[] list){        setReference(UniprotDBXref.GENEFARM, list);    }
    public void setGeneIDReferences(String[] list){        setReference(UniprotDBXref.GENEID, list);    }
    public void setGeneTreeReferences(String[] list){        setReference(UniprotDBXref.GENETREE, list);    }
    public void setGenevestigatorReferences(String[] list){        setReference(UniprotDBXref.GENEVESTIGATOR, list);    }
    public void setGenolistReferences(String[] list){        setReference(UniprotDBXref.GENOLIST, list);    }
    public void setGenomeReviewsReferences(String[] list){        setReference(UniprotDBXref.GENOME_REVIEWS, list);    }
    public void setGenomeRNAIReferences(String[] list){        setReference(UniprotDBXref.GENOME_RNAI, list);    }
    public void setGermOnlineReferences(String[] list){        setReference(UniprotDBXref.GERMONLINE, list);    }
    public void setGlycoSuiteDBReferences(String[] list){        setReference(UniprotDBXref.GLYCO_SUITE_DB, list);    }
    public void setGPCRDBReferences(String[] list){        setReference(UniprotDBXref.GPCR_DB, list);    }
    public void setGrameneReferences(String[] list){        setReference(UniprotDBXref.GRAMENE, list);    }
    public void setHINVDBReferences(String[] list){        setReference(UniprotDBXref.HINV_DB, list);    }
    public void setHamapReferences(String[] list){        setReference(UniprotDBXref.HAMAP, list);    }
    public void setHGNCReferences(String[] list){        setReference(UniprotDBXref.HGNC, list);    }
    public void setHogenomReferences(String[] list){        setReference(UniprotDBXref.HOGENOM, list);    }
    public void setHovergenReferences(String[] list){        setReference(UniprotDBXref.HOVERGEN, list);    }
    public void setHpaReferences(String[] list){        setReference(UniprotDBXref.HPA, list);    }
    public void setHsspReferences(String[] list){        setReference(UniprotDBXref.HSSP, list);    }
    public void setHugeReferences(String[] list){        setReference(UniprotDBXref.HUGE, list);    }
    public void setIMGTReferences(String[] list){        setReference(UniprotDBXref.IMGT, list);    }
    public void setINPARANOIDReferences(String[] list){        setReference(UniprotDBXref.INPARANOID, list);    }
    public void setIntactReferences(String[] list){        setReference(UniprotDBXref.INTACT, list);    }
    public void setIPIReferences(String[] list){        setReference(UniprotDBXref.IPI, list);    }
    public void setKOReferences(String[] list){        setReference(UniprotDBXref.KO, list);    }
    public void setLegioListReferences(String[] list){        setReference(UniprotDBXref.LEGIOLIST, list);    }
    public void setLepromaReferences(String[] list){        setReference(UniprotDBXref.LEPROMA, list);    }
    public void setMaizeGDBReferences(String[] list){        setReference(UniprotDBXref.MAIZEGD_DB, list);    }
    public void setMeropsReferences(String[] list){        setReference(UniprotDBXref.MEROPS, list);    }
    public void setMGIReferences(String[] list){        setReference(UniprotDBXref.MGI, list);    }
    public void setMicadoReferences(String[] list){        setReference(UniprotDBXref.MICADO, list);    }
    public void setMIMReferences(String[] list){        setReference(UniprotDBXref.MIM, list);    }
    public void setMINTReferences(String[] list){        setReference(UniprotDBXref.MINT, list);    }
    public void setModbaseReferences(String[] list){        setReference(UniprotDBXref.MODBASE, list);    }
    public void setMycoclapReferences(String[] list){        setReference(UniprotDBXref.MYCOCLAP, list);    }
    public void setNextbioReferences(String[] list){        setReference(UniprotDBXref.NEXTBIO, list);    }
    public void setNextprotReferences(String[] list){        setReference(UniprotDBXref.NEXTPROT, list);    }
    public void setOGPReferences(String[] list){        setReference(UniprotDBXref.OGP, list);    }
    public void setOMAReferences(String[] list){        setReference(UniprotDBXref.OMA, list);    }
    public void setOrphanetReferences(String[] list){        setReference(UniprotDBXref.ORPHANET, list);    }
    public void setOrthoDBReferences(String[] list){        setReference(UniprotDBXref.ORTHO_DB, list);    }
    public void setPantherReferences(String[] list){        setReference(UniprotDBXref.PANTHER, list);    }
    public void setPathwayInteractionDBReferences(String[] list){        setReference(UniprotDBXref.PATHWAY_INTERACTION_DB, list);    }
    public void setPatricReferences(String[] list){        setReference(UniprotDBXref.PATRIC, list);    }
    public void setPaxDBReferences(String[] list){        setReference(UniprotDBXref.PAXDB, list);    }
    public void setPDBReferences(String[] list){        setReference(UniprotDBXref.PDB, list);    }
    public void setPDBJReferences(String[] list){        setReference(UniprotDBXref.PDBJ, list);    }
    public void setPDBSumReferences(String[] list){        setReference(UniprotDBXref.PDBSUM, list);    }
    public void setPeptideAtlasReferences(String[] list){        setReference(UniprotDBXref.PEPTIDE_ATLAS, list);    }
    public void setPeroxibaseReferences(String[] list){        setReference(UniprotDBXref.PEROXIBASE, list);    }
    public void setPharmGKBReferences(String[] list){        setReference(UniprotDBXref.PHARM_GKB, list);    }
    public void setPhosphositeReferences(String[] list){        setReference(UniprotDBXref.PHOSPHOSITE, list);    }
    public void setPhosSiteReferences(String[] list){        setReference(UniprotDBXref.PHOS_SITE, list);    }
    public void setPhylomeDBReferences(String[] list){        setReference(UniprotDBXref.PHYLOME_DB, list);    }
    public void setPIRSFReferences(String[] list){        setReference(UniprotDBXref.PIRSF, list);    }
    public void setPmapCutDBReferences(String[] list){        setReference(UniprotDBXref.PMAP_CUT_DB, list);    }    
    public void setPomBaseReferences(String[] list){        setReference(UniprotDBXref.POMBASE, list);    }
    public void setPPTaseDBReferences(String[] list){        setReference(UniprotDBXref.PPTASE_DB, list);    }
    public void setPrideReferences(String[] list){        setReference(UniprotDBXref.PRIDE, list);    }
    public void setPrintsReferences(String[] list){        setReference(UniprotDBXref.PRINTS, list);    }
    public void setProdomReferences(String[] list){        setReference(UniprotDBXref.PRODOM, list);    }
    public void setPromexReferences(String[] list){        setReference(UniprotDBXref.PROMEX, list);    }
    public void setPrositeReferences(String[] list){        setReference(UniprotDBXref.PROSITE, list);    }
    public void setProtClustDBReferences(String[] list){        setReference(UniprotDBXref.PROT_CLUST_DB, list);    }
    public void setProteinModelPortalReferences(String[] list){        setReference(UniprotDBXref.PROTEIN_MODEL_PORTAL, list);    }
    public void setProtonetReferences(String[] list){        setReference(UniprotDBXref.PROTONET, list);    }
    public void setPseudoCapReferences(String[] list){        setReference(UniprotDBXref.PSEUDOCAP, list);    }
    public void setRCSBPDBReferences(String[] list){        setReference(UniprotDBXref.RCSBPDB, list);    }
    public void setRebaseReferences(String[] list){        setReference(UniprotDBXref.REBASE, list);    }
    public void setReproduction2DPageReferences(String[] list){        setReference(UniprotDBXref.REPRODUCTION_2D_PAGE, list);    }
    public void setRGDReferences(String[] list){        setReference(UniprotDBXref.RGD, list);    }
    public void setRougeReferences(String[] list){        setReference(UniprotDBXref.ROUGE, list);    }
    public void setSBKBReferences(String[] list){        setReference(UniprotDBXref.SBKB, list);    }
    public void setSGDReferences(String[] list){       setReference(UniprotDBXref.SGD, list);    }    
    public void setSmartReferences(String[] list){        setReference(UniprotDBXref.SMART, list);    }
    public void setSMRReferences(String[] list){        setReference(UniprotDBXref.SMR, list);    }    
    public void setSourceReferences(String[] list){        setReference(UniprotDBXref.SOURCE, list);    }
    public void setStringReferences(String[] list){        setReference(UniprotDBXref.STRING, list);    }
    public void setSupfamReferences(String[] list){        setReference(UniprotDBXref.SUPFAM, list);    }
    public void setSwiss2DPageReferences(String[] list){        setReference(UniprotDBXref.SWISS_2D_PAGE, list);    }
    public void setTairReferences(String[] list){        setReference(UniprotDBXref.TAIR, list);    }    
    public void setTCDBReferences(String[] list){        setReference(UniprotDBXref.TCDB, list);    }
    public void setTIGRFAMSReferences(String[] list){    setReference(UniprotDBXref.TIGRFAMS, list);    }      
    public void setTuberculistReferences(String[] list){ setReference(UniprotDBXref.TUBERCULIST, list);    }      
    public void setUCD2DPageReferences(String[] list){   setReference(UniprotDBXref.UCD_2D_PAGE, list);    }     
    public void setUCSCReferences(String[] list){     setReference(UniprotDBXref.UCSC, list);    }      
    public void setUnipathwayReferences(String[] list){   setReference(UniprotDBXref.UNIPATHWAY, list);    }   
    public void setVectorBaseReferences(String[] list){   setReference(UniprotDBXref.VECTORBASE, list);    }    
    public void setWorld2DPageReferences(String[] list){  setReference(UniprotDBXref.WORLD_2D_PAGE, list); }
    public void setWormBaseReferences(String[] list){     setReference(UniprotDBXref.WORMBASE, list);  }    
    public void setXenBaseReferences(String[] list){     setReference(UniprotDBXref.XENBASE, list);    }
    public void setZFINReferences(String[] list){        setReference(UniprotDBXref.ZFIN, list);    }
    
    
    
    @Override
    public String[] getReference(UniprotDBXref ref){
        String[] result;
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(ref.getProteinReferencePropertyName()).iterator();
        List<String> tempList = new LinkedList<>();
        while(iterator.hasNext()){
            tempList.add((String)iterator.next().getAttribute());
        }
        result = tempList.toArray(new String[tempList.size()]);
        return result;
    }
    
    @Override
    public void setReference(UniprotDBXref ref, String[] value){
        TitanVertex tempVertex = (TitanVertex) vertex;
        //first we have to delete any previous properties
        tempVertex.removeProperty(ref.getProteinReferencePropertyName());
        for (String string : value) {
            tempVertex.addProperty(ref.getProteinReferencePropertyName(), string);
        }  
    }
    
}
