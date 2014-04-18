package bio4j.model.test

import bio4j.model._

import edgeTypes._
import relTypes._
import vertexTypes._
import vertices._
import shapeless.record.FieldType

object rels {

  case class RelImpl[RT <: AnyRelType](val rt: RT)(
    val source: RT#SourceType
  )

  object usersMembersOfOrgs extends Rel(UsersMembersOfOrgs) { rel =>
    type Rep = String
  }

/*
object getSource extends Source(memberOf, user) {

  @Override  
  def apply(relRep: memberOf.RelRep): user.VertexRep = 
    user ->> UserImpl(
                        id = "1ad3a34df",
                        name = "Robustiano Satrústegui",
                        since = 2349965
                      )
  }
*/
}
