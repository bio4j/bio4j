package com.bio4j.model.go.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.name;
import com.bio4j.model.properties.id;
import com.bio4j.model.properties.comment;
import com.bio4j.model.properties.obsolete;
import com.bio4j.model.properties.definition;
import com.bio4j.model.properties.alternativeIds;

// other nodes
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GoTerm extends Node<GoTerm, GoTerm.type>,
  // properties
  name,
  id,
  definition,
  comment,
  obsolete,
  alternativeIds
{

  public static type TYPE = type.goTerm;
  public static enum type implements NodeType<GoTerm, GoTerm.type> {
    goTerm;
    public type value() { return goTerm; }
  }

  // relationships
  public List<Protein> associatedProteins();
  // should be
  public List<Protein> in_proteinGo();

  public List<GoTerm> isAGoNodes();
  // should be two of them
  public List<GoTerm> in_isAGo();
  public List<GoTerm> out_isAGo();

  public List<GoTerm> negativelyRegulatesNodes();
  public List<GoTerm> positivelyRegulatesNodes();
  public List<GoTerm> partOfNodes();
  public List<GoTerm> hasPartOfNodes();
}
