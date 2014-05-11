
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
            + target
            + isoforms
              + relationships
                + [AlternativeProductInitiation.java][main/java/com/bio4j/model/isoforms/relationships/AlternativeProductInitiation.java]
                + [AlternativeProductRibosomalFrameshifting.java][main/java/com/bio4j/model/isoforms/relationships/AlternativeProductRibosomalFrameshifting.java]
                + [AlternativeProductSplicing.java][main/java/com/bio4j/model/isoforms/relationships/AlternativeProductSplicing.java]
                + [IsoformEventGenerator.java][main/java/com/bio4j/model/isoforms/relationships/IsoformEventGenerator.java]
                + [AlternativeProductPromoter.java][main/java/com/bio4j/model/isoforms/relationships/AlternativeProductPromoter.java]
              + nodes
                + [AlternativeProduct.java][main/java/com/bio4j/model/isoforms/nodes/AlternativeProduct.java]
                + [Isoform.java][main/java/com/bio4j/model/isoforms/nodes/Isoform.java]
              + [IsoformsModule.java][main/java/com/bio4j/model/isoforms/IsoformsModule.java]
            + uniref
              + [UniRefModule.java][main/java/com/bio4j/model/uniref/UniRefModule.java]
              + relationships
                + [UniRef100Member.java][main/java/com/bio4j/model/uniref/relationships/UniRef100Member.java]
                + [UniRef50Member.java][main/java/com/bio4j/model/uniref/relationships/UniRef50Member.java]
                + [UniRef90Member.java][main/java/com/bio4j/model/uniref/relationships/UniRef90Member.java]
            + enzymedb
              + indexes
                + [ById.java][main/java/com/bio4j/model/enzymedb/indexes/ById.java]
              + [EnzymeDBModule.java][main/java/com/bio4j/model/enzymedb/EnzymeDBModule.java]
              + relationships
                + [EnzymaticActivity.java][main/java/com/bio4j/model/enzymedb/relationships/EnzymaticActivity.java]
              + nodes
                + [Enzyme.java][main/java/com/bio4j/model/enzymedb/nodes/Enzyme.java]
            + properties
              + [DoId.java][main/java/com/bio4j/model/properties/DoId.java]
              + [NCBITaxonomyId.java][main/java/com/bio4j/model/properties/NCBITaxonomyId.java]
              + [GeneNames.java][main/java/com/bio4j/model/properties/GeneNames.java]
              + [AlternativeIds.java][main/java/com/bio4j/model/properties/AlternativeIds.java]
              + [Obsolete.java][main/java/com/bio4j/model/properties/Obsolete.java]
              + [Name.java][main/java/com/bio4j/model/properties/Name.java]
              + [PubmedId.java][main/java/com/bio4j/model/properties/PubmedId.java]
              + [Sequence.java][main/java/com/bio4j/model/properties/Sequence.java]
              + [ScientificName.java][main/java/com/bio4j/model/properties/ScientificName.java]
              + [TaxId.java][main/java/com/bio4j/model/properties/TaxId.java]
              + [FullName.java][main/java/com/bio4j/model/properties/FullName.java]
              + [ShortName.java][main/java/com/bio4j/model/properties/ShortName.java]
              + [Definition.java][main/java/com/bio4j/model/properties/Definition.java]
              + [Locator.java][main/java/com/bio4j/model/properties/Locator.java]
              + [Positions.java][main/java/com/bio4j/model/properties/Positions.java]
              + [AlternativeAccessions.java][main/java/com/bio4j/model/properties/AlternativeAccessions.java]
              + [Status.java][main/java/com/bio4j/model/properties/Status.java]
              + [PathwayName.java][main/java/com/bio4j/model/properties/PathwayName.java]
              + [TaxonomicRank.java][main/java/com/bio4j/model/properties/TaxonomicRank.java]
              + [PrositeCrossReferences.java][main/java/com/bio4j/model/properties/PrositeCrossReferences.java]
              + [ModifiedDate.java][main/java/com/bio4j/model/properties/ModifiedDate.java]
              + [AlternateNames.java][main/java/com/bio4j/model/properties/AlternateNames.java]
              + [Version.java][main/java/com/bio4j/model/properties/Version.java]
              + [OfficialName.java][main/java/com/bio4j/model/properties/OfficialName.java]
              + [Evidence.java][main/java/com/bio4j/model/properties/Evidence.java]
              + [Id.java][main/java/com/bio4j/model/properties/Id.java]
              + [Accession.java][main/java/com/bio4j/model/properties/Accession.java]
              + [Comment.java][main/java/com/bio4j/model/properties/Comment.java]
              + [Cofactors.java][main/java/com/bio4j/model/properties/Cofactors.java]
              + [SynonymName.java][main/java/com/bio4j/model/properties/SynonymName.java]
              + [Number.java][main/java/com/bio4j/model/properties/Number.java]
              + [Institute.java][main/java/com/bio4j/model/properties/Institute.java]
              + [Experiments.java][main/java/com/bio4j/model/properties/Experiments.java]
              + [Length.java][main/java/com/bio4j/model/properties/Length.java]
              + [CommonName.java][main/java/com/bio4j/model/properties/CommonName.java]
              + [EmblCode.java][main/java/com/bio4j/model/properties/EmblCode.java]
              + [Last.java][main/java/com/bio4j/model/properties/Last.java]
              + [Mass.java][main/java/com/bio4j/model/properties/Mass.java]
              + [Date.java][main/java/com/bio4j/model/properties/Date.java]
              + [Title.java][main/java/com/bio4j/model/properties/Title.java]
              + [CatalyticActivity.java][main/java/com/bio4j/model/properties/CatalyticActivity.java]
              + [First.java][main/java/com/bio4j/model/properties/First.java]
              + [Volume.java][main/java/com/bio4j/model/properties/Volume.java]
              + [MedlineId.java][main/java/com/bio4j/model/properties/MedlineId.java]
              + [Note.java][main/java/com/bio4j/model/properties/Note.java]
              + [Country.java][main/java/com/bio4j/model/properties/Country.java]
            + enums
              + [UniprotDBXref.java][main/java/com/bio4j/model/enums/UniprotDBXref.java]
            + ncbiTaxonomy
              + indexes
                + [ById.java][main/java/com/bio4j/model/ncbiTaxonomy/indexes/ById.java]
              + relationships
                + [Parent.java][main/java/com/bio4j/model/ncbiTaxonomy/relationships/Parent.java]
              + nodes
                + [NCBITaxon.java][main/java/com/bio4j/model/ncbiTaxonomy/nodes/NCBITaxon.java]
              + [NcbiTaxonomyModule.java][main/java/com/bio4j/model/ncbiTaxonomy/NcbiTaxonomyModule.java]
            + go
              + [GoModule.java][main/java/com/bio4j/model/go/GoModule.java]
              + indexes
                + [ById.java][main/java/com/bio4j/model/go/indexes/ById.java]
              + relationships
                + [Term.java][main/java/com/bio4j/model/go/relationships/Term.java]
                + [PositivelyRegulates.java][main/java/com/bio4j/model/go/relationships/PositivelyRegulates.java]
                + [HasPartOf.java][main/java/com/bio4j/model/go/relationships/HasPartOf.java]
                + [Regulates.java][main/java/com/bio4j/model/go/relationships/Regulates.java]
                + [PartOf.java][main/java/com/bio4j/model/go/relationships/PartOf.java]
                + [IsA.java][main/java/com/bio4j/model/go/relationships/IsA.java]
                + [NegativelyRegulates.java][main/java/com/bio4j/model/go/relationships/NegativelyRegulates.java]
              + nodes
                + [MolecularFunction.java][main/java/com/bio4j/model/go/nodes/MolecularFunction.java]
                + [GoTerm.java][main/java/com/bio4j/model/go/nodes/GoTerm.java]
                + [GoNamespace.java][main/java/com/bio4j/model/go/nodes/GoNamespace.java]
            + util
              + [OnlineJournalRetriever.java][main/java/com/bio4j/model/util/OnlineJournalRetriever.java]
              + [PfamRetriever.java][main/java/com/bio4j/model/util/PfamRetriever.java]
              + [SubmissionRetriever.java][main/java/com/bio4j/model/util/SubmissionRetriever.java]
              + [ArticleRetriever.java][main/java/com/bio4j/model/util/ArticleRetriever.java]
              + [IsoformRetriever.java][main/java/com/bio4j/model/util/IsoformRetriever.java]
              + [InterproRetriever.java][main/java/com/bio4j/model/util/InterproRetriever.java]
              + [PublisherRetriever.java][main/java/com/bio4j/model/util/PublisherRetriever.java]
              + [ConsortiumRetriever.java][main/java/com/bio4j/model/util/ConsortiumRetriever.java]
              + [CountryRetriever.java][main/java/com/bio4j/model/util/CountryRetriever.java]
              + [TaxonRetriever.java][main/java/com/bio4j/model/util/TaxonRetriever.java]
              + [ByTaxId.java][main/java/com/bio4j/model/util/ByTaxId.java]
              + [OrganismRetriever.java][main/java/com/bio4j/model/util/OrganismRetriever.java]
              + [AlternativeProductRetriever.java][main/java/com/bio4j/model/util/AlternativeProductRetriever.java]
              + [DBRetriever.java][main/java/com/bio4j/model/util/DBRetriever.java]
              + [NodeIndex.java][main/java/com/bio4j/model/util/NodeIndex.java]
              + [NCBITaxonRetriever.java][main/java/com/bio4j/model/util/NCBITaxonRetriever.java]
              + [SequenceCautionRetriever.java][main/java/com/bio4j/model/util/SequenceCautionRetriever.java]
              + [RelationshipRetriever.java][main/java/com/bio4j/model/util/RelationshipRetriever.java]
              + [EnzymeRetriever.java][main/java/com/bio4j/model/util/EnzymeRetriever.java]
              + [ProteinRetriever.java][main/java/com/bio4j/model/util/ProteinRetriever.java]
              + [GoTermRetriever.java][main/java/com/bio4j/model/util/GoTermRetriever.java]
              + [PersonRetriever.java][main/java/com/bio4j/model/util/PersonRetriever.java]
              + [PatentRetriever.java][main/java/com/bio4j/model/util/PatentRetriever.java]
              + [GenomeElementRetriever.java][main/java/com/bio4j/model/util/GenomeElementRetriever.java]
              + [ThesisRetriever.java][main/java/com/bio4j/model/util/ThesisRetriever.java]
              + [CityRetriever.java][main/java/com/bio4j/model/util/CityRetriever.java]
              + [NodeRetriever.java][main/java/com/bio4j/model/util/NodeRetriever.java]
              + [FeatureTypeRetriever.java][main/java/com/bio4j/model/util/FeatureTypeRetriever.java]
              + [InstituteRetriever.java][main/java/com/bio4j/model/util/InstituteRetriever.java]
              + [CommentTypeRetriever.java][main/java/com/bio4j/model/util/CommentTypeRetriever.java]
              + [OnlineArticleRetriever.java][main/java/com/bio4j/model/util/OnlineArticleRetriever.java]
              + [DatasetRetriever.java][main/java/com/bio4j/model/util/DatasetRetriever.java]
              + [KeywordRetriever.java][main/java/com/bio4j/model/util/KeywordRetriever.java]
              + [SubcellularLocationRetriever.java][main/java/com/bio4j/model/util/SubcellularLocationRetriever.java]
              + [JournalRetriever.java][main/java/com/bio4j/model/util/JournalRetriever.java]
              + [BookRetriever.java][main/java/com/bio4j/model/util/BookRetriever.java]
              + [ReactomeTermRetriever.java][main/java/com/bio4j/model/util/ReactomeTermRetriever.java]
            + uniprot
              + [UniProtModule.java][main/java/com/bio4j/model/uniprot/UniProtModule.java]
              + relationships
                + [OnlineArticleJournal.java][main/java/com/bio4j/model/uniprot/relationships/OnlineArticleJournal.java]
                + [SignalPeptideFeature.java][main/java/com/bio4j/model/uniprot/relationships/SignalPeptideFeature.java]
                + [BookCity.java][main/java/com/bio4j/model/uniprot/relationships/BookCity.java]
                + [NonStandardAminoAcidFeature.java][main/java/com/bio4j/model/uniprot/relationships/NonStandardAminoAcidFeature.java]
                + [SpliceVariantFeature.java][main/java/com/bio4j/model/uniprot/relationships/SpliceVariantFeature.java]
                + [TransitPeptideFeature.java][main/java/com/bio4j/model/uniprot/relationships/TransitPeptideFeature.java]
                + [IntramembraneRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/IntramembraneRegionFeature.java]
                + [OnlineInformationComment.java][main/java/com/bio4j/model/uniprot/relationships/OnlineInformationComment.java]
                + [ChainFeature.java][main/java/com/bio4j/model/uniprot/relationships/ChainFeature.java]
                + [ProteinFrameshift.java][main/java/com/bio4j/model/uniprot/relationships/ProteinFrameshift.java]
                + [PeptideFeature.java][main/java/com/bio4j/model/uniprot/relationships/PeptideFeature.java]
                + [CautionComment.java][main/java/com/bio4j/model/uniprot/relationships/CautionComment.java]
                + [ZincFingerRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/ZincFingerRegionFeature.java]
                + [ProteinKeyword.java][main/java/com/bio4j/model/uniprot/relationships/ProteinKeyword.java]
                + [FunctionComment.java][main/java/com/bio4j/model/uniprot/relationships/FunctionComment.java]
                + [ErroneousTranslation.java][main/java/com/bio4j/model/uniprot/relationships/ErroneousTranslation.java]
                + [CalciumBindingRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/CalciumBindingRegionFeature.java]
                + [ErroneousTermination.java][main/java/com/bio4j/model/uniprot/relationships/ErroneousTermination.java]
                + [HelixFeature.java][main/java/com/bio4j/model/uniprot/relationships/HelixFeature.java]
                + [BasicCommentType.java][main/java/com/bio4j/model/uniprot/relationships/BasicCommentType.java]
                + [ProteinDataset.java][main/java/com/bio4j/model/uniprot/relationships/ProteinDataset.java]
                + [ProteinEnzymaticActivity.java][main/java/com/bio4j/model/uniprot/relationships/ProteinEnzymaticActivity.java]
                + [SequenceConflictFeature.java][main/java/com/bio4j/model/uniprot/relationships/SequenceConflictFeature.java]
                + [SimilarityComment.java][main/java/com/bio4j/model/uniprot/relationships/SimilarityComment.java]
                + [TaxonParent.java][main/java/com/bio4j/model/uniprot/relationships/TaxonParent.java]
                + [DnaBindingFeature.java][main/java/com/bio4j/model/uniprot/relationships/DnaBindingFeature.java]
                + [SiteFeature.java][main/java/com/bio4j/model/uniprot/relationships/SiteFeature.java]
                + [TransmembraneRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/TransmembraneRegionFeature.java]
                + [BiotechnologyComment.java][main/java/com/bio4j/model/uniprot/relationships/BiotechnologyComment.java]
                + [UnpublishedObservationAuthor.java][main/java/com/bio4j/model/uniprot/relationships/UnpublishedObservationAuthor.java]
                + [NucleotidePhosphateBindingRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/NucleotidePhosphateBindingRegionFeature.java]
                + [NonTerminalResidueFeature.java][main/java/com/bio4j/model/uniprot/relationships/NonTerminalResidueFeature.java]
                + [BookPublisher.java][main/java/com/bio4j/model/uniprot/relationships/BookPublisher.java]
                + [InstituteCountry.java][main/java/com/bio4j/model/uniprot/relationships/InstituteCountry.java]
                + [ProteinReactome.java][main/java/com/bio4j/model/uniprot/relationships/ProteinReactome.java]
                + references
                  + [Article.java][main/java/com/bio4j/model/uniprot/relationships/references/Article.java]
                  + [Book.java][main/java/com/bio4j/model/uniprot/relationships/references/Book.java]
                  + [OnlineArticle.java][main/java/com/bio4j/model/uniprot/relationships/references/OnlineArticle.java]
                  + [Person.java][main/java/com/bio4j/model/uniprot/relationships/references/Person.java]
                  + [Thesis.java][main/java/com/bio4j/model/uniprot/relationships/references/Thesis.java]
                  + [Submission.java][main/java/com/bio4j/model/uniprot/relationships/references/Submission.java]
                  + [Published.java][main/java/com/bio4j/model/uniprot/relationships/references/Published.java]
                  + [Cited.java][main/java/com/bio4j/model/uniprot/relationships/references/Cited.java]
                  + [Consortium.java][main/java/com/bio4j/model/uniprot/relationships/references/Consortium.java]
                  + [Patent.java][main/java/com/bio4j/model/uniprot/relationships/references/Patent.java]
                  + [UnpublishedObservation.java][main/java/com/bio4j/model/uniprot/relationships/references/UnpublishedObservation.java]
                  + [Journal.java][main/java/com/bio4j/model/uniprot/relationships/references/Journal.java]
                + [BookEditor.java][main/java/com/bio4j/model/uniprot/relationships/BookEditor.java]
                + [SubmissionProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/SubmissionProteinCitation.java]
                + [BasicFeatureType.java][main/java/com/bio4j/model/uniprot/relationships/BasicFeatureType.java]
                + [PatentAuthor.java][main/java/com/bio4j/model/uniprot/relationships/PatentAuthor.java]
                + [ProteinOrganism.java][main/java/com/bio4j/model/uniprot/relationships/ProteinOrganism.java]
                + [TurnFeature.java][main/java/com/bio4j/model/uniprot/relationships/TurnFeature.java]
                + [TissueSpecificityComment.java][main/java/com/bio4j/model/uniprot/relationships/TissueSpecificityComment.java]
                + [LipidMoietyBindingRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/LipidMoietyBindingRegionFeature.java]
                + [DevelopmentalStageComment.java][main/java/com/bio4j/model/uniprot/relationships/DevelopmentalStageComment.java]
                + [ProteinErroneousInitiation.java][main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousInitiation.java]
                + [OnlineArticleAuthor.java][main/java/com/bio4j/model/uniprot/relationships/OnlineArticleAuthor.java]
                + [BindingSiteFeature.java][main/java/com/bio4j/model/uniprot/relationships/BindingSiteFeature.java]
                + [InitiatorMethionineFeature.java][main/java/com/bio4j/model/uniprot/relationships/InitiatorMethionineFeature.java]
                + [ThesisAuthor.java][main/java/com/bio4j/model/uniprot/relationships/ThesisAuthor.java]
                + [ProteinPfam.java][main/java/com/bio4j/model/uniprot/relationships/ProteinPfam.java]
                + [PropeptideFeature.java][main/java/com/bio4j/model/uniprot/relationships/PropeptideFeature.java]
                + [EnzymeRegulationComment.java][main/java/com/bio4j/model/uniprot/relationships/EnzymeRegulationComment.java]
                + [SequenceVariantFeature.java][main/java/com/bio4j/model/uniprot/relationships/SequenceVariantFeature.java]
                + [MutagenesisSiteFeature.java][main/java/com/bio4j/model/uniprot/relationships/MutagenesisSiteFeature.java]
                + [TopologicalDomainFeature.java][main/java/com/bio4j/model/uniprot/relationships/TopologicalDomainFeature.java]
                + [ErroneousInitiation.java][main/java/com/bio4j/model/uniprot/relationships/ErroneousInitiation.java]
                + [AllergenComment.java][main/java/com/bio4j/model/uniprot/relationships/AllergenComment.java]
                + [SubunitComment.java][main/java/com/bio4j/model/uniprot/relationships/SubunitComment.java]
                + [BookProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/BookProteinCitation.java]
                + [InductionComment.java][main/java/com/bio4j/model/uniprot/relationships/InductionComment.java]
                + [ArticleProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/ArticleProteinCitation.java]
                + [CatalyticActivityComment.java][main/java/com/bio4j/model/uniprot/relationships/CatalyticActivityComment.java]
                + [SubcellularLocationParent.java][main/java/com/bio4j/model/uniprot/relationships/SubcellularLocationParent.java]
                + [PharmaceuticalComment.java][main/java/com/bio4j/model/uniprot/relationships/PharmaceuticalComment.java]
                + [UnsureResidueFeature.java][main/java/com/bio4j/model/uniprot/relationships/UnsureResidueFeature.java]
                + [ToxicDoseComment.java][main/java/com/bio4j/model/uniprot/relationships/ToxicDoseComment.java]
                + [OnlineArticleProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/OnlineArticleProteinCitation.java]
                + [ProteinGenomeElement.java][main/java/com/bio4j/model/uniprot/relationships/ProteinGenomeElement.java]
                + [RnaEditingComment.java][main/java/com/bio4j/model/uniprot/relationships/RnaEditingComment.java]
                + [DisulfideBondFeature.java][main/java/com/bio4j/model/uniprot/relationships/DisulfideBondFeature.java]
                + [PathwayComment.java][main/java/com/bio4j/model/uniprot/relationships/PathwayComment.java]
                + [ArticleJournal.java][main/java/com/bio4j/model/uniprot/relationships/ArticleJournal.java]
                + [NonConsecutiveResiduesFeature.java][main/java/com/bio4j/model/uniprot/relationships/NonConsecutiveResiduesFeature.java]
                + [RegionOfInterestFeature.java][main/java/com/bio4j/model/uniprot/relationships/RegionOfInterestFeature.java]
                + [SubmissionDb.java][main/java/com/bio4j/model/uniprot/relationships/SubmissionDb.java]
                + [MetalIonBindingSiteFeature.java][main/java/com/bio4j/model/uniprot/relationships/MetalIonBindingSiteFeature.java]
                + [GlycosylationSiteFeature.java][main/java/com/bio4j/model/uniprot/relationships/GlycosylationSiteFeature.java]
                + [Frameshift.java][main/java/com/bio4j/model/uniprot/relationships/Frameshift.java]
                + [ThesisInstitute.java][main/java/com/bio4j/model/uniprot/relationships/ThesisInstitute.java]
                + [CoiledCoilRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/CoiledCoilRegionFeature.java]
                + [CompositionallyBiasedRegionFeature.java][main/java/com/bio4j/model/uniprot/relationships/CompositionallyBiasedRegionFeature.java]
                + [UnpublishedObservationProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/UnpublishedObservationProteinCitation.java]
                + [MiscellaneousComment.java][main/java/com/bio4j/model/uniprot/relationships/MiscellaneousComment.java]
                + [ProteinErroneousTermination.java][main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousTermination.java]
                + [CrossLinkFeature.java][main/java/com/bio4j/model/uniprot/relationships/CrossLinkFeature.java]
                + [BasicProteinSequenceCaution.java][main/java/com/bio4j/model/uniprot/relationships/BasicProteinSequenceCaution.java]
                + [ProteinSubcellularLocation.java][main/java/com/bio4j/model/uniprot/relationships/ProteinSubcellularLocation.java]
                + [PostTransactionalModificationComment.java][main/java/com/bio4j/model/uniprot/relationships/PostTransactionalModificationComment.java]
                + [BookAuthor.java][main/java/com/bio4j/model/uniprot/relationships/BookAuthor.java]
                + [DisruptionPhenotypeComment.java][main/java/com/bio4j/model/uniprot/relationships/DisruptionPhenotypeComment.java]
                + [ProteinErroneousTranslation.java][main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousTranslation.java]
                + [ProteinErroneousGeneModelPrediction.java][main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousGeneModelPrediction.java]
                + [DomainComment.java][main/java/com/bio4j/model/uniprot/relationships/DomainComment.java]
                + [PolymorphismComment.java][main/java/com/bio4j/model/uniprot/relationships/PolymorphismComment.java]
                + [ErroneousGeneModelPrediction.java][main/java/com/bio4j/model/uniprot/relationships/ErroneousGeneModelPrediction.java]
                + [CofactorComment.java][main/java/com/bio4j/model/uniprot/relationships/CofactorComment.java]
                + [PatentProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/PatentProteinCitation.java]
                + [BasicComment.java][main/java/com/bio4j/model/uniprot/relationships/BasicComment.java]
                + [MiscellaneousDiscrepancy.java][main/java/com/bio4j/model/uniprot/relationships/MiscellaneousDiscrepancy.java]
                + [DiseaseComment.java][main/java/com/bio4j/model/uniprot/relationships/DiseaseComment.java]
                + [StrandFeature.java][main/java/com/bio4j/model/uniprot/relationships/StrandFeature.java]
                + [MassSpectometryComment.java][main/java/com/bio4j/model/uniprot/relationships/MassSpectometryComment.java]
                + [ProteinMiscellaneousDiscrepancy.java][main/java/com/bio4j/model/uniprot/relationships/ProteinMiscellaneousDiscrepancy.java]
                + [DomainFeature.java][main/java/com/bio4j/model/uniprot/relationships/DomainFeature.java]
                + [ShortSequenceMotifFeature.java][main/java/com/bio4j/model/uniprot/relationships/ShortSequenceMotifFeature.java]
                + [BioPhysicoChemicalPropertiesComment.java][main/java/com/bio4j/model/uniprot/relationships/BioPhysicoChemicalPropertiesComment.java]
                + [RepeatFeature.java][main/java/com/bio4j/model/uniprot/relationships/RepeatFeature.java]
                + [ModifiedResidueFeature.java][main/java/com/bio4j/model/uniprot/relationships/ModifiedResidueFeature.java]
                + [ActiveSiteFeature.java][main/java/com/bio4j/model/uniprot/relationships/ActiveSiteFeature.java]
                + [ProteinInterpro.java][main/java/com/bio4j/model/uniprot/relationships/ProteinInterpro.java]
                + [BasicFeature.java][main/java/com/bio4j/model/uniprot/relationships/BasicFeature.java]
                + [ThesisProteinCitation.java][main/java/com/bio4j/model/uniprot/relationships/ThesisProteinCitation.java]
                + [SubmissionAuthor.java][main/java/com/bio4j/model/uniprot/relationships/SubmissionAuthor.java]
              + nodes
                + [Taxon.java][main/java/com/bio4j/model/uniprot/nodes/Taxon.java]
                + [Publisher.java][main/java/com/bio4j/model/uniprot/nodes/Publisher.java]
                + [Book.java][main/java/com/bio4j/model/uniprot/nodes/Book.java]
                + [OnlineArticle.java][main/java/com/bio4j/model/uniprot/nodes/OnlineArticle.java]
                + [Person.java][main/java/com/bio4j/model/uniprot/nodes/Person.java]
                + [SubcellularLocation.java][main/java/com/bio4j/model/uniprot/nodes/SubcellularLocation.java]
                + [FeatureType.java][main/java/com/bio4j/model/uniprot/nodes/FeatureType.java]
                + [ReactomeTerm.java][main/java/com/bio4j/model/uniprot/nodes/ReactomeTerm.java]
                + [Thesis.java][main/java/com/bio4j/model/uniprot/nodes/Thesis.java]
                + references
                  + [Articles.java][main/java/com/bio4j/model/uniprot/nodes/references/Articles.java]
                  + [Persons.java][main/java/com/bio4j/model/uniprot/nodes/references/Persons.java]
                  + [Reference.java][main/java/com/bio4j/model/uniprot/nodes/references/Reference.java]
                  + [Submissions.java][main/java/com/bio4j/model/uniprot/nodes/references/Submissions.java]
                  + [Books.java][main/java/com/bio4j/model/uniprot/nodes/references/Books.java]
                  + [OnlineArticles.java][main/java/com/bio4j/model/uniprot/nodes/references/OnlineArticles.java]
                  + [Publication.java][main/java/com/bio4j/model/uniprot/nodes/references/Publication.java]
                  + [Author.java][main/java/com/bio4j/model/uniprot/nodes/references/Author.java]
                  + [Patents.java][main/java/com/bio4j/model/uniprot/nodes/references/Patents.java]
                  + [Consortiums.java][main/java/com/bio4j/model/uniprot/nodes/references/Consortiums.java]
                  + [Editor.java][main/java/com/bio4j/model/uniprot/nodes/references/Editor.java]
                  + [Theses.java][main/java/com/bio4j/model/uniprot/nodes/references/Theses.java]
                  + [Journals.java][main/java/com/bio4j/model/uniprot/nodes/references/Journals.java]
                  + [UnpublishedObservations.java][main/java/com/bio4j/model/uniprot/nodes/references/UnpublishedObservations.java]
                + [Keyword.java][main/java/com/bio4j/model/uniprot/nodes/Keyword.java]
                + [Protein.java][main/java/com/bio4j/model/uniprot/nodes/Protein.java]
                + [Submission.java][main/java/com/bio4j/model/uniprot/nodes/Submission.java]
                + [CommentType.java][main/java/com/bio4j/model/uniprot/nodes/CommentType.java]
                + [SequenceCaution.java][main/java/com/bio4j/model/uniprot/nodes/SequenceCaution.java]
                + [DB.java][main/java/com/bio4j/model/uniprot/nodes/DB.java]
                + [City.java][main/java/com/bio4j/model/uniprot/nodes/City.java]
                + [Institute.java][main/java/com/bio4j/model/uniprot/nodes/Institute.java]
                + [Consortium.java][main/java/com/bio4j/model/uniprot/nodes/Consortium.java]
                + [Pfam.java][main/java/com/bio4j/model/uniprot/nodes/Pfam.java]
                + [OnlineJournal.java][main/java/com/bio4j/model/uniprot/nodes/OnlineJournal.java]
                + [Patent.java][main/java/com/bio4j/model/uniprot/nodes/Patent.java]
                + [UnpublishedObservation.java][main/java/com/bio4j/model/uniprot/nodes/UnpublishedObservation.java]
                + [Interpro.java][main/java/com/bio4j/model/uniprot/nodes/Interpro.java]
                + [Organism.java][main/java/com/bio4j/model/uniprot/nodes/Organism.java]
                + [Dataset.java][main/java/com/bio4j/model/uniprot/nodes/Dataset.java]
                + [Journal.java][main/java/com/bio4j/model/uniprot/nodes/Journal.java]
                + [Country.java][main/java/com/bio4j/model/uniprot/nodes/Country.java]
            + proteinInteractions
              + [ProteinInteractionsModule.java][main/java/com/bio4j/model/proteinInteractions/ProteinInteractionsModule.java]
              + relationships
                + [ProteinProteinInteraction.java][main/java/com/bio4j/model/proteinInteractions/relationships/ProteinProteinInteraction.java]
                + [ProteinIsoformInteraction.java][main/java/com/bio4j/model/proteinInteractions/relationships/ProteinIsoformInteraction.java]
            + uniprot_ncbiTaxonomy
              + [UniProt_NcbiTaxonomyModule.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProt_NcbiTaxonomyModule.java]
              + relationships
                + [OrganismNCBITaxon.java][main/java/com/bio4j/model/uniprot_ncbiTaxonomy/relationships/OrganismNCBITaxon.java]
            + refseq
              + relationships
                + [HasGene.java][main/java/com/bio4j/model/refseq/relationships/HasGene.java]
                + [HasMRNA.java][main/java/com/bio4j/model/refseq/relationships/HasMRNA.java]
                + [HasGenomicFeatureType.java][main/java/com/bio4j/model/refseq/relationships/HasGenomicFeatureType.java]
                + [HasNcRNA.java][main/java/com/bio4j/model/refseq/relationships/HasNcRNA.java]
                + [HasGenomicFeature.java][main/java/com/bio4j/model/refseq/relationships/HasGenomicFeature.java]
                + [HasTmRNA.java][main/java/com/bio4j/model/refseq/relationships/HasTmRNA.java]
                + [HasTRNA.java][main/java/com/bio4j/model/refseq/relationships/HasTRNA.java]
                + [HasMiscRNA.java][main/java/com/bio4j/model/refseq/relationships/HasMiscRNA.java]
                + [HasCDS.java][main/java/com/bio4j/model/refseq/relationships/HasCDS.java]
                + [HasRRNA.java][main/java/com/bio4j/model/refseq/relationships/HasRRNA.java]
              + [RefSeqModule.java][main/java/com/bio4j/model/refseq/RefSeqModule.java]
              + nodes
                + [GenomicFeature.java][main/java/com/bio4j/model/refseq/nodes/GenomicFeature.java]
                + [MRNA.java][main/java/com/bio4j/model/refseq/nodes/MRNA.java]
                + [Gene.java][main/java/com/bio4j/model/refseq/nodes/Gene.java]
                + [RNAType.java][main/java/com/bio4j/model/refseq/nodes/RNAType.java]
                + [RRNA.java][main/java/com/bio4j/model/refseq/nodes/RRNA.java]
                + [CDS.java][main/java/com/bio4j/model/refseq/nodes/CDS.java]
                + [NcRNA.java][main/java/com/bio4j/model/refseq/nodes/NcRNA.java]
                + [MiscRNA.java][main/java/com/bio4j/model/refseq/nodes/MiscRNA.java]
                + [TmRNA.java][main/java/com/bio4j/model/refseq/nodes/TmRNA.java]
                + [GenomeElement.java][main/java/com/bio4j/model/refseq/nodes/GenomeElement.java]
                + [TRNA.java][main/java/com/bio4j/model/refseq/nodes/TRNA.java]
                + [GenomicFeatureType.java][main/java/com/bio4j/model/refseq/nodes/GenomicFeatureType.java]
                + [RNA.java][main/java/com/bio4j/model/refseq/nodes/RNA.java]
            + uniprot_go
              + relationships
                + [GoAnnotation.java][main/java/com/bio4j/model/uniprot_go/relationships/GoAnnotation.java]
              + [UniProt_GoModule.java][main/java/com/bio4j/model/uniprot_go/UniProt_GoModule.java]
            + uniprot_isoforms
              + relationships
                + [ProteinIsoform.java][main/java/com/bio4j/model/uniprot_isoforms/relationships/ProteinIsoform.java]

[main/java/com/bio4j/model/isoforms/relationships/AlternativeProductInitiation.java]: ../isoforms/relationships/AlternativeProductInitiation.java.md
[main/java/com/bio4j/model/isoforms/relationships/AlternativeProductRibosomalFrameshifting.java]: ../isoforms/relationships/AlternativeProductRibosomalFrameshifting.java.md
[main/java/com/bio4j/model/isoforms/relationships/AlternativeProductSplicing.java]: ../isoforms/relationships/AlternativeProductSplicing.java.md
[main/java/com/bio4j/model/isoforms/relationships/IsoformEventGenerator.java]: ../isoforms/relationships/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/isoforms/relationships/AlternativeProductPromoter.java]: ../isoforms/relationships/AlternativeProductPromoter.java.md
[main/java/com/bio4j/model/isoforms/nodes/AlternativeProduct.java]: ../isoforms/nodes/AlternativeProduct.java.md
[main/java/com/bio4j/model/isoforms/nodes/Isoform.java]: ../isoforms/nodes/Isoform.java.md
[main/java/com/bio4j/model/isoforms/IsoformsModule.java]: ../isoforms/IsoformsModule.java.md
[main/java/com/bio4j/model/uniref/UniRefModule.java]: ../uniref/UniRefModule.java.md
[main/java/com/bio4j/model/uniref/relationships/UniRef100Member.java]: ../uniref/relationships/UniRef100Member.java.md
[main/java/com/bio4j/model/uniref/relationships/UniRef50Member.java]: ../uniref/relationships/UniRef50Member.java.md
[main/java/com/bio4j/model/uniref/relationships/UniRef90Member.java]: ../uniref/relationships/UniRef90Member.java.md
[main/java/com/bio4j/model/enzymedb/indexes/ById.java]: ../enzymedb/indexes/ById.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBModule.java]: ../enzymedb/EnzymeDBModule.java.md
[main/java/com/bio4j/model/enzymedb/relationships/EnzymaticActivity.java]: ../enzymedb/relationships/EnzymaticActivity.java.md
[main/java/com/bio4j/model/enzymedb/nodes/Enzyme.java]: ../enzymedb/nodes/Enzyme.java.md
[main/java/com/bio4j/model/properties/DoId.java]: ../properties/DoId.java.md
[main/java/com/bio4j/model/properties/NCBITaxonomyId.java]: ../properties/NCBITaxonomyId.java.md
[main/java/com/bio4j/model/properties/GeneNames.java]: ../properties/GeneNames.java.md
[main/java/com/bio4j/model/properties/AlternativeIds.java]: ../properties/AlternativeIds.java.md
[main/java/com/bio4j/model/properties/Obsolete.java]: ../properties/Obsolete.java.md
[main/java/com/bio4j/model/properties/Name.java]: ../properties/Name.java.md
[main/java/com/bio4j/model/properties/PubmedId.java]: ../properties/PubmedId.java.md
[main/java/com/bio4j/model/properties/Sequence.java]: ../properties/Sequence.java.md
[main/java/com/bio4j/model/properties/ScientificName.java]: ../properties/ScientificName.java.md
[main/java/com/bio4j/model/properties/TaxId.java]: ../properties/TaxId.java.md
[main/java/com/bio4j/model/properties/FullName.java]: ../properties/FullName.java.md
[main/java/com/bio4j/model/properties/ShortName.java]: ../properties/ShortName.java.md
[main/java/com/bio4j/model/properties/Definition.java]: ../properties/Definition.java.md
[main/java/com/bio4j/model/properties/Locator.java]: ../properties/Locator.java.md
[main/java/com/bio4j/model/properties/Positions.java]: ../properties/Positions.java.md
[main/java/com/bio4j/model/properties/AlternativeAccessions.java]: ../properties/AlternativeAccessions.java.md
[main/java/com/bio4j/model/properties/Status.java]: ../properties/Status.java.md
[main/java/com/bio4j/model/properties/PathwayName.java]: ../properties/PathwayName.java.md
[main/java/com/bio4j/model/properties/TaxonomicRank.java]: ../properties/TaxonomicRank.java.md
[main/java/com/bio4j/model/properties/PrositeCrossReferences.java]: ../properties/PrositeCrossReferences.java.md
[main/java/com/bio4j/model/properties/ModifiedDate.java]: ../properties/ModifiedDate.java.md
[main/java/com/bio4j/model/properties/AlternateNames.java]: ../properties/AlternateNames.java.md
[main/java/com/bio4j/model/properties/Version.java]: ../properties/Version.java.md
[main/java/com/bio4j/model/properties/OfficialName.java]: ../properties/OfficialName.java.md
[main/java/com/bio4j/model/properties/Evidence.java]: ../properties/Evidence.java.md
[main/java/com/bio4j/model/properties/Id.java]: ../properties/Id.java.md
[main/java/com/bio4j/model/properties/Accession.java]: ../properties/Accession.java.md
[main/java/com/bio4j/model/properties/Comment.java]: ../properties/Comment.java.md
[main/java/com/bio4j/model/properties/Cofactors.java]: ../properties/Cofactors.java.md
[main/java/com/bio4j/model/properties/SynonymName.java]: ../properties/SynonymName.java.md
[main/java/com/bio4j/model/properties/Number.java]: ../properties/Number.java.md
[main/java/com/bio4j/model/properties/Institute.java]: ../properties/Institute.java.md
[main/java/com/bio4j/model/properties/Experiments.java]: ../properties/Experiments.java.md
[main/java/com/bio4j/model/properties/Length.java]: ../properties/Length.java.md
[main/java/com/bio4j/model/properties/CommonName.java]: ../properties/CommonName.java.md
[main/java/com/bio4j/model/properties/EmblCode.java]: ../properties/EmblCode.java.md
[main/java/com/bio4j/model/properties/Last.java]: ../properties/Last.java.md
[main/java/com/bio4j/model/properties/Mass.java]: ../properties/Mass.java.md
[main/java/com/bio4j/model/properties/Date.java]: ../properties/Date.java.md
[main/java/com/bio4j/model/properties/Title.java]: ../properties/Title.java.md
[main/java/com/bio4j/model/properties/CatalyticActivity.java]: ../properties/CatalyticActivity.java.md
[main/java/com/bio4j/model/properties/First.java]: ../properties/First.java.md
[main/java/com/bio4j/model/properties/Volume.java]: ../properties/Volume.java.md
[main/java/com/bio4j/model/properties/MedlineId.java]: ../properties/MedlineId.java.md
[main/java/com/bio4j/model/properties/Note.java]: ../properties/Note.java.md
[main/java/com/bio4j/model/properties/Country.java]: ../properties/Country.java.md
[main/java/com/bio4j/model/enums/UniprotDBXref.java]: UniprotDBXref.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/indexes/ById.java]: ../ncbiTaxonomy/indexes/ById.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/relationships/Parent.java]: ../ncbiTaxonomy/relationships/Parent.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/nodes/NCBITaxon.java]: ../ncbiTaxonomy/nodes/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NcbiTaxonomyModule.java]: ../ncbiTaxonomy/NcbiTaxonomyModule.java.md
[main/java/com/bio4j/model/go/GoModule.java]: ../go/GoModule.java.md
[main/java/com/bio4j/model/go/indexes/ById.java]: ../go/indexes/ById.java.md
[main/java/com/bio4j/model/go/relationships/Term.java]: ../go/relationships/Term.java.md
[main/java/com/bio4j/model/go/relationships/PositivelyRegulates.java]: ../go/relationships/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/relationships/HasPartOf.java]: ../go/relationships/HasPartOf.java.md
[main/java/com/bio4j/model/go/relationships/Regulates.java]: ../go/relationships/Regulates.java.md
[main/java/com/bio4j/model/go/relationships/PartOf.java]: ../go/relationships/PartOf.java.md
[main/java/com/bio4j/model/go/relationships/IsA.java]: ../go/relationships/IsA.java.md
[main/java/com/bio4j/model/go/relationships/NegativelyRegulates.java]: ../go/relationships/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/nodes/MolecularFunction.java]: ../go/nodes/MolecularFunction.java.md
[main/java/com/bio4j/model/go/nodes/GoTerm.java]: ../go/nodes/GoTerm.java.md
[main/java/com/bio4j/model/go/nodes/GoNamespace.java]: ../go/nodes/GoNamespace.java.md
[main/java/com/bio4j/model/util/OnlineJournalRetriever.java]: ../util/OnlineJournalRetriever.java.md
[main/java/com/bio4j/model/util/PfamRetriever.java]: ../util/PfamRetriever.java.md
[main/java/com/bio4j/model/util/SubmissionRetriever.java]: ../util/SubmissionRetriever.java.md
[main/java/com/bio4j/model/util/ArticleRetriever.java]: ../util/ArticleRetriever.java.md
[main/java/com/bio4j/model/util/IsoformRetriever.java]: ../util/IsoformRetriever.java.md
[main/java/com/bio4j/model/util/InterproRetriever.java]: ../util/InterproRetriever.java.md
[main/java/com/bio4j/model/util/PublisherRetriever.java]: ../util/PublisherRetriever.java.md
[main/java/com/bio4j/model/util/ConsortiumRetriever.java]: ../util/ConsortiumRetriever.java.md
[main/java/com/bio4j/model/util/CountryRetriever.java]: ../util/CountryRetriever.java.md
[main/java/com/bio4j/model/util/TaxonRetriever.java]: ../util/TaxonRetriever.java.md
[main/java/com/bio4j/model/util/ByTaxId.java]: ../util/ByTaxId.java.md
[main/java/com/bio4j/model/util/OrganismRetriever.java]: ../util/OrganismRetriever.java.md
[main/java/com/bio4j/model/util/AlternativeProductRetriever.java]: ../util/AlternativeProductRetriever.java.md
[main/java/com/bio4j/model/util/DBRetriever.java]: ../util/DBRetriever.java.md
[main/java/com/bio4j/model/util/NodeIndex.java]: ../util/NodeIndex.java.md
[main/java/com/bio4j/model/util/NCBITaxonRetriever.java]: ../util/NCBITaxonRetriever.java.md
[main/java/com/bio4j/model/util/SequenceCautionRetriever.java]: ../util/SequenceCautionRetriever.java.md
[main/java/com/bio4j/model/util/RelationshipRetriever.java]: ../util/RelationshipRetriever.java.md
[main/java/com/bio4j/model/util/EnzymeRetriever.java]: ../util/EnzymeRetriever.java.md
[main/java/com/bio4j/model/util/ProteinRetriever.java]: ../util/ProteinRetriever.java.md
[main/java/com/bio4j/model/util/GoTermRetriever.java]: ../util/GoTermRetriever.java.md
[main/java/com/bio4j/model/util/PersonRetriever.java]: ../util/PersonRetriever.java.md
[main/java/com/bio4j/model/util/PatentRetriever.java]: ../util/PatentRetriever.java.md
[main/java/com/bio4j/model/util/GenomeElementRetriever.java]: ../util/GenomeElementRetriever.java.md
[main/java/com/bio4j/model/util/ThesisRetriever.java]: ../util/ThesisRetriever.java.md
[main/java/com/bio4j/model/util/CityRetriever.java]: ../util/CityRetriever.java.md
[main/java/com/bio4j/model/util/NodeRetriever.java]: ../util/NodeRetriever.java.md
[main/java/com/bio4j/model/util/FeatureTypeRetriever.java]: ../util/FeatureTypeRetriever.java.md
[main/java/com/bio4j/model/util/InstituteRetriever.java]: ../util/InstituteRetriever.java.md
[main/java/com/bio4j/model/util/CommentTypeRetriever.java]: ../util/CommentTypeRetriever.java.md
[main/java/com/bio4j/model/util/OnlineArticleRetriever.java]: ../util/OnlineArticleRetriever.java.md
[main/java/com/bio4j/model/util/DatasetRetriever.java]: ../util/DatasetRetriever.java.md
[main/java/com/bio4j/model/util/KeywordRetriever.java]: ../util/KeywordRetriever.java.md
[main/java/com/bio4j/model/util/SubcellularLocationRetriever.java]: ../util/SubcellularLocationRetriever.java.md
[main/java/com/bio4j/model/util/JournalRetriever.java]: ../util/JournalRetriever.java.md
[main/java/com/bio4j/model/util/BookRetriever.java]: ../util/BookRetriever.java.md
[main/java/com/bio4j/model/util/ReactomeTermRetriever.java]: ../util/ReactomeTermRetriever.java.md
[main/java/com/bio4j/model/uniprot/UniProtModule.java]: ../uniprot/UniProtModule.java.md
[main/java/com/bio4j/model/uniprot/relationships/OnlineArticleJournal.java]: ../uniprot/relationships/OnlineArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/relationships/SignalPeptideFeature.java]: ../uniprot/relationships/SignalPeptideFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/BookCity.java]: ../uniprot/relationships/BookCity.java.md
[main/java/com/bio4j/model/uniprot/relationships/NonStandardAminoAcidFeature.java]: ../uniprot/relationships/NonStandardAminoAcidFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/SpliceVariantFeature.java]: ../uniprot/relationships/SpliceVariantFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/TransitPeptideFeature.java]: ../uniprot/relationships/TransitPeptideFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/IntramembraneRegionFeature.java]: ../uniprot/relationships/IntramembraneRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/OnlineInformationComment.java]: ../uniprot/relationships/OnlineInformationComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ChainFeature.java]: ../uniprot/relationships/ChainFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinFrameshift.java]: ../uniprot/relationships/ProteinFrameshift.java.md
[main/java/com/bio4j/model/uniprot/relationships/PeptideFeature.java]: ../uniprot/relationships/PeptideFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/CautionComment.java]: ../uniprot/relationships/CautionComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ZincFingerRegionFeature.java]: ../uniprot/relationships/ZincFingerRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinKeyword.java]: ../uniprot/relationships/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/relationships/FunctionComment.java]: ../uniprot/relationships/FunctionComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ErroneousTranslation.java]: ../uniprot/relationships/ErroneousTranslation.java.md
[main/java/com/bio4j/model/uniprot/relationships/CalciumBindingRegionFeature.java]: ../uniprot/relationships/CalciumBindingRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ErroneousTermination.java]: ../uniprot/relationships/ErroneousTermination.java.md
[main/java/com/bio4j/model/uniprot/relationships/HelixFeature.java]: ../uniprot/relationships/HelixFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/BasicCommentType.java]: ../uniprot/relationships/BasicCommentType.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinDataset.java]: ../uniprot/relationships/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinEnzymaticActivity.java]: ../uniprot/relationships/ProteinEnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot/relationships/SequenceConflictFeature.java]: ../uniprot/relationships/SequenceConflictFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/SimilarityComment.java]: ../uniprot/relationships/SimilarityComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/TaxonParent.java]: ../uniprot/relationships/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/relationships/DnaBindingFeature.java]: ../uniprot/relationships/DnaBindingFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/SiteFeature.java]: ../uniprot/relationships/SiteFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/TransmembraneRegionFeature.java]: ../uniprot/relationships/TransmembraneRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/BiotechnologyComment.java]: ../uniprot/relationships/BiotechnologyComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/UnpublishedObservationAuthor.java]: ../uniprot/relationships/UnpublishedObservationAuthor.java.md
[main/java/com/bio4j/model/uniprot/relationships/NucleotidePhosphateBindingRegionFeature.java]: ../uniprot/relationships/NucleotidePhosphateBindingRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/NonTerminalResidueFeature.java]: ../uniprot/relationships/NonTerminalResidueFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/BookPublisher.java]: ../uniprot/relationships/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/relationships/InstituteCountry.java]: ../uniprot/relationships/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinReactome.java]: ../uniprot/relationships/ProteinReactome.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Article.java]: ../uniprot/relationships/references/Article.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Book.java]: ../uniprot/relationships/references/Book.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/OnlineArticle.java]: ../uniprot/relationships/references/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Person.java]: ../uniprot/relationships/references/Person.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Thesis.java]: ../uniprot/relationships/references/Thesis.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Submission.java]: ../uniprot/relationships/references/Submission.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Published.java]: ../uniprot/relationships/references/Published.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Cited.java]: ../uniprot/relationships/references/Cited.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Consortium.java]: ../uniprot/relationships/references/Consortium.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Patent.java]: ../uniprot/relationships/references/Patent.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/UnpublishedObservation.java]: ../uniprot/relationships/references/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/relationships/references/Journal.java]: ../uniprot/relationships/references/Journal.java.md
[main/java/com/bio4j/model/uniprot/relationships/BookEditor.java]: ../uniprot/relationships/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/relationships/SubmissionProteinCitation.java]: ../uniprot/relationships/SubmissionProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/BasicFeatureType.java]: ../uniprot/relationships/BasicFeatureType.java.md
[main/java/com/bio4j/model/uniprot/relationships/PatentAuthor.java]: ../uniprot/relationships/PatentAuthor.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinOrganism.java]: ../uniprot/relationships/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/relationships/TurnFeature.java]: ../uniprot/relationships/TurnFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/TissueSpecificityComment.java]: ../uniprot/relationships/TissueSpecificityComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/LipidMoietyBindingRegionFeature.java]: ../uniprot/relationships/LipidMoietyBindingRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/DevelopmentalStageComment.java]: ../uniprot/relationships/DevelopmentalStageComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousInitiation.java]: ../uniprot/relationships/ProteinErroneousInitiation.java.md
[main/java/com/bio4j/model/uniprot/relationships/OnlineArticleAuthor.java]: ../uniprot/relationships/OnlineArticleAuthor.java.md
[main/java/com/bio4j/model/uniprot/relationships/BindingSiteFeature.java]: ../uniprot/relationships/BindingSiteFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/InitiatorMethionineFeature.java]: ../uniprot/relationships/InitiatorMethionineFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ThesisAuthor.java]: ../uniprot/relationships/ThesisAuthor.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinPfam.java]: ../uniprot/relationships/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/relationships/PropeptideFeature.java]: ../uniprot/relationships/PropeptideFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/EnzymeRegulationComment.java]: ../uniprot/relationships/EnzymeRegulationComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/SequenceVariantFeature.java]: ../uniprot/relationships/SequenceVariantFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/MutagenesisSiteFeature.java]: ../uniprot/relationships/MutagenesisSiteFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/TopologicalDomainFeature.java]: ../uniprot/relationships/TopologicalDomainFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ErroneousInitiation.java]: ../uniprot/relationships/ErroneousInitiation.java.md
[main/java/com/bio4j/model/uniprot/relationships/AllergenComment.java]: ../uniprot/relationships/AllergenComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/SubunitComment.java]: ../uniprot/relationships/SubunitComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/BookProteinCitation.java]: ../uniprot/relationships/BookProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/InductionComment.java]: ../uniprot/relationships/InductionComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ArticleProteinCitation.java]: ../uniprot/relationships/ArticleProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/CatalyticActivityComment.java]: ../uniprot/relationships/CatalyticActivityComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/SubcellularLocationParent.java]: ../uniprot/relationships/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/relationships/PharmaceuticalComment.java]: ../uniprot/relationships/PharmaceuticalComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/UnsureResidueFeature.java]: ../uniprot/relationships/UnsureResidueFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ToxicDoseComment.java]: ../uniprot/relationships/ToxicDoseComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/OnlineArticleProteinCitation.java]: ../uniprot/relationships/OnlineArticleProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinGenomeElement.java]: ../uniprot/relationships/ProteinGenomeElement.java.md
[main/java/com/bio4j/model/uniprot/relationships/RnaEditingComment.java]: ../uniprot/relationships/RnaEditingComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/DisulfideBondFeature.java]: ../uniprot/relationships/DisulfideBondFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/PathwayComment.java]: ../uniprot/relationships/PathwayComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ArticleJournal.java]: ../uniprot/relationships/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/relationships/NonConsecutiveResiduesFeature.java]: ../uniprot/relationships/NonConsecutiveResiduesFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/RegionOfInterestFeature.java]: ../uniprot/relationships/RegionOfInterestFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/SubmissionDb.java]: ../uniprot/relationships/SubmissionDb.java.md
[main/java/com/bio4j/model/uniprot/relationships/MetalIonBindingSiteFeature.java]: ../uniprot/relationships/MetalIonBindingSiteFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/GlycosylationSiteFeature.java]: ../uniprot/relationships/GlycosylationSiteFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/Frameshift.java]: ../uniprot/relationships/Frameshift.java.md
[main/java/com/bio4j/model/uniprot/relationships/ThesisInstitute.java]: ../uniprot/relationships/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/relationships/CoiledCoilRegionFeature.java]: ../uniprot/relationships/CoiledCoilRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/CompositionallyBiasedRegionFeature.java]: ../uniprot/relationships/CompositionallyBiasedRegionFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/UnpublishedObservationProteinCitation.java]: ../uniprot/relationships/UnpublishedObservationProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/MiscellaneousComment.java]: ../uniprot/relationships/MiscellaneousComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousTermination.java]: ../uniprot/relationships/ProteinErroneousTermination.java.md
[main/java/com/bio4j/model/uniprot/relationships/CrossLinkFeature.java]: ../uniprot/relationships/CrossLinkFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/BasicProteinSequenceCaution.java]: ../uniprot/relationships/BasicProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinSubcellularLocation.java]: ../uniprot/relationships/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/relationships/PostTransactionalModificationComment.java]: ../uniprot/relationships/PostTransactionalModificationComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/BookAuthor.java]: ../uniprot/relationships/BookAuthor.java.md
[main/java/com/bio4j/model/uniprot/relationships/DisruptionPhenotypeComment.java]: ../uniprot/relationships/DisruptionPhenotypeComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousTranslation.java]: ../uniprot/relationships/ProteinErroneousTranslation.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinErroneousGeneModelPrediction.java]: ../uniprot/relationships/ProteinErroneousGeneModelPrediction.java.md
[main/java/com/bio4j/model/uniprot/relationships/DomainComment.java]: ../uniprot/relationships/DomainComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/PolymorphismComment.java]: ../uniprot/relationships/PolymorphismComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ErroneousGeneModelPrediction.java]: ../uniprot/relationships/ErroneousGeneModelPrediction.java.md
[main/java/com/bio4j/model/uniprot/relationships/CofactorComment.java]: ../uniprot/relationships/CofactorComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/PatentProteinCitation.java]: ../uniprot/relationships/PatentProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/BasicComment.java]: ../uniprot/relationships/BasicComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/MiscellaneousDiscrepancy.java]: ../uniprot/relationships/MiscellaneousDiscrepancy.java.md
[main/java/com/bio4j/model/uniprot/relationships/DiseaseComment.java]: ../uniprot/relationships/DiseaseComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/StrandFeature.java]: ../uniprot/relationships/StrandFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/MassSpectometryComment.java]: ../uniprot/relationships/MassSpectometryComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinMiscellaneousDiscrepancy.java]: ../uniprot/relationships/ProteinMiscellaneousDiscrepancy.java.md
[main/java/com/bio4j/model/uniprot/relationships/DomainFeature.java]: ../uniprot/relationships/DomainFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ShortSequenceMotifFeature.java]: ../uniprot/relationships/ShortSequenceMotifFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/BioPhysicoChemicalPropertiesComment.java]: ../uniprot/relationships/BioPhysicoChemicalPropertiesComment.java.md
[main/java/com/bio4j/model/uniprot/relationships/RepeatFeature.java]: ../uniprot/relationships/RepeatFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ModifiedResidueFeature.java]: ../uniprot/relationships/ModifiedResidueFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ActiveSiteFeature.java]: ../uniprot/relationships/ActiveSiteFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ProteinInterpro.java]: ../uniprot/relationships/ProteinInterpro.java.md
[main/java/com/bio4j/model/uniprot/relationships/BasicFeature.java]: ../uniprot/relationships/BasicFeature.java.md
[main/java/com/bio4j/model/uniprot/relationships/ThesisProteinCitation.java]: ../uniprot/relationships/ThesisProteinCitation.java.md
[main/java/com/bio4j/model/uniprot/relationships/SubmissionAuthor.java]: ../uniprot/relationships/SubmissionAuthor.java.md
[main/java/com/bio4j/model/uniprot/nodes/Taxon.java]: ../uniprot/nodes/Taxon.java.md
[main/java/com/bio4j/model/uniprot/nodes/Publisher.java]: ../uniprot/nodes/Publisher.java.md
[main/java/com/bio4j/model/uniprot/nodes/Book.java]: ../uniprot/nodes/Book.java.md
[main/java/com/bio4j/model/uniprot/nodes/OnlineArticle.java]: ../uniprot/nodes/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/nodes/Person.java]: ../uniprot/nodes/Person.java.md
[main/java/com/bio4j/model/uniprot/nodes/SubcellularLocation.java]: ../uniprot/nodes/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/nodes/FeatureType.java]: ../uniprot/nodes/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/nodes/ReactomeTerm.java]: ../uniprot/nodes/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/nodes/Thesis.java]: ../uniprot/nodes/Thesis.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Articles.java]: ../uniprot/nodes/references/Articles.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Persons.java]: ../uniprot/nodes/references/Persons.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Reference.java]: ../uniprot/nodes/references/Reference.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Submissions.java]: ../uniprot/nodes/references/Submissions.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Books.java]: ../uniprot/nodes/references/Books.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/OnlineArticles.java]: ../uniprot/nodes/references/OnlineArticles.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Publication.java]: ../uniprot/nodes/references/Publication.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Author.java]: ../uniprot/nodes/references/Author.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Patents.java]: ../uniprot/nodes/references/Patents.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Consortiums.java]: ../uniprot/nodes/references/Consortiums.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Editor.java]: ../uniprot/nodes/references/Editor.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Theses.java]: ../uniprot/nodes/references/Theses.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/Journals.java]: ../uniprot/nodes/references/Journals.java.md
[main/java/com/bio4j/model/uniprot/nodes/references/UnpublishedObservations.java]: ../uniprot/nodes/references/UnpublishedObservations.java.md
[main/java/com/bio4j/model/uniprot/nodes/Keyword.java]: ../uniprot/nodes/Keyword.java.md
[main/java/com/bio4j/model/uniprot/nodes/Protein.java]: ../uniprot/nodes/Protein.java.md
[main/java/com/bio4j/model/uniprot/nodes/Submission.java]: ../uniprot/nodes/Submission.java.md
[main/java/com/bio4j/model/uniprot/nodes/CommentType.java]: ../uniprot/nodes/CommentType.java.md
[main/java/com/bio4j/model/uniprot/nodes/SequenceCaution.java]: ../uniprot/nodes/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/nodes/DB.java]: ../uniprot/nodes/DB.java.md
[main/java/com/bio4j/model/uniprot/nodes/City.java]: ../uniprot/nodes/City.java.md
[main/java/com/bio4j/model/uniprot/nodes/Institute.java]: ../uniprot/nodes/Institute.java.md
[main/java/com/bio4j/model/uniprot/nodes/Consortium.java]: ../uniprot/nodes/Consortium.java.md
[main/java/com/bio4j/model/uniprot/nodes/Pfam.java]: ../uniprot/nodes/Pfam.java.md
[main/java/com/bio4j/model/uniprot/nodes/OnlineJournal.java]: ../uniprot/nodes/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/nodes/Patent.java]: ../uniprot/nodes/Patent.java.md
[main/java/com/bio4j/model/uniprot/nodes/UnpublishedObservation.java]: ../uniprot/nodes/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/nodes/Interpro.java]: ../uniprot/nodes/Interpro.java.md
[main/java/com/bio4j/model/uniprot/nodes/Organism.java]: ../uniprot/nodes/Organism.java.md
[main/java/com/bio4j/model/uniprot/nodes/Dataset.java]: ../uniprot/nodes/Dataset.java.md
[main/java/com/bio4j/model/uniprot/nodes/Journal.java]: ../uniprot/nodes/Journal.java.md
[main/java/com/bio4j/model/uniprot/nodes/Country.java]: ../uniprot/nodes/Country.java.md
[main/java/com/bio4j/model/proteinInteractions/ProteinInteractionsModule.java]: ../proteinInteractions/ProteinInteractionsModule.java.md
[main/java/com/bio4j/model/proteinInteractions/relationships/ProteinProteinInteraction.java]: ../proteinInteractions/relationships/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/proteinInteractions/relationships/ProteinIsoformInteraction.java]: ../proteinInteractions/relationships/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProt_NcbiTaxonomyModule.java]: ../uniprot_ncbiTaxonomy/UniProt_NcbiTaxonomyModule.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/relationships/OrganismNCBITaxon.java]: ../uniprot_ncbiTaxonomy/relationships/OrganismNCBITaxon.java.md
[main/java/com/bio4j/model/refseq/relationships/HasGene.java]: ../refseq/relationships/HasGene.java.md
[main/java/com/bio4j/model/refseq/relationships/HasMRNA.java]: ../refseq/relationships/HasMRNA.java.md
[main/java/com/bio4j/model/refseq/relationships/HasGenomicFeatureType.java]: ../refseq/relationships/HasGenomicFeatureType.java.md
[main/java/com/bio4j/model/refseq/relationships/HasNcRNA.java]: ../refseq/relationships/HasNcRNA.java.md
[main/java/com/bio4j/model/refseq/relationships/HasGenomicFeature.java]: ../refseq/relationships/HasGenomicFeature.java.md
[main/java/com/bio4j/model/refseq/relationships/HasTmRNA.java]: ../refseq/relationships/HasTmRNA.java.md
[main/java/com/bio4j/model/refseq/relationships/HasTRNA.java]: ../refseq/relationships/HasTRNA.java.md
[main/java/com/bio4j/model/refseq/relationships/HasMiscRNA.java]: ../refseq/relationships/HasMiscRNA.java.md
[main/java/com/bio4j/model/refseq/relationships/HasCDS.java]: ../refseq/relationships/HasCDS.java.md
[main/java/com/bio4j/model/refseq/relationships/HasRRNA.java]: ../refseq/relationships/HasRRNA.java.md
[main/java/com/bio4j/model/refseq/RefSeqModule.java]: ../refseq/RefSeqModule.java.md
[main/java/com/bio4j/model/refseq/nodes/GenomicFeature.java]: ../refseq/nodes/GenomicFeature.java.md
[main/java/com/bio4j/model/refseq/nodes/MRNA.java]: ../refseq/nodes/MRNA.java.md
[main/java/com/bio4j/model/refseq/nodes/Gene.java]: ../refseq/nodes/Gene.java.md
[main/java/com/bio4j/model/refseq/nodes/RNAType.java]: ../refseq/nodes/RNAType.java.md
[main/java/com/bio4j/model/refseq/nodes/RRNA.java]: ../refseq/nodes/RRNA.java.md
[main/java/com/bio4j/model/refseq/nodes/CDS.java]: ../refseq/nodes/CDS.java.md
[main/java/com/bio4j/model/refseq/nodes/NcRNA.java]: ../refseq/nodes/NcRNA.java.md
[main/java/com/bio4j/model/refseq/nodes/MiscRNA.java]: ../refseq/nodes/MiscRNA.java.md
[main/java/com/bio4j/model/refseq/nodes/TmRNA.java]: ../refseq/nodes/TmRNA.java.md
[main/java/com/bio4j/model/refseq/nodes/GenomeElement.java]: ../refseq/nodes/GenomeElement.java.md
[main/java/com/bio4j/model/refseq/nodes/TRNA.java]: ../refseq/nodes/TRNA.java.md
[main/java/com/bio4j/model/refseq/nodes/GenomicFeatureType.java]: ../refseq/nodes/GenomicFeatureType.java.md
[main/java/com/bio4j/model/refseq/nodes/RNA.java]: ../refseq/nodes/RNA.java.md
[main/java/com/bio4j/model/uniprot_go/relationships/GoAnnotation.java]: ../uniprot_go/relationships/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_go/UniProt_GoModule.java]: ../uniprot_go/UniProt_GoModule.java.md
[main/java/com/bio4j/model/uniprot_isoforms/relationships/ProteinIsoform.java]: ../uniprot_isoforms/relationships/ProteinIsoform.java.md