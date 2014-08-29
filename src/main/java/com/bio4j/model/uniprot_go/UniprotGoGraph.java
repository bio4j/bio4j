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

    protected I raw = null;

    public UniprotGoGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	public abstract UniprotGraph<I, RV, RVT, RE, RET> uniprotGraph();
	public abstract GoGraph<I, RV, RVT, RE, RET> goGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges
	public abstract GoAnnotationType GoAnnotation();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class GoAnnotationType
	extends
		UniprotGoEdgeType <
			// src
			Protein<I, RV, RVT, RE, RET>, 
			UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
			UniprotGraph<I, RV, RVT, RE, RET>,
			// edge
			GoAnnotation<I, RV, RVT, RE, RET>, 
			UniprotGoGraph<I, RV, RVT, RE, RET>.GoAnnotationType,
			// tgt
			GoTerm<I, RV, RVT, RE, RET>,
			GoGraph<I, RV, RVT, RE, RET>.GoTermType,
			GoGraph<I, RV, RVT, RE, RET>
		>
	implements
		TypedEdge.Type.ManyToMany 
	{

		protected GoAnnotationType(RET raw) {

			super(
				UniprotGoGraph.this.uniprotGraph().Protein(), 
				raw, 
				UniprotGoGraph.this.goGraph().GoTerm()
			);
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
			V extends UniprotGoVertex<V, VT, I, RV, RVT, RE, RET>,
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
		// src
		S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
		ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
		SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
		// edge
		E extends UniprotGoEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
		ET extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoEdgeType<S,ST,SG, E,ET, T,TT,TG>,
		// tgt
		T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
		TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
		TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
		I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
	>
	implements
		TypedEdge<
			S, ST, SG,
			E, ET, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
			T, TT, TG
		> 
	{

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
		// src
		S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
		ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
		SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
		// edge
		E extends UniprotGoEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
		ET extends UniprotGoGraph<I, RV, RVT, RE, RET>.UniprotGoEdgeType<S,ST,SG, E,ET, T,TT,TG>,
		// tgt
		T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
		TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
		TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
	>
	implements
		TypedEdge.Type<
			S, ST, SG,
			E, ET, UniprotGoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
			T, TT, TG
		>
	{

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
