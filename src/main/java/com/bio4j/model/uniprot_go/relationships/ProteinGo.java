package com.bio4j.model.uniprot_go.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 */
public interface ProteinGo extends Relationship <
  Protein, Protein.type,
  ProteinGo, ProteinGo.type,
  GoTerm, GoTerm.type
> {

  public static type TYPE = type.proteinGo;
  public static enum type implements RelationshipType <
    Protein, Protein.type,
    ProteinGo, ProteinGo.type,
    GoTerm, GoTerm.type
  > {
    proteinGo;
    public type value() { return proteinGo; }
    public arity arity() { return arity.manyToMany; }
  }

  // TODO: migrate
  public String evidence();

  @Override
  public Protein source();
  @Override
  public GoTerm target();
}