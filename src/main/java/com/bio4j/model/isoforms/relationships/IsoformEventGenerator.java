package com.bio4j.model.isoforms.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

// source, target
import com.bio4j.model.isoforms.nodes.AlternativeProduct;
import com.bio4j.model.isoforms.nodes.Isoform;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface IsoformEventGenerator extends Relationship <
  Isoform, Isoform.Type,
  IsoformEventGenerator,  IsoformEventGenerator.Type,
  AlternativeProduct, AlternativeProduct.Type
> 
{

  @Override  
  public Isoform source();
  @Override
  public AlternativeProduct target();


  public static Type TYPE = Type.isoformEventGenerator;
  public static enum Type implements RelationshipType <
    Isoform, Isoform.Type,
    IsoformEventGenerator,  IsoformEventGenerator.Type,
    AlternativeProduct, AlternativeProduct.Type
  > {
    isoformEventGenerator;
    public Type value() { return isoformEventGenerator; }

    public Isoform.Type sourceType() { return Isoform.TYPE; }
    public AlternativeProduct.Type targetType() { return AlternativeProduct.TYPE; }
    // TODO: review
    public Arity arity() { return Arity.manyToMany; }
  }
}
