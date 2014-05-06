
```java
package com.bio4j.model.enums;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public enum UniprotDBXref {
        
    ALLERGOME("protein_allergome_references","Allergome"),
    ARACHNO_SERVER("protein_arachnoserver_references","ArachnoServer"),
    ARRAY_EXPRESS("protein_array_express_references","ArrayExpress"), 
    BGEE("protein_bgee_references","Bgee"),
    BINDING_DB("protein_binding_db_references","BindingDB"),
    BIOCYC("protein_biocyc_references","BioCyc"),
    BRENDA("protein_brenda_references","BRENDA"), 
    CAZY("protein_cazy_references","CAZy"),
    CGD("protein_cgd_references", "CGD"),
    CHEMBL("protein_chembl_references", "ChEMBL"),
    CHITARS("protein_chitars_references","ChiTaRS"),
    CLEANEX("protein_cleanex_references","CleanEx"),
    COMPLUYEAST_2D_PAGE("protein_compluyeast_2dpage_references","COMPLUYEAST-2DPAGE"), 
    CONO_SERVER("protein_conoserver_references","ConoServer"),
    CTD("protein_ctd_references", "CTD"),
    CYGD("protein_cygd_references","CYGD"),
    DBSNP("protein_dbsnp_references","dbSNP"), 
    DDBJ("protein_ddbj_references","DDBJ"), 
    DICTY_BASE("protein_dicty_base_references","dictyBase"),
    DIP("protein_dip_references","DIP"), 
    DISPROT("protein_dip_references","DisProt"), 
    DMDM("protein_dmdm_references","DMDM"), 
    DNASU("protein_dnasu_references","DNASU"), 
    DOSAC_COBS_2D_PAGE("protein_dosac_cobs_2dpage_references","DOSAC-COBS-2DPAGE"),
    DRUG_BANK("protein_drugbank_references","DrugBank"),
    ECHO_BASE("protein_echobase_references","EchoBASE"), 
    ECO_GENE("protein_ecogene_references","EcoGene"), 
    EGGNOG("protein_eggnog_references","eggNOG"),      
    EMBL("protein_embl_references","EMBL"), 
    ENSEMBL("protein_ensembl_references", "Ensembl"),
    ENSEMBL_BACTERIA("protein_ensembl_bacteria_references","EnsemblBacteria"), 
    ENSEMBL_FUNGI("protein_ensembl_fungi_references","EnsemblFungi"), 
    ENSEMBL_METAZOA("protein_ensembl_metazoa_references","EnsemblMetazoa"), 
    ENSEMBL_PLANTS("protein_ensembl_plants_references", "EnsemblPlants"), 
    ENSEMBL_PROTISTS("protein_ensembl_protists_references","EnsemblProtists"),
    EUHCV_DB("protein_euhcvdb_references","euHCVdb"),
    EUPATH_DB("protein_eupathdb_references","EuPathDB"), 
    EVOLUTIONARY_TRACE("protein_evolutionary_trace_references","EvolutionaryTrace"), 
    FLYBASE("protein_flybase_references","FlyBase"), 
    GENATLAS("protein_genatlas_references","GenAtlas"), 
    GENBANK("protein_genbank_references","GenBank"), 
    GENE3D("protein_gene3d_references","Gene3D"), 
    GENECARDS("protein_genecards_references","GeneCards"), 
    GENEFARM("protein_genefarm_references","GeneFarm"), 
    GENEID("protein_geneid_references","GeneID"), 
    GENETREE("protein_genetree_references","GeneTree"), 
    GENEVESTIGATOR("protein_genevestigator_references","Genevestigator"),
    GENOLIST("protein_genolist_references","GenoList"), 
    GENOME_REVIEWS("protein_genome_reviews_references","GenomeReviews"),
    GENOME_RNAI("protein_genome_rnai_references","GenomeRNAi"),
    GERMONLINE("protein_germonline_references","GermOnline"), 
    GLYCO_SUITE_DB("protein_glycosuitedb_references","GlycoSuiteDB"), 
    GPCR_DB("protein_gpcrdb_references","GPCRDB"), 
    GRAMENE("protein_gramene_references","Gramene"), 
    HINV_DB("protein_hinvdb_references","H-InvDB"), 
    HAMAP("protein_hamap_references","HAMAP"),
    HGNC("protein_hgnc_references","HGNC"), 
    HOGENOM("protein_hogenom_references","HOGENOM"), 
    HOVERGEN("protein_hovergen_references","HOVERGEN"),
    HPA("protein_hpa_references","HPA"),
    HSSP("protein_hssp_references","HSSP"), 
    HUGE("protein_huge_references","HUGE"),
    IMGT("protein_imgt_references","IMGT"),
    INPARANOID("protein_inparanoid_references","InParanoid"),
    INTACT("protein_intact_references","IntAct"), 
    IPI("protein_ipi_references","IPI"), 
    KEGG("protein_kegg_references","KEGG"),    
    KO("protein_ko_references","KO"), 
    LEGIOLIST("protein_legio_list_references","LegioList"), 
    LEPROMA("protein_leproma_references","Leproma"),
    MAIZEGD_DB("protein_maize_gdb_references","MaizeGDB"),
    MEROPS("protein_merops_references","MEROPS"), 
    MGI("protein_mgi_references","MGI"), 
    MICADO("protein_micado_references","Micado"), 
    MIM("protein_mim_references","MIM"), 
    MINT("protein_mint_references","MINT"), 
    MODBASE("protein_modbase_references","ModBase"), 
    MYCOCLAP("protein_mycoclap_references","mycoCLAP"), 
    NEXTBIO("protein_nextbio_references","NextBio"), 
    NEXTPROT("protein_nextprot_references","neXtProt"), 
    OGP("protein_ogp_references","OGP"), 
    OMA("protein_oma_references","OMA"), 
    ORPHANET("protein_orphanet_references","Orphanet"), 
    ORTHO_DB("protein_orthodb_references","OrthoDB"), 
    PANTHER("protein_panther_references","PANTHER"), 
    PATHWAY_INTERACTION_DB("protein_pathway_interaction_db_references","Pathway_Interaction_DB"),
    PATRIC("protein_patric_references","PATRIC"), 
    PAXDB("protein_paxdb_references","PaxDb"), 
    PDB("protein_pdb_references","PDB"), 
    PDBJ("protein_pdbj_references","PDBj"), 
    PDBSUM("protein_pdbsum_references","PDBsum"), 
    PEPTIDE_ATLAS("protein_peptide_atlas_references","PeptideAtlas"), 
    PEROXIBASE("protein_peroxibase_references","PeroxiBase"), 
    PHARM_GKB("protein_pharmgkb_references","PharmGKB"), 
    PHOSPHOSITE("protein_phosphosite_references","PhosphoSite"), 
    PHOS_SITE("protein_phos_site_references","PhosSite"), 
    PHYLOME_DB("protein_phylome_db_references","PhylomeDB"), 
    PIR("protein_pir_references","PIR"), 
    PIRSF("protein_pirsf_references","PIRSF"), 
    PMAP_CUT_DB("protein_pmap_cutdb_references","PMAP-CutDB"), 
    POMBASE("protein_pombase_references","PomBase"),
    PPTASE_DB("protein_pptasedb_references","PptaseDB"),
    PRIDE("protein_pride_references","PRIDE"), 
    PRINTS("protein_prints_references","PRINTS"), 
    PRODOM("protein_prodom_references","ProDom"), 
    PROMEX("protein_promex_references","ProMEX"), 
    PROSITE("protein_prosite_references","PROSITE"), 
    PROT_CLUST_DB("protein_prot_clust_db_references","ProtClustDB"), 
    PROTEIN_MODEL_PORTAL("protein_model_portal_references","ProteinModelPortal"), 
    PROTONET("protein_protonet_references","ProtoNet"), 
    PSEUDOCAP("protein_pseudo_cap_references","PseudoCAP"), 
    RCSBPDB("protein_rcsb_pdb_references","RCSB PDB"), 
    REBASE("protein_rebase_references","REBASE"),
    REFSEQ("protein_refseq_references","RefSeq"), 
    REPRODUCTION_2D_PAGE("protein_reproduction_2dpage_references","REPRODUCTION-2DPAGE"),
    RGD("protein_rgd_references","RGD"), 
    ROUGE("protein_rouge_references","Rouge"),
    SABIO_RK("protein_sabio_rk_references","SABIO-RK"), 
    SBKB("protein_sbkb_references","SBKB"), 
    SGD("protein_sgd_references","SGD"), 
    SMART("protein_smart_references","SMART"), 
    SMR("protein_smr_references","SMR"), 
    SOURCE("protein_source_references","SOURCE"),
    STRING("protein_string_references","STRING"), 
    SUPFAM("protein_supfam_references","SUPFAM"), 
    SWISS_2D_PAGE("protein_swiss_2dpage_references","SWISS-2DPAGE"), 
    TAIR("protein_tair_references","TAIR"), 
    TCDB("protein_tcb_references","TCDB"), 
    TIGRFAMS("protein_tigrfams_references","TIGRFAMs"), 
    TUBERCULIST("protein_tuberculist_references","TubercuList"), 
    UCD_2D_PAGE("protein_ucd_2dpage_references","UCD-2DPAGE"), 
    UCSC("protein_ucsc_references","UCSC"),
    UNIGENE("protein_unigene_references","UniGene"),
    UNIPATHWAY("protein_unipathway_references","UniPathway"), 
    VECTORBASE("protein_vector_base_references","VectorBase"), 
    WORLD_2D_PAGE("protein_world_2dpage_references","World-2DPAGE"), 
    WORMBASE("protein_worm_base_references","WormBase"), 
    XENBASE("protein_xen_base_references","Xenbase"), 
    ZFIN("protein_zfin_references","ZFIN");
    
    private final String uniprotAtributeValue;
    private final String proteinReferencePropertyName;
    
    private UniprotDBXref(String uniprotAttValue, String propertyName) {
        this.uniprotAtributeValue = uniprotAttValue;
        this.proteinReferencePropertyName = propertyName;
    }
    
    public String getProteinReferencePropertyName(){
        return proteinReferencePropertyName;
    }
    
    public String getUniprotAttributeValue(){
        return uniprotAtributeValue;
    }
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
            + enums
              + [UniprotDBXref.java][main/java/com/bio4j/model/enums/UniprotDBXref.java]
            + [Node.java][main/java/com/bio4j/model/Node.java]
            + nodes
              + [AlternativeProduct.java][main/java/com/bio4j/model/nodes/AlternativeProduct.java]
              + citation
                + [Article.java][main/java/com/bio4j/model/nodes/citation/Article.java]
                + [Book.java][main/java/com/bio4j/model/nodes/citation/Book.java]
                + [DB.java][main/java/com/bio4j/model/nodes/citation/DB.java]
                + [Journal.java][main/java/com/bio4j/model/nodes/citation/Journal.java]
                + [OnlineArticle.java][main/java/com/bio4j/model/nodes/citation/OnlineArticle.java]
                + [OnlineJournal.java][main/java/com/bio4j/model/nodes/citation/OnlineJournal.java]
                + [Patent.java][main/java/com/bio4j/model/nodes/citation/Patent.java]
                + [Publisher.java][main/java/com/bio4j/model/nodes/citation/Publisher.java]
                + [Submission.java][main/java/com/bio4j/model/nodes/citation/Submission.java]
                + [Thesis.java][main/java/com/bio4j/model/nodes/citation/Thesis.java]
                + [UnpublishedObservation.java][main/java/com/bio4j/model/nodes/citation/UnpublishedObservation.java]
              + [City.java][main/java/com/bio4j/model/nodes/City.java]
              + [CommentType.java][main/java/com/bio4j/model/nodes/CommentType.java]
              + [Consortium.java][main/java/com/bio4j/model/nodes/Consortium.java]
              + [Country.java][main/java/com/bio4j/model/nodes/Country.java]
              + [Dataset.java][main/java/com/bio4j/model/nodes/Dataset.java]
              + [Enzyme.java][main/java/com/bio4j/model/nodes/Enzyme.java]
              + [FeatureType.java][main/java/com/bio4j/model/nodes/FeatureType.java]
              + [GoTerm.java][main/java/com/bio4j/model/nodes/GoTerm.java]
              + [Institute.java][main/java/com/bio4j/model/nodes/Institute.java]
              + [Interpro.java][main/java/com/bio4j/model/nodes/Interpro.java]
              + [Isoform.java][main/java/com/bio4j/model/nodes/Isoform.java]
              + [Keyword.java][main/java/com/bio4j/model/nodes/Keyword.java]
              + ncbi
                + [NCBITaxon.java][main/java/com/bio4j/model/nodes/ncbi/NCBITaxon.java]
              + [Organism.java][main/java/com/bio4j/model/nodes/Organism.java]
              + [Person.java][main/java/com/bio4j/model/nodes/Person.java]
              + [Pfam.java][main/java/com/bio4j/model/nodes/Pfam.java]
              + [Protein.java][main/java/com/bio4j/model/nodes/Protein.java]
              + reactome
                + [ReactomeTerm.java][main/java/com/bio4j/model/nodes/reactome/ReactomeTerm.java]
              + refseq
                + [CDS.java][main/java/com/bio4j/model/nodes/refseq/CDS.java]
                + [Gene.java][main/java/com/bio4j/model/nodes/refseq/Gene.java]
                + [GenomeElement.java][main/java/com/bio4j/model/nodes/refseq/GenomeElement.java]
                + rna
                  + [MiscRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/MiscRNA.java]
                  + [MRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/MRNA.java]
                  + [NcRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/NcRNA.java]
                  + [RNA.java][main/java/com/bio4j/model/nodes/refseq/rna/RNA.java]
                  + [RRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/RRNA.java]
                  + [TmRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/TmRNA.java]
                  + [TRNA.java][main/java/com/bio4j/model/nodes/refseq/rna/TRNA.java]
              + [SequenceCaution.java][main/java/com/bio4j/model/nodes/SequenceCaution.java]
              + [SubcellularLocation.java][main/java/com/bio4j/model/nodes/SubcellularLocation.java]
              + [Taxon.java][main/java/com/bio4j/model/nodes/Taxon.java]
            + [Relationship.java][main/java/com/bio4j/model/Relationship.java]
            + relationships
              + aproducts
                + [AlternativeProductInitiation.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductInitiation.java]
                + [AlternativeProductPromoter.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductPromoter.java]
                + [AlternativeProductRibosomalFrameshifting.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductRibosomalFrameshifting.java]
                + [AlternativeProductSplicing.java][main/java/com/bio4j/model/relationships/aproducts/AlternativeProductSplicing.java]
              + citation
                + article
                  + [ArticleAuthor.java][main/java/com/bio4j/model/relationships/citation/article/ArticleAuthor.java]
                  + [ArticleJournal.java][main/java/com/bio4j/model/relationships/citation/article/ArticleJournal.java]
                  + [ArticleProteinCitation.java][main/java/com/bio4j/model/relationships/citation/article/ArticleProteinCitation.java]
                + book
                  + [BookAuthor.java][main/java/com/bio4j/model/relationships/citation/book/BookAuthor.java]
                  + [BookCity.java][main/java/com/bio4j/model/relationships/citation/book/BookCity.java]
                  + [BookEditor.java][main/java/com/bio4j/model/relationships/citation/book/BookEditor.java]
                  + [BookProteinCitation.java][main/java/com/bio4j/model/relationships/citation/book/BookProteinCitation.java]
                  + [BookPublisher.java][main/java/com/bio4j/model/relationships/citation/book/BookPublisher.java]
                + onarticle
                  + [OnlineArticleAuthor.java][main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleAuthor.java]
                  + [OnlineArticleJournal.java][main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleJournal.java]
                  + [OnlineArticleProteinCitation.java][main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleProteinCitation.java]
                + patent
                  + [PatentAuthor.java][main/java/com/bio4j/model/relationships/citation/patent/PatentAuthor.java]
                  + [PatentProteinCitation.java][main/java/com/bio4j/model/relationships/citation/patent/PatentProteinCitation.java]
                + submission
                  + [SubmissionAuthor.java][main/java/com/bio4j/model/relationships/citation/submission/SubmissionAuthor.java]
                  + [SubmissionDb.java][main/java/com/bio4j/model/relationships/citation/submission/SubmissionDb.java]
                  + [SubmissionProteinCitation.java][main/java/com/bio4j/model/relationships/citation/submission/SubmissionProteinCitation.java]
                + thesis
                  + [ThesisAuthor.java][main/java/com/bio4j/model/relationships/citation/thesis/ThesisAuthor.java]
                  + [ThesisInstitute.java][main/java/com/bio4j/model/relationships/citation/thesis/ThesisInstitute.java]
                  + [ThesisProteinCitation.java][main/java/com/bio4j/model/relationships/citation/thesis/ThesisProteinCitation.java]
                + uo
                  + [UnpublishedObservationAuthor.java][main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationAuthor.java]
                  + [UnpublishedObservationProteinCitation.java][main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationProteinCitation.java]
              + comment
                + [AllergenComment.java][main/java/com/bio4j/model/relationships/comment/AllergenComment.java]
                + [BasicComment.java][main/java/com/bio4j/model/relationships/comment/BasicComment.java]
                + [BioPhysicoChemicalPropertiesComment.java][main/java/com/bio4j/model/relationships/comment/BioPhysicoChemicalPropertiesComment.java]
                + [BiotechnologyComment.java][main/java/com/bio4j/model/relationships/comment/BiotechnologyComment.java]
                + [CatalyticActivityComment.java][main/java/com/bio4j/model/relationships/comment/CatalyticActivityComment.java]
                + [CautionComment.java][main/java/com/bio4j/model/relationships/comment/CautionComment.java]
                + [CofactorComment.java][main/java/com/bio4j/model/relationships/comment/CofactorComment.java]
                + [DevelopmentalStageComment.java][main/java/com/bio4j/model/relationships/comment/DevelopmentalStageComment.java]
                + [DiseaseComment.java][main/java/com/bio4j/model/relationships/comment/DiseaseComment.java]
                + [DisruptionPhenotypeComment.java][main/java/com/bio4j/model/relationships/comment/DisruptionPhenotypeComment.java]
                + [DomainComment.java][main/java/com/bio4j/model/relationships/comment/DomainComment.java]
                + [EnzymeRegulationComment.java][main/java/com/bio4j/model/relationships/comment/EnzymeRegulationComment.java]
                + [FunctionComment.java][main/java/com/bio4j/model/relationships/comment/FunctionComment.java]
                + [InductionComment.java][main/java/com/bio4j/model/relationships/comment/InductionComment.java]
                + [MassSpectometryComment.java][main/java/com/bio4j/model/relationships/comment/MassSpectometryComment.java]
                + [MiscellaneousComment.java][main/java/com/bio4j/model/relationships/comment/MiscellaneousComment.java]
                + [OnlineInformationComment.java][main/java/com/bio4j/model/relationships/comment/OnlineInformationComment.java]
                + [PathwayComment.java][main/java/com/bio4j/model/relationships/comment/PathwayComment.java]
                + [PharmaceuticalComment.java][main/java/com/bio4j/model/relationships/comment/PharmaceuticalComment.java]
                + [PolymorphismComment.java][main/java/com/bio4j/model/relationships/comment/PolymorphismComment.java]
                + [PostTransactionalModificationComment.java][main/java/com/bio4j/model/relationships/comment/PostTransactionalModificationComment.java]
                + [RnaEditingComment.java][main/java/com/bio4j/model/relationships/comment/RnaEditingComment.java]
                + [SimilarityComment.java][main/java/com/bio4j/model/relationships/comment/SimilarityComment.java]
                + [SubunitComment.java][main/java/com/bio4j/model/relationships/comment/SubunitComment.java]
                + [TissueSpecificityComment.java][main/java/com/bio4j/model/relationships/comment/TissueSpecificityComment.java]
                + [ToxicDoseComment.java][main/java/com/bio4j/model/relationships/comment/ToxicDoseComment.java]
              + features
                + [ActiveSiteFeature.java][main/java/com/bio4j/model/relationships/features/ActiveSiteFeature.java]
                + [BasicFeature.java][main/java/com/bio4j/model/relationships/features/BasicFeature.java]
                + [BindingSiteFeature.java][main/java/com/bio4j/model/relationships/features/BindingSiteFeature.java]
                + [CalciumBindingRegionFeature.java][main/java/com/bio4j/model/relationships/features/CalciumBindingRegionFeature.java]
                + [ChainFeature.java][main/java/com/bio4j/model/relationships/features/ChainFeature.java]
                + [CoiledCoilRegionFeature.java][main/java/com/bio4j/model/relationships/features/CoiledCoilRegionFeature.java]
                + [CompositionallyBiasedRegionFeature.java][main/java/com/bio4j/model/relationships/features/CompositionallyBiasedRegionFeature.java]
                + [CrossLinkFeature.java][main/java/com/bio4j/model/relationships/features/CrossLinkFeature.java]
                + [DisulfideBondFeature.java][main/java/com/bio4j/model/relationships/features/DisulfideBondFeature.java]
                + [DnaBindingFeature.java][main/java/com/bio4j/model/relationships/features/DnaBindingFeature.java]
                + [DomainFeature.java][main/java/com/bio4j/model/relationships/features/DomainFeature.java]
                + [GlycosylationSiteFeature.java][main/java/com/bio4j/model/relationships/features/GlycosylationSiteFeature.java]
                + [HelixFeature.java][main/java/com/bio4j/model/relationships/features/HelixFeature.java]
                + [InitiatorMethionineFeature.java][main/java/com/bio4j/model/relationships/features/InitiatorMethionineFeature.java]
                + [IntramembraneRegionFeature.java][main/java/com/bio4j/model/relationships/features/IntramembraneRegionFeature.java]
                + [LipidMoietyBindingRegionFeature.java][main/java/com/bio4j/model/relationships/features/LipidMoietyBindingRegionFeature.java]
                + [MetalIonBindingSiteFeature.java][main/java/com/bio4j/model/relationships/features/MetalIonBindingSiteFeature.java]
                + [ModifiedResidueFeature.java][main/java/com/bio4j/model/relationships/features/ModifiedResidueFeature.java]
                + [MutagenesisSiteFeature.java][main/java/com/bio4j/model/relationships/features/MutagenesisSiteFeature.java]
                + [NonConsecutiveResiduesFeature.java][main/java/com/bio4j/model/relationships/features/NonConsecutiveResiduesFeature.java]
                + [NonStandardAminoAcidFeature.java][main/java/com/bio4j/model/relationships/features/NonStandardAminoAcidFeature.java]
                + [NonTerminalResidueFeature.java][main/java/com/bio4j/model/relationships/features/NonTerminalResidueFeature.java]
                + [NucleotidePhosphateBindingRegionFeature.java][main/java/com/bio4j/model/relationships/features/NucleotidePhosphateBindingRegionFeature.java]
                + [PeptideFeature.java][main/java/com/bio4j/model/relationships/features/PeptideFeature.java]
                + [PropeptideFeature.java][main/java/com/bio4j/model/relationships/features/PropeptideFeature.java]
                + [RegionOfInterestFeature.java][main/java/com/bio4j/model/relationships/features/RegionOfInterestFeature.java]
                + [RepeatFeature.java][main/java/com/bio4j/model/relationships/features/RepeatFeature.java]
                + [SequenceConflictFeature.java][main/java/com/bio4j/model/relationships/features/SequenceConflictFeature.java]
                + [SequenceVariantFeature.java][main/java/com/bio4j/model/relationships/features/SequenceVariantFeature.java]
                + [ShortSequenceMotifFeature.java][main/java/com/bio4j/model/relationships/features/ShortSequenceMotifFeature.java]
                + [SignalPeptideFeature.java][main/java/com/bio4j/model/relationships/features/SignalPeptideFeature.java]
                + [SiteFeature.java][main/java/com/bio4j/model/relationships/features/SiteFeature.java]
                + [SpliceVariantFeature.java][main/java/com/bio4j/model/relationships/features/SpliceVariantFeature.java]
                + [StrandFeature.java][main/java/com/bio4j/model/relationships/features/StrandFeature.java]
                + [TopologicalDomainFeature.java][main/java/com/bio4j/model/relationships/features/TopologicalDomainFeature.java]
                + [TransitPeptideFeature.java][main/java/com/bio4j/model/relationships/features/TransitPeptideFeature.java]
                + [TransmembraneRegionFeature.java][main/java/com/bio4j/model/relationships/features/TransmembraneRegionFeature.java]
                + [TurnFeature.java][main/java/com/bio4j/model/relationships/features/TurnFeature.java]
                + [UnsureResidueFeature.java][main/java/com/bio4j/model/relationships/features/UnsureResidueFeature.java]
                + [ZincFingerRegionFeature.java][main/java/com/bio4j/model/relationships/features/ZincFingerRegionFeature.java]
              + go
                + [HasPartOfGo.java][main/java/com/bio4j/model/relationships/go/HasPartOfGo.java]
                + [IsAGo.java][main/java/com/bio4j/model/relationships/go/IsAGo.java]
                + [NegativelyRegulatesGo.java][main/java/com/bio4j/model/relationships/go/NegativelyRegulatesGo.java]
                + [PartOfGo.java][main/java/com/bio4j/model/relationships/go/PartOfGo.java]
                + [PositivelyRegulatesGo.java][main/java/com/bio4j/model/relationships/go/PositivelyRegulatesGo.java]
                + [RegulatesGo.java][main/java/com/bio4j/model/relationships/go/RegulatesGo.java]
              + [InstituteCountry.java][main/java/com/bio4j/model/relationships/InstituteCountry.java]
              + [IsoformEventGenerator.java][main/java/com/bio4j/model/relationships/IsoformEventGenerator.java]
              + ncbi
                + [NCBITaxon.java][main/java/com/bio4j/model/relationships/ncbi/NCBITaxon.java]
                + [NCBITaxonParent.java][main/java/com/bio4j/model/relationships/ncbi/NCBITaxonParent.java]
              + protein
                + [BasicProteinSequenceCaution.java][main/java/com/bio4j/model/relationships/protein/BasicProteinSequenceCaution.java]
                + [ProteinDataset.java][main/java/com/bio4j/model/relationships/protein/ProteinDataset.java]
                + [ProteinEnzymaticActivity.java][main/java/com/bio4j/model/relationships/protein/ProteinEnzymaticActivity.java]
                + [ProteinErroneousGeneModelPrediction.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousGeneModelPrediction.java]
                + [ProteinErroneousInitiation.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousInitiation.java]
                + [ProteinErroneousTermination.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousTermination.java]
                + [ProteinErroneousTranslation.java][main/java/com/bio4j/model/relationships/protein/ProteinErroneousTranslation.java]
                + [ProteinFrameshift.java][main/java/com/bio4j/model/relationships/protein/ProteinFrameshift.java]
                + [ProteinGenomeElement.java][main/java/com/bio4j/model/relationships/protein/ProteinGenomeElement.java]
                + [ProteinGo.java][main/java/com/bio4j/model/relationships/protein/ProteinGo.java]
                + [ProteinInterpro.java][main/java/com/bio4j/model/relationships/protein/ProteinInterpro.java]
                + [ProteinIsoform.java][main/java/com/bio4j/model/relationships/protein/ProteinIsoform.java]
                + [ProteinIsoformInteraction.java][main/java/com/bio4j/model/relationships/protein/ProteinIsoformInteraction.java]
                + [ProteinKeyword.java][main/java/com/bio4j/model/relationships/protein/ProteinKeyword.java]
                + [ProteinMiscellaneousDiscrepancy.java][main/java/com/bio4j/model/relationships/protein/ProteinMiscellaneousDiscrepancy.java]
                + [ProteinOrganism.java][main/java/com/bio4j/model/relationships/protein/ProteinOrganism.java]
                + [ProteinPfam.java][main/java/com/bio4j/model/relationships/protein/ProteinPfam.java]
                + [ProteinProteinInteraction.java][main/java/com/bio4j/model/relationships/protein/ProteinProteinInteraction.java]
                + [ProteinReactome.java][main/java/com/bio4j/model/relationships/protein/ProteinReactome.java]
                + [ProteinSubcellularLocation.java][main/java/com/bio4j/model/relationships/protein/ProteinSubcellularLocation.java]
              + refseq
                + [GenomeElementCDS.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementCDS.java]
                + [GenomeElementGene.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementGene.java]
                + [GenomeElementMiscRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementMiscRna.java]
                + [GenomeElementMRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementMRna.java]
                + [GenomeElementNcRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementNcRna.java]
                + [GenomeElementRRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementRRna.java]
                + [GenomeElementTmRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementTmRna.java]
                + [GenomeElementTRna.java][main/java/com/bio4j/model/relationships/refseq/GenomeElementTRna.java]
              + sc
                + [ErroneousGeneModelPrediction.java][main/java/com/bio4j/model/relationships/sc/ErroneousGeneModelPrediction.java]
                + [ErroneousInitiation.java][main/java/com/bio4j/model/relationships/sc/ErroneousInitiation.java]
                + [ErroneousTermination.java][main/java/com/bio4j/model/relationships/sc/ErroneousTermination.java]
                + [ErroneousTranslation.java][main/java/com/bio4j/model/relationships/sc/ErroneousTranslation.java]
                + [Frameshift.java][main/java/com/bio4j/model/relationships/sc/Frameshift.java]
                + [MiscellaneousDiscrepancy.java][main/java/com/bio4j/model/relationships/sc/MiscellaneousDiscrepancy.java]
              + [SubcellularLocationParent.java][main/java/com/bio4j/model/relationships/SubcellularLocationParent.java]
              + [TaxonParent.java][main/java/com/bio4j/model/relationships/TaxonParent.java]
              + uniref
                + [UniRef100Member.java][main/java/com/bio4j/model/relationships/uniref/UniRef100Member.java]
                + [Uniref50Member.java][main/java/com/bio4j/model/relationships/uniref/Uniref50Member.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/relationships/uniref/UniRef90Member.java]
            + util
              + [NodeRetriever.java][main/java/com/bio4j/model/util/NodeRetriever.java]

[main/java/com/bio4j/model/enums/UniprotDBXref.java]: UniprotDBXref.java.md
[main/java/com/bio4j/model/Node.java]: ../Node.java.md
[main/java/com/bio4j/model/nodes/AlternativeProduct.java]: ../nodes/AlternativeProduct.java.md
[main/java/com/bio4j/model/nodes/citation/Article.java]: ../nodes/citation/Article.java.md
[main/java/com/bio4j/model/nodes/citation/Book.java]: ../nodes/citation/Book.java.md
[main/java/com/bio4j/model/nodes/citation/DB.java]: ../nodes/citation/DB.java.md
[main/java/com/bio4j/model/nodes/citation/Journal.java]: ../nodes/citation/Journal.java.md
[main/java/com/bio4j/model/nodes/citation/OnlineArticle.java]: ../nodes/citation/OnlineArticle.java.md
[main/java/com/bio4j/model/nodes/citation/OnlineJournal.java]: ../nodes/citation/OnlineJournal.java.md
[main/java/com/bio4j/model/nodes/citation/Patent.java]: ../nodes/citation/Patent.java.md
[main/java/com/bio4j/model/nodes/citation/Publisher.java]: ../nodes/citation/Publisher.java.md
[main/java/com/bio4j/model/nodes/citation/Submission.java]: ../nodes/citation/Submission.java.md
[main/java/com/bio4j/model/nodes/citation/Thesis.java]: ../nodes/citation/Thesis.java.md
[main/java/com/bio4j/model/nodes/citation/UnpublishedObservation.java]: ../nodes/citation/UnpublishedObservation.java.md
[main/java/com/bio4j/model/nodes/City.java]: ../nodes/City.java.md
[main/java/com/bio4j/model/nodes/CommentType.java]: ../nodes/CommentType.java.md
[main/java/com/bio4j/model/nodes/Consortium.java]: ../nodes/Consortium.java.md
[main/java/com/bio4j/model/nodes/Country.java]: ../nodes/Country.java.md
[main/java/com/bio4j/model/nodes/Dataset.java]: ../nodes/Dataset.java.md
[main/java/com/bio4j/model/nodes/Enzyme.java]: ../nodes/Enzyme.java.md
[main/java/com/bio4j/model/nodes/FeatureType.java]: ../nodes/FeatureType.java.md
[main/java/com/bio4j/model/nodes/GoTerm.java]: ../nodes/GoTerm.java.md
[main/java/com/bio4j/model/nodes/Institute.java]: ../nodes/Institute.java.md
[main/java/com/bio4j/model/nodes/Interpro.java]: ../nodes/Interpro.java.md
[main/java/com/bio4j/model/nodes/Isoform.java]: ../nodes/Isoform.java.md
[main/java/com/bio4j/model/nodes/Keyword.java]: ../nodes/Keyword.java.md
[main/java/com/bio4j/model/nodes/ncbi/NCBITaxon.java]: ../nodes/ncbi/NCBITaxon.java.md
[main/java/com/bio4j/model/nodes/Organism.java]: ../nodes/Organism.java.md
[main/java/com/bio4j/model/nodes/Person.java]: ../nodes/Person.java.md
[main/java/com/bio4j/model/nodes/Pfam.java]: ../nodes/Pfam.java.md
[main/java/com/bio4j/model/nodes/Protein.java]: ../nodes/Protein.java.md
[main/java/com/bio4j/model/nodes/reactome/ReactomeTerm.java]: ../nodes/reactome/ReactomeTerm.java.md
[main/java/com/bio4j/model/nodes/refseq/CDS.java]: ../nodes/refseq/CDS.java.md
[main/java/com/bio4j/model/nodes/refseq/Gene.java]: ../nodes/refseq/Gene.java.md
[main/java/com/bio4j/model/nodes/refseq/GenomeElement.java]: ../nodes/refseq/GenomeElement.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/MiscRNA.java]: ../nodes/refseq/rna/MiscRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/MRNA.java]: ../nodes/refseq/rna/MRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/NcRNA.java]: ../nodes/refseq/rna/NcRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/RNA.java]: ../nodes/refseq/rna/RNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/RRNA.java]: ../nodes/refseq/rna/RRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/TmRNA.java]: ../nodes/refseq/rna/TmRNA.java.md
[main/java/com/bio4j/model/nodes/refseq/rna/TRNA.java]: ../nodes/refseq/rna/TRNA.java.md
[main/java/com/bio4j/model/nodes/SequenceCaution.java]: ../nodes/SequenceCaution.java.md
[main/java/com/bio4j/model/nodes/SubcellularLocation.java]: ../nodes/SubcellularLocation.java.md
[main/java/com/bio4j/model/nodes/Taxon.java]: ../nodes/Taxon.java.md
[main/java/com/bio4j/model/Relationship.java]: ../Relationship.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductInitiation.java]: ../relationships/aproducts/AlternativeProductInitiation.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductPromoter.java]: ../relationships/aproducts/AlternativeProductPromoter.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductRibosomalFrameshifting.java]: ../relationships/aproducts/AlternativeProductRibosomalFrameshifting.java.md
[main/java/com/bio4j/model/relationships/aproducts/AlternativeProductSplicing.java]: ../relationships/aproducts/AlternativeProductSplicing.java.md
[main/java/com/bio4j/model/relationships/citation/article/ArticleAuthor.java]: ../relationships/citation/article/ArticleAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/article/ArticleJournal.java]: ../relationships/citation/article/ArticleJournal.java.md
[main/java/com/bio4j/model/relationships/citation/article/ArticleProteinCitation.java]: ../relationships/citation/article/ArticleProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookAuthor.java]: ../relationships/citation/book/BookAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookCity.java]: ../relationships/citation/book/BookCity.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookEditor.java]: ../relationships/citation/book/BookEditor.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookProteinCitation.java]: ../relationships/citation/book/BookProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/book/BookPublisher.java]: ../relationships/citation/book/BookPublisher.java.md
[main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleAuthor.java]: ../relationships/citation/onarticle/OnlineArticleAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleJournal.java]: ../relationships/citation/onarticle/OnlineArticleJournal.java.md
[main/java/com/bio4j/model/relationships/citation/onarticle/OnlineArticleProteinCitation.java]: ../relationships/citation/onarticle/OnlineArticleProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/patent/PatentAuthor.java]: ../relationships/citation/patent/PatentAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/patent/PatentProteinCitation.java]: ../relationships/citation/patent/PatentProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/submission/SubmissionAuthor.java]: ../relationships/citation/submission/SubmissionAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/submission/SubmissionDb.java]: ../relationships/citation/submission/SubmissionDb.java.md
[main/java/com/bio4j/model/relationships/citation/submission/SubmissionProteinCitation.java]: ../relationships/citation/submission/SubmissionProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/thesis/ThesisAuthor.java]: ../relationships/citation/thesis/ThesisAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/thesis/ThesisInstitute.java]: ../relationships/citation/thesis/ThesisInstitute.java.md
[main/java/com/bio4j/model/relationships/citation/thesis/ThesisProteinCitation.java]: ../relationships/citation/thesis/ThesisProteinCitation.java.md
[main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationAuthor.java]: ../relationships/citation/uo/UnpublishedObservationAuthor.java.md
[main/java/com/bio4j/model/relationships/citation/uo/UnpublishedObservationProteinCitation.java]: ../relationships/citation/uo/UnpublishedObservationProteinCitation.java.md
[main/java/com/bio4j/model/relationships/comment/AllergenComment.java]: ../relationships/comment/AllergenComment.java.md
[main/java/com/bio4j/model/relationships/comment/BasicComment.java]: ../relationships/comment/BasicComment.java.md
[main/java/com/bio4j/model/relationships/comment/BioPhysicoChemicalPropertiesComment.java]: ../relationships/comment/BioPhysicoChemicalPropertiesComment.java.md
[main/java/com/bio4j/model/relationships/comment/BiotechnologyComment.java]: ../relationships/comment/BiotechnologyComment.java.md
[main/java/com/bio4j/model/relationships/comment/CatalyticActivityComment.java]: ../relationships/comment/CatalyticActivityComment.java.md
[main/java/com/bio4j/model/relationships/comment/CautionComment.java]: ../relationships/comment/CautionComment.java.md
[main/java/com/bio4j/model/relationships/comment/CofactorComment.java]: ../relationships/comment/CofactorComment.java.md
[main/java/com/bio4j/model/relationships/comment/DevelopmentalStageComment.java]: ../relationships/comment/DevelopmentalStageComment.java.md
[main/java/com/bio4j/model/relationships/comment/DiseaseComment.java]: ../relationships/comment/DiseaseComment.java.md
[main/java/com/bio4j/model/relationships/comment/DisruptionPhenotypeComment.java]: ../relationships/comment/DisruptionPhenotypeComment.java.md
[main/java/com/bio4j/model/relationships/comment/DomainComment.java]: ../relationships/comment/DomainComment.java.md
[main/java/com/bio4j/model/relationships/comment/EnzymeRegulationComment.java]: ../relationships/comment/EnzymeRegulationComment.java.md
[main/java/com/bio4j/model/relationships/comment/FunctionComment.java]: ../relationships/comment/FunctionComment.java.md
[main/java/com/bio4j/model/relationships/comment/InductionComment.java]: ../relationships/comment/InductionComment.java.md
[main/java/com/bio4j/model/relationships/comment/MassSpectometryComment.java]: ../relationships/comment/MassSpectometryComment.java.md
[main/java/com/bio4j/model/relationships/comment/MiscellaneousComment.java]: ../relationships/comment/MiscellaneousComment.java.md
[main/java/com/bio4j/model/relationships/comment/OnlineInformationComment.java]: ../relationships/comment/OnlineInformationComment.java.md
[main/java/com/bio4j/model/relationships/comment/PathwayComment.java]: ../relationships/comment/PathwayComment.java.md
[main/java/com/bio4j/model/relationships/comment/PharmaceuticalComment.java]: ../relationships/comment/PharmaceuticalComment.java.md
[main/java/com/bio4j/model/relationships/comment/PolymorphismComment.java]: ../relationships/comment/PolymorphismComment.java.md
[main/java/com/bio4j/model/relationships/comment/PostTransactionalModificationComment.java]: ../relationships/comment/PostTransactionalModificationComment.java.md
[main/java/com/bio4j/model/relationships/comment/RnaEditingComment.java]: ../relationships/comment/RnaEditingComment.java.md
[main/java/com/bio4j/model/relationships/comment/SimilarityComment.java]: ../relationships/comment/SimilarityComment.java.md
[main/java/com/bio4j/model/relationships/comment/SubunitComment.java]: ../relationships/comment/SubunitComment.java.md
[main/java/com/bio4j/model/relationships/comment/TissueSpecificityComment.java]: ../relationships/comment/TissueSpecificityComment.java.md
[main/java/com/bio4j/model/relationships/comment/ToxicDoseComment.java]: ../relationships/comment/ToxicDoseComment.java.md
[main/java/com/bio4j/model/relationships/features/ActiveSiteFeature.java]: ../relationships/features/ActiveSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/BasicFeature.java]: ../relationships/features/BasicFeature.java.md
[main/java/com/bio4j/model/relationships/features/BindingSiteFeature.java]: ../relationships/features/BindingSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/CalciumBindingRegionFeature.java]: ../relationships/features/CalciumBindingRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/ChainFeature.java]: ../relationships/features/ChainFeature.java.md
[main/java/com/bio4j/model/relationships/features/CoiledCoilRegionFeature.java]: ../relationships/features/CoiledCoilRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/CompositionallyBiasedRegionFeature.java]: ../relationships/features/CompositionallyBiasedRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/CrossLinkFeature.java]: ../relationships/features/CrossLinkFeature.java.md
[main/java/com/bio4j/model/relationships/features/DisulfideBondFeature.java]: ../relationships/features/DisulfideBondFeature.java.md
[main/java/com/bio4j/model/relationships/features/DnaBindingFeature.java]: ../relationships/features/DnaBindingFeature.java.md
[main/java/com/bio4j/model/relationships/features/DomainFeature.java]: ../relationships/features/DomainFeature.java.md
[main/java/com/bio4j/model/relationships/features/GlycosylationSiteFeature.java]: ../relationships/features/GlycosylationSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/HelixFeature.java]: ../relationships/features/HelixFeature.java.md
[main/java/com/bio4j/model/relationships/features/InitiatorMethionineFeature.java]: ../relationships/features/InitiatorMethionineFeature.java.md
[main/java/com/bio4j/model/relationships/features/IntramembraneRegionFeature.java]: ../relationships/features/IntramembraneRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/LipidMoietyBindingRegionFeature.java]: ../relationships/features/LipidMoietyBindingRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/MetalIonBindingSiteFeature.java]: ../relationships/features/MetalIonBindingSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/ModifiedResidueFeature.java]: ../relationships/features/ModifiedResidueFeature.java.md
[main/java/com/bio4j/model/relationships/features/MutagenesisSiteFeature.java]: ../relationships/features/MutagenesisSiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/NonConsecutiveResiduesFeature.java]: ../relationships/features/NonConsecutiveResiduesFeature.java.md
[main/java/com/bio4j/model/relationships/features/NonStandardAminoAcidFeature.java]: ../relationships/features/NonStandardAminoAcidFeature.java.md
[main/java/com/bio4j/model/relationships/features/NonTerminalResidueFeature.java]: ../relationships/features/NonTerminalResidueFeature.java.md
[main/java/com/bio4j/model/relationships/features/NucleotidePhosphateBindingRegionFeature.java]: ../relationships/features/NucleotidePhosphateBindingRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/PeptideFeature.java]: ../relationships/features/PeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/PropeptideFeature.java]: ../relationships/features/PropeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/RegionOfInterestFeature.java]: ../relationships/features/RegionOfInterestFeature.java.md
[main/java/com/bio4j/model/relationships/features/RepeatFeature.java]: ../relationships/features/RepeatFeature.java.md
[main/java/com/bio4j/model/relationships/features/SequenceConflictFeature.java]: ../relationships/features/SequenceConflictFeature.java.md
[main/java/com/bio4j/model/relationships/features/SequenceVariantFeature.java]: ../relationships/features/SequenceVariantFeature.java.md
[main/java/com/bio4j/model/relationships/features/ShortSequenceMotifFeature.java]: ../relationships/features/ShortSequenceMotifFeature.java.md
[main/java/com/bio4j/model/relationships/features/SignalPeptideFeature.java]: ../relationships/features/SignalPeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/SiteFeature.java]: ../relationships/features/SiteFeature.java.md
[main/java/com/bio4j/model/relationships/features/SpliceVariantFeature.java]: ../relationships/features/SpliceVariantFeature.java.md
[main/java/com/bio4j/model/relationships/features/StrandFeature.java]: ../relationships/features/StrandFeature.java.md
[main/java/com/bio4j/model/relationships/features/TopologicalDomainFeature.java]: ../relationships/features/TopologicalDomainFeature.java.md
[main/java/com/bio4j/model/relationships/features/TransitPeptideFeature.java]: ../relationships/features/TransitPeptideFeature.java.md
[main/java/com/bio4j/model/relationships/features/TransmembraneRegionFeature.java]: ../relationships/features/TransmembraneRegionFeature.java.md
[main/java/com/bio4j/model/relationships/features/TurnFeature.java]: ../relationships/features/TurnFeature.java.md
[main/java/com/bio4j/model/relationships/features/UnsureResidueFeature.java]: ../relationships/features/UnsureResidueFeature.java.md
[main/java/com/bio4j/model/relationships/features/ZincFingerRegionFeature.java]: ../relationships/features/ZincFingerRegionFeature.java.md
[main/java/com/bio4j/model/relationships/go/HasPartOfGo.java]: ../relationships/go/HasPartOfGo.java.md
[main/java/com/bio4j/model/relationships/go/IsAGo.java]: ../relationships/go/IsAGo.java.md
[main/java/com/bio4j/model/relationships/go/NegativelyRegulatesGo.java]: ../relationships/go/NegativelyRegulatesGo.java.md
[main/java/com/bio4j/model/relationships/go/PartOfGo.java]: ../relationships/go/PartOfGo.java.md
[main/java/com/bio4j/model/relationships/go/PositivelyRegulatesGo.java]: ../relationships/go/PositivelyRegulatesGo.java.md
[main/java/com/bio4j/model/relationships/go/RegulatesGo.java]: ../relationships/go/RegulatesGo.java.md
[main/java/com/bio4j/model/relationships/InstituteCountry.java]: ../relationships/InstituteCountry.java.md
[main/java/com/bio4j/model/relationships/IsoformEventGenerator.java]: ../relationships/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/relationships/ncbi/NCBITaxon.java]: ../relationships/ncbi/NCBITaxon.java.md
[main/java/com/bio4j/model/relationships/ncbi/NCBITaxonParent.java]: ../relationships/ncbi/NCBITaxonParent.java.md
[main/java/com/bio4j/model/relationships/protein/BasicProteinSequenceCaution.java]: ../relationships/protein/BasicProteinSequenceCaution.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinDataset.java]: ../relationships/protein/ProteinDataset.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinEnzymaticActivity.java]: ../relationships/protein/ProteinEnzymaticActivity.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousGeneModelPrediction.java]: ../relationships/protein/ProteinErroneousGeneModelPrediction.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousInitiation.java]: ../relationships/protein/ProteinErroneousInitiation.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousTermination.java]: ../relationships/protein/ProteinErroneousTermination.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinErroneousTranslation.java]: ../relationships/protein/ProteinErroneousTranslation.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinFrameshift.java]: ../relationships/protein/ProteinFrameshift.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinGenomeElement.java]: ../relationships/protein/ProteinGenomeElement.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinGo.java]: ../relationships/protein/ProteinGo.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinInterpro.java]: ../relationships/protein/ProteinInterpro.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinIsoform.java]: ../relationships/protein/ProteinIsoform.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinIsoformInteraction.java]: ../relationships/protein/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinKeyword.java]: ../relationships/protein/ProteinKeyword.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinMiscellaneousDiscrepancy.java]: ../relationships/protein/ProteinMiscellaneousDiscrepancy.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinOrganism.java]: ../relationships/protein/ProteinOrganism.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinPfam.java]: ../relationships/protein/ProteinPfam.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinProteinInteraction.java]: ../relationships/protein/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinReactome.java]: ../relationships/protein/ProteinReactome.java.md
[main/java/com/bio4j/model/relationships/protein/ProteinSubcellularLocation.java]: ../relationships/protein/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementCDS.java]: ../relationships/refseq/GenomeElementCDS.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementGene.java]: ../relationships/refseq/GenomeElementGene.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementMiscRna.java]: ../relationships/refseq/GenomeElementMiscRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementMRna.java]: ../relationships/refseq/GenomeElementMRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementNcRna.java]: ../relationships/refseq/GenomeElementNcRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementRRna.java]: ../relationships/refseq/GenomeElementRRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementTmRna.java]: ../relationships/refseq/GenomeElementTmRna.java.md
[main/java/com/bio4j/model/relationships/refseq/GenomeElementTRna.java]: ../relationships/refseq/GenomeElementTRna.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousGeneModelPrediction.java]: ../relationships/sc/ErroneousGeneModelPrediction.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousInitiation.java]: ../relationships/sc/ErroneousInitiation.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousTermination.java]: ../relationships/sc/ErroneousTermination.java.md
[main/java/com/bio4j/model/relationships/sc/ErroneousTranslation.java]: ../relationships/sc/ErroneousTranslation.java.md
[main/java/com/bio4j/model/relationships/sc/Frameshift.java]: ../relationships/sc/Frameshift.java.md
[main/java/com/bio4j/model/relationships/sc/MiscellaneousDiscrepancy.java]: ../relationships/sc/MiscellaneousDiscrepancy.java.md
[main/java/com/bio4j/model/relationships/SubcellularLocationParent.java]: ../relationships/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/relationships/TaxonParent.java]: ../relationships/TaxonParent.java.md
[main/java/com/bio4j/model/relationships/uniref/UniRef100Member.java]: ../relationships/uniref/UniRef100Member.java.md
[main/java/com/bio4j/model/relationships/uniref/Uniref50Member.java]: ../relationships/uniref/Uniref50Member.java.md
[main/java/com/bio4j/model/relationships/uniref/UniRef90Member.java]: ../relationships/uniref/UniRef90Member.java.md
[main/java/com/bio4j/model/util/NodeRetriever.java]: ../util/NodeRetriever.java.md
