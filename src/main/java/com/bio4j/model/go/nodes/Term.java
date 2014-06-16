package com.bio4j.model.go.nodes;

import com.bio4j.model.go.GoGraph.TermType;
import com.bio4j.model.go.relationships.*;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;

import java.util.List;

// base types
// relationships

// goAnnotation
// import com.bio4j.model.uniprot_go.relationships.GoAnnotation;
// import com.bio4j.model.uniprot.nodes.Protein;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface Term<
		N extends Term<N, NT>,
		NT extends TermType<N, NT>
		>
		extends Node<N, NT> {

	public String id();
	public String name();
	public String comment();
	public String obsolete();
	public String definition();
	public String synonym();

	// properties
	public static interface id<
			N extends Term<N, NT>,
			NT extends TermType<N, NT>,
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

	public static interface name<
			N extends Term<N, NT>,
			NT extends TermType<N, NT>,
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

	public static interface synonym<
			N extends Term<N, NT>,
			NT extends TermType<N, NT>,
			P extends synonym<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "synonym";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface definition<
			N extends Term<N, NT>,
			NT extends TermType<N, NT>,
			P extends definition<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "definition";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface comment<
			N extends Term<N, NT>,
			NT extends TermType<N, NT>,
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

	public static interface obsolete<
			N extends Term<N, NT>,
			NT extends TermType<N, NT>,
			P extends obsolete<N, NT, P>
			>
			extends Property<N, NT, P, String> {
		@Override
		public default String name() {
			return "obsolete";
		}

		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// SubOntologies

	// SubOntology
	// outgoing
	public SubOntology subOntology_out();

	public SubOntologies subOntology_outNodes();

	// isA
	// incoming
	public List<? extends IsA> isA_in();

	public List<? extends Term> isA_inNodes();

	// outgoing
	public List<? extends IsA> isA_out();

	public List<? extends Term> isA_outNodes();

	// regulates
	// incoming
	public List<? extends Regulates> regulates_in();

	public List<? extends Term> regulates_inNodes();

	// outgoing
	public List<? extends Regulates> regulates_out();

	public List<? extends Term> regulates_outNodes();

	// negativelyRegulates
	// incoming
	public List<? extends NegativelyRegulates> negativelyRegulates_in();

	public List<? extends Term> negativelyRegulates_inNodes();

	// outgoing
	public List<? extends NegativelyRegulates> negativelyRegulates_out();

	public List<? extends Term> negativelyRegulates_outNodes();


	// positivelyRegulates
	// incoming
	public List<? extends PositivelyRegulates> positivelyRegulates_in();

	public List<? extends Term> positivelyRegulates_inNodes();

	// outgoing
	public List<? extends PositivelyRegulates> positivelyRegulates_out();

	public List<? extends Term> positivelyRegulates_outNodes();

	// partOf
	// incoming
	public List<? extends PartOf> partOf_in();

	public List<? extends Term> partOf_inNodes();

	// outgoing
	public List<? extends PartOf> partOf_out();

	public List<? extends Term> partOf_outNodes();

	// hasPartOf
	// incoming
	public List<? extends HasPartOf> hasPartOf_in();

	public List<? extends Term> hasPartOf_inNodes();

	// outgoing
	public List<? extends HasPartOf> hasPartOf_out();

	public List<? extends Term> hasPartOf_outNodes();


	///////////////////////// extras ////////////////////////////////////

	// goAnnotation
	// incoming
	// public List<? extends GoAnnotation> goAnnotation_in();
	// public List<? extends Protein> goAnnotation_inNodes();
}
