
```scala
package bio4j.model.test

import bio4j.model._
import vertexTypes._
import properties._

object edgeTypes {
  // specify a source and target
  case object MemberOf extends ManyToMany(User, Org)
  // add some more props externally  
  // directly
  implicit val withSince = EdgeTypeHasProperty(MemberOf, since)
  // through ops
  implicit val withValidUntil = MemberOf has validUntil

  // explicit witness
  case object Owns extends ManyToOne(User, Org){
    implicit val x = this has since
    implicit val y = this has validUntil
  }

  // pretty cool DSL
  implicit val thisIsSoCoolItScaresMe = many(User) -- "owns" --> one(Org)
  implicit val manymanymany = many(User) -- "member of" --> many(Org)

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