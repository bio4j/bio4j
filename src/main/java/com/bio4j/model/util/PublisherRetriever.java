package com.bio4j.model.util;

import com.bio4j.model.nodes.citation.Publisher;

public interface PublisherRetriever extends NodeRetriever<Publisher> {

  public Publisher getPublisherByName(String publisherName);

}
