package com.bio4j.model.uniref.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public interface UniRef100Member extends Relationship <

  Protein, Protein.Type,
  UniRef100Member, UniRef100Member.Type,
  Protein, Protein.Type

> {

  public static Type TYPE = Type.uniRef100Member;
  public static enum Type implements RelationshipType <

    Protein, Protein.Type,
    UniRef100Member, UniRef100Member.Type,
    Protein, Protein.Type

  > {

    uniRef100Member;

    public Arity arity() { return Arity.manyToOne; }

    public Type value() { return uniRef100Member; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Protein.Type targetType() { return Protein.TYPE; }
  }  
}
