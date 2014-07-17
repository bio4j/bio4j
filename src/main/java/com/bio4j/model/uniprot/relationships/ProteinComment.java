package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.CommentType;
import com.bio4j.model.uniprot.nodes.Protein;
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
}
