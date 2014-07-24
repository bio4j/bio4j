package com.bio4j.model.uniprot.relationships;

import com.bio4j.model.uniprot.UniprotGraph.ProteinKeywordType;
import com.bio4j.model.uniprot.UniprotGraph.KeywordType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.bio4j.model.uniprot.nodes.Keyword;
import com.bio4j.model.uniprot.nodes.Protein;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/30/2014.
 */
public interface ProteinKeyword <
		S extends Protein<S, ST>, ST extends ProteinType<S, ST>,
		R extends ProteinKeyword<S, ST, R, RT, T, TT>, RT extends ProteinKeywordType<S, ST, R, RT, T, TT>,
		T extends Keyword<T, TT>, TT extends KeywordType<T, TT>
		>
		extends Relationship<S, ST, R, RT, T, TT> {
}
