## Examples of Bio4j usage

* A set of sample tools using Bio4j in [bio4j/examples](https://github.com/bio4j/examples)
* A group of sample files showing how to do basic queries with Bio4j:
  - [Bio4j Cypher cheat sheet](bio4j-cypher-cheat-sheet.md)
  - [Bio4j Gremlin cheat sheet](bio4j-gremlin-cheat-sheet.md)
  - Basic node retrieving:
    + [RetrieveProtein](https://github.com/bio4j/Bio4j/blob/master/src/main/java/com/era7/bioinfo/bio4j/neo4j/codesamples/RetrieveProteinSample.java): Gets a protein by its accession and prints out some basic information about it.
    + [GetEnzymeData](https://github.com/bio4j/Bio4j/blob/master/src/main/java/com/era7/bioinfo/bio4j/neo4j/codesamples/GetEnzymeData.java): Gets basic data for the Enzyme ID provided.
    + [GetGOAnnotationsForOrganism](https://github.com/bio4j/Bio4j/blob/master/src/main/java/com/era7/bioinfo/bio4j/neo4j/codesamples/GetGOAnnotationsForOrganism.java): Retrieves Gene Ontology (GO) annotations associated to the organism provided.
  - Real use cases:
    + [GetProteinsWithInterpro](https://github.com/bio4j/Bio4j/blob/master/src/main/java/com/era7/bioinfo/bio4j/neo4j/codesamples/GetProteinsWithInterpro.java): Retrieves proteins with the interpro motif provided.
