package com.bio4j.model.go;

import com.bio4j.model.go.nodes.GoSlims;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.go.nodes.SubOntologies;
import com.bio4j.model.go.relationships.*;
import com.ohnosequences.typedGraphs.*;

/*

# Gene Ontology graph

This graph includes all of the data from [Gene Ontology](http://www.geneontology.org). A good place to start reading about it is

- [Gene Ontology docs - Ontology Structure](http://www.geneontology.org/GO.ontology.structure.shtml)

## data model

Basically there are `GoTerm` nodes and relationships between them. The modeling is straightforward, and as close as possible to the data model in GO.

### GoTerms

We have a `GoTerm` node which contains property data present for each term. Do note though that some of these properties are represented as edges.

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
*/
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

		protected GoSlimsType(RVT raw) {
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

		protected GoTermType(RVT raw) {
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
				super(GoTermType.this.graph().GoTerm());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class name
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, name, String> {
			public name() {
				super(GoTermType.this.graph().GoTerm());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class definition
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, definition, String> {
			public definition() {
				super(GoTermType.this.graph().GoTerm());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class comment
				extends
				GoVertexProperty<GoTerm<I, RV, RVT, RE, RET>, GoTermType, comment, String> {
			public comment() {
				super(GoTermType.this.graph().GoTerm());
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

		protected SubOntologiesType(RVT raw) {
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

		protected PartOfType(RET raw) {
			super(GoGraph.this.GoTerm(), raw, GoGraph.this.GoTerm());
		}
		// protected PartOfType(RET raw) { super( this.graph().GoTerm(), raw, this.graph().GoTerm() ); }

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

		protected HasPartOfType(RET raw) {
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

		protected IsAType(RET raw) {
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

		protected NegativelyRegulatesType(RET raw) {
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

		protected PositivelyRegulatesType(RET raw) {
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

		protected RegulatesType(RET raw) {
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

		protected SubOntologyType(RET raw) {
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
