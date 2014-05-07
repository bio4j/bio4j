package com.bio4j.model.isoforms.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

// source, target
import com.bio4j.model.isoforms.nodes.AlternativeProduct;
import com.bio4j.model.isoforms.nodes.Isoform;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface IsoformEventGenerator extends Relationship <
  Isoform, Isoform.Type,
  IsoformEventGenerator,  IsoformEventGenerator.Type,
  AlternativeProduct, AlternativeProduct.Type
> 
{

  public Isoform source();
  public AlternativeProduct target();

  public static Type TYPE = Type.isoformEventGenerator;
  public static enum Type implements RelationshipType <
    Isoform, Isoform.Type,
    IsoformEventGenerator,  IsoformEventGenerator.Type,
    AlternativeProduct, AlternativeProduct.Type
  > {
    isoformEventGenerator;

    // TODO: review
    public Arity arity() { return Arity.manyToMany; }

    public Type value() { return isoformEventGenerator; }

    public Isoform.Type sourceType() { return Isoform.TYPE; }
    public AlternativeProduct.Type targetType() { return AlternativeProduct.TYPE; }
  }
}
