package com.bio4j.model.nodes;

import com.bio4j.model.Node;

import com.bio4j.model.nodes.citation.Book;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface City extends Node{
    
    //----GETTERS---
    public String getName();
    public List<Book> getBooks();

    //----SETTERS----
    public void setName(String value);     
    
    
}
