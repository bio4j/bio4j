package bio4j.model.test

import bio4j.model._

import edgeTypes._
import vertexTypes._
import properties._

object edges {

  case class MemberOfImpl(
    val isPublic: Boolean,
    val since: Int,
    val validUntil: Int
  )

  object memberOf extends Edge(MemberOf) { edge =>
    type Rep = MemberOfImpl

    implicit object readSince extends ReadProperty(since) {
      def apply(rep: edge.EdgeRep): since.Rep = (rep: edge.Rep).since
    }
    implicit object readIsPublic extends ReadProperty(isPublic) {
      def apply(rep: edge.EdgeRep): isPublic.Rep = (rep: edge.Rep).isPublic
    }
    implicit object readValidUntil extends ReadProperty(validUntil) {
      def apply(rep: edge.EdgeRep): validUntil.Rep = (rep: edge.Rep).validUntil
    }
  }
}
