package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface ProteinProteinInteraction extends Relationship <

  Protein, Protein.type,
  ProteinProteinInteraction, ProteinProteinInteraction.type,
  Protein, Protein.type

> 
{

  @Override
  public Protein source();
  @Override
  public Protein target();

  // TODO migrate to properties

  public String getOrganismsDiffer();
  public String getExperiments();
  public String getIntactId2();
  public String getIntactId1();

  public void setOrganismsDiffer(String value);
  public void setExperiments(String value);
  public void setIntactId2(String value);
  public void setIntactId1(String value);

  public static type TYPE = type.proteinProteinInteraction;
  public static enum type implements RelationshipType <
    Protein, Protein.type,
    ProteinProteinInteraction, ProteinProteinInteraction.type,
    Protein, Protein.type
  > {
    proteinProteinInteraction;
    public type value() { return proteinProteinInteraction; }
    public arity arity() { return arity.manyToMany; }
    public Protein.type sourceType() { return Protein.TYPE; }
    public Protein.type targetType() { return Protein.TYPE; }
  }  
}