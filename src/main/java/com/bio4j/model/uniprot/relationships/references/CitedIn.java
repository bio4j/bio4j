package com.bio4j.model.uniprot.relationships.references;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot.nodes.references.Reference;

public interface CitedIn extends Relationship <
  
  Protein, Protein.Type, // source
  CitedIn, CitedIn.Type, // rel
  Reference, Reference.Type // target

>
{

  public static Type TYPE = Type.citedIn;

  public static enum Type implements RelationshipType <

    Protein, Protein.Type,
    CitedIn, CitedIn.Type,
    Reference, Reference.Type

  >
  {
    citedIn;

    public Arity arity() { return Arity.manyToMany; }

    public Protein.Type sourceType() { return Protein.TYPE; }
    public Reference.Type targetType() { return Reference.TYPE; }

    public Type value() { return citedIn; }
  }
}