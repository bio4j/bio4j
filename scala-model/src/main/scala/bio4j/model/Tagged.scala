package bio4j.model

import shapeless.record._

trait Tagged { self =>
  type Rep
  
  // TODO add to TaggedRep the vertex type
  type TaggedRep = FieldType[self.type, Rep]
  def ->>(r: Rep): FieldType[self.type, Rep] = field[self.type](r)
}

object vertexTags  {

  trait AnyVertexTag {

    type Vertex <: AnyVertex
  }
  
  trait VertexTag[V <: AnyVertex] extends AnyVertexTag with KeyTag[V,V#Rep] { type Vertex = V }

  type VertexRepType[V <: AnyVertex] = V#Rep with VertexTag[V]

  def vrep[V <: AnyVertex] = new VertexBuilder[V]

  class VertexBuilder[V <: AnyVertex] {
    def apply(vr : V#Rep): VertexRepType[V] = vr.asInstanceOf[VertexRepType[V]]
  }
}

