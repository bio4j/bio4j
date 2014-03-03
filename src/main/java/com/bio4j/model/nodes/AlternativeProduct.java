package com.bio4j.model.nodes;

import java.util.List;
import com.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface AlternativeProduct extends Node {
    
    //----GETTERS---
    public String getName();

    //----SETTERS----
    public void setName(String value);  
    
    public List<Isoform> getIsoforms();
}
