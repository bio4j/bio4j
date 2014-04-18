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

  object memberOf extends Edge(MemberOf) { self =>
    type Rep = MemberOfImpl

    implicit object readSince extends ReadProperty(since) {
      def apply(rep: self.TaggedRep) = rep.since
    }
    implicit object readIsPublic extends ReadProperty(isPublic) {
      def apply(rep: self.TaggedRep) = rep.isPublic
    }
    implicit object readValidUntil extends ReadProperty(validUntil) {
      def apply(rep: self.TaggedRep) = rep.validUntil
    }
  }
}

class EdgeSuite extends org.scalatest.FunSuite {

  import edges._  

  test("retrieve edge properties") {

    import edgeTypes._
    import edgeTypes.MemberOf._
    import edges.memberOf._
    val m = memberOf ->> MemberOfImpl(isPublic = true, since = 2349965, validUntil = 38724987)

    // the witness for `isPublic` is commented in the edgeTypes
    // so we just add it here:
    implicit val witness = MemberOf has isPublic

    assert((m get isPublic) === true)
    assert((m get since) === 2349965)
    assert((m get validUntil) === 38724987)

  }
}
