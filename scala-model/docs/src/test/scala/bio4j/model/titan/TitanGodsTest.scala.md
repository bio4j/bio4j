
```scala
package bio4j.model.test.titan

import com.thinkaurelius.titan.example.GraphOfTheGodsFactory
import com.thinkaurelius.titan.core._
import java.io.File

import godsSchema._
import godsImplementation._

import bio4j.model._

class titanSuite extends org.scalatest.FunSuite {

  test("create a titan graph") {
    val graphLocation = new File("/tmp/titanTest")
```

Do this once:

```scala
    // graphLocation.delete
    // val g = GraphOfTheGodsFactory.create(graphLocation.getAbsolutePath)

```

and then reuse the graph

```scala
    val g: TitanGraph = TitanFactory.open(graphLocation.getAbsolutePath)
```

maybe for tests, a safer approach is to create the graph every time:

```scala
    // val tmpDir = java.nio.file.Files.createTempDirectory("titanTest")
    // val g = GraphOfTheGodsFactory.create(tmpDir.toString)

    // NOTE: this import shouldn't be needed, but it is
    // Taking it into account, maybe it's better to declare 
    // all has-property outside (in the GodsVertexTypes object)
    val saturn = titan ->> (g.getVertices("name", "saturn").iterator().next().asInstanceOf[TitanVertex])
```

pure blueprints with string keys and casting:

```scala
    assert(saturn.getProperty[Int]("age") === 10000)
```

safe and nifty:

```scala
    assert(saturn.get(age) === 10000)
```

just another vertex

```scala
    val hercules = demigod ->> (g.getVertices("name", "hercules").iterator().next().asInstanceOf[TitanVertex])
```

same thing

```scala
    assert(hercules.getProperty[Int]("age") === (hercules get age))
```

Don't forget to turn the light off:

```scala
    g.shutdown
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