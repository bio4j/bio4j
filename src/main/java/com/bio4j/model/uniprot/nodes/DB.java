
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;

import java.util.List;

import com.bio4j.model.NodeType;
import com.bio4j.model.uniprot.nodes.Book.Type;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface DB extends Node<DB, DB.Type> {
    
	enum Type implements NodeType<DB, DB.Type> {
	
	    db;
	    public Type value() { return db; }
	}    
    //----GETTERS---
    public String getName();
    public List<Submission> getAssociatedSubmissions();
    
    //----SETTERS----
    public void setName(String value);
    
}
