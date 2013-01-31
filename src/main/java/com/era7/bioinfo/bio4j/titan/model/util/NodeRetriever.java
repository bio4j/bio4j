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
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.tinkerpop.blueprints.Vertex;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class NodeRetriever {

    protected Bio4jManager manager;

    public NodeRetriever(Bio4jManager bio4jManager) {
        manager = bio4jManager;
    }

    //-------------------------------------------------------------------
    //--------------------------ENZYME-----------------------------------
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
    public DatasetNode getDatasetByName(String name) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, name).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

    public DatasetNode getSwissProtDataset() {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(DatasetNode.NAME_PROPERTY, DatasetNode.SWISS_PROT_DATASET_NAME).iterator();
        if (iterator.hasNext()) {
            return new DatasetNode(iterator.next());
        } else {
            return null;
        }
    }

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
    public GoTermNode getGoTermById(String goId) {
        Iterator<Vertex> iterator = manager.getGraph().getVertices(GoTermNode.ID_PROPERTY, goId).iterator();
        if (iterator.hasNext()) {
            return new GoTermNode(iterator.next());
        } else {
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
    public ProteinNode getProteinNodeByAccession(String proteinAccession) {
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
    public ProteinNode getProteinNodeByEnsemblPlantsRef(String ensemblPlantsRef){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.ENSEMBL_PLANTS_REFERENCES_PROPERTY, ensemblPlantsRef).iterator();        
        if(iterator.hasNext()){
            return new ProteinNode(iterator.next());
        }else{
            return null;
        }        
    }    
    /**
     * 
     * @param proteinFullName
     * @return List of proteins (if any) which match the full name provided
     */
    public List<ProteinNode> getProteinsByFullName(String proteinFullName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.FULL_NAME_PROPERTY, proteinFullName).iterator();        
        List<ProteinNode> list = new LinkedList<ProteinNode>();        
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
    public List<ProteinNode> getProteinsByGeneNames(String proteinGeneName){        
        Iterator<Vertex> iterator = manager.getGraph().getVertices(ProteinNode.GENE_NAMES_PROPERTY, proteinGeneName).iterator();        
        List<ProteinNode> list = new LinkedList<ProteinNode>();        
        while(iterator.hasNext()){
            list.add(new ProteinNode(iterator.next()));
        }        
        return list;        
    }
    
    //-------------------------------------------------------------------
    //--------------------KEYWORDS--------------------------------    
    /**
     * 
     * @param keywordId 
     * @return KeywordNode with the id provided
     */
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
    public TaxonNode getTaxonByName(String taxonName){
        Iterator<Vertex> iterator = manager.getGraph().getVertices(TaxonNode.NAME_PROPERTY, taxonName).iterator();        
        if(iterator.hasNext()){
            return new TaxonNode(iterator.next());
        }else{
            return null;
        }   
    }
}
