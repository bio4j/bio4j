package com.bio4j.model.uniprot_uniref;


import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_uniref.relationships.*;
import com.bio4j.model.uniref.UniRefGraph;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
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

	public abstract UniprotGraph<I, RV, RVT, RE, RET> uniprotGraph();
	public abstract UniRefGraph<I, RV, RVT, RE, RET> uniRefGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges

	public abstract UniRef50MemberType UniRef50Member();

	public abstract UniRef90MemberType UniRef90Member();

	public abstract UniRef100MemberType UniRef100Member();

	public abstract UniRef100RepresentantType UniRef100Representant();

	public abstract UniRef90RepresentantType UniRef90Representant();

	public abstract UniRef50RepresentantType UniRef50Representant();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class UniRef50MemberType
			extends
			UniprotUniRefEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					UniRef50Member<I, RV, RVT, RE, RET>,
					UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef50MemberType,
					// tgt
					UniRef50Cluster<I, RV, RVT, RE, RET>,
					UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
					UniRefGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.ManyToOne
	{

		protected UniRef50MemberType(RET raw) {

			super(
					UniprotUniRefGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotUniRefGraph.this.uniRefGraph().UniRef50Cluster()
			);
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

	public final class UniRef90MemberType
			extends
			UniprotUniRefEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					UniRef90Member<I, RV, RVT, RE, RET>,
					UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef90MemberType,
					// tgt
					UniRef90Cluster<I, RV, RVT, RE, RET>,
					UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
					UniRefGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.ManyToOne
	{

		protected UniRef90MemberType(RET raw) {

			super(
					UniprotUniRefGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotUniRefGraph.this.uniRefGraph().UniRef90Cluster()
			);
		}

		@Override
		public UniRef90MemberType value() {
			return graph().UniRef90Member();
		}

		@Override
		public UniRef90Member<I, RV, RVT, RE, RET> from(RE edge) {
			return new UniRef90Member<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class UniRef100MemberType
			extends
			UniprotUniRefEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					UniRef100Member<I, RV, RVT, RE, RET>,
					UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef100MemberType,
					// tgt
					UniRef100Cluster<I, RV, RVT, RE, RET>,
					UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
					UniRefGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.ManyToOne
	{

		protected UniRef100MemberType(RET raw) {

			super(
					UniprotUniRefGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotUniRefGraph.this.uniRefGraph().UniRef100Cluster()
			);
		}

		@Override
		public UniRef100MemberType value() {
			return graph().UniRef100Member();
		}

		@Override
		public UniRef100Member<I, RV, RVT, RE, RET> from(RE edge) {
			return new UniRef100Member<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class UniRef100RepresentantType
			extends
			UniprotUniRefEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					UniRef100Representant<I, RV, RVT, RE, RET>,
					UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef100RepresentantType,
					// tgt
					UniRef100Cluster<I, RV, RVT, RE, RET>,
					UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType,
					UniRefGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.OneToOne
	{

		protected UniRef100RepresentantType(RET raw) {

			super(
					UniprotUniRefGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotUniRefGraph.this.uniRefGraph().UniRef100Cluster()
			);
		}

		@Override
		public UniRef100RepresentantType value() {
			return graph().UniRef100Representant();
		}

		@Override
		public UniRef100Representant<I, RV, RVT, RE, RET> from(RE edge) {
			return new UniRef100Representant<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class UniRef50RepresentantType
			extends
			UniprotUniRefEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					UniRef50Representant<I, RV, RVT, RE, RET>,
					UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef50RepresentantType,
					// tgt
					UniRef50Cluster<I, RV, RVT, RE, RET>,
					UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType,
					UniRefGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.OneToOne
	{

		protected UniRef50RepresentantType(RET raw) {

			super(
					UniprotUniRefGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotUniRefGraph.this.uniRefGraph().UniRef50Cluster()
			);
		}

		@Override
		public UniRef50RepresentantType value() {
			return graph().UniRef50Representant();
		}

		@Override
		public UniRef50Representant<I, RV, RVT, RE, RET> from(RE edge) {
			return new UniRef50Representant<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	public final class UniRef90RepresentantType
			extends
			UniprotUniRefEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					UniRef90Representant<I, RV, RVT, RE, RET>,
					UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniRef90RepresentantType,
					// tgt
					UniRef90Cluster<I, RV, RVT, RE, RET>,
					UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType,
					UniRefGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.OneToOne
	{

		protected UniRef90RepresentantType(RET raw) {

			super(
					UniprotUniRefGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotUniRefGraph.this.uniRefGraph().UniRef90Cluster()
			);
		}

		@Override
		public UniRef90RepresentantType value() {
			return graph().UniRef90Representant();
		}

		@Override
		public UniRef90Representant<I, RV, RVT, RE, RET> from(RE edge) {
			return new UniRef90Representant<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniprotUniRefVertexProperty<
			V extends UniprotUniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniprotUniRefVertexType<V, VT>,
			P extends UniprotUniRefVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UniprotUniRefVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UniprotUniRefVertex<
			V extends UniprotUniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniprotUniRefVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniprotUniRefVertex(RV vertex, VT type) {

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

	abstract class UniprotUniRefVertexType<
			V extends UniprotUniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniprotUniRefVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniprotUniRefVertexType(RVT raw) {
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

	public abstract static class UniprotUniRefEdge<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UniprotUniRefEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniprotUniRefEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, SG,
					E, ET, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RE edge;
		private ET type;

		protected UniprotUniRefEdge(RE edge, ET type) {

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

	abstract class UniprotUniRefEdgeType<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UniprotUniRefEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UniprotUniRefGraph<I, RV, RVT, RE, RET>.UniprotUniRefEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
			>
			implements
			TypedEdge.Type<
					S, ST, SG,
					E, ET, UniprotUniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UniprotUniRefEdgeType(ST srcT, RET raw, TT tgtT) {

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
