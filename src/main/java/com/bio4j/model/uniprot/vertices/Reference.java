package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.angulillos.UntypedGraph;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ppareja on 7/29/2014.
 */
public final class Reference <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
		extends UniprotGraph.UniprotVertex<
		Reference<I, RV, RVT, RE, RET>,
		UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType,
		I, RV, RVT, RE, RET
		> {

	public Reference(RV vertex, UniprotGraph<I, RV, RVT, RE, RET>.ReferenceType type) {
		super(vertex, type);
	}

	@Override
	public Reference<I, RV, RVT, RE, RET> self() {
		return this;
	}

	// properties
	public String date() {
		return get(type().date);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////

	// relationships

	// proteinReference
	// ingoing
	public Stream<ProteinReference<I, RV, RVT, RE, RET>> proteinReference_in(){		return inMany(graph().ProteinReference());	}
	public Stream<Protein<I, RV, RVT, RE, RET>> proteinReference_inV(){
		return inManyV(graph().ProteinReference());
	}
	// referenceArticle
	// outgoing
	public Optional<ReferenceArticle<I, RV, RVT, RE, RET>> referenceArticle_out(){		return outOneOptional(graph().ReferenceArticle());	}
	public Optional<Article<I, RV, RVT, RE, RET>> referenceArticle_outV(){		return outOneOptionalV(graph().ReferenceArticle());	}
	// referenceBook
	// outgoing
	public Optional<ReferenceBook<I, RV, RVT, RE, RET>> referenceBook_out(){		return outOneOptional(graph().ReferenceBook());	}
	public Optional<Book<I, RV, RVT, RE, RET>> referenceBook_outV(){		return outOneOptionalV(graph().ReferenceBook());	}
	// referenceOnlineArticle
	// outgoing
	public Optional<ReferenceOnlineArticle<I, RV, RVT, RE, RET>> referenceOnlineArticle_out(){		return outOneOptional(graph().ReferenceOnlineArticle());	}
	public Optional<OnlineArticle<I, RV, RVT, RE, RET>> referenceOnlineArticle_outV(){		return outOneOptionalV(graph().ReferenceOnlineArticle());	}
	// referencePatent
	// outgoing
	public Optional<ReferencePatent<I, RV, RVT, RE, RET>> referencePatent_out(){		return outOneOptional(graph().ReferencePatent());	}
	public Optional<Patent<I, RV, RVT, RE, RET>> referencePatent_outV(){		return outOneOptionalV(graph().ReferencePatent());	}
	// referenceSubmission
	// outgoing
	public Optional<ReferenceSubmission<I, RV, RVT, RE, RET>> referenceSubmission_out(){		return outOneOptional(graph().ReferenceSubmission());	}
	public Optional<Submission<I, RV, RVT, RE, RET>> referenceSubmission_outV(){		return outOneOptionalV(graph().ReferenceSubmission());	}
	// referenceThesis
	// outgoing
	public Optional<ReferenceThesis<I, RV, RVT, RE, RET>> referenceThesis_out(){		return outOneOptional(graph().ReferenceThesis());	}
	public Optional<Thesis<I, RV, RVT, RE, RET>> referenceThesis_outV(){		return outOneOptionalV(graph().ReferenceThesis());	}
	// referenceUnpublishedObservation
	// outgoing
	public Optional<ReferenceUnpublishedObservation<I, RV, RVT, RE, RET>> referenceUnpublishedObservation_out(){		return outOneOptional(graph().ReferenceUnpublishedObservation());	}
	public Optional<UnpublishedObservation<I, RV, RVT, RE, RET>> referenceUnpublishedObservation_outV(){		return outOneOptionalV(graph().ReferenceUnpublishedObservation());	}
	// referenceAuthorConsortium
	// outgoing
	public Optional<Stream<ReferenceAuthorConsortium<I, RV, RVT, RE, RET>>> referenceAuthorConsortium_out(){		return outManyOptional(graph().ReferenceAuthorConsortium());	}
	public Optional<Stream<Consortium<I, RV, RVT, RE, RET>>> referenceAuthorConsortium_outV(){		return outManyOptionalV(graph().ReferenceAuthorConsortium());	}
	// referenceAuthorPerson
	// outgoing
	public Optional<Stream<ReferenceAuthorPerson<I, RV, RVT, RE, RET>>> referenceAuthorPerson_out(){		return outManyOptional(graph().ReferenceAuthorPerson());	}
	public Optional<Stream<Person<I, RV, RVT, RE, RET>>> referenceAuthorPerson_outV(){		return outManyOptionalV(graph().ReferenceAuthorPerson());	}



}
