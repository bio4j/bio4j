package com.bio4j.model.ncbiTaxonomy.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Parent extends Relationship <
  NCBITaxon, NCBITaxon.Type,
  Parent, Parent.Type,
  NCBITaxon, NCBITaxon.Type
> 
{

  public NCBITaxon source();
  public NCBITaxon target();

  public static Type TYPE = Type.parent;
  public static enum Type implements RelationshipType <
    NCBITaxon, NCBITaxon.Type,
    Parent, Parent.Type,
    NCBITaxon, NCBITaxon.Type
  > {

    parent;

    public Arity arity() { return Arity.manyToOne; }
    
    public Type value() { return parent; }
    public NCBITaxon.Type sourceType() { return NCBITaxon.TYPE; }
    public NCBITaxon.Type targetType() { return NCBITaxon.TYPE; }
  }  
}
