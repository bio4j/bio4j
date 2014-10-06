package com.bio4j.model.ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy.edges.NCBITaxonParent;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniprotNCBITaxonomyGraph;
import com.ohnosequences.typedGraphs.*;

/*

# NCBI Taxonomy graph

This graph includes all Enzyme terms that are included in ExPASy database but those that have been either transferred or deleted.
You can check more information about Enzyme database [here](http://enzyme.expasy.org/)

## data model

It only consists of the vertices of Enzyme type.

### Enzymes

We have a `Enzyme` vertex which contains property data present for each term.

##### Enzyme properties stored

- id
- official name
- cofactors _(stored as a flatten String array)_
- prosite cross references _(stored as a flatten String array)_
- catalytic activity
- comment


 */
public abstract class NCBITaxonomyGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

    protected I raw = null;

    public NCBITaxonomyGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	// indices
	public abstract TypedVertexIndex.Unique <
			// vertex
			NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonType,
			// property
			NCBITaxonType.id, String,
			// graph
			NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
			I, RV, RVT, RE, RET
			>
	nCBITaxonIdIndex();

	public abstract UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET> uniprotNCBITaxonomyGraph();

	// types
	// vertices
	public abstract NCBITaxonType NCBITaxon();

	// edges
	public abstract NCBITaxonParentType NCBITaxonParent();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class NCBITaxonType
			extends
			NCBITaxonomyVertexType<
					NCBITaxon<I, RV, RVT, RE, RET>,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType
					> {

		public final id id = new id();
		public final name name = new name();
		public final comment comment = new comment();
		public final scientificName scientificName = new scientificName();
		public final taxonomicRank taxonomicRank = new taxonomicRank();

		public NCBITaxonType(RVT raw) {
			super(raw);
		}

		@Override
		public NCBITaxonType value() {
			return graph().NCBITaxon();
		}

		@Override
		public NCBITaxon<I, RV, RVT, RE, RET> from(RV vertex) {
			return new NCBITaxon<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				NCBITaxonomyVertexProperty<NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonType, id, String> {
			public id() {
				super(NCBITaxonType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class name
				extends
				NCBITaxonomyVertexProperty<NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonType, name, String> {
			public name() {
				super(NCBITaxonType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class comment
				extends
				NCBITaxonomyVertexProperty<NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonType, comment, String> {
			public comment() {
				super(NCBITaxonType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class scientificName
				extends
				NCBITaxonomyVertexProperty<NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonType, scientificName, String> {
			public scientificName() {
				super(NCBITaxonType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class taxonomicRank
				extends
				NCBITaxonomyVertexProperty<NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonType, taxonomicRank, String> {
			public taxonomicRank() {
				super(NCBITaxonType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}


	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types

	public final class NCBITaxonParentType
			extends
			NCBITaxonomyEdgeType<
					NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
					NCBITaxonParent<I, RV, RVT, RE, RET>, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonParentType,
					NCBITaxon<I, RV, RVT, RE, RET>, NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType
					>
			implements
			TypedEdge.Type.ManyToOne {

		public NCBITaxonParentType(RET raw) {
			super(NCBITaxonomyGraph.this.NCBITaxon(), raw, NCBITaxonomyGraph.this.NCBITaxon());
		}

		@Override
		public NCBITaxonParentType value() {
			return graph().NCBITaxonParent();
		}

		@Override
		public NCBITaxonParent<I, RV, RVT, RE, RET> from(RE edge) {
			return new NCBITaxonParent<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class NCBITaxonomyVertexProperty<
			V extends NCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<V, VT>,
			P extends NCBITaxonomyVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, NCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected NCBITaxonomyVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class NCBITaxonomyVertex<
			V extends NCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, NCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected NCBITaxonomyVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public NCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class NCBITaxonomyVertexType<
			V extends NCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, NCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected NCBITaxonomyVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final NCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
			return NCBITaxonomyGraph.this;
		}
	}

	public abstract static class NCBITaxonomyEdge<
			S extends NCBITaxonomyVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<S, ST>,
			E extends NCBITaxonomyEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyEdgeType<S, ST, E, ET, T, TT>,
			T extends NCBITaxonomyVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
					E, ET, NCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, NCBITaxonomyGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected NCBITaxonomyEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public NCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class NCBITaxonomyEdgeType<
			S extends NCBITaxonomyVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<S, ST>,
			E extends NCBITaxonomyEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyEdgeType<S, ST, E, ET, T, TT>,
			T extends NCBITaxonomyVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonomyVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
					E, ET, NCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, NCBITaxonomyGraph<I, RV, RVT, RE, RET>
					> {

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected NCBITaxonomyEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final NCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
			return NCBITaxonomyGraph.this;
		}
	}

}
