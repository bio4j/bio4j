
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;

import java.util.List;

import com.bio4j.model.NodeType;
import com.bio4j.model.properties.Name;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface DB extends Node<DB, DB.Type>,
	Name<DB,DB.Type>{
	
	public static Type TYPE = Type.INSTANCE;
    
	enum Type implements NodeType<DB, DB.Type> {
	
	    db;
	    public Type value() { return db; }
	    public static Type INSTANCE = db;
	}    
	
	
    public List<Submission> getAssociatedSubmissions();
    
    
}
