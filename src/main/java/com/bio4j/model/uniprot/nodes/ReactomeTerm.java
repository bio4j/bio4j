package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ReactomeTerm extends Node<ReactomeTerm, ReactomeTerm.type> {
    
  enum type implements NodeType<ReactomeTerm, ReactomeTerm.type> {

    reactomeTerm;
    public type value() { return reactomeTerm; }
  }
  
  //------GETTERS-----
  public String getId();
  public String getPathwayName();
  public List<Protein> getAssociatedProteins();

  //------SETTERS------
  public void setId(String value);
  public void setPathwayName(String value);
   
}