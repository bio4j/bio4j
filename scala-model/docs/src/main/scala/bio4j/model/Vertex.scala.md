
```scala
package bio4j.model
```


`AnyVertex` defines a denotation of the corresponding `VertexType`.

Instances are modeled as instances of other type tagged with the singleton type of a `Vertex`. For example, an instance of a self of type `User` when stored/represented by a `Neo4jNode` is going to be something of type `FieldType[user.type, Neo4jNode]`  where `user.type <: AnyVertex { type VertexType = User.type; type Rep = Neo4jNode }`.

They are designed to be compatible with shapeless records (maybe, we'll see).


```scala
trait AnyVertex extends Denotation[AnyVertexType] { vertex =>
```

Getters for incoming/outgoing edges

```scala
  trait AnyRetrieveOutEdge {
    type Edge <: AnyEdge
    val e: Edge

    def apply(rep: vertex.TaggedRep): e.tpe.Out[e.TaggedRep]
  }
  abstract class RetrieveOutEdge[E <: Singleton with AnyEdge](val e: E) 
      extends AnyRetrieveOutEdge { type Edge = E }

  trait AnyRetrieveInEdge {
    type Edge <: AnyEdge
    val e: Edge

    def apply(rep: vertex.TaggedRep): e.tpe.In[e.TaggedRep]
  }
  abstract class RetrieveInEdge[E <: Singleton with AnyEdge](val e: E) 
      extends AnyRetrieveInEdge { type Edge = E }


  implicit def vertexOps(rep: vertex.TaggedRep) = VertexOps(rep)
  case class   VertexOps(rep: vertex.TaggedRep) {

    def out[E <: Singleton with AnyEdge { type Tpe <: EdgeFrom[vertex.Tpe] }]
      (e: E)(implicit mkRetriever: E => RetrieveOutEdge[E]): E#Tpe#Out[E#TaggedRep] = {
        val retriever = mkRetriever(e)
        retriever(rep)
      }

    def in[E <: Singleton with AnyEdge { type Tpe <: EdgeTo[vertex.Tpe] }]
      (e: E)(implicit mkRetriever: E => RetrieveInEdge[E]): E#Tpe#In[E#TaggedRep] = {
        val retriever = mkRetriever(e)
        retriever(rep)
      }

  }

}

abstract class Vertex[VT <: AnyVertexType](val tpe: VT) 
  extends AnyVertex { type Tpe = VT }

object AnyVertex {
  type ofType[VT <: AnyVertexType] = AnyVertex { type Tpe = VT }
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

[main/scala/bio4j/model/Denotation.scala]: Denotation.scala.md
[main/scala/bio4j/model/Edge.scala]: Edge.scala.md
[main/scala/bio4j/model/EdgeType.scala]: EdgeType.scala.md
[main/scala/bio4j/model/Property.scala]: Property.scala.md
[main/scala/bio4j/model/Vertex.scala]: Vertex.scala.md
[main/scala/bio4j/model/VertexType.scala]: VertexType.scala.md
[test/scala/bio4j/model/edges.scala]: ../../../../test/scala/bio4j/model/edges.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[test/scala/bio4j/model/properties.scala]: ../../../../test/scala/bio4j/model/properties.scala.md
[test/scala/bio4j/model/titan/godsImplementation.scala]: ../../../../test/scala/bio4j/model/titan/godsImplementation.scala.md
[test/scala/bio4j/model/titan/GodsSchema.scala]: ../../../../test/scala/bio4j/model/titan/GodsSchema.scala.md
[test/scala/bio4j/model/titan/TEdge.scala]: ../../../../test/scala/bio4j/model/titan/TEdge.scala.md
[test/scala/bio4j/model/titan/TitanGodsTest.scala]: ../../../../test/scala/bio4j/model/titan/TitanGodsTest.scala.md
[test/scala/bio4j/model/titan/TVertex.scala]: ../../../../test/scala/bio4j/model/titan/TVertex.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md