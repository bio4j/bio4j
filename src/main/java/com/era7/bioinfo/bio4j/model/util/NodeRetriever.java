/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era7.bioinfo.bio4j.model.util;

import com.era7.bioinfo.bio4j.model.nodes.AlternativeProduct;
import com.era7.bioinfo.bio4j.model.nodes.City;
import com.era7.bioinfo.bio4j.model.nodes.CommentType;
import com.era7.bioinfo.bio4j.model.nodes.Consortium;
import com.era7.bioinfo.bio4j.model.nodes.Country;
import com.era7.bioinfo.bio4j.model.nodes.Dataset;
import com.era7.bioinfo.bio4j.model.nodes.Enzyme;
import com.era7.bioinfo.bio4j.model.nodes.FeatureType;
import com.era7.bioinfo.bio4j.model.nodes.GoTerm;
import com.era7.bioinfo.bio4j.model.nodes.Institute;
import com.era7.bioinfo.bio4j.model.nodes.Interpro;
import com.era7.bioinfo.bio4j.model.nodes.Isoform;
import com.era7.bioinfo.bio4j.model.nodes.Keyword;
import com.era7.bioinfo.bio4j.model.nodes.Organism;
import com.era7.bioinfo.bio4j.model.nodes.Person;
import com.era7.bioinfo.bio4j.model.nodes.Pfam;
import com.era7.bioinfo.bio4j.model.nodes.Protein;
import com.era7.bioinfo.bio4j.model.nodes.SequenceCaution;
import com.era7.bioinfo.bio4j.model.nodes.SubcellularLocation;
import com.era7.bioinfo.bio4j.model.nodes.Taxon;
import com.era7.bioinfo.bio4j.model.nodes.citation.Article;
import com.era7.bioinfo.bio4j.model.nodes.citation.Book;
import com.era7.bioinfo.bio4j.model.nodes.citation.DB;
import com.era7.bioinfo.bio4j.model.nodes.citation.Journal;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineArticle;
import com.era7.bioinfo.bio4j.model.nodes.citation.OnlineJournal;
import com.era7.bioinfo.bio4j.model.nodes.citation.Patent;
import com.era7.bioinfo.bio4j.model.nodes.citation.Publisher;
import com.era7.bioinfo.bio4j.model.nodes.citation.Submission;
import com.era7.bioinfo.bio4j.model.nodes.citation.Thesis;
import com.era7.bioinfo.bio4j.model.nodes.ncbi.NCBITaxon;
import com.era7.bioinfo.bio4j.model.nodes.reactome.ReactomeTerm;
import com.era7.bioinfo.bio4j.model.nodes.refseq.GenomeElement;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NodeRetriever {
    
    public Enzyme getEnzymeById(String id);
    
    public Dataset getDatasetByName(String name);
    public Dataset getSwissProtDataset();
    public Dataset getTremblDataset();
    
    public GenomeElement getGenomeElementByVersion(String version);
    
    public GoTerm getGoTermById(String goId); 
    public GoTerm getMolecularFunctionGoTerm();
    public GoTerm getBiologicalProcessGoTerm();
    public GoTerm getCellularComponentGoTerm();
    
    public Protein getProteinByAccession(String proteinAccession);
    public List<Protein> getProteinByEnsemblPlantsRef(String ensemblPlantsRef);
    public List<Protein> getProteinByFullName(String proteinFullName);
    public List<Protein> getProteinByGeneNames(String proteinGeneName);
    public List<Protein> getProteinByPIRReference(String id);
    public List<Protein> getProteinByKEGGReference(String id);
    public List<Protein> getProteinByEmblReference(String id);
    public List<Protein> getProteinByRefSeqReference(String id);
    public List<Protein> getProteinByArrayExpressReference(String id);
    public List<Protein> getProteinByUniGeneReference(String id);
    public List<Protein> getProteinByDBaseEcoliReference(String id);
    public List<Protein> getProteinByAarhusGhent2DPageReference(String id);
    public List<Protein> getProteinByAGDReference(String id);
    public List<Protein> getProteinByAllergomeReference(String id);
    public List<Protein> getProteinsByAnu2DPageReference(String id);
    public List<Protein> getProteinsByArachnoServerReference(String id);
    public List<Protein> getProteinsByBGEEReference(String id);
    public List<Protein> getProteinsByBindingDBReference(String id);
    public List<Protein> getProteinsByBioCycReference(String id);
    public List<Protein> getProteinsByBrendaReference(String id);
    public List<Protein> getProteinsByCazyReference(String id);
    public List<Protein> getProteinsByCGDReference(String id);
    public List<Protein> getProteinsByCHEmblReference(String id);
    public List<Protein> getProteinsByCleanexReference(String id);
    public List<Protein> getProteinsByCompluYeast2DPageReference(String id);
    public List<Protein> getProteinsByConoServerReference(String id);
    public List<Protein> getProteinsByCornea2DPageReference(String id);
    public List<Protein> getProteinsByCTDReference(String id);
    public List<Protein> getProteinsByCYGDReference(String id);
    public List<Protein> getProteinsByDBSNPReference(String id);
    public List<Protein> getProteinsByDDBJReference(String id);
    public List<Protein> getProteinsByDictyBaseReference(String id);
    public List<Protein> getProteinsByDIPReference(String id);
    public List<Protein> getProteinsByDisprotReference(String id);
    public List<Protein> getProteinsByDMDMReference(String id);
    public List<Protein> getProteinsByDNASUReference(String id);
    public List<Protein> getProteinsByDosacCobs2DPageReference(String id);
    public List<Protein> getProteinsByEchoBaseReference(String id);
    public List<Protein> getProteinsByEcoGeneReference(String id);
    public List<Protein> getProteinsByEggNogReference(String id);
    public List<Protein> getProteinsByEnsemblBacteriaReference(String id);
    public List<Protein> getProteinsByEnsemblFungiReference(String id);
    public List<Protein> getProteinsByEnsemblMetazoaReference(String id);
    public List<Protein> getProteinsByEnsemblProtistsReference(String id);
    public List<Protein> getProteinsByEUHCVDBReference(String id);
    public List<Protein> getProteinsByEUPathDBReference(String id);
    public List<Protein> getProteinsByEvolutionaryTraceReference(String id);
    public List<Protein> getProteinsByFlyBaseReference(String id);
    
    
    public Keyword getKeywordById(String keywordId);
    public Keyword getKeywordByName(String keywordName);
    
    public Interpro getInterproById(String interproId);
    
    public Pfam getPfamById(String pfamId);
    
    public Organism getOrganismByScientificName(String scientificName);
    public Organism getOrganismByNCBITaxonomyId(String ncbiTaxonomyId);
    
    public Taxon getTaxonByName(String taxonName);
    
    public NCBITaxon getNCBITaxonByTaxId(String taxId);
    public NCBITaxon getNCBITaxonByGiId(String giId);
    
    public Isoform getIsoformById(String isoformId);
    
    public List<Person> getPersonByName(String personName);
    
    public Consortium getConsortiumByName(String consortiumName);
    
    public Institute getInstituteByName(String instituteName);
    
    public Country getCountryNodeByName(String countryName);
    
    public City getCityNodeByName(String cityName);
    
    public List<Thesis> getThesisByTitle(String thesisTitle);
    
    public Patent getPatentByNumber(String patentNumber);
    
    public List<Book> getBooksByName(String bookName);
    
    public Publisher getPublisherByName(String publisherName);
    
    public List<OnlineArticle> getOnlineArticlesByTitle(String onlineArticleTitle);
    
    public OnlineJournal getOnlineJournalByName(String onlineJournalName);
    
    public List<Article> getArticlesByTitle(String articleTitle);    
    public Article getArticleByMedlineId(String articleMedlineId);
    public Article getArticleByDoiId(String articleDoiId);    
    public Article getArticleByPubmedId(String articlePubmedId);
    
    public Journal getJournalByName(String journalName);
    
    public ReactomeTerm getReactomeTermById(String reactomeTermId);
    
    public FeatureType getFeatureTypeByName(String featureTypeName);
    
    public CommentType getCommentTypeByName(String commentTypeName);
    
    public SubcellularLocation getSubcellularLocationByName(String subcellularLocationName);
    
    public AlternativeProduct getAlternativeProductInitiationNode();
    public AlternativeProduct getAlternativeProductPromoterNode();
    public AlternativeProduct getAlternativeProductRibosomalFrameshiftingNode();
    public AlternativeProduct getAlternativeProductSplicingNode();
    
    public SequenceCaution getSequenceCautionErroneousGeneModelPredictionNode();
    public SequenceCaution getSequenceCautionErroneousInitiationNode();
    public SequenceCaution getSequenceCautionErroneousTranslationNode();
    public SequenceCaution getSequenceCautionErroneousTerminationNode();
    public SequenceCaution getSequenceCautionFrameshiftNode();
    public SequenceCaution getSequenceCautionMiscellaneousDiscrepancyNode();
    
    public Submission getSubmissionByTitle(String submissionTitle);
    
    public DB getDBByName(String dbName);
}
