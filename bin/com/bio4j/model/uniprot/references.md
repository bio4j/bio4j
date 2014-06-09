## references

See

- [UniProt manual - references](http://www.uniprot.org/manual/references)
- [UniProt OWL - Citation](http://www.uniprot.org/core/Citation)

1. add type nodes for each citation type
2. move the properties to the typing rel: `p -- citedIn --> ref -- Article --> Articles` in this case `Article` would contain all article-specific properties.
3. getting all articles, books, whatever is really easy; same for adding further structure and/or types
4. at the code level, we have a base node class for `Reference`. No inheritance here: this is done through node types.
5. we can do inheritance at the relationship level just for code economy: if all citations have some common data that could be added at a base class of the reference relationship.