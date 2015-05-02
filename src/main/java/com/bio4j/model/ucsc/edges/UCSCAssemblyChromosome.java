package com.bio4j.model.ucsc.edges;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCAssembly;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;


public final class UCSCAssemblyChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCGraph.UCSCEdge<
                // src
                UCSCAssembly<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType,
                // edge
                UCSCAssemblyChromosome<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyChromosomeType,
                //tgt
                UCSCChromosome<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public UCSCAssemblyChromosome(RE edge, UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyChromosomeType type) {

        super(edge, type);
    }


    @Override
    public UCSCAssemblyChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }
}