# Bio4j roadmap

## team

- [@eparejatobes](https://github.com/eparejatobes)
    + project direction
        * strategic decisions
        * GSoC admin and mentor
    + technical lead
    + leads Scala development
    + leads Idris and dependent types development
    + communication
        * responsible for all Scala-related things
        * responsible for all Idris and dependent types things
- [@pablopareja](https://github.com/pablopareja)
    + project direction
        * strategic decisions
        * GSoC admin and mentor
    + leads Java development
    + communication:
        * responsible for all Java-related things
- [@rtobes](https://github.com/rtobes)
    + leads Biology side
        * data sources
        * conformance to biological semantics
        * applications
    + Scala developer
- [@epareja](https://github.com/epareja)
    + biology -> cancer genomics applications
    + general project strategy
- [@laughedelic](https://github.com/laughedelic)
    + Scala developer
    + Idris and dependent types developer
    + release process and infrastructure
    + GSoC mentor
- [@evdokim](https://github.com/evdokim)
    + Scala developer
    + GSoC mentor
- [@marina-manrique](https://github.com/marina-manrique)
    + Scala developer
    + biology and bioinformatics applications

## Bio4j 1.0 Java version

- **Deadline** 2014-06-01
- **Who**
    + @eparejatobes
    + @pablopareja
    + @rtobes
    + @marina-manrique
    + @epareja

It is about time to have a stable, solid 1.0 Java version for Bio4j. And we can have a release candidate in two weeks! the timing is just perfect to match this with the GSoC community bonding phase.

I will create issues for all the following items, with milestones.

### stable 1.0 model API

With respect to the code, we are going to finish with the migration to new, more expressive API. This will 

At the data model level, I want to review **all** nodes, relationships and properties to see if something should be changed; in particular

- properties could be promoted to nodes + rels in some cases
- review indexes: add or remove them, etc.
- simplify relationship names
- simplify node names
- see if we can add type nodes in some cases
- review everything from the semantics point of view (@rtobes, @marina-manrique)

Documentation for the code should reflect all this, linking each module to the corresponding data source. It is also important to have a reasonably detailed description of the design (funny F-bounded stuff, node and relationship types, etc).

### stable 1.0 Titan implementation

The single most disruptive change here would be to adapt the existing code to the new model. While we're at it, we will update to Neo4j 2.0.

- create abstract wrappers for Neo4j types based on Node and Relationship types
- an abstract Property reader/setter
- split the import code per module

For documentation,

- the AWS option needs to be promoted as the default
- the import docs should be crystal clear

### examples

- @evdokim
- laughedelic
- @marina-manrique

All of them based in AWS and bundles. At a minimum

- basic traversals using the abstract model
- some Cypher stuff
- any biologically meaningful thing

## Bio4j paper

- **Deadline** 2014-??-??
- **Who**
    + @eparejatobes
    + @rtobes
    + @epareja
    + @pablopareja
    + @marina-manrique

This depends on a stable 1.0 Java version.

## AWS-based release process

- **Deadline** 2014-??-??
- **Who**
    + @laughedelic
    + @eparejatobes
    + @evdokim

Automated testing, included as a release step. Possibly make the data releases independent projects. Think about it.

## model REST services

- **Deadline** 2014-05-20
- **Who**
    + @laughedelic
    + @eparejatobes

We need to have REST services exposing the data model in a JSON-based format: [GraphSON](https://github.com/thinkaurelius/faunus/wiki/GraphSON-Format). This is going to be used for

- graphical interactive exploration of the Bio4j model
- interactive client-side query building?
- ...

Taking advantage of the homoiconicity of graphs, we can easily describe a graph schema as a graph:

- vertex, property _and edge_ types are represented as vertices
- source and target information is encoded as edges of the corresponding types
- same for ascribing properties to vertices and edges
- indexes should fit in this framework too

We will store this in a graph database of course :) It needs to be scoped by model version and module. We will define later how to integrate this with model releases (one possible option is including a super naïve implementation of the model corresponding to the types).

Given that, it is really easy to implement a service giving you a `.graphson` representation of the model. Probably [Unfiltered](https://github.com/unfiltered/unfiltered), we want something simple.

Now that I think of it, we could use the Java `NodeType`, `RelationshipType`, `PropertyType` stuff for generating all this; if all of them would be stored as nested enums we could probably get all instances and iterate over them somehow.

This would need to be manually maintained; I don't see any other option. Not so bad anyway; in the future we could turn it round and generate code from this schema-like graph data, or use type providers of some sort.

## Experimental Scala model API

- **Deadline** 2014-04-20
- **Who**
    + @eparejatobes
    + @laughedelic
    + @evdokim

Working on it. Should be finished before the end of the GSoC community bonding phase. It would be nice to start working on a Titan implementation for it.

## Drosophila data integration?

- **Deadline** ??
- **Who**
    + @laughedelic
    + @rtobes
    + @eparejatobes

Need to check this with @rtobes

## Idris + Bio4j type providers

- **Deadline** 2014-06-20
- **Who**
    + @eparejatobes
    + @laughedelic

The basic idea goes as follows

1. write an Idris representation of the Gremlin "language" or some equivalent spec.
2. write a compiler targeting 
    1. Gremlin Groovy syntax? see [Using Gremlin through Java](https://github.com/tinkerpop/gremlin/wiki/Using-Gremlin-through-Java)
    2. [Blueprints code](http://www.tinkerpop.com/docs/javadocs/blueprints/2.4.0/) as a combination of `Vertex`/`Edge` methods and `VertexQuery`
3. the resulting code could then be easily run :)

We need to understand exactly what kind of query operations we have here. Initially, just something simple.

- [The power of pi](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.175.8947&rep=rep1&type=pdf) the SQL section.



