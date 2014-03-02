
package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.nodes.citation.Book;
import com.bio4j.model.nodes.citation.Publisher;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BookPublisher extends Relationship {
    
    //-------GETTERS-------------
    public Book getBook();
    public Publisher getPublisher();
    
}
