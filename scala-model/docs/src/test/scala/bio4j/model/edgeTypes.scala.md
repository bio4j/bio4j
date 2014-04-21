
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
  + test
    + scala
      + bio4j
        + model
          + [properties.scala][test/scala/bio4j/model/properties.scala]
          + [edges.scala][test/scala/bio4j/model/edges.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + titan
            + [TitanGodsTest.scala][test/scala/bio4j/model/titan/TitanGodsTest.scala]
            + [TEdge.scala][test/scala/bio4j/model/titan/TEdge.scala]
            + [TVertex.scala][test/scala/bio4j/model/titan/TVertex.scala]
            + [godsImplementation.scala][test/scala/bio4j/model/titan/godsImplementation.scala]
            + [godsSchema.scala][test/scala/bio4j/model/titan/godsSchema.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [Denotation.scala][main/scala/bio4j/model/Denotation.scala]
          + [EdgeType.scala][main/scala/bio4j/model/EdgeType.scala]
          + [VertexType.scala][main/scala/bio4j/model/VertexType.scala]
          + [Vertex.scala][main/scala/bio4j/model/Vertex.scala]
          + [Edge.scala][main/scala/bio4j/model/Edge.scala]
          + [Property.scala][main/scala/bio4j/model/Property.scala]

[test/scala/bio4j/model/properties.scala]: properties.scala.md
[test/scala/bio4j/model/edges.scala]: edges.scala.md
[test/scala/bio4j/model/vertices.scala]: vertices.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: titan/TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: titan/TEdge.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: titan/TVertex.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: titan/godsImplementation.scala.md
[test/scala/bio4j/model/titan/godsSchema.scala]: titan/godsSchema.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md
[main/scala/bio4j/model/Denotation.scala]: ../../../../main/scala/bio4j/model/Denotation.scala.md
[main/scala/bio4j/model/EdgeType.scala]: ../../../../main/scala/bio4j/model/EdgeType.scala.md
[main/scala/bio4j/model/VertexType.scala]: ../../../../main/scala/bio4j/model/VertexType.scala.md
[main/scala/bio4j/model/Vertex.scala]: ../../../../main/scala/bio4j/model/Vertex.scala.md
[main/scala/bio4j/model/Edge.scala]: ../../../../main/scala/bio4j/model/Edge.scala.md
[main/scala/bio4j/model/Property.scala]: ../../../../main/scala/bio4j/model/Property.scala.md