package com.bio4j.model.go.relationships;

import java.util.List;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.RelTypes;

import com.bio4j.model.go.nodes.*;

// properties
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.Obsolete;
import com.bio4j.model.properties.Definition;
import com.bio4j.model.properties.AlternativeIds;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Term extends Relationship <
  GoTerm, GoTerm.Type,
  Term, Term.Type,
  GoNamespace, GoNamespace.Type
>,
  // properties
  Id<Term, Term.Type>,
  Name<Term, Term.Type>,
  Definition<Term, Term.Type>,
  Comment<Term, Term.Type>,
  Obsolete<Term, Term.Type>,
  AlternativeIds<Term, Term.Type>
{

  public static Type TYPE = Type.term;
  @Override public default Type type() { return TYPE; }
  
  public static enum Type implements RelTypes.ManyToOne <
    GoTerm, GoTerm.Type,
    Term, Term.Type,
    GoNamespace, GoNamespace.Type
  >
  {

    term;

    @Override public Type value() { return term; }
    @Override public GoTerm.Type sourceType() { return GoTerm.TYPE; }
    @Override public GoNamespace.Type targetType() { return GoNamespace.TYPE; }
  }
}