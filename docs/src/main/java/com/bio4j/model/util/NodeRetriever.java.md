
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


```java
package com.bio4j.model.util;

import com.bio4j.model.nodes.AlternativeProduct;
import com.bio4j.model.nodes.City;
import com.bio4j.model.nodes.CommentType;
import com.bio4j.model.nodes.Consortium;
import com.bio4j.model.nodes.Country;
import com.bio4j.model.nodes.Dataset;
import com.bio4j.model.nodes.Enzyme;
import com.bio4j.model.nodes.FeatureType;
import com.bio4j.model.nodes.GoTerm;
import com.bio4j.model.nodes.Institute;
import com.bio4j.model.nodes.Interpro;
import com.bio4j.model.nodes.Isoform;
import com.bio4j.model.nodes.Keyword;
import com.bio4j.model.nodes.Organism;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Pfam;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.SequenceCaution;
import com.bio4j.model.nodes.SubcellularLocation;
import com.bio4j.model.nodes.Taxon;
import com.bio4j.model.nodes.citation.Article;
import com.bio4j.model.nodes.citation.Book;
import com.bio4j.model.nodes.citation.DB;
import com.bio4j.model.nodes.citation.Journal;
import com.bio4j.model.nodes.citation.OnlineArticle;
import com.bio4j.model.nodes.citation.OnlineJournal;
import com.bio4j.model.nodes.citation.Patent;
import com.bio4j.model.nodes.citation.Publisher;
import com.bio4j.model.nodes.citation.Submission;
import com.bio4j.model.nodes.citation.Thesis;
import com.bio4j.model.nodes.ncbi.NCBITaxon;
import com.bio4j.model.nodes.reactome.ReactomeTerm;
import com.bio4j.model.nodes.refseq.GenomeElement;
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

```


------

### Index

