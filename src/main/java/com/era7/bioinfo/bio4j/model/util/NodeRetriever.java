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
public interface NodeRetriever{
    
    public <T extends Enzyme> T getEnzymeById(String id);
    
    public <T extends Dataset> T getDatasetByName(String name);
    public <T extends Dataset> T getSwissProtDataset();
    public <T extends Dataset> T getTremblDataset();
    
    public <T extends GenomeElement> T getGenomeElementByVersion(String version);
    
    public <T extends GoTerm> T getGoTermById(String goId); 
    public <T extends GoTerm> T getMolecularFunctionGoTerm();
    public <T extends GoTerm> T getBiologicalProcessGoTerm();
    public <T extends GoTerm> T getCellularComponentGoTerm();
    
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
    public List<? extends Protein> getProteinByDBaseEcoliReference(String id);
    public List<? extends Protein> getProteinByAarhusGhent2DPageReference(String id);
    public List<? extends Protein> getProteinByAGDReference(String id);
    public List<? extends Protein> getProteinByAllergomeReference(String id);
    public List<? extends Protein> getProteinsByAnu2DPageReference(String id);
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
    public List<? extends Protein> getProteinsByCornea2DPageReference(String id);
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
    public List<? extends Protein> getProteinsByPHCI2DPageReference(String id);
    public List<? extends Protein> getProteinsByPhosphositeReference(String id);
    public List<? extends Protein> getProteinsByPhosSiteReference(String id);
    public List<? extends Protein> getProteinsByPhylomeDBReference(String id);
    public List<? extends Protein> getProteinsByPirsfReference(String id);
    public List<? extends Protein> getProteinsByPMapCutDBReference(String id);
    public List<? extends Protein> getProteinsByPMMA2DPageReference(String id);
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
    public List<? extends Protein> getProteinsByRatHeart2DPageReference(String id);
    public List<? extends Protein> getProteinsByRCSBPDBReference(String id);
    public List<? extends Protein> getProteinsByReBaseReference(String id);
    public List<? extends Protein> getProteinsByReproductionReference(String id);
    public List<? extends Protein> getProteinsByRGDReference(String id);
    public List<? extends Protein> getProteinsByRougeReference(String id);
    public List<? extends Protein> getProteinsBySBKBReference(String id);
    public List<? extends Protein> getProteinsBySGDReference(String id);
    public List<? extends Protein> getProteinsBySiena2DPageReference(String id);
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
    
    public <T extends Keyword> T getKeywordById(String keywordId);
    public <T extends Keyword> T getKeywordByName(String keywordName);
    
    public <T extends Interpro> T getInterproById(String interproId);
    
    public <T extends Pfam> T getPfamById(String pfamId);
    
    public <T extends Organism> T getOrganismByScientificName(String scientificName);
    public <T extends Organism> T getOrganismByNCBITaxonomyId(String ncbiTaxonomyId);
    
    public <T extends Taxon> T getTaxonByName(String taxonName);
    
    public <T extends NCBITaxon> T getNCBITaxonByTaxId(String taxId);
    public <T extends NCBITaxon> T getNCBITaxonByGiId(String giId);
    
    public <T extends Isoform> T getIsoformById(String isoformId);
    
    public List<? extends Person> getPersonByName(String personName);
    
    public <T extends Consortium> T getConsortiumByName(String consortiumName);
    
    public <T extends Institute> T getInstituteByName(String instituteName);
    
    public <T extends Country> T getCountryNodeByName(String countryName);
    
    public <T extends City> T getCityNodeByName(String cityName);
    
    public List<? extends Thesis> getThesisByTitle(String thesisTitle);
    
    public <T extends Patent> T getPatentByNumber(String patentNumber);
    
    public List<? extends Book> getBooksByName(String bookName);
    
    public <T extends Publisher> T getPublisherByName(String publisherName);
    
    public List<? extends OnlineArticle> getOnlineArticlesByTitle(String onlineArticleTitle);
    
    public <T extends OnlineJournal> T getOnlineJournalByName(String onlineJournalName);
    
    public List<? extends Article> getArticlesByTitle(String articleTitle);    
    public <T extends Article> T getArticleByMedlineId(String articleMedlineId);
    public <T extends Article> T getArticleByDoiId(String articleDoiId);    
    public <T extends Article> T getArticleByPubmedId(String articlePubmedId);
    
    public <T extends Journal> T getJournalByName(String journalName);
    
    public <T extends ReactomeTerm> T getReactomeTermById(String reactomeTermId);
    
    public <T extends FeatureType> T getFeatureTypeByName(String featureTypeName);
    
    public <T extends CommentType> T getCommentTypeByName(String commentTypeName);
    
    public <T extends SubcellularLocation> T getSubcellularLocationByName(String subcellularLocationName);
    
    public <T extends AlternativeProduct> T getAlternativeProductInitiationNode();
    public <T extends AlternativeProduct> T getAlternativeProductPromoterNode();
    public <T extends AlternativeProduct> T getAlternativeProductRibosomalFrameshiftingNode();
    public <T extends AlternativeProduct> T getAlternativeProductSplicingNode();
    
    public <T extends SequenceCaution> T getSequenceCautionErroneousGeneModelPredictionNode();
    public <T extends SequenceCaution> T getSequenceCautionErroneousInitiationNode();
    public <T extends SequenceCaution> T getSequenceCautionErroneousTranslationNode();
    public <T extends SequenceCaution> T getSequenceCautionErroneousTerminationNode();
    public <T extends SequenceCaution> T getSequenceCautionFrameshiftNode();
    public <T extends SequenceCaution> T getSequenceCautionMiscellaneousDiscrepancyNode();
    
    public <T extends Submission> T getSubmissionByTitle(String submissionTitle);
    
    public <T extends DB> T getDBByName(String dbName);
}
