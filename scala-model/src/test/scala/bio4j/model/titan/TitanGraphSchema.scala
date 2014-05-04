package bio4j.model.test.titan

import bio4j.model._
import com.thinkaurelius.titan.core._

object MakeKeys {

  abstract class ArityMaker[ET <: AnyEdgeType](et: ET) {
    def apply: (LabelMaker => LabelMaker)
  }

  object ArityMaker {
    implicit def manyToManyMaker[ET <: ManyIn with ManyOut](et: ET): ArityMaker[ET] = 
      new ArityMaker[ET](et) { def apply = _.manyToMany }
    implicit def  manyToOneMaker[ET <: ManyIn with OneOut](et: ET): ArityMaker[ET] = 
      new ArityMaker[ET](et) { def apply = _.manyToOne }
    implicit def  oneToManyMaker[ET <: OneIn with ManyOut](et: ET): ArityMaker[ET] = 
      new ArityMaker[ET](et) { def apply = _.oneToMany }
    implicit def   oneToOneMaker[ET <: OneIn with OneOut](et: ET): ArityMaker[ET] = 
      new ArityMaker[ET](et) { def apply = _.oneToOne }
  }

  implicit def graphSchemaOps(g: TitanGraph): GraphSchemaOps = GraphSchemaOps(g)
  case class   GraphSchemaOps(g: TitanGraph) {

    import scala.reflect._
    import scala.reflect.runtime.universe._

    // TODO: add uniqueness and indexing parameters
    def addPropertyKey[P <: AnyProperty](p: P)(implicit c: ClassTag[p.Rep]): TitanKey = {
      // FIXME: this compiles, but is not what we want (see the test)
      val clazz = c.runtimeClass.asInstanceOf[Class[p.Rep]]
      g.makeKey(p.label).dataType(clazz).single.make
    }

    // TODO: add sortKey/signature parameters
    def addEdgeLabel[E <: AnyEdge](e: E)(implicit arityMaker: e.Tpe => ArityMaker[e.Tpe]): TitanLabel = {
        val arity = arityMaker(e.tpe)
        arity.apply(g.makeLabel(e.tpe.label).directed).make
    }

  }
}
