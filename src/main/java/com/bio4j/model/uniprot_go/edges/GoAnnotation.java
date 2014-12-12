package com.bio4j.model.uniprot_go.edges;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.angulillos.UntypedGraph;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class GoAnnotation<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
        extends
        UniProtGoGraph.UniProtGoEdge<
                // src
                Protein<I, RV, RVT, RE, RET>,
                UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                UniProtGraph<I, RV, RVT, RE, RET>,
                // edge
                GoAnnotation<I, RV, RVT, RE, RET>,
                UniProtGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType,
                //tgt
                GoTerm<I, RV, RVT, RE, RET>,
                GoGraph<I, RV, RVT, RE, RET>.GoTermType,
                GoGraph<I, RV, RVT, RE, RET>,
                // raw stuff
                I, RV, RVT, RE, RET
                > {

    public GoAnnotation(RE edge, UniProtGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType type) {

        super(edge, type);
    }

    @Override
    public GoAnnotation<I, RV, RVT, RE, RET> self() {
        return this;
    }
}