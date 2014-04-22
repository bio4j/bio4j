package bio4j.model.test.titan

import bio4j.model._

trait AnyTVertex extends AnyVertex { tvertex =>

  type Rep = com.thinkaurelius.titan.core.TitanVertex

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

  // TODO: provide ReadFrom for %:

  /* Retrieving edges */
  import com.tinkerpop.blueprints.Direction
  import scala.collection.JavaConversions._

  // TODO: when we get all edges with the given label, they can come from vertices with the wrong type

  /* OUT */
  implicit def unsafeRetrieveOneOutEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[tvertex.Tpe] with SmthToOne }
  ](e: E): RetrieveOutEdge[E] = new RetrieveOutEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.Out[e.TaggedRep] = {
        val it = rep.getEdges(Direction.OUT, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.headOption: Option[e.TaggedRep]
      }
    }

  implicit def unsafeRetrieveManyOutEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[tvertex.Tpe] with SmthToMany }
  ](e: E): RetrieveOutEdge[E] = new RetrieveOutEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.Out[e.TaggedRep] = {
        val it = rep.getEdges(Direction.OUT, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.toList: List[e.TaggedRep]
      }
    }

  /* IN */
  implicit def unsafeRetrieveOneInEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeTo[tvertex.Tpe] with OneToSmth }
  ](e: E): RetrieveInEdge[E] = new RetrieveInEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.In[e.TaggedRep] = {
        val it = rep.getEdges(Direction.IN, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.headOption: Option[e.TaggedRep]
      }
    }

  implicit def unsafeRetrieveManyInEdge[
    E <: Singleton with AnyEdge { type Tpe <: EdgeTo[tvertex.Tpe] with ManyToSmth }
  ](e: E): RetrieveInEdge[E] = new RetrieveInEdge[E](e) {

      def apply(rep: tvertex.TaggedRep): e.tpe.In[e.TaggedRep] = {
        val it = rep.getEdges(Direction.IN, e.tpe.label).asInstanceOf[java.lang.Iterable[e.TaggedRep]]
        it.toList: List[e.TaggedRep]
      }
    }

}

class TVertex[VT <: AnyVertexType](val tpe: VT) 
  extends AnyTVertex { type Tpe = VT }
