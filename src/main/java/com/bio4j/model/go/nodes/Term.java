package com.bio4j.model.go.nodes;

import java.util.List;

// base types
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

// properties
import com.bio4j.model.properties.Name;
import com.bio4j.model.properties.Synonym;
import com.bio4j.model.properties.Id;
import com.bio4j.model.properties.Comment;
import com.bio4j.model.properties.Definition;

// relationships
import com.bio4j.model.go.relationships.IsA;
import com.bio4j.model.go.relationships.HasPartOf;
import com.bio4j.model.go.relationships.PartOf;
import com.bio4j.model.go.relationships.NegativelyRegulates;
import com.bio4j.model.go.relationships.PositivelyRegulates;
import com.bio4j.model.go.relationships.Regulates;
import com.bio4j.model.go.relationships.MolecularFunction;
import com.bio4j.model.go.relationships.BiologicalProcess;
import com.bio4j.model.go.relationships.CellularComponent;

// goAnnotation
import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
import com.bio4j.model.uniprot.nodes.Protein;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Term extends Node<Term, Term.Type>,

  // properties
  Id<Term,Term.Type>,
  Name<Term,Term.Type>,
  Synonym<Term,Term.Type>,
  Definition<Term,Term.Type>,
  Comment<Term,Term.Type>
  
{
  
  // SubOntologies
    
  // MolecularFunction
  // outgoing
  public MolecularFunction molecularFunction_out(); 
  public SubOntologies molecularFunction_outNodes();

  // BiologicalProcess
  // outgoing
  public BiologicalProcess biologicalProcess_out(); 
  public SubOntologies biologicalProcess_outNodes();

  // CellularComponent
  // outgoing
  public CellularComponent cellularComponent_out(); 
  public SubOntologies cellularComponent_outNodes();

  // isA
  // incoming
  public List<? extends IsA> isA_in();
  public List<? extends Term> isA_inNodes();
  // outgoing
  public List<? extends IsA> isA_out(); 
  public List<? extends Term> isA_outNodes();

  // regulates
  // incoming
  public List<? extends Regulates> regulates_in();
  public List<? extends Term> regulates_inNodes();
  // outgoing
  public List<? extends Regulates> regulates_out(); 
  public List<? extends Term> regulates_outNodes();

  // negativelyRegulates
  // incoming
  public List<? extends NegativelyRegulates> negativelyRegulates_in();
  public List<? extends Term> negativelyRegulates_inNodes();
  // outgoing
  public List<? extends NegativelyRegulates> negativelyRegulates_out(); 
  public List<? extends Term> negativelyRegulates_outNodes();
    

  // positivelyRegulates
  // incoming
  public List<? extends PositivelyRegulates> positivelyRegulates_in();
  public List<? extends Term> positivelyRegulates_inNodes();
  // outgoing
  public List<? extends PositivelyRegulates> positivelyRegulates_out(); 
  public List<? extends Term> positivelyRegulates_outNodes();  
  
  // partOf
  // incoming
  public List<? extends PartOf> partOf_in();
  public List<? extends Term> partOf_inNodes();
  // outgoing
  public List<? extends PartOf> partOf_out();
  public List<? extends Term> partOf_outNodes();

  // hasPartOf
  // incoming
  public List<? extends HasPartOf> hasPartOf_in();
  public List<? extends Term> hasPartOf_inNodes();
  // outgoing
  public List<? extends HasPartOf> hasPartOf_out();
  public List<? extends Term> hasPartOf_outNodes();

  @Override public default Type type() { return TYPE; }

  public static Type TYPE = Type.term;

  public static enum Type implements NodeType<Term, Term.Type> {

    term;

    public Type value() { return term; }
  }

  ///////////////////////// extras ////////////////////////////////////

  // goAnnotation
  // incoming
  public List<? extends GoAnnotation> goAnnotation_in();
  public List<? extends Protein> goAnnotation_inNodes();
}
