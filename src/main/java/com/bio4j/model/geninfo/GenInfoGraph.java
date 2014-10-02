package com.bio4j.model.geninfo;

import com.bio4j.model.geninfo.vertices.GenInfo;
import com.bio4j.model.ncbiTaxonomy_geninfo.NCBITaxonomyGenInfoGraph;
import com.ohnosequences.typedGraphs.*;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class GenInfoGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				GenInfoGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

	protected I raw = null;

	public GenInfoGraph(I graph){
		raw = graph;
	}

	public I raw(){
		return raw;
	}

	public abstract NCBITaxonomyGenInfoGraph<I,RV, RVT, RE, RET> ncbiTaxonomyGenInfoGraph();

	// indices
	public abstract TypedVertexIndex.Unique <
			// vertex
			GenInfo<I, RV, RVT, RE, RET>, GenInfoType,
			// property
			GenInfoType.id, String,
			// graph
			GenInfoGraph<I, RV, RVT, RE, RET>,
			I, RV, RVT, RE, RET
			>
	genInfoIdIndex();

	// types
	// vertices
	public abstract GenInfoType GenInfo();


	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class GenInfoType
			extends
			GenInfoVertexType<
					GenInfo<I, RV, RVT, RE, RET>,
					GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoType
					> {

		public final id id = new id();

		public GenInfoType(RVT raw) {
			super(raw);
		}

		@Override
		public GenInfoType value() {
			return graph().GenInfo();
		}

		@Override
		public GenInfo<I, RV, RVT, RE, RET> from(RV vertex) {
			return new GenInfo<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
				extends
				GenInfoVertexProperty<GenInfo<I, RV, RVT, RE, RET>, GenInfoType, id, String> {
			public id() {
				super(GenInfoType.this);
			}

			public Class<String> valueClass() {
				return String.class;
			}
		}

	}



	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class GenInfoVertexProperty<
			V extends GenInfoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoVertexType<V, VT>,
			P extends GenInfoVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, GenInfoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		protected GenInfoVertexProperty(VT type) {

			this.type = type;
		}

		private VT type;

		@Override
		public final VT elementType() {
			return type;
		}
	}

	public abstract static class GenInfoVertex<
			V extends GenInfoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, GenInfoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected GenInfoVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public GenInfoGraph<I, RV, RVT, RE, RET> graph() {
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

	abstract class GenInfoVertexType<
			V extends GenInfoVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends GenInfoGraph<I, RV, RVT, RE, RET>.GenInfoVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, GenInfoGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected GenInfoVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final GenInfoGraph<I, RV, RVT, RE, RET> graph() {
			return GenInfoGraph.this;
		}
	}

}
