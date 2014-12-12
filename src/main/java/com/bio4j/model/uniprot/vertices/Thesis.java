package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.ReferenceThesis;
import com.bio4j.model.uniprot.edges.ThesisInstitute;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Optional;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public final class Thesis<I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniProtGraph.UniprotVertex<
		Thesis<I, RV, RVT, RE, RET>,
		UniProtGraph<I, RV, RVT, RE, RET>.ThesisType,
		I, RV, RVT, RE, RET
		>  {

	public Thesis(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ThesisType type) {
		super(vertex, type);
	}

	@Override
	public Thesis<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String title() {
		return get(type().title);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// referenceThesis
	// ingoing
	public ReferenceThesis<I, RV, RVT, RE, RET> referenceThesis_in(){
		return inOne(graph().ReferenceThesis());
	}
	public Reference<I, RV, RVT, RE, RET> referenceThesis_inV(){
        return inOneV(graph().ReferenceThesis());
	}
    // thesisInstitute
    // outgoing
    public Optional<ThesisInstitute<I, RV, RVT, RE, RET>> thesisInstitute_out(){
        return outOneOptional(graph().ThesisInstitute());
    }
    public Optional<Institute<I, RV, RVT, RE, RET>> thesisInstitute_outV(){
        return outOneOptionalV(graph().ThesisInstitute());
    }
}
