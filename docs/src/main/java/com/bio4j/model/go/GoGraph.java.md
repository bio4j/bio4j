
```java
package com.bio4j.model.go;

import com.bio4j.model.go.vertices.GoSlims;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.go.vertices.SubOntologies;
import com.bio4j.model.go.edges.*;
import com.bio4j.model.uniprot_go.UniprotGoGraph;
import com.ohnosequences.typedGraphs.*;
```



# Gene Ontology graph

This graph includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

## data model

Basically there are `GoTerm` nodes and relationships between them. The modeling is straightforward, and as close as possible to the data model in GO.

### GoTerms

We have a `GoTerm` vertex which contains property data present for each term. Do note though that some of these properties are represented as edges.

##### [Essential elements](http://www.geneontology.org/GO.ontology.structure.shtml#essential)

- `id` property of the `GoTerm` rel
- `name` property of the `GoTerm` rel
- `definition` property of the `GoTerm` rel

The `namespace` is represented by relationships (one type per namespace) going out of the term node. There are three of them:

- cellular component
- biological process
- molecular function

##### [Optional extras](http://www.geneontology.org/GO.ontology.structure.shtml#opt)

- `secondary_ids` We drop this. It is just legacy data with no meaning.
- `synonyms` They are split into
  + `exact`
  + `broad`
  + `narrow`
  + `related`

