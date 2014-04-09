package com.bio4j.model.enzymedb.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.id;
import com.bio4j.model.properties.officialName;
import com.bio4j.model.properties.alternateNames;
import com.bio4j.model.properties.catalyticActivity;
import com.bio4j.model.properties.cofactors;
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
public interface Enzyme extends Node<Enzyme, Enzyme.type>,

  // properties
  // id,
  // officialName,
  // alternateNames,
  // catalyticActivity,
  // cofactors,
  // comment, // WARNING: changed this from comments to comment
  // prositeCrossReferences
  id<Enzyme, Enzyme.type>,
  cofactors<Enzyme, Enzyme.type>
{
  
  // enzymaticActivity
  // incoming
  public List<EnzymaticActivity> in_enzymaticActivity();
  public List<Protein> in_enzymaticActivity_nodes();
  
  // WARNING: moved to rel method
  // public List<Protein> associatedProteins();  

  public static type TYPE = type.enzyme;
  public static enum type implements NodeType<Enzyme, Enzyme.type> {

    enzyme;
    public type value() { return enzyme; }
  }  
}
