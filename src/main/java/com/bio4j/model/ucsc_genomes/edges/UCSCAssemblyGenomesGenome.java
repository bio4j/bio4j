package com.bio4j.model.ucsc_genomes.edges;

import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.vertices.GenomesGenome;
import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCAssembly;
import com.bio4j.model.ucsc_genomes.UCSCGenomesGraph;
import com.bio4j.angulillos.UntypedGraph;

public final class UCSCAssemblyGenomesGenome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCGenomesGraph.UCSCUniProtEdge<
                // src
                UCSCAssembly<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType,
                UCSCGraph<I, RV, RVT, RE, RET>,
                // edge
                UCSCAssemblyGenomesGenome<I, RV, RVT, RE, RET>,
                UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCAssemblyGenomesGenomeType,
                //tgt
                GenomesGenome<I, RV, RVT, RE, RET>,
                GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
                GenomesGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public UCSCAssemblyGenomesGenome(RE edge, UCSCGenomesGraph<I, RV, RVT, RE, RET>.UCSCAssemblyGenomesGenomeType type) {
        super(edge, type);
    }


    @Override
    public UCSCAssemblyGenomesGenome<I, RV, RVT, RE, RET> self() {
        return this;
    }
}