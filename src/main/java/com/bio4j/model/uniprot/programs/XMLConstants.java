package com.bio4j.model.uniprot.programs;

public final class XMLConstants {

  public static final String ENTRY_TAG_NAME                 = "entry";
  public static final String ENTRY_ACCESSION_TAG_NAME       = "accession";
  public static final String ENTRY_NAME_TAG_NAME            = "name";
  public static final String ENTRY_MODIFIED_DATE_ATTRIBUTE  = "modified";
  public static final String ENTRY_CREATED_DATE_ATTRIBUTE   = "created";
  public static final String ENTRY_VERSION_ATTRIBUTE        = "version";
  public static final String ENTRY_DATASET_ATTRIBUTE        = "dataset";
  public static final String ENTRY_SEQUENCE_TAG_NAME        = "sequence";

  public static final String KEYWORD_TAG_NAME     = "keyword";
  public static final String KEYWORD_ID_ATTRIBUTE = "id";

  public static final String REFERENCE_TAG_NAME = "reference";
  public static final String CITATION_TAG_NAME  = "citation";

  public static final String GENE_LOCATION_TAG_NAME = "geneLocation";

  public static final String ORGANISM_TAG_NAME              = "organism";
  public static final String ORGANISM_NAME_TAG_NAME         = "name";
  public static final String ORGANISM_NAME_TYPE_ATTRIBUTE   = "type";
  public static final String ORGANISM_SCIENTIFIC_NAME_TYPE  = "scientific";
  public static final String ORGANISM_COMMON_NAME_TYPE      = "common";
  public static final String ORGANISM_SYNONYM_NAME_TYPE     = "synonym";

  public static final String DB_REFERENCE_TAG_NAME          = "dbReference";
  public static final String DB_REFERENCE_TYPE_ATTRIBUTE    = "type";
  public static final String DB_REFERENCE_ID_ATTRIBUTE      = "id";
  public static final String DB_REFERENCE_VALUE_ATTRIBUTE   = "value";
  public static final String DB_REFERENCE_PROPERTY_TAG_NAME = "property";

  public static final String INTERPRO_DB_REFERENCE_TYPE = "InterPro";
  public static final String INTERPRO_ENTRY_NAME        = "entry name";

  public static final String GO_DB_REFERENCE_TYPE     = "GO";
  public static final String EVIDENCE_TYPE_ATTRIBUTE  = "evidence";

  public static final String SEQUENCE_MASS_ATTRIBUTE    = "mass";
  public static final String SEQUENCE_LENGTH_ATTRIBUTE  = "length";

  public static final String PROTEIN_TAG_NAME                   = "protein";
  public static final String PROTEIN_RECOMMENDED_NAME_TAG_NAME  = "recommendedName";
  public static final String PROTEIN_FULL_NAME_TAG_NAME         = "fullName";
  public static final String PROTEIN_SHORT_NAME_TAG_NAME        = "shortName"
  ;
  public static final String GENE_TAG_NAME        = "gene";
  public static final String GENE_NAME_TAG_NAME   = "name";

  public static final String COMMENT_TAG_NAME                   = "comment";
  public static final String COMMENT_TYPE_ATTRIBUTE             = "type";
  public static final String COMMENT_ALTERNATIVE_PRODUCTS_TYPE  = "alternative products";
  public static final String COMMENT_SEQUENCE_CAUTION_TYPE      = "sequence caution";
  public static final String SUBCELLULAR_LOCATION_TAG_NAME      = "subcellularLocation";

  public static final String LOCATION_TAG_NAME                    = "location";
  public static final String COMMENT_TEXT_TAG_NAME                = "text";
  public static final String FEATURE_TAG_NAME                     = "feature";
  public static final String FEATURE_TYPE_ATTRIBUTE               = "type";
  public static final String FEATURE_DESCRIPTION_ATTRIBUTE        = "description";
  public static final String STATUS_ATTRIBUTE                     = "status";
  public static final String FEATURE_REF_ATTRIBUTE                = "ref";
  public static final String FEATURE_ID_ATTRIBUTE                 = "id";
  public static final String EVIDENCE_ATTRIBUTE                   = "evidence";
  public static final String FEATURE_LOCATION_TAG_NAME            = "location";
  public static final String FEATURE_ORIGINAL_TAG_NAME            = "original";
  public static final String FEATURE_VARIATION_TAG_NAME           = "variation";
  public static final String FEATURE_POSITION_TAG_NAME            = "position";
  public static final String FEATURE_LOCATION_BEGIN_TAG_NAME      = "begin";
  public static final String FEATURE_LOCATION_END_TAG_NAME        = "end";
  public static final String FEATURE_LOCATION_POSITION_ATTRIBUTE  = "position";
  public static final String FEATURE_POSITION_POSITION_ATTRIBUTE  = "position";

  public static final String THESIS_CITATION_TYPE                   = "thesis";
  public static final String PATENT_CITATION_TYPE                   = "patent";
  public static final String SUBMISSION_CITATION_TYPE               = "submission";
  public static final String ARTICLE_CITATION_TYPE                  = "journal article";
  public static final String ONLINE_ARTICLE_CITATION_TYPE           = "online journal article";
  public static final String BOOK_CITATION_TYPE                     = "book";
  public static final String UNPUBLISHED_OBSERVATION_CITATION_TYPE  = "unpublished observations";


  interface ENTRY {
    String element = "entry";
    interface ACCESSION {
      String element = "accession";
    }
  }
  
  interface COMMENT {
    String element = "comment";
    interface TYPE {
      String attribute = "type";
      // possible values
      public static final String INTERACTION = "interaction";
      // COMMMENT.TYPE.attr.equals(COMMMENT.TYPE.INTERACTION)
    }
    interface INTERACTANT     { String element = "interactant";
      interface INTACTID  { String attribute  = "intactId"; }
      interface ID        { String element    = "id";       }
    }
    interface ORGANISMSDIFFER { String element    = "organismsDiffer";  }
    interface EXPERIMENTS     { String element    = "experiments";      }
  }

  public static final String COMMENT_TYPE_DISEASE                         = "disease";
  public static final String COMMENT_TYPE_FUNCTION                        = "function";
  public static final String COMMENT_TYPE_COFACTOR                        = "cofactor";
  public static final String COMMENT_TYPE_CATALYTIC_ACTIVITY              = "catalytic activity";
  public static final String COMMENT_TYPE_ENZYME_REGULATION               = "enzyme regulation";
  public static final String COMMENT_TYPE_BIOPHYSICOCHEMICAL_PROPERTIES   = "biophysicochemical properties";
  public static final String COMMENT_TYPE_SUBUNIT                         = "subunit";
  public static final String COMMENT_TYPE_PATHWAY                         = "pathway";
  public static final String COMMENT_TYPE_SUBCELLULAR_LOCATION            = "subcellular location";
  public static final String COMMENT_TYPE_TISSUE_SPECIFICITY              = "tissue specificity";
  public static final String COMMENT_TYPE_DEVELOPMENTAL_STAGE             = "developmental stage";
  public static final String COMMENT_TYPE_INDUCTION                       = "induction";
  public static final String COMMENT_TYPE_DOMAIN                          = "domain";
  public static final String COMMENT_TYPE_POST_TRANSLATIONAL_MODIFICATION = "PTM";
  public static final String COMMENT_TYPE_RNA_EDITING                     = "RNA editing";
  public static final String COMMENT_TYPE_MASS_SPECTROMETRY               = "mass spectrometry";
  public static final String COMMENT_TYPE_POLYMORPHISM                    = "polymorphism";
  public static final String COMMENT_TYPE_DISRUPTION_PHENOTYPE            = "disruption phenotype";
  public static final String COMMENT_TYPE_ALLERGEN                        = "allergen";
  public static final String COMMENT_TYPE_TOXIC_DOSE                      = "toxic dose";
  public static final String COMMENT_TYPE_BIOTECHNOLOGY                   = "biotechnology";
  public static final String COMMENT_TYPE_PHARMACEUTICAL                  = "pharmaceutical";
  public static final String COMMENT_TYPE_MISCELLANEOUS                   = "miscellaneous";
  public static final String COMMENT_TYPE_SIMILARITY                      = "similarity";
  public static final String COMMENT_TYPE_INTERACTION                     = "interaction";
}
