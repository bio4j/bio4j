
```scala
package bio4j.model.test

import bio4j.model._

object edges {

  import edgeTypes._
  import vertexTypes._
  import vertices._
  import properties._

  case class MemberOfImpl(
    val source: user.TaggedRep,
    val target: org.TaggedRep
  )(
    val isPublic: Boolean,
    val since: Int,
    val validUntil: Int
  )

  object memberOf extends Edge(MemberOf) { self =>
    type Rep = MemberOfImpl

    implicit object sourceGetter extends GetSource[user.type](user) {
      def apply(rep: self.TaggedRep) = rep.source
    }

    implicit object readSince extends GetProperty(since) {
      def apply(rep: self.TaggedRep) = rep.since
    }
    implicit object readValidUntil extends GetProperty(validUntil) {
      def apply(rep: self.TaggedRep) = rep.validUntil
    }
  }

  case class OwnsImpl(
    val source: user.TaggedRep,
    val target: org.TaggedRep
  )
  object owns extends Edge(Owns) { self =>
    type Rep = OwnsImpl

    implicit object sourceGetter extends GetSource[user.type](user) {
      def apply(rep: self.TaggedRep) = rep.source
    }
    implicit object targetGetter extends GetTarget[org.type](org) {
      def apply(rep: self.TaggedRep) = rep.target
    }
  }

}

class EdgeSuite extends org.scalatest.FunSuite {

  import edges._  
  import edgeTypes._

  import vertexTypes._
  import vertices._
  import properties._

  test("retrieve edge's sourde-edge-target") {

    import edges.memberOf._
    import edgeTypes.MemberOf._
    val u = user ->> UserImpl(id = "1ad3a34df", name = "Robustiano Satrústegui", since = 2349965)
    val oracle: org.TaggedRep = org ->> UserImpl(id = "NYSE:ORCL", name = "Orcale Inc.", since = 1977)

    val m = memberOf ->> MemberOfImpl(source = u, target = oracle)(isPublic = true, since = 2349965, validUntil = 38724987)

    assert((m source) === u)

    // I think this is a bug; this import shouldn't be needed
    import vertexTypes.User._
    assert(
      (m.source get id) === "1ad3a34df"
    )

    // val followers_ids = (user out follows) map { _ get id }

```

Adding target getter externally:

```scala
    implicit object targetGetter extends GetTarget[org.type](org) {
      def apply(rep: memberOf.TaggedRep) = rep.target
    }
    assert(m.target === oracle)
```

Getting edge properties

```scala
    assert(m.get(since) === 2349965)
    assert(m.get(validUntil) === 38724987)

    implicit val weForgotToImportIt = memberOf.tpe has isPublic
    implicit object readIsPublic extends GetProperty(isPublic) {
      def apply(rep: memberOf.TaggedRep) = rep.isPublic
    }
    assert(m.get(isPublic) === true)
```

More vartices and edges

```scala
    val bob = user ->> UserImpl(id = "-1", name = "Bob", since = 0)
    val martin = user ->> UserImpl(id = "0", name = "Martin Odersky", since = 2011)
    val paulp = user ->> UserImpl(id = "1", name = "Paul Phillips", since = 2011)

    val typesafe = org ->> UserImpl(id = "martin123", name = "Typesafe Inc.", since = 2011)
```

Just a static list of all `memberOf` edges

```scala
    val members: List[memberOf.TaggedRep] = List(
      memberOf ->> MemberOfImpl(u, oracle)(false, 0, 0),
      // every company has some bob
      memberOf ->> MemberOfImpl(bob, oracle)(true, 0, 0),
      memberOf ->> MemberOfImpl(bob, typesafe)(false, 0, 0),
      memberOf ->> MemberOfImpl(martin, typesafe)(true, 0, 0)
      // memberOf ->> MemberOfImpl(paulp, typesafe)(true, 0, 0) // not anymore
    )
```

Retrieving edge

```scala
    implicit object retrieveMemberOf extends user.RetrieveEdge(memberOf) {
      def apply(rep: user.TaggedRep) = members filter { _.source == rep }
    }
    assert(bob.out(memberOf).map(_.target) === List(oracle, typesafe))


    val owners: List[owns.TaggedRep] = List(
      owns ->> OwnsImpl(u, oracle), // together with bob of course
      owns ->> OwnsImpl(bob, oracle),
      owns ->> OwnsImpl(martin, typesafe)
    )

    implicit object retrieveOwns extends user.RetrieveEdge(owns) {
      def apply(rep: user.TaggedRep) = owners find { _.source == rep }
    }
    assert(martin.out(owns).map(_.target) === Some(typesafe))
    // cool, martin owns some typesafe org

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
          + [Edge.scala][main/scala/bio4j/model/Edge.scala]
          + [EdgeType.scala][main/scala/bio4j/model/EdgeType.scala]
          + [Property.scala][main/scala/bio4j/model/Property.scala]
          + [Tagged.scala][main/scala/bio4j/model/Tagged.scala]
          + [Vertex.scala][main/scala/bio4j/model/Vertex.scala]
          + [VertexType.scala][main/scala/bio4j/model/VertexType.scala]
  + test
    + scala
      + bio4j
        + model
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]

[main/scala/bio4j/model/Edge.scala]: ../../../../main/scala/bio4j/model/Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: ../../../../main/scala/bio4j/model/EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: ../../../../main/scala/bio4j/model/Property.scala.md
[main/scala/bio4j/model/Tagged.scala]: ../../../../main/scala/bio4j/model/Tagged.scala.md
[main/scala/bio4j/model/Vertex.scala]: ../../../../main/scala/bio4j/model/Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: ../../../../main/scala/bio4j/model/VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: properties.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md