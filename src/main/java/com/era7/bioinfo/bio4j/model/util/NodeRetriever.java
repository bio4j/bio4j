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
    public List<Protein> getProteinsByGenAtlasReference(String id);
    public List<Protein> getProteinsByGenBankReference(String id);
    public List<Protein> getProteinsByGene3DReference(String id);
    public List<Protein> getProteinsByGenecardsReference(String id);
    public List<Protein> getProteinsByGeneFarmReference(String id);
    public List<Protein> getProteinsByGeneIDReference(String id);
    public List<Protein> getProteinsByGeneTreeReference(String id);
    public List<Protein> getProteinsByGenevestigatorReference(String id);
    public List<Protein> getProteinsByGenolistReference(String id);
    public List<Protein> getProteinsByGenomeReviewsReference(String id);
    public List<Protein> getProteinsByGenomeRNAIReference(String id);
    public List<Protein> getProteinsByGermOnlineReference(String id);
    public List<Protein> getProteinsByGlycoSuiteDBReference(String id);
    public List<Protein> getProteinsByGPCRDBReference(String id);
    public List<Protein> getProteinsByGrameneReference(String id);
    public List<Protein> getProteinsByHinvDBReference(String id);
    public List<Protein> getProteinsByHamapReference(String id);
    public List<Protein> getProteinsByHGNCReference(String id);
    public List<Protein> getProteinsByHogenomReference(String id);
    public List<Protein> getProteinsByHovergenReference(String id);
    public List<Protein> getProteinsByHPAReference(String id);
    public List<Protein> getProteinsByHSSPReference(String id);
    public List<Protein> getProteinsByHugeReference(String id);
    public List<Protein> getProteinsByIMGTReference(String id);
    public List<Protein> getProteinsByInparanoidReference(String id);
    public List<Protein> getProteinsByIntactReference(String id);
    public List<Protein> getProteinsByIPIReference(String id);
    public List<Protein> getProteinsByKOReference(String id);
    public List<Protein> getProteinsByLegioListReference(String id);
    public List<Protein> getProteinsByLepromaReference(String id);
    public List<Protein> getProteinsByMaizeGDBReference(String id);
    public List<Protein> getProteinsByMeropsReference(String id);
    public List<Protein> getProteinsByMGIReference(String id);
    public List<Protein> getProteinsByMicadoReference(String id);
    public List<Protein> getProteinsByMIMReference(String id);
    public List<Protein> getProteinsByMintReference(String id);
    public List<Protein> getProteinsByModBaseReference(String id);
    public List<Protein> getProteinsByMycoclapReference(String id);
    public List<Protein> getProteinsByNextBioReference(String id);
    public List<Protein> getProteinsByNextProtReference(String id);
    public List<Protein> getProteinsByOGPReference(String id);
    public List<Protein> getProteinsByOMAReference(String id);
    public List<Protein> getProteinsByOrphanetReference(String id);
    public List<Protein> getProteinsByOrthoDBReference(String id);
    public List<Protein> getProteinsByPantherReference(String id);
    public List<Protein> getProteinsByPathwayReference(String id);
    public List<Protein> getProteinsByPatricReference(String id);
    public List<Protein> getProteinsByPaxDBReference(String id);
    public List<Protein> getProteinsByPDBReference(String id);
    public List<Protein> getProteinsByPDBJReference(String id);
    public List<Protein> getProteinsByPDBSumReference(String id);
    public List<Protein> getProteinsByPeptideAtlasReference(String id);
    public List<Protein> getProteinsByPeroxiBaseReference(String id);
    public List<Protein> getProteinsByPharmGKBReference(String id);
    public List<Protein> getProteinsByPHCI2DPageReference(String id);
    public List<Protein> getProteinsByPhosphositeReference(String id);
    public List<Protein> getProteinsByPhylomeDBReference(String id);
    public List<Protein> getProteinsByPirsfReference(String id);
    public List<Protein> getProteinsByPMapCutDBReference(String id);
    public List<Protein> getProteinsByPMMA2DPageReference(String id);
    public List<Protein> getProteinsByPPTaseDBReference(String id);
    public List<Protein> getProteinsByPrideReference(String id);
    public List<Protein> getProteinsByPrintsReference(String id);
    public List<Protein> getProteinsByProDomReference(String id);
    public List<Protein> getProteinsByPromexReference(String id);
    public List<Protein> getProteinsByProSiteReference(String id);
    public List<Protein> getProteinsByProtClustDBReference(String id);
    public List<Protein> getProteinsByProteinModelPortalReference(String id);
    public List<Protein> getProteinsByProtonetReference(String id);
    public List<Protein> getProteinsByPseudoCapReference(String id);
    public List<Protein> getProteinsByRatHeart2DPageReference(String id);
    public List<Protein> getProteinsByRCSBPDBReference(String id);
    public List<Protein> getProteinsByReBaseReference(String id);
    public List<Protein> getProteinsByReproductionReference(String id);
    public List<Protein> getProteinsByRGDReference(String id);
    public List<Protein> getProteinsByRougeReference(String id);
    public List<Protein> getProteinsBySBKBReference(String id);
    public List<Protein> getProteinsBySGDReference(String id);
    public List<Protein> getProteinsBySiena2DPageReference(String id);
    public List<Protein> getProteinsBySmartReference(String id);
    public List<Protein> getProteinsBySMRReference(String id);
    public List<Protein> getProteinsBySourceReference(String id);
    public List<Protein> getProteinsByStringReference(String id);
    public List<Protein> getProteinsBySupfamReference(String id);
    public List<Protein> getProteinsBySwiss2DPageReference(String id);
    public List<Protein> getProteinsByTairReference(String id);
    public List<Protein> getProteinsByTCDBReference(String id);
    public List<Protein> getProteinsByTigrFamsReference(String id);
    public List<Protein> getProteinsByTuberculistReference(String id);
    public List<Protein> getProteinsByUCD2DPageReference(String id);
    public List<Protein> getProteinsByUCSCReference(String id);
    public List<Protein> getProteinsByUniPathwayReference(String id);
    public List<Protein> getProteinsByVectorBaseReference(String id);
    public List<Protein> getProteinsByWorld2DPageReference(String id);
    public List<Protein> getProteinsByWormBaseReference(String id);
    public List<Protein> getProteinsByXenBaseReference(String id);
    public List<Protein> getProteinsByZfinReference(String id);    
    
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
