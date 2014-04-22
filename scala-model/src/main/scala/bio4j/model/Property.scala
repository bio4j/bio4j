package bio4j.model

/*
  Properties
*/


// LiteralType has a label!
// TODO move to AnyDenotation
trait AnyProperty extends LiteralType {
  // NOTE: should this go somewhere else?
  type Rep
}
class Property[V]() extends AnyProperty with shapeless.FieldOf[V] { type Rep = V }

object AnyProperty {

  import SmthHasProperty._

  type VertexTag = AnyDenotationTag { type Denotation <: AnyVertex }

  /* Right associative property getter for vertices */
  implicit class PropertyOps[P <: AnyProperty](val p: P) {

    def %:[VT <: VertexTag](vr: VT)
      (implicit
        ev: PropertyOf[vr.DenotedType]#is[P],
        mkReader: VT => ReadFrom[VT]
      ): p.Rep = mkReader(vr).apply(p)

  }

  /* For using `%:` you have to provide an implicit val of `ReadFrom` */
  abstract class ReadFrom[VT <: VertexTag](val vt: VT) {
    // NOTE: can't add `PropertyOf[vt.DenotedType]#is` requirement here
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
