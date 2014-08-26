package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniprotGraph;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportUniprot<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

    private static final Logger logger = Logger.getLogger("ImportUniprot");
    private static FileHandler fh;

    public static final String ENTRY_TAG_NAME = "entry";
    public static final String ENTRY_ACCESSION_TAG_NAME = "accession";
    public static final String ENTRY_NAME_TAG_NAME = "name";
    public static final String ENTRY_MODIFIED_DATE_ATTRIBUTE = "modified";
    public static final String ENTRY_CREATED_DATE_ATTRIBUTE = "created";
    public static final String ENTRY_VERSION_ATTRIBUTE = "version";
    public static final String ENTRY_DATASET_ATTRIBUTE = "dataset";
    public static final String ENTRY_SEQUENCE_TAG_NAME = "sequence";

    public static final String KEYWORD_TAG_NAME = "keyword";
    public static final String KEYWORD_ID_ATTRIBUTE = "id";

    public static final String REFERENCE_TAG_NAME = "reference";
    public static final String CITATION_TAG_NAME = "citation";

    public static final String ORGANISM_TAG_NAME = "organism";
    public static final String ORGANISM_NAME_TAG_NAME = "name";
    public static final String ORGANISM_NAME_TYPE_ATTRIBUTE = "type";
    public static final String ORGANISM_SCIENTIFIC_NAME_TYPE = "scientific";
    public static final String ORGANISM_COMMON_NAME_TYPE = "common";
    public static final String ORGANISM_SYNONYM_NAME_TYPE = "synonym";

    public static final String DB_REFERENCE_TAG_NAME = "dbReference";
    public static final String DB_REFERENCE_TYPE_ATTRIBUTE = "type";
    public static final String DB_REFERENCE_ID_ATTRIBUTE = "id";
    public static final String DB_REFERENCE_VALUE_ATTRIBUTE = "value";
    public static final String DB_REFERENCE_PROPERTY_TAG_NAME = "property";

    public static final String INTERPRO_DB_REFERENCE_TYPE = "InterPro";
    public static final String INTERPRO_ENTRY_NAME = "entry name";

    public static final String GO_DB_REFERENCE_TYPE = "GO";
    public static final String EVIDENCE_TYPE_ATTRIBUTE = "evidence";

    public static final String SEQUENCE_MASS_ATTRIBUTE = "mass";
    public static final String SEQUENCE_LENGTH_ATTRIBUTE = "length";
    public static final String PROTEIN_TAG_NAME = "protein";
    public static final String PROTEIN_RECOMMENDED_NAME_TAG_NAME = "recommendedName";
    public static final String PROTEIN_FULL_NAME_TAG_NAME = "fullName";
    public static final String PROTEIN_SHORT_NAME_TAG_NAME = "shortName";
    public static final String GENE_TAG_NAME = "gene";
    public static final String GENE_NAME_TAG_NAME = "name";
    public static final String COMMENT_TAG_NAME = "comment";
    public static final String COMMENT_TYPE_ATTRIBUTE = "type";
    public static final String COMMENT_ALTERNATIVE_PRODUCTS_TYPE = "alternative products";
    public static final String COMMENT_SEQUENCE_CAUTION_TYPE = "sequence caution";
    public static final String SUBCELLULAR_LOCATION_TAG_NAME = "subcellularLocation";
    public static final String LOCATION_TAG_NAME = "location";
    public static final String COMMENT_TEXT_TAG_NAME = "text";
    public static final String FEATURE_TAG_NAME = "feature";
    public static final String FEATURE_TYPE_ATTRIBUTE = "type";
    public static final String FEATURE_DESCRIPTION_ATTRIBUTE = "description";
    public static final String STATUS_ATTRIBUTE = "status";
    public static final String FEATURE_REF_ATTRIBUTE = "ref";
    public static final String FEATURE_ID_ATTRIBUTE = "id";
    public static final String EVIDENCE_ATTRIBUTE = "evidence";


    protected abstract UniprotGraph<I,RV,RVT,RE,RET> config();
}
