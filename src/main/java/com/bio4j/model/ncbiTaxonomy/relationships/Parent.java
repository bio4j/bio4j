package com.bio4j.model.ncbiTaxonomy.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;

/**
 *
 * @author Pablo Pareja <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface Parent extends Relationship <

  NCBITaxon, NCBITaxon.Type,
  Parent, Parent.Type,
  NCBITaxon, NCBITaxon.Type

> 
{

  @Override
  public NCBITaxon source();
  @Override
  public NCBITaxon target();

  public static Type TYPE = Type.parent;
  public static enum Type implements RelationshipType <
    NCBITaxon, NCBITaxon.Type,
    Parent, Parent.Type,
    NCBITaxon, NCBITaxon.Type
  > {
    parent;
    public Type value() { return parent; }
    public NCBITaxon.Type sourceType() { return NCBITaxon.TYPE; }
    public NCBITaxon.Type targetType() { return NCBITaxon.TYPE; }
    public Arity arity() { return Arity.manyToOne; }
  }  
}
