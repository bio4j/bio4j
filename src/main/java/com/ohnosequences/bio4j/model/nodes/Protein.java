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
package com.ohnosequences.bio4j.model.nodes;

import com.ohnosequences.bio4j.model.enums.UniprotDBXref;
import com.ohnosequences.bio4j.model.nodes.citation.Article;
import com.ohnosequences.bio4j.model.nodes.citation.Book;
import com.ohnosequences.bio4j.model.nodes.citation.OnlineArticle;
import com.ohnosequences.bio4j.model.nodes.citation.Patent;
import com.ohnosequences.bio4j.model.nodes.citation.Submission;
import com.ohnosequences.bio4j.model.nodes.citation.Thesis;
import com.ohnosequences.bio4j.model.nodes.citation.UnpublishedObservation;
import com.ohnosequences.bio4j.model.nodes.reactome.ReactomeTerm;
import com.ohnosequences.bio4j.model.nodes.refseq.GenomeElement;
import com.ohnosequences.bio4j.model.relationships.comment.DomainComment;
import com.ohnosequences.bio4j.model.relationships.comment.FunctionComment;
import com.ohnosequences.bio4j.model.relationships.comment.PathwayComment;
import com.ohnosequences.bio4j.model.relationships.comment.SimilarityComment;
import com.ohnosequences.bio4j.model.relationships.features.ActiveSiteFeature;
import com.ohnosequences.bio4j.model.relationships.features.SignalPeptideFeature;
import com.ohnosequences.bio4j.model.relationships.features.SpliceVariantFeature;
import com.ohnosequences.bio4j.model.relationships.features.TransmembraneRegionFeature;
import com.ohnosequences.bio4j.model.relationships.protein.ProteinIsoformInteraction;
import com.ohnosequences.bio4j.model.relationships.protein.ProteinProteinInteraction;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Protein extends BasicNode{
 
    //---------------------------------------------------------------------------------------------
    //-------------------------------------GETTERS-------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public String getName();
    public String getFullName();
    public String getShortName();
    public String getAccession();
    public String getSequence();
    public String getModifiedDate();
    public float getMass();
    public int getLength();
    public String[] getGeneNames();    
    public String[] getAlternativeAcessions();
    
    public String[] getReference(UniprotDBXref ref);   
   
    public boolean isUniref50Representant();
    public boolean isUniref90Representant();
    public boolean isUniref100Representant();
    public List<Protein> getUniref50ClusterThisProteinBelongsTo();
    public List<Protein> getUniref90ClusterThisProteinBelongsTo();
    public List<Protein> getUniref100ClusterThisProteinBelongsTo();
    public Organism getOrganism();
    public Dataset getDataset();
    public List<GenomeElement> getGenomeElements();
    public List<SubcellularLocation> getSubcellularLocations();
    public List<Interpro> getInterpro();
    public List<Pfam> getPfamTerms();
    public List<ReactomeTerm> getReactomeTerms();
    public List<Enzyme> getProteinEnzymaticActivity();
    public List<GoTerm> getGOAnnotations();
    public List<Keyword> getKeywords();
    public List<SignalPeptideFeature> getSignalPeptideFeature();
    public List<SpliceVariantFeature> getSpliceVariantFeature();
    public List<TransmembraneRegionFeature> getTransmembraneRegionFeature();
    public List<ActiveSiteFeature> getActiveSiteFeature();
    public List<FunctionComment> getFunctionComment();
    public List<PathwayComment> getPathwayComment();
    public List<DomainComment> getDomainComment();
    public List<SimilarityComment> getSimilarityComment();
    public List<ProteinProteinInteraction> getProteinOutgoingInteractions();
    public List<ProteinProteinInteraction> getProteinIncomingInteractions();
    public List<ProteinIsoformInteraction> getIsoformOutgoingInteractions();
    public List<ProteinIsoformInteraction> getIsoformIncomingInteractions();
    public List<Article> getArticleCitations();
    public List<Submission> getSubmissionCitations();
    public List<OnlineArticle> getOnlineArticleCitations();
    public List<Book> getBookCitations();
    public List<Patent> getPatentCitations();
    public List<Thesis> getThesisCitations();
    public List<UnpublishedObservation> getUnpublishedObservationsCitations();
    
    //---------------------------------------------------------------------------------------------
    //-------------------------------------SETTERS-------------------------------------------------
    //---------------------------------------------------------------------------------------------
    public void setName(String value);
    public void setFullName(String value);
    public void setShortName(String value);
    public void setAccession(String value);
    public void setSequence(String value);
    public void setModifiedDate(String value);
    public void setMass(float value);
    public void setLength(int value);
    public void setGeneNames(String[] value);
    public void setAlternativeAccessions(String[] value);
    
    public void setReference(UniprotDBXref ref, String[] value);
    

    
}
