package com.bio4j.model.nodes;

import com.bio4j.model.Vertex;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Person extends Vertex {
    
    //----GETTERS---
    public String getName();
    
    //----SETTERS----
    public void setName(String value);    
    
}
