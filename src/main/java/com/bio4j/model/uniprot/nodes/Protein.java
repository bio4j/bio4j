package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.enums.UniprotDBXref;
// import com.bio4j.model.uniprot.nodes.Article;
// import com.bio4j.model.uniprot.nodes.Book;
// import com.bio4j.model.uniprot.nodes.OnlineArticle;
// import com.bio4j.model.uniprot.nodes.Patent;
// import com.bio4j.model.uniprot.nodes.Submission;
// import com.bio4j.model.uniprot.nodes.Thesis;
// import com.bio4j.model.uniprot.nodes.UnpublishedObservation;

import com.bio4j.model.uniprot.nodes.ReactomeTerm;
import com.bio4j.model.uniprot.relationships.DomainComment;
import com.bio4j.model.uniprot.relationships.FunctionComment;
import com.bio4j.model.uniprot.relationships.PathwayComment;
import com.bio4j.model.uniprot.relationships.SimilarityComment;
import com.bio4j.model.uniprot.relationships.ActiveSiteFeature;
import com.bio4j.model.uniprot.relationships.SignalPeptideFeature;
import com.bio4j.model.uniprot.relationships.SpliceVariantFeature;
import com.bio4j.model.uniprot.relationships.TransmembraneRegionFeature;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.properties.FullName;
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Sequence;
import com.bio4j.model.properties.ShortName;
import com.bio4j.model.proteinInteractions.relationships.ProteinIsoformInteraction;
import com.bio4j.model.proteinInteractions.relationships.ProteinProteinInteraction;

import java.util.List;

public interface Protein extends Node<Protein, Protein.Type>,

	Name,
	Sequence,
	FullName,
	ShortName
	{
  
	  public static Type TYPE = Type.protein; 
	  public static enum Type implements NodeType<Protein, Protein.Type> {
	
	    protein;
	    public Type value() { return protein; }
	  }
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
