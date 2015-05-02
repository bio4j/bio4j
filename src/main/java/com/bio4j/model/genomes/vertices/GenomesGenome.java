package com.bio4j.model.genomes.vertices;

import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.genomes.GenomesGraph;
import com.bio4j.model.genomes.edges.GenomesGenomeChromosome;

import java.util.Optional;
import java.util.stream.Stream;



public final class GenomesGenome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends GenomesGraph.GenomesVertex<
        GenomesGenome<I, RV, RVT, RE, RET>,
        GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType,
        I, RV, RVT, RE, RET
        > {

    public GenomesGenome(RV vertex, GenomesGraph<I, RV, RVT, RE, RET>.GenomesGenomeType type) {
        super(vertex, type);
    }

    @Override
    public GenomesGenome<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


   // outgoing
    public Optional<Stream<GenomesGenomeChromosome<I, RV, RVT, RE, RET>>> GenomesGenomeChromosome_out() {
        return outManyOptional(graph().GenomesGenomeChromosome());
    }

    public Optional<Stream<GenomesChromosome<I, RV, RVT, RE, RET>>> GenomesGenomeChromosome_outV() {
        return outManyOptionalV(graph().GenomesGenomeChromosome());
    }


}

