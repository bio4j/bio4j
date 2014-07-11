package com.bio4j.model.enzymedb.nodes;

import com.bio4j.model.enzymedb.EnzymeDBGraph.EnzymeType;
import com.bio4j.model.enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.go.relationships.*;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 6/17/2014.
 */
public interface Enzyme<
		N extends Enzyme<N, NT>,
		NT extends EnzymeType<N, NT>
		>
		extends Node<N, NT>  {

	public String id();
	public String[] cofactors();
	public String officialName();
	//public String alternateNames();
	public String catalyticActivity();
	public String comment();
	public String[] prositeCrossReferences();

	// properties
	public static interface id<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>,
			P extends id<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "id";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface cofactors<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>,
			P extends cofactors<N, NT, P>
			>
			extends Property<N, NT, P, String[]> {
		@Override
		public default String name() {
			return "cofactors";
		}

		@Override
		public default Class<String[]> valueClass() {
			return String[].class;
		}
	}

	public static interface officialName<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>,
			P extends officialName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "officialName";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

//	public static interface alternateNames<
//			N extends Enzyme<N, NT>,
//			NT extends EnzymeType<N, NT>,
//			P extends alternateNames<N, NT, P>
//			>
//			extends Property<N, NT, P, String> {
//		@Override
//		public default String name() {
//			return "alternateNames";
//		}
//
//		@Override
//		public default Class<String> valueClass() {
//			return String.class;
//		}
//	}

	public static interface catalyticActivity<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>,
			P extends catalyticActivity<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "catalyticActivity";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface comment<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>,
			P extends comment<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "comment";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface prositeCrossReferences<
			N extends Enzyme<N, NT>,
			NT extends EnzymeType<N, NT>,
			P extends prositeCrossReferences<N, NT, P>
			>
			extends Property<N, NT, P, String[]> {
		@Override
		public default String name() {
			return "prositeCrossReferences";
		}

		@Override
		public default Class<String[]> valueClass() {
			return String[].class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// enzymaticActivity
    // incoming
    public List<? extends EnzymaticActivity> enzymaticActivity_in();
    public List<? extends Protein> enzymaticActivity_inNodes();


}
