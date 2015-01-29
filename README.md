## Bio4j

Bio4j is a bioinformatics graph data platform, integrating most data available in [**Uniprot KB**](http://www.uniprot.org/) (SwissProt + Trembl), [**Gene Ontology**](http://www.geneontology.org/) (GO), [**UniRef**](http://www.ebi.ac.uk/uniref/) (50,90,100), [**NCBI Taxonomy**](http://www.ncbi.nlm.nih.gov/Taxonomy/), and [**Expasy Enzyme DB**](http://enzyme.expasy.org/). 

Bio4j provides a completely new and powerful **framework for protein related information querying and management**. 
The use of a graph-based data model makes possible to store and query data in a way that semantically represents its own structure. On the contrary, traditional relational models and databases must flatten the data they represent into tables, creating _artificial_ ids in order to connect the different tuples; which can in some cases eventually lead to domain models that have almost nothing to do with the actual structure of data.

### Project structure

#### bio4j/bio4j

In this repository [bio4j/bio4j](https://github.com/bio4j/bio4j) you will find the generic Bio4j model and API. Entities, relationships and their properties are modeled using a typed [property graph](https://github.com/tinkerpop/blueprints/wiki/property-graph-model) model. For example, there are vertex types for `Protein` or `GoTerm`, and a `GoAnnotation` edge type going from `Protein` to `GoTerm`. This graph schema is separated into different graphs, corresponding to the different data sources (`UniProt`, `Go`, `UniRef`, ...) and connections between them (`UniProtGo`, `UniProtUniRef`, ...).

The API, based on [bio4j/angulillos](https://github.com/bio4j/angulillos), let's you write generic typed traversals over this graph schema:

``` Java
protein.uniref50Member_outV()
  .map( 
    UniRef50Cluster::uniRef50Member_inV
  )
  .map( 
    prts -> prts.map(
      Protein::goAnnotation_outV
    )
  );
```

which can later be executed on a particular backend. Generic data import code is also here, which can be used to load the data using any implementation of angulillos.

#### bio4j/bio4j-titan

In [bio4j/bio4j-titan](https://github.com/bio4j/bio4j-titan) you will find a [Titan](https://github.com/thinkaurelius/titan/)-based Bio4j distribution. This is the the default standard distribution, and we also provide through AWS S3 the database binaries with all data already loaded. Go there if you want to stop reading and use Bio4j now!

###  Licensing

Bio4j is an **open source** platform released under the [**AGPLv3**](http://www.gnu.org/licenses/agpl.html) license.

-----


## Documentation

* [Getting started](docs/getting-started.md)
* [Domain model](docs/domain-model.md)
* [Bio4j modules](docs/bio4j-modules.md)
* [Importing Bio4j](docs/importing-bio4j.md)
* Entry points and Indexing:
  - [Auxiliary relationships](docs/auxiliary-relationships.md)
  - [Node indexing](docs/node-indexing.md)
* [FAQ](docs/faq.md)
* API Docs: [v0.12.0-RC1](http://bio4j.com/bio4j/docs/api/0.12.0-RC1)
* [Examples](docs/examples.md)


### SBT dependency

To use it in your sbt-project, add this to `build.sbt`:

```scala
resolvers += "Era7 maven releases" at "https://s3-eu-west-1.amazonaws.com/releases.era7.com"

libraryDependencies += "bio4j" % "bio4j" % "0.12.0-RC1"
```

-----


## Community

### Mail list
There is a [google user group](http://groups.google.com/group/bio4j-user) available for Bio4j. Here you can post any question or general issue you may have related to Bio4j project. 

### Twitter
Bio4j twitter account [@bio4j](http://twitter.com/bio4j) is quite active, follow us if you want to be up to date with new features and project versions.

### LinkedIn

Bio4j [LinkedIn group](http://www.linkedin.com/groups/Bio4j-3890937) 

### Github issues

You can check or open new issues in the Bio4j repository [issue tracker](https://github.com/bio4j/bio4j/issues).
