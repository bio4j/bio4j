package com.bio4j.model.util;

import java.util.List;
import com.bio4j.model.nodes.citation.Book;

public interface BookRetriever extends NodeRetriever<Book> {

  public List<? extends Book> getBooksByName(String bookName);

}
