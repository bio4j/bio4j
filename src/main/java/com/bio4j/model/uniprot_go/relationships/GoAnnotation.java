package com.bio4j.model.uniprot_go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com> 
 */
public interface GoAnnotation extends Relationship <
  Protein, Protein.type,
  GoAnnotation, GoAnnotation.type,
  GoTerm, GoTerm.Type
> {

  public static type TYPE = type.goAnnotation;
  public static enum type implements RelationshipType <
    Protein, Protein.type,
    GoAnnotation, GoAnnotation.type,
    GoTerm, GoTerm.Type
  > {
    goAnnotation;
    public type value() { return goAnnotation; }
    public arity arity() { return arity.manyToMany; }
    public Protein.type sourceType() { return Protein.TYPE; }
    public GoTerm.Type targetType() { return GoTerm.TYPE; }
  }

  // TODO: migrate
  public String evidence();

  @Override
  public Protein source();
  @Override
  public GoTerm target();
}