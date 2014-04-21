package bio4j.model.test.titan

import bio4j.model._
import com.thinkaurelius.titan.core.TitanVertex

trait AnyTVertex extends AnyVertex {

  type Rep = TitanVertex

  /* Reading any property from a TitanVertex */
  import AnyProperty._
  implicit def readFromTitanVertex(vr: TaggedRep) = 
    new ReadFrom[TaggedRep](vr) {
      def apply[P <: AnyProperty](p: P): p.Rep = vr.getProperty[p.Rep](p.label)
    }

  /* Getting a property from any TitanVertex */
  import SmthHasProperty._
  implicit def unsafeGetProperty[P <: AnyProperty: PropertyOf[this.Tpe]#is](p: P) = 
    new GetProperty[P](p) {
      def apply(rep: TaggedRep): p.Rep = rep.getProperty[p.Rep](p.label)
    }

  /* Retrieving edges */
  import com.tinkerpop.blueprints.Direction
  // import scala.collection.JavaConversions._
  // implicit def iterableToOption[T](i: Iterable[T]): Option[T] = i.headOption
  // implicit def   iterableToList[T](i: Iterable[T]): List[T] = i.toList
  
  import AnyEdge._
  implicit def unsafeRetrieveOutEdge[E <: AnyEdge](e: E)(implicit conv: Iterable[e.TaggedRep] => e.Out[e.Rep]):
  RetrieveOutEdge[e.type] = new RetrieveOutEdge[e.type](e) {

    def apply(rep: TaggedRep): e.Out[e.Rep] = {

      val uh = rep.getEdges(Direction.OUT, e.tpe.label).asInstanceOf[Iterable[e.TaggedRep]]
      val hey: e.Out[e.Rep] = conv(uh)
      hey
    }
  }
}

class TVertex[VT <: AnyVertexType](val tpe: VT) extends AnyTVertex { type Tpe = VT }

