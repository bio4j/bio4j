package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.relationships.ArticleJournal;
import com.bio4j.model.uniprot.relationships.ProteinDataset;
import com.ohnosequences.typedGraphs.UntypedGraph;

import java.util.List;

/**
 * Created by ppareja on 7/23/2014.
 */
public final class Journal <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Journal<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.JournalType,
		I, RV, RVT, RE, RET
		> {

	public Journal(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.JournalType type) {
		super(vertex, type);
	}

	@Override
	public Journal<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String name() {
		return get(type().name);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// articleJournal
	// ingoing
	public List<ArticleJournal<I, RV, RVT, RE, RET>> articleJournal_in(){
		return inMany(graph().ArticleJournal());
	}
	public List<Article<I, RV, RVT, RE, RET>> articleJournal_inNodes(){
		return inManyV(graph().ArticleJournal());
	}


}
