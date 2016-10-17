# Bio4j bioinformatics graph data platform

[![](https://travis-ci.org/bio4j/bio4j.svg)](https://travis-ci.org/bio4j/bio4j)
[![](https://img.shields.io/codacy/a580b554490b42969eec4ab82d8463a0.svg)](https://www.codacy.com/app/bio4j/bio4j)
[![](http://github-release-version.herokuapp.com/github/bio4j/bio4j/release.svg)](https://github.com/bio4j/bio4j/releases/latest)
[![](https://img.shields.io/badge/license-AGPLv3-blue.svg)](https://tldrlegal.com/license/gnu-affero-general-public-license-v3-%28agpl-3.0%29)
[![](https://img.shields.io/badge/contact-gitter_chat-dd1054.svg)](https://gitter.im/bio4j/bio4j)


Bio4j is a bioinformatics graph data platform, integrating most data available in [**Uniprot KB**](http://www.uniprot.org/) (SwissProt + Trembl), [**Gene Ontology**](http://www.geneontology.org/) (GO), [**UniRef**](http://www.ebi.ac.uk/uniref/) (50,90,100), [**NCBI Taxonomy**](http://www.ncbi.nlm.nih.gov/Taxonomy/), and [**Expasy Enzyme DB**](http://enzyme.expasy.org/).

Bio4j provides a completely new and powerful **framework for protein related information querying and management**.
The use of a graph-based data model makes possible to store and query data in a way that semantically represents its own structure. On the contrary, traditional relational models and databases must flatten the data they represent into tables, creating _artificial_ ids in order to connect the different tuples; which can in some cases eventually lead to domain models that have almost nothing to do with the actual structure of data.

## Project structure and overview

<!-- TODO: add a diagram here, I think it would help -->

Bio4j can look a bit intimidating at first, with all those repositories with kind of similar names; here you have a guided tour around:

#### bio4j/bio4j

In this repository [bio4j/bio4j](https://github.com/bio4j/bio4j) you will find the generic Bio4j model and API. Entities, relationships and their properties are modeled using a typed [property graph](https://github.com/tinkerpop/blueprints/wiki/property-graph-model) model. For example, there are vertex types for `Protein` or `GoTerm`, and a `GoAnnotation` edge type going from `Protein` to `GoTerm`. This graph schema is separated into different graphs, corresponding to the different data sources (`UniProt`, `Go`, `UniRef`, ...) and connections between them (`UniProtGo`, `UniProtUniRef`, ...).

The API, based on [bio4j/angulillos](https://github.com/bio4j/angulillos), lets you write generic typed traversals over this graph schema:

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

#### bio4j/angulillos

You can think of [bio4j/angulillos](https://github.com/bio4j/angulillos) as a strongly typed version of the property graph model. You can describe graph schemas and write generic traversals over them which are guranteed to be well-typed in that for example

- you cannot retrieve the outgoing edges of and edge
- and you can get the tweets that a user tweeted, but not the users that a tweet follows!

#### bio4j/bio4j-titan

In [bio4j/bio4j-titan](https://github.com/bio4j/bio4j-titan) you will find a [Titan](https://github.com/thinkaurelius/titan/)-based Bio4j distribution. This is the the default standard distribution, and we also provide through AWS S3 the database binaries with all data already loaded. Go there if you want to stop reading and use Bio4j now!

#### bio4j/angulillos-titan

[bio4j/angulillos-titan](https://github.com/bio4j/angulillos-titan) is an implementation of the angulillos API using [Titan](https://github.com/thinkaurelius/titan/).

<!-- TODO: add more repos -->

## Documentation

* General docs: [docs/](docs/)
* Code docs: [docs/src/](docs/src/main/java/com/bio4j/model/)
* API Docs: [v0.12.0](http://bio4j.com/bio4j/docs/api/0.12.0/)

<!-- * [Getting started](docs/getting-started.md)
* [Domain model](docs/domain-model.md)
* [Bio4j modules](docs/bio4j-modules.md)
* [Importing Bio4j](docs/importing-bio4j.md)
* Entry points and Indexing:
  - [Auxiliary relationships](docs/auxiliary-relationships.md)
  - [Node indexing](docs/node-indexing.md)
* [FAQ](docs/faq.md)
* [Examples](docs/examples.md) -->


## Community and contact

- **[Gitter chat](https://gitter.im/bio4j/bio4j)** _The easiest and fastest way to contact developers and ask for help_
- **[bio4j-user](http://groups.google.com/group/bio4j-user)** _Google group / mailing list_
- **[@bio4j](http://twitter.com/bio4j)** _Twitter_
- **[Bio4j](http://www.linkedin.com/groups/Bio4j-3890937)** _LinkedIn_

##  Licensing

Bio4j is an **open source** platform released under the [**AGPLv3**](http://www.gnu.org/licenses/agpl.html) license.
