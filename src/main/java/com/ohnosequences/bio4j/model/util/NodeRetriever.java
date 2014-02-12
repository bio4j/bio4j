/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ohnosequences.bio4j.model.util;

import com.ohnosequences.bio4j.model.nodes.AlternativeProduct;
import com.ohnosequences.bio4j.model.nodes.City;
import com.ohnosequences.bio4j.model.nodes.CommentType;
import com.ohnosequences.bio4j.model.nodes.Consortium;
import com.ohnosequences.bio4j.model.nodes.Country;
import com.ohnosequences.bio4j.model.nodes.Dataset;
import com.ohnosequences.bio4j.model.nodes.Enzyme;
import com.ohnosequences.bio4j.model.nodes.FeatureType;
import com.ohnosequences.bio4j.model.nodes.GoTerm;
import com.ohnosequences.bio4j.model.nodes.Institute;
import com.ohnosequences.bio4j.model.nodes.Interpro;
import com.ohnosequences.bio4j.model.nodes.Isoform;
import com.ohnosequences.bio4j.model.nodes.Keyword;
import com.ohnosequences.bio4j.model.nodes.Organism;
import com.ohnosequences.bio4j.model.nodes.Person;
import com.ohnosequences.bio4j.model.nodes.Pfam;
import com.ohnosequences.bio4j.model.nodes.Protein;
import com.ohnosequences.bio4j.model.nodes.SequenceCaution;
import com.ohnosequences.bio4j.model.nodes.SubcellularLocation;
import com.ohnosequences.bio4j.model.nodes.Taxon;
import com.ohnosequences.bio4j.model.nodes.citation.Article;
import com.ohnosequences.bio4j.model.nodes.citation.Book;
import com.ohnosequences.bio4j.model.nodes.citation.DB;
import com.ohnosequences.bio4j.model.nodes.citation.Journal;
import com.ohnosequences.bio4j.model.nodes.citation.OnlineArticle;
import com.ohnosequences.bio4j.model.nodes.citation.OnlineJournal;
import com.ohnosequences.bio4j.model.nodes.citation.Patent;
import com.ohnosequences.bio4j.model.nodes.citation.Publisher;
import com.ohnosequences.bio4j.model.nodes.citation.Submission;
import com.ohnosequences.bio4j.model.nodes.citation.Thesis;
import com.ohnosequences.bio4j.model.nodes.ncbi.NCBITaxon;
import com.ohnosequences.bio4j.model.nodes.reactome.ReactomeTerm;
import com.ohnosequences.bio4j.model.nodes.refseq.GenomeElement;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NodeRetriever{
    
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
    public List<? extends Protein> getProteinByEnsemblPlantsRef(String ensemblPlantsRef);
    public List<? extends Protein> getProteinByFullName(String proteinFullName);
    public List<? extends Protein> getProteinByGeneNames(String proteinGeneName);
    public List<? extends Protein> getProteinByPIRReference(String id);
    public List<? extends Protein> getProteinByKEGGReference(String id);
    public List<? extends Protein> getProteinByEmblReference(String id);
    public List<? extends Protein> getProteinByRefSeqReference(String id);
    public List<? extends Protein> getProteinByArrayExpressReference(String id);
    public List<? extends Protein> getProteinByUniGeneReference(String id);
    public List<? extends Protein> getProteinByAllergomeReference(String id);
    public List<? extends Protein> getProteinsByArachnoServerReference(String id);
    public List<? extends Protein> getProteinsByBGEEReference(String id);
    public List<? extends Protein> getProteinsByBindingDBReference(String id);
    public List<? extends Protein> getProteinsByBioCycReference(String id);
    public List<? extends Protein> getProteinsByBrendaReference(String id);
    public List<? extends Protein> getProteinsByCazyReference(String id);
    public List<? extends Protein> getProteinsByCGDReference(String id);
    public List<? extends Protein> getProteinsByCHEmblReference(String id);
    public List<? extends Protein> getProteinsByCleanexReference(String id);
    public List<? extends Protein> getProteinsByCompluYeast2DPageReference(String id);
    public List<? extends Protein> getProteinsByConoServerReference(String id);
    public List<? extends Protein> getProteinsByCTDReference(String id);
    public List<? extends Protein> getProteinsByCYGDReference(String id);
    public List<? extends Protein> getProteinsByDBSNPReference(String id);
    public List<? extends Protein> getProteinsByDDBJReference(String id);
    public List<? extends Protein> getProteinsByDictyBaseReference(String id);
    public List<? extends Protein> getProteinsByDIPReference(String id);
    public List<? extends Protein> getProteinsByDisprotReference(String id);
    public List<? extends Protein> getProteinsByDMDMReference(String id);
    public List<? extends Protein> getProteinsByDNASUReference(String id);
    public List<? extends Protein> getProteinsByDosacCobs2DPageReference(String id);
    public List<? extends Protein> getProteinsByEchoBaseReference(String id);
    public List<? extends Protein> getProteinsByEcoGeneReference(String id);
    public List<? extends Protein> getProteinsByEggNogReference(String id);
    public List<? extends Protein> getProteinsByEnsemblBacteriaReference(String id);
    public List<? extends Protein> getProteinsByEnsemblFungiReference(String id);
    public List<? extends Protein> getProteinsByEnsemblMetazoaReference(String id);
    public List<? extends Protein> getProteinsByEnsemblProtistsReference(String id);
    public List<? extends Protein> getProteinsByEUHCVDBReference(String id);
    public List<? extends Protein> getProteinsByEUPathDBReference(String id);
    public List<? extends Protein> getProteinsByEvolutionaryTraceReference(String id);
    public List<? extends Protein> getProteinsByFlyBaseReference(String id);
    public List<? extends Protein> getProteinsByGenAtlasReference(String id);
    public List<? extends Protein> getProteinsByGenBankReference(String id);
    public List<? extends Protein> getProteinsByGene3DReference(String id);
    public List<? extends Protein> getProteinsByGenecardsReference(String id);
    public List<? extends Protein> getProteinsByGeneFarmReference(String id);
    public List<? extends Protein> getProteinsByGeneIDReference(String id);
    public List<? extends Protein> getProteinsByGeneTreeReference(String id);
    public List<? extends Protein> getProteinsByGenevestigatorReference(String id);
    public List<? extends Protein> getProteinsByGenolistReference(String id);
    public List<? extends Protein> getProteinsByGenomeReviewsReference(String id);
    public List<? extends Protein> getProteinsByGenomeRNAIReference(String id);
    public List<? extends Protein> getProteinsByGermOnlineReference(String id);
    public List<? extends Protein> getProteinsByGlycoSuiteDBReference(String id);
    public List<? extends Protein> getProteinsByGPCRDBReference(String id);
    public List<? extends Protein> getProteinsByGrameneReference(String id);
    public List<? extends Protein> getProteinsByHinvDBReference(String id);
    public List<? extends Protein> getProteinsByHamapReference(String id);
    public List<? extends Protein> getProteinsByHGNCReference(String id);
    public List<? extends Protein> getProteinsByHogenomReference(String id);
    public List<? extends Protein> getProteinsByHovergenReference(String id);
    public List<? extends Protein> getProteinsByHPAReference(String id);
    public List<? extends Protein> getProteinsByHSSPReference(String id);
    public List<? extends Protein> getProteinsByHugeReference(String id);
    public List<? extends Protein> getProteinsByIMGTReference(String id);
    public List<? extends Protein> getProteinsByInparanoidReference(String id);
    public List<? extends Protein> getProteinsByIntactReference(String id);
    public List<? extends Protein> getProteinsByIPIReference(String id);
    public List<? extends Protein> getProteinsByKOReference(String id);
    public List<? extends Protein> getProteinsByLegioListReference(String id);
    public List<? extends Protein> getProteinsByLepromaReference(String id);
    public List<? extends Protein> getProteinsByMaizeGDBReference(String id);
    public List<? extends Protein> getProteinsByMeropsReference(String id);
    public List<? extends Protein> getProteinsByMGIReference(String id);
    public List<? extends Protein> getProteinsByMicadoReference(String id);
    public List<? extends Protein> getProteinsByMIMReference(String id);
    public List<? extends Protein> getProteinsByMintReference(String id);
    public List<? extends Protein> getProteinsByModBaseReference(String id);
    public List<? extends Protein> getProteinsByMycoclapReference(String id);
    public List<? extends Protein> getProteinsByNextBioReference(String id);
    public List<? extends Protein> getProteinsByNextProtReference(String id);
    public List<? extends Protein> getProteinsByOGPReference(String id);
    public List<? extends Protein> getProteinsByOMAReference(String id);
    public List<? extends Protein> getProteinsByOrphanetReference(String id);
    public List<? extends Protein> getProteinsByOrthoDBReference(String id);
    public List<? extends Protein> getProteinsByPantherReference(String id);
    public List<? extends Protein> getProteinsByPathwayReference(String id);
    public List<? extends Protein> getProteinsByPatricReference(String id);
    public List<? extends Protein> getProteinsByPaxDBReference(String id);
    public List<? extends Protein> getProteinsByPDBReference(String id);
    public List<? extends Protein> getProteinsByPDBJReference(String id);
    public List<? extends Protein> getProteinsByPDBSumReference(String id);
    public List<? extends Protein> getProteinsByPeptideAtlasReference(String id);
    public List<? extends Protein> getProteinsByPeroxiBaseReference(String id);
    public List<? extends Protein> getProteinsByPharmGKBReference(String id);
    public List<? extends Protein> getProteinsByPhosphositeReference(String id);
    public List<? extends Protein> getProteinsByPhosSiteReference(String id);
    public List<? extends Protein> getProteinsByPhylomeDBReference(String id);
    public List<? extends Protein> getProteinsByPirsfReference(String id);
    public List<? extends Protein> getProteinsByPMapCutDBReference(String id);
    public List<? extends Protein> getProteinsByPPTaseDBReference(String id);
    public List<? extends Protein> getProteinsByPrideReference(String id);
    public List<? extends Protein> getProteinsByPrintsReference(String id);
    public List<? extends Protein> getProteinsByProDomReference(String id);
    public List<? extends Protein> getProteinsByPromexReference(String id);
    public List<? extends Protein> getProteinsByProSiteReference(String id);
    public List<? extends Protein> getProteinsByProtClustDBReference(String id);
    public List<? extends Protein> getProteinsByProteinModelPortalReference(String id);
    public List<? extends Protein> getProteinsByProtonetReference(String id);
    public List<? extends Protein> getProteinsByPseudoCapReference(String id);
    public List<? extends Protein> getProteinsByRCSBPDBReference(String id);
    public List<? extends Protein> getProteinsByReBaseReference(String id);
    public List<? extends Protein> getProteinsByReproductionReference(String id);
    public List<? extends Protein> getProteinsByRGDReference(String id);
    public List<? extends Protein> getProteinsByRougeReference(String id);
    public List<? extends Protein> getProteinsBySBKBReference(String id);
    public List<? extends Protein> getProteinsBySGDReference(String id);
    public List<? extends Protein> getProteinsBySmartReference(String id);
    public List<? extends Protein> getProteinsBySMRReference(String id);
    public List<? extends Protein> getProteinsBySourceReference(String id);
    public List<? extends Protein> getProteinsByStringReference(String id);
    public List<? extends Protein> getProteinsBySupfamReference(String id);
    public List<? extends Protein> getProteinsBySwiss2DPageReference(String id);
    public List<? extends Protein> getProteinsByTairReference(String id);
    public List<? extends Protein> getProteinsByTCDBReference(String id);
    public List<? extends Protein> getProteinsByTigrFamsReference(String id);
    public List<? extends Protein> getProteinsByTuberculistReference(String id);
    public List<? extends Protein> getProteinsByUCD2DPageReference(String id);
    public List<? extends Protein> getProteinsByUCSCReference(String id);
    public List<? extends Protein> getProteinsByUniPathwayReference(String id);
    public List<? extends Protein> getProteinsByVectorBaseReference(String id);
    public List<? extends Protein> getProteinsByWorld2DPageReference(String id);
    public List<? extends Protein> getProteinsByWormBaseReference(String id);
    public List<? extends Protein> getProteinsByXenBaseReference(String id);
    public List<? extends Protein> getProteinsByZfinReference(String id);    
    
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
    
    public List<? extends Person> getPersonByName(String personName);
    
    public Consortium getConsortiumByName(String consortiumName);
    
    public Institute getInstituteByName(String instituteName);
    
    public Country getCountryNodeByName(String countryName);
    
    public City getCityNodeByName(String cityName);
    
    public List<? extends Thesis> getThesisByTitle(String thesisTitle);
    
    public Patent getPatentByNumber(String patentNumber);
    
    public List<? extends Book> getBooksByName(String bookName);
    
    public Publisher getPublisherByName(String publisherName);
    
    public List<? extends OnlineArticle> getOnlineArticlesByTitle(String onlineArticleTitle);
    
    public OnlineJournal getOnlineJournalByName(String onlineJournalName);
    
    public List<? extends Article> getArticlesByTitle(String articleTitle);    
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
