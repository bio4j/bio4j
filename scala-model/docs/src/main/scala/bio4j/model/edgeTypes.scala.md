
```scala
package bio4j.model
```


Declares an Edge type. They are essentially classified by its label, a `String`.

Its source and target types are not bound here, as that would lead to an explosion of equally named but different objects. The goal is to be able to express any source and target type configurations through witnesses.


```scala
trait AnyEdgeType { val label: String }
```


A convenience class for declaring types as case objects; just do

``` scala
case object Follows extends EdgeType
```


```scala
class EdgeType extends AnyEdgeType { val label = this.toString }

object AnyEdgeType {

  implicit class EdgeTypeOps[E <: AnyEdgeType](val edgeType: E) {

    def has[P <: AnyProperty](property: P): (E EdgeTypeHasProperty P) = EdgeTypeHasProperty(edgeType, property)
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