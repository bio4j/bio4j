package com.bio4j.model.uniprot.nodes.references;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

/*
* This node represents an editor of a reference. Properties are accessible through the corresponding outgoing relationships.
* 
* @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
* @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Editor extends Node<Editor, Editor.Type> {
  
  public static Type TYPE = Type.editor;
  
  public static enum Type implements NodeType<Editor, Editor.Type> {
    
    editor;
    
    public Type value() { return editor; }
  }
}
