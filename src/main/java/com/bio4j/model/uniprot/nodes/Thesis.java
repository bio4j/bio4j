package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Institute;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

import com.bio4j.model.properties.Title;
import com.bio4j.model.properties.Date;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Thesis extends Node<Thesis, Thesis.Type>,

  // properties
  Title<Thesis, Thesis.Type>,
  Date<Thesis, Thesis.Type>
{
  
  public static Type TYPE = Type.thesis;
  
  public static enum Type implements NodeType<Thesis, Thesis.Type> {

    thesis;
    public Type value() { return thesis; }
  }

  // TODO move to rels
  public Institute getInstitute();
  public Person getAuthor();
  public List<Protein> getProteinCitations();
}
