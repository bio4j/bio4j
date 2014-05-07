package com.bio4j.model.uniprot.relationships.references;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.references.Reference;

import com.bio4j.model.properties.Date;

/*

  Here goes as properties that which is common to all references. AFAIK only `Date`
*/
public interface Cited extends Relationship <
  
  Protein, Protein.Type, // source
  Cited, Cited.Type, // rel
  Reference, Reference.Type // target

>,

  // properties
  Date<Cited, Cited.Type>
{

  public static Type TYPE = Type.cited;

  public static enum Type implements RelationshipType <

    Protein, Protein.Type,
    Cited, Cited.Type,
    Reference, Reference.Type

  >
  {
    cited;

    public Arity arity() { return Arity.manyToMany; }

    public Protein.Type sourceType() { return Protein.TYPE; }
    public Reference.Type targetType() { return Reference.TYPE; }

    public Type value() { return cited; }
  }
}