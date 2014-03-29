package com.bio4j.model.enzymedb.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.id;
// import com.bio4j.model.properties.comments;

// other nodes
import com.bio4j.model.uniprot.nodes.Protein;
/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Enzyme extends Node<Enzyme, Enzyme.type>,
  // properties
  id
  // TODO: add the others
{
  
  public static type TYPE = type.enzyme;
  enum type implements NodeType<Enzyme, Enzyme.type> {

    enzyme;
    public type value() { return enzyme; }
  }
   
  public String officialName();
  public String[] alternateNames();
  public String catalyticActivity();
  public String[] cofactors();
  public String comments();
  public String[] prositeCrossReferences();

  public List<Protein> associatedProteins();    
}
