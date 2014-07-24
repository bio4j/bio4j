package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticlePubmed;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

/**
 * Created by ppareja on 7/23/2014.
 */
public interface UnpublishedObservation <
		N extends UnpublishedObservation<N, NT>,
		NT extends UniprotGraph.UnpublishedObservationType<N, NT>
		>
		extends Node<N, NT> {

	public String date();

	// properties

	public static interface date<
			N extends UnpublishedObservation<N, NT>,
			NT extends UniprotGraph.UnpublishedObservationType<N, NT>,
			P extends date<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "date";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}


	//////////////////////////////////////////////////////////////////////////////////////////////

}
