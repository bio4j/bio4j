
package com.bio4j.model.relationships.citation.book;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.nodes.citation.Book;
import com.bio4j.model.Edge;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BookProteinCitation extends Edge {
    
    //-------GETTERS-------------
    public String getTitle();
    public String getVolume();
    public String getFirst();
    public String getLast();
    public Book getBook();    
    public Protein getProtein();

    //-------SETTERS---------------
    public void setTitle(String value);
    public void setVolume(String value);
    public void setFirst(String value);
    public void setLast(String value);    
    
}
