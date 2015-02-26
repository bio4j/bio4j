package com.bio4j.model.ucsc_uniprot.edges;

import com.bio4j.model.ucsc.UCSCGenesGraph;
import com.bio4j.model.ucsc.vertices.UCSCGenesChromosome;
import com.bio4j.model.ucsc_uniprot.UCSCGenesUniProtGraph;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class UCSCGenesChromosomeProtein<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCGenesUniProtGraph.UCSCGenesUniProtEdge<
                // src
                UCSCGenesChromosome<I, RV, RVT, RE, RET>,
                UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeType,
                UCSCGenesGraph<I, RV, RVT, RE, RET>,
                // edge
                UCSCGenesChromosomeProtein<I, RV, RVT, RE, RET>,
                UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeProteinType,
                //tgt
                Protein<I, RV, RVT, RE, RET>,
                UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                UniProtGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                >
{

    public UCSCGenesChromosomeProtein(RE edge, UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeProteinType type) {

        super(edge, type);
    }

    @Override
    public UCSCGenesChromosomeProtein<I, RV, RVT, RE, RET> self() {
        return this;
    }
}