package bio4j.model

import shapeless.FieldOf

trait AnyProperty extends Literal
class Property[V]() extends AnyProperty with FieldOf[V]

// why literals? they're just not needed
// user ->> Node(buh)
// age ->> 23
// edge 