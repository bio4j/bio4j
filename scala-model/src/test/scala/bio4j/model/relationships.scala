package bio4j.model.test

import bio4j.model._

import edgeTypes._
import vertexTypes._
import vertices._
import shapeless.record.FieldType

object memberOf extends Rel(usersMembersOfOrgs) {

  self =>

  type Rep = String
}

object getSource extends Source(memberOf, user) {

  @Override  
  def apply(relRep: FieldType[memberOf.type, memberOf.Rep]): FieldType[user.type, user.Rep] = 
    user ->> UserImpl(
                        id = "1ad3a34df",
                        name = "Robustiano Satrústegui",
                        since = 2349965
                      )
  }