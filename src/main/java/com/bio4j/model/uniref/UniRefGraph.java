package com.bio4j.model.uniref;

import com.bio4j.model.uniprot_uniref.UniProtUniRefGraph;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.*;

/*

# UniRef graph

This graph includes virtually all data from UniRef clusters including 50, 90 and 100.
You can check more information about UniRef data [here](http://www.uniprot.org/help/uniref)

## data model

It only consists of three different vertex types, one for each type of UniRefCluster:

- `UniRef50Cluster`
- `UniRef90Cluster`
- `UniRef100Cluster`


##### UniRef clusters' properties stored

- `id`
- `name`
- `updatedDate`

_There no specific property for any of the three different types of cluster_

 */
public abstract class UniRefGraph<
		// untyped graph
		I extends UntypedGraph<RV, RVT, RE, RET>,
		// vertices
		RV, RVT,
		// edges
		RE, RET
		>
		implements
		TypedGraph<
				UniRefGraph<I, RV, RVT, RE, RET>,
				I, RV, RVT, RE, RET
				> {

    protected I raw = null;

    public UniRefGraph(I graph){
        raw = graph;
    }

    public I raw(){
        return raw;
    }

	public abstract UniProtUniRefGraph<I, RV, RVT, RE, RET> uniProtUniRefGraph();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// indices
	public abstract TypedVertexIndex.Unique <
			// vertex
			UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType,
			// property
			UniRef50ClusterType.id, String,
			// graph
			UniRefGraph<I, RV, RVT, RE, RET>,
			I, RV, RVT, RE, RET
			>
	uniRef50ClusterIdIndex();
	public abstract TypedVertexIndex.Unique <
			// vertex
			UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType,
			// property
			UniRef90ClusterType.id, String,
			// graph
			UniRefGraph<I, RV, RVT, RE, RET>,
			I, RV, RVT, RE, RET
			>
	uniRef90ClusterIdIndex();
	public abstract TypedVertexIndex.Unique <
			// vertex
			UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType,
			// property
			UniRef100ClusterType.id, String,
			// graph
			UniRefGraph<I, RV, RVT, RE, RET>,
			I, RV, RVT, RE, RET
			>
	uniRef100ClusterIdIndex();

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// types
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// vertices
	public abstract UniRef50ClusterType UniRef50Cluster();

	public abstract UniRef90ClusterType UniRef90Cluster();

	public abstract UniRef100ClusterType UniRef100Cluster();



	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Vertex types

	public final class UniRef50ClusterType
		extends
			UniRefVertexType<
				UniRef50Cluster<I, RV, RVT, RE, RET>,
				UniRefGraph<I, RV, RVT, RE, RET>.UniRef50ClusterType
			>
	{

		public final id id = new id();
		public final name name = new name();
		public final updatedDate updatedDate = new updatedDate();
		public final representantAccession representantAccession = new representantAccession();
		public final members members = new members();

		public UniRef50ClusterType(RVT raw) { super(raw); }

		@Override
		public UniRef50ClusterType value() { return graph().UniRef50Cluster(); }

		@Override
		public UniRef50Cluster<I, RV, RVT, RE, RET> from(RV vertex) {
			return new UniRef50Cluster<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
			extends
				UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, id, String>
		{
			public id() { super(UniRef50ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class name
			extends
				UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, name, String> 
		{	
			public name() { super(UniRef50ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class updatedDate
			extends
				UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, updatedDate, String>
		{
			public updatedDate() { super(UniRef50ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class representantAccession
				extends
				UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, representantAccession, String>
		{
			public representantAccession() { super(UniRef50ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class members
				extends
				UniRefVertexProperty<UniRef50Cluster<I, RV, RVT, RE, RET>, UniRef50ClusterType, members, String[]>
		{
			public members() { super(UniRef50ClusterType.this); }
			public Class<String[]> valueClass() { return String[].class; }
		}
	}

	public final class UniRef90ClusterType
		extends
			UniRefVertexType<
				UniRef90Cluster<I, RV, RVT, RE, RET>,
				UniRefGraph<I, RV, RVT, RE, RET>.UniRef90ClusterType
			>
	{

		public final id id = new id();
		public final name name = new name();
		public final updatedDate updatedDate = new updatedDate();
		public final representantAccession representantAccession = new representantAccession();
		public final members members = new members();

		public UniRef90ClusterType(RVT raw) { super(raw); }

		@Override
		public UniRef90ClusterType value() { return graph().UniRef90Cluster(); }

		@Override
		public UniRef90Cluster<I, RV, RVT, RE, RET> from(RV vertex) { 

			return new UniRef90Cluster<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
			extends
				UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, id, String> 
		{

			public id() { super(UniRef90ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class name
			extends
				UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, name, String> 
		{
			public name() { super(UniRef90ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class updatedDate
			extends
				UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, updatedDate, String> 
		{
			public updatedDate() { super(UniRef90ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class representantAccession
				extends
				UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, representantAccession, String>
		{
			public representantAccession() { super(UniRef90ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class members
				extends
				UniRefVertexProperty<UniRef90Cluster<I, RV, RVT, RE, RET>, UniRef90ClusterType, members, String[]>
		{
			public members() { super(UniRef90ClusterType.this); }
			public Class<String[]> valueClass() { return String[].class; }
		}
	}

	public final class UniRef100ClusterType
		extends
			UniRefVertexType<
				UniRef100Cluster<I, RV, RVT, RE, RET>,
				UniRefGraph<I, RV, RVT, RE, RET>.UniRef100ClusterType
			>
	{
		public final id id = new id();
		public final name name = new name();
		public final updatedDate updatedDate = new updatedDate();
		public final representantAccession representantAccession = new representantAccession();
		public final members members = new members();

		public UniRef100ClusterType(RVT raw) { super(raw); }

		@Override
		public UniRef100ClusterType value() { return graph().UniRef100Cluster(); }

		@Override
		public UniRef100Cluster<I, RV, RVT, RE, RET> from(RV vertex) {

			return new UniRef100Cluster<I, RV, RVT, RE, RET>(vertex, this);
		}

		public final class id
			extends
				UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, id, String>
		{
			public id() { super(UniRef100ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class name
			extends
				UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, name, String> 
		{
			public name() { super(UniRef100ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class updatedDate
			extends
				UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, updatedDate, String>
		{	
			public updatedDate() { super(UniRef100ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class representantAccession
				extends
				UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, representantAccession, String>
		{
			public representantAccession() { super(UniRef100ClusterType.this); }
			public Class<String> valueClass() { return String.class; }
		}

		public final class members
				extends
				UniRefVertexProperty<UniRef100Cluster<I, RV, RVT, RE, RET>, UniRef100ClusterType, members, String[]>
		{
			public members() { super(UniRef100ClusterType.this); }
			public Class<String[]> valueClass() { return String[].class; }
		}
	}



	////////////////////////////////////////////////////////////////////////////////////////////////////////
	// helper classes

	public abstract class UniRefVertexProperty<
			V extends UniRefVertex<V, VT, I, RV, RVT, RE, RET>,
			VT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>,
			P extends UniRefVertexProperty<V, VT, P, PV>,
			PV
			>
			implements
			Property<V, VT, P, PV, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

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
			VT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedVertex<V, VT, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RV vertex;
		private VT type;

		protected UniRefVertex(RV vertex, VT type) {

			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public UniRefGraph<I, RV, RVT, RE, RET> graph() {
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
			VT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<V, VT>
			>
			implements
			TypedVertex.Type<V, VT, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> {

		private RVT raw;

		protected UniRefVertexType(RVT raw) {
			this.raw = raw;
		}

		@Override
		public final RVT raw() {
			return raw;
		}

		@Override
		public final UniRefGraph<I, RV, RVT, RE, RET> graph() {
			return UniRefGraph.this;
		}
	}

	public abstract static class UniRefEdge<
			S extends UniRefVertex<S, ST, I, RV, RVT, RE, RET>,
			ST extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<S, ST>,
			E extends UniRefEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefEdgeType<S, ST, E, ET, T, TT>,
			T extends UniRefVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<T, TT>,
			I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
			>
			implements
			TypedEdge<
					S, ST, UniRefGraph<I, RV, RVT, RE, RET>,
					E, ET, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniRefGraph<I, RV, RVT, RE, RET>
					> {

		private RE edge;
		private ET type;

		protected UniRefEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public UniRefGraph<I, RV, RVT, RE, RET> graph() {
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
			ST extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<S, ST>,
			E extends UniRefEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
			ET extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefEdgeType<S, ST, E, ET, T, TT>,
			T extends UniRefVertex<T, TT, I, RV, RVT, RE, RET>,
			TT extends UniRefGraph<I, RV, RVT, RE, RET>.UniRefVertexType<T, TT>
			>
			implements
			TypedEdge.Type<
					S, ST, UniRefGraph<I, RV, RVT, RE, RET>,
					E, ET, UniRefGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
					T, TT, UniRefGraph<I, RV, RVT, RE, RET>
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
		public final UniRefGraph<I, RV, RVT, RE, RET> graph() {
			return UniRefGraph.this;
		}
	}


}
