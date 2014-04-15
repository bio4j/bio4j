package com.bio4j.model.proteinInteractions.relationships;

import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.Relationship;
import com.bio4j.model.RelationshipType;

import com.bio4j.model.properties.Experiments;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface ProteinProteinInteraction extends Relationship <

  Protein, Protein.Type,
  ProteinProteinInteraction, ProteinProteinInteraction.Type,
  Protein, Protein.Type

>,

  // properties
  Experiments<ProteinProteinInteraction, ProteinProteinInteraction.Type>
{

  public Protein source();
  public Protein target();

  // TODO migrate to properties
  public String getOrganismsDiffer(); // TODO ????
  public String getIntactId2(); // TODO what is this
  public String getIntactId1(); // TODO what is this

  public static Type TYPE = Type.proteinProteinInteraction;
  public static enum Type implements RelationshipType <
    Protein, Protein.Type,
    ProteinProteinInteraction, ProteinProteinInteraction.Type,
    Protein, Protein.Type
  > {

    proteinProteinInteraction;

    public Arity arity() { return Arity.manyToMany; }

    public Type value() { return proteinProteinInteraction; }
    public Protein.Type sourceType() { return Protein.TYPE; }
    public Protein.Type targetType() { return Protein.TYPE; }
  }  
}