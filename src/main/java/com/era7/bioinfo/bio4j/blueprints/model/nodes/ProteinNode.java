/*
 * Copyright (C) 2010-2011  "Bio4j"
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
package com.era7.bioinfo.bio4j.blueprints.model.nodes;

import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.SignalPeptideFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef90MemberRel;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Uniprot proteins
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinNode extends BasicNode {

    public static final String NODE_TYPE = ProteinNode.class.getCanonicalName();
    
    public static final String NAME_PROPERTY = "protein_name";
    public static final String FULL_NAME_PROPERTY = "protein_full_name";
    public static final String SHORT_NAME_PROPERTY = "protein_short_name";
    public static final String ACCESSION_PROPERTY = "protein_accession";
    public static final String SEQUENCE_PROPERTY = "protein_sequence";
    public static final String MASS_PROPERTY = "protein_mass";
    public static final String LENGTH_PROPERTY = "protein_length";
    public static final String MODIFIED_DATE_PROPERTY = "protein_modified_date";
    public static final String GENE_NAMES_PROPERTY = "protein_gene_names";
    public static final String ENSEMBL_ID_PROPERTY = "protein_ensembl_id";
    public static final String PIR_ID_PROPERTY = "protein_pir_id";
    public static final String KEGG_ID_PROPERTY = "protein_kegg_id";
    public static final String EMBL_REFERENCES_PROPERTY = "protein_embl_references";
    public static final String REFSEQ_REFERENCES_PROPERTY = "protein_refseq_references";
    public static final String ARRAY_EXPRESS_ID_PROPERTY = "protein_array_express_id";
    public static final String UNIGENE_ID_PROPERTY = "protein_unigene_id";
    public static final String ALTERNATIVE_ACCESSIONS_PROPERTY = "protein_alternative_accessions";
    public static final String ENSEMBL_PLANTS_REFERENCES_PROPERTY = "protein_ensembl_plants_references";

    //public static final String GENE_NAMES_SEPARATOR = "\t";

    public ProteinNode(Vertex v) {
        super(v);
    }

    public String getName() {
        return String.valueOf(vertex.getProperty(NAME_PROPERTY));
    }

    public String getFullName(){
        return String.valueOf(vertex.getProperty(FULL_NAME_PROPERTY));
    }

    public String getShortName(){
        return String.valueOf(vertex.getProperty(SHORT_NAME_PROPERTY));
    }

    public String getAccession() {
        return String.valueOf(vertex.getProperty(ACCESSION_PROPERTY));
    }

    public String getSequence() {
        return String.valueOf(vertex.getProperty(SEQUENCE_PROPERTY));
    }

    public String getEnsemblId(){
        return String.valueOf(vertex.getProperty(ENSEMBL_ID_PROPERTY));
    }

    public String[] getEMBLreferences(){
        return (String[]) vertex.getProperty(EMBL_REFERENCES_PROPERTY);
    }
    
    public String[] getEnsemblPlantsReferences(){
        return (String[]) vertex.getProperty(ENSEMBL_PLANTS_REFERENCES_PROPERTY);
    }
    
    public String[] getRefseqReferences(){
        return (String[]) vertex.getProperty(REFSEQ_REFERENCES_PROPERTY);
    }

    public String[] getAlternativeAcessions(){
        return (String[]) vertex.getProperty(ALTERNATIVE_ACCESSIONS_PROPERTY);
    }

    public String getPIRId(){
        return String.valueOf(vertex.getProperty(PIR_ID_PROPERTY));
    }

    public String getKeggId(){
        return String.valueOf(vertex.getProperty(KEGG_ID_PROPERTY));
    }

    public String getArrayExpressId(){
        return String.valueOf(vertex.getProperty(ARRAY_EXPRESS_ID_PROPERTY));
    }

    public String getUniGeneId(){
        return String.valueOf(vertex.getProperty(UNIGENE_ID_PROPERTY));
    }

    public String getModifiedDate(){
        return String.valueOf(vertex.getProperty(MODIFIED_DATE_PROPERTY));
    }

    public float getMass() {
        return Float.parseFloat(String.valueOf(vertex.getProperty(MASS_PROPERTY)));
    }

    public int getLength() {
        return Integer.parseInt(String.valueOf(vertex.getProperty(LENGTH_PROPERTY)));
    }

    public String[] getGeneNames(){
        return (String[])vertex.getProperty(GENE_NAMES_PROPERTY);
    }

//    public String[] getGeneNamesArray(){
//        return String.valueOf(node.getProperty(GENE_NAMES_PROPERTY)).split(GENE_NAMES_SEPARATOR);
//    }

    
    public boolean isUniref50Representant(){
        return !vertex.getEdges(Direction.IN, UniRef50MemberRel.NAME).iterator().hasNext();
    }
    public boolean isUniref90Representant(){
        return !vertex.getEdges(Direction.IN, UniRef90MemberRel.NAME).iterator().hasNext();
    }
    public boolean isUniref100Representant(){
        return !vertex.getEdges(Direction.IN, UniRef100MemberRel.NAME).iterator().hasNext();
    }
    
    public List<ProteinNode> getUniref50ClusterThisProteinBelongsTo(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        if(isUniref50Representant()){
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef50MemberRel.NAME).iterator();
            while(relIterator.hasNext()){
                list.add(new ProteinNode(relIterator.next()));
            }
        }else{
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef50MemberRel.NAME).iterator().next());
            return representant.getUniref50ClusterThisProteinBelongsTo();
        }
        return list;
    }
    
    public List<ProteinNode> getUniref90ClusterThisProteinBelongsTo(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        if(isUniref90Representant()){
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef90MemberRel.NAME).iterator();
            while(relIterator.hasNext()){
                list.add(new ProteinNode(relIterator.next()));
            }
        }else{
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef90MemberRel.NAME).iterator().next());
            return representant.getUniref90ClusterThisProteinBelongsTo();
        }
        return list;
    }
    
    public List<ProteinNode> getUniref100ClusterThisProteinBelongsTo(){
        List<ProteinNode> list = new LinkedList<ProteinNode>();
        if(isUniref100Representant()){
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef100MemberRel.NAME).iterator();
            while(relIterator.hasNext()){
                list.add(new ProteinNode(relIterator.next()));
            }
        }else{
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef100MemberRel.NAME).iterator().next());
            return representant.getUniref100ClusterThisProteinBelongsTo();
        }
        return list;
    }
    
    public OrganismNode getOrganism() {
        OrganismNode org = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinOrganismRel.NAME).iterator();
        if(iterator.hasNext()){
            org = new OrganismNode(iterator.next());
        }
        return org;
    }
    

    public DatasetNode getDataset(){
        DatasetNode dataset = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinDatasetRel.NAME).iterator();
        if(iterator.hasNext()){
            dataset = new DatasetNode(iterator.next());
        }
        return dataset;
    }
    
    public List<GenomeElementNode> getGenomeElements(){
        List<GenomeElementNode> list = new ArrayList<GenomeElementNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGenomeElementRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new GenomeElementNode(iterator.next()));
        }
        return list;
    }

    
    public List<SubcellularLocationNode> getSubcellularLocations(){
        List<SubcellularLocationNode> list = new ArrayList<SubcellularLocationNode>();        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinSubcellularLocationRel.NAME).iterator();
        while(iterator.hasNext()){
            list.add(new SubcellularLocationNode(iterator.next()));
        }
        return list;
    }
    
    public List<InterproNode> getInterpro(){
        List<InterproNode> interpros = new ArrayList<InterproNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinInterproRel.NAME).iterator();
        while(iterator.hasNext()){
            InterproNode interpro = new InterproNode(iterator.next());
            interpros.add(interpro);                        
        }
        return interpros;  
    }
    
    public List<PfamNode> getPfamTerms(){
        List<PfamNode> pfamTerms = new ArrayList<PfamNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinPfamRel.NAME).iterator();
        while(iterator.hasNext()){
            PfamNode interpro = new PfamNode(iterator.next());
            pfamTerms.add(interpro);                        
        }
        return pfamTerms;  
    }
    
    public List<ReactomeTermNode> getReactomeTerms(){
        List<ReactomeTermNode> list = new LinkedList<ReactomeTermNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinReactomeRel.NAME).iterator();
        while(iterator.hasNext()){
            ReactomeTermNode reactomeTerm = new ReactomeTermNode(iterator.next());
            list.add(reactomeTerm);
        }
        return list;
    }
    
    public List<EnzymeNode> getProteinEnzymaticActivity(){
        List<EnzymeNode> list = new LinkedList<EnzymeNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinEnzymaticActivityRel.NAME).iterator();
        while(iterator.hasNext()){
            EnzymeNode enzyme = new EnzymeNode(iterator.next());
            list.add(enzyme);
        }
        return list;        
    }
    
    public List<GoTermNode> getGOAnnotations(){
        List<GoTermNode> list = new ArrayList<GoTermNode>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGoRel.NAME).iterator();
        while(iterator.hasNext()){
            GoTermNode goTerm = new GoTermNode(iterator.next());
            list.add(goTerm);                        
        }        
        return list;
    }
    
    public List<KeywordNode> getKeywords(){
        List<KeywordNode> keywords = new ArrayList<KeywordNode>();
        
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinKeywordRel.NAME).iterator();
        while(iterator.hasNext()){
            KeywordNode keyword = new KeywordNode(iterator.next());
            keywords.add(keyword);                        
        }
        return keywords;  
    }
    
    public List<SignalPeptideFeatureRel> getSignalPeptideFeature(){
        List<SignalPeptideFeatureRel> list = new ArrayList<SignalPeptideFeatureRel>();
        
        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SignalPeptideFeatureRel.NAME).iterator();
        while(iterator.hasNext()){
            SignalPeptideFeatureRel rel = new SignalPeptideFeatureRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    public List<SpliceVariantFeatureRel> getSpliceVariantFeature(){
        List<SpliceVariantFeatureRel> list = new ArrayList<SpliceVariantFeatureRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new SpliceVariantFeatureRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            SpliceVariantFeatureRel rel = new SpliceVariantFeatureRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    public List<TransmembraneRegionFeatureRel> getTransmembraneRegionFeature(){
        List<TransmembraneRegionFeatureRel> list = new ArrayList<TransmembraneRegionFeatureRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new TransmembraneRegionFeatureRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            TransmembraneRegionFeatureRel rel = new TransmembraneRegionFeatureRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    public List<ActiveSiteFeatureRel> getActiveSiteFeature(){
        List<ActiveSiteFeatureRel> list = new ArrayList<ActiveSiteFeatureRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ActiveSiteFeatureRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            ActiveSiteFeatureRel rel = new ActiveSiteFeatureRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    
    public List<FunctionCommentRel> getFunctionComment(){
        List<FunctionCommentRel> list = new ArrayList<FunctionCommentRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new FunctionCommentRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            FunctionCommentRel rel = new FunctionCommentRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    
    public List<PathwayCommentRel> getPathwayComment(){
        List<PathwayCommentRel> list = new ArrayList<PathwayCommentRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new PathwayCommentRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            PathwayCommentRel rel = new PathwayCommentRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    
    public List<DomainCommentRel> getDomainComment(){
        List<DomainCommentRel> list = new ArrayList<DomainCommentRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new DomainCommentRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            DomainCommentRel rel = new DomainCommentRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    
    public List<SimilarityCommentRel> getSimilarityComment(){
        List<SimilarityCommentRel> list = new ArrayList<SimilarityCommentRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new SimilarityCommentRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            SimilarityCommentRel rel = new SimilarityCommentRel(iterator.next());
            list.add(rel);                        
        }        
        return list;
    }
    
    /**
     * Protein-protein outgoing interactions
     * @return 
     */
    public List<ProteinProteinInteractionRel> getProteinOutgoingInteractions(){
        List<ProteinProteinInteractionRel> list = new ArrayList<ProteinProteinInteractionRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinProteinInteractionRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinProteinInteractionRel(iterator.next()));
        }
        
        return list;
    }
    /**
     * Protein-protein incoming interactions
     * @return 
     */
    public List<ProteinProteinInteractionRel> getProteinIncomingInteractions(){
        List<ProteinProteinInteractionRel> list = new ArrayList<ProteinProteinInteractionRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinProteinInteractionRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinProteinInteractionRel(iterator.next()));
        }
        
        return list;
    }
    
    /**
     * Protein-Isoform outgoing interactions
     * @return 
     */
    public List<ProteinIsoformInteractionRel> getIsoformOutgoingInteractions(){
        List<ProteinIsoformInteractionRel> list = new ArrayList<ProteinIsoformInteractionRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinIsoformInteractionRel(null), Direction.OUTGOING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinIsoformInteractionRel(iterator.next()));
        }
        
        return list;
    }
    
    /**
     * Protein-Isoform incoming interactions
     * @return 
     */
    public List<ProteinIsoformInteractionRel> getIsoformIncomingInteractions(){
        List<ProteinIsoformInteractionRel> list = new ArrayList<ProteinIsoformInteractionRel>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ProteinIsoformInteractionRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new ProteinIsoformInteractionRel(iterator.next()));
        }
        
        return list;
    }
    
    //---------------------------------------------------------------------------------------------
    //-------------------------------------CITATIONS-----------------------------------------------
    //---------------------------------------------------------------------------------------------
    
    /**
     * Protein article citations
     * @return 
     */
    public List<ArticleNode> getArticleCitations(){
        List<ArticleNode> list = new ArrayList<ArticleNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ArticleProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new ArticleNode(iterator.next().getStartNode()));
        }
        return list;
    }
    /**
     * Protein submission citations
     * @return 
     */
    public List<SubmissionNode> getSubmissionCitations(){
        List<SubmissionNode> list = new ArrayList<SubmissionNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new SubmissionProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new SubmissionNode(iterator.next().getStartNode()));
        }
        return list;
    }
    /**
     * Protein Online article citations
     * @return 
     */
    public List<OnlineArticleNode> getOnlineArticleCitations(){
        List<OnlineArticleNode> list = new ArrayList<OnlineArticleNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new OnlineArticleProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new OnlineArticleNode(iterator.next().getStartNode()));
        }
        return list;
    } 
    /**
     * Protein Book citations
     * @return 
     */
    public List<BookNode> getBookCitations(){
        List<BookNode> list = new ArrayList<BookNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new BookProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new BookNode(iterator.next().getStartNode()));
        }
        return list;
    }
    /**
     * Protein patent citations
     * @return 
     */
    public List<PatentNode> getPatentCitations(){
        List<PatentNode> list = new ArrayList<PatentNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new PatentProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new PatentNode(iterator.next().getStartNode()));
        }
        return list;
    }
    /**
     * Protein thesis citations
     * @return 
     */
    public List<ThesisNode> getThesisCitations(){
        List<ThesisNode> list = new ArrayList<ThesisNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new ThesisProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new ThesisNode(iterator.next().getStartNode()));
        }
        return list;
    }
    /**
     * Protein unpublished observations citations
     * @return 
     */
    public List<UnpublishedObservationNode> getUnpublishedObservationsCitations(){
        List<UnpublishedObservationNode> list = new ArrayList<UnpublishedObservationNode>();
        
        Iterator<Relationship> iterator = node.getRelationships(new UnpublishedObservationProteinCitationRel(null), Direction.INCOMING).iterator();
        while(iterator.hasNext()){
            list.add(new UnpublishedObservationNode(iterator.next().getStartNode()));
        }
        return list;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    
    public void setName(String value) {
        vertex.setProperty(NAME_PROPERTY, value);
    }

    public void setFullName(String value){
        vertex.setProperty(FULL_NAME_PROPERTY, value);
    }

    public void setShortName(String value){
        vertex.setProperty(SHORT_NAME_PROPERTY, value);
    }

    public void setAccession(String value) {
        vertex.setProperty(ACCESSION_PROPERTY, value);
    }

    public void setSequence(String value) {
        vertex.setProperty(SEQUENCE_PROPERTY, value);
    }

    public void setModifiedDate(String value){
        vertex.setProperty(MODIFIED_DATE_PROPERTY, value);
    }

    public void setEnsemblId(String value){
        vertex.setProperty(ENSEMBL_ID_PROPERTY, value);
    }

    public void setKeggId(String value){
        vertex.setProperty(KEGG_ID_PROPERTY, value);
    }

    public void setPIRId(String value){
        vertex.setProperty(PIR_ID_PROPERTY, value);
    }

    public void setEMBLreferences(String[] value){
        vertex.setProperty(EMBL_REFERENCES_PROPERTY, value);
    }
    
    public void setEnsemblPlantsReferences(String[] value){
        vertex.setProperty(ENSEMBL_PLANTS_REFERENCES_PROPERTY, value);
    }

    public void setRefseqReferences(String[] value){
        vertex.setProperty(REFSEQ_REFERENCES_PROPERTY, value);
    }
    
    public void setAlternativeAccessions(String[] value){
        vertex.setProperty(ALTERNATIVE_ACCESSIONS_PROPERTY, value);
    }

    public void setArrayExpressId(String value){
        vertex.setProperty(ARRAY_EXPRESS_ID_PROPERTY, value);
    }

    public void setUniGeneId(String value){
        vertex.setProperty(UNIGENE_ID_PROPERTY, value);
    }

    public void setMass(float value) {
        vertex.setProperty(MASS_PROPERTY, value);
    }

    public void setLength(int value) {
        vertex.setProperty(LENGTH_PROPERTY, value);
    }

    public void setGeneNames(String[] value){
        vertex.setProperty(GENE_NAMES_PROPERTY, value);
    }
    

    @Override
    public String toString() {
        
        String geneNamesSt = "";        
        for (String geneName : getGeneNames()) {
            geneNamesSt += geneName + ", ";
        }
        if(geneNamesSt.length() > 0){
            geneNamesSt = geneNamesSt.substring(0,geneNamesSt.length() - 2);
        }
        
        String emblReferencesSt = "";        
        for (String emblReference : getEMBLreferences()) {
            emblReferencesSt += emblReference + ", ";
        }
        if(emblReferencesSt.length() > 0){
            emblReferencesSt = emblReferencesSt.substring(0,emblReferencesSt.length() - 2);
        }
        
        
        
        String result = "\naccession = " + this.getAccession() +
                "\nname = " + this.getName() +
                "\nfull name = " + this.getFullName() +
                "\nshort name = " + this.getShortName() + 
                "\nmodified date = " + this.getModifiedDate() +
                "\nmass = " + this.getMass() +
                "\nlength = " + this.getLength() +
                "\ngene names = " + geneNamesSt +
                "\nEMBL references = " + emblReferencesSt +
                "\nPIR id = " + this.getPIRId() +
                "\nKegg id = " + this.getKeggId() +
                "\nArrayExpress id = " + this.getArrayExpressId() +
                "\nEnsembl id = " + this.getEnsemblId() +
                "\nUniGene id = " + this.getUniGeneId() + 
                "\nsequence = " + this.getSequence();

        return result;
    }
}
