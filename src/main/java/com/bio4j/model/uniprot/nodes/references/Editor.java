package com.bio4j.model.uniprot.nodes.references;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

/*
* This node represents an editor of a reference. Properties are accessible through the corresponding outgoing relationships.
* 
* @author Pablo Pareja Tobes <ppareja@era7.com>
* @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public interface Editor extends Node<Editor, Editor.Type> {
  
  public static Type TYPE = Type.editor;
  
  public static enum Type implements NodeType<Editor, Editor.Type> {
    
    editor;
    
    public Type value() { return editor; }
  }
}