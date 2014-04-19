package bio4j.model

import shapeless.record._

trait Tagged { self =>
  type Rep
  
  type TaggedRep = FieldType[self.type, Rep]
  def ->>(r: Rep): FieldType[self.type, Rep] = field[self.type](r)
}
