package com.bio4j.model.uniprot.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Organism extends Node<Organism, Organism.Type> {
    
  public static Type TYPE = Type.organism;
  public static enum Type implements NodeType<Organism, Organism.Type> {

    organism;
    public Type value() { return organism; }
  }
    
    //----GETTERS---
    public String getScientificName();
    public String getCommonName();
    public String getSynonymName();
    public String getNcbiTaxonomyId();
    
    public List<Protein> getAssociatedProteins();

    //----SETTERS----
    public void setScientificName(String value);
    public void setCommonName(String value);
    public void setSynonymName(String value);
    public void setNcbiTaxonomyId(String value);   
    
}
