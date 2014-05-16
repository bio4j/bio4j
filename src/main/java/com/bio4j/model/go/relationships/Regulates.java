package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;


import com.bio4j.model.go.nodes.Term;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Regulates extends Relationship <
  Term, Term.Type,
  Regulates,  Regulates.Type,
  Term, Term.Type
> {

  public static Type TYPE = Type.regulates; 

  @Override public default Type type() { return TYPE; }

  public static enum Type implements RelationshipType.ManyToMany <
    Term, Term.Type,
    Regulates,  Regulates.Type,
    Term, Term.Type
  >
  {    
    regulates;

    public Type value() { return regulates; }
    public Term.Type sourceType() { return Term.TYPE; }
    public Term.Type targetType() { return Term.TYPE; }
  }
}
