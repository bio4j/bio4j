package com.bio4j.model.enzymedb.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source and target
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.enzymedb.nodes.Enzyme;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface EnzymaticActivity extends Relationship <
  Protein, Protein.Type,
  EnzymaticActivity, EnzymaticActivity.Type,
  Enzyme, Enzyme.Type
> {

  public static Type TYPE = Type.enzymaticActivity;
  public static enum Type implements RelationshipType <
    Protein, Protein.Type,
    EnzymaticActivity, EnzymaticActivity.Type,
    Enzyme, Enzyme.Type
  > {
    enzymaticActivity;
    public Type value() { return enzymaticActivity; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Enzyme.Type targetType() { return Enzyme.TYPE; }
    public Arity arity() { return Arity.manyToMany; }
  }

  public Protein source();
  public Enzyme target();
}