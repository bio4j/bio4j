package com.bio4j.model.uniprot.edges;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.CommentType;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.angulillos.UntypedGraph;

/**
* Created by ppareja on 7/28/2014.
*/
public final class ProteinComment <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends
UniProtGraph.UniProtEdge<
Protein<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
ProteinComment<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.ProteinCommentType,
CommentType<I, RV, RVT, RE, RET>, UniProtGraph<I, RV, RVT, RE, RET>.CommentTypeType,
I, RV, RVT, RE, RET
> {

  public ProteinComment(RE edge, UniProtGraph<I, RV, RVT, RE, RET>.ProteinCommentType type) {

    super(edge, type);
  }

  @Override
  public ProteinComment<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
