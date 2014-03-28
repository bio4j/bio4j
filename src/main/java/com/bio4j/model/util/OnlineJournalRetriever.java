package com.bio4j.model.util;

import com.bio4j.model.uniprot.nodes.OnlineJournal;

public interface OnlineJournalRetriever extends NodeRetriever<OnlineJournal> {

  public OnlineJournal getOnlineJournalByName(String onlineJournalName);

}
