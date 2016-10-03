
# UniProt ENZYME annotations

UniProt annotates entries, proteins, domains and whatnot with ENZYME classes, subclasses, subsubclasses or enzymes.

## What are these annotations?

The only documentation for this is included in these [release notes](http://www.uniprot.org/help/2007/10/23/release). Quoting from there:

>  In the UniProt Knowledgebase some enzymes are assigned so-called partial EC numbers where part of the numbers are replaced by dashes (e.g. EC 3.4.24.-). This happens in the following situations:
>
>  1. The catalytic activity of the protein is not known exactly.
>  2. The protein catalyzes a reaction that is known, but not yet included in the IUBMB EC list.

Now compare that with [P15291 xml](http://www.uniprot.org/uniprot/P15291.xml). There we have

- `<ecNumber>2.4.1.-</ecNumber>` for the `<protein>` element
- *Precise* enzyme codes for domains, which are modeled as just *names* for the protein: `<ecNumber>2.4.1.22</ecNumber>` for `Lactose synthase A protein`, or `<ecNumber>2.4.1.90</ecNumber>` for `N-acetyllactosamine synthase`

Then as cross-reference to ENZYME we have all of them together, with no scope or anything

``` xml
<dbReference type="EC" id="2.4.1.-"/>
<dbReference type="EC" id="2.4.1.22"/>
<dbReference type="EC" id="2.4.1.90"/>
<dbReference type="EC" id="2.4.1.38"/>
```

If you search for the ENZYME cross-ref description in [this somewhat hidden flat-text file](http://www.uniprot.org/docs/dbxref) you will read there "*Implicit; All UniProtKB entries that contain >=1 EC numbers in their description lines*".

So clearly this is not any of 1., 2. quoted above; it's just a bad data model where entities are hidden behind strings. All in all, these enzyme annotations are unreliable, badly modeled, and hard to interpret. Our choice is to have edges linking the canonical protein of an entry to ENZYME classes, subclasses, subsubclasses, or enzymes.


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
```


## Enzyme protein annotations

Edges going to

1. classes
2. subclasses
3. subsubclasses
4. enzymes

The names are in singular, start with Enzyme and correspond to the target type.


```java
  public final class EnzymeClass extends GenericEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    EnzymeClass,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.EnzymeClass
  >
  {

    private EnzymeClass(E edge) { super(edge, enzymeClass); }

    @Override public final EnzymeClass self() { return this; }
  }

  public final EnzymeClassType enzymeClass = new EnzymeClassType();
  public final class EnzymeClassType extends GenericEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    EnzymeClass,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.EnzymeClass
  >
  implements FromAny, ToAny {

    private EnzymeClassType() { super(uniProtGraph.protein, enzymeGraph.enzymeClass); }

    @Override public final EnzymeClass fromRaw(E edge) { return new EnzymeClass(edge); }
  }

  public final class EnzymeSubClass extends GenericEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    EnzymeSubClass,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.EnzymeSubClass
  >
  {

    private EnzymeSubClass(E edge) { super(edge, enzymeSubClass); }

    @Override public final EnzymeSubClass self() { return this; }
  }

  public final EnzymeSubClassType enzymeSubClass = new EnzymeSubClassType();
  public final class EnzymeSubClassType extends GenericEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    EnzymeSubClass,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.EnzymeSubClass
  >
  implements FromAny, ToAny {

    private EnzymeSubClassType() { super(uniProtGraph.protein, enzymeGraph.enzymeSubClass); }

    @Override public final EnzymeSubClass fromRaw(E edge) { return new EnzymeSubClass(edge); }
  }

  public final class EnzymeSubSubClass extends GenericEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    EnzymeSubSubClass,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.EnzymeSubSubClass
  >
  {

    private EnzymeSubSubClass(E edge) { super(edge, enzymeSubSubClass); }

    @Override public final EnzymeSubSubClass self() { return this; }
  }

  public final EnzymeSubSubClassType enzymeSubSubClass = new EnzymeSubSubClassType();
  public final class EnzymeSubSubClassType extends GenericEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    EnzymeSubSubClass,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.EnzymeSubSubClass
  >
  implements FromAny, ToAny {

    private EnzymeSubSubClassType() { super(uniProtGraph.protein, enzymeGraph.enzymeSubSubClass); }

    @Override public final EnzymeSubSubClass fromRaw(E edge) { return new EnzymeSubSubClass(edge); }
  }

  public final class Enzyme extends GenericEdge<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Enzyme,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.Enzyme
  >
  {

    private Enzyme(E edge) { super(edge, enzyme); }

    @Override public final Enzyme self() { return this; }
  }

  public final EnzymeType enzyme = new EnzymeType();
  public final class EnzymeType extends GenericEdgeType<
    UniProtGraph<V,E>, UniProtGraph<V,E>.Protein,
    Enzyme,
    ENZYMEGraph<V,E>, ENZYMEGraph<V,E>.Enzyme
  >
  implements FromAny, ToAny {

    private EnzymeType() { super(uniProtGraph.protein, enzymeGraph.enzyme); }

    @Override public final Enzyme fromRaw(E edge) { return new Enzyme(edge); }
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