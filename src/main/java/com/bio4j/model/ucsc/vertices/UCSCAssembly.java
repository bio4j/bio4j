package com.bio4j.model.ucsc.vertices;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.ucsc.edges.UCSCAssemblyChromosome;

import java.util.Optional;
import java.util.stream.Stream;



public final class UCSCAssembly<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends UCSCGraph.UCSCVertex<
        UCSCAssembly<I, RV, RVT, RE, RET>,
        UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType,
        I, RV, RVT, RE, RET
        > {

    public UCSCAssembly(RV vertex, UCSCGraph<I, RV, RVT, RE, RET>.UCSCAssemblyType type) {
        super(vertex, type);
    }

    @Override
    public UCSCAssembly<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


   // outgoing
    public Optional<Stream<UCSCAssemblyChromosome<I, RV, RVT, RE, RET>>> UCSCAssemblyChromosome_out() {
        return outManyOptional(graph().UCSCAssemblyChromosome());
    }

    public Optional<Stream<UCSCChromosome<I, RV, RVT, RE, RET>>> UCSCAssemblyChromosome_outV() {
        return outManyOptionalV(graph().UCSCAssemblyChromosome());
    }


}

