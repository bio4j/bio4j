package com.bio4j.model.nodes;

import com.bio4j.model.Node;

import com.bio4j.model.enums.UniprotDBXref;
import com.bio4j.model.nodes.citation.Article;
import com.bio4j.model.nodes.citation.Book;
import com.bio4j.model.nodes.citation.OnlineArticle;
import com.bio4j.model.nodes.citation.Patent;
import com.bio4j.model.nodes.citation.Submission;
import com.bio4j.model.nodes.citation.Thesis;
import com.bio4j.model.nodes.citation.UnpublishedObservation;
import com.bio4j.model.nodes.reactome.ReactomeTerm;
import com.bio4j.model.nodes.refseq.GenomeElement;
import com.bio4j.model.relationships.comment.DomainComment;
import com.bio4j.model.relationships.comment.FunctionComment;
import com.bio4j.model.relationships.comment.PathwayComment;
import com.bio4j.model.relationships.comment.SimilarityComment;
import com.bio4j.model.relationships.features.ActiveSiteFeature;
import com.bio4j.model.relationships.features.SignalPeptideFeature;
import com.bio4j.model.relationships.features.SpliceVariantFeature;
import com.bio4j.model.relationships.features.TransmembraneRegionFeature;
import com.bio4j.model.relationships.protein.ProteinIsoformInteraction;
import com.bio4j.model.relationships.protein.ProteinProteinInteraction;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Protein extends Node {
 
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
