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

  Protein, Protein.type,
  ProteinIsoformInteraction, ProteinIsoformInteraction.type,
  Isoform, Isoform.type

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

  public static type TYPE = type.proteinIsoformInteraction;
  public static enum type implements RelationshipType <
    Protein, Protein.type,
    ProteinIsoformInteraction, ProteinIsoformInteraction.type,
    Isoform, Isoform.type
  > {
    proteinIsoformInteraction;
    public type value() { return proteinIsoformInteraction; }
    public arity arity() { return arity.manyToMany; }
    public Protein.type sourceType() { return Protein.TYPE; }
    public Isoform.type targetType() { return Isoform.TYPE; }
  }  
}