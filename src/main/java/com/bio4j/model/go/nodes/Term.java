package com.bio4j.model.go.nodes;

import java.util.List;

// base types
import com.ohnosequences.typedGraphs.Node;
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
public interface Term <
  N extends Term<N,NT>,
  NT extends Term.Type<N,NT>
> 
  extends Node<N,NT>
{

  // the node type
  public static interface Type <
    N extends Term<N,NT>,
    NT extends Term.Type<N,NT>
  > 
    extends Node.Type<N,NT>
  {}
  
  public static abstract class id <
    N extends Term<N,NT>,
    NT extends Term.Type<N,NT>,
    P extends id<N,NT,P>
  > 
    extends Property<N,NT,P,String> 
  {
    Class<String> clazz = String.Class;
  }

  public static abstract class name <
    N extends Term<N,NT>,
    NT extends Term.Type<N,NT>,
    P extends name<N,NT,P>
  > 
    extends Property<N,NT,P,String> 
  {
    Class<String> clazz = String.Class;
  }

  public static abstract class synonym <
    N extends Term<N,NT>,
    NT extends Term.Type<N,NT>,
    P extends synonym<N,NT,P>
  > 
    extends Property<N,NT,P,String> 
  {
    Class<String> clazz = String.Class;
  }

  public static abstract class definition <
    N extends Term<N,NT>,
    NT extends Term.Type<N,NT>,
    P extends definition<N,NT,P>
  > 
    extends Property<N,NT,P,String> 
  {
    Class<String> clazz = String.Class;
  }

  public static abstract class comment <
    N extends Term<N,NT>,
    NT extends Term.Type<N,NT>,
    P extends comment<N,NT,P>
  > 
    extends Property<N,NT,P,String> 
  {
    Class<String> clazz = String.Class;
  }

  // all this is optional now
  @Override public default String id() { return get(Id.TYPE(type())); }
  @Override public default String name() { return get(Name.TYPE(type())); }
  @Override public default String synonym() { return get(Synonym.TYPE(type())); }
  @Override public default String definition() { return get(Definition.TYPE(type())); }
  @Override public default String comment() { return get(Comment.TYPE(type())); }
  
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

  

  ///////////////////////// extras ////////////////////////////////////

  // goAnnotation
  // incoming
  public List<? extends GoAnnotation> goAnnotation_in();
  public List<? extends Protein> goAnnotation_inNodes();
}
