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

    implicit def iterableToOption[T](i: Iterable[T]): Option[T] = i.headOption
    implicit def   iterableToList[T](i: Iterable[T]): List[T] = i.toList

    // FIXME: Can't find RetrieveOutEdge for hercules.out(...)
    // becase even with chaining implicits as in property getters,
    // unsafeRetrieveOutEdge requires an additiona implicit for
    // converting Iterable to e.Out[...]

    // import demigod._
    // val ms = hercules.out(battled)(demigod.unsafeRetrieveOutEdge)
    // println(ms.map(_.get(times)))

    /* Don't forget to turn the light off: */
    g.shutdown
  }

}
