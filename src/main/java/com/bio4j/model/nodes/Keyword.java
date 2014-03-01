package com.bio4j.model.nodes;

import com.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Keyword extends Node{
    
    //----GETTERS---
    public String getId();
    public String getName();

    //----SETTERS----
    public void setId(String value);
    public void setName(String value);
    
}
