package com.bio4j.model.nodes;

import com.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
// this is uniprot taxon in contrast to the NCBI taxon
public interface Taxon extends Node {
    
    //----GETTERS---
    public String getName();
    
    //----SETTERS----
    public void setName(String value); 
    
}