+ src
  + main
    + java
      + com
        + bio4j
          + model
            + [Relationship.java][main/java/com/bio4j/model/Relationship.java]
            + [Node.java][main/java/com/bio4j/model/Node.java]
            + enums
              + [UniprotDBXref.java][main/java/com/bio4j/model/enums/UniprotDBXref.java]
            + relationships
              + uniref
                + [UniRef100Member.java][main/java/com/bio4j/model/relationships/uniref/UniRef100Member.java]
                + [Uniref50Member.java][main/java/com/bio4j/model/relationships/uniref/Uniref50Member.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/relationships/uniref/UniRef90Member.java]
              + [TaxonParent.java][main/java/com/bio4j/model/relationships/TaxonParent.java]
              + [InstituteCountry.java][main/java/com/bio4j/model/relationships/InstituteCountry.java]
              + sc
                + [ErroneousTranslation.java][main/java/com/bio4j/model/relationships/sc/ErroneousTranslation.java]
                + [ErroneousTermination.java][main/java/com/bio4j/model/relationships/sc/ErroneousTermination.java]
                + [ErroneousInitiation.java][main/java/com/bio4j/model/relationships/sc/ErroneousInitiation.java]
                + [Frameshift.java][main/java/com/bio4j/model/relationships/sc/Frameshift.java]
                + [ErroneousGeneModelPrediction.java][main/java/com/bio4j/model/relationships/sc/ErroneousGeneModelPrediction.java]
                + [MiscellaneousDiscrepancy.java][main/java/com/bio4j/model/relationships/sc/MiscellaneousDiscrepancy.java]
              + go
                + [NegativelyRegulatesGo.java][main/java/com/bio4j/model/relationships/go/NegativelyRegulatesGo.java]
                + [RegulatesGo.java][main/java/com/bio4j/model/relationships/go/RegulatesGo.java]
                + [HasPartOfGo.java][main/java/com/bio4j/model/relationships/go/HasPartOfGo.java]
                + [PositivelyRegulatesGo.java][main/java/com/bio4j/model/relationships/go/PositivelyRegulatesGo.java]
                + [PartOfGo.java][main/java/com/bio4j/model/relationships/go/PartOfGo.java]
                + [IsAGo.java][main/java/com/bio4j/model/relationships/go/IsAGo.java]
              + [SubcellularLocationParent.java][main/java/com/bio4j/model/relationships/SubcellularLocationParent.java]
              + refseq
                + [GenomeElementTRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementTRna.java]
                + [GenomeElementMiscRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementMiscRna.java]
                + [GenomeElementTmRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementTmRna.java]
                + [GenomeElementNcRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementNcRna.java]
                + [GenomeElementGene.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementGene.java]
                + [GenomeElementMRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementMRna.java]
                + [GenomeElementRRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementRRna.java]
                + [GenomeElementCDS.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementCDS.java]
              + [IsoformEventGenerator.java][main/java/com/bio4j/model/relationships/IsoformEventGenerator.java]
              + ncbi
                + [NCBITaxonParent.java][main/java/com/bio4j/model/relationships/ncbi/NCBITaxonParent.java]
                + [NCBITaxon.java][main/java/com/bio4j/model/relationships/ncbi/NCBITaxon.java]
              + protein
                + [ProteinIsoform.java][main/java/com/bio4j/model/relationships/protein/ProteinIsoform.java]
                + [ProteinFrameshift.java][main/java/com/bio4j/model/relationships/protein/ProteinFrameshift.java]
                + [ProteinKeyword.java][main/java/com/bio4j/model/relationships/protein/ProteinKeyword.java]
                + [ProteinDataset.java][main/java/com/bio4j/model/relationships/protein/ProteinDataset.java]
                + [ProteinEnzymaticActivity.java][main/java/com/bio4j/model/relationships/protein/ProteinEnzymaticActivity.java]
                + [ProteinReactome.java][main/java/com/bio4j/model/relationships/protein/ProteinReactome.java]
                + [ProteinGo.java][main/java/com/bio4j/model/relationships/protein/ProteinGo.java]
                + [ProteinOrganism.java][main/java/com/bio4j/model/relationships/protein/ProteinOrganism.java]
                + [ProteinErroneousInitiation.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousInitiation.java]
                + [ProteinPfam.java][main/java/com/bio4j/model/relationships/protein/ProteinPfam.java]
                + [ProteinGenomeElement.java][main/java/com/bio4j/model/relationships/protein/ProteinGenomeElement.java]
                + [ProteinProteinInteraction.java][main/java/com/bio4j/model/relationships/protein/ProteinProteinInteraction.java]
                + [ProteinErroneousTermination.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousTermination.java]
                + [BasicProteinSequenceCaution.java][main/java/com/bio4j/model/relationships/protein/BasicProteinSequenceCaution.java]
                + [ProteinSubcellularLocation.java][main/java/com/bio4j/model/relationships/protein/ProteinSubcellularLocation.java]
                + [ProteinErroneousTranslation.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousTranslation.java]
                + [ProteinErroneousGeneModelPrediction.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousGeneModelPrediction.java]
                + [ProteinIsoformInteraction.java][main/java/com/bio4j/model/relationships/protein/ProteinIsoformInteraction.java]
                + [ProteinMiscellaneousDiscrepancy.java][main/java/com/bio4j/model/relationships/protein/ProteinMiscellaneousDiscrepancy.java]
                + [ProteinInterpro.java][main/java/com/bio4j/model/relationships/protein/ProteinInterpro.java]
              + comment
                + [OnlineInformationComment.java][main/java/com/bio4j/model/relationships/comment/OnlineInformationComment.java]
                + [CautionComment.java][main/java/com/bio4j/model/relationships/comment/CautionComment.java]
                + [FunctionComment.java][main/java/com/bio4j/model/relationships/comment/FunctionComment.java]
                + [SimilarityComment.java][main/java/com/bio4j/model/relationships/comment/SimilarityComment.java]
                + [BiotechnologyComment.java][main/java/com/bio4j/model/relationships/comment/BiotechnologyComment.java]
                + [TissueSpecificityComment.java][main/java/com/bio4j/model/relationships/comment/TissueSpecificityComment.java]
                + [DevelopmentalStageComment.java][main/java/com/bio4j/model/relationships/comment/DevelopmentalStageComment.java]
                + [EnzymeRegulationComment.java][main/java/com/bio4j/model/relationships/comment/EnzymeRegulationComment.java]
                + [AllergenComment.java][main/java/com/bio4j/model/relationships/comment/AllergenComment.java]
                + [SubunitComment.java][main/java/com/bio4j/model/relationships/comment/SubunitComment.java]
                + [InductionComment.java][main/java/com/bio4j/model/relationships/comment/InductionComment.java]
                + [CatalyticActivityComment.java][main/java/com/bio4j/model/relationships/comment/CatalyticActivityComment.java]
                + [PharmaceuticalComment.java][main/java/com/bio4j/model/relationships/comment/PharmaceuticalComment.java]
                + [ToxicDoseComment.java][main/java/com/bio4j/model/relationships/comment/ToxicDoseComment.java]
                + [RnaEditingComment.java][main/java/com/bio4j/model/relationships/comment/RnaEditingComment.java]
                + [PathwayComment.java][main/java/com/bio4j/model/relationships/comment/PathwayComment.java]
                + [MiscellaneousComment.java][main/java/com/bio4j/model/relationships/comment/MiscellaneousComment.java]
                + [PostTransactionalModificationComment.java][main/java/com/bio4j/model/relationships/comment/PostTransactionalModificationComment.java]
                + [DisruptionPhenotypeComment.java][main/java/com/bio4j/model/relationships/comment/DisruptionPhenotypeComment.java]
                + [DomainComment.java][main/java/com/bio4j/model/relationships/comment/DomainComment.java]
                + [PolymorphismComment.java][main/java/com/bio4j/model/relationships/comment/PolymorphismComment.java]
                + [CofactorComment.java][main/java/com/bio4j/model/relationships/comment/CofactorComment.java]
                + [BasicComment.java][main/java/com/bio4j/model/relationships/comment/BasicComment.java]
                + [DiseaseComment.java][main/java/com/bio4j/model/relationships/comment/DiseaseComment.java]
                + [MassSpectometryComment.java][main/java/com/bio4j/model/relationships/comment/MassSpectometryComment.java]
                + [BioPhysicoChemicalPropertiesComment.java][main/java/com/bio4j/model/relationships/comment/BioPhysicoChemicalPropertiesComment.java]
              + aproducts
                + [AlternativeProductInitiation.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductInitiation.java]
                + [AlternativeProductRibosomalFrameshifting.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductRibosomalFrameshifting.java]
                + [AlternativeProductSplicing.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductSplicing.java]
                + [AlternativeProductPromoter.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductPromoter.java]
              + features
                + [SignalPeptideFeature.java][main/java/com/bio4j/model/relationships/features/SignalPeptideFeature.java]
                + [NonStandardAminoAcidFeature.java][main/java/com/bio4j/model/relationships/features/NonStandardAminoAcidFeature.java]
                + [SpliceVariantFeature.java][main/java/com/bio4j/model/relationships/features/SpliceVariantFeature.java]
                + [TransitPeptideFeature.java][main/java/com/bio4j/model/relationships/features/TransitPeptideFeature.java]
                + [IntramembraneRegionFeature.java][main/java/com/bio4j/model/relationships/features/IntramembraneRegionFeature.java]
                + [ChainFeature.java][main/java/com/bio4j/model/relationships/features/ChainFeature.java]
                + [PeptideFeature.java][main/java/com/bio4j/model/relationships/features/PeptideFeature.java]
                + [ZincFingerRegionFeature.java][main/java/com/bio4j/model/relationships/features/ZincFingerRegionFeature.java]
                + [CalciumBindingRegionFeature.java][main/java/com/bio4j/model/relationships/features/CalciumBindingRegionFeature.java]
                + [HelixFeature.java][main/java/com/bio4j/model/relationships/features/HelixFeature.java]
                + [SequenceConflictFeature.java][main/java/com/bio4j/model/relationships/features/SequenceConflictFeature.java]
                + [DnaBindingFeature.java][main/java/com/bio4j/model/relationships/features/DnaBindingFeature.java]
                + [SiteFeature.java][main/java/com/bio4j/model/relationships/features/SiteFeature.java]
                + [TransmembraneRegionFeature.java][main/java/com/bio4j/model/relationships/features/TransmembraneRegionFeature.java]
                + [NucleotidePhosphateBindingRegionFeature.java][main/java/com/bio4j/model/relationships/features/NucleotidePhosphateBindingRegionFeature.java]
                + [NonTerminalResidueFeature.java][main/java/com/bio4j/model/relationships/features/NonTerminalResidueFeature.java]
                + [TurnFeature.java][main/java/com/bio4j/model/relationships/features/TurnFeature.java]
                + [LipidMoietyBindingRegionFeature.java][main/java/com/bio4j/model/relationships/features/LipidMoietyBindingRegionFeature.java]
                + [BindingSiteFeature.java][main/java/com/bio4j/model/relationships/features/BindingSiteFeature.java]
                + [InitiatorMethionineFeature.java][main/java/com/bio4j/model/relationships/features/InitiatorMethionineFeature.java]
                + [PropeptideFeature.java][main/java/com/bio4j/model/relationships/features/PropeptideFeature.java]
                + [SequenceVariantFeature.java][main/java/com/bio4j/model/relationships/features/SequenceVariantFeature.java]
                + [MutagenesisSiteFeature.java][main/java/com/bio4j/model/relationships/features/MutagenesisSiteFeature.java]
                + [TopologicalDomainFeature.java][main/java/com/bio4j/model/relationships/features/TopologicalDomainFeature.java]
                + [UnsureResidueFeature.java][main/java/com/bio4j/model/relationships/features/UnsureResidueFeature.java]
                + [DisulfideBondFeature.java][main/java/com/bio4j/model/relationships/features/DisulfideBondFeature.java]
                + [NonConsecutiveResiduesFeature.java][main/java/com/bio4j/model/relationships/features/NonConsecutiveResiduesFeature.java]
                + [RegionOfInterestFeature.java][main/java/com/bio4j/model/relationships/features/RegionOfInterestFeature.java]
                + [MetalIonBindingSiteFeature.java][main/java/com/bio4j/model/relationships/features/MetalIonBindingSiteFeature.java]
                + [GlycosylationSiteFeature.java][main/java/com/bio4j/model/relationships/features/GlycosylationSiteFeature.java]
                + [CoiledCoilRegionFeature.java][main/java/com/bio4j/model/relationships/features/CoiledCoilRegionFeature.java]
                + [CompositionallyBiasedRegionFeature.java][main/java/com/bio4j/model/relationships/features/CompositionallyBiasedRegionFeature.java]
                + [CrossLinkFeature.java][main/java/com/bio4j/model/relationships/features/CrossLinkFeature.java]
                + [StrandFeature.java][main/java/com/bio4j/model/relationships/features/StrandFeature.java]
                + [DomainFeature.java][main/java/com/bio4j/model/relationships/features/DomainFeature.java]
                + [ShortSequenceMotifFeature.java][main/java/com/bio4j/model/relationships/features/ShortSequenceMotifFeature.java]
                + [RepeatFeature.java][main/java/com/bio4j/model/relationships/features/RepeatFeature.java]
                + [ModifiedResidueFeature.java][main/java/com/bio4j/model/relationships/features/ModifiedResidueFeature.java]
                + [ActiveSiteFeature.java][main/java/com/bio4j/model/relationships/features/ActiveSiteFeature.java]
                + [BasicFeature.java][main/java/com/bio4j/model/relationships/features/BasicFeature.java]
              + citation
                + book
                  + [BookCity.java][main/java/com/bio4j/model/relationships/citation/book/BookCity.java]
                  + [BookPublisher.java][main/java/com/bio4j/model/relationships/citation/book/BookPublisher.java]
                  + [BookEditor.java][main/java/com/bio4j/model/relationships/citation/book/BookEditor.java]
                  + [BookProteinCitation.java][main/java/com/bio4j/model/relationships/citation/book/BookProteinCitation.java]
                  + [BookAuthor.java][main/java/com/bio4j/model/relationships/citation/book/BookAuthor.java]
                + patent
                  + [PatentAuthor.java][main/java/com/bio4j/model/relationships/citation/patent/PatentAuthor.java]
                  + [PatentProteinCitation.java][main/java/com/bio4j/model/relationships/citation/patent/PatentProteinCitation.java]
                + article
                  + [ArticleAuthor.java][main/java/com/bio4j/model/relationships/citation/article/ArticleAuthor.java]
                  + [ArticleProteinCitation.java][main/java/com/bio4j/model/relationships/citation/article/ArticleProteinCitation.java]
                  + [ArticleJournal.java][main/java/com/bio4j/model/relationships/citation/article/ArticleJournal.java]
                + uo
                  + [UnpublishedObservationAuthor.java][main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationAuthor.java]
                  + [UnpublishedObservationProteinCitation.java][main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationProteinCitation.java]
                + onarticle
                  + [OnlineArticleJournal.java][main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleJournal.java]
                  + [OnlineArticleAuthor.java][main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleAuthor.java]
                  + [OnlineArticleProteinCitation.java][main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleProteinCitation.java]
                + submission
                  + [SubmissionProteinCitation.java][main/java/com/bio4j/model/relationships/citation/submission/SubmissionProteinCitation.java]
                  + [SubmissionDb.java][main/java/com/bio4j/model/relationships/citation/submission/SubmissionDb.java]
                  + [SubmissionAuthor.java][main/java/com/bio4j/model/relationships/citation/submission/SubmissionAuthor.java]
                + thesis
                  + [ThesisAuthor.java][main/java/com/bio4j/model/relationships/citation/thesis/ThesisAuthor.java]
                  + [ThesisInstitute.java][main/java/com/bio4j/model/relationships/citation/thesis/ThesisInstitute.java]
                  + [ThesisProteinCitation.java][main/java/com/bio4j/model/relationships/citation/thesis/ThesisProteinCitation.java]
            + util
              + [NodeRetriever.java][main/java/com/bio4j/model/util/NodeRetriever.java]
            + nodes
              + [Taxon.java][main/java/com/bio4j/model/nodes/Taxon.java]
              + [Person.java][main/java/com/bio4j/model/nodes/Person.java]
              + [SubcellularLocation.java][main/java/com/bio4j/model/nodes/SubcellularLocation.java]
              + [FeatureType.java][main/java/com/bio4j/model/nodes/FeatureType.java]
              + [Keyword.java][main/java/com/bio4j/model/nodes/Keyword.java]
              + [Protein.java][main/java/com/bio4j/model/nodes/Protein.java]
              + [CommentType.java][main/java/com/bio4j/model/nodes/CommentType.java]
              + [GoTerm.java][main/java/com/bio4j/model/nodes/GoTerm.java]
              + [SequenceCaution.java][main/java/com/bio4j/model/nodes/SequenceCaution.java]
              + [City.java][main/java/com/bio4j/model/nodes/City.java]
              + [AlternativeProduct.java][main/java/com/bio4j/model/nodes/AlternativeProduct.java]
              + refseq
                + [Gene.java][main/java/com/bio4j/model/nodes/refseq/Gene.java]
                + [CDS.java][main/java/com/bio4j/model/nodes/refseq/CDS.java]
                + [GenomeElement.java][main/java/com/bio4j/model/nodes/refseq/GenomeElement.java]
                + rna
                  + [MRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/MRNA.java]
                  + [RRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/RRNA.java]
                  + [NcRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/NcRNA.java]
                  + [MiscRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/MiscRNA.java]
                  + [TmRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/TmRNA.java]
                  + [TRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/TRNA.java]
                  + [RNA.java][main/java/com/bio4j/model/nodes/refseq/rna/RNA.java]
              + [Institute.java][main/java/com/bio4j/model/nodes/Institute.java]
              + [Isoform.java][main/java/com/bio4j/model/nodes/Isoform.java]
              + [Consortium.java][main/java/com/bio4j/model/nodes/Consortium.java]
              + [Pfam.java][main/java/com/bio4j/model/nodes/Pfam.java]
              + [Enzyme.java][main/java/com/bio4j/model/nodes/Enzyme.java]
              + reactome
                + [ReactomeTerm.java][main/java/com/bio4j/model/nodes/reactome/ReactomeTerm.java]
              + [Interpro.java][main/java/com/bio4j/model/nodes/Interpro.java]
              + ncbi
                + [NCBITaxon.java][main/java/com/bio4j/model/nodes/ncbi/NCBITaxon.java]
              + [Organism.java][main/java/com/bio4j/model/nodes/Organism.java]
              + [Dataset.java][main/java/com/bio4j/model/nodes/Dataset.java]
              + citation
                + [Article.java][main/java/com/bio4j/model/nodes/citation/Article.java]
                + [Publisher.java][main/java/com/bio4j/model/nodes/citation/Publisher.java]
                + [Book.java][main/java/com/bio4j/model/nodes/citation/Book.java]
                + [OnlineArticle.java][main/java/com/bio4j/model/nodes/citation/OnlineArticle.java]
                + [Thesis.java][main/java/com/bio4j/model/nodes/citation/Thesis.java]
                + [Submission.java][main/java/com/bio4j/model/nodes/citation/Submission.java]
                + [DB.java][main/java/com/bio4j/model/nodes/citation/DB.java]
                + [OnlineJournal.java][main/java/com/bio4j/model/nodes/citation/OnlineJournal.java]
                + [Patent.java][main/java/com/bio4j/model/nodes/citation/Patent.java]
                + [UnpublishedObservation.java][main/java/com/bio4j/model/nodes/citation/UnpublishedObservation.java]
                + [Journal.java][main/java/com/bio4j/model/nodes/citation/Journal.java]
              + [Country.java][main/java/com/bio4j/model/nodes/Country.java]

