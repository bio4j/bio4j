package com.bio4j.model.uniprot_go.relationships;

import com.bio4j.model.go.GoGraph.TermType;
import com.bio4j.model.go.nodes.GoTerm;
import com.bio4j.model.uniprot.nodes.Protein;
import com.bio4j.model.uniprot_go.UniprotGoGraph.GoAnnotationType;
import com.bio4j.model.uniprot.UniprotGraph.ProteinType;
import com.ohnosequences.typedGraphs.Relationship;

/**
 * Created by ppareja on 6/25/2014.
 */
public interface GoAnnotation <
		S extends Protein<S,ST>, ST extends ProteinType<S,ST>,
		R extends GoAnnotation<S,ST,R,RT,T,TT>, RT extends GoAnnotationType<S,ST,R,RT,T,TT>,
		T extends GoTerm<T,TT>, TT extends TermType<T,TT>
		>
		extends Relationship<S,ST,R,RT,T,TT>
{}
