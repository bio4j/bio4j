package com.bio4j.model.enzymedb.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.id;
import com.bio4j.model.properties.OfficialName;
import com.bio4j.model.properties.AlternateNames;
import com.bio4j.model.properties.CatalyticActivity;
import com.bio4j.model.properties.Cofactors;
import com.bio4j.model.properties.comment;
import com.bio4j.model.properties.prositeCrossReferences;

// relationships
import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;

// other nodes
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
 */
public interface Enzyme extends Node<Enzyme, Enzyme.Type>,

  // properties
  id,
  OfficialName,
  AlternateNames,
  CatalyticActivity,
  Cofactors,
  comment, // WARNING: changed this from comments to comment
  prositeCrossReferences

{
  
  // enzymaticActivity
  // incoming
  public List<EnzymaticActivity> enzymaticActivity_in();
  public List<Protein> enzymaticActivityin_Nodes();
  
  // WARNING: moved to rel method
  // public List<Protein> associatedProteins();  

  public static Type TYPE = Type.enzyme;
  public static enum Type implements NodeType<Enzyme, Enzyme.Type> {

    enzyme;
    public Type value() { return enzyme; }
  }  
}
