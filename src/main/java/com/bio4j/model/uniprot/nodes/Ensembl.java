package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ProteinEnsembl;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 7/4/2014.
 */
public interface Ensembl <
		N extends Ensembl<N, NT>,
		NT extends UniprotGraph.EnsemblType<N, NT>
		>
		extends Node<N, NT> {

	public String proteinSequenceId();
	public String id();
	public String moleculeId();
	public String geneId();

	public static interface id<
			N extends Ensembl<N, NT>,
			NT extends UniprotGraph.EnsemblType<N, NT>,
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
			N extends Ensembl<N, NT>,
			NT extends UniprotGraph.EnsemblType<N, NT>,
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

	public static interface moleculeId<
			N extends Ensembl<N, NT>,
			NT extends UniprotGraph.EnsemblType<N, NT>,
			P extends moleculeId<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "moleculeId";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface geneId<
			N extends Ensembl<N, NT>,
			NT extends UniprotGraph.EnsemblType<N, NT>,
			P extends geneId<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "geneId";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinEnsembl
	// ingoing
	public List<? extends ProteinEnsembl> proteinEnsembl_in();
	public List<? extends Protein> proteinEnsembl_inNodes();
}
