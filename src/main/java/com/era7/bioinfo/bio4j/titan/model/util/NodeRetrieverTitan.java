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
package com.era7.bioinfo.bio4j.titan.model.util;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductInitiationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductPromoterRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductRibosomalFrameshiftingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.aproducts.AlternativeProductSplicingRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.model.enums.UniprotDBXref;
import com.era7.bioinfo.bio4j.model.nodes.Dataset;
import com.era7.bioinfo.bio4j.model.nodes.GoTerm;
import com.era7.bioinfo.bio4j.model.util.NodeRetriever;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeRetrieverTitan implements NodeRetriever{

    protected Bio4jManager manager;

    public NodeRetrieverTitan(Bio4jManager bio4jManager) {
        manager = bio4jManager;
    }

    //-------------------------------------------------------------------
    //--------------------------ENZYME-----------------------------------
    @Override
    public EnzymeNode getEnzymeById(String id) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(EnzymeNode.ID_PROPERTY, id).iterator();
        if (iterator.hasNext()) {
            return new EnzymeNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------------DATASETS-----------------------------------
    @Override
    public DatasetNode getDatasetByName(String name) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, name).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    @Override
    public Dataset getSwissProtDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.SWISS_PROT_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    @Override
    public DatasetNode getTremblDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.TREMBL_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------------REFSEQ-----------------------------------    
    @Override
    public GenomeElementNode getGenomeElementByVersion(String version) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GenomeElementNode.VERSION_PROPERTY, version).iterator();
        if (iterator.hasNext()) {
            return new GenomeElementNode(iterator.next());
        } else {
            return null;
        }
    }

    //-------------------------------------------------------------------
    //--------------------GENE ONTOLOGY--------------------------------    
    /**
     *
     * @param goId
     * @return GoTermNode with the id provided
     */
    @Override
    public GoTermNode getGoTermById(String goId) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, goId).iterator();
        if (iterator.hasNext()) {
            return new GoTermNode(iterator.next());
        } else {
            return null;
        }
    }
    @Override
    public GoTerm getMolecularFunctionGoTerm(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, GoTermNode.MOLECULAR_FUNCTION_GO_ID).iterator();
        if(iterator.hasNext()){
            return new GoTermNode(iterator.next());
        }else{
            return null;
        }
    }
    @Override
    public GoTermNode getBiologicalProcessGoTerm(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, GoTermNode.BIOLOGICAL_PROCESS_GO_ID).iterator();
        if(iterator.hasNext()){
            return new GoTermNode(iterator.next());
        }else{
            return null;
        }
    }
    @Override
    public GoTermNode getCellularComponentGoTerm(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, GoTermNode.CELLULAR_COMPONENT_GO_ID).iterator();
        if(iterator.hasNext()){
            return new GoTermNode(iterator.next());
        }else{
            return null;
        }
    }
    //-------------------------------------------------------------------
    //--------------------PROTEINS--------------------------------

    /**
     *
     * @param proteinAccession
     * @return ProteinNode with the accession provided
     */
    @Override
    public ProteinNode getProteinByAccession(String proteinAccession) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.ACCESSION_PROPERTY, proteinAccession).iterator();
        if (iterator.hasNext()) {
            return new ProteinNode(iterator.next());
        } else {
            //we check if we can find it as a an alternative accession
            iterator = manager.getGraph().getVertices(ProteinNode.ALTERNATIVE_ACCESSIONS_PROPERTY, proteinAccession).iterator();
            if (iterator.hasNext()) {
                return new ProteinNode(iterator.next());
            } else {
                return null;
            }
        }
    }
    /**
     * 
     * @param ensemblPlantsRef 
     * @return ProteinNode with the Ensembl Plants reference provided
     */
    @Override
    public List<ProteinNode> getProteinByEnsemblPlantsRef(String ensemblPlantsRef){        
        return getProteinReference(UniprotDBXref.ENSEMBL_PLANTS.getProteinReferencePropertyName(), ensemblPlantsRef);          
    }    
    /**
     * 
     * @param proteinFullName
     * @return List of proteins (if any) which match the full name provided
     */
    @Override
    public List<ProteinNode> getProteinByFullName(String proteinFullName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.FULL_NAME_PROPERTY, proteinFullName).iterator();        
        List<ProteinNode> list = new LinkedList<>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;        
    }
    /**
     * 
     * @param proteinGeneName
     * @return List of proteins (if any) which match the gene name provided
     */
    @Override
    public List<ProteinNode> getProteinByGeneNames(String proteinGeneName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.GENE_NAMES_PROPERTY, proteinGeneName).iterator();        
        List<ProteinNode> list = new LinkedList<>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;        
    }
    @Override
    public List<ProteinNode> getProteinByPIRReference(String id){              
        return getProteinReference(UniprotDBXref.PIR.getProteinReferencePropertyName(), id);                  
    }
    @Override
    public List<ProteinNode> getProteinByKEGGReference(String id){            
        return getProteinReference(UniprotDBXref.KEGG.getProteinReferencePropertyName(), id);                
    }
    @Override
    public List<ProteinNode> getProteinByEmblReference(String id){            
        return getProteinReference(UniprotDBXref.EMBL.getProteinReferencePropertyName(), id);            
    }
    @Override
    public List<ProteinNode> getProteinByRefSeqReference(String id){              
        return getProteinReference(UniprotDBXref.REFSEQ.getProteinReferencePropertyName(), id);      
    }
    @Override
    public List<ProteinNode> getProteinByArrayExpressReference(String id){    
        return getProteinReference(UniprotDBXref.ARRAY_EXPRESS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinByUniGeneReference(String id){           
        return getProteinReference(UniprotDBXref.UNIGENE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinByAllergomeReference(String id){           
        return getProteinReference(UniprotDBXref.ALLERGOME.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByArachnoServerReference(String id){           
        return getProteinReference(UniprotDBXref.ARACHNO_SERVER.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByBGEEReference(String id){           
        return getProteinReference(UniprotDBXref.BGEE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByBindingDBReference(String id){           
        return getProteinReference(UniprotDBXref.BINDING_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByBioCycReference(String id){           
        return getProteinReference(UniprotDBXref.BIOCYC.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByBrendaReference(String id){           
        return getProteinReference(UniprotDBXref.BRENDA.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByCazyReference(String id){           
        return getProteinReference(UniprotDBXref.CAZY.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByCGDReference(String id){           
        return getProteinReference(UniprotDBXref.CGD.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByCHEmblReference(String id){           
        return getProteinReference(UniprotDBXref.CHEMBL.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByCleanexReference(String id){           
        return getProteinReference(UniprotDBXref.CLEANEX.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByCompluYeast2DPageReference(String id){           
        return getProteinReference(UniprotDBXref.COMPLUYEAST_2D_PAGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByConoServerReference(String id) {
        return getProteinReference(UniprotDBXref.CONO_SERVER.getProteinReferencePropertyName(), id);
    }    
    @Override
    public List<ProteinNode> getProteinsByCTDReference(String id){           
        return getProteinReference(UniprotDBXref.CTD.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByCYGDReference(String id){           
        return getProteinReference(UniprotDBXref.CYGD.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDBSNPReference(String id){           
        return getProteinReference(UniprotDBXref.DBSNP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDDBJReference(String id){           
        return getProteinReference(UniprotDBXref.DDBJ.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDictyBaseReference(String id){           
        return getProteinReference(UniprotDBXref.DICTY_BASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDIPReference(String id){           
        return getProteinReference(UniprotDBXref.DIP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDisprotReference(String id) {
        return getProteinReference(UniprotDBXref.DISPROT.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDMDMReference(String id){           
        return getProteinReference(UniprotDBXref.DMDM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDNASUReference(String id){           
        return getProteinReference(UniprotDBXref.DNASU.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByDosacCobs2DPageReference(String id){           
        return getProteinReference(UniprotDBXref.DOSAC_COBS_2D_PAGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEchoBaseReference(String id){           
        return getProteinReference(UniprotDBXref.ECHO_BASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEcoGeneReference(String id){           
        return getProteinReference(UniprotDBXref.ECO_GENE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEggNogReference(String id){           
        return getProteinReference(UniprotDBXref.EGGNOG.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEnsemblBacteriaReference(String id){           
        return getProteinReference(UniprotDBXref.ENSEMBL_BACTERIA.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEnsemblFungiReference(String id){           
        return getProteinReference(UniprotDBXref.ENSEMBL_FUNGI.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEnsemblMetazoaReference(String id){           
        return getProteinReference(UniprotDBXref.ENSEMBL_METAZOA.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEnsemblProtistsReference(String id){           
        return getProteinReference(UniprotDBXref.ENSEMBL_PROTISTS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEUHCVDBReference(String id){           
        return getProteinReference(UniprotDBXref.EUHCV_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEUPathDBReference(String id){           
        return getProteinReference(UniprotDBXref.EUPATH_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByEvolutionaryTraceReference(String id){           
        return getProteinReference(UniprotDBXref.EVOLUTIONARY_TRACE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByFlyBaseReference(String id){           
        return getProteinReference(UniprotDBXref.FLYBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenAtlasReference(String id){           
        return getProteinReference(UniprotDBXref.GENATLAS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenBankReference(String id){           
        return getProteinReference(UniprotDBXref.GENBANK.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGene3DReference(String id){           
        return getProteinReference(UniprotDBXref.GENE3D.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenecardsReference(String id){           
        return getProteinReference(UniprotDBXref.GENECARDS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGeneFarmReference(String id){           
        return getProteinReference(UniprotDBXref.GENEFARM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGeneIDReference(String id){           
        return getProteinReference(UniprotDBXref.GENEID.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGeneTreeReference(String id){           
        return getProteinReference(UniprotDBXref.GENETREE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenevestigatorReference(String id){           
        return getProteinReference(UniprotDBXref.GENEVESTIGATOR.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenolistReference(String id){           
        return getProteinReference(UniprotDBXref.GENOLIST.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenomeReviewsReference(String id){           
        return getProteinReference(UniprotDBXref.GENOME_REVIEWS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGenomeRNAIReference(String id){           
        return getProteinReference(UniprotDBXref.GENOME_RNAI.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGermOnlineReference(String id){           
        return getProteinReference(UniprotDBXref.GERMONLINE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGlycoSuiteDBReference(String id){           
        return getProteinReference(UniprotDBXref.GLYCO_SUITE_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGPCRDBReference(String id){           
        return getProteinReference(UniprotDBXref.GPCR_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByGrameneReference(String id){           
        return getProteinReference(UniprotDBXref.GRAMENE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHinvDBReference(String id){           
        return getProteinReference(UniprotDBXref.HINV_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHamapReference(String id){           
        return getProteinReference(UniprotDBXref.HAMAP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHGNCReference(String id){           
        return getProteinReference(UniprotDBXref.HGNC.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHogenomReference(String id){           
        return getProteinReference(UniprotDBXref.HOGENOM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHovergenReference(String id){           
        return getProteinReference(UniprotDBXref.HOVERGEN.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHPAReference(String id){           
        return getProteinReference(UniprotDBXref.HPA.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHSSPReference(String id){           
        return getProteinReference(UniprotDBXref.HSSP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByHugeReference(String id){           
        return getProteinReference(UniprotDBXref.HUGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByIMGTReference(String id){           
        return getProteinReference(UniprotDBXref.IMGT.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByInparanoidReference(String id){           
        return getProteinReference(UniprotDBXref.INPARANOID.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByIntactReference(String id){           
        return getProteinReference(UniprotDBXref.INTACT.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByIPIReference(String id){           
        return getProteinReference(UniprotDBXref.IPI.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByKOReference(String id){           
        return getProteinReference(UniprotDBXref.KO.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByLegioListReference(String id){           
        return getProteinReference(UniprotDBXref.LEGIOLIST.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByLepromaReference(String id){           
        return getProteinReference(UniprotDBXref.LEPROMA.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMaizeGDBReference(String id){           
        return getProteinReference(UniprotDBXref.MAIZEGD_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMeropsReference(String id){           
        return getProteinReference(UniprotDBXref.MEROPS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMGIReference(String id){           
        return getProteinReference(UniprotDBXref.MGI.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMicadoReference(String id){           
        return getProteinReference(UniprotDBXref.MICADO.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMIMReference(String id){           
        return getProteinReference(UniprotDBXref.MIM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMintReference(String id){           
        return getProteinReference(UniprotDBXref.MINT.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByModBaseReference(String id){           
        return getProteinReference(UniprotDBXref.MODBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByMycoclapReference(String id){           
        return getProteinReference(UniprotDBXref.MYCOCLAP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByNextBioReference(String id){           
        return getProteinReference(UniprotDBXref.NEXTBIO.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByNextProtReference(String id){           
        return getProteinReference(UniprotDBXref.NEXTPROT.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByOGPReference(String id){           
        return getProteinReference(UniprotDBXref.OGP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByOMAReference(String id){           
        return getProteinReference(UniprotDBXref.OMA.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByOrphanetReference(String id){           
        return getProteinReference(UniprotDBXref.ORPHANET.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByOrthoDBReference(String id){           
        return getProteinReference(UniprotDBXref.ORTHO_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPantherReference(String id){           
        return getProteinReference(UniprotDBXref.PANTHER.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPathwayReference(String id){           
        return getProteinReference(UniprotDBXref.PATHWAY_INTERACTION_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPatricReference(String id){           
        return getProteinReference(UniprotDBXref.PATRIC.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPaxDBReference(String id){           
        return getProteinReference(UniprotDBXref.PAXDB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPDBReference(String id){           
        return getProteinReference(UniprotDBXref.PDB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPDBJReference(String id){           
        return getProteinReference(UniprotDBXref.PDBJ.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPDBSumReference(String id){           
        return getProteinReference(UniprotDBXref.PDBSUM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPeptideAtlasReference(String id){           
        return getProteinReference(UniprotDBXref.PEPTIDE_ATLAS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPeroxiBaseReference(String id){           
        return getProteinReference(UniprotDBXref.PEROXIBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPharmGKBReference(String id){           
        return getProteinReference(UniprotDBXref.PHARM_GKB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPhosphositeReference(String id){           
        return getProteinReference(UniprotDBXref.PHOSPHOSITE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPhosSiteReference(String id){           
        return getProteinReference(UniprotDBXref.PHOS_SITE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPhylomeDBReference(String id){           
        return getProteinReference(UniprotDBXref.PHYLOME_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPirsfReference(String id){           
        return getProteinReference(UniprotDBXref.PIRSF.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPMapCutDBReference(String id){           
        return getProteinReference(UniprotDBXref.PMAP_CUT_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPPTaseDBReference(String id){           
        return getProteinReference(UniprotDBXref.PPTASE_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPrideReference(String id){           
        return getProteinReference(UniprotDBXref.PRIDE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPrintsReference(String id){           
        return getProteinReference(UniprotDBXref.PRINTS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByProDomReference(String id){           
        return getProteinReference(UniprotDBXref.PRODOM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPromexReference(String id){           
        return getProteinReference(UniprotDBXref.PROMEX.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByProSiteReference(String id){           
        return getProteinReference(UniprotDBXref.PROSITE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByProtClustDBReference(String id){           
        return getProteinReference(UniprotDBXref.PROT_CLUST_DB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByProteinModelPortalReference(String id){           
        return getProteinReference(UniprotDBXref.PROTEIN_MODEL_PORTAL.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByProtonetReference(String id){           
        return getProteinReference(UniprotDBXref.PROTONET.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByPseudoCapReference(String id){           
        return getProteinReference(UniprotDBXref.PSEUDOCAP.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByRCSBPDBReference(String id){           
        return getProteinReference(UniprotDBXref.RCSBPDB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByReBaseReference(String id){           
        return getProteinReference(UniprotDBXref.REBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByReproductionReference(String id){           
        return getProteinReference(UniprotDBXref.REPRODUCTION_2D_PAGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByRGDReference(String id){           
        return getProteinReference(UniprotDBXref.RGD.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByRougeReference(String id){           
        return getProteinReference(UniprotDBXref.ROUGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySBKBReference(String id){           
        return getProteinReference(UniprotDBXref.SBKB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySGDReference(String id){           
        return getProteinReference(UniprotDBXref.SGD.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySmartReference(String id){           
        return getProteinReference(UniprotDBXref.SMART.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySMRReference(String id){           
        return getProteinReference(UniprotDBXref.SMR.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySourceReference(String id){           
        return getProteinReference(UniprotDBXref.SOURCE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByStringReference(String id){           
        return getProteinReference(UniprotDBXref.STRING.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySupfamReference(String id){           
        return getProteinReference(UniprotDBXref.SUPFAM.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsBySwiss2DPageReference(String id){           
        return getProteinReference(UniprotDBXref.SWISS_2D_PAGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByTairReference(String id){           
        return getProteinReference(UniprotDBXref.TAIR.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByTCDBReference(String id){           
        return getProteinReference(UniprotDBXref.TCDB.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByTigrFamsReference(String id){           
        return getProteinReference(UniprotDBXref.TIGRFAMS.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByTuberculistReference(String id){           
        return getProteinReference(UniprotDBXref.TUBERCULIST.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByUCD2DPageReference(String id){           
        return getProteinReference(UniprotDBXref.UCD_2D_PAGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByUCSCReference(String id){           
        return getProteinReference(UniprotDBXref.UCSC.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByUniPathwayReference(String id){           
        return getProteinReference(UniprotDBXref.UNIPATHWAY.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByVectorBaseReference(String id){           
        return getProteinReference(UniprotDBXref.VECTORBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByWorld2DPageReference(String id){           
        return getProteinReference(UniprotDBXref.WORLD_2D_PAGE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByWormBaseReference(String id){           
        return getProteinReference(UniprotDBXref.WORMBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByXenBaseReference(String id){           
        return getProteinReference(UniprotDBXref.XENBASE.getProteinReferencePropertyName(), id);
    }
    @Override
    public List<ProteinNode> getProteinsByZfinReference(String id){           
        return getProteinReference(UniprotDBXref.ZFIN.getProteinReferencePropertyName(), id);
    }
    //-------------------------------------------------------------------
    //--------------------KEYWORDS--------------------------------    
    /**
     * 
     * @param keywordId 
     * @return KeywordNode with the id provided
     */
    @Override
    public KeywordNode getKeywordById(String keywordId){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(KeywordNode.ID_PROPERTY, keywordId).iterator();        
        if(iterator.hasNext()){
            return new KeywordNode(iterator.next());
        }else{
            return null;
        }        
    }    
    /**
     * 
     * @param keywordName 
     * @return KeywordNode with the id provided
     */
    @Override
    public KeywordNode getKeywordByName(String keywordName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(KeywordNode.NAME_PROPERTY, keywordName).iterator();        
        if(iterator.hasNext()){
            return new KeywordNode(iterator.next());
        }else{
            return null;
        }        
    }
    
    //-------------------------------------------------------------------
    //--------------------INTERPRO--------------------------------
    /**
     * 
     * @param interproId 
     * @return InterproNode with the id provided
     */
    @Override
    public InterproNode getInterproById(String interproId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(InterproNode.ID_PROPERTY, interproId).iterator();        
        if(iterator.hasNext()){
            return new InterproNode(iterator.next());
        }else{
            return null;
        }        
    }
    //-------------------------------------------------------------------
    //--------------------PFAM--------------------------------
    /**
     * 
     * @param pfamId 
     * @return PfamNode with the id provided
     */
    @Override
    public PfamNode getPfamById(String pfamId){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PfamNode.ID_PROPERTY, pfamId).iterator();        
        if(iterator.hasNext()){
            return new PfamNode(iterator.next());
        }else{
            return null;
        }        
    }
    //-------------------------------------------------------------------
    //--------------------ORGANISM--------------------------------
    /**
     * 
     * @param scientificName 
     * @return OrganismNode with the scientific name provided
     */
    @Override
    public OrganismNode getOrganismByScientificName(String scientificName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OrganismNode.SCIENTIFIC_NAME_PROPERTY, scientificName).iterator();        
        if(iterator.hasNext()){
            return new OrganismNode(iterator.next());
        }else{
            return null;
        }        
    }
    /**
     * 
     * @param ncbiTaxonomyId 
     * @return OrganismNode with the scientific name provided
     */
    @Override
    public OrganismNode getOrganismByNCBITaxonomyId(String ncbiTaxonomyId){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, ncbiTaxonomyId).iterator();        
        if(iterator.hasNext()){
            return new OrganismNode(iterator.next());
        }else{
            return null;
        }        
    }
    //-------------------------------------------------------------------
    //--------------------TAXON--------------------------------
    
    /**
     * 
     * @param taxonName
     * @return TaxonNode with the name provided
     */
    @Override
    public TaxonNode getTaxonByName(String taxonName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(TaxonNode.NAME_PROPERTY, taxonName).iterator();        
        if(iterator.hasNext()){
            return new TaxonNode(iterator.next());
        }else{
            return null;
        }   
    }
    /**
     * 
     * @param taxId
     * @return NCBITaxonNode with the tax id provided
     */
    @Override
    public NCBITaxonNode getNCBITaxonByTaxId(String taxId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(NCBITaxonNode.TAX_ID_PROPERTY, taxId).iterator();        
        if(iterator.hasNext()){
            return new NCBITaxonNode(iterator.next());
        }else{
            return null;
        }   
    }
    /**
     * 
     * @param giId
     * @return NCBITaxonNode with the tax id provided
     */
    @Override
    public NCBITaxonNode getNCBITaxonByGiId(String giId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(NCBITaxonNode.GI_IDS_PROPERTY, giId).iterator();        
        if(iterator.hasNext()){
            return new NCBITaxonNode(iterator.next());
        }else{
            return null;
        }   
    }
    //-------------------------------------------------------------------
    //--------------------ISOFORMS--------------------------------
    /**
     * 
     * @param isoformId
     * @return IsoformNode with the id provided
     */
    @Override
    public IsoformNode getIsoformById(String isoformId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(IsoformNode.ID_PROPERTY, isoformId).iterator();        
        if(iterator.hasNext()){
            return new IsoformNode(iterator.next());
        }else{
            return null;
        }   
    }
    //-------------------------------------------------------------------
    //--------------------PERSON--------------------------------
    /**
     * 
     * @param personName
     * @return PersonNode list with the name matching the value provided
     */
    @Override
    public List<PersonNode> getPersonByName(String personName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PersonNode.NAME_PROPERTY, personName).iterator();        
        List<PersonNode> list = new LinkedList<PersonNode>();        
        while(iterator.hasNext()){
            list.add(new PersonNode(iterator.next()));
        }        
        return list; 
    }
    //-------------------------------------------------------------------
    //--------------------CONSORTIUMS--------------------------------
    /**
     * 
     * @param consortiumName
     * @return ConsortiumNode with the name provided
     */
    @Override
    public ConsortiumNode getConsortiumByName(String consortiumName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ConsortiumNode.NAME_PROPERTY, consortiumName).iterator();        
        if(iterator.hasNext()){
            return new ConsortiumNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------INSTITUTES--------------------------------
    /**
     * 
     * @param instituteName
     * @return InstituteNode with the name provided
     */
    @Override
    public InstituteNode getInstituteByName(String instituteName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(InstituteNode.NAME_PROPERTY, instituteName).iterator();        
        if(iterator.hasNext()){
            return new InstituteNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------COUNTRIES--------------------------------
    /**
     * 
     * @param countryName
     * @return CountryNode with the name provided
     */
    @Override
    public CountryNode getCountryNodeByName(String countryName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(CountryNode.NAME_PROPERTY, countryName).iterator();        
        if(iterator.hasNext()){
            return new CountryNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------CITY--------------------------------
    /**
     * 
     * @param cityName
     * @return CityNode with the name provided
     */
    @Override
    public CityNode getCityNodeByName(String cityName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(CityNode.NAME_PROPERTY, cityName).iterator();        
        if(iterator.hasNext()){
            return new CityNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------THESIS--------------------------------
    /**
     * 
     * @param thesisTitle
     * @return ThesisNode list with the name matching the value provided
     */
    @Override
    public List<ThesisNode> getThesisByTitle(String thesisTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ThesisNode.TITLE_PROPERTY, thesisTitle).iterator();        
        List<ThesisNode> list = new LinkedList<ThesisNode>();        
        while(iterator.hasNext()){
            list.add(new ThesisNode(iterator.next()));
        }        
        return list; 
    }
    //-------------------------------------------------------------------
    //--------------------PATENTS--------------------------------
    /**
     * 
     * @param patentNumber
     * @return PatentNode with the number provided
     */
    @Override
    public PatentNode getPatentByNumber(String patentNumber){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PatentNode.NUMBER_PROPERTY, patentNumber).iterator();        
        if(iterator.hasNext()){
            return new PatentNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------BOOKS--------------------------------
    /**
     * 
     * @param bookName
     * @return BookNode list with the name matching the value provided
     */
    @Override
    public List<BookNode> getBooksByName(String bookName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(BookNode.NAME_PROPERTY, bookName).iterator();        
        List<BookNode> list = new LinkedList<BookNode>();        
        while(iterator.hasNext()){
            list.add(new BookNode(iterator.next()));
        }        
        return list; 
    }
    //-------------------------------------------------------------------
    //--------------------PUBLISHER--------------------------------
    /**
     * 
     * @param publisherName
     * @return PublisherNode with the name provided
     */
    @Override
    public PublisherNode getPublisherByName(String publisherName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(PublisherNode.NAME_PROPERTY, publisherName).iterator();        
        if(iterator.hasNext()){
            return new PublisherNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------ONLINE ARTICLES--------------------------------
    /**
     * 
     * @param onlineArticleTitle
     * @return OnlineArticleNode list with the title matching the value provided
     */
    @Override
    public List<OnlineArticleNode> getOnlineArticlesByTitle(String onlineArticleTitle){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OnlineArticleNode.TITLE_PROPERTY, onlineArticleTitle).iterator();        
        List<OnlineArticleNode> list = new LinkedList<OnlineArticleNode>();        
        while(iterator.hasNext()){
            list.add(new OnlineArticleNode(iterator.next()));
        }        
        return list; 
    }
    //-------------------------------------------------------------------
    //--------------------ONLINE JOURNAL--------------------------------
    /**
     * 
     * @param onlineJournalName
     * @return OnlineJournalNode with the name provided
     */
    @Override
    public OnlineJournalNode getOnlineJournalByName(String onlineJournalName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(OnlineJournalNode.NAME_PROPERTY, onlineJournalName).iterator();        
        if(iterator.hasNext()){
            return new OnlineJournalNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------ARTICLES--------------------------------
    /**
     * 
     * @param articleTitle
     * @return ArticleNode list with the title matching the value provided
     */
    @Override
    public List<ArticleNode> getArticlesByTitle(String articleTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.TITLE_PROPERTY, articleTitle).iterator();        
        List<ArticleNode> list = new LinkedList<ArticleNode>();        
        while(iterator.hasNext()){
            list.add(new ArticleNode(iterator.next()));
        }        
        return list; 
    }
    /**
     * 
     * @param articleMedlineId
     * @return ArticleNode with the medline id provided
     */
    @Override
    public ArticleNode getArticleByMedlineId(String articleMedlineId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.MEDLINE_ID_PROPERTY, articleMedlineId).iterator();        
        if(iterator.hasNext()){
            return new ArticleNode(iterator.next());
        }else{
            return null;
        } 
    }
    /**
     * 
     * @param articleDoiId
     * @return ArticleNode with the DOI id provided
     */
    @Override
    public ArticleNode getArticleByDoiId(String articleDoiId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.DOI_ID_PROPERTY, articleDoiId).iterator();        
        if(iterator.hasNext()){
            return new ArticleNode(iterator.next());
        }else{
            return null;
        } 
    }
     /**
     * 
     * @param articlePubmedId
     * @return ArticleNode with the Pubmed id provided
     */
    @Override
    public ArticleNode getArticleByPubmedId(String articlePubmedId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ArticleNode.PUBMED_ID_PROPERTY, articlePubmedId).iterator();        
        if(iterator.hasNext()){
            return new ArticleNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------JOURNALS--------------------------------
    /**
     * 
     * @param journalName
     * @return JournalNode with the name provided
     */
    @Override
    public JournalNode getJournalByName(String journalName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(JournalNode.NAME_PROPERTY, journalName).iterator();        
        if(iterator.hasNext()){
            return new JournalNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------REACTOME--------------------------------
    /**
     * 
     * @param reactomeTermId
     * @return ReactomeTermNode with the id provided
     */
    @Override
    public ReactomeTermNode getReactomeTermById(String reactomeTermId){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ReactomeTermNode.ID_PROPERTY, reactomeTermId).iterator();
        if(iterator.hasNext()){
            return new ReactomeTermNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------FEATURE TYPE--------------------------------
    /**
     * 
     * @param featureTypeName
     * @return 
     */
    @Override
    public FeatureTypeNode getFeatureTypeByName(String featureTypeName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(FeatureTypeNode.NAME_PROPERTY, featureTypeName).iterator();
        if(iterator.hasNext()){
            return new FeatureTypeNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------COMMENT TYPE--------------------------------
    /**
     * 
     * @param commentTypeName
     * @return 
     */
    @Override
    public CommentTypeNode getCommentTypeByName(String commentTypeName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(CommentTypeNode.NAME_PROPERTY, commentTypeName).iterator();
        if(iterator.hasNext()){
            return new CommentTypeNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------SUBCELLULAR LOCATION--------------------------------
    /**
     * 
     * @param subcellularLocationName
     * @return 
     */
    @Override
    public SubcellularLocationNode getSubcellularLocationByName(String subcellularLocationName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SubcellularLocationNode.NAME_PROPERTY, subcellularLocationName).iterator();
        if(iterator.hasNext()){
            return new SubcellularLocationNode(iterator.next());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------ALTERNATIVE PRODUCTS--------------------------------
    
    @Override
    public AlternativeProductNode getAlternativeProductInitiationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public AlternativeProductNode getAlternativeProductPromoterNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public AlternativeProductNode getAlternativeProductRibosomalFrameshiftingNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public AlternativeProductNode getAlternativeProductSplicingNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(AlternativeProductNode.NAME_PROPERTY, AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new AlternativeProductNode(iterator.next());
        }else{
            return null;
        } 
    }
    
    //-------------------------------------------------------------------
    //--------------------SEQ-CAUTION--------------------------------
    @Override
    public SequenceCautionNode getSequenceCautionErroneousGeneModelPredictionNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCautionNode getSequenceCautionErroneousInitiationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCautionNode getSequenceCautionErroneousTranslationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCautionNode getSequenceCautionErroneousTerminationNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCautionNode getSequenceCautionFrameshiftNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    @Override
    public SequenceCautionNode getSequenceCautionMiscellaneousDiscrepancyNode(){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SequenceCautionNode.NAME_PROPERTY, ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE).iterator();
        if(iterator.hasNext()){
            return new SequenceCautionNode(iterator.next());
        }else{
            return null;
        } 
    }
    //-------------------------------------------------------------------
    //--------------------SUBMISSION--------------------------------
    /**
     * 
     * @param submissionTitle
     * @return 
     */
    @Override
    public SubmissionNode getSubmissionByTitle(String submissionTitle){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(SubmissionNode.TITLE_PROPERTY, submissionTitle).iterator();        
        SubmissionNode submissionNode = null;        
        if(iterator.hasNext()){
            submissionNode = new SubmissionNode(iterator.next());
        }        
        return submissionNode; 
    }
    //-------------------------------------------------------------------
    //--------------------DB--------------------------------
    /**
     * 
     */
    @Override
    public DBNode getDBByName(String dbName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DBNode.NAME_PROPERTY, dbName).iterator();        
        DBNode dbNode = null;        
        if(iterator.hasNext()){
            dbNode = new DBNode(iterator.next());
        }        
        return dbNode; 
    }
    
    
    
    private List<ProteinNode> getProteinReference(String referenceName, String id){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(referenceName, id).iterator();        
        List<ProteinNode> list = new LinkedList<ProteinNode>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list; 
    }
    
}
