package com.bio4j.model.ucsc.edges;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.vertices.UCSCAssembly;
import com.bio4j.model.ucsc.vertices.UCSCChromosome;
import com.bio4j.model.ucsc.vertices.UCSCFragment;


public final class UCSCChromosomeFragment<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UCSCGraph.UCSCEdge<
                // src
                UCSCChromosome<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
                // edge
                UCSCChromosomeFragment<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeFragmentType,
                //tgt
                UCSCFragment<I, RV, RVT, RE, RET>,
                UCSCGraph<I, RV, RVT, RE, RET>.UCSCFragmentType,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public UCSCChromosomeFragment(RE edge, UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeFragmentType type) {

        super(edge, type);
    }


    @Override
    public UCSCChromosomeFragment<I, RV, RVT, RE, RET> self() {
        return this;
    }
}