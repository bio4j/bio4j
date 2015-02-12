package com.bio4j.model.enzymedb;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.uniprot_enzymedb.UniProtEnzymeDBGraph;
import com.bio4j.angulillos.*;

/*

# ExPASy Enzyme DB graph

This graph includes all Enzyme terms that are included in the ExPASy ENZYME database but **not** those that have been either _transferred_ or _deleted_.

You can get more information about the Enzyme database from its [website](http://enzyme.expasy.org/), in particular

- [Enzyme description and sample entry](http://enzyme.expasy.org/enzyme_details.html)
- [User manual](http://enzyme.expasy.org/enzuser.txt) which contains a more precise description of the data model

## data model

It only consists of the vertices of `Enzyme` type; There are other graphs which add edges between `Enzyme`s and other entitities such as proteins.

### Enzymes

We have a `Enzyme` vertex which contains as properties the data present for each enzyme term:

- id
- official name
- cofactors _(stored as a flatten String array)_
- prosite cross references _(stored as a flatten String array)_
- catalytic activity
- comment

 */
public abstract class EnzymeDBGraph <
	I extends UntypedGraph<RV,RVT,RE,RET>,
	RV,RVT,
	RE,RET
>
implements
	TypedGraph <
		EnzymeDBGraph<I,RV,RVT,RE,RET>,
		I,RV,RVT,RE,RET
	>
{

	protected I raw = null;
  public I raw() { return raw; }

  public EnzymeDBGraph(I graph) {
			
		this.raw = graph;
  }

/*

### Indices

`Enzyme`s are indexed for unique id matches.

*/
	public abstract TypedVertexIndex.Unique <
		Enzyme<I,RV,RVT,RE,RET>, EnzymeType,
		EnzymeType.id, String,
		EnzymeDBGraph<I,RV,RVT,RE,RET>,
		I,RV,RVT,RE,RET
	>
	enzymeIdIndex();

/*

### Extensions

You can extend the EnzymeDB graph with a graph adding an edge to UniProt proteins. See [UniProtEnzymeDBGraph](../uniprot_enzymedb/UniProtEnzymeDBGraph.java) for more.

*/
	public abstract UniProtEnzymeDBGraph<I,RV,RVT,RE,RET> uniProtEnzymeDBGraph();

/*

### Types

This graph has only vertices.

#### Vertices 

One vertex type for enzyme vertices:

##### EnzymeType

*/
	public abstract EnzymeType Enzyme();

	public final class EnzymeType
	extends
		EnzymeDBVertexType <
			Enzyme<I,RV,RVT,RE,RET>,
			EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeType
		>
	{

/*

##### EnzymeType Properties

*/
		public final id id = new id();
		public final cofactors cofactors = new cofactors();
		public final comment comment = new comment();
		public final officialName officialName = new officialName();
		public final alternateNames alternateNames = new alternateNames();
		public final catalyticActivity catalyticActivity = new catalyticActivity();
		public final prositeCrossReferences prositeCrossReferences = new prositeCrossReferences();

		public EnzymeType(RVT raw) { super(raw); }

		public final EnzymeType value() { return graph().Enzyme(); }
		public final Enzyme<I,RV,RVT,RE,RET> from(RV vertex) { return new Enzyme<I,RV,RVT,RE,RET>(vertex, this); }

		public final class id
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,id,String>
		{
			public id() { super(EnzymeType.this); }
			public final Class<String> valueClass() { return String.class; }
		}

		public final class cofactors
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,cofactors,String[]>
		{
			public cofactors() { super(EnzymeType.this); }
			public final Class<String[]> valueClass() { return String[].class; }
		}

		public final class comment
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,comment,String>
		{
			public comment() { super(EnzymeType.this); }
			public final Class<String> valueClass() { return String.class; }
		}

		public final class officialName
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,officialName,String>
		{
			public officialName() { super(EnzymeType.this); }
			public final Class<String> valueClass() { return String.class; }
		}

		public final class alternateNames
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,alternateNames,String[]>
		{
			public alternateNames() { super(EnzymeType.this); }
			public final Class<String[]> valueClass() { return String[].class; }
		}

		public final class catalyticActivity
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,catalyticActivity,String>
		{
			public catalyticActivity() { super(EnzymeType.this); }
			public final Class<String> valueClass() { return String.class; }
		}

		public final class prositeCrossReferences
		extends
			EnzymeDBVertexProperty<Enzyme<I,RV,RVT,RE,RET>,EnzymeType,prositeCrossReferences,String[]>
		{
			public prositeCrossReferences() { super(EnzymeType.this); }
			public final Class<String[]> valueClass() { return String[].class; }
		}
	}


/*

### Helper classes

These classes are used to bound all the types and implementation that is generic for every type of this graph: the `graph()` reference of types, vertex, property, edge types should be members of this graph, etc.

*/
	public abstract static class EnzymeDBVertex <
		V extends EnzymeDBVertex<V,VT,I,RV,RVT,RE,RET>,
		VT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<V,VT>,
		I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET
	>
	implements
		TypedVertex<V,VT,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
	{

		private RV vertex;
		private VT type;

		protected EnzymeDBVertex(RV vertex, VT type) {
			this.vertex = vertex;
			this.type = type;
		}

		@Override
		public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }

		@Override
		public final RV raw() { return this.vertex; }

		@Override
		public final VT type() { return type; }
	}

	public abstract class EnzymeDBVertexType <
		V extends EnzymeDBVertex<V,VT,I,RV,RVT,RE,RET>,
		VT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<V,VT>
	>
	implements
		TypedVertex.Type<V,VT,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
	{

		private RVT raw;

		protected EnzymeDBVertexType(RVT raw) { this.raw = raw; }

		@Override
		public final RVT raw() { return raw; }

		@Override
		public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() {	return EnzymeDBGraph.this; }
	}



	public abstract class EnzymeDBVertexProperty <
		V extends EnzymeDBVertex<V,VT,I,RV,RVT,RE,RET>,
		VT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<V,VT>,
		P extends EnzymeDBVertexProperty<V,VT,P,PV>,
		PV
	>
	implements
		Property<V,VT,P,PV,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET>
	{

		private VT type;

		protected EnzymeDBVertexProperty(VT type) { this.type = type; }

		@Override
		public final VT elementType() { return type; }
	}

	

	public abstract static class EnzymeDBEdge <
		S extends EnzymeDBVertex<S, ST,I,RV,RVT,RE,RET>,
		ST extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<S, ST>,
		E extends EnzymeDBEdge<S,ST,E,ET,T,TT,I,RV,RVT,RE,RET>,
		ET extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBEdgeType<S,ST,E,ET,T,TT>,
		T extends EnzymeDBVertex<T, TT,I,RV,RVT,RE,RET>,
		TT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<T, TT>,
		I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
	>
	implements
		TypedEdge <
			S,ST,EnzymeDBGraph<I,RV,RVT,RE,RET>,
			E,ET,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET,
			T,TT,EnzymeDBGraph<I,RV,RVT,RE,RET>
		>
	{

		private RE edge;
		private ET type;

		protected EnzymeDBEdge(RE edge, ET type) {

			this.edge = edge;
			this.type = type;
		}

		@Override
		public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }

		@Override
		public RE raw() { return this.edge; }

		@Override
		public ET type() { return type; }
	}

	public abstract class EnzymeDBEdgeType <
		S extends EnzymeDBVertex<S, ST,I,RV,RVT,RE,RET>,
		ST extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<S, ST>,
		E extends EnzymeDBEdge<S,ST,E,ET,T,TT,I,RV,RVT,RE,RET>,
		ET extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBEdgeType<S,ST,E,ET,T,TT>,
		T extends EnzymeDBVertex<T, TT,I,RV,RVT,RE,RET>,
		TT extends EnzymeDBGraph<I,RV,RVT,RE,RET>.EnzymeDBVertexType<T, TT>
	>
	implements
		TypedEdge.Type<
			S,ST,EnzymeDBGraph<I,RV,RVT,RE,RET>,
			E,ET,EnzymeDBGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET,
			T,TT,EnzymeDBGraph<I,RV,RVT,RE,RET>
		>
	{

		private RET raw;
		private ST srcT;
		private TT tgtT;

		protected EnzymeDBEdgeType(ST srcT, RET raw, TT tgtT) {

			this.raw = raw;
			this.srcT = srcT;
			this.tgtT = tgtT;
		}

		@Override
		public final ST sourceType() { return srcT; }

		@Override
		public final TT targetType() { return tgtT; }

		@Override
		public final RET raw() { return raw; }

		@Override
		public final EnzymeDBGraph<I,RV,RVT,RE,RET> graph() { return EnzymeDBGraph.this; }
	}
}