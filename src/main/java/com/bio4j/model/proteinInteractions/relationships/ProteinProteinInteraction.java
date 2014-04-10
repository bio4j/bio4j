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

  Protein, Protein.Type,
  ProteinProteinInteraction, ProteinProteinInteraction.Type,
  Protein, Protein.Type

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

  public static Type TYPE = Type.proteinProteinInteraction;
  public static enum Type implements RelationshipType <
    Protein, Protein.Type,
    ProteinProteinInteraction, ProteinProteinInteraction.Type,
    Protein, Protein.Type
  > {
    proteinProteinInteraction;
    public Type value() { return proteinProteinInteraction; }
    public Arity arity() { return Arity.manyToMany; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Protein.Type targetType() { return Protein.TYPE; }
  }  
}