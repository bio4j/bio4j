package bio4j.model

import shapeless.record.FieldType

/*
  A labeled Node
*/
trait AnyNode {

  val label: String
}
class Node    extends AnyNode { val label = this.toString }
class Literal extends AnyNode { val label = this.toString; type Value }

/*
  witness of a node of type N having a property of type P
*/
trait PropertyOf {

  type N <: AnyNode
  type P <: AnyProperty
}

/*
  used mainly for context bounds on the companion trait
*/
object PropertyOf {

  /*
    use as

    ``` scala
    import PropertyOf._
    def get[P <: AnyProperty: PropertyOf[N]#is](p: P): FieldType[p.type, p.Value]
    ```
  */
  type of[aN <: AnyNode] = {
    type is[aP <: AnyProperty] = PropertyOf { type N = aN; type P = aP }
  }

  type is[aN <: AnyNode, aP <: AnyProperty] = PropertyOf { type N = aN; type P = aP }
}


object NodeSchemas {

  trait AnyHas {

    type Node <: AnyNode
    val node: Node

    type Property <: AnyProperty
    val property: Property
  }

  case class Has[N <: AnyNode, P <: AnyProperty](node: N, property: P) extends AnyHas {

    type Node = N
    type Property = P
  }

  sealed trait AnyArity {

    type Has <: AnyHas
    val has: Has
    // TODO: think about the Node case; what is the value?
    // type Source
    // type Target
  }
  case class ManyToMany[H <: AnyHas](has: H) extends AnyArity { type Has = H
    // type Out = Option[List[has.property.Value]]
  }
  case class ManyToOne[H <: AnyHas](has: H) extends AnyArity {  type Has = H
    // type Out = has.property.Value
  }
  case class OneToMany[H <: AnyHas](has: H) extends AnyArity {  type Has = H  }
  case class OneToOne[H <: AnyHas](has: H) extends AnyArity { type Has = H  }


  case class SchemaOps[N <: AnyNode](node: N) {

    def has[P <: AnyProperty](property: P): (N Has P) = Has(node, property)
  }
  implicit def schemaOps[N <: AnyNode](node: N): SchemaOps[N] = SchemaOps(node)

  case class HasOps[H <: AnyHas](has: H){

    def manyToMany: ManyToMany[H] = ManyToMany(has)
    def manyToOne: ManyToOne[H] = ManyToOne(has)
  }
  implicit def hasOps[H <: AnyHas](has: H): HasOps[H] = HasOps(has)
}


// let's start without modules
trait AnyNodeOps {

  type N <: AnyNode
  val node: N

  import PropertyOf._
  def get[P <: AnyProperty: of[N]#is](p: P): FieldType[p.type, p.Value]
}

