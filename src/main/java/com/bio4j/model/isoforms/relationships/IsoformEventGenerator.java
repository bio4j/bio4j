package com.bio4j.model.isoforms.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source, target
import com.bio4j.model.isoforms.nodes.AlternativeProduct;
import com.bio4j.model.isoforms.nodes.Isoform;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface IsoformEventGenerator extends Relationship <
  Isoform, Isoform.type,
  IsoformEventGenerator,  IsoformEventGenerator.type,
  AlternativeProduct, AlternativeProduct.type
> 
{

  public static type TYPE = type.isoformEventGenerator;
  public static enum type implements RelationshipType <
    Isoform, Isoform.type,
    IsoformEventGenerator,  IsoformEventGenerator.type,
    AlternativeProduct, AlternativeProduct.type
  > {
    isoformEventGenerator;
    public type value() { return isoformEventGenerator; }
    // TODO: review
    public arity arity() { return arity.manyToMany; }
  }

  @Override  
  public Isoform source();
  @Override
  public AlternativeProduct target();
}
