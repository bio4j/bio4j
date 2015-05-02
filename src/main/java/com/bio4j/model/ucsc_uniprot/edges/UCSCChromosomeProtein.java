package com.bio4j.model.ucsc_uniprot.edges;

import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc_uniprot.UCSCUniProtGraph;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

public final class UCSCChromosomeProtein<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCUniProtGraph.UCSCUniProtEdge<
                // src
                UCSCChromosome<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                UCSCGraph<I, RV, RVT, RE, RET>,
                // edge
                UCSCChromosomeProtein<I, RV, RVT, RE, RET>,
                UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCChromosomeProteinType,
                //tgt
                Protein<I, RV, RVT, RE, RET>,
                UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                UniProtGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public UCSCChromosomeProtein(RE edge, UCSCUniProtGraph<I, RV, RVT, RE, RET>.UCSCChromosomeProteinType type) {

        super(edge, type);
    }

    public String alignID() {
        return get(type().alignID);
    }

    public Long cdsStart() {
        return get(type().cdsStart);
    }


    public Long csdEnd() {
        return get(type().csdEnd);
    }


    public Long txStart() {
        return get(type().txStart);
    }

    public Long txEnd() {
        return get(type().txEnd);
    }

    public String strand() {
        return get(type().strand);
    }

    @Override
    public UCSCChromosomeProtein<I, RV, RVT, RE, RET> self() {
        return this;
    }
}