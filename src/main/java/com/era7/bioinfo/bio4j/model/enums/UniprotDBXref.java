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
package com.era7.bioinfo.bio4j.model.enums;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public enum UniprotDBXref {
    
    ALLERGOME("protein_allergome_references","Allergome"),
    ARACHNO_SERVER("protein_arachnoserver_references","ArachnoServer"),
    ARRAY_EXPRESS("protein_array_express_references","ArrayExpress"), 
    BGEE("protein_bgee_references","Bgee"),
    BINDING_DB("protein_binding_db_references","BindingDB"),
    BIOCYC("protein_biocyc_references","BioCyc"),
    BRENDA("protein_brenda_references","BRENDA"),
    
    ENSEMBL("protein_ensembl_references", "Ensembl"), 
    EMBL("protein_embl_references","EMBL"), 
    ENSEMBL_PLANTS("protein_ensembl_plants_references", "EnsemblPlants"), 
    REFSEQ("protein_refseq_references","RefSeq"), 
    PIR("protein_pir_references","PIR"), 
    KEGG("protein_kegg_references","KEGG"), 
    
    UNIGENE("protein_unigene_references","UniGene"),
    CAZY("protein_cazy_references","CAZy"),
    CGD("protein_cgd_references", "CGD"),
    CHEMBL("protein_chembl_references", "ChEMBL"),
    CHITARS("protein_chitars_references","ChiTaRS"),
    CLEANEX("protein_cleanex_references","CleanEx"),
    COMPLUYEAST_2D_PAGE("protein_compluyeast_2dpage_references","COMPLUYEAST-2DPAGE"), 
    CONO_SERVER("protein_conoserver_references","ConoServer"),
    CTD("protein_ctd_references", "CTD"),
    CYGD("protein_cygd_references","CYGD"),
    DBSNP("protein_dbsnp_references","dbSNP"), 
    DDBJ("protein_ddbj_references","DDBJ"), 
    DICTY_BASE("protein_dicty_base_references","dictyBase"),
    DIP("protein_dip_references","DIP"), 
    DISPROT("protein_dip_references","DisProt"), 
    DMDM, DNASU, DOSAC_COBS_2D_PAGE, DRUG_BANK, ECHO_BASE, ECO_GENE, EGGNOG, 
    ENSEMBL_BACTERIA, ENSEMBL_FUNGI, ENSEMBL_METAZOA, ENSEMBL_PROTISTS, EUHCV_DB,
    EUPATH_DB, EVOLUTIONARY_TRACE, FLYBASE, GENATLAS, GENBANK, GENE3D, GENECARDS, 
    GENEFARM, GENEID, GENETREE, GENEVESTIGATOR, GENOLIST, GENOME_REVIEWS, GENOME_RNAI,
    GERMONLINE, GLYCO_SUITE_DB, GPCR_DB, GRAMENE, HINV_DB, HAMAP, HGNC, HOGENOME, 
    HOVERGEN, HPA, HSSP, HUGE, INPARANOID, INTACT, IPIR, KO, LEGIOLIST, LEPROMA,
    MAIZEGD_DB, MEROPS, MGI, MICADO, MIM, MINT, MODBASE, MYCOCLAP, NEXTBIO, 
    NEXTPROT, OGP, OMAR, ORPHANET, ORTHO_DB, PANTHER, PATHWAY_INTERACTION_DB,
    PATRIC, PAXDB, PDB, PDBJ, PDMSUM, PEPTIDE_ATLAS, PEROXIBASE, PHARM_GKB, PHCI_2D_PAGE,
    PHOSPHOSITE, PHOS_SITE, PHYLOME_DB, PIRSF, PMAP_CUT_DB, PMMA_2D_PAGE, POMBASE,
    PPTASE_DB, PRIDE, PRINTS, PRODOM, PROMEX, PROSITE, PROT_CLUST_DB, 
    PROTEIN_MODEL_PORTAL, PROTONET, PSEUDOCAP, RAT_HEART_2D_PAGE, RCSBPDB, REBASE,
    REPRODUCTION_2D_PAGE, RGD, ROUGE, SBKB, SGD, SIENA_2D_PAGE, SMART, SMR, SOURCE,
    STRING, SUPFAM, SWISS_2D_PAGE, TAIR, TCDB, TIGRFAMS, TUBERCULIST, UCD2D, UCSC,
    UNIPATHWAY, VECTORBASE, WORLD_2D_PAGE, WORMBASE, XENBASE, ZFIN;
    
    private final String uniprotAtributeValue;
    private final String proteinReferencePropertyName;
    
    private UniprotDBXref(String uniprotAttValue, String propertyName) {
        this.uniprotAtributeValue = uniprotAttValue;
        this.proteinReferencePropertyName = propertyName;
    }
    
    public String getProteinReferencePropertyName(){
        return proteinReferencePropertyName;
    }
    
    public String getUniprotAttributeValue(){
        return uniprotAtributeValue;
    }
}
