package com.bio4j.model.ucsc.vertices;

import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.ucsc_uniprot.edges.UCSCChromosomeProtein;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;
import java.util.stream.Stream;

public final class UCSCChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends UCSCGraph.UCSCVertex<
        UCSCChromosome<I, RV, RVT, RE, RET>,
        UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType,
        I, RV, RVT, RE, RET
        > {

    public UCSCChromosome(RV vertex, UCSCGraph<I, RV, RVT, RE, RET>.UCSCChromosomeType type) {
        super(vertex, type);
    }

    @Override
    public UCSCChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


    // outgoinh
    public Optional<Stream<UCSCChromosomeProtein<I, RV, RVT, RE, RET>>> UCSCChromosomeProtein_out() {
        return outManyOptional(graph().UCSCUniProtGraph().UCSCChromosomeProtein());
    }

    public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> UCSCChromosomeProtein_outV() {
        return outManyOptionalV(graph().UCSCUniProtGraph().UCSCChromosomeProtein());
    }


}
