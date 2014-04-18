
```scala
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

```


------

### Index

+ src
  + main
    + scala
      + bio4j
        + model
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
  + test
    + scala
      + bio4j
        + model
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [rels.scala][test/scala/bio4j/model/rels.scala]
          + [relTypes.scala][test/scala/bio4j/model/relTypes.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]

[main/scala/bio4j/model/edges.scala]: ../../../../main/scala/bio4j/model/edges.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: ../../../../main/scala/bio4j/model/edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: ../../../../main/scala/bio4j/model/properties.scala.md
[main/scala/bio4j/model/relationships.scala]: ../../../../main/scala/bio4j/model/relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: ../../../../main/scala/bio4j/model/relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: ../../../../main/scala/bio4j/model/vertexTypes.scala.md
[main/scala/bio4j/model/vertices.scala]: ../../../../main/scala/bio4j/model/vertices.scala.md
[test/scala/bio4j/model/edges.scala]: edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: properties.scala.md
[test/scala/bio4j/model/rels.scala]: rels.scala.md
[test/scala/bio4j/model/relTypes.scala]: relTypes.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md