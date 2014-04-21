package bio4j.model

import shapeless.record._

/*
  This trait represents a mapping between 

  - members `Tpe` of a universe of types `TYPE`
  - and `Rep` a type meant to be a denotation of `Tpe` thus the name

  Tagging is used for being able to operate on `Rep` values knowing what they are denotating.
*/
trait AnyDenotation { self =>

  /* The base type for the types that this thing denotes */
  type TYPE
  type Tpe <: TYPE
  val tpe: Tpe

  type Rep

  import Tagged._
  type TaggedRep = TaggedWith[self.type]
  def ->>(r: Rep): TaggedWith[self.type] = tagWith[self.type](r)



  /*
    
    #### properties

    As we are using this for edges and vertices, we have here the corresponding property logic. It would be good to split this into a different trait, maybe with a self bound on `AnyDenotation`; note that properties themselves should be instances of `AnyDenotation` but we don't want them to have properties.

  */

  /* Read a property from this representation */
  import SmthHasProperty._

  trait AnyGetProperty {
    type Property <: AnyProperty
    val p: Property

    def apply(rep: self.TaggedRep): p.Rep
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

/*
  tagging functionality
*/
object Tagged {

  type TaggedWith[D <: AnyDenotation] = D#Rep with DenotationTag[D]

  def tagWith[D <: AnyDenotation] = new TagBuilder[D]

  class TagBuilder[D <: AnyDenotation] {
    def apply(dr : D#Rep): TaggedWith[D] = dr.asInstanceOf[TaggedWith[D]]
  }

}
