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

import com.era7.bioinfo.bio4j.blueprints.model.nodes.citation.*;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.era7.bioinfo.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.article.ArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.book.BookProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.onarticle.OnlineArticleProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.patent.PatentProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.submission.SubmissionProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.thesis.ThesisProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.citation.uo.UnpublishedObservationProteinCitationRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.DomainCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.FunctionCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.PathwayCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.comment.SimilarityCommentRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.ActiveSiteFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.SignalPeptideFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.SpliceVariantFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.features.TransmembraneRegionFeatureRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.protein.*;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef100MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef50MemberRel;
import com.era7.bioinfo.bio4j.blueprints.model.relationships.uniref.UniRef90MemberRel;
import com.era7.bioinfo.bio4j.model.enums.UniprotDBXref;
import com.era7.bioinfo.bio4j.model.nodes.Enzyme;
import com.era7.bioinfo.bio4j.model.nodes.GoTerm;
import com.era7.bioinfo.bio4j.model.nodes.Interpro;
import com.era7.bioinfo.bio4j.model.nodes.Keyword;
import com.era7.bioinfo.bio4j.model.nodes.Pfam;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.SubcellularLocation;
import com.era7.bioinfo.bio4j.model.nodes.citation.Article;
import com.era7.bioinfo.bio4j.model.nodes.citation.Book;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineArticle;
import com.era7.bioinfo.bio4j.model.nodes.citation.Patent;
import com.era7.bioinfo.bio4j.model.nodes.citation.Submission;
import com.era7.bioinfo.bio4j.model.nodes.citation.Thesis;
import com.era7.bioinfo.bio4j.model.nodes.citation.UnpublishedObservation;
import com.era7.bioinfo.bio4j.model.nodes.reactome.ReactomeTerm;
import com.era7.bioinfo.bio4j.model.nodes.refseq.GenomeElement;
import com.era7.bioinfo.bio4j.model.relationships.comment.DomainComment;
import com.era7.bioinfo.bio4j.model.relationships.comment.FunctionComment;
import com.era7.bioinfo.bio4j.model.relationships.comment.PathwayComment;
import com.era7.bioinfo.bio4j.model.relationships.comment.SimilarityComment;
import com.era7.bioinfo.bio4j.model.relationships.features.ActiveSiteFeature;
import com.era7.bioinfo.bio4j.model.relationships.features.SignalPeptideFeature;
import com.era7.bioinfo.bio4j.model.relationships.features.SpliceVariantFeature;
import com.era7.bioinfo.bio4j.model.relationships.features.TransmembraneRegionFeature;
import com.era7.bioinfo.bio4j.model.relationships.protein.ProteinIsoformInteraction;
import com.era7.bioinfo.bio4j.model.relationships.protein.ProteinProteinInteraction;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Uniprot proteins
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class ProteinNode extends BasicVertex implements Protein{

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
    public static final String ALTERNATIVE_ACCESSIONS_PROPERTY = "protein_alternative_accessions";
       

    public ProteinNode(Vertex v) {
        super(v);
    }

    @Override
    public String getName() {
        return String.valueOf(vertex.getProperty(NAME_PROPERTY));
    }

    @Override
    public String getFullName() {
        return String.valueOf(vertex.getProperty(FULL_NAME_PROPERTY));
    }

    @Override
    public String getShortName() {
        return String.valueOf(vertex.getProperty(SHORT_NAME_PROPERTY));
    }

    @Override
    public String getAccession() {
        return String.valueOf(vertex.getProperty(ACCESSION_PROPERTY));
    }

    @Override
    public String getSequence() {
        return String.valueOf(vertex.getProperty(SEQUENCE_PROPERTY));
    }    

    @Override
    public String getModifiedDate() {
        return String.valueOf(vertex.getProperty(MODIFIED_DATE_PROPERTY));
    }

    @Override
    public float getMass() {
        return Float.parseFloat(String.valueOf(vertex.getProperty(MASS_PROPERTY)));
    }

    @Override
    public int getLength() {
        return Integer.parseInt(String.valueOf(vertex.getProperty(LENGTH_PROPERTY)));
    }

    @Override
    public String[] getGeneNames() {
        return (String[]) vertex.getProperty(GENE_NAMES_PROPERTY);
    }
    
    @Override
    public String[] getAlternativeAcessions() {
        return (String[]) vertex.getProperty(ALTERNATIVE_ACCESSIONS_PROPERTY);
    }
    
    @Override
    public String[] getReference(UniprotDBXref ref){
        return (String[]) vertex.getProperty(ref.getProteinReferencePropertyName());
    }
    
    
    @Override
    public boolean isUniref50Representant() {
        return !vertex.getEdges(Direction.IN, UniRef50MemberRel.NAME).iterator().hasNext();
    }

    @Override
    public boolean isUniref90Representant() {
        return !vertex.getEdges(Direction.IN, UniRef90MemberRel.NAME).iterator().hasNext();
    }

    @Override
    public boolean isUniref100Representant() {
        return !vertex.getEdges(Direction.IN, UniRef100MemberRel.NAME).iterator().hasNext();
    }

    @Override
    public List<Protein> getUniref50ClusterThisProteinBelongsTo() {
        List<Protein> list = new LinkedList<>();
        if (isUniref50Representant()) {
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef50MemberRel.NAME).iterator();
            while (relIterator.hasNext()) {
                list.add(new ProteinNode(relIterator.next()));
            }
        } else {
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef50MemberRel.NAME).iterator().next());
            return representant.getUniref50ClusterThisProteinBelongsTo();
        }
        return list;
    }

    @Override
    public List<Protein> getUniref90ClusterThisProteinBelongsTo() {
        List<Protein> list = new LinkedList<>();
        if (isUniref90Representant()) {
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef90MemberRel.NAME).iterator();
            while (relIterator.hasNext()) {
                list.add(new ProteinNode(relIterator.next()));
            }
        } else {
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef90MemberRel.NAME).iterator().next());
            return representant.getUniref90ClusterThisProteinBelongsTo();
        }
        return list;
    }

    @Override
    public List<Protein> getUniref100ClusterThisProteinBelongsTo() {
        List<Protein> list = new LinkedList<>();
        if (isUniref100Representant()) {
            list.add(this);
            Iterator<Vertex> relIterator = vertex.getVertices(Direction.OUT, UniRef100MemberRel.NAME).iterator();
            while (relIterator.hasNext()) {
                list.add(new ProteinNode(relIterator.next()));
            }
        } else {
            ProteinNode representant = new ProteinNode(vertex.getVertices(Direction.IN, UniRef100MemberRel.NAME).iterator().next());
            return representant.getUniref100ClusterThisProteinBelongsTo();
        }
        return list;
    }

    @Override
    public OrganismNode getOrganism() {
        OrganismNode org = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinOrganismRel.NAME).iterator();
        if (iterator.hasNext()) {
            org = new OrganismNode(iterator.next());
        }
        return org;
    }

    @Override
    public DatasetNode getDataset() {
        DatasetNode dataset = null;
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinDatasetRel.NAME).iterator();
        if (iterator.hasNext()) {
            dataset = new DatasetNode(iterator.next());
        }
        return dataset;
    }

    @Override
    public List<GenomeElement> getGenomeElements() {
        List<GenomeElement> list = new LinkedList<>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGenomeElementRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new GenomeElementNode(iterator.next()));
        }
        return list;
    }

    @Override
    public List<SubcellularLocation> getSubcellularLocations() {
        List<SubcellularLocation> list = new LinkedList<>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinSubcellularLocationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new SubcellularLocationNode(iterator.next()));
        }
        return list;
    }

    @Override
    public List<Interpro> getInterpro() {
        List<Interpro> interpros = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinInterproRel.NAME).iterator();
        while (iterator.hasNext()) {
            InterproNode interpro = new InterproNode(iterator.next());
            interpros.add(interpro);
        }
        return interpros;
    }

    @Override
    public List<Pfam> getPfamTerms() {
        List<Pfam> pfamTerms = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinPfamRel.NAME).iterator();
        while (iterator.hasNext()) {
            PfamNode interpro = new PfamNode(iterator.next());
            pfamTerms.add(interpro);
        }
        return pfamTerms;
    }

    @Override
    public List<ReactomeTerm> getReactomeTerms() {
        List<ReactomeTerm> list = new LinkedList<>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinReactomeRel.NAME).iterator();
        while (iterator.hasNext()) {
            ReactomeTermNode reactomeTerm = new ReactomeTermNode(iterator.next());
            list.add(reactomeTerm);
        }
        return list;
    }

    @Override
    public List<Enzyme> getProteinEnzymaticActivity() {
        List<Enzyme> list = new LinkedList<>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinEnzymaticActivityRel.NAME).iterator();
        while (iterator.hasNext()) {
            EnzymeNode enzyme = new EnzymeNode(iterator.next());
            list.add(enzyme);
        }
        return list;
    }

    @Override
    public List<GoTerm> getGOAnnotations() {
        List<GoTerm> list = new LinkedList<>();
        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinGoRel.NAME).iterator();
        while (iterator.hasNext()) {
            GoTermNode goTerm = new GoTermNode(iterator.next());
            list.add(goTerm);
        }
        return list;
    }

    @Override
    public List<Keyword> getKeywords() {
        List<Keyword> keywords = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.OUT, ProteinKeywordRel.NAME).iterator();
        while (iterator.hasNext()) {
            KeywordNode keyword = new KeywordNode(iterator.next());
            keywords.add(keyword);
        }
        return keywords;
    }

    @Override
    public List<SignalPeptideFeature> getSignalPeptideFeature() {
        List<SignalPeptideFeature> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SignalPeptideFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            SignalPeptideFeatureRel rel = new SignalPeptideFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<SpliceVariantFeature> getSpliceVariantFeature() {
        List<SpliceVariantFeature> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SpliceVariantFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            SpliceVariantFeatureRel rel = new SpliceVariantFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<TransmembraneRegionFeature> getTransmembraneRegionFeature() {
        List<TransmembraneRegionFeature> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, TransmembraneRegionFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            TransmembraneRegionFeatureRel rel = new TransmembraneRegionFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<ActiveSiteFeature> getActiveSiteFeature() {
        List<ActiveSiteFeature> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ActiveSiteFeatureRel.NAME).iterator();
        while (iterator.hasNext()) {
            ActiveSiteFeatureRel rel = new ActiveSiteFeatureRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<FunctionComment> getFunctionComment() {
        List<FunctionComment> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, FunctionCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            FunctionCommentRel rel = new FunctionCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<PathwayComment> getPathwayComment() {
        List<PathwayComment> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, PathwayCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            PathwayCommentRel rel = new PathwayCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<DomainComment> getDomainComment() {
        List<DomainComment> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, DomainCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            DomainCommentRel rel = new DomainCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    @Override
    public List<SimilarityComment> getSimilarityComment() {
        List<SimilarityComment> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, SimilarityCommentRel.NAME).iterator();
        while (iterator.hasNext()) {
            SimilarityCommentRel rel = new SimilarityCommentRel(iterator.next());
            list.add(rel);
        }
        return list;
    }

    /**
     * Protein-protein outgoing interactions
     *
     * @return
     */
    @Override
    public List<ProteinProteinInteraction> getProteinOutgoingInteractions() {
        List<ProteinProteinInteraction> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ProteinProteinInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinProteinInteractionRel(iterator.next()));
        }

        return list;
    }

    /**
     * Protein-protein incoming interactions
     *
     * @return
     */
    @Override
    public List<ProteinProteinInteraction> getProteinIncomingInteractions() {
        List<ProteinProteinInteraction> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.IN, ProteinProteinInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinProteinInteractionRel(iterator.next()));
        }

        return list;
    }

    /**
     * Protein-Isoform outgoing interactions
     *
     * @return
     */
    @Override
    public List<ProteinIsoformInteraction> getIsoformOutgoingInteractions() {
        List<ProteinIsoformInteraction> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.OUT, ProteinIsoformInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinIsoformInteractionRel(iterator.next()));
        }

        return list;
    }

    /**
     * Protein-Isoform incoming interactions
     *
     * @return
     */
    @Override
    public List<ProteinIsoformInteraction> getIsoformIncomingInteractions() {
        List<ProteinIsoformInteraction> list = new LinkedList<>();

        Iterator<Edge> iterator = vertex.getEdges(Direction.IN, ProteinIsoformInteractionRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ProteinIsoformInteractionRel(iterator.next()));
        }

        return list;
    }

    //---------------------------------------------------------------------------------------------
    //-------------------------------------CITATIONS-----------------------------------------------
    //---------------------------------------------------------------------------------------------
    /**
     * Protein article citations
     *
     * @return
     */
    @Override
    public List<Article> getArticleCitations() {
        List<Article> list = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ArticleProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ArticleNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein submission citations
     *
     * @return
     */
    @Override
    public List<Submission> getSubmissionCitations() {
        List<Submission> list = new ArrayList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, SubmissionProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new SubmissionNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein Online article citations
     *
     * @return
     */
    @Override
    public List<OnlineArticle> getOnlineArticleCitations() {
        List<OnlineArticle> list = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, OnlineArticleProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new OnlineArticleNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein Book citations
     *
     * @return
     */
    @Override
    public List<Book> getBookCitations() {
        List<Book> list = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, BookProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new BookNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein patent citations
     *
     * @return
     */
    @Override
    public List<Patent> getPatentCitations() {
        List<Patent> list = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, PatentProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new PatentNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein thesis citations
     *
     * @return
     */
    @Override
    public List<Thesis> getThesisCitations() {
        List<Thesis> list = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, ThesisProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new ThesisNode(iterator.next()));
        }
        return list;
    }

    /**
     * Protein unpublished observations citations
     *
     * @return
     */
    @Override
    public List<UnpublishedObservation> getUnpublishedObservationsCitations() {
        List<UnpublishedObservation> list = new LinkedList<>();

        Iterator<Vertex> iterator = vertex.getVertices(Direction.IN, UnpublishedObservationProteinCitationRel.NAME).iterator();
        while (iterator.hasNext()) {
            list.add(new UnpublishedObservationNode(iterator.next()));
        }
        return list;
    }
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------

    @Override
    public void setName(String value) {
        vertex.setProperty(NAME_PROPERTY, value);
    }

    @Override
    public void setFullName(String value) {
        vertex.setProperty(FULL_NAME_PROPERTY, value);
    }

    @Override
    public void setShortName(String value) {
        vertex.setProperty(SHORT_NAME_PROPERTY, value);
    }

    @Override
    public void setAccession(String value) {
        vertex.setProperty(ACCESSION_PROPERTY, value);
    }

    @Override
    public void setSequence(String value) {
        vertex.setProperty(SEQUENCE_PROPERTY, value);
    }

    @Override
    public void setModifiedDate(String value) {
        vertex.setProperty(MODIFIED_DATE_PROPERTY, value);
    }

    @Override
    public void setMass(float value) {
        vertex.setProperty(MASS_PROPERTY, value);
    }

    @Override
    public void setLength(int value) {
        vertex.setProperty(LENGTH_PROPERTY, value);
    }

    @Override
    public void setGeneNames(String[] value) {
        vertex.setProperty(GENE_NAMES_PROPERTY, value);
    }
    
    @Override
    public void setAlternativeAccessions(String[] value) {
        vertex.setProperty(ALTERNATIVE_ACCESSIONS_PROPERTY, value);
    }
    
    
    @Override
    public void setReference(UniprotDBXref ref, String[] value) {
        vertex.setProperty(ref.getProteinReferencePropertyName(), value);
    }

}
