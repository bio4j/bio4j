package com.bio4j.model.util;

import com.bio4j.model.nodes.citation.Journal;

public interface JournalRetriever extends NodeRetriever<Journal> {

  public Journal getJournalByName(String journalName);

}
