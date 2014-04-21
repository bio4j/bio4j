
```scala
package bio4j.model.test.titan

import bio4j.model._

object godsSchema {
```


#### Properties


```scala
  case object name extends Property[String]
  case object age extends Property[Int]
  // instead of this property we will create REAL types (because we are cool)
  // case object `type` extends Property[String]
  case object time extends Property[Int]
  case object reason extends Property[String]
  // This is something advanced:
  import com.thinkaurelius.titan.core.attribute.Geoshape
  case object place extends Property[Geoshape]
```


#### vertices


```scala
  case object Titan extends VertexType
  implicit val Titan_name = Titan has name
  implicit val Titan_age  = Titan has age

  case object God extends VertexType
  implicit val God_name = God has name
  implicit val God_age  = God has age

  case object Demigod extends VertexType
  implicit val Demigod_name = Demigod has name
  implicit val Demigod_age  = Demigod has age

  case object Human extends VertexType
  implicit val Human_name = Human has name
  implicit val Human_age  = Human has age

  case object Monster extends VertexType
  implicit val Monster_name = Monster has name
  
  case object Location extends VertexType
  implicit val Location_name = Location has name
```


#### edges

Family relationships

```scala
  // gods can have a titan father
  case object TitanFather extends ManyToOne(God, Titan)
  // demigods can have a god father
  case object GodFather   extends ManyToOne(Demigod, God)
  // demigods can have a human mother
  case object HumanMother extends ManyToOne(Demigod, Human)
  // gods can be brothers with gods
  case object GodBrother  extends ManyToMany(God, God)
  // TODO: other combinations are also possible, but are just not needed for the example

```

Other relationships

```scala
  // a god can have moster pets
  case object Pet         extends OneToMany(God, Monster)
  // demigods battle with monsters
  case object Battled     extends ManyToMany(Demigod, Monster)
  implicit val Battled_time  = Battled has time
  implicit val Battled_place = Battled has place
  // gods live in some location
  case object GodLives extends ManyToOne(God, Location)
  implicit val GodLives_reason = GodLives has reason
  // monsters live in some location (without any reason)
  case object MonsterLives extends ManyToOne(Monster, Location) 
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

[test/scala/bio4j/model/properties.scala]: ../properties.scala.md
[test/scala/bio4j/model/edges.scala]: ../edges.scala.md
[test/scala/bio4j/model/vertices.scala]: ../vertices.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: TEdge.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: TVertex.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: godsImplementation.scala.md
[test/scala/bio4j/model/titan/godsSchema.scala]: godsSchema.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../vertexTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../edgeTypes.scala.md
[main/scala/bio4j/model/Denotation.scala]: ../../../../../main/scala/bio4j/model/Denotation.scala.md
[main/scala/bio4j/model/EdgeType.scala]: ../../../../../main/scala/bio4j/model/EdgeType.scala.md
[main/scala/bio4j/model/VertexType.scala]: ../../../../../main/scala/bio4j/model/VertexType.scala.md
[main/scala/bio4j/model/Vertex.scala]: ../../../../../main/scala/bio4j/model/Vertex.scala.md
[main/scala/bio4j/model/Edge.scala]: ../../../../../main/scala/bio4j/model/Edge.scala.md
[main/scala/bio4j/model/Property.scala]: ../../../../../main/scala/bio4j/model/Property.scala.md