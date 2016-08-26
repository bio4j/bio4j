
# UniProt ENZYME annotations

UniProt annotates *entries* with ENZYME (partial) codes. This graph adds an edge connecting the canonical protein of an entry with the ENZYME codes with which the entry is annotated.


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniProtENZYMEGraph<V,E> extends TypedGraph<UniProtENZYMEGraph<V,E>,V,E> {

  public UniProtENZYMEGraph(UniProtGraph<V,E> uniProtGraph, ENZYMEGraph<V,E> enzymeGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
    this.enzymeGraph = enzymeGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;
  public final ENZYMEGraph<V,E> enzymeGraph;

  @Override public final UniProtENZYMEGraph<V,E> self() { return this; }

  // TODO add edge here
}

```




[main/java/com/bio4j/model/UniProtGraph.java]: UniProtGraph.java.md
[main/java/com/bio4j/model/UniProtENZYMEGraph.java]: UniProtENZYMEGraph.java.md
[main/java/com/bio4j/model/NCBITaxonomyGraph.java]: NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ENZYMEGraph.java]: ENZYMEGraph.java.md
[main/java/com/bio4j/model/UniProtNCBITaxonomyGraph.java]: UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/GOGraph.java]: GOGraph.java.md
[main/java/com/bio4j/model/UniProtGOGraph.java]: UniProtGOGraph.java.md