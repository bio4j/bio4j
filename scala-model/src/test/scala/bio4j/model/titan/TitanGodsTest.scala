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

    /* Do this once: */
    // graphLocation.delete
    // val g = GraphOfTheGodsFactory.create(graphLocation.getAbsolutePath)

    /* and then reuse the graph */
    val g: TitanGraph = TitanFactory.open(graphLocation.getAbsolutePath)

    /* maybe for tests, a safer approach is to create the graph every time: */
    // val tmpDir = java.nio.file.Files.createTempDirectory("titanTest")
    // val g = GraphOfTheGodsFactory.create(tmpDir.toString)

    // NOTE: this import shouldn't be needed, but it is
    // Taking it into account, maybe it's better to declare 
    // all has-property outside (in the GodsVertexTypes object)
    val saturn = titan ->> (g.getVertices("name", "saturn").iterator().next().asInstanceOf[TitanVertex])

    /* pure blueprints with string keys and casting: */
    assert(saturn.getProperty[Int]("age") === 10000)
    /* safe and nifty: */
    assert(saturn.get(age) === 10000)

    /* just another vertex */
    val hercules = demigod ->> (g.getVertices("name", "hercules").iterator().next().asInstanceOf[TitanVertex])
    /* same thing */
    assert(hercules.getProperty[Int]("age") === (hercules get age))

    /* Don't forget to turn the light off: */
    g.shutdown
  }

}
