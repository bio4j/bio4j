
# UniProt NCBI taxonomy annotations

There are two interesnting pieces of information here:

1. the organism from which this protein comes
2. the host, for viruses and the like


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniProtNCBITaxonomyGraph<V,E> extends TypedGraph<UniProtNCBITaxonomyGraph<V,E>,V,E> {

  public UniProtNCBITaxonomyGraph(UniProtGraph<V,E> uniProtGraph, NCBITaxonomyGraph<V,E> ncbiTaxonomyGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
    this.ncbiTaxonomyGraph = ncbiTaxonomyGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;
  public final NCBITaxonomyGraph<V,E> ncbiTaxonomyGraph;

  @Override public final UniProtNCBITaxonomyGraph<V,E> self() { return this; }

  // TODO add edges here: organism which is toOne, and host about which I'm not sure
}

```




[main/java/com/bio4j/model/UniProtGraph.java]: UniProtGraph.java.md
[main/java/com/bio4j/model/UniProtENZYMEGraph.java]: UniProtENZYMEGraph.java.md
[main/java/com/bio4j/model/NCBITaxonomyGraph.java]: NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/UniRefGraph.java]: UniRefGraph.java.md
[main/java/com/bio4j/model/ENZYMEGraph.java]: ENZYMEGraph.java.md
[main/java/com/bio4j/model/UniProtNCBITaxonomyGraph.java]: UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/GOGraph.java]: GOGraph.java.md
[main/java/com/bio4j/model/UniProtGOGraph.java]: UniProtGOGraph.java.md
[main/java/com/bio4j/model/LinkGraph.java]: LinkGraph.java.md