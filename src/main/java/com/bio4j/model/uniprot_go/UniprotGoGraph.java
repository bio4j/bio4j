package com.bio4j.model.uniprot_go;


import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
import com.ohnosequences.typedGraphs.*;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class UniprotGoGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				UniprotGoGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

	public abstract UniprotGraph uniprotGraph();
	public abstract GoGraph goGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges
	public abstract GoAnnotationType GoAnnotation();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class GoAnnotationType
			extends
			UniprotGoEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					GoAnnotation<I, RV, RVT, RE, RET>, UniprotGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType,
					GoTerm<I, RV, RVT, RE, RET>, GoGraph<I, RV, RVT, RE, RET>.GoTermType
					>
			implements
			TypedEdge.Type.ManyToMany {

		protected GoAnnotationType(RET raw) {
			super(UniprotGraph.Protein(), raw, GoGraph.GoTermType());
		}

		@Override
		public GoAnnotationType value() {
			return graph().GoAnnotation();
		}

		@Override
		public GoAnnotation<I, RV, RVT, RE, RET> from(RE edge) {
			return new GoAnnotation<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniprotGoVertexProperty<
			V extends TypedVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoVertexType<V, VT>,
			P extends UniprotGoVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UniprotGoVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UniprotGoVertex<
			V extends UniprotGoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniprotGoVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UniprotGoGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniprotGoVertexType<
			V extends UniprotGoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniprotGoVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UniprotGoGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotGoGraph.this;
		}
	}

	public abstract static class UniprotGoEdge<
			S extends TypedVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends TypedGraph<I, RV, RVT, RE, RET>.TypedVertexType<S, ST>,
			E extends UniprotGoEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoEdgeType<S, ST, E, ET, T, TT>,
			T extends TypedVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends TypedGraph<I, RV, RVT, RE, RET>.TypedVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, UniprotGoGraph<I, RV, RVT, RE, RET>,
					E, ET, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniprotGoGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected UniprotGoEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UniprotGoGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniprotGoEdgeType<
			S extends TypedVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends TypedGraph<I, RV, RVT, RE, RET>.TypedVertexType<S, ST>,
			E extends UniprotGoEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoEdgeType<S, ST, E, ET, T, TT>,
			T extends TypedVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends TypedGraph<I, RV, RVT, RE, RET>.TypedVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, UniprotGoGraph<I, RV, RVT, RE, RET>,
					E, ET, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniprotGoGraph<I, RV, RVT, RE, RET>
					> {

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UniprotGoEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final UniprotGoGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotGoGraph.this;
		}
	}
}
