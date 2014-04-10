package com.bio4j.model.go.nodes;

import java.util.List;

import com.bio4j.model.Node;
import com.bio4j.model.NodeType;

// properties
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.Obsolete;
import com.bio4j.model.properties.Definition;
import com.bio4j.model.properties.AlternativeIds;

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
public interface GoTerm extends Node<GoTerm, GoTerm.Type>,

  // properties
  Id <GoTerm, GoTerm.Type>,
  Name <GoTerm, GoTerm.Type>,
  Definition <GoTerm, GoTerm.Type>,
  Comment <GoTerm, GoTerm.Type>,
  Obsolete <GoTerm, GoTerm.Type>,
  AlternativeIds <GoTerm, GoTerm.Type>

{

  // isA
  // incoming
  public List<IsA> isA_in();
  public List<GoTerm> isA_inNodes();
  // outgoing
  public List<IsA> isA_out(); 
  public List<GoTerm> isA_outNodes();

  // regulates
  // incoming
  public List<Regulates> regulates_in();
  public List<GoTerm> regulates_inNodes();
  // outgoing
  public List<Regulates> regulates_out(); 
  public List<GoTerm> regulates_outNodes();

  // negativelyRegulates
  // incoming
  public List<NegativelyRegulates> negativelyRegulates_in();
  public List<GoTerm> negativelyRegulates_inNodes();
  // outgoing
  public List<NegativelyRegulates> negativelyRegulates_out(); 
  public List<GoTerm> negativelyRegulates_outNodes();
    

  // positivelyRegulates
  // incoming
  public List<PositivelyRegulates> positivelyRegulates_in();
  public List<GoTerm> positivelyRegulates_inNodes();
  // outgoing
  public List<PositivelyRegulates> positivelyRegulates_out(); 
  public List<GoTerm> positivelyRegulates_outNodes();  
  
  // partOf
  // incoming
  public List<PartOf> partOf_in();
  public List<GoTerm> partOf_inNodes();
  // outgoing
  public List<PartOf> partOf_out();
  public List<GoTerm> partOf_outNodes();

  // hasPartOf
  // incoming
  public List<HasPartOf> hasPartOf_in();
  public List<GoTerm> hasPartOf_inNodes();
  // outgoing
  public List<HasPartOf> hasPartOf_out();
  public List<GoTerm> hasPartOf_outNodes();

  public default Type getType() { return TYPE; }
  public static Type TYPE = Type.goTerm;
  public static enum Type implements NodeType<GoTerm, GoTerm.Type> {
    goTerm;
    public Type value() { return goTerm; }
  }

  ///////////////////////// extras ////////////////////////////////////

  // goAnnotation
  // incoming
  public List<GoAnnotation> goAnnotation_in();
  public List<Protein> goAnnotation_inNodes();



  // relationships
  // public List<Protein> associatedProteins();
  // public List<GoTerm> isAGoNodes();
  // public List<GoTerm> negativelyRegulatesNodes();
  // public List<GoTerm> positivelyRegulatesNodes();
  // public List<GoTerm> partOfNodes();
}
