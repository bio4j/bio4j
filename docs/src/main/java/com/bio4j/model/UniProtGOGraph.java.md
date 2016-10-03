
# UniProt GO annotations

This graph contains an edge connecting a protein with their Gene Ontology annotations (terms).


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class UniProtGOGraph<V,E> extends TypedGraph<UniProtGOGraph<V,E>,V,E> {

  public UniProtGOGraph(UniProtGraph<V,E> uniProtGraph, GOGraph<V,E> goGraph) {

    super(uniProtGraph.raw());
    this.uniProtGraph = uniProtGraph;
    this.goGraph = goGraph;
  }

  public final UniProtGraph<V,E> uniProtGraph;
  public final GOGraph<V,E> goGraph;

  @Override public final UniProtGOGraph<V,E> self() { return this; }

  public final class Annotation extends GenericEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Annotation,
    GOGraph<V,E>, GOGraph<V,E>.Term
  >
  {
    private Annotation(E edge) { super(edge, annotation); }
    @Override public final Annotation self() { return this; }
  }

  public final AnnotationType annotation = new AnnotationType();
  public final class AnnotationType extends GenericEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Annotation,
    GOGraph<V,E>, GOGraph<V,E>.Term
  >
  implements FromAny, ToAny {

    private AnnotationType() { super(uniProtGraph.protein, goGraph.term); }
    @Override public final Annotation fromRaw(E edge) { return new Annotation(edge); }
  }
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