package com.bio4j.model.uniprot_ncbiTaxonomy.edges;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class ProteinNCBITaxon<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UniProtNCBITaxonomyGraph.UniProtNCBITaxonomyEdge<
                // src
                Protein<I, RV, RVT, RE, RET>,
                UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                UniProtGraph<I, RV, RVT, RE, RET>,
                // edge
                ProteinNCBITaxon<I, RV, RVT, RE, RET>,
                UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.ProteinNCBITaxonType,
                //tgt
                NCBITaxon<I, RV, RVT, RE, RET>,
                NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
                NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                >
{

    public ProteinNCBITaxon(RE edge, UniProtNCBITaxonomyGraph<I, RV, RVT, RE, RET>.ProteinNCBITaxonType type) {

        super(edge, type);
    }

    @Override
    public ProteinNCBITaxon<I, RV, RVT, RE, RET> self() {
        return this;
    }
}