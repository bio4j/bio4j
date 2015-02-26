package com.bio4j.model.ucsc.vertices;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ucsc.UCSCGenesGraph;
import com.bio4j.model.ucsc.edges.UCSCGenesAssemblyChromosome;

import java.util.Optional;
import java.util.stream.Stream;



public final class UCSCGenesAssembly<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends UCSCGenesGraph.UCSCGenesVertex<
        UCSCGenesAssembly<I, RV, RVT, RE, RET>,
        UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesAssemblyType,
        I, RV, RVT, RE, RET
        > {

    public UCSCGenesAssembly(RV vertex, UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesAssemblyType type) {
        super(vertex, type);
    }

    @Override
    public UCSCGenesAssembly<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


   // outgoing
    public Optional<Stream<UCSCGenesAssemblyChromosome<I, RV, RVT, RE, RET>>> ucscGenesAssemblyChromosome_out() {
        return outManyOptional(graph().UCSCGenesAssemblyChromosome());
    }

    public Optional<Stream<UCSCGenesChromosome<I, RV, RVT, RE, RET>>> ucscGenesAssemblyChromosome_outV() {
        return outManyOptionalV(graph().UCSCGenesAssemblyChromosome());
    }


}

