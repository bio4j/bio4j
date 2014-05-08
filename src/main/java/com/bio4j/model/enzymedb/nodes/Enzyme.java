package com.bio4j.model.enzymedb.nodes;

import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

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
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
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
  public List<Protein> enzymaticActivity_inNodes();
  
  // WARNING: moved to rel method
  // public List<Protein> associatedProteins();

  public default Type type() { return Enzyme.TYPE; }

  public static Type TYPE = Type.enzyme;
  public static enum Type implements NodeType<Enzyme, Enzyme.Type> {

    enzyme;
    public Type value() { return enzyme; }
  }  
}
