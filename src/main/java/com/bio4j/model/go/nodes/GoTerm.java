package com.bio4j.model.go.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.name;

// other nodes
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GoTerm extends Node<GoTerm, GoTerm.type>,
name 

{
    
  public static enum type implements NodeType<GoTerm, GoTerm.type> {
    goTerm;
    public type value() { return goTerm; }
  }
 
  public String id();
  public String definition();
  public String namespace();
  public Boolean obsolete();
  public String comment();
  public String[] alternativeIds();
  
  // relationships
  public List<Protein> associatedProteins();
  public List<GoTerm> isAGoNodes();
  public List<GoTerm> negativelyRegulatesNodes();
  public List<GoTerm> positivelyRegulatesNodes();
  public List<GoTerm> partOfNodes();
  public List<GoTerm> hasPartOfNodes();
}
