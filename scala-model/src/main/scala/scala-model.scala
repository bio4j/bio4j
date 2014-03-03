package bio4j.scalamodel


/*

  The basic idea is to treat literals and nodes in the same way. This means that nodes are different just based in

  - their type, and
  - the intrinsic graph structure
  
  Given this, give `n,m : User` their properties are considered as edges (named as the property); their scope should probably be
  restricted to the corresponding node type, `User` in this case. This gives you a nice understanding of indexes at the node level: 
  they are just a way of traversing these edges in the other direction.

  Now, what's the difference between this and a "normal" so to say Edge? There should be none, as far as I understand.

  It is nice that this matches with one the options suggested for Tinkerpop 3: 

  - https://groups.google.com/d/msg/gremlin-users/KqBv3jlj6xM/Fz7QLltUnxwJ

  All this means that the fields and relationships that a node has are

  - local to that type
  - declared externally
  - accessed through typeclasses

  ### restrictions

  When you declare a property, you can

  - restrict the source type and its arity
  - restrict the target type and its arity

  Note that at this point one does not need to choose between properties and edges. Essentially, a property is just and edge which

  1. has as source a non-primitive type, as target a primitive one
  2. for a given source it is unique

  _I need to remind myself about the view of properties as a special kind of edges_
  
*/


/*
  A node has just a type; all of its properties can be accessed through rels
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