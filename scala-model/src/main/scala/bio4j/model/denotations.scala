package bio4j.model

import shapeless.record._

object denotations {

  trait AnyDenote {

    // the base type for the types that this thing denotes
    type TYPE
    type Tpe <: TYPE
    val tpe: Tpe

    type Rep

    type TaggedRep = TaggedWith[this.type]
    def ->>(r: Rep): TaggedWith[this.type] = tagWith[this.type](r)
  }

  trait Denote[T] extends AnyDenote { type TYPE = T }

  trait AnyDenotationTag {

    type Denotation <: AnyDenote
    type DenotedType = Denotation#Tpe
  }
  trait DenotationTag[D <: AnyDenote] extends AnyDenotationTag with KeyTag[D,D#Rep] { type Denotation = D }

  type TaggedWith[D <: AnyDenote] = D#Rep with DenotationTag[D]

  def tagWith[D <: AnyDenote] = new TagBuilder[D]
  class TagBuilder[D <: AnyDenote] {
    def apply(dr : D#Rep): TaggedWith[D] = dr.asInstanceOf[TaggedWith[D]]
  }
}