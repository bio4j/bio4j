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

  NCBITaxon, NCBITaxon.type,
  Parent, Parent.type,
  NCBITaxon, NCBITaxon.type

> 
{

  @Override
  public NCBITaxon source();
  @Override
  public NCBITaxon target();

  public static type TYPE = type.parent;
  public static enum type implements RelationshipType <
    NCBITaxon, NCBITaxon.type,
    Parent, Parent.type,
    NCBITaxon, NCBITaxon.type
  > {
    parent;
    public type value() { return parent; }
    public NCBITaxon.type sourceType() { return NCBITaxon.TYPE; }
    public NCBITaxon.type targetType() { return NCBITaxon.TYPE; }
    public arity arity() { return arity.manyToOne; }
  }  
}
