package bio4j.scalamodel

/*
  A node has just a type; all of its "properties" can be accessed through rels
*/
trait Node {

  val label: String
}

case object Protein extends Node { val label = "protein" }

case class NodeOps[N <: Node, M <: Module](node: N, module: M) {

  import Has._
  // bound or explicit implicit, not so important
  def get[P <: Property: MemberOf[N]#is](p: P)(implicit ev: Has.is[N,P]): ev.Out = ev.eval(node)
}

trait Module
// now let people get properties depending on this
object Has {

  type MemberOf[aN <: Node] = {
    type is[aP <: Property] = Has { type N = aN; type P = aP }
  }

  type is[aN <: Node, aP <: Property] = Has { type N = aN; type P = aP }
}
trait Has {

  type N <: Node
  type P <: Property

  type Out

  def eval(n: N): Out
}






trait Edge {

  type Source
  type Target
}

// key-value stuff
trait Property