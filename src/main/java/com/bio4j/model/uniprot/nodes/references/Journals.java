package com.bio4j.model.uniprot.nodes.references;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

import com.bio4j.model.uniprot.relationships.references.Journal;

/**
 *  This Node has just one instance. A `Publication` node is a journal if it has a relationship of type `Journal` pointing to the unique `Journals` node.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
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
