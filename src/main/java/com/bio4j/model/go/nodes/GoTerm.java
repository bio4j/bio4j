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

// relationships
import com.bio4j.model.go.relationships.IsA;
import com.bio4j.model.go.relationships.HasPartOf;
import com.bio4j.model.go.relationships.PartOf;
import com.bio4j.model.go.relationships.NegativelyRegulates;
import com.bio4j.model.go.relationships.PositivelyRegulates;
import com.bio4j.model.go.relationships.Regulates;

// goAnnotation
import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
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

  // isA
  // incoming
  public List<IsA> in_isA();
  public List<GoTerm> in_isA_nodes();
  // outgoing
  public List<IsA> out_isA(); 
  public List<GoTerm> out_isA_nodes();

  // regulates
  // incoming
  public List<Regulates> in_regulates();
  public List<GoTerm> in_regulates_nodes();
  // outgoing
  public List<Regulates> out_regulates(); 
  public List<GoTerm> out_regulates_nodes();

  // negativelyRegulates
  // incoming
  public List<NegativelyRegulates> in_negativelyRegulates();
  public List<GoTerm> in_negativelyRegulates_nodes();
  // outgoing
  public List<NegativelyRegulates> out_negativelyRegulates(); 
  public List<GoTerm> out_negativelyRegulates_nodes();

  // positivelyRegulates
  // incoming
  public List<PositivelyRegulates> in_positivelyRegulates();
  public List<GoTerm> in_positivelyRegulates_nodes();
  // outgoing
  public List<PositivelyRegulates> out_positivelyRegulates(); 
  public List<GoTerm> out_positivelyRegulates_nodes();  
  
  // partOf
  // incoming
  public List<PartOf> in_partOf();
  public List<GoTerm> in_partOf_nodes();
  // outgoing
  public List<PartOf> out_partOf();
  public List<GoTerm> out_partOf_nodes();

  // hasPartOf
  // incoming
  public List<HasPartOf> in_hasPartOf();
  public List<GoTerm> in_hasPartOf_nodes();
  // outgoing
  public List<HasPartOf> out_hasPartOf();
  public List<GoTerm> out_hasPartOf_nodes();

  public static type TYPE = type.goTerm;
  
  public static enum type implements NodeType<GoTerm, GoTerm.type> {
    goTerm;
    public type value() { return goTerm; }
  }

  ///////////////////////// extras ////////////////////////////////////

  // goAnnotation
  // incoming
  public List<GoAnnotation> in_goAnnotation();
  public List<Protein> in_goAnnotation_nodes();



  // relationships
  // public List<Protein> associatedProteins();
  // public List<GoTerm> isAGoNodes();
  // public List<GoTerm> negativelyRegulatesNodes();
  // public List<GoTerm> positivelyRegulatesNodes();
  // public List<GoTerm> partOfNodes();
}
