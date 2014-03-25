package com.bio4j.model.util;

import com.bio4j.model.nodes.citation.Submission;

public interface SubmissionRetriever extends NodeRetriever<Submission> {

  public Submission getSubmissionByTitle(String submissionTitle);

}
