package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinComment;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/17/2014.
 */
public interface CommentType <
		N extends CommentType<N, NT>,
		NT extends UniprotGraph.CommentTypeType<N, NT>
		>
		extends Node<N, NT> {

	public String name();

	// properties

	public static interface name<
			N extends CommentType<N, NT>,
			NT extends UniprotGraph.CommentTypeType<N, NT>,
			P extends name<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "name";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinComment
	// ingoing
	public List<? extends ProteinComment> proteinComment_in();
	public List<? extends Protein> proteinComment_inNodes();


}
