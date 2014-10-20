package com.bio4j.model.ncbiTaxonomy_geninfo;


import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ncbiTaxonomy_geninfo.edges.GenInfoNCBITaxon;
import com.ohnosequences.typedGraphs.*;

/*

# NCBI taxonomy GenInfo graph

This graph includes the edges among NCBI taxonomic units and GenInfo vertices.

## data model

### GenInfoNCBITaxon

Connecting `GenInfo` and `NCBITaxon` vertices.

 */
public abstract class NCBITaxonomyGenInfoGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

	protected I raw = null;

	public NCBITaxonomyGenInfoGraph(I graph){
		raw = graph;
	}

	public I raw(){
		return raw;
	}

	public abstract NCBITaxonomyGraph<I, RV, RVT, RE, RET> ncbiTaxonomyGraph();
	public abstract GenInfoGraph<I, RV, RVT, RE, RET> genInfoGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges
	public abstract GenInfoNCBITaxonType GenInfoNCBITaxon();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class GenInfoNCBITaxonType
			extends
			NCBITaxonomyGenInfoEdgeType <
					// src
					GenInfo<I, RV, RVT, RE, RET>,
					GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType,
					GenInfoGraph<I, RV, RVT, RE, RET>,
					// edge
					GenInfoNCBITaxon<I, RV, RVT, RE, RET>,
					NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>.GenInfoNCBITaxonType,
					// tgt
					NCBITaxon<I, RV, RVT, RE, RET>,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>>
			implements
			TypedEdge.Type.ManyToOne
	{

		public GenInfoNCBITaxonType(RET raw) {

			super(
					NCBITaxonomyGenInfoGraph.this.genInfoGraph().GenInfo(),
					raw,
					NCBITaxonomyGenInfoGraph.this.ncbiTaxonomyGraph().NCBITaxon()
			);
		}

		@Override
		public GenInfoNCBITaxonType value() {
			return graph().GenInfoNCBITaxon();
		}

		@Override
		public GenInfoNCBITaxon<I, RV, RVT, RE, RET> from(RE edge) {
			return new GenInfoNCBITaxon<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract static class NCBITaxonomyGenInfoEdge<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends NCBITaxonomyGenInfoEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>.NCBITaxonomyGenInfoEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, SG,
					E, ET, NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RE edge;
		private ET type;

		protected NCBITaxonomyGenInfoEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class NCBITaxonomyGenInfoEdgeType<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends NCBITaxonomyGenInfoEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>.NCBITaxonomyGenInfoEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
			>
			implements
			TypedEdge.Type<
					S, ST, SG,
					E, ET, NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected NCBITaxonomyGenInfoEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final NCBITaxonomyGenInfoGraph<I, RV, RVT, RE, RET> graph() {
			return NCBITaxonomyGenInfoGraph.this;
		}
	}
}
