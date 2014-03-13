# a Scala based model

## why?

- using Bio4j from Scala code
- abstract over storage
 

## how

### simple

This is what we are going to do as a first step:

- create an abstract typeclass per vertex/edge type
- bound the wrapped type and use it as a guide for typeclass resolution
- the return type is typeclass-specific
- create abstract providers for typeclasses using abstract modules
- a Bio4j module just groups them: they are there, inside

``` scala
trait GeneModule {

  trait Gene extends Vertex
  abstract class GeneOps[V <: Gene](val v: V) {

    // note that this type is local to the typeclass
    type E <: Encodes
    def encodes: E

    // if needed this could be specialized per operation
    // by requiring a method-specific typeclass
    // etc etc 
  }

  implicit def geneOps[G <: Gene](v: G): GeneOps[G] = GeneOps(v)
}

trait Encodes extends Edge
```

I don't see any signifcant problems with this approach, and it would allow for all the things that we need

1. store/access different modules in different ways, express modules at the abstract level
2. get precise typing for everything

### nice but hard

Everything through typeclasses, separate types from values, use records. It is _maybe_ possible, but looks pretty hard.
