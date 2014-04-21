package bio4j.model.test.titan

import com.thinkaurelius.titan.example.GraphOfTheGodsFactory
import com.thinkaurelius.titan.core._
import java.io.File

import GodsSchema._
import GodsImplementation._

import bio4j.model._

class titanSuite extends org.scalatest.FunSuite {

  test("create a titan graph") {
    val graphLocation = new File("/tmp/titanTest")

    /* Do this once: */
    // val g = GraphOfTheGodsFactory.create(graphLocation.getAbsolutePath)
    /* and then reuse the graph */
    // val g: TitanGraph = TitanFactory.open(graphLocation.getAbsolutePath)

    /* maybe for tests, a safer approach is to create the graph every time (but it's slow): */
    val tmpDir = java.nio.file.Files.createTempDirectory("titanTest")
    val g = GraphOfTheGodsFactory.create(tmpDir.toString)

    assert(titan.tpe.label === "titan")

    val saturn = titan ->> (g.getVertices("name", "saturn").iterator().next().asInstanceOf[TitanVertex])

    /* pure blueprints with string keys and casting: */
    assert(saturn.getProperty[Int]("age") === 10000)
    /* safe and nifty: */
    assert(saturn.get(age) === 10000)

    /* just another vertex */
    val hercules = demigod ->> (g.getVertices("name", "hercules").iterator().next().asInstanceOf[TitanVertex])
    /* same thing */
    assert(hercules.getProperty[Int]("age") === (hercules get age))

    // import demigod._
    // FIXME: it should be `List[battled.TaggedRep]`, but tags are lost...
    val es = hercules out battled
    val uh: List[battled.TaggedRep] = es
    assert((es map { e => (battled ->> e).get(time) }) === List(1, 2, 12))

    /* Don't forget to turn the light off: */
    g.shutdown
  }

}
