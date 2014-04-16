package com.bio4j.model.uniprot.relationships.references;

import java.util.List;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// properties
import com.bio4j.model.properties.Institute;
import com.bio4j.model.properties.Country;

import com.bio4j.model.uniprot.nodes.references.Theses;
import com.bio4j.model.uniprot.nodes.references.Reference;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Thesis extends Relationship <

  Reference, Reference.Type,
  Thesis, Thesis.Type,
  Theses, Theses.Type

>,
  
  // properties
  Institute<Thesis, Thesis.Type>,
  Country<Thesis, Thesis.Type>
{
  
  public static Type TYPE = Type.thesis;
  
  public static enum Type implements RelationshipType <
    
    Reference, Reference.Type,
    Thesis, Thesis.Type,
    Theses, Theses.Type
  >
  {
    thesis;

    // there is only one Theses node => many to one.
    public Arity arity() { return Arity.manyToOne; }

    public Reference.Type sourceType() { return Reference.TYPE; }
    public Theses.Type targetType() { return Theses.TYPE; }

    public Type value() { return thesis; }
  }
}
