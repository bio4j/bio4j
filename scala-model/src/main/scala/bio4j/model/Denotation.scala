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

  def tagWith[D <: AnyDenotation with Singleton] = new TagBuilder[D]

  class TagBuilder[D <: AnyDenotation] {
    def apply(dr : D#Rep): TaggedWith[D] = dr.asInstanceOf[TaggedWith[D]]
  }

}
