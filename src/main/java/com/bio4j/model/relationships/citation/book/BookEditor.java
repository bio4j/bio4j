
package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.citation.Book;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BookEditor extends Edge {
    
    //------GETTERS------
    public Book getBook();
    public Person getEditor();
}
