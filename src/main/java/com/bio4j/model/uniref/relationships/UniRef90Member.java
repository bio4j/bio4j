package com.bio4j.model.uniref.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UniRef90Member extends Relationship <

  Protein, Protein.Type,
  UniRef90Member, UniRef90Member.Type,
  Protein, Protein.Type

> {

  public static Type TYPE = Type.uniRef90Member;
  public static enum Type implements RelationshipType <

    Protein, Protein.Type,
    UniRef90Member, UniRef90Member.Type,
    Protein, Protein.Type

  > {

    uniRef90Member;

    public Arity arity() { return Arity.manyToOne; }

    public Type value() { return uniRef90Member; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Protein.Type targetType() { return Protein.TYPE; }
  }  
}
