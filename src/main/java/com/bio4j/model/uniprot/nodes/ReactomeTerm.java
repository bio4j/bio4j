package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ReactomeTerm extends Node<ReactomeTerm, ReactomeTerm.Type> {
    
  public static enum Type implements NodeType<ReactomeTerm, ReactomeTerm.Type> {

    reactomeTerm;
    public Type value() { return reactomeTerm; }
  }
  
  //------GETTERS-----
  public String getId();
  public String getPathwayName();
  public List<Protein> getAssociatedProteins();

  //------SETTERS------
  public void setId(String value);
  public void setPathwayName(String value);
   
}