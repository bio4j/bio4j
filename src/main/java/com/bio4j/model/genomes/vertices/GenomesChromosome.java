package com.bio4j.model.genomes.vertices;

import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;
import java.util.stream.Stream;

public final class GenomesChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends GenomesGraph.GenomesVertex<
        GenomesChromosome<I, RV, RVT, RE, RET>,
        GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType,
        I, RV, RVT, RE, RET
        > {

    public GenomesChromosome(RV vertex, GenomesGraph<I, RV, RVT, RE, RET>.GenomesChromosomeType type) {
        super(vertex, type);
    }

    @Override
    public GenomesChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


    // outgoing
//    public Optional<Stream<GenomesChromosomeProtein<I, RV, RVT, RE, RET>>> GenomesChromosomeProtein_out() {
//        return outManyOptional(graph().UCSCUniProtGraph().GenomesChromosomeProtein());
//    }
//
//    public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> GenomesChromosomeProtein_outV() {
//        return outManyOptionalV(graph().UCSCUniProtGraph().GenomesChromosomeProtein());
//    }


}
