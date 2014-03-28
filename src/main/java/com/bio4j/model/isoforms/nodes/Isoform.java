package com.bio4j.model.isoforms.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.name;
import com.bio4j.model.properties.id;
import com.bio4j.model.properties.sequence;
import com.bio4j.model.properties.note;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Isoform extends Node<Isoform, Isoform.type>,  
  id,
  name,
  sequence,
  note
{   
  public static enum type implements NodeType<Isoform, Isoform.type> {
    isoform;
    public type value() { return isoform; }
  }
}
