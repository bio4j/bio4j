package com.bio4j.model.ucsc.vertices;

import com.bio4j.model.ucsc.UCSCGraph;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;
import java.util.stream.Stream;

public final class UCSCFragment<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends UCSCGraph.UCSCVertex<
        UCSCFragment<I, RV, RVT, RE, RET>,
        UCSCGraph<I, RV, RVT, RE, RET>.UCSCFragmentType,
        I, RV, RVT, RE, RET
        > {

    public UCSCFragment(RV vertex, UCSCGraph<I, RV, RVT, RE, RET>.UCSCFragmentType type) {
        super(vertex, type);
    }

    @Override
    public UCSCFragment<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // properties
    public String id() {
        return get(type().id);
    }


    // outgoing
//    public Optional<Stream<UCSCFragmentProtein<I, RV, RVT, RE, RET>>> UCSCFragmentProtein_out() {
//        return outManyOptional(graph().UCSCUniProtGraph().UCSCFragmentProtein());
//    }
//
//    public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> UCSCFragmentProtein_outV() {
//        return outManyOptionalV(graph().UCSCUniProtGraph().UCSCFragmentProtein());
//    }


}
