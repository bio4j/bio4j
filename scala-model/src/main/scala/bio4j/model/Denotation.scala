package bio4j.model

import shapeless.record._

trait AnyDenotation { self =>

  /* The base type for the types that this thing denotes */
  type TYPE
  type Tpe <: TYPE
  val tpe: Tpe

  type Rep

  import Tagged._
  type TaggedRep = TaggedWith[self.type]
  def ->>(r: Rep): TaggedWith[self.type] = tagWith[self.type](r)


  /* Read a property from this representation */
  import SmthHasProperty._

  trait AnyGetProperty {
    type Property <: AnyProperty
    val p: Property

    // NOTE: Why `self.TaggedRep`, but not `p.Rep`? (it compiles fine and passes tests, btw)
    def apply(rep: self.TaggedRep): Property#Rep
  }

  abstract class GetProperty[P <: AnyProperty](val p: P) 
      extends AnyGetProperty { type Property = P }

  implicit def propertyOps(rep: self.TaggedRep): PropertyOps = PropertyOps(rep)
  case class   PropertyOps(rep: self.TaggedRep) {

    def get[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit mkGetter: P => GetProperty[P]): P#Rep = mkGetter(p).apply(rep)

  }

  /* If have just an independent getter for a particular property: */
  implicit def idGetter[P <: AnyProperty: PropertyOf[self.Tpe]#is](p: P)
      (implicit getter: GetProperty[P]) = getter

}

trait Denotation[T] extends AnyDenotation { type TYPE = T }


trait AnyDenotationTag {
  type Denotation <: AnyDenotation
  type DenotedType = Denotation#Tpe
}

trait DenotationTag[D <: AnyDenotation] extends AnyDenotationTag with KeyTag[D, D#Rep] {
  type Denotation = D
}


object Tagged {

  type TaggedWith[D <: AnyDenotation] = D#Rep with DenotationTag[D]

  def tagWith[D <: AnyDenotation] = new TagBuilder[D]

  class TagBuilder[D <: AnyDenotation] {
    def apply(dr : D#Rep): TaggedWith[D] = dr.asInstanceOf[TaggedWith[D]]
  }

}
