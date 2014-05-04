package bio4j.model

import shapeless._
import shapeless.ops.hlist._

sealed trait Morphism {

  type Source <: HList
  val source: Source

  type Target <: HList
  val target: Target
}

  case class Simple[E <: AnyEdgeType with Singleton](val edge: E)

  extends Morphism {

    type Source = edge.SourceType :: HNil
    val source  = edge.sourceType :: HNil

    type Target = edge.TargetType :: HNil
    val target  = edge.targetType :: HNil
  }

  case class Par[F <: Morphism, G <: Morphism](val f: F, val g: G)
  (implicit
    val prependSource: Prepend[F#Source, G#Source],
    val prependTarget: Prepend[F#Target, G#Target]
  )

  extends Morphism {

    // need Prepend for both sources and targets
    type Source = prependSource.Out
    val  source = (f.source:F#Source) ::: (g.source:G#Source)
    
    type Target = prependTarget.Out
    val  target = (f.target:F#Target) ::: (g.target:G#Target)
  }

  case class Compose[G <: Morphism, F <: Morphism](val g: G, val f: F)
  (implicit
    val eqSrcTgt: (G#Source =:= F#Target)
  )

  extends Morphism {

    type Source = f.Source
    val  source = f.source

    type Target = g.Target
    val  target = g.target
  }