
# ENZYME

This graph includes all Enzyme terms that are included in the ExPASy ENZYME database but **not** those that have been either _transferred_ or _deleted_.

You can get more information about the Enzyme database from its [website](http://enzyme.expasy.org/), in particular

- **[Enzyme description and sample entry](http://enzyme.expasy.org/enzyme_details.html)**
- the **[User manual](http://enzyme.expasy.org/enzuser.txt)**, which contains a more precise description of the data model

The main entity here are `Enzyme`s, which are categorized into a hierarchy of classes. There are other graphs which add edges between `Enzyme`s and other entitities such as proteins.


```java
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class ENZYMEGraph<V,E> extends TypedGraph<ENZYMEGraph<V,E>,V,E> {

  public ENZYMEGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final ENZYMEGraph<V,E> self() { return this; }
```


## Enzymes


```java
  public final class Enzyme extends Vertex<Enzyme> {

    private Enzyme(V raw) { super(raw, enzyme); }

    @Override public final Enzyme self() { return this; }
  }

  public final EnzymeType enzyme = new EnzymeType();
  public final class EnzymeType extends VertexType<Enzyme> {

    public final Enzyme fromRaw(V raw) { return new Enzyme(raw); }
```


### ID

The ENZYME ID of this enzyme, as a `String`. This property is indexed for unique matches.


```java
    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {

      private ID() { super(String.class); }

      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID,String> {

        private Index() { super(id); }
      }
    }
```


### Cofactors

The cofactors for this enzyme, stored in an array of `String`s.


```java
    public final Cofactors cofactors = new Cofactors();
    public final class Cofactors extends Property<String[]> implements FromAny {

      private Cofactors() { super(String[].class); }
    }
```


### Comments

Enzymes have sometimes text comments; this property will have them as value, stored in a `String` array.


```java
    public final Comments comments = new Comments();
    public final class Comments extends Property<String[]> implements FromAny {

      private Comments() { super(String[].class); }
    }
```


### Name

The (official) name of this enzyme.


```java
    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }
```


### Alternate names

Sometimes enzymes have alternate names; they are available here as an array of `String`s.


```java
    public final AlternateNames alternateNames = new AlternateNames();
    public final class AlternateNames extends Property<String[]> implements FromAny {

      private AlternateNames() { super(String[].class); }
    }
```


### Catalytic activity

Reactions in which this enzyme takes part, described textually.


```java
    public final CatalyticActivity catalyticActivity = new CatalyticActivity();
    public final class CatalyticActivity extends Property<String[]> implements FromAny {

      private CatalyticActivity() { super(String[].class); }
    }
  }
```


## Enzyme classes, sub-classes, subsub-classes

Classes are a set of sub-classes, who are a set of subsub-classes, who are a set of enzymes.

They are defined in [enzclass.txt](ftp://ftp.expasy.org/databases/enzyme/enzclass.txt) from the ENZYME ftp.


```java
  public final class EnzymeClass extends Vertex<EnzymeClass> {

    private EnzymeClass(V raw) { super(raw, enzymeClass); }

    @Override public final EnzymeClass self() { return this; }
  }

  public final EnzymeClassType enzymeClass = new EnzymeClassType();
  public final class EnzymeClassType extends VertexType<EnzymeClass> {

    public final EnzymeClass fromRaw(V raw) { return new EnzymeClass(raw); }
  }

  public final class EnzymeSubClass extends Vertex<EnzymeSubClass> {

    private EnzymeSubClass(V raw) { super(raw, enzymeSubClass); }

    @Override public final EnzymeSubClass self() { return this; }
  }

  public final EnzymeSubClassType enzymeSubClass = new EnzymeSubClassType();
  public final class EnzymeSubClassType extends VertexType<EnzymeSubClass> {

    public final EnzymeSubClass fromRaw(V raw) { return new EnzymeSubClass(raw); }
  }

  public final class EnzymeSubSubClass extends Vertex<EnzymeSubSubClass> {

    private EnzymeSubSubClass(V raw) { super(raw, enzymeSubSubClass); }

    @Override public final EnzymeSubSubClass self() { return this; }
  }

  public final EnzymeSubSubClassType enzymeSubSubClass = new EnzymeSubSubClassType();
  public final class EnzymeSubSubClassType extends VertexType<EnzymeSubSubClass> {

    public final EnzymeSubSubClass fromRaw(V raw) { return new EnzymeSubSubClass(raw); }
  }
```


### Sub-class, subsub-class edges

These edges go from an element to its members. The Enzyme ID structure `w.x.y.z` mirrors this structure, where each prefix ending in a dot determines one of these categories. Going up in this hierarchy corresponds to a less specific function. Note that annotations coming from other databases such as UniProt frequently point to enzyme classes, not enzymes themselves.


```java
  public final class SubClass extends Edge<EnzymeClass, SubClass, EnzymeSubClass> {

    private SubClass(E edge) { super(edge, subClass); }

    @Override
    public final SubClass self() { return this; }
  }

  public final SubClassType subClass = new SubClassType();
  public final class SubClassType extends EdgeType<EnzymeClass, SubClass, EnzymeSubClass> implements FromOne, ToAtLeastOne {

    private SubClassType() { super(enzymeClass, enzymeSubClass); }

    @Override
    public final SubClass fromRaw(E edge) { return new SubClass(edge); }
  }

  public final class SubSubClass extends Edge<EnzymeSubClass, SubSubClass, EnzymeSubSubClass> {

    private SubSubClass(E edge) { super(edge, subSubClass); }

    @Override
    public final SubSubClass self() { return this; }
  }

  public final SubSubClassType subSubClass = new SubSubClassType();
  public final class SubSubClassType extends EdgeType<EnzymeSubClass, SubSubClass, EnzymeSubSubClass> implements FromOne, ToAtLeastOne {

    private SubSubClassType() { super(enzymeSubClass, enzymeSubSubClass); }

    @Override
    public final SubSubClass fromRaw(E edge) { return new SubSubClass(edge); }
  }

  public final class Enzymes extends Edge<EnzymeSubSubClass, Enzymes, Enzyme> {

    private Enzymes(E edge) { super(edge, enzymes); }

    @Override
    public final Enzymes self() { return this; }
  }

  public final EnzymesType enzymes = new EnzymesType();
  public final class EnzymesType extends EdgeType<EnzymeSubSubClass, Enzymes, Enzyme> implements FromOne, ToAtLeastOne {

    private EnzymesType() { super(enzymeSubSubClass, enzyme); }

    @Override
    public final Enzymes fromRaw(E edge) { return new Enzymes(edge); }
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