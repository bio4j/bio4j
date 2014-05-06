package com.bio4j.model.go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.go.nodes.GoTerm;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface PartOf extends Relationship <
  GoTerm, GoTerm.Type,
  PartOf,  PartOf.Type,
  GoTerm, GoTerm.Type
> {

  public GoTerm source();
  public GoTerm target();

  public static Type TYPE = Type.partOfGo;
  public static enum Type implements RelationshipType <
    GoTerm, GoTerm.Type,
    PartOf,  PartOf.Type,
    GoTerm, GoTerm.Type
  > {

    partOfGo;

    public Arity arity() { return Arity.manyToMany; } // TODO review arity

    public Type value() { return partOfGo; }
    public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }
}
