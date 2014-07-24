package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.*;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.Date;
import java.util.List;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface Reference <
		N extends Reference<N, NT>,
		NT extends UniprotGraph.ReferenceType<N, NT>
		>
		extends Node<N, NT> {

	public String date();

	// properties
	public static interface date<
			N extends Reference<N, NT>,
			NT extends UniprotGraph.ReferenceType<N, NT>,
			P extends date<N, NT, P>
			>
			extends Property<N, NT, P, Date> {
		@Override
		public default String name() {
			return "date";
		}
		@Override
		public default Class<Date> valueClass() {
			return Date.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinReference
	// ingoing
	public List<? extends ProteinReference> proteinReference_in();
	public List<? extends Protein> proteinReference_inNodes();
	// referenceBook
	// outgoing
	public <T extends ReferenceBook> T referenceBook_out();
	public <T extends Book> T referenceBook_outNode();
	// referenceArticle
	// outgoing
	public <T extends ReferenceArticle> T referenceArticle_out();
	public <T extends Article> T referenceArticle_outNode();
	// referenceOnlineArticle
	// outgoing
	public <T extends ReferenceOnlineArticle> T referenceOnlineArticle_out();
	public <T extends OnlineArticle> T referenceOnlineArticle_outNode();
	// referenceThesis
	// outgoing
	public <T extends ReferenceThesis> T referenceThesis_out();
	public <T extends Thesis> T referenceThesis_outNode();
	// referencePatent
	// outgoing
	public <T extends ReferencePatent> T referencePatent_out();
	public <T extends Patent> T referencePatent_outNode();
	// referenceUnpublishedObservation
	// outgoing
	public <T extends ReferenceUnpublishedObservation> T referenceUnpublishedObservation_out();
	public <T extends UnpublishedObservation> T referenceUnpublishedObservation_outNode();

}
