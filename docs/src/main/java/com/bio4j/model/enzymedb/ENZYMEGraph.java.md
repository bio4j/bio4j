
# ENZYME graph

This graph includes all Enzyme terms that are included in the ExPASy ENZYME database but **not** those that have been either _transferred_ or _deleted_.

You can get more information about the Enzyme database from its [website](http://enzyme.expasy.org/), in particular

- **[Enzyme description and sample entry](http://enzyme.expasy.org/enzyme_details.html)**
- the **[User manual](http://enzyme.expasy.org/enzuser.txt)**, which contains a more precise description of the data model

This graph has only vertices, of `Enzyme` type; There are other graphs which add edges between `Enzyme`s and other entitities such as proteins.


```java
package com.bio4j.model.enzymedb;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class ENZYMEGraph<V,E> extends TypedGraph<ENZYMEGraph<V,E>,V,E> {
```


## Vertices


### Enzyme


```java
  public final class EnzymeType extends VertexType<Enzyme> {
```


#### Enzyme id

The ENZYME id of this enzyme, as a `String`. This property is indexed.


```java
    public final Id id = new Id();
    public final ById byId = new ById();
    public final class Id extends Property<String> implements FromAtMostOne, ToOne {
      private Id() { super(String.class); }
    }
    public final class ById extends UniqueIndex<Id,String> {
      private ById() { super(id); }
    }
    public final EnzymeType enzyme = new EnzymeType();
```


#### Enzyme cofactors

The cofactors ids? for this enzyme, stored in an array of `String`s.


```java
    public final Cofactors cofactors = new Cofactors();
    public final class Cofactors extends Property<String[]> implements FromAny {
      private Cofactors() { super(String[].class); }
    }
```


    #### Enzyme comments

    Enzymes have sometimes a text comment; this property will have as value that comment stored in one `String`, which will be empty if there's no such comment.


```java
    public final Comment comment = new Comment();
    public final class Comment extends Property<String> implements FromAny {
      private Comment() { super(String.class); }
    }
```


#### Enzyme official name

_TO DO_ link to somewhere


```java
    public final OfficialName officialName = new OfficialName();
    public final class OfficialName extends Property<String> implements FromAny, ToOne {
      private OfficialName() { super(String.class); }
    }
```


#### Enzyme alternate names

Sometimes enzymes have alternate names (synonyms?). They are available here as an array of `String`s.


```java
    public final AlternateNames alternateNames = new AlternateNames();
    public final class AlternateNames extends Property<String[]> implements FromAny {
      private AlternateNames() { super(String[].class); }
    }
```


#### Enzyme catalytic activity

_TO DO_ explain this.


```java
    public final CatalyticActivity catalyticActivity = new CatalyticActivity();
    public final class CatalyticActivity extends Property<String> implements FromAny {
      private CatalyticActivity() { super(String.class); }
    }
```


#### Enzyme cross-references to ProSite

_TO DO_ explain this. Shuold this be a crossref?


```java
    public final PrositeCrossReferences prositeCrossReferences = new PrositeCrossReferences();
    public final class PrositeCrossReferences extends Property<String[]> implements FromAny {
      private PrositeCrossReferences() { super(String[].class); }
    }

    public final Enzyme fromRaw(V raw) { return new Enzyme(raw); }
  }
  public final EnzymeType enzyme = new EnzymeType();

  public final class Enzyme extends Vertex<Enzyme> {
    @Override public final Enzyme self() { return this; }
    private Enzyme(V raw) { super(raw, enzyme); }
  }

  public ENZYMEGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final ENZYMEGraph<V,E> self() { return this; }
}

```




[main/java/com/bio4j/model/enzymedb/ENZYMEGraph.java]: ENZYMEGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/go/GOGraph.java]: ../go/GOGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/unigene/UniGeneGraph.java]: ../unigene/UniGeneGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../uniprot/UniProtGraph.java.md