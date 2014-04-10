package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.isoforms.nodes.Isoform;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface ProteinIsoformInteraction extends Relationship <

  Protein, Protein.Type,
  ProteinIsoformInteraction, ProteinIsoformInteraction.Type,
  Isoform, Isoform.Type

> 
{

  @Override
  public Protein source();
  @Override
  public Isoform target();

  // TODO migrate to properties
  public String getOrganismsDiffer();
  public String getExperiments();
  public String getIntactId2();
  public String getIntactId1();

  public void setOrganismsDiffer(String value);
  public void setExperiments(String value);
  public void setIntactId2(String value);
  public void setIntactId1(String value);

  public static Type TYPE = Type.proteinIsoformInteraction;
  public static enum Type implements RelationshipType <
    Protein, Protein.Type,
    ProteinIsoformInteraction, ProteinIsoformInteraction.Type,
    Isoform, Isoform.Type
  > {
    proteinIsoformInteraction;
    public Type value() { return proteinIsoformInteraction; }
    public Arity arity() { return Arity.manyToMany; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Isoform.Type targetType() { return Isoform.TYPE; }
  }  
}