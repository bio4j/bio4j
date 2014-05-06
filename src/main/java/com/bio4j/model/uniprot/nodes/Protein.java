package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;
import com.bio4j.model.enums.UniprotDBXref;

// nodes
import com.bio4j.model.uniprot.nodes.Article;
import com.bio4j.model.uniprot.nodes.Book;
import com.bio4j.model.uniprot.nodes.OnlineArticle;
import com.bio4j.model.uniprot.nodes.Patent;
import com.bio4j.model.uniprot.nodes.Submission;
import com.bio4j.model.uniprot.nodes.Thesis;
import com.bio4j.model.uniprot.nodes.UnpublishedObservation;
import com.bio4j.model.uniprot.nodes.ReactomeTerm;
import com.bio4j.model.uniprot.nodes.references.Reference;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.refseq.nodes.GenomeElement;


// relationships
import com.bio4j.model.uniprot.relationships.SubmissionProteinCitation;
import com.bio4j.model.uniprot.relationships.ArticleProteinCitation;
import com.bio4j.model.uniprot.relationships.DomainComment;
import com.bio4j.model.uniprot.relationships.FunctionComment;
import com.bio4j.model.uniprot.relationships.PathwayComment;
import com.bio4j.model.uniprot.relationships.SimilarityComment;
import com.bio4j.model.uniprot.relationships.ActiveSiteFeature;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.bio4j.model.uniprot.relationships.ProteinGenomeElement;
import com.bio4j.model.uniprot.relationships.ProteinInterpro;
import com.bio4j.model.uniprot.relationships.ProteinKeyword;
import com.bio4j.model.uniprot.relationships.ProteinOrganism;
import com.bio4j.model.uniprot.relationships.ProteinPfam;
import com.bio4j.model.uniprot.relationships.ProteinReactome;
import com.bio4j.model.uniprot.relationships.ProteinSubcellularLocation;
import com.bio4j.model.uniprot.relationships.SignalPeptideFeature;
import com.bio4j.model.uniprot.relationships.SpliceVariantFeature;
import com.bio4j.model.uniprot.relationships.TransmembraneRegionFeature;
import com.bio4j.model.uniprot.relationships.references.Cited;
import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.proteinInteractions.relationships.ProteinIsoformInteraction;
import com.bio4j.model.proteinInteractions.relationships.ProteinProteinInteraction;


// properties
import com.bio4j.model.properties.Accession;
import com.bio4j.model.properties.AlternativeAccessions;
import com.bio4j.model.properties.FullName;
import com.bio4j.model.properties.GeneNames;
import com.bio4j.model.properties.Length;
import com.bio4j.model.properties.Mass;
import com.bio4j.model.properties.ModifiedDate;
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Sequence;
import com.bio4j.model.properties.ShortName;

import java.util.List;

public interface Protein extends Node<Protein, Protein.Type>,

	Name<Protein, Protein.Type>,
	Sequence<Protein, Protein.Type>,
	FullName<Protein, Protein.Type>,
	ShortName<Protein, Protein.Type>,
	Accession<Protein, Protein.Type>,
	ModifiedDate<Protein, Protein.Type>,
	Mass<Protein, Protein.Type>,
	Length<Protein, Protein.Type>,
	GeneNames<Protein, Protein.Type>,
	AlternativeAccessions<Protein, Protein.Type>
	{
  
	public static Type TYPE = Type.protein; 
	public static enum Type implements NodeType<Protein, Protein.Type> {
	
	    protein;
	    public Type value() { return protein; }
	 }
    //---------------------------------------------------------------------------------------------
    //-------------------------------------GETTERS-------------------------------------------------
    //---------------------------------------------------------------------------------------------
        
    public String[] getReference(UniprotDBXref ref);   
   
    public boolean isUniref50Representant();
    public boolean isUniref90Representant();
    public boolean isUniref100Representant();
    public List<Protein> getUniref50ClusterThisProteinBelongsTo();
    public List<Protein> getUniref90ClusterThisProteinBelongsTo();
    public List<Protein> getUniref100ClusterThisProteinBelongsTo();
    
    // proteinOrganism
    // outgoing
    public List<ProteinOrganism> proteinOrganism_out(); 
    public List<Organism> proteinOrganism_outNodes();
    
    // proteinDataset
    // outgoing
    public ProteinDataset proteinDataset_out(); 
    public Dataset proteinDataset_outNodes();
    
    // proteinGenomeElement
    // outgoing
    public List<ProteinGenomeElement> proteinGenomeElement_out(); 
    public List<GenomeElement> proteinGenomeElement_outNodes();
    
    // proteinSubcellularLocation
    // outgoing
    public ProteinSubcellularLocation proteinSubcellularLocation_out(); 
    public SubcellularLocation proteinSubcellularLocation_outNodes();
    
    // proteinInterpro
    // outgoing
    public List<ProteinInterpro> proteinInterpro_out(); 
    public List<Interpro> proteinInterpro_outNodes();
    
    // proteinPfam
    // outgoing
    public List<ProteinPfam> proteinPfam_out(); 
    public List<Pfam> proteinPfam_outNodes();
    
    // proteinReactome
    // outgoing
    public List<ProteinReactome> proteinReactome_out(); 
    public List<ReactomeTerm> proteinReactome_outNodes();
    
    // proteinEnzymaticActivity
    // outgoing
    public List<EnzymaticActivity> proteinEnzymaticActivity_out(); 
    public List<Enzyme> proteinEnzymaticActivity_outNodes();
    
    // proteinKeyword
    // outgoing
    public List<ProteinKeyword> proteinKeyword_out(); 
    public List<Keyword> proteinKeyword_outNodes();
    
    // cited
    // outgoing
    public List<Cited> cited_out(); 
    public List<Reference> cited_outNodes();
    
    // submissionProteinCitation
    // outgoing
    public List<SubmissionProteinCitation> submissionProteinCitation_out(); 
    public List<Submission> submissionProteinCitation_outNodes();
    
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
    public List<Submission> getSubmissionCitations();
    public List<OnlineArticle> getOnlineArticleCitations();
    public List<Book> getBookCitations();
    public List<Patent> getPatentCitations();
    public List<Thesis> getThesisCitations();
    public List<UnpublishedObservation> getUnpublishedObservationsCitations();
    public List<GoTerm> getGOAnnotations();
    
    
    //---------------------------------------------------------------------------------------------
    //-------------------------------------SETTERS-------------------------------------------------
    //---------------------------------------------------------------------------------------------
    
    public void setGeneNames(String[] value);
    public void setAlternativeAccessions(String[] value);
    
    public void setReference(UniprotDBXref ref, String[] value);
    

    
}
