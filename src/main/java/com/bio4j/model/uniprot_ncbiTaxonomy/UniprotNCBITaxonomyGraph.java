package com.bio4j.model.uniprot_ncbiTaxonomy;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.*;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class UniprotNCBITaxonomyGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

	public abstract UniprotGraph<I, RV, RVT, RE, RET> uniprotGraph();
	public abstract NCBITaxonomyGraph<I, RV, RVT, RE, RET> ncbiTaxonomyGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges
	public abstract ProteinNCBITaxonType ProteinNCBITaxon();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class ProteinNCBITaxonType
			extends
			UniprotNCBITaxonomyEdgeType <
					// src
					Protein<I, RV, RVT, RE, RET>,
					UniprotGraph<I, RV, RVT, RE, RET>.ProteinType,
					UniprotGraph<I, RV, RVT, RE, RET>,
					// edge
					ProteinNCBITaxon<I, RV, RVT, RE, RET>,
					UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>.ProteinNCBITaxonType,
					// tgt
					NCBITaxon<I, RV, RVT, RE, RET>,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.ManyToOne
	{

		protected ProteinNCBITaxonType(RET raw) {

			super(
					UniprotNCBITaxonomyGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotNCBITaxonomyGraph.this.ncbiTaxonomyGraph().NCBITaxon()
			);
		}

		@Override
		public ProteinNCBITaxonType value() {
			return graph().ProteinNCBITaxon();
		}

		@Override
		public ProteinNCBITaxon<I, RV, RVT, RE, RET> from(RE edge) {
			return new ProteinNCBITaxon<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniprotNCBITaxonomyVertexProperty<
			V extends UniprotNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniprotNCBITaxonomyVertexType<V, VT>,
			P extends UniprotNCBITaxonomyVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UniprotNCBITaxonomyVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UniprotNCBITaxonomyVertex<
			V extends UniprotNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniprotNCBITaxonomyVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniprotNCBITaxonomyVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniprotNCBITaxonomyVertexType<
			V extends UniprotNCBITaxonomyVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniprotNCBITaxonomyVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniprotNCBITaxonomyVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotNCBITaxonomyGraph.this;
		}
	}

	public abstract static class UniprotNCBITaxonomyEdge<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UniprotNCBITaxonomyEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniprotNCBITaxonomyEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, SG,
					E, ET, UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RE edge;
		private ET type;

		protected UniprotNCBITaxonomyEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniprotNCBITaxonomyEdgeType<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UniprotNCBITaxonomyEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>.UniprotNCBITaxonomyEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
			>
			implements
			TypedEdge.Type<
					S, ST, SG,
					E, ET, UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UniprotNCBITaxonomyEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final UniprotNCBITaxonomyGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotNCBITaxonomyGraph.this;
		}
	}
}
