## Retrieving basic info from nodes

Get protein by its accession number and return its full name

```	
gremlin> g.idx('protein_accession_index')[['protein_accession_index':'P12345']].protein_full_name
==> Aspartate aminotransferase, mitochondrial
```

Get Keyword by its ID:

```
gremlin> g.idx('keyword_id_index')[['keyword_id_index':'KW-0181']].keyword_name
==> Complete proteome
```

## Basic traversals

Get proteins (accessions) associated to an interpro motif (limited to 10 results)

```	
gremlin> g.idx('interpro_id_index')[['interpro_id_index':'IPR023306']].inE('PROTEIN_INTERPRO').outV.protein_accession[0..9]
==> E2GK26
==> G3PMS4
==> G3Q865
==> G3PIL8
==> G3NNA4
==> G3PIN3
==> F7CCX7
==> B1H2M4
==> Q6VYM4
==> E3V034
gremlin> 
```

