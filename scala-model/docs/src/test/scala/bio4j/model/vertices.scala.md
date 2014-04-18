
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
  
  case object user extends Vertex(User) { self =>
```

Now users can be created with `user ->> UserImpl(...)`

```scala
    type Rep = UserImpl
```

Provide implicits here (or elsewhere) for all (or some) properties

```scala
    implicit object readId extends ReadProperty(id) {
      def apply(rep: self.TaggedRep): id.Rep = (rep: self.Rep).id
    }
    implicit object readSince extends ReadProperty(since) {
      def apply(rep: self.TaggedRep): since.Rep = (rep: self.Rep).since
    }
  }

  case object org extends Vertex(Org) { self =>
```


We are lazy, so we will use the same representation for orgs
even though we care only about the `name` property


```scala
    type Rep = UserImpl

    implicit object readName extends ReadProperty(name) {
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
    implicit object readUserName extends user.ReadProperty(name) {
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
    implicit object readOrgSince extends org.ReadProperty(since) {
      def apply(rep: org.TaggedRep) = rep.since
    }
    assert((o get since) === 1977)

  }
}

```


------

### Index

+ src
  + test
    + scala
      + bio4j
        + model
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + [rels.scala][test/scala/bio4j/model/rels.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [relTypes.scala][test/scala/bio4j/model/relTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [reps.scala][main/scala/bio4j/model/reps.scala]
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]

[test/scala/bio4j/model/properties.scala]: properties.scala.md
[test/scala/bio4j/model/edges.scala]: edges.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md
[test/scala/bio4j/model/rels.scala]: rels.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/relTypes.scala]: relTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: ../../../../main/scala/bio4j/model/properties.scala.md
[main/scala/bio4j/model/reps.scala]: ../../../../main/scala/bio4j/model/reps.scala.md
[main/scala/bio4j/model/edges.scala]: ../../../../main/scala/bio4j/model/edges.scala.md
[main/scala/bio4j/model/vertices.scala]: ../../../../main/scala/bio4j/model/vertices.scala.md
[main/scala/bio4j/model/relationships.scala]: ../../../../main/scala/bio4j/model/relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: ../../../../main/scala/bio4j/model/relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: ../../../../main/scala/bio4j/model/vertexTypes.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: ../../../../main/scala/bio4j/model/edgeTypes.scala.md