package com.bio4j.model.ucsc_uniprot;

import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.ucsc.UCSCGenesGraph;
import com.bio4j.model.ucsc.vertices.UCSCGenesChromosome;
import com.bio4j.model.ucsc_uniprot.edges.UCSCGenesChromosomeProtein;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.angulillos.*;

/*

# UniProt NCBI taxonomy graph

This graph includes the connection between Proteins and NCBI taxonmic units modelled via the edge type ProteinNCBITaxon

## data model

### ProteinNCBITaxon

Connecting `Protein` and `NCBITaxon` vertices.

 */
public abstract class UCSCGenesUniProtGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
                UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

    protected I raw = null;

    public UCSCGenesUniProtGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	public abstract UniProtGraph<I, RV, RVT, RE, RET> uniProtGraph();
	public abstract UCSCGenesGraph<I, RV, RVT, RE, RET> ucscGenesGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// edges
	public abstract UCSCGenesChromosomeProteinType UCSCGenesChromosomeProtein();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class UCSCGenesChromosomeProteinType
			extends
            UCSCGenesUniProtEdgeType<
                                // src
                                UCSCGenesChromosome<I, RV, RVT, RE, RET>,
                                UCSCGenesGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeType,
                                UCSCGenesGraph<I, RV, RVT, RE, RET>,
                                // edge
                                UCSCGenesChromosomeProtein<I, RV, RVT, RE, RET>,
                                UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesChromosomeProteinType,
                                // tgt
                                Protein<I, RV, RVT, RE, RET>,
                                UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
                                UniProtGraph<I, RV, RVT, RE, RET>
                                >
			implements
			TypedEdge.Type.OneToMany
	{

		public UCSCGenesChromosomeProteinType(RET raw) {

			super(
                    UCSCGenesUniProtGraph.this.ucscGenesGraph().UCSCGenesChromosome(),
					raw,
                    UCSCGenesUniProtGraph.this.uniProtGraph().Protein()
			);
		}

		@Override
		public UCSCGenesChromosomeProteinType value() {
			return graph().UCSCGenesChromosomeProtein();
		}

		@Override
		public UCSCGenesChromosomeProtein<I, RV, RVT, RE, RET> from(RE edge) {
			return new UCSCGenesChromosomeProtein<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UCSCGenesUniProtVertexProperty<
			V extends UCSCGenesUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesUniProtVertexType<V, VT>,
			P extends UCSCGenesUniProtVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UCSCGenesUniProtVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UCSCGenesUniProtVertex<
			V extends UCSCGenesUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesUniProtVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UCSCGenesUniProtVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UCSCGenesUniProtVertexType<
			V extends UCSCGenesUniProtVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesUniProtVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UCSCGenesUniProtVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> graph() {
			return UCSCGenesUniProtGraph.this;
		}
	}

	public abstract static class UCSCGenesUniProtEdge<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UCSCGenesUniProtEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesUniProtEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, SG,
					E, ET, UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RE edge;
		private ET type;

		protected UCSCGenesUniProtEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UCSCGenesUniProtEdgeType<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UCSCGenesUniProtEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>.UCSCGenesUniProtEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
			>
			implements
			TypedEdge.Type<
					S, ST, SG,
					E, ET, UCSCGenesUniProtGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UCSCGenesUniProtEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final UCSCGenesUniProtGraph<I, RV, RVT, RE, RET> graph() {
			return UCSCGenesUniProtGraph.this;
		}
	}
}
