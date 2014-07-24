package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinEMBL;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/3/2014.
 */
public interface EMBL<
		N extends EMBL<N, NT>,
		NT extends UniprotGraph.EMBLType<N, NT>
		>
		extends Node<N, NT> {

	public String proteinSequenceId();
	public String id();
	public String moleculeType();

	public static interface id<
			N extends EMBL<N, NT>,
			NT extends UniprotGraph.EMBLType<N, NT>,
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

	public static interface proteinSequenceId<
			N extends EMBL<N, NT>,
			NT extends UniprotGraph.EMBLType<N, NT>,
			P extends proteinSequenceId<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "proteinSequenceId";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface moleculeType<
			N extends EMBL<N, NT>,
			NT extends UniprotGraph.EMBLType<N, NT>,
			P extends moleculeType<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "moleculeType";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinEMBL
	// ingoing
	public List<? extends ProteinEMBL> proteinEMBL_in();
	public List<? extends Protein> proteinEMBL_inNodes();
}
