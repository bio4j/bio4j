package bio4j.model.test.titan

import bio4j.model._
import com.thinkaurelius.titan.core.TitanVertex

trait AnyTVertex extends AnyVertex { tvertex =>

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
  // implicit def iterableToOption[T](i: Iterable[T]): Option[T] = i.headOption
  // implicit def   iterableToList[T](i: Iterable[T]): List[T] = i.toList

  implicit def unsafeRetrieveOneOutEdge[E <: AnyEdge { type Tpe <: SmthToOne with EdgeFrom[tvertex.Tpe] }](e: E) =
      new RetrieveOneOutEdge(e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.Out[e.TaggedRep] = {
        import scala.collection.JavaConversions._
        val uh = iterableAsScalaIterable(rep.getEdges(Direction.OUT, e.tpe.label)).asInstanceOf[Iterable[e.TaggedRep]]
        uh.headOption
      }
    }
}

class TVertex[VT <: AnyVertexType](val tpe: VT) extends AnyTVertex { tvertex =>

  type Tpe = VT 

   import com.tinkerpop.blueprints.Direction
  import AnyEdge._
  implicit def unsafeRetrieveManyOutEdge[
    E <: Singleton with ofTypeSmthToMany with withSourceType[this.Tpe]
  ](e: E): RetrieveManyOutEdge[E] = new RetrieveManyOutEdge(e) {

      def apply(rep: tvertex.TaggedRep): E#Tpe#Out[E#TaggedRep] = {
        import scala.collection.JavaConversions._
        // FIXME: here the (e ->> _) tag is lost. This `.asInstanceOf` doesn't work.
        val it: java.lang.Iterable[E#TaggedRep] = rep.getEdges(Direction.OUT, e.tpe.label).asInstanceOf[java.lang.Iterable[E#TaggedRep]]
        // it.map(i => e ->> i.asInstanceOf[e.Rep]).toList
        (it.toList: List[E#TaggedRep])
      }
    }
}

object TVertex {

  

}
