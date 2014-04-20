package bio4j.model.test

import com.thinkaurelius.titan.core.TitanVertex

import vertexTypes._
import properties._
import bio4j.model._
import shapeless.record.FieldType
import SmthHasProperty._

class TVertex[VT <: AnyVertexType](tpe: VT) extends Vertex(tpe) { self =>

  type Rep = TitanVertex

  import SmthHasProperty._
  implicit def unsafeGetProperty[P <: AnyProperty: PropertyOf[this.Tpe]#is](p0: P): this.GetProperty[P] = new GetProperty[P](p0) {
    def apply(rep: TaggedRep): p.Rep = rep.getProperty[p.Rep](p.label)
  }
}

case object titanUser extends TVertex[User.type](User)

abstract class stupidTest {

  import vertexTypes._
  import User._
  import titanUser._

  val uh: titanUser.TaggedRep

  val z = uh get id
  def getId(u: titanUser.TaggedRep) = u.get(id)
}
