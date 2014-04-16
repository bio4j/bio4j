In general

1. don't use inheritance
2. encode the schema in the graph:
  1. types represented as nodes
  2. relationships going to type nodes represent type adscription (kinds etc possible)
  3. all properties corresponding to that type go in the relationship


I think that this applies to

- annotations (features)
- references (citations)
- keywords
- _there's something else here?_

##### examples

- **Getting all nodes of a given type** just retrieve incoming rels to the type node
- **sort all nodes based on a particular property** local indexes! note how it is key to have the properties _in_ the edge here

## annotations

See

- [UniProt manual - sequence annotations](http://www.uniprot.org/manual/sequence_annotation)
- [UniProt OWL - Annotation](http://www.uniprot.org/core/Annotation)

## references

See

- [UniProt manual - references](http://www.uniprot.org/manual/references)
- [UniProt OWL - Citation](http://www.uniprot.org/core/Citation)

As before

1. add type nodes for each citation type
2. move the properties to the typing rel: `p -- citedIn --> ref -- article --> articleType`
3. getting all articles, books, whatever is really easy; same for adding further structure and/or types
4. at the code level, we have a base node class for `Reference`. No inheritance here: this is done through node types.
5. we can do inheritance at the relationship level just for code economy: if all citations have some common data that could be added at a base class of the reference relationship.

## keywords

We will integrate them as types in the future

- [UniProt docs - keywords list](http://www.uniprot.org/docs/keywlist)
- [UniProt RDF distribution - keywords](http://beta.sparql.uniprot.org/keywords)


