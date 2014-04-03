# Bio4j roadmap

## team

- [@eparejatobes](https://github.com/)
    + project direction
        * strategic decisions
        * GSoC admin
        * ...
    + technical lead
    + leads Scala development
    + leads Idris and dependent types development
    + communication
        * responsible for all Scala-related things
        * responsible for all Idris and dependent types things
- [@pablopareja](https://github.com/pablopareja)
    + project direction
        * strategic decisions
        * GSoC admin
        * ...
    + leads Java development
    + communication:
        * responsible for all Java-related things
- [@rtobes](https://github.com/rtobes)
    + leads Biology side
        * data sources
        * conformance to biological semantics
        * applications
        * ...
- [@laughedelic](https://github.com/laughedelic)
    + Scala developer
    + Idris and dependent types developer
    + release process and infrastructure
- [@evdokim](https://github.com/evdokim)
    + Scala developer
    + (depending on the intercrossing project, other things)
- [@marina-manrique](https://github.com/marina-manrique)
    + Scala developer
    + biology and bioinformatics applications

## Bio4j 1.0 Java version

- **Deadline** 2014-04-18

_assumptions_

- I will be working full-time the next 2 weeks
- same for @pablopareja

It is about time to have a stable, solid 1.0 Java version for Bio4j. And we can have a release candidate in two weeks! the timing is just perfect to match this with the GSoC community bonding phase.

I will create issues for all the following items, with milestones.

### stable 1.0 model API

With respect to the code, we are going to finish with the migration to new, more expressive API.

At the data model level, I want to review **all** nodes, relationships and properties to see if something should be changed; in particular

- properties could be promoted to nodes + rels in some cases
- review indexes: add or remove them, etc.
- simplify relationship names
- simplify node names
- see if we can add type nodes in some cases
- review everything from the semantics point of view (@rtobes, @marina-manrique)

Documentation for the code should reflect all this, linking each module to the corresponding data source. It is also important to have a reasonably detailed description of the design (funny F-bounded stuff, node and relationship types, etc).

### stable 1.0 Neo4j implementation

The single most disruptive change here would be to adapt the existing code to the new model. While we're at it, we will update to Neo4j 2.0.

- create abstract wrappers for Neo4j types based on Node and Relationship types
- an abstract Property reader/setter
- split the import code per module

## Bio4j paper

- **Deadline** 2014-??-??

This depends on a stable 1.0 Java version.

## AWS-based release process

- **Deadline** 2014-??-??

Automated testing, included as a release step. Possibly make the data releases independent projects. Think about it.

## Experimental Scala model API

- **Deadline** 2014-??-??

Working on it. Should be finished before the end of the GSoC community bonding phase. 




