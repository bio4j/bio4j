package com.bio4j.model.enzymedb;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.angulillos.*;

/*

# ExPASy Enzyme DB graph

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
public abstract class EnzymeDBGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				EnzymeDBGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

	// indices
	public abstract TypedVertexIndex.Unique <
			// vertex
			Enzyme<I, RV, RVT, RE, RET>, EnzymeType,
			// property
			EnzymeType.id, String,
			// graph
			EnzymeDBGraph<I, RV, RVT, RE, RET>,
			I, RV, RVT, RE, RET
			>
	enzymeIdIndex();

    protected I raw = null;

    public EnzymeDBGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	public abstract UniProtEnzymeDBGraph<I, RV, RVT, RE, RET> uniProtEnzymeDBGraph();

	// types
	// vertices
	public abstract EnzymeType Enzyme();


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class EnzymeType
			extends
			EnzymeDBVertexType<
					Enzyme<I, RV, RVT, RE, RET>,
					EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeType
					> {

		public final id id = new id();
		public final cofactors cofactors = new cofactors();
		public final comment comment = new comment();
		public final officialName officialName = new officialName();
		public final alternateNames alternateNames = new alternateNames();
		public final catalyticActivity catalyticActivity = new catalyticActivity();
		public final prositeCrossReferences prositeCrossReferences = new prositeCrossReferences();

		public EnzymeType(RVT raw) {
			super(raw);
		}

		@Override
		public EnzymeType value() {
			return graph().Enzyme();
		}

		@Override
		public Enzyme<I, RV, RVT, RE, RET> from(RV vertex) {
			return new Enzyme<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, id, String> {
			public id() {
				super(EnzymeType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class cofactors
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, cofactors, String[]> {
			public cofactors() {
				super(EnzymeType.this);
			}

			public Class<String[]> valueClass() {
				return String[].class;
			}
		}

		public final class comment
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, comment, String> {
			public comment() {
				super(EnzymeType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class officialName
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, officialName, String> {
			public officialName() {
				super(EnzymeType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class alternateNames
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, alternateNames, String[]> {
			public alternateNames() {
				super(EnzymeType.this);
			}

			public Class<String[]> valueClass() {
				return String[].class;
			}
		}

		public final class catalyticActivity
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, catalyticActivity, String> {
			public catalyticActivity() {
				super(EnzymeType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class prositeCrossReferences
				extends
				EnzymeDBVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, prositeCrossReferences, String[]> {
			public prositeCrossReferences() {
				super(EnzymeType.this);
			}

			public Class<String[]> valueClass() {
				return String[].class;
			}
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class EnzymeDBVertexProperty<
			V extends EnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<V, VT>,
			P extends EnzymeDBVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected EnzymeDBVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class EnzymeDBVertex<
			V extends EnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected EnzymeDBVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public EnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class EnzymeDBVertexType<
			V extends EnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected EnzymeDBVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final EnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
			return EnzymeDBGraph.this;
		}
	}

	public abstract static class EnzymeEdge<
			S extends EnzymeDBVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<S, ST>,
			E extends EnzymeEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeEdgeType<S, ST, E, ET, T, TT>,
			T extends EnzymeDBVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, EnzymeDBGraph<I, RV, RVT, RE, RET>,
					E, ET, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, EnzymeDBGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected EnzymeEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public EnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class EnzymeEdgeType<
			S extends EnzymeDBVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<S, ST>,
			E extends EnzymeEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeEdgeType<S, ST, E, ET, T, TT>,
			T extends EnzymeDBVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeDBVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, EnzymeDBGraph<I, RV, RVT, RE, RET>,
					E, ET, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, EnzymeDBGraph<I, RV, RVT, RE, RET>
					> {

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected EnzymeEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final EnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
			return EnzymeDBGraph.this;
		}
	}

}
