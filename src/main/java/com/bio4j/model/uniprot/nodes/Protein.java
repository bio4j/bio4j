package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.relationships.*;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

/**
 * Created by ppareja on 6/18/2014.
 */
public interface Protein<
		N extends Protein<N, NT>,
		NT extends ProteinType<N, NT>
		>
		extends Node<N, NT>  {

	public String name();
	public String sequence();
	public String fullName();
	public String shortName();
	public String accession();
	public String modifiedDate();
	public String mass();
	public int length();

	// properties
	public static interface name<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
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

	public static interface sequence<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends sequence<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "sequence";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface fullName<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends fullName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "fullName";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface shortName<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends shortName<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "shortName";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface accession<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends accession<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "accession";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface modifiedDate<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends modifiedDate<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "modifiedDate";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface mass<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends mass<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "mass";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface length<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends length<N, NT, P>
			>
			extends Property<N, NT, P, Integer> {
		@Override
		public default String name() {
			return "length";
		}
		@Override
		public default Class<Integer> valueClass() {
			return Integer.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinOrganism
	// outgoing
	public <T extends ProteinOrganism> T proteinOrganism_out();
	public <T extends Organism> T proteinOrganism_outNodes();

	// proteinDataset
	// outgoing
	public <T extends ProteinDataset> T  proteinDataset_out();
	public <T extends Dataset> T proteinDataset_outNodes();

	// proteinInterpro
	// outgoing
	public List<? extends ProteinInterpro>  proteinIntepro_out();
	public List<? extends Interpro> proteinInterpro_outNodes();

	// proteinReactomeTerm
	// outgoing
	public List<? extends ProteinReactomeTerm>  proteinReactomeTerm_out();
	public List<? extends ReactomeTerm> proteinReactomeTerm_outNodes();

	// proteinKeyword
	// outgoing
	public List<? extends ProteinKeyword>  proteinKeyword_out();
	public List<? extends Keyword> proteinKeyword_outNodes();


}
