package com.bio4j.model.ucsc.vertices;

import com.bio4j.model.ucsc.UCSCGenesGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.ucsc_uniprot.edges.UCSCGenesChromosomeProtein;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;
import java.util.stream.Stream;

public final class UCSCGenesChromosome<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends UCSCGenesGraph.UCSCGenesVertex<
        UCSCGenesChromosome<I, RV, RVT, RE, RET>,
        UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeType,
        I, RV, RVT, RE, RET
        > {

    public UCSCGenesChromosome(RV vertex, UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeType type) {
        super(vertex, type);
    }

    @Override
    public UCSCGenesChromosome<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


    // outgoinh
    public Optional<Stream<UCSCGenesChromosomeProtein<I, RV, RVT, RE, RET>>> ucscGenesChromosomeProtein_out() {
        return outManyOptional(graph().ucscGenesUniProtGraph().UCSCGenesChromosomeProtein());
    }

    public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> ucscGenesChromosomeProtein_outV() {
        return outManyOptionalV(graph().ucscGenesUniProtGraph().UCSCGenesChromosomeProtein());
    }


}
