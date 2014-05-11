package com.bio4j.model.enzymedb.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

// source and target
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.enzymedb.nodes.Enzyme;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface EnzymaticActivity extends Relationship <
  Protein, Protein.Type,
  EnzymaticActivity, EnzymaticActivity.Type,
  Enzyme, Enzyme.Type
> 
{

  public Protein source();
  public Enzyme target();

  public static Type TYPE = Type.enzymaticActivity;
  public static enum Type implements RelationshipType <
    Protein, Protein.Type,
    EnzymaticActivity, EnzymaticActivity.Type,
    Enzyme, Enzyme.Type
  > {
    
    enzymaticActivity;

    public Arity arity() { return Arity.manyToMany; }

    public Type value() { return enzymaticActivity; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Enzyme.Type targetType() { return Enzyme.TYPE; }
    
  }

}
