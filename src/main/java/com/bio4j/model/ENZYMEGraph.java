/*
  # ENZYME

  This graph includes all Enzyme terms that are included in the ExPASy ENZYME database but **not** those that have been either _transferred_ or _deleted_.

  You can get more information about the Enzyme database from its [website](http://enzyme.expasy.org/), in particular

  - **[Enzyme description and sample entry](http://enzyme.expasy.org/enzyme_details.html)**
  - the **[User manual](http://enzyme.expasy.org/enzuser.txt)**, which contains a more precise description of the data model

  The main entity here are `Enzyme`s, which are categorized into a hierarchy of classes. There are other graphs which add edges between `Enzyme`s and other entitities such as proteins.
*/
package com.bio4j.model;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

public final class ENZYMEGraph<V,E> extends TypedGraph<ENZYMEGraph<V,E>,V,E> {

  public ENZYMEGraph(UntypedGraph<V,E> graph) { super(graph); }

  @Override
  public final ENZYMEGraph<V,E> self() { return this; }

  /*
    ## Enzymes
  */
  public final class Enzyme extends Vertex<Enzyme> {

    private Enzyme(V raw) { super(raw, enzyme); }

    @Override public final Enzyme self() { return this; }
  }

  public final EnzymeType enzyme = new EnzymeType();
  public final class EnzymeType extends VertexType<Enzyme> {

    public final Enzyme fromRaw(V raw) { return new Enzyme(raw); }

    /*
      ### ID

      The ENZYME ID of this enzyme, as a `String`. This property is indexed for unique matches.
    */
    public final ID id = new ID();
    public final class ID extends Property<String> implements FromAtMostOne, ToOne {

      private ID() { super(String.class); }

      public final Index index = new Index();
      public final class Index extends UniqueIndex<ID,String> {

        private Index() { super(id); }
      }
    }

    /*
      ### Cofactors

      The cofactors for this enzyme, stored in an array of `String`s.
    */
    public final Cofactors cofactors = new Cofactors();
    public final class Cofactors extends Property<String[]> implements FromAny {
      private Cofactors() { super(String[].class); }
    }

    /*
      ### Comment

      Enzymes have sometimes a text comment; this property will have as value that comment stored in one `String`.
    */
    public final Comment comment = new Comment();
    public final class Comment extends Property<String> implements FromAny {
      private Comment() { super(String.class); }
    }

    /*
      ### Name

      The (official) name of this enzyme.
    */
    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {

      private Name() { super(String.class); }
    }

    /*
      ### Alternate names

      Sometimes enzymes have alternate names; they are available here as an array of `String`s.
    */
    public final AlternateNames alternateNames = new AlternateNames();
    public final class AlternateNames extends Property<String[]> implements FromAny {

      private AlternateNames() { super(String[].class); }
    }

    /*
      ### Catalytic activity

      Reactions in which this enzyme takes part, described textually.
    */
    public final CatalyticActivity catalyticActivity = new CatalyticActivity();
    public final class CatalyticActivity extends Property<String[]> implements FromAny {

      private CatalyticActivity() { super(String[].class); }
    }
  }

  /*
    ## Enzyme classes, sub-classes, subsub-classes
  */
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
}
