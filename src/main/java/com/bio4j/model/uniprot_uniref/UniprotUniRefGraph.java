package com.bio4j.model.uniprot_uniref;


import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.relationships.UniRef50Member;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.ohnosequences.typedGraphs.*;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class UniprotUniRefGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				UniprotUniRefGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

	public abstract UniprotGraph uniprotGraph();
	public abstract UniRefGraph uniRefGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges

	public abstract UniRef50MemberType UniRef50Member();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class UniRef50MemberType
			extends
			UniRefEdgeType<
					Protein<I, RV, RVT, RE, RET>, UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniRef50Member<I, RV, RVT, RE, RET>, UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef50MemberType,
					UniRef50Cluster<I, RV, RVT, RE, RET>, UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType
					>
			implements
			TypedEdge.Type.ManyToOne {

		protected UniRef50MemberType(RET raw) {
			super(UniprotGraph.Protein(), raw, UniRefGraph.UniRef50Cluster());
		}

		@Override
		public UniRef50MemberType value() {
			return graph().UniRef50Member();
		}

		@Override
		public UniRef50Member<I, RV, RVT, RE, RET> from(RE edge) {
			return new UniRef50Member<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniRefVertexProperty<
			V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>,
			P extends UniRefVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UniRefVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UniRefVertex<
			V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniRefVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UniprotUniRefGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniRefVertexType<
			V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniRefVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UniprotUniRefGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotUniRefGraph.this;
		}
	}

	public abstract static class UniRefEdge<
			S extends UniRefVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<S, ST>,
			E extends UniRefEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefEdgeType<S, ST, E, ET, T, TT>,
			T extends UniRefVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, UniprotUniRefGraph<I, RV, RVT, RE, RET>,
					E, ET, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniprotUniRefGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected UniRefEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UniprotUniRefGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniRefEdgeType<
			S extends UniRefVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<S, ST>,
			E extends UniRefEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefEdgeType<S, ST, E, ET, T, TT>,
			T extends UniRefVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, UniprotUniRefGraph<I, RV, RVT, RE, RET>,
					E, ET, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniprotUniRefGraph<I, RV, RVT, RE, RET>
					> {

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UniRefEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final UniprotUniRefGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotUniRefGraph.this;
		}
	}
}