We drop **all** of them **but** `exact`, and add an index over it.
- `cross_ref` an array of strings, property of the `GoTerm` rel. _TODO is this connected with the corresponding DBs?_
- `comment` a standard text field.
- `subset` an array of strings. Each of them corresponds to a particular GoSlim. As with namespaces, this is modeled as relations going from each term node to a `GoSlims` node. See [GO Slim](http://www.geneontology.org/GO.slims.shtml).
- `obsolete` GoTerms marked as obsolete are **dropped**.

### GO Relationships

See [GO Ontology Relations](http://www.geneontology.org/GO.ontology.relations.shtml). They are obviously modeled as edges. We have

- is a
- part of
- has part of
- regulates
  - negatively regulates 
  - positively regulates

## examples

_TODO_


```java
public abstract class GoGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				GoGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

    protected I raw = null;

    public GoGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	public abstract UniprotGoGraph<I,RV, RVT, RE, RET> uniprotGoGraph();

	// indices
	public abstract TypedVertexIndex.Unique <
		// vertex
		GoTerm<I, RV, RVT, RE, RET>, GoTermType, 
		// property
		GoTermType.id, String, 
		// graph
		GoGraph<I, RV, RVT, RE, RET>, 
		I, RV, RVT, RE, RET
	>  
	goTermIdIndex();

	public abstract TypedVertexIndex.Unique <
		SubOntologies<I, RV, RVT, RE, RET>, SubOntologiesType,
		SubOntologiesType.name, String, 
		GoGraph<I, RV, RVT, RE, RET>, 
		I, RV, RVT, RE, RET
	>
	subontologiesNameIndex();


	// types
	// vertices
	public abstract GoSlimsType GoSlims();

	public abstract GoTermType GoTerm();

	public abstract SubOntologiesType SubOntologies();

	// edges
	public abstract PartOfType PartOf();

	public abstract HasPartOfType HasPartOf();

	public abstract NegativelyRegulatesType NegativelyRegulates();

	public abstract PositivelyRegulatesType PositivelyRegulates();

	public abstract RegulatesType Regulates();

	public abstract IsAType IsA();

	public abstract SubOntologyType SubOntology();


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class GoSlimsType
			extends
			GoVertexType<
					GoSlims<I, RV, RVT, RE, RET>,
					GoGraph<I, RV, RVT, RE, RET>.GoSlimsType
					> {

        public GoSlimsType(RVT raw) {
			super(raw);
		}

		@Override
		public GoSlimsType value() {
			return graph().GoSlims();
		}

		@Override
		public GoSlims<I, RV, RVT, RE, RET> from(RV vertex) {
			return new GoSlims<I, RV, RVT, RE, RET>(vertex, this);
		}
	}

	public final class GoTermType
			extends
			GoVertexType<
					GoTerm<I, RV, RVT, RE, RET>,
					GoGraph<I, RV, RVT, RE, RET>.GoTermType
					> {

		public final id id = new id();
		public final name name = new name();
		public final comment comment = new comment();
		public final definition definition = new definition();
        public final obsolete obsolete = new obsolete();
        public final synonym synonym = new synonym();

        public GoTermType(RVT raw) {
			super(raw);
		}

		@Override
		public GoTermType value() {
			return graph().GoTerm();
		}

		@Override
		public GoTerm<I, RV, RVT, RE, RET> from(RV vertex) {
			return new GoTerm<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, id, String> {
			public id() {
				super(GoTermType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class name
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, name, String> {
			public name() {
				super(GoTermType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class definition
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, definition, String> {
			public definition() {
				super(GoTermType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class comment
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, comment, String> {
			public comment() {
				super(GoTermType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

        public final class obsolete
                extends
                GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, obsolete, String> {
            public obsolete() {
                super(GoTermType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }

        public final class synonym
                extends
                GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, synonym, String> {
            public synonym() {
                super(GoTermType.this);
            }

            public Class<String> valueClass() {
                return String.class;
            }
        }
	}

	public final class SubOntologiesType
			extends
			GoVertexType<
					SubOntologies<I, RV, RVT, RE, RET>,
					GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType
					> {

		public final name name = new name();

        public SubOntologiesType(RVT raw) {
			super(raw);
		}

		@Override
		public SubOntologiesType value() {
			return graph().SubOntologies();
		}

		@Override
		public SubOntologies<I, RV, RVT, RE, RET> from(RV vertex) {
			return new SubOntologies<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class name
				extends
				GoVertexProperty<SubOntologies<I, RV, RVT, RE, RET>, SubOntologiesType, name, String> {
			public name() {
				super(SubOntologiesType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types

	public final class PartOfType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					PartOf<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.PartOfType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

        public PartOfType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}

		@Override
		public PartOfType value() {
			return graph().PartOf();
		}

		@Override
		public PartOf<I, RV, RVT, RE, RET> from(RE edge) {
			return new PartOf<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class HasPartOfType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					HasPartOf<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.HasPartOfType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

        public HasPartOfType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}

		@Override
		public HasPartOfType value() {
			return graph().HasPartOf();
		}

		@Override
		public HasPartOf<I, RV, RVT, RE, RET> from(RE edge) {
			return new HasPartOf<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class IsAType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					IsA<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.IsAType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

		public IsAType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}

		@Override
		public IsAType value() {
			return graph().IsA();
		}

		@Override
		public IsA<I, RV, RVT, RE, RET> from(RE edge) {
			return new IsA<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class NegativelyRegulatesType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					NegativelyRegulates<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.NegativelyRegulatesType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

        public NegativelyRegulatesType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}

		@Override
		public NegativelyRegulatesType value() {
			return graph().NegativelyRegulates();
		}

		@Override
		public NegativelyRegulates<I, RV, RVT, RE, RET> from(RE edge) {
			return new NegativelyRegulates<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class PositivelyRegulatesType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					PositivelyRegulates<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.PositivelyRegulatesType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

        public PositivelyRegulatesType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}

		@Override
		public PositivelyRegulatesType value() {
			return graph().PositivelyRegulates();
		}

		@Override
		public PositivelyRegulates<I, RV, RVT, RE, RET> from(RE edge) {
			return new PositivelyRegulates<I, RV, RVT, RE, RET>(edge, this);
		}
	}


	public final class RegulatesType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					Regulates<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.RegulatesType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

        public RegulatesType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}

		@Override
		public RegulatesType value() {
			return graph().Regulates();
		}

		@Override
		public Regulates<I, RV, RVT, RE, RET> from(RE edge) {
			return new Regulates<I, RV, RVT, RE, RET>(edge, this);
		}
	}


	public final class SubOntologyType
			extends
			GoEdgeType<
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType,
					SubOntology<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.SubOntologyType,
					SubOntologies<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.SubOntologiesType
					>
			implements
			TypedEdge.Type.ManyToOne {

		public SubOntologyType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.SubOntologies());
		}

		@Override
		public SubOntologyType value() {
			return graph().SubOntology();
		}

		@Override
		public SubOntology<I, RV, RVT, RE, RET> from(RE edge) {
			return new SubOntology<I, RV, RVT, RE, RET>(edge, this);
		}
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class GoVertexProperty<
			V extends GoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<V, VT>,
			P extends GoVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, GoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected GoVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class GoVertex<
			V extends GoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, GoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected GoVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public GoGraph<I, RV, RVT, RE, RET> graph() {
			return type().graph();
		}

		@Override
		public RV raw() {
			return this.vertex;
		}

		@Override
		public VT type() {
			return type;
		}
	}

	abstract class GoVertexType<
			V extends GoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, GoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected GoVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final GoGraph<I, RV, RVT, RE, RET> graph() {
			return GoGraph.this;
		}
	}

	public abstract static class GoEdge<
			S extends GoVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<S, ST>,
			E extends GoEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends GoGraph<I, RV, RVT, RE, RET>.GoEdgeType<S, ST, E, ET, T, TT>,
			T extends GoVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, GoGraph<I, RV, RVT, RE, RET>,
					E, ET, GoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, GoGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected GoEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public GoGraph<I, RV, RVT, RE, RET> graph() {
			return type().graph();
		}

		@Override
		public RE raw() {
			return this.edge;
		}

		@Override
		public ET type() {
			return type;
		}
	}

	abstract class GoEdgeType<
			S extends GoVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<S, ST>,
			E extends GoEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends GoGraph<I, RV, RVT, RE, RET>.GoEdgeType<S, ST, E, ET, T, TT>,
			T extends GoVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends GoGraph<I, RV, RVT, RE, RET>.GoVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, GoGraph<I, RV, RVT, RE, RET>,
					E, ET, GoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, GoGraph<I, RV, RVT, RE, RET>
					> {

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected GoEdgeType(ST srcT, RET raw, TT tgtT) {

			this.raw = raw;
			this.srcT = srcT;
			this.tgtT = tgtT;
		}

		@Override
		public final ST sourceType() {
			return srcT;
		}

		@Override
		public final TT targetType() {
			return tgtT;
		}

		@Override
		public final RET raw() {
			return raw;
		}

		@Override
		public final GoGraph<I, RV, RVT, RE, RET> graph() {
			return GoGraph.this;
		}
	}
}

```


------

### Index

+ src
  + main
    + java
      + com
        + bio4j
          + model
            + enums
            + enzymedb
              + [EnzymeDBGraph.java][main\java\com\bio4j\model\enzymedb\EnzymeDBGraph.java]
              + programs
                + [ImportEnzymeDB.java][main\java\com\bio4j\model\enzymedb\programs\ImportEnzymeDB.java]
              + vertices
                + [Enzyme.java][main\java\com\bio4j\model\enzymedb\vertices\Enzyme.java]
            + geninfo
              + [GenInfoGraph.java][main\java\com\bio4j\model\geninfo\GenInfoGraph.java]
              + vertices
                + [GenInfo.java][main\java\com\bio4j\model\geninfo\vertices\GenInfo.java]
            + go
              + edges
                + goSlims
                  + [GoSlim.java][main\java\com\bio4j\model\go\edges\goSlims\GoSlim.java]
                  + [PlantSlim.java][main\java\com\bio4j\model\go\edges\goSlims\PlantSlim.java]
                + [HasPartOf.java][main\java\com\bio4j\model\go\edges\HasPartOf.java]
                + [IsA.java][main\java\com\bio4j\model\go\edges\IsA.java]
                + [NegativelyRegulates.java][main\java\com\bio4j\model\go\edges\NegativelyRegulates.java]
                + [PartOf.java][main\java\com\bio4j\model\go\edges\PartOf.java]
                + [PositivelyRegulates.java][main\java\com\bio4j\model\go\edges\PositivelyRegulates.java]
                + [Regulates.java][main\java\com\bio4j\model\go\edges\Regulates.java]
                + [SubOntology.java][main\java\com\bio4j\model\go\edges\SubOntology.java]
              + [GoGraph.java][main\java\com\bio4j\model\go\GoGraph.java]
              + programs
                + [ImportGO.java][main\java\com\bio4j\model\go\programs\ImportGO.java]
              + vertices
                + [GoSlims.java][main\java\com\bio4j\model\go\vertices\GoSlims.java]
                + [GoTerm.java][main\java\com\bio4j\model\go\vertices\GoTerm.java]
                + [SubOntologies.java][main\java\com\bio4j\model\go\vertices\SubOntologies.java]
            + ncbiTaxonomy
              + edges
                + [NCBITaxonParent.java][main\java\com\bio4j\model\ncbiTaxonomy\edges\NCBITaxonParent.java]
              + [NCBITaxonomyGraph.java][main\java\com\bio4j\model\ncbiTaxonomy\NCBITaxonomyGraph.java]
              + programs
                + [ImportNCBITaxonomy.java][main\java\com\bio4j\model\ncbiTaxonomy\programs\ImportNCBITaxonomy.java]
              + vertices
                + [NCBITaxon.java][main\java\com\bio4j\model\ncbiTaxonomy\vertices\NCBITaxon.java]
            + ncbiTaxonomy_geninfo
              + edges
                + [GenInfoNCBITaxon.java][main\java\com\bio4j\model\ncbiTaxonomy_geninfo\edges\GenInfoNCBITaxon.java]
              + [NCBITaxonomyGenInfoGraph.java][main\java\com\bio4j\model\ncbiTaxonomy_geninfo\NCBITaxonomyGenInfoGraph.java]
            + uniprot
              + edges
                + [ArticleJournal.java][main\java\com\bio4j\model\uniprot\edges\ArticleJournal.java]
                + [ArticlePubmed.java][main\java\com\bio4j\model\uniprot\edges\ArticlePubmed.java]
                + [BookCity.java][main\java\com\bio4j\model\uniprot\edges\BookCity.java]
                + [BookEditor.java][main\java\com\bio4j\model\uniprot\edges\BookEditor.java]
                + [BookPublisher.java][main\java\com\bio4j\model\uniprot\edges\BookPublisher.java]
                + [InstituteCountry.java][main\java\com\bio4j\model\uniprot\edges\InstituteCountry.java]
                + [IsoformEventGenerator.java][main\java\com\bio4j\model\uniprot\edges\IsoformEventGenerator.java]
                + [IsoformProteinInteraction.java][main\java\com\bio4j\model\uniprot\edges\IsoformProteinInteraction.java]
                + [OnlineArticleOnlineJournal.java][main\java\com\bio4j\model\uniprot\edges\OnlineArticleOnlineJournal.java]
                + [OrganismTaxon.java][main\java\com\bio4j\model\uniprot\edges\OrganismTaxon.java]
                + [ProteinComment.java][main\java\com\bio4j\model\uniprot\edges\ProteinComment.java]
                + [ProteinDataset.java][main\java\com\bio4j\model\uniprot\edges\ProteinDataset.java]
                + [ProteinDisease.java][main\java\com\bio4j\model\uniprot\edges\ProteinDisease.java]
                + [ProteinEMBL.java][main\java\com\bio4j\model\uniprot\edges\ProteinEMBL.java]
                + [ProteinEnsembl.java][main\java\com\bio4j\model\uniprot\edges\ProteinEnsembl.java]
                + [ProteinFeature.java][main\java\com\bio4j\model\uniprot\edges\ProteinFeature.java]
                + [ProteinGeneLocation.java][main\java\com\bio4j\model\uniprot\edges\ProteinGeneLocation.java]
                + [ProteinInterpro.java][main\java\com\bio4j\model\uniprot\edges\ProteinInterpro.java]
                + [ProteinIsoformInteraction.java][main\java\com\bio4j\model\uniprot\edges\ProteinIsoformInteraction.java]
                + [ProteinKegg.java][main\java\com\bio4j\model\uniprot\edges\ProteinKegg.java]
                + [ProteinKeyword.java][main\java\com\bio4j\model\uniprot\edges\ProteinKeyword.java]
                + [ProteinOrganism.java][main\java\com\bio4j\model\uniprot\edges\ProteinOrganism.java]
                + [ProteinPfam.java][main\java\com\bio4j\model\uniprot\edges\ProteinPfam.java]
                + [ProteinPIR.java][main\java\com\bio4j\model\uniprot\edges\ProteinPIR.java]
                + [ProteinProteinInteraction.java][main\java\com\bio4j\model\uniprot\edges\ProteinProteinInteraction.java]
                + [ProteinReactomeTerm.java][main\java\com\bio4j\model\uniprot\edges\ProteinReactomeTerm.java]
                + [ProteinReference.java][main\java\com\bio4j\model\uniprot\edges\ProteinReference.java]
                + [ProteinRefSeq.java][main\java\com\bio4j\model\uniprot\edges\ProteinRefSeq.java]
                + [ProteinSequenceCaution.java][main\java\com\bio4j\model\uniprot\edges\ProteinSequenceCaution.java]
                + [ProteinSubcellularLocation.java][main\java\com\bio4j\model\uniprot\edges\ProteinSubcellularLocation.java]
                + [ProteinUniGene.java][main\java\com\bio4j\model\uniprot\edges\ProteinUniGene.java]
                + [ReferenceArticle.java][main\java\com\bio4j\model\uniprot\edges\ReferenceArticle.java]
                + [ReferenceAuthorConsortium.java][main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorConsortium.java]
                + [ReferenceAuthorPerson.java][main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorPerson.java]
                + [ReferenceBook.java][main\java\com\bio4j\model\uniprot\edges\ReferenceBook.java]
                + [ReferenceOnlineArticle.java][main\java\com\bio4j\model\uniprot\edges\ReferenceOnlineArticle.java]
                + [ReferencePatent.java][main\java\com\bio4j\model\uniprot\edges\ReferencePatent.java]
                + [ReferenceSubmission.java][main\java\com\bio4j\model\uniprot\edges\ReferenceSubmission.java]
                + [ReferenceThesis.java][main\java\com\bio4j\model\uniprot\edges\ReferenceThesis.java]
                + [ReferenceUnpublishedObservation.java][main\java\com\bio4j\model\uniprot\edges\ReferenceUnpublishedObservation.java]
                + [SubcellularLocationParent.java][main\java\com\bio4j\model\uniprot\edges\SubcellularLocationParent.java]
                + [SubmissionDB.java][main\java\com\bio4j\model\uniprot\edges\SubmissionDB.java]
                + [TaxonParent.java][main\java\com\bio4j\model\uniprot\edges\TaxonParent.java]
                + [ThesisInstitute.java][main\java\com\bio4j\model\uniprot\edges\ThesisInstitute.java]
              + programs
                + [ImportIsoformSequences.java][main\java\com\bio4j\model\uniprot\programs\ImportIsoformSequences.java]
                + [ImportProteinInteractions.java][main\java\com\bio4j\model\uniprot\programs\ImportProteinInteractions.java]
                + [ImportUniprot.java][main\java\com\bio4j\model\uniprot\programs\ImportUniprot.java]
              + [UniprotGraph.java][main\java\com\bio4j\model\uniprot\UniprotGraph.java]
              + vertices
                + [AlternativeProduct.java][main\java\com\bio4j\model\uniprot\vertices\AlternativeProduct.java]
                + [Article.java][main\java\com\bio4j\model\uniprot\vertices\Article.java]
                + [Book.java][main\java\com\bio4j\model\uniprot\vertices\Book.java]
                + [City.java][main\java\com\bio4j\model\uniprot\vertices\City.java]
                + [CommentType.java][main\java\com\bio4j\model\uniprot\vertices\CommentType.java]
                + [Consortium.java][main\java\com\bio4j\model\uniprot\vertices\Consortium.java]
                + [Country.java][main\java\com\bio4j\model\uniprot\vertices\Country.java]
                + [Dataset.java][main\java\com\bio4j\model\uniprot\vertices\Dataset.java]
                + [DB.java][main\java\com\bio4j\model\uniprot\vertices\DB.java]
                + [Disease.java][main\java\com\bio4j\model\uniprot\vertices\Disease.java]
                + [EMBL.java][main\java\com\bio4j\model\uniprot\vertices\EMBL.java]
                + [Ensembl.java][main\java\com\bio4j\model\uniprot\vertices\Ensembl.java]
                + [FeatureType.java][main\java\com\bio4j\model\uniprot\vertices\FeatureType.java]
                + [GeneLocation.java][main\java\com\bio4j\model\uniprot\vertices\GeneLocation.java]
                + [Institute.java][main\java\com\bio4j\model\uniprot\vertices\Institute.java]
                + [Interpro.java][main\java\com\bio4j\model\uniprot\vertices\Interpro.java]
                + [Isoform.java][main\java\com\bio4j\model\uniprot\vertices\Isoform.java]
                + [Journal.java][main\java\com\bio4j\model\uniprot\vertices\Journal.java]
                + [Kegg.java][main\java\com\bio4j\model\uniprot\vertices\Kegg.java]
                + [Keyword.java][main\java\com\bio4j\model\uniprot\vertices\Keyword.java]
                + [OnlineArticle.java][main\java\com\bio4j\model\uniprot\vertices\OnlineArticle.java]
                + [OnlineJournal.java][main\java\com\bio4j\model\uniprot\vertices\OnlineJournal.java]
                + [Organism.java][main\java\com\bio4j\model\uniprot\vertices\Organism.java]
                + [Patent.java][main\java\com\bio4j\model\uniprot\vertices\Patent.java]
                + [Person.java][main\java\com\bio4j\model\uniprot\vertices\Person.java]
                + [Pfam.java][main\java\com\bio4j\model\uniprot\vertices\Pfam.java]
                + [PIR.java][main\java\com\bio4j\model\uniprot\vertices\PIR.java]
                + [Protein.java][main\java\com\bio4j\model\uniprot\vertices\Protein.java]
                + [Publisher.java][main\java\com\bio4j\model\uniprot\vertices\Publisher.java]
                + [Pubmed.java][main\java\com\bio4j\model\uniprot\vertices\Pubmed.java]
                + [ReactomeTerm.java][main\java\com\bio4j\model\uniprot\vertices\ReactomeTerm.java]
                + [Reference.java][main\java\com\bio4j\model\uniprot\vertices\Reference.java]
                + [RefSeq.java][main\java\com\bio4j\model\uniprot\vertices\RefSeq.java]
                + [SequenceCaution.java][main\java\com\bio4j\model\uniprot\vertices\SequenceCaution.java]
                + [SubcellularLocation.java][main\java\com\bio4j\model\uniprot\vertices\SubcellularLocation.java]
                + [Submission.java][main\java\com\bio4j\model\uniprot\vertices\Submission.java]
                + [Taxon.java][main\java\com\bio4j\model\uniprot\vertices\Taxon.java]
                + [Thesis.java][main\java\com\bio4j\model\uniprot\vertices\Thesis.java]
                + [UniGene.java][main\java\com\bio4j\model\uniprot\vertices\UniGene.java]
                + [UnpublishedObservation.java][main\java\com\bio4j\model\uniprot\vertices\UnpublishedObservation.java]
            + uniprot_enzymedb
              + edges
                + [EnzymaticActivity.java][main\java\com\bio4j\model\uniprot_enzymedb\edges\EnzymaticActivity.java]
              + programs
                + [ImportUniprotEnzymeDB.java][main\java\com\bio4j\model\uniprot_enzymedb\programs\ImportUniprotEnzymeDB.java]
              + [UniprotEnzymeDBGraph.java][main\java\com\bio4j\model\uniprot_enzymedb\UniprotEnzymeDBGraph.java]
            + uniprot_go
              + edges
                + [GoAnnotation.java][main\java\com\bio4j\model\uniprot_go\edges\GoAnnotation.java]
              + programs
                + [ImportUniprotGo.java][main\java\com\bio4j\model\uniprot_go\programs\ImportUniprotGo.java]
              + [UniprotGoGraph.java][main\java\com\bio4j\model\uniprot_go\UniprotGoGraph.java]
            + uniprot_ncbiTaxonomy
              + edges
                + [ProteinNCBITaxon.java][main\java\com\bio4j\model\uniprot_ncbiTaxonomy\edges\ProteinNCBITaxon.java]
              + [UniprotNCBITaxonomyGraph.java][main\java\com\bio4j\model\uniprot_ncbiTaxonomy\UniprotNCBITaxonomyGraph.java]
            + uniprot_uniref
              + edges
                + [UniRef100Member.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Member.java]
                + [UniRef100Representant.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Representant.java]
                + [UniRef50Member.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Member.java]
                + [UniRef50Representant.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Representant.java]
                + [UniRef90Member.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Member.java]
                + [UniRef90Representant.java][main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Representant.java]
              + programs
                + [ImportUniprotUniRef.java][main\java\com\bio4j\model\uniprot_uniref\programs\ImportUniprotUniRef.java]
              + [UniprotUniRefGraph.java][main\java\com\bio4j\model\uniprot_uniref\UniprotUniRefGraph.java]
            + uniref
              + programs
                + [ImportUniRef.java][main\java\com\bio4j\model\uniref\programs\ImportUniRef.java]
              + [UniRefGraph.java][main\java\com\bio4j\model\uniref\UniRefGraph.java]
              + vertices
                + [UniRef100Cluster.java][main\java\com\bio4j\model\uniref\vertices\UniRef100Cluster.java]
                + [UniRef50Cluster.java][main\java\com\bio4j\model\uniref\vertices\UniRef50Cluster.java]
                + [UniRef90Cluster.java][main\java\com\bio4j\model\uniref\vertices\UniRef90Cluster.java]
    + scala
  + test
    + java
    + scala

[main\java\com\bio4j\model\enzymedb\EnzymeDBGraph.java]: ..\enzymedb\EnzymeDBGraph.java.md
[main\java\com\bio4j\model\enzymedb\programs\ImportEnzymeDB.java]: ..\enzymedb\programs\ImportEnzymeDB.java.md
[main\java\com\bio4j\model\enzymedb\vertices\Enzyme.java]: ..\enzymedb\vertices\Enzyme.java.md
[main\java\com\bio4j\model\geninfo\GenInfoGraph.java]: ..\geninfo\GenInfoGraph.java.md
[main\java\com\bio4j\model\geninfo\vertices\GenInfo.java]: ..\geninfo\vertices\GenInfo.java.md
[main\java\com\bio4j\model\go\edges\goSlims\GoSlim.java]: edges\goSlims\GoSlim.java.md
[main\java\com\bio4j\model\go\edges\goSlims\PlantSlim.java]: edges\goSlims\PlantSlim.java.md
[main\java\com\bio4j\model\go\edges\HasPartOf.java]: edges\HasPartOf.java.md
[main\java\com\bio4j\model\go\edges\IsA.java]: edges\IsA.java.md
[main\java\com\bio4j\model\go\edges\NegativelyRegulates.java]: edges\NegativelyRegulates.java.md
[main\java\com\bio4j\model\go\edges\PartOf.java]: edges\PartOf.java.md
[main\java\com\bio4j\model\go\edges\PositivelyRegulates.java]: edges\PositivelyRegulates.java.md
[main\java\com\bio4j\model\go\edges\Regulates.java]: edges\Regulates.java.md
[main\java\com\bio4j\model\go\edges\SubOntology.java]: edges\SubOntology.java.md
[main\java\com\bio4j\model\go\GoGraph.java]: GoGraph.java.md
[main\java\com\bio4j\model\go\programs\ImportGO.java]: programs\ImportGO.java.md
[main\java\com\bio4j\model\go\vertices\GoSlims.java]: vertices\GoSlims.java.md
[main\java\com\bio4j\model\go\vertices\GoTerm.java]: vertices\GoTerm.java.md
[main\java\com\bio4j\model\go\vertices\SubOntologies.java]: vertices\SubOntologies.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\edges\NCBITaxonParent.java]: ..\ncbiTaxonomy\edges\NCBITaxonParent.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\NCBITaxonomyGraph.java]: ..\ncbiTaxonomy\NCBITaxonomyGraph.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\programs\ImportNCBITaxonomy.java]: ..\ncbiTaxonomy\programs\ImportNCBITaxonomy.java.md
[main\java\com\bio4j\model\ncbiTaxonomy\vertices\NCBITaxon.java]: ..\ncbiTaxonomy\vertices\NCBITaxon.java.md
[main\java\com\bio4j\model\ncbiTaxonomy_geninfo\edges\GenInfoNCBITaxon.java]: ..\ncbiTaxonomy_geninfo\edges\GenInfoNCBITaxon.java.md
[main\java\com\bio4j\model\ncbiTaxonomy_geninfo\NCBITaxonomyGenInfoGraph.java]: ..\ncbiTaxonomy_geninfo\NCBITaxonomyGenInfoGraph.java.md
[main\java\com\bio4j\model\uniprot\edges\ArticleJournal.java]: ..\uniprot\edges\ArticleJournal.java.md
[main\java\com\bio4j\model\uniprot\edges\ArticlePubmed.java]: ..\uniprot\edges\ArticlePubmed.java.md
[main\java\com\bio4j\model\uniprot\edges\BookCity.java]: ..\uniprot\edges\BookCity.java.md
[main\java\com\bio4j\model\uniprot\edges\BookEditor.java]: ..\uniprot\edges\BookEditor.java.md
[main\java\com\bio4j\model\uniprot\edges\BookPublisher.java]: ..\uniprot\edges\BookPublisher.java.md
[main\java\com\bio4j\model\uniprot\edges\InstituteCountry.java]: ..\uniprot\edges\InstituteCountry.java.md
[main\java\com\bio4j\model\uniprot\edges\IsoformEventGenerator.java]: ..\uniprot\edges\IsoformEventGenerator.java.md
[main\java\com\bio4j\model\uniprot\edges\IsoformProteinInteraction.java]: ..\uniprot\edges\IsoformProteinInteraction.java.md
[main\java\com\bio4j\model\uniprot\edges\OnlineArticleOnlineJournal.java]: ..\uniprot\edges\OnlineArticleOnlineJournal.java.md
[main\java\com\bio4j\model\uniprot\edges\OrganismTaxon.java]: ..\uniprot\edges\OrganismTaxon.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinComment.java]: ..\uniprot\edges\ProteinComment.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinDataset.java]: ..\uniprot\edges\ProteinDataset.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinDisease.java]: ..\uniprot\edges\ProteinDisease.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinEMBL.java]: ..\uniprot\edges\ProteinEMBL.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinEnsembl.java]: ..\uniprot\edges\ProteinEnsembl.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinFeature.java]: ..\uniprot\edges\ProteinFeature.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinGeneLocation.java]: ..\uniprot\edges\ProteinGeneLocation.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinInterpro.java]: ..\uniprot\edges\ProteinInterpro.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinIsoformInteraction.java]: ..\uniprot\edges\ProteinIsoformInteraction.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinKegg.java]: ..\uniprot\edges\ProteinKegg.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinKeyword.java]: ..\uniprot\edges\ProteinKeyword.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinOrganism.java]: ..\uniprot\edges\ProteinOrganism.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinPfam.java]: ..\uniprot\edges\ProteinPfam.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinPIR.java]: ..\uniprot\edges\ProteinPIR.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinProteinInteraction.java]: ..\uniprot\edges\ProteinProteinInteraction.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinReactomeTerm.java]: ..\uniprot\edges\ProteinReactomeTerm.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinReference.java]: ..\uniprot\edges\ProteinReference.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinRefSeq.java]: ..\uniprot\edges\ProteinRefSeq.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinSequenceCaution.java]: ..\uniprot\edges\ProteinSequenceCaution.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinSubcellularLocation.java]: ..\uniprot\edges\ProteinSubcellularLocation.java.md
[main\java\com\bio4j\model\uniprot\edges\ProteinUniGene.java]: ..\uniprot\edges\ProteinUniGene.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceArticle.java]: ..\uniprot\edges\ReferenceArticle.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorConsortium.java]: ..\uniprot\edges\ReferenceAuthorConsortium.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceAuthorPerson.java]: ..\uniprot\edges\ReferenceAuthorPerson.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceBook.java]: ..\uniprot\edges\ReferenceBook.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceOnlineArticle.java]: ..\uniprot\edges\ReferenceOnlineArticle.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferencePatent.java]: ..\uniprot\edges\ReferencePatent.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceSubmission.java]: ..\uniprot\edges\ReferenceSubmission.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceThesis.java]: ..\uniprot\edges\ReferenceThesis.java.md
[main\java\com\bio4j\model\uniprot\edges\ReferenceUnpublishedObservation.java]: ..\uniprot\edges\ReferenceUnpublishedObservation.java.md
[main\java\com\bio4j\model\uniprot\edges\SubcellularLocationParent.java]: ..\uniprot\edges\SubcellularLocationParent.java.md
[main\java\com\bio4j\model\uniprot\edges\SubmissionDB.java]: ..\uniprot\edges\SubmissionDB.java.md
[main\java\com\bio4j\model\uniprot\edges\TaxonParent.java]: ..\uniprot\edges\TaxonParent.java.md
[main\java\com\bio4j\model\uniprot\edges\ThesisInstitute.java]: ..\uniprot\edges\ThesisInstitute.java.md
[main\java\com\bio4j\model\uniprot\programs\ImportIsoformSequences.java]: ..\uniprot\programs\ImportIsoformSequences.java.md
[main\java\com\bio4j\model\uniprot\programs\ImportProteinInteractions.java]: ..\uniprot\programs\ImportProteinInteractions.java.md
[main\java\com\bio4j\model\uniprot\programs\ImportUniprot.java]: ..\uniprot\programs\ImportUniprot.java.md
[main\java\com\bio4j\model\uniprot\UniprotGraph.java]: ..\uniprot\UniprotGraph.java.md
[main\java\com\bio4j\model\uniprot\vertices\AlternativeProduct.java]: ..\uniprot\vertices\AlternativeProduct.java.md
[main\java\com\bio4j\model\uniprot\vertices\Article.java]: ..\uniprot\vertices\Article.java.md
[main\java\com\bio4j\model\uniprot\vertices\Book.java]: ..\uniprot\vertices\Book.java.md
[main\java\com\bio4j\model\uniprot\vertices\City.java]: ..\uniprot\vertices\City.java.md
[main\java\com\bio4j\model\uniprot\vertices\CommentType.java]: ..\uniprot\vertices\CommentType.java.md
[main\java\com\bio4j\model\uniprot\vertices\Consortium.java]: ..\uniprot\vertices\Consortium.java.md
[main\java\com\bio4j\model\uniprot\vertices\Country.java]: ..\uniprot\vertices\Country.java.md
[main\java\com\bio4j\model\uniprot\vertices\Dataset.java]: ..\uniprot\vertices\Dataset.java.md
[main\java\com\bio4j\model\uniprot\vertices\DB.java]: ..\uniprot\vertices\DB.java.md
[main\java\com\bio4j\model\uniprot\vertices\Disease.java]: ..\uniprot\vertices\Disease.java.md
[main\java\com\bio4j\model\uniprot\vertices\EMBL.java]: ..\uniprot\vertices\EMBL.java.md
[main\java\com\bio4j\model\uniprot\vertices\Ensembl.java]: ..\uniprot\vertices\Ensembl.java.md
[main\java\com\bio4j\model\uniprot\vertices\FeatureType.java]: ..\uniprot\vertices\FeatureType.java.md
[main\java\com\bio4j\model\uniprot\vertices\GeneLocation.java]: ..\uniprot\vertices\GeneLocation.java.md
[main\java\com\bio4j\model\uniprot\vertices\Institute.java]: ..\uniprot\vertices\Institute.java.md
[main\java\com\bio4j\model\uniprot\vertices\Interpro.java]: ..\uniprot\vertices\Interpro.java.md
[main\java\com\bio4j\model\uniprot\vertices\Isoform.java]: ..\uniprot\vertices\Isoform.java.md
[main\java\com\bio4j\model\uniprot\vertices\Journal.java]: ..\uniprot\vertices\Journal.java.md
[main\java\com\bio4j\model\uniprot\vertices\Kegg.java]: ..\uniprot\vertices\Kegg.java.md
[main\java\com\bio4j\model\uniprot\vertices\Keyword.java]: ..\uniprot\vertices\Keyword.java.md
[main\java\com\bio4j\model\uniprot\vertices\OnlineArticle.java]: ..\uniprot\vertices\OnlineArticle.java.md
[main\java\com\bio4j\model\uniprot\vertices\OnlineJournal.java]: ..\uniprot\vertices\OnlineJournal.java.md
[main\java\com\bio4j\model\uniprot\vertices\Organism.java]: ..\uniprot\vertices\Organism.java.md
[main\java\com\bio4j\model\uniprot\vertices\Patent.java]: ..\uniprot\vertices\Patent.java.md
[main\java\com\bio4j\model\uniprot\vertices\Person.java]: ..\uniprot\vertices\Person.java.md
[main\java\com\bio4j\model\uniprot\vertices\Pfam.java]: ..\uniprot\vertices\Pfam.java.md
[main\java\com\bio4j\model\uniprot\vertices\PIR.java]: ..\uniprot\vertices\PIR.java.md
[main\java\com\bio4j\model\uniprot\vertices\Protein.java]: ..\uniprot\vertices\Protein.java.md
[main\java\com\bio4j\model\uniprot\vertices\Publisher.java]: ..\uniprot\vertices\Publisher.java.md
[main\java\com\bio4j\model\uniprot\vertices\Pubmed.java]: ..\uniprot\vertices\Pubmed.java.md
[main\java\com\bio4j\model\uniprot\vertices\ReactomeTerm.java]: ..\uniprot\vertices\ReactomeTerm.java.md
[main\java\com\bio4j\model\uniprot\vertices\Reference.java]: ..\uniprot\vertices\Reference.java.md
[main\java\com\bio4j\model\uniprot\vertices\RefSeq.java]: ..\uniprot\vertices\RefSeq.java.md
[main\java\com\bio4j\model\uniprot\vertices\SequenceCaution.java]: ..\uniprot\vertices\SequenceCaution.java.md
[main\java\com\bio4j\model\uniprot\vertices\SubcellularLocation.java]: ..\uniprot\vertices\SubcellularLocation.java.md
[main\java\com\bio4j\model\uniprot\vertices\Submission.java]: ..\uniprot\vertices\Submission.java.md
[main\java\com\bio4j\model\uniprot\vertices\Taxon.java]: ..\uniprot\vertices\Taxon.java.md
[main\java\com\bio4j\model\uniprot\vertices\Thesis.java]: ..\uniprot\vertices\Thesis.java.md
[main\java\com\bio4j\model\uniprot\vertices\UniGene.java]: ..\uniprot\vertices\UniGene.java.md
[main\java\com\bio4j\model\uniprot\vertices\UnpublishedObservation.java]: ..\uniprot\vertices\UnpublishedObservation.java.md
[main\java\com\bio4j\model\uniprot_enzymedb\edges\EnzymaticActivity.java]: ..\uniprot_enzymedb\edges\EnzymaticActivity.java.md
[main\java\com\bio4j\model\uniprot_enzymedb\programs\ImportUniprotEnzymeDB.java]: ..\uniprot_enzymedb\programs\ImportUniprotEnzymeDB.java.md
[main\java\com\bio4j\model\uniprot_enzymedb\UniprotEnzymeDBGraph.java]: ..\uniprot_enzymedb\UniprotEnzymeDBGraph.java.md
[main\java\com\bio4j\model\uniprot_go\edges\GoAnnotation.java]: ..\uniprot_go\edges\GoAnnotation.java.md
[main\java\com\bio4j\model\uniprot_go\programs\ImportUniprotGo.java]: ..\uniprot_go\programs\ImportUniprotGo.java.md
[main\java\com\bio4j\model\uniprot_go\UniprotGoGraph.java]: ..\uniprot_go\UniprotGoGraph.java.md
[main\java\com\bio4j\model\uniprot_ncbiTaxonomy\edges\ProteinNCBITaxon.java]: ..\uniprot_ncbiTaxonomy\edges\ProteinNCBITaxon.java.md
[main\java\com\bio4j\model\uniprot_ncbiTaxonomy\UniprotNCBITaxonomyGraph.java]: ..\uniprot_ncbiTaxonomy\UniprotNCBITaxonomyGraph.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Member.java]: ..\uniprot_uniref\edges\UniRef100Member.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef100Representant.java]: ..\uniprot_uniref\edges\UniRef100Representant.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Member.java]: ..\uniprot_uniref\edges\UniRef50Member.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef50Representant.java]: ..\uniprot_uniref\edges\UniRef50Representant.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Member.java]: ..\uniprot_uniref\edges\UniRef90Member.java.md
[main\java\com\bio4j\model\uniprot_uniref\edges\UniRef90Representant.java]: ..\uniprot_uniref\edges\UniRef90Representant.java.md
[main\java\com\bio4j\model\uniprot_uniref\programs\ImportUniprotUniRef.java]: ..\uniprot_uniref\programs\ImportUniprotUniRef.java.md
[main\java\com\bio4j\model\uniprot_uniref\UniprotUniRefGraph.java]: ..\uniprot_uniref\UniprotUniRefGraph.java.md
[main\java\com\bio4j\model\uniref\programs\ImportUniRef.java]: ..\uniref\programs\ImportUniRef.java.md
[main\java\com\bio4j\model\uniref\UniRefGraph.java]: ..\uniref\UniRefGraph.java.md
[main\java\com\bio4j\model\uniref\vertices\UniRef100Cluster.java]: ..\uniref\vertices\UniRef100Cluster.java.md
[main\java\com\bio4j\model\uniref\vertices\UniRef50Cluster.java]: ..\uniref\vertices\UniRef50Cluster.java.md
[main\java\com\bio4j\model\uniref\vertices\UniRef90Cluster.java]: ..\uniref\vertices\UniRef90Cluster.java.md