package com.bio4j.model.enzymedb.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.nodes.Protein;
/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Enzyme extends Node<Enzyme, Enzyme.type> {
    
  enum type implements NodeType<Enzyme, Enzyme.type> {

    enzyme;
    public type value() { return enzyme; }
  }
   
  public String id();
  public String officialName();
  public String[] alternateNames();
  public String catalyticActivity();
  public String[] cofactors();
  public String comments();
  public String[] prositeCrossReferences();

  public List<Protein> associatedProteins();    
}
