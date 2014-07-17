package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.CommentType;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/17/2014.
 */
public interface ProteinComment <
		S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
		R extends ProteinComment<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinCommentType<S, ST, R, RT, T, TT>,
		T extends CommentType<T, TT>, TT extends UniprotGraph.CommentTypeType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {

	// properties

	public static interface text<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinComment<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinCommentType<S, ST, R, RT, T, TT>,
			T extends CommentType<T, TT>, TT extends UniprotGraph.CommentTypeType<T, TT>,
			P extends text<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "text";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface evidence<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinComment<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinCommentType<S, ST, R, RT, T, TT>,
			T extends CommentType<T, TT>, TT extends UniprotGraph.CommentTypeType<T, TT>,
			P extends evidence<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "evidence";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

	public static interface status<
			S extends Protein<S, ST>, ST extends UniprotGraph.ProteinType<S, ST>,
			R extends ProteinComment<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ProteinCommentType<S, ST, R, RT, T, TT>,
			T extends CommentType<T, TT>, TT extends UniprotGraph.CommentTypeType<T, TT>,
			P extends status<S, ST, R, RT, T, TT,  P>
			>
			extends Property<R, RT, P, String> {
		@Override
		public default String name() {
			return "status";
		}
		@Override
		public default Class<String> valueClass() {
			return String.class;
		}
	}

}
