package com.bio4j.model.go.vertices;

// base types

import com.bio4j.model.go.GoGraph;
import com.bio4j.angulillos.*;
import com.bio4j.model.go.edges.SubOntology;

import java.util.stream.Stream;

/**
 * Rels into this singleton node represent subontologies.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public final class SubOntologies<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends GoGraph.GoVertex<
        SubOntologies<I, RV, RVT, RE, RET>,
        GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType,
        I, RV, RVT, RE, RET
        > {

    public SubOntologies(RV vertex, GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType type) {

        super(vertex, type);
    }

    @Override
    public SubOntologies<I, RV, RVT, RE, RET> self() {
        return this;
    }

    // rels

    //-----subontology-----
    public Stream<SubOntology<I, RV, RVT, RE, RET>> subontoloty_in(){
        return inMany(graph().SubOntology());
    }
    //-----subontology-----
    public Stream<GoTerm<I, RV, RVT, RE, RET>> subontoloty_inV(){
        return inManyV(graph().SubOntology());
    }


}
