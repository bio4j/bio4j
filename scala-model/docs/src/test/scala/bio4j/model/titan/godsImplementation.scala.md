
```scala
package bio4j.model.test.titan

import GodsSchema._

object GodsImplementation {
```


### Vertices


```scala
  case object titan    extends TVertex(Titan)
  case object god      extends TVertex(God)
  case object demigod  extends TVertex(Demigod)
  case object human    extends TVertex(Human)
  case object monster  extends TVertex(Monster)
  case object location extends TVertex(Location)
```


### Edges


```scala
  case object titanFather  extends TEdge(god,     TitanFather,  titan)
  case object godFather    extends TEdge(demigod, GodFather,    god)
  case object humanMother  extends TEdge(demigod, HumanMother,  human)
  case object brother      extends TEdge(god,     Brother,      god)
  case object pet          extends TEdge(god,     Pet,          monster)
  case object battled      extends TEdge(demigod, Battled,      monster)
  case object godLives     extends TEdge(god,     GodLives,     location)
  case object monsterLives extends TEdge(monster, MonsterLives, location)

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

[main/scala/bio4j/model/Denotation.scala]: ../../../../../main/scala/bio4j/model/Denotation.scala.md
[main/scala/bio4j/model/Edge.scala]: ../../../../../main/scala/bio4j/model/Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: ../../../../../main/scala/bio4j/model/EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: ../../../../../main/scala/bio4j/model/Property.scala.md
[main/scala/bio4j/model/Vertex.scala]: ../../../../../main/scala/bio4j/model/Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: ../../../../../main/scala/bio4j/model/VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: ../edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: ../properties.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: godsImplementation.scala.md
[test/scala/bio4j/model/titan/GodsSchema.scala]: GodsSchema.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: TEdge.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: TVertex.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../vertices.scala.md