package bio4j.model

/*
  Properties
*/
import shapeless.FieldOf
import vertexTags._

/*
  This has a label!
*/
trait AnyProperty extends LiteralType {

  // should this go somewhere else?
  type Rep
}
class Property[V]() extends AnyProperty with FieldOf[V] {

  type Rep = V
}

object AnyProperty {

  import SmthHasProperty._


  implicit class PropertyOps[P <: AnyProperty](val p: P) {

    def %:[VT <: AnyVertexTag](vr: VT)(
      implicit
      ev: PropertyOf[vr.Vertex#Tpe]#is[P],
      mkGetter: VT => ReadFrom[VT]
    ): p.Rep = {

      val g = mkGetter(vr)
      g(p)
    }
  }

  abstract class ReadFrom[VT <: AnyVertexTag](val vt: VT) {

    def apply[P <: AnyProperty](p: P): p.Rep
  }


}



/* Evidence that an arbitrary type `Smth` has property `Property` */
trait SmthHasProperty {
  type Smth
  val smth: Smth

  type Property <: AnyProperty
  val property: Property
}

object SmthHasProperty {
  type PropertyOf[S] = { 
    type is[P <: AnyProperty] = SmthHasProperty { type Smth = S; type Property = P }
  }
}
