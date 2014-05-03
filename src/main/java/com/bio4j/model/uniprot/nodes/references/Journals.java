package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.relationships.references.Journal;

/**
 *  This Node has just one instance. A `Publication` node is a journal if it has a relationship of type `Journal` pointing to the unique `Journals` node.
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface Journals extends Node<Journals, Journals.Type> {
  
  public List<? extends Journal> journal_in();
  public List<? extends Publication> journal_inNodes();

  public static Type TYPE = Type.journals;

  public static enum Type implements NodeType<Journals, Journals.Type> {
    
    journals;
    
    public Type value() { return journals; }
  }
}