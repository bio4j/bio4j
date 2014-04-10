package com.bio4j.model.enzymedb.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Cofactors;
import com.bio4j.model.properties.OfficialName;
import com.bio4j.model.properties.AlternateNames;
import com.bio4j.model.properties.CatalyticActivity;
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.PrositeCrossReferences;

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
  Id<Enzyme, Enzyme.Type>,
  Cofactors<Enzyme, Enzyme.Type>,
  OfficialName<Enzyme, Enzyme.Type>,
  AlternateNames<Enzyme, Enzyme.Type>,
  CatalyticActivity<Enzyme, Enzyme.Type>,
  Comment<Enzyme, Enzyme.Type>, // WARNING: changed this from comments to comment
  PrositeCrossReferences<Enzyme, Enzyme.Type>

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
