
```scala
package bio4j.model
```


Arities for rels. The point of this is that it lets you specify the right output type for when you get the outgoing edges of a given type from a vertex.


```scala
sealed trait AnyArityVertex { 
  type VType <: AnyVertexType
  val  vType: VType
  def --[E <: AnyEdgeType](e: E) = SourceAndEdgeType(this, e)
}
sealed trait ArityVertex[VT <: AnyVertexType] extends AnyArityVertex { type VType = VT }
case class  one[V <: AnyVertexType](vType: V) extends ArityVertex[V]
case class many[V <: AnyVertexType](vType: V) extends ArityVertex[V]

case class SourceAndEdgeType[S <: AnyArityVertex, E <: AnyEdgeType](s: S, e: E) {
  def -->[T <: AnyArityVertex](t: T) = new RelType[S,E,T](s, e, t)
}
```


Witnesses of a sourceType/type adscription to an edge type are called rels. It's not that I love this name, but...


```scala
trait AnyRelType {

  type InArity <: AnyArityVertex
  val inArity: InArity

  type SourceType = InArity#VType
  val sourceType: SourceType = inArity.vType

  type EdgeType <: AnyEdgeType
  val edgeType: EdgeType

  type OutArity <: AnyArityVertex
  val outArity: OutArity

  type TargetType = OutArity#VType
  val targetType: TargetType = outArity.vType

}

// object AnyRelType {
  // implicit def relTypeOps[R <: AnyRelType](rel: R): RelOps[R] = RelOps(rel)
// }

class RelType[
  X <: AnyArityVertex, 
  E <: AnyEdgeType, 
  Y <: AnyArityVertex
](val inArity: X, val edgeType: E, val outArity: Y) extends AnyRelType {

  type InArity = X
  type EdgeType = E
  type OutArity = Y

  // implicit val s = SourceOf[this.type]#is[this.SourceType]
}

object AnyRelType {
  type SourceOf[RT <: AnyRelType] = { 
    type is[VT <: AnyVertexType] = AnyRelType { type SourceType = VT }
  }
  type EdgeOf[RT <: AnyRelType] = { 
    type is[ET <: AnyEdgeType] = AnyRelType { type EdgeType = ET }
  }
  type TargetOf[RT <: AnyRelType] = { 
    type is[VT <: AnyVertexType] = AnyRelType { type TargetType = VT }
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

[test/scala/bio4j/model/properties.scala]: ../../../../test/scala/bio4j/model/properties.scala.md
[test/scala/bio4j/model/edges.scala]: ../../../../test/scala/bio4j/model/edges.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md
[test/scala/bio4j/model/rels.scala]: ../../../../test/scala/bio4j/model/rels.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/relTypes.scala]: ../../../../test/scala/bio4j/model/relTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: properties.scala.md
[main/scala/bio4j/model/reps.scala]: reps.scala.md
[main/scala/bio4j/model/edges.scala]: edges.scala.md
[main/scala/bio4j/model/vertices.scala]: vertices.scala.md
[main/scala/bio4j/model/relationships.scala]: relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md