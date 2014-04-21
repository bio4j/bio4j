package bio4j.model.test.titan

import bio4j.model._

trait AnyTEdge extends AnyEdge {

  type Rep = com.thinkaurelius.titan.core.TitanEdge

  /* Getting a property from any TitanEdge */
  import SmthHasProperty._
  implicit def unsafeGetProperty[P <: AnyProperty: PropertyOf[this.Tpe]#is](p: P) = 
    new GetProperty[P](p) {
      def apply(rep: TaggedRep): p.Rep = rep.getProperty[p.Rep](p.label)
    }
}

class TEdge[VT <: AnyEdgeType](val tpe: VT) extends AnyTEdge { edge =>

  type Tpe = VT
  // add a conversion from Iterable[this.Rep]

  // implicit def fromIterToList(iterRep: Iterable[edge.TaggedRep])
  //   (implicit outList: ListOutType[edge.tpe]): edge.Out[edge.Rep] = {

  //   // transform using whatever conversion you might have
  // }

}

