package com.bio4j.model.ncbiTaxonomy_geninfo.;


import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.geninfo.GenInfoGraph;
import com.bio4j.model.geninfo.nodes.GenInfo;
import com.bio4j.model.ncbiTaxonomy.NCBITaxonomyGraph;
import com.bio4j.model.ncbiTaxonomy.nodes.NCBITaxon;
import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
import com.ohnosequences.typedGraphs.*;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
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
	public abstract NCBITaxonGenInfoType NCBITaxonGenInfo();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Edge types
	public final class NCBITaxonGenInfoType
			extends
			NCBITaxonGenInfoEdgeType <
					// src
					NCBITaxon<I, RV, RVT, RE, RET>,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>.NCBITaxonType,
					NCBITaxonomyGraph<I, RV, RVT, RE, RET>,
					// edge
					NCBITaxonGenInfo<I, RV, RVT, RE, RET>,
					UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.EnzymaticActivityType,
					// tgt
					GenInfo<I, RV, RVT, RE, RET>,
					GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType,
					GenInfoGraph<I, RV, RVT, RE, RET>
					>
			implements
			TypedEdge.Type.ManyToMany
	{

		public EnzymaticActivityType(RET raw) {

			super(
					UniprotEnzymeDBGraph.this.uniprotGraph().Protein(),
					raw,
					UniprotEnzymeDBGraph.this.enzymeDBGraph().Enzyme()
			);
		}

		@Override
		public EnzymaticActivityType value() {
			return graph().EnzymaticActivity();
		}

		@Override
		public EnzymaticActivity<I, RV, RVT, RE, RET> from(RE edge) {
			return new EnzymaticActivity<I, RV, RVT, RE, RET>(edge, this);
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniprotEnzymeDBVertexProperty<
			V extends UniprotEnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.UniprotEnzymeDBVertexType<V, VT>,
			P extends UniprotEnzymeDBVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected UniprotEnzymeDBVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class UniprotEnzymeDBVertex<
			V extends UniprotEnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.UniprotEnzymeDBVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniprotEnzymeDBVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UniprotEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniprotEnzymeDBVertexType<
			V extends UniprotEnzymeDBVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.UniprotEnzymeDBVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniprotEnzymeDBVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UniprotEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotEnzymeDBGraph.this;
		}
	}

	public abstract static class UniprotEnzymeDBEdge<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UniprotEnzymeDBEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.UniprotEnzymeDBEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, SG,
					E, ET, UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RE edge;
		private ET type;

		protected UniprotEnzymeDBEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UniprotEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class UniprotEnzymeDBEdgeType<
			// src
			S extends TypedVertex<S, ST, SG, I, RV, RVT, RE, RET>,
			ST extends TypedVertex.Type<S,ST, SG, I, RV, RVT, RE, RET>,
			SG extends TypedGraph<SG, I, RV, RVT, RE, RET>,
			// edge
			E extends UniprotEnzymeDBEdge<S,ST,SG, E,ET, T, TT,TG, I, RV, RVT, RE, RET>,
			ET extends UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>.UniprotEnzymeDBEdgeType<S,ST,SG, E,ET, T,TT,TG>,
			// tgt
			T extends TypedVertex<T,TT,TG, I, RV, RVT, RE, RET>,
			TT extends TypedVertex.Type<T,TT,TG, I, RV, RVT, RE, RET>,
			TG extends TypedGraph<TG, I, RV, RVT, RE, RET>
			>
			implements
			TypedEdge.Type<
					S, ST, SG,
					E, ET, UniprotEnzymeDBGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, TG
					>
	{

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected UniprotEnzymeDBEdgeType(ST srcT, RET raw, TT tgtT) {

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
		public final UniprotEnzymeDBGraph<I, RV, RVT, RE, RET> graph() {
			return UniprotEnzymeDBGraph.this;
		}
	}
}
