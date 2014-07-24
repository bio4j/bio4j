package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinRefSeq;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/14/2014.
 */
public interface RefSeq<
		N extends RefSeq<N, NT>,
		NT extends UniprotGraph.RefSeqType<N, NT>
		>
		extends Node<N, NT> {

	public String id();
	public String nucleotideSequenceId();

	// properties
	public static interface id<
			N extends RefSeq<N, NT>,
			NT extends UniprotGraph.RefSeqType<N, NT>,
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

	public static interface nucleotideSequenceId<
			N extends RefSeq<N, NT>,
			NT extends UniprotGraph.RefSeqType<N, NT>,
			P extends nucleotideSequenceId<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "nucleotideSequenceId";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinRefSeq
	// ingoing
	public List<? extends ProteinRefSeq> proteinRefSeq_in();
	public List<? extends Protein> proteinRefSeq_inNodes();
}