[main/java/com/bio4j/model/Relationship.java]: ../Relationship.java.md
[main/java/com/bio4j/model/Node.java]: ../Node.java.md
[main/java/com/bio4j/model/enums/UniprotDBXref.java]: ../enums/UniprotDBXref.java.md
[main/java/com/bio4j/model/relationships/uniref/UniRef100Member.java]: ../relationships/uniref/UniRef100Member.java.md
[main/java/com/bio4j/model/relationships/uniref/Uniref50Member.java]: ../relationships/uniref/Uniref50Member.java.md
[main/java/com/bio4j/model/relationships/uniref/UniRef90Member.java]: ../relationships/uniref/UniRef90Member.java.md
[main/java/com/bio4j/model/relationships/TaxonParent.java]: ../relationships/TaxonParent.java.md
[main/java/com/bio4j/model/relationships/InstituteCountry.java]: ../relationships/InstituteCountry.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousTranslation.java]: ../relationships/sc/ErroneousTranslation.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousTermination.java]: ../relationships/sc/ErroneousTermination.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousInitiation.java]: ../relationships/sc/ErroneousInitiation.java.md
[main/java/com/bio4j/model/relationships/sc/Frameshift.java]: ../relationships/sc/Frameshift.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousGeneModelPrediction.java]: ../relationships/sc/ErroneousGeneModelPrediction.java.md
[main/java/com/bio4j/model/relationships/sc/MiscellaneousDiscrepancy.java]: ../relationships/sc/MiscellaneousDiscrepancy.java.md
[main/java/com/bio4j/model/relationships/go/NegativelyRegulatesGo.java]: ../relationships/go/NegativelyRegulatesGo.java.md
[main/java/com/bio4j/model/relationships/go/RegulatesGo.java]: ../relationships/go/RegulatesGo.java.md
[main/java/com/bio4j/model/relationships/go/HasPartOfGo.java]: ../relationships/go/HasPartOfGo.java.md
[main/java/com/bio4j/model/relationships/go/PositivelyRegulatesGo.java]: ../relationships/go/PositivelyRegulatesGo.java.md
[main/java/com/bio4j/model/relationships/go/PartOfGo.java]: ../relationships/go/PartOfGo.java.md
[main/java/com/bio4j/model/relationships/go/IsAGo.java]: ../relationships/go/IsAGo.java.md
[main/java/com/bio4j/model/relationships/SubcellularLocationParent.java]: ../relationships/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementTRna.java]: ../relationships/refseq/GenomeElementTRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementMiscRna.java]: ../relationships/refseq/GenomeElementMiscRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementTmRna.java]: ../relationships/refseq/GenomeElementTmRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementNcRna.java]: ../relationships/refseq/GenomeElementNcRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementGene.java]: ../relationships/refseq/GenomeElementGene.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementMRna.java]: ../relationships/refseq/GenomeElementMRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementRRna.java]: ../relationships/refseq/GenomeElementRRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementCDS.java]: ../relationships/refseq/GenomeElementCDS.java.md
[main/java/com/bio4j/model/relationships/IsoformEventGenerator.java]: ../relationships/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/relationships/ncbi/NCBITaxonParent.java]: ../relationships/ncbi/NCBITaxonParent.java.md
[main/java/com/bio4j/model/relationships/ncbi/NCBITaxon.java]: ../relationships/ncbi/NCBITaxon.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinIsoform.java]: ../relationships/protein/ProteinIsoform.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinFrameshift.java]: ../relationships/protein/ProteinFrameshift.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinKeyword.java]: ../relationships/protein/ProteinKeyword.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinDataset.java]: ../relationships/protein/ProteinDataset.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinEnzymaticActivity.java]: ../relationships/protein/ProteinEnzymaticActivity.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinReactome.java]: ../relationships/protein/ProteinReactome.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinGo.java]: ../relationships/protein/ProteinGo.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinOrganism.java]: ../relationships/protein/ProteinOrganism.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousInitiation.java]: ../relationships/protein/ProteinErroneousInitiation.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinPfam.java]: ../relationships/protein/ProteinPfam.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinGenomeElement.java]: ../relationships/protein/ProteinGenomeElement.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinProteinInteraction.java]: ../relationships/protein/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousTermination.java]: ../relationships/protein/ProteinErroneousTermination.java.md
[main/java/com/bio4j/model/relationships/protein/BasicProteinSequenceCaution.java]: ../relationships/protein/BasicProteinSequenceCaution.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinSubcellularLocation.java]: ../relationships/protein/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousTranslation.java]: ../relationships/protein/ProteinErroneousTranslation.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousGeneModelPrediction.java]: ../relationships/protein/ProteinErroneousGeneModelPrediction.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinIsoformInteraction.java]: ../relationships/protein/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinMiscellaneousDiscrepancy.java]: ../relationships/protein/ProteinMiscellaneousDiscrepancy.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinInterpro.java]: ../relationships/protein/ProteinInterpro.java.md
[main/java/com/bio4j/model/relationships/comment/OnlineInformationComment.java]: ../relationships/comment/OnlineInformationComment.java.md
[main/java/com/bio4j/model/relationships/comment/CautionComment.java]: ../relationships/comment/CautionComment.java.md
[main/java/com/bio4j/model/relationships/comment/FunctionComment.java]: ../relationships/comment/FunctionComment.java.md
[main/java/com/bio4j/model/relationships/comment/SimilarityComment.java]: ../relationships/comment/SimilarityComment.java.md
[main/java/com/bio4j/model/relationships/comment/BiotechnologyComment.java]: ../relationships/comment/BiotechnologyComment.java.md
[main/java/com/bio4j/model/relationships/comment/TissueSpecificityComment.java]: ../relationships/comment/TissueSpecificityComment.java.md
[main/java/com/bio4j/model/relationships/comment/DevelopmentalStageComment.java]: ../relationships/comment/DevelopmentalStageComment.java.md
[main/java/com/bio4j/model/relationships/comment/EnzymeRegulationComment.java]: ../relationships/comment/EnzymeRegulationComment.java.md
[main/java/com/bio4j/model/relationships/comment/AllergenComment.java]: ../relationships/comment/AllergenComment.java.md
[main/java/com/bio4j/model/relationships/comment/SubunitComment.java]: ../relationships/comment/SubunitComment.java.md
[main/java/com/bio4j/model/relationships/comment/InductionComment.java]: ../relationships/comment/InductionComment.java.md
[main/java/com/bio4j/model/relationships/comment/CatalyticActivityComment.java]: ../relationships/comment/CatalyticActivityComment.java.md
[main/java/com/bio4j/model/relationships/comment/PharmaceuticalComment.java]: ../relationships/comment/PharmaceuticalComment.java.md
[main/java/com/bio4j/model/relationships/comment/ToxicDoseComment.java]: ../relationships/comment/ToxicDoseComment.java.md
[main/java/com/bio4j/model/relationships/comment/RnaEditingComment.java]: ../relationships/comment/RnaEditingComment.java.md
[main/java/com/bio4j/model/relationships/comment/PathwayComment.java]: ../relationships/comment/PathwayComment.java.md
[main/java/com/bio4j/model/relationships/comment/MiscellaneousComment.java]: ../relationships/comment/MiscellaneousComment.java.md
[main/java/com/bio4j/model/relationships/comment/PostTransactionalModificationComment.java]: ../relationships/comment/PostTransactionalModificationComment.java.md
[main/java/com/bio4j/model/relationships/comment/DisruptionPhenotypeComment.java]: ../relationships/comment/DisruptionPhenotypeComment.java.md
[main/java/com/bio4j/model/relationships/comment/DomainComment.java]: ../relationships/comment/DomainComment.java.md
[main/java/com/bio4j/model/relationships/comment/PolymorphismComment.java]: ../relationships/comment/PolymorphismComment.java.md
[main/java/com/bio4j/model/relationships/comment/CofactorComment.java]: ../relationships/comment/CofactorComment.java.md
[main/java/com/bio4j/model/relationships/comment/BasicComment.java]: ../relationships/comment/BasicComment.java.md
[main/java/com/bio4j/model/relationships/comment/DiseaseComment.java]: ../relationships/comment/DiseaseComment.java.md
[main/java/com/bio4j/model/relationships/comment/MassSpectometryComment.java]: ../relationships/comment/MassSpectometryComment.java.md
[main/java/com/bio4j/model/relationships/comment/BioPhysicoChemicalPropertiesComment.java]: ../relationships/comment/BioPhysicoChemicalPropertiesComment.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductInitiation.java]: ../relationships/aproducts/AlternativeProductInitiation.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductRibosomalFrameshifting.java]: ../relationships/aproducts/AlternativeProductRibosomalFrameshifting.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductSplicing.java]: ../relationships/aproducts/AlternativeProductSplicing.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductPromoter.java]: ../relationships/aproducts/AlternativeProductPromoter.java.md
[main/java/com/bio4j/model/relationships/features/SignalPeptideFeature.java]: ../relationships/features/SignalPeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/NonStandardAminoAcidFeature.java]: ../relationships/features/NonStandardAminoAcidFeature.java.md
[main/java/com/bio4j/model/relationships/features/SpliceVariantFeature.java]: ../relationships/features/SpliceVariantFeature.java.md
[main/java/com/bio4j/model/relationships/features/TransitPeptideFeature.java]: ../relationships/features/TransitPeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/IntramembraneRegionFeature.java]: ../relationships/features/IntramembraneRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/ChainFeature.java]: ../relationships/features/ChainFeature.java.md
[main/java/com/bio4j/model/relationships/features/PeptideFeature.java]: ../relationships/features/PeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/ZincFingerRegionFeature.java]: ../relationships/features/ZincFingerRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/CalciumBindingRegionFeature.java]: ../relationships/features/CalciumBindingRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/HelixFeature.java]: ../relationships/features/HelixFeature.java.md
[main/java/com/bio4j/model/relationships/features/SequenceConflictFeature.java]: ../relationships/features/SequenceConflictFeature.java.md
[main/java/com/bio4j/model/relationships/features/DnaBindingFeature.java]: ../relationships/features/DnaBindingFeature.java.md
[main/java/com/bio4j/model/relationships/features/SiteFeature.java]: ../relationships/features/SiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/TransmembraneRegionFeature.java]: ../relationships/features/TransmembraneRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/NucleotidePhosphateBindingRegionFeature.java]: ../relationships/features/NucleotidePhosphateBindingRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/NonTerminalResidueFeature.java]: ../relationships/features/NonTerminalResidueFeature.java.md
[main/java/com/bio4j/model/relationships/features/TurnFeature.java]: ../relationships/features/TurnFeature.java.md
[main/java/com/bio4j/model/relationships/features/LipidMoietyBindingRegionFeature.java]: ../relationships/features/LipidMoietyBindingRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/BindingSiteFeature.java]: ../relationships/features/BindingSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/InitiatorMethionineFeature.java]: ../relationships/features/InitiatorMethionineFeature.java.md
[main/java/com/bio4j/model/relationships/features/PropeptideFeature.java]: ../relationships/features/PropeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/SequenceVariantFeature.java]: ../relationships/features/SequenceVariantFeature.java.md
[main/java/com/bio4j/model/relationships/features/MutagenesisSiteFeature.java]: ../relationships/features/MutagenesisSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/TopologicalDomainFeature.java]: ../relationships/features/TopologicalDomainFeature.java.md
[main/java/com/bio4j/model/relationships/features/UnsureResidueFeature.java]: ../relationships/features/UnsureResidueFeature.java.md
[main/java/com/bio4j/model/relationships/features/DisulfideBondFeature.java]: ../relationships/features/DisulfideBondFeature.java.md
[main/java/com/bio4j/model/relationships/features/NonConsecutiveResiduesFeature.java]: ../relationships/features/NonConsecutiveResiduesFeature.java.md
[main/java/com/bio4j/model/relationships/features/RegionOfInterestFeature.java]: ../relationships/features/RegionOfInterestFeature.java.md
[main/java/com/bio4j/model/relationships/features/MetalIonBindingSiteFeature.java]: ../relationships/features/MetalIonBindingSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/GlycosylationSiteFeature.java]: ../relationships/features/GlycosylationSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/CoiledCoilRegionFeature.java]: ../relationships/features/CoiledCoilRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/CompositionallyBiasedRegionFeature.java]: ../relationships/features/CompositionallyBiasedRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/CrossLinkFeature.java]: ../relationships/features/CrossLinkFeature.java.md
[main/java/com/bio4j/model/relationships/features/StrandFeature.java]: ../relationships/features/StrandFeature.java.md
[main/java/com/bio4j/model/relationships/features/DomainFeature.java]: ../relationships/features/DomainFeature.java.md
[main/java/com/bio4j/model/relationships/features/ShortSequenceMotifFeature.java]: ../relationships/features/ShortSequenceMotifFeature.java.md
[main/java/com/bio4j/model/relationships/features/RepeatFeature.java]: ../relationships/features/RepeatFeature.java.md
[main/java/com/bio4j/model/relationships/features/ModifiedResidueFeature.java]: ../relationships/features/ModifiedResidueFeature.java.md
[main/java/com/bio4j/model/relationships/features/ActiveSiteFeature.java]: ../relationships/features/ActiveSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/BasicFeature.java]: ../relationships/features/BasicFeature.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookCity.java]: ../relationships/citation/book/BookCity.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookPublisher.java]: ../relationships/citation/book/BookPublisher.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookEditor.java]: ../relationships/citation/book/BookEditor.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookProteinCitation.java]: ../relationships/citation/book/BookProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookAuthor.java]: ../relationships/citation/book/BookAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/patent/PatentAuthor.java]: ../relationships/citation/patent/PatentAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/patent/PatentProteinCitation.java]: ../relationships/citation/patent/PatentProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/article/ArticleAuthor.java]: ../relationships/citation/article/ArticleAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/article/ArticleProteinCitation.java]: ../relationships/citation/article/ArticleProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/article/ArticleJournal.java]: ../relationships/citation/article/ArticleJournal.java.md
[main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationAuthor.java]: ../relationships/citation/uo/UnpublishedObservationAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationProteinCitation.java]: ../relationships/citation/uo/UnpublishedObservationProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleJournal.java]: ../relationships/citation/onarticle/OnlineArticleJournal.java.md
[main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleAuthor.java]: ../relationships/citation/onarticle/OnlineArticleAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleProteinCitation.java]: ../relationships/citation/onarticle/OnlineArticleProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/submission/SubmissionProteinCitation.java]: ../relationships/citation/submission/SubmissionProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/submission/SubmissionDb.java]: ../relationships/citation/submission/SubmissionDb.java.md
[main/java/com/bio4j/model/relationships/citation/submission/SubmissionAuthor.java]: ../relationships/citation/submission/SubmissionAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/thesis/ThesisAuthor.java]: ../relationships/citation/thesis/ThesisAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/thesis/ThesisInstitute.java]: ../relationships/citation/thesis/ThesisInstitute.java.md
[main/java/com/bio4j/model/relationships/citation/thesis/ThesisProteinCitation.java]: ../relationships/citation/thesis/ThesisProteinCitation.java.md
[main/java/com/bio4j/model/util/NodeRetriever.java]: NodeRetriever.java.md
[main/java/com/bio4j/model/nodes/Taxon.java]: ../nodes/Taxon.java.md
[main/java/com/bio4j/model/nodes/Person.java]: ../nodes/Person.java.md
[main/java/com/bio4j/model/nodes/SubcellularLocation.java]: ../nodes/SubcellularLocation.java.md
[main/java/com/bio4j/model/nodes/FeatureType.java]: ../nodes/FeatureType.java.md
[main/java/com/bio4j/model/nodes/Keyword.java]: ../nodes/Keyword.java.md
[main/java/com/bio4j/model/nodes/Protein.java]: ../nodes/Protein.java.md
[main/java/com/bio4j/model/nodes/CommentType.java]: ../nodes/CommentType.java.md
[main/java/com/bio4j/model/nodes/GoTerm.java]: ../nodes/GoTerm.java.md
[main/java/com/bio4j/model/nodes/SequenceCaution.java]: ../nodes/SequenceCaution.java.md
[main/java/com/bio4j/model/nodes/City.java]: ../nodes/City.java.md
[main/java/com/bio4j/model/nodes/AlternativeProduct.java]: ../nodes/AlternativeProduct.java.md
[main/java/com/bio4j/model/nodes/refseq/Gene.java]: ../nodes/refseq/Gene.java.md
[main/java/com/bio4j/model/nodes/refseq/CDS.java]: ../nodes/refseq/CDS.java.md
[main/java/com/bio4j/model/nodes/refseq/GenomeElement.java]: ../nodes/refseq/GenomeElement.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/MRNA.java]: ../nodes/refseq/rna/MRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/RRNA.java]: ../nodes/refseq/rna/RRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/NcRNA.java]: ../nodes/refseq/rna/NcRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/MiscRNA.java]: ../nodes/refseq/rna/MiscRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/TmRNA.java]: ../nodes/refseq/rna/TmRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/TRNA.java]: ../nodes/refseq/rna/TRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/RNA.java]: ../nodes/refseq/rna/RNA.java.md
[main/java/com/bio4j/model/nodes/Institute.java]: ../nodes/Institute.java.md
[main/java/com/bio4j/model/nodes/Isoform.java]: ../nodes/Isoform.java.md
[main/java/com/bio4j/model/nodes/Consortium.java]: ../nodes/Consortium.java.md
[main/java/com/bio4j/model/nodes/Pfam.java]: ../nodes/Pfam.java.md
[main/java/com/bio4j/model/nodes/Enzyme.java]: ../nodes/Enzyme.java.md
[main/java/com/bio4j/model/nodes/reactome/ReactomeTerm.java]: ../nodes/reactome/ReactomeTerm.java.md
[main/java/com/bio4j/model/nodes/Interpro.java]: ../nodes/Interpro.java.md
[main/java/com/bio4j/model/nodes/ncbi/NCBITaxon.java]: ../nodes/ncbi/NCBITaxon.java.md
[main/java/com/bio4j/model/nodes/Organism.java]: ../nodes/Organism.java.md
[main/java/com/bio4j/model/nodes/Dataset.java]: ../nodes/Dataset.java.md
[main/java/com/bio4j/model/nodes/citation/Article.java]: ../nodes/citation/Article.java.md
[main/java/com/bio4j/model/nodes/citation/Publisher.java]: ../nodes/citation/Publisher.java.md
[main/java/com/bio4j/model/nodes/citation/Book.java]: ../nodes/citation/Book.java.md
[main/java/com/bio4j/model/nodes/citation/OnlineArticle.java]: ../nodes/citation/OnlineArticle.java.md
[main/java/com/bio4j/model/nodes/citation/Thesis.java]: ../nodes/citation/Thesis.java.md
[main/java/com/bio4j/model/nodes/citation/Submission.java]: ../nodes/citation/Submission.java.md
[main/java/com/bio4j/model/nodes/citation/DB.java]: ../nodes/citation/DB.java.md
[main/java/com/bio4j/model/nodes/citation/OnlineJournal.java]: ../nodes/citation/OnlineJournal.java.md
[main/java/com/bio4j/model/nodes/citation/Patent.java]: ../nodes/citation/Patent.java.md
[main/java/com/bio4j/model/nodes/citation/UnpublishedObservation.java]: ../nodes/citation/UnpublishedObservation.java.md
[main/java/com/bio4j/model/nodes/citation/Journal.java]: ../nodes/citation/Journal.java.md
[main/java/com/bio4j/model/nodes/Country.java]: ../nodes/Country.java.md