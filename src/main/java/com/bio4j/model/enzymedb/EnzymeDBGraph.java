package com.bio4j.model.enzymedb;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniprotEnzymeDBGraph;
import com.ohnosequences.typedGraphs.*;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
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

    protected I raw = null;

    public EnzymeDBGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	public abstract UniprotEnzymeDBGraph<I, RV, RVT, RE, RET> uniprotEnzymeDBGraph();

	// types
	// vertices
	public abstract EnzymeType Enzyme();


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class EnzymeType
			extends
			EnzymeVertexType<
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
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, id, String> {
			public id() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class cofactors
				extends
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, cofactors, String[]> {
			public cofactors() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String[]> valueClass() {
				return String[].class;
			}
		}

		public final class comment
				extends
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, comment, String> {
			public comment() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class officialName
				extends
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, officialName, String> {
			public officialName() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class alternateNames
				extends
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, alternateNames, String[]> {
			public alternateNames() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String[]> valueClass() {
				return String[].class;
			}
		}

		public final class catalyticActivity
				extends
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, catalyticActivity, String> {
			public catalyticActivity() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

		public final class prositeCrossReferences
				extends
				EnzymeVertexProperty<Enzyme<I, RV, RVT, RE, RET>, EnzymeType, prositeCrossReferences, String[]> {
			public prositeCrossReferences() {
				super(EnzymeType.this.graph().Enzyme());
			}

			public Class<String[]> valueClass() {
				return String[].class;
			}
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class EnzymeVertexProperty<
			V extends EnzymeVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<V, VT>,
			P extends EnzymeVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected EnzymeVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class EnzymeVertex<
			V extends EnzymeVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected EnzymeVertex(RV vertex, VT type) {

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

	abstract class EnzymeVertexType<
			V extends EnzymeVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, EnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected EnzymeVertexType(RVT raw) {
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
			S extends EnzymeVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<S, ST>,
			E extends EnzymeEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeEdgeType<S, ST, E, ET, T, TT>,
			T extends EnzymeVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<T, TT>,
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
			S extends EnzymeVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<S, ST>,
			E extends EnzymeEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeEdgeType<S, ST, E, ET, T, TT>,
			T extends EnzymeVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends EnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymeVertexType<T, TT>
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
