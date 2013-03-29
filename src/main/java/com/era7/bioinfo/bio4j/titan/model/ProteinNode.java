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
        return getMultiValuedProperty(GENE_NAMES_PROPERTY);        
    }

    @Override
    public String[] getAlternativeAcessions(){
        return getMultiValuedProperty(ALTERNATIVE_ACCESSIONS_PROPERTY);
    }
    
    public String[] getEMBLReferences(){        return getReference(UniprotDBXref.EMBL);        }
    public String[] getEnsemblPlantsReferences(){        return getReference(UniprotDBXref.ENSEMBL_PLANTS);    }
    public String[] getRefseqReferences(){        return getReference(UniprotDBXref.REFSEQ);    }
    public String[] getEnsemblReferences(){        return getReference(UniprotDBXref.ENSEMBL);    }
    public String[] getPIRReferences(){        return getReference(UniprotDBXref.PIR);    }
    public String[] getKeggReferences(){        return getReference(UniprotDBXref.KEGG);    }
    public String[] getArrayExpressReferences(){        return getReference(UniprotDBXref.ARRAY_EXPRESS);    }
    public String[] getUniGeneReferences(){        return getReference(UniprotDBXref.UNIGENE);    }
    public String[] getArachnoServerReferences(){        return getReference(UniprotDBXref.ARACHNO_SERVER);    }
    public String[] getBGeeReferences(){        return getReference(UniprotDBXref.BGEE);    }
    public String[] getBindingDBReferences(){        return getReference(UniprotDBXref.BINDING_DB);    }
    public String[] getBioCycReferences(){        return getReference(UniprotDBXref.BIOCYC);    }
    public String[] getBrendaReferences(){        return getReference(UniprotDBXref.BRENDA);    }
    public String[] getCazyReferences(){        return getReference(UniprotDBXref.CAZY);    }
    public String[] getCGDReferences(){        return getReference(UniprotDBXref.CGD);    }
    public String[] getCHEMBLReferences(){        return getReference(UniprotDBXref.CHEMBL);    }
    public String[] getCleanexReferences(){        return getReference(UniprotDBXref.CLEANEX);    }
    public String[] getCompluyeast2DPageReferences(){        return getReference(UniprotDBXref.COMPLUYEAST_2D_PAGE);    }
    public String[] getConoServerReferences(){        return getReference(UniprotDBXref.CONO_SERVER);    }    
    public String[] getCTDReferences(){        return getReference(UniprotDBXref.CTD);    }
    public String[] getCYGDReferences(){        return getReference(UniprotDBXref.CYGD);    }
    public String[] getDBSNPReferences(){        return getReference(UniprotDBXref.DBSNP);    }
    public String[] getDDBJReferences(){        return getReference(UniprotDBXref.DDBJ);    }
    public String[] getDictyBaseReferences(){        return getReference(UniprotDBXref.DICTY_BASE);    }
    public String[] getDIPReferences(){        return getReference(UniprotDBXref.DIP);    }
    public String[] getDisprotReferences(){        return getReference(UniprotDBXref.DISPROT);    }
    public String[] getDMDMReferences(){        return getReference(UniprotDBXref.DMDM);    }
    public String[] getDnasuReferences(){        return getReference(UniprotDBXref.DNASU);    }
    public String[] getDosacCobs2DPageReferences(){        return getReference(UniprotDBXref.DOSAC_COBS_2D_PAGE);    }    
    public String[] getDrugBankReferences(){        return getReference(UniprotDBXref.DRUG_BANK);    }
    public String[] getEchoBaseReferences(){        return getReference(UniprotDBXref.ECHO_BASE);    }
    public String[] getEcoGeneReferences(){        return getReference(UniprotDBXref.ECO_GENE);    }
    public String[] getEggnogReferences(){        return getReference(UniprotDBXref.EGGNOG);    }
    public String[] getEnsemblBacteriaReferences(){        return getReference(UniprotDBXref.ENSEMBL_BACTERIA);    }
    public String[] getEnsemblFungiReferences(){        return getReference(UniprotDBXref.ENSEMBL_FUNGI);    }
    public String[] getEnsemblMetazoaReferences(){        return getReference(UniprotDBXref.ENSEMBL_METAZOA);    }
    public String[] getEnsemblProtistsReferences(){        return getReference(UniprotDBXref.ENSEMBL_PROTISTS);    }
    public String[] getEUHCVDBReferences(){        return getReference(UniprotDBXref.EUHCV_DB);    }
    public String[] getEUPathDBReferences(){        return getReference(UniprotDBXref.EUPATH_DB);    }
    public String[] getEvolutionaryTraceReferences(){        return getReference(UniprotDBXref.EVOLUTIONARY_TRACE);    }
    public String[] getFlybaseReferences(){        return getReference(UniprotDBXref.FLYBASE);    }
    public String[] getGenAtlasReferences(){        return getReference(UniprotDBXref.GENATLAS);    }
    public String[] getGenbankReferences(){        return getReference(UniprotDBXref.GENBANK);    }
    public String[] getGene3DReferences(){        return getReference(UniprotDBXref.GENE3D);    }
    public String[] getGenecardsReferences(){        return getReference(UniprotDBXref.GENECARDS);    }
    public String[] getGenefarmReferences(){        return getReference(UniprotDBXref.GENEFARM);    }
    public String[] getGeneIDReferences(){        return getReference(UniprotDBXref.GENEID);    }
    public String[] getGeneTreeReferences(){        return getReference(UniprotDBXref.GENETREE);    }
    public String[] getGenevestigatorReferences(){        return getReference(UniprotDBXref.GENEVESTIGATOR);    }
    public String[] getGenolistReferences(){        return getReference(UniprotDBXref.GENOLIST);    }
    public String[] getGenomeReviewsReferences(){        return getReference(UniprotDBXref.GENOME_REVIEWS);    }
    public String[] getGenomeRNAIReferences(){        return getReference(UniprotDBXref.GENOME_RNAI);    }
    public String[] getGermOnlineReferences(){        return getReference(UniprotDBXref.GERMONLINE);    }    
    public String[] getGlycoSuiteDBReferences(){        return getReference(UniprotDBXref.GLYCO_SUITE_DB);    }
    public String[] getGPCRDBReferences(){        return getReference(UniprotDBXref.GPCR_DB);    }
    public String[] getGrameneReferences(){        return getReference(UniprotDBXref.GRAMENE);    }
    public String[] getHINVDBReferences(){        return getReference(UniprotDBXref.HINV_DB);    }
    public String[] getHamapReferences(){        return getReference(UniprotDBXref.HAMAP);    }
    public String[] getHGNCReferences(){        return getReference(UniprotDBXref.HGNC);    }
    public String[] getHogenomReferences(){        return getReference(UniprotDBXref.HOGENOM);    }
    public String[] getHovergenReferences(){        return getReference(UniprotDBXref.HOVERGEN);    }
    public String[] getHPAReferences(){        return getReference(UniprotDBXref.HPA);    }
    public String[] getHSSPReferences(){        return getReference(UniprotDBXref.HSSP);    }
    public String[] getHugeReferences(){        return getReference(UniprotDBXref.HUGE);    }
    public String[] getIMGTReferences(){        return getReference(UniprotDBXref.IMGT);    }
    public String[] getInparanoidReferences(){        return getReference(UniprotDBXref.INPARANOID);    }
    public String[] getIntactReferences(){        return getReference(UniprotDBXref.INTACT);    }
    public String[] getIPIReferences(){        return getReference(UniprotDBXref.IPI);    }
    public String[] getKOReferences(){        return getReference(UniprotDBXref.KO);    }
    public String[] getLegioListReferences(){        return getReference(UniprotDBXref.LEGIOLIST);    }    
    public String[] getLepromaReferences(){        return getReference(UniprotDBXref.LEPROMA);    }
    public String[] getMaizeGDBReferences(){        return getReference(UniprotDBXref.MAIZEGD_DB);    }
    public String[] getMeropsReferences(){        return getReference(UniprotDBXref.MEROPS);    }
    public String[] getMGIReferences(){        return getReference(UniprotDBXref.MGI);    }
    public String[] getMicadoReferences(){        return getReference(UniprotDBXref.MICADO);    }
    public String[] getMIMReferences(){        return getReference(UniprotDBXref.MIM);    }
    public String[] getMintReferences(){        return getReference(UniprotDBXref.MINT);    }
    public String[] getModbaseReferences(){        return getReference(UniprotDBXref.MODBASE);    }
    public String[] getMycoClapReferences(){        return getReference(UniprotDBXref.MYCOCLAP);    }
    public String[] getNextBioReferences(){        return getReference(UniprotDBXref.NEXTBIO);    }
    public String[] getNextProtReferences(){        return getReference(UniprotDBXref.NEXTPROT);    }
    public String[] getOGPReferences(){        return getReference(UniprotDBXref.OGP);    }
    public String[] getOMAReferences(){        return getReference(UniprotDBXref.OMA);    }
    public String[] getOrphanetReferences(){        return getReference(UniprotDBXref.ORPHANET);    }
    public String[] getOrthoDBReferences(){        return getReference(UniprotDBXref.ORTHO_DB);    }
    public String[] getPantherReferences(){        return getReference(UniprotDBXref.PANTHER);    }
    public String[] getPathwayInteractionDBReferences(){        return getReference(UniprotDBXref.PATHWAY_INTERACTION_DB);    }
    public String[] getPatricReferences(){        return getReference(UniprotDBXref.PATRIC);    }
    public String[] getPaxDBReferences(){        return getReference(UniprotDBXref.PAXDB);    }
    public String[] getPDBReferences(){        return getReference(UniprotDBXref.PDB);    }
    public String[] getPDBJReferences(){        return getReference(UniprotDBXref.PDBJ);    }
    public String[] getPDBSumReferences(){        return getReference(UniprotDBXref.PDBSUM);   }
    public String[] getPeptideAtlasReferences(){        return getReference(UniprotDBXref.PEPTIDE_ATLAS);    }
    public String[] getPeroxibaseReferences(){        return getReference(UniprotDBXref.PEROXIBASE);    }
    public String[] getPharmGKBReferences(){        return getReference(UniprotDBXref.PHARM_GKB);    }
    public String[] getPhosphositeReferences(){        return getReference(UniprotDBXref.PHOSPHOSITE);    }
    public String[] getPhosSiteReferences(){        return getReference(UniprotDBXref.PHOS_SITE);    }
    public String[] getPhylomeDBReferences(){        return getReference(UniprotDBXref.PHYLOME_DB);    }
    public String[] getPIRSFReferences(){        return getReference(UniprotDBXref.PIRSF);    }
    public String[] getPmapCutDBReferences(){        return getReference(UniprotDBXref.PMAP_CUT_DB);    }
    public String[] getPombaseReferences(){        return getReference(UniprotDBXref.POMBASE);    }
    public String[] getPptaseDBReferences(){        return getReference(UniprotDBXref.PPTASE_DB);    }
    public String[] getPrideReferences(){        return getReference(UniprotDBXref.PRIDE);    }
    public String[] getPrintsReferences(){        return getReference(UniprotDBXref.PRINTS);    }
    public String[] getProdomReferences(){        return getReference(UniprotDBXref.PRODOM);    }
    public String[] getPromexReferences(){        return getReference(UniprotDBXref.PROMEX);    }
    public String[] getPrositeReferences(){        return getReference(UniprotDBXref.PROSITE);    }
    public String[] getProtClustDBReferences(){        return getReference(UniprotDBXref.PROT_CLUST_DB);    }
    public String[] getProteinModelPortalReferences(){        return getReference(UniprotDBXref.PROTEIN_MODEL_PORTAL);    }
    public String[] getProtonetReferences(){        return getReference(UniprotDBXref.PROTONET);    }
    public String[] getPseudoCapReferences(){        return getReference(UniprotDBXref.PSEUDOCAP);    }
    public String[] getRCSBPDBReferences(){       return getReference(UniprotDBXref.RCSBPDB);    }
    public String[] getRebaseReferences(){        return getReference(UniprotDBXref.REBASE);    }
    public String[] getReproduction2DPageReferences(){        return getReference(UniprotDBXref.REPRODUCTION_2D_PAGE);    }
    public String[] getRGDReferences(){        return getReference(UniprotDBXref.RGD);    }
    public String[] getRougeReferences(){        return getReference(UniprotDBXref.ROUGE);    }
    public String[] getSBKBReferences(){        return getReference(UniprotDBXref.SBKB);    }
    public String[] getSGDReferences(){        return getReference(UniprotDBXref.SGD);    }
    public String[] getSmartReferences(){        return getReference(UniprotDBXref.SMART);    }
    public String[] getSMRReferences(){        return getReference(UniprotDBXref.SMR);    }
    public String[] getSourceReferences(){        return getReference(UniprotDBXref.SOURCE);    }
    public String[] getStringReferences(){        return getReference(UniprotDBXref.STRING);    }
    public String[] getSupfamReferences(){        return getReference(UniprotDBXref.SUPFAM);    }
    public String[] getSwiss2DPageReferences(){        return getReference(UniprotDBXref.SWISS_2D_PAGE);    }
    public String[] getTairReferences(){        return getReference(UniprotDBXref.TAIR );    }
    public String[] getTCDBReferences(){        return getReference(UniprotDBXref.TCDB);    }
    public String[] getTIGRFAMSReferences(){        return getReference(UniprotDBXref.TIGRFAMS);    }
    public String[] getTuberculistReferences(){        return getReference(UniprotDBXref.TUBERCULIST);    }
    public String[] getUCD2DPageReferences(){        return getReference(UniprotDBXref.UCD_2D_PAGE);    }
    public String[] getUCSCPageReferences(){        return getReference(UniprotDBXref.UCSC);    }
    public String[] getUnipathwayReferences(){        return getReference(UniprotDBXref.UNIPATHWAY);    }
    public String[] getVectorBaseReferences(){        return getReference(UniprotDBXref.VECTORBASE);    }
    public String[] getWorld2DPageReferences(){        return getReference(UniprotDBXref.WORLD_2D_PAGE);    }
    public String[] getWormBaseReferences(){        return getReference(UniprotDBXref.WORMBASE);    }
    public String[] getXenBaseReferences(){        return getReference(UniprotDBXref.XENBASE);    }
    public String[] getZfinReferences(){        return getReference(UniprotDBXref.ZFIN);    }
    
    
    
    //----------------------------SETTERS-----------------------------------
    //---------------------------------------------------------------------
    
    @Override
    public void setGeneNames(String[] list){    setMultiValuedProperty(GENE_NAMES_PROPERTY, list);}
    @Override
    public void setAlternativeAccessions(String[] list){ setMultiValuedProperty(ALTERNATIVE_ACCESSIONS_PROPERTY, list);}
       
    
    public void setRefseqReferences(String[] list){        setReference(UniprotDBXref.REFSEQ, list);    }   
    public void setKeggReferences(String[] list){        setReference(UniprotDBXref.KEGG, list);    }
    public void setArrayExpressReferences(String[] list){        setReference(UniprotDBXref.ARRAY_EXPRESS, list);    }
    public void setUniGeneReferences(String[] list){        setReference(UniprotDBXref.UNIGENE, list);    }
    public void setAllergomeReferences(String[] list){        setReference(UniprotDBXref.ALLERGOME, list);    }
    public void setArachnoServerReferences(String[] list){        setReference(UniprotDBXref.ARACHNO_SERVER, list);    }
    public void setBGeeReferences(String[] list){        setReference(UniprotDBXref.BGEE, list);    }
    public void setBindingDBReferences(String[] list){        setReference(UniprotDBXref.BINDING_DB, list);    }
    public void setBiocycReferences(String[] list){        setReference(UniprotDBXref.BIOCYC, list);    }
    public void setBrendaReferences(String[] list){        setReference(UniprotDBXref.BRENDA, list);    }
    public void setCazyReferences(String[] list){        setReference(UniprotDBXref.CAZY, list);    }
    public void setCGDReferences(String[] list){        setReference(UniprotDBXref.CGD, list);    }
    public void setCHEmblReferences(String[] list){        setReference(UniprotDBXref.CHEMBL, list);    }
    public void setCleanexReferences(String[] list){        setReference(UniprotDBXref.CLEANEX, list);    }
    public void setCompluYeast2DPageReferences(String[] list){        setReference(UniprotDBXref.COMPLUYEAST_2D_PAGE, list);    }
    public void setConoServerReferences(String[] list){        setReference(UniprotDBXref.CONO_SERVER, list);    }
    public void setCTDReferences(String[] list){        setReference(UniprotDBXref.CTD, list);    }
    public void setCYGDReferences(String[] list){        setReference(UniprotDBXref.CYGD, list);    }
    public void setDBSNPReferences(String[] list){        setReference(UniprotDBXref.DBSNP, list);    }
    public void setDDBJReferences(String[] list){        setReference(UniprotDBXref.DDBJ, list);    }
    public void setDictyBaseReferences(String[] list){        setReference(UniprotDBXref.DICTY_BASE, list);    }
    public void setDIPReferences(String[] list){        setReference(UniprotDBXref.DIP, list);    }
    public void setDisprotReferences(String[] list){        setReference(UniprotDBXref.DISPROT, list);    }
    public void setDMDMReferences(String[] list){        setReference(UniprotDBXref.DMDM, list);    }
    public void setDnasuReferences(String[] list){        setReference(UniprotDBXref.DNASU, list);    }
    public void setDosacCobs2DPageReferences(String[] list){        setReference(UniprotDBXref.DOSAC_COBS_2D_PAGE, list);    }
    public void setDrugbankReferences(String[] list){        setReference(UniprotDBXref.DRUG_BANK, list);    }
    public void setEchoBaseReferences(String[] list){        setReference(UniprotDBXref.ECHO_BASE, list);    }
    public void setEcogeneReferences(String[] list){        setReference(UniprotDBXref.ECO_GENE, list);    }
    public void setEggnogReferences(String[] list){        setReference(UniprotDBXref.EGGNOG, list);    }
    public void setEMBLreferences(String[] list){        setReference(UniprotDBXref.EMBL, list);           }   
    public void setEnsemblBacteriaReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_BACTERIA, list);    }
    public void setEnsemblFungiReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_FUNGI, list);    }
    public void setEnsemblMetazoaReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_METAZOA, list);    }
    public void setEnsemblPlantsReferences(String[] list){        setReference(UniprotDBXref.ENSEMBL_PLANTS, list);    }
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
    public void setPIRReferences(String[] list){        setReference(UniprotDBXref.PIR, list);    }
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
        setMultiValuedProperty(ref.getProteinReferencePropertyName(), value);
    }
    
    private void setMultiValuedProperty(String propertyName, String[] value){
        TitanVertex tempVertex = (TitanVertex) vertex;
        tempVertex.removeProperty(propertyName);
        for (String string : value) {
            tempVertex.addProperty(propertyName, string);
        } 
    }
    
    private String[] getMultiValuedProperty(String propertyName){
        TitanVertex tempVertex = (TitanVertex) vertex;
        Iterator<TitanProperty> iterator = tempVertex.getProperties(propertyName).iterator();
        LinkedList<String> list = new LinkedList<>();
        while(iterator.hasNext()){
            list.add(String.valueOf(iterator.next().getAttribute()));
        }
        return list.toArray(new String[list.size()]);
    }
        
    
}
