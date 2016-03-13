package com.bio4j.model.go.vertices;

import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.edges.*;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_go.edges.GoAnnotation;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public final class GoTerm<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
  extends GoGraph.GoVertex<
  GoTerm<I, RV, RVT, RE, RET>,
  GoGraph<I, RV, RVT, RE, RET>.GoTermType,
  I, RV, RVT, RE, RET
  >
{

  public GoTerm(RV vertex, GoGraph<I, RV, RVT, RE, RET>.GoTermType type) {
    super(vertex, type);
  }

  @Override
  public final GoTerm<I, RV, RVT, RE, RET> self() {
    return this;
  }

  public final String id() {
    return get(type().id);
  }

  public final String name() {
    return get(type().name);
  }

  public final String definition() {
    return get(type().definition);
  }

  public final String comment() {
    return get(type().comment);
  }

  public final String obsolete() {
    return get(type().obsolete);
  }

  public final String synonym() {
    return get(type().synonym);
  }

  public Optional<Stream<PartOf<I, RV, RVT, RE, RET>>> partOf_in() {
    return inManyOptional(graph().PartOf());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> partOf_inV() {
    return inManyOptionalV(graph().PartOf());
  }

  public Optional<Stream<PartOf<I, RV, RVT, RE, RET>>> partOf_out() {
    return outManyOptional(graph().PartOf());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> partOf_outV() {
    return outManyOptionalV(graph().PartOf());
  }

  public Optional<Stream<HasPartOf<I, RV, RVT, RE, RET>>> hasPartOf_in(){
    return inManyOptional(graph().HasPartOf());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> hasPartOf_inV(){
    return inManyOptionalV(graph().HasPartOf());
  }
  public Optional<Stream<HasPartOf<I, RV, RVT, RE, RET>>> hasPartOf_out(){
    return outManyOptional(graph().HasPartOf());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> hasPartOf_outV(){
    return outManyOptionalV(graph().HasPartOf());
  }

  public Optional<Stream<Regulates<I, RV, RVT, RE, RET>>> regulates_in(){
    return inManyOptional(graph().Regulates());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> regulates_inV(){
    return inManyOptionalV(graph().Regulates());
  }
  public Optional<Stream<Regulates<I, RV, RVT, RE, RET>>> regulates_out(){
    return outManyOptional(graph().Regulates());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> regulates_outV(){
    return outManyOptionalV(graph().Regulates());
  }

  public Optional<Stream<PositivelyRegulates<I, RV, RVT, RE, RET>>> positivelyRegulates_in(){
    return inManyOptional(graph().PositivelyRegulates());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> positivelyRegulates_inV(){
    return inManyOptionalV(graph().PositivelyRegulates());
  }
  public Optional<Stream<PositivelyRegulates<I, RV, RVT, RE, RET>>> positivelyRegulates_out(){
    return outManyOptional(graph().PositivelyRegulates());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> positivelyRegulates_outV(){
    return outManyOptionalV(graph().PositivelyRegulates());
  }

  //----negatively regulates-------
  public Optional<Stream<NegativelyRegulates<I, RV, RVT, RE, RET>>> negativelyRegulates_in(){
    return inManyOptional(graph().NegativelyRegulates());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> negativelyRegulates_inV(){
    return inManyOptionalV(graph().NegativelyRegulates());
  }
  public Optional<Stream<NegativelyRegulates<I, RV, RVT, RE, RET>>> negaitivelyRegulates_out(){
    return outManyOptional(graph().NegativelyRegulates());
  }

  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> negativelyRegulates_outV(){
    return outManyOptionalV(graph().NegativelyRegulates());
  }

  //----is a-------
  public Optional<Stream<IsA<I, RV, RVT, RE, RET>>> isA_in(){
    return inManyOptional(graph().IsA());
  }
  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> isA_inV(){
    return inManyOptionalV(graph().IsA());
  }

  public Optional<Stream<IsA<I, RV, RVT, RE, RET>>> isA_out(){
    return outManyOptional(graph().IsA());
  }
  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>> isA_outV(){
    return outManyOptionalV(graph().IsA());
  }

  //-----subontology-----
  public SubOntology<I, RV, RVT, RE, RET> subontoloty_out(){
    return outOne(graph().SubOntology());
  }
  //-----subontology-----
  public SubOntologies<I, RV, RVT, RE, RET> subontoloty_outV(){
    return outOneV(graph().SubOntology());
  }

  public Optional<Stream<GoAnnotation<I, RV, RVT, RE, RET>>> goAnnotation_in() {
    return inManyOptional( graph().uniProtGoGraph().GoAnnotation() );
  }

  public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> goAnnotation_inV(){
    return inManyOptionalV(graph().uniProtGoGraph().GoAnnotation());
  }
}
