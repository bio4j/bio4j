package bio4j.model.test.titan

import bio4j.model._

trait AnyTEdge extends AnyEdge { tedge =>

  type Rep = com.thinkaurelius.titan.core.TitanEdge

  type Source <: AnyVertex.ofType[Tpe#SourceType]
  val source: Source

  type Target <: AnyVertex.ofType[Tpe#TargetType]
  val target: Target

  /* Getting a property from any TitanEdge */
  import SmthHasProperty._
  implicit def unsafeGetProperty[P <: AnyProperty: PropertyOf[this.Tpe]#is](p: P) = 
    new GetProperty[P](p) {
      def apply(rep: tedge.TaggedRep): p.Rep = rep.getProperty[p.Rep](p.label)
    }

  import com.tinkerpop.blueprints.Direction

  /* Getting source vertex */
  implicit object sourceGetter extends GetSource[Source](source) {
    def apply(rep: tedge.TaggedRep): source.TaggedRep = 
      source ->> rep.getVertex(Direction.OUT).asInstanceOf[source.Rep]
  }

  /* Getting target vertex */
  implicit object targetGetter extends GetTarget[Target](target) {
    def apply(rep: tedge.TaggedRep): target.TaggedRep = 
      target ->> rep.getVertex(Direction.IN).asInstanceOf[target.Rep]
  }

}

class TEdge[
    ET <: AnyEdgeType, 
    S <: AnyVertex.ofType[ET#SourceType], 
    T <: AnyVertex.ofType[ET#TargetType]
  ](val source: S, val tpe: ET, val target: T) extends AnyTEdge { 
    type Source = S
    type Tpe = ET 
    type Target = T
  }
