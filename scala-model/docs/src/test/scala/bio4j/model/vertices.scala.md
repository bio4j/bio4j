
```scala
package bio4j.model.test

import vertexTypes._
import properties._
import bio4j.model._
import shapeless.record.FieldType

object vertices {
```

A representation of the `User` vertex type

```scala
  case class UserImpl(
    val id: String,
    val name: String,
    val since: Int
  )
  
  case object user extends Vertex[User.type](User) { self =>
```

Now users can be created with `user ->> UserImpl(...)`

```scala
    type Rep = UserImpl
```

Provide implicits here (or elsewhere) for all (or some) properties

```scala
    implicit object readId extends GetProperty(id) {
      def apply(rep: user.TaggedRep): id.Rep = (rep: user.Rep).id
    }
    implicit object readSince extends GetProperty(since) {
      def apply(rep: self.TaggedRep): since.Rep = (rep: self.Rep).since
    }
  }

  case object org extends Vertex[Org.type](Org) { self =>
```


We are lazy, so we will use the same representation for orgs
even though we care only about the `name` property


```scala
    type Rep = UserImpl

    implicit object readName extends GetProperty(name) {
      def apply(rep: self.TaggedRep) = rep.name
    }
  }

}

class VertexSuite extends org.scalatest.FunSuite {

  import vertices._  

  test("retrieve vertex properties") {

    import vertices.user._
    import vertexTypes.User._
    val u = user ->> UserImpl(id = "1ad3a34df", name = "Robustiano Satrústegui", since = 2349965)

    val u_id = u.get(id)
```


Too bad, no witness for `since`, so `u.get(since)` won't compile.
But we can add it here!


```scala
    implicit val userSince = User has since
    val u_since = u.get(since)
    val u_since_again = u get since
```


We can also add a retriever for the `name` property externally:


```scala
    implicit object readUserName extends GetProperty(name) {
      def apply(rep: user.TaggedRep) = rep.name
    }

    assert((u get id) === "1ad3a34df")
    assert((u get name) === "Robustiano Satrústegui")
    assert((u get since) === 2349965)
```


Again, we are using user's representation for org, 
even though it has only the `name` property


```scala
    import vertices.org._
    import vertexTypes.Org._
    val o = org ->> UserImpl(id = "NYSE:ORCL", name = "Oracle Inc.", since = 1977)

    assert((o get name) === "Oracle Inc.")
```


Now we realized, that we can do more things with this 
representation of org so we just implement it in place:


```scala
    implicit val orgFounded = Org has since
    implicit object readOrgSince extends org.GetProperty(since) {
      def apply(rep: org.TaggedRep) = rep.since
    }
    assert((o get since) === 1977)

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
          + [Denotation.scala][main/scala/bio4j/model/Denotation.scala]
          + [Edge.scala][main/scala/bio4j/model/Edge.scala]
          + [EdgeType.scala][main/scala/bio4j/model/EdgeType.scala]
          + [Property.scala][main/scala/bio4j/model/Property.scala]
          + [Vertex.scala][main/scala/bio4j/model/Vertex.scala]
          + [VertexType.scala][main/scala/bio4j/model/VertexType.scala]
  + test
    + scala
      + bio4j
        + model
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + titan
            + [godsImplementation.scala][test/scala/bio4j/model/titan/godsImplementation.scala]
            + [GodsSchema.scala][test/scala/bio4j/model/titan/GodsSchema.scala]
            + [TEdge.scala][test/scala/bio4j/model/titan/TEdge.scala]
            + [TitanGodsTest.scala][test/scala/bio4j/model/titan/TitanGodsTest.scala]
            + [TVertex.scala][test/scala/bio4j/model/titan/TVertex.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]

[main/scala/bio4j/model/Denotation.scala]: ../../../../main/scala/bio4j/model/Denotation.scala.md
[main/scala/bio4j/model/Edge.scala]: ../../../../main/scala/bio4j/model/Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: ../../../../main/scala/bio4j/model/EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: ../../../../main/scala/bio4j/model/Property.scala.md
[main/scala/bio4j/model/Vertex.scala]: ../../../../main/scala/bio4j/model/Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: ../../../../main/scala/bio4j/model/VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: properties.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: titan/godsImplementation.scala.md
[test/scala/bio4j/model/titan/GodsSchema.scala]: titan/GodsSchema.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: titan/TEdge.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: titan/TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: titan/TVertex.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md