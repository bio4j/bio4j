## Bio4j

Bio4j is a bioinformatics graph based DB including most data available in [**Uniprot KB**](http://www.uniprot.org/) (SwissProt + Trembl), [**Gene Ontology**](http://www.geneontology.org/) (GO), [**UniRef**](http://www.ebi.ac.uk/uniref/) (50,90,100), [**NCBI Taxonomy**](http://www.ncbi.nlm.nih.gov/Taxonomy/), and [**Expasy Enzyme DB**](http://enzyme.expasy.org/). 

Bio4j provides a completely new and powerful **framework for protein related information querying and management**. 
Since it relies on a high-performance graph engine, data is stored in a way that semantically represents its own structure. 
On the contrary, traditional relational databases must flatten the data they represent into tables, creating _artificial_ ids in order to connect the different tuples; which can in some cases eventually lead to domain models that have almost nothing to do with the actual structure of data.


### Scalability

* First of all, Bio4j has an [**Abstract Domain Model**](docs/domain-model.md), which allows you to use it without binding to a particular backend implementation.

* Next, it has an intermediate [**Blueprints layer**](https://github.com/bio4j/blueprints), which allows us to make a default implementation of the abstract interface using [Tinkerpop Blueprints API](https://github.com/tinkerpop/blueprints/wiki) and at the same time stay independent from the choice of database technology.

* And finally, there are technology specific versions:
  - [**Titan DB implementation**](https://github.com/bio4j/bio4j-titan)
  - [**Neo4j DB implementation**](https://github.com/bio4j/bio4j-neo4j)

### Modularity

Bio4j includes a few different data sources and you may not always be interested in having all of them together. That’s why the importing process is modular and customizable, allowing you to import just the data you are interested in.

Also, Bio4j has [Statika-based module system](https://github.com/bio4j/modules), which dramatically simplifies the process of building and deploying custom releases of Bio4j.

### Performance

In Bio4j data is organized in a way semantically equivalent to what it represents thanks to the graph structure. That means that queries which would even be impossible to perform with a standard Relational DB, can be feasible with Bio4j obtaining good performance results.

###  Licensing

Bio4j is an **open source** platform released under [**AGPLv3**](http://www.gnu.org/licenses/agpl.html).


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
* API Docs: [v0.11.0](http://bio4j.com/bio4j/docs/api/0.11.0)
* [Examples](docs/examples.md)


### SBT dependency

To use it in your sbt-project, add this to `build.sbt`:

```scala
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

libraryDependencies += "bio4j" % "bio4j" % "0.11.0"
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
