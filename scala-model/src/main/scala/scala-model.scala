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

  ### restrictions

  One simple way of enforcing such restrictions is available when declaring such a model

  ``` scala
    
    // a Protein node
    // note that it can perfectly be an object!
    case object Protein extends Node

    // which fields and ops are available is done through ProteinOps
    trait NodeOps[N <: Node, M <: Module] {
  
      // get something of a rel that can be associated with N
      // guided by a singleton type (scoped to module?)
    }
  ```

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