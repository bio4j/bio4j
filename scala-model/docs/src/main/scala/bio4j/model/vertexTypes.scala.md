
```scala
package bio4j.model
```

Declares a Vertex type. They are essentially classified by its label, a `String`.

```scala
trait AnyVertexType {  val label: String  }
```

  A convenience class for declaring types as case objects; just do

  ``` scala
  case object User extends VertexType
  ```


```scala
class VertexType  extends AnyVertexType { val label = this.toString }
class LiteralType extends AnyVertexType { val label = this.toString }

object AnyVertexType {

  implicit def vertexTypeOps[E <: AnyVertexType](vertexType: E): VertexTypeOps[E] = VertexTypeOps(vertexType) 
}

case class VertexTypeOps[E <: AnyVertexType](val vertexType: E) {


  def has[P <: AnyProperty](property: P): (E VertexTypeHasProperty P) = 
    VertexTypeHasProperty(vertexType, property)
}

```


------

### Index

+ src
  + test
    + scala
      + bio4j
        + model
          + [propertyTypes.scala][test/scala/bio4j/model/propertyTypes.scala]
          + [vertices.scala][test/scala/bio4j/model/vertices.scala]
          + [relationships.scala][test/scala/bio4j/model/relationships.scala]
          + [vertexTypes.scala][test/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][test/scala/bio4j/model/edgeTypes.scala]
  + main
    + scala
      + bio4j
        + model
          + [properties.scala][main/scala/bio4j/model/properties.scala]
          + [edges.scala][main/scala/bio4j/model/edges.scala]
          + [vertices.scala][main/scala/bio4j/model/vertices.scala]
          + [relationships.scala][main/scala/bio4j/model/relationships.scala]
          + [relationshipTypes.scala][main/scala/bio4j/model/relationshipTypes.scala]
          + [vertexTypes.scala][main/scala/bio4j/model/vertexTypes.scala]
          + [edgeTypes.scala][main/scala/bio4j/model/edgeTypes.scala]

[test/scala/bio4j/model/propertyTypes.scala]: ../../../../test/scala/bio4j/model/propertyTypes.scala.md
[test/scala/bio4j/model/vertices.scala]: ../../../../test/scala/bio4j/model/vertices.scala.md
[test/scala/bio4j/model/relationships.scala]: ../../../../test/scala/bio4j/model/relationships.scala.md
[test/scala/bio4j/model/vertexTypes.scala]: ../../../../test/scala/bio4j/model/vertexTypes.scala.md
[test/scala/bio4j/model/edgeTypes.scala]: ../../../../test/scala/bio4j/model/edgeTypes.scala.md
[main/scala/bio4j/model/properties.scala]: properties.scala.md
[main/scala/bio4j/model/edges.scala]: edges.scala.md
[main/scala/bio4j/model/vertices.scala]: vertices.scala.md
[main/scala/bio4j/model/relationships.scala]: relationships.scala.md
[main/scala/bio4j/model/relationshipTypes.scala]: relationshipTypes.scala.md
[main/scala/bio4j/model/vertexTypes.scala]: vertexTypes.scala.md
[main/scala/bio4j/model/edgeTypes.scala]: edgeTypes.scala.md