package bio4j.model.test.titan

// import org.scalatest._

import com.thinkaurelius.titan.example.GraphOfTheGodsFactory
import com.thinkaurelius.titan.core._
import java.io.File

import GodsSchema._
import GodsImplementation._

import bio4j.model._

class TitanSuite extends org.scalatest.FunSuite with org.scalatest.BeforeAndAfterAll {

  val graphLocation = new File("/tmp/titanTest")
  var g: TitanGraph = null

  // Reusing the graph if possible, else cleaning the directory and creating graph
  override def beforeAll() {
    try { 
      g = TitanFactory.open(graphLocation.getAbsolutePath)
      // checking that the graph is there:
      g.getVertices("name", "saturn").iterator().next()
      println("Reusing Titan graph")
    } catch {
      case e: Exception => {
        def cleanDir(f: File) {
          if (f.isDirectory) f.listFiles.foreach(cleanDir(_))
          else { println(f.toString); f.delete }
        }
        cleanDir(graphLocation)
        g = GraphOfTheGodsFactory.create(graphLocation.getAbsolutePath)
        println("Created Titan graph")
      }
    }
  }

  override def afterAll() {
    if(g != null) {
      g.shutdown
      println("Shutdown Titan graph")
    }
  }

  implicit class graphOps(tg: TitanGraph) {
    // just a shortcut
    def getTagged[V <: AnyTVertex](vx: V)(k: String, v: String): vx.TaggedRep = {
      vx ->> tg.getVertices(k, v).iterator().next().asInstanceOf[TitanVertex]
    }
  } 

  test("get vertex property") {

    val saturn = g.getTagged(titan)("name", "saturn")

    /* pure blueprints with string keys and casting: */
    assert(saturn.getProperty[Int]("age") === 10000)
    /* safe and nifty: */
    assert(saturn.get(age) === 10000)

  }

  test("get OUTgoing edges and their property") {

    val hercules = g.getTagged(demigod)("name", "hercules")
    
    assert(hercules.getProperty[Int]("age") === (hercules get age))

    val es: List[battled.TaggedRep] = hercules out battled
    assert((hercules out battled map { _ get time }).toSet === Set(1, 12, 2))
  }

  ignore("get INcoming edges and their property") {

    val tartarus = g.getTagged(location)("name", "tartarus")

    // FIXME: godLives and monsterLives have the same label.
    // it should get only one edge (for pluto), but it gets both, because they have the same label:
    info((tartarus in godLives map { _ get reason }).mkString("['","', '","']"))
  }

  test("get target/source vertices of incoming/outgoing edges") {

    val pluto = g.getTagged(god)("name", "pluto")

    val pe: List[pet.TaggedRep] = pluto out pet
    assert(pluto.out(pet).map{ _.target }.map{ _.get(name) } === List("cerberus"))

    assert(pluto.in(brother).map{ _.source }.map{ _.get(name) }.toSet === Set("neptune", "jupiter"))
    // symmetry:
    assert(pluto.in(brother).map{ _.source } 
      === pluto.out(brother).map{ _.target })
  }
}
