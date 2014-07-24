package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.nodes.Book;
import com.bio4j.model.uniprot.nodes.Reference;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 7/24/2014.
 */
public interface ReferenceBook <
		S extends Reference<S, ST>, ST extends UniprotGraph.ReferenceType<S, ST>,
		R extends ReferenceBook<S, ST, R, RT, T, TT>, RT extends UniprotGraph.ReferenceBookType<S, ST, R, RT, T, TT>,
		T extends Book<T, TT>, TT extends UniprotGraph.BookType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
