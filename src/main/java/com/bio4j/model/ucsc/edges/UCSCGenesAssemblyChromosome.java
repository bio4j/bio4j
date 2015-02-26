package com.bio4j.model.ucsc.edges;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ucsc.UCSCGenesGraph;
import com.bio4j.model.ucsc.vertices.UCSCGenesAssembly;
import com.bio4j.model.ucsc.vertices.UCSCGenesChromosome;


public final class UCSCGenesAssemblyChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCGenesGraph.UCSCGenesEdge<
                // src
                UCSCGenesAssembly<I, RV, RVT, RE, RET>,
                UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesAssemblyType,
                // edge
                UCSCGenesAssemblyChromosome<I, RV, RVT, RE, RET>,
                UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesAssemblyChromosomeType,
                //tgt
                UCSCGenesChromosome<I, RV, RVT, RE, RET>,
                UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeType,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public UCSCGenesAssemblyChromosome(RE edge, UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesAssemblyChromosomeType type) {

        super(edge, type);
    }


    @Override
    public UCSCGenesAssemblyChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }
}