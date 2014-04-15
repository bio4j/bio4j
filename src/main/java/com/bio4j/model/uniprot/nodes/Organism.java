package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.ScientificName;
import com.bio4j.model.properties.CommonName;
import com.bio4j.model.properties.SynonymName;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Organism extends Node<Organism, Organism.Type>,

  // properties
  ScientificName<Organism, Organism.Type>,
  CommonName<Organism, Organism.Type>,
  SynonymName<Organism, Organism.Type>
{
    
  public static Type TYPE = Type.organism;
  public static enum Type implements NodeType<Organism, Organism.Type> {

    organism;
    public Type value() { return organism; }
  }
    
  // TODO rel?
  public String getNcbiTaxonomyId();
  // TODO move to rel 
  public List<Protein> getAssociatedProteins();
  public void setNcbiTaxonomyId(String value);   
  
}
