package bio4j.model.test

import bio4j.model._

import properties._
import vertexTypes._

object edgeTypes {

  case object MemberOf extends EdgeType {
    this has isPublic
  }
  // add some more props externally  
  // directly
  implicit val withSince = EdgeTypeHasProperty(MemberOf, since)
  // through ops
  implicit val withValidUntil = MemberOf has validUntil


  case object Owns extends EdgeType {
    implicit val x = this has since
    implicit val y = this has validUntil
  }
}
