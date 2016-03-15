package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.model.uniprot_enzymedb.edges.EnzymaticActivity;
import com.bio4j.model.uniprot_go.edges.GoAnnotation;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.model.uniprot_uniref.edges.*;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public final class Protein <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends
    UniProtGraph.UniProtVertex <
      Protein<I, RV, RVT, RE, RET>,
      UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
      I, RV, RVT, RE, RET
    >
{

  public Protein(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType type) {
    super(vertex, type);
  }

  @Override
  public Protein<I, RV, RVT, RE, RET> self() {
    return this;
  }
}
