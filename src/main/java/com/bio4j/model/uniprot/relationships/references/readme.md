## references

See

- [UniProt manual - references](http://www.uniprot.org/manual/references)
- [UniProt OWL - Citation](http://www.uniprot.org/core/Citation)

All specific properties of each type are stored in relationships pointing to a singleton vertex acting as a graph-level witness of the type ascription.

For citations we have

1. a relationship going `Protein -- CitedIn --> Reference` holding the citation generic data: I think that only date (year) here.
2. `Author` rels go `Reference -- Author --> Author`; they include a name. Note that the `Author` node has just one ID; its label is not important, actually. The author type can be a person or a consortium; we create the corresponding rels
    - `Author -- Person --> Persons`
    - `Author -- Consortium --> Consortiums`
  properties specific to the type go the relationship again.
3. From `Reference` we have at least `Thesis`, `JournalArticle`, `Book`, `UnpublishedObervation`. Same as before: `Reference -- Article --> Articles`.


#### aside: type refinement

Imagine that now you want to add an edge out of an author, but only in the case that it is a consortium; think of adding the consortium members (institutions). **If** you're working on a dependently typed language, you can keep your data as-is and just add those edges from the author node; it is an option. What would be the other option?

- create consortium nodes _containing just the IDs_ with a `consortium` label if you want
- add consortium properties as before as part of the corresponding edges
- create any other edges there.

the point is: this is just the same! the only difference is doing it through a vertex label or the label of an edge pointing to a singleton vertex.