package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.enzymedb.nodes.Enzyme;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.relationships.*;
import com.bio4j.model.uniprot_enzymedb.relationships.EnzymaticActivity;
import com.bio4j.model.uniprot_uniref.relationships.*;
import com.bio4j.model.uniref.nodes.UniRef100Cluster;
import com.bio4j.model.uniref.nodes.UniRef50Cluster;
import com.bio4j.model.uniref.nodes.UniRef90Cluster;
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
	public String createdDate();
	public int version();
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

	public static interface createdDate<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends createdDate<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "createdDate";
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

	public static interface version<
			N extends Protein<N, NT>,
			NT extends ProteinType<N, NT>,
			P extends version<N, NT, P>
			>
			extends Property<N, NT, P, Integer> {
		@Override
		public default String name() {
			return "version";
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

	// enzymaticActivity
	// outgoing
	public List<? extends EnzymaticActivity>  enzymaticActivity_out();
	public List<? extends Enzyme>  enzymaticActivity_outNodes();

	// uniref50Member
	// ingoing
	public <T extends UniRef50Member> T  uniref50Member_in();
	public <T extends UniRef50Cluster> T uniref50Member_inNode();

	// uniref50Representant
	// ingoing
	public <T extends UniRef50Representant> T  uniref50Representant_in();
	public <T extends UniRef50Cluster> T uniref50Representant_inNode();

	// uniref90Member
	// ingoing
	public <T extends UniRef90Member> T  uniref90Member_in();
	public <T extends UniRef90Cluster> T uniref90Member_inNode();

	// uniref90Representant
	// ingoing
	public <T extends UniRef90Representant> T  uniref90Representant_in();
	public <T extends UniRef90Cluster> T uniref90Representant_inNode();

	// uniref100Member
	// ingoing
	public <T extends UniRef100Member> T  uniref100Member_in();
	public <T extends UniRef100Cluster> T uniref100Member_inNode();

	// uniref90Representant
	// ingoing
	public <T extends UniRef100Representant> T  uniref100Representant_in();
	public <T extends UniRef100Cluster> T uniref100Representant_inNode();

	// proteinReference
	// outgoing
	public List<? extends ProteinReference>  proteinReference_out();
	public List<? extends Reference> proteinReference_outNodes();


}
