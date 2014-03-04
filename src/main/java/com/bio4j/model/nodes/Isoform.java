package com.bio4j.model.nodes;

import com.bio4j.model.Vertex;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Isoform extends Vertex {
    
    //----GETTERS---
    public String getId();
    public String getNote();
    public String getName();
    public String getSequence();

    //----SETTERS----
    public void setId(String value);
    public void setNote(String value);
    public void setName(String value);
    public void setSequence(String value);  
}
