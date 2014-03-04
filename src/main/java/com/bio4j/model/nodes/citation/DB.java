
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Vertex;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface DB extends Vertex {
    
    //----GETTERS---
    public String getName();
    public List<Submission> getAssociatedSubmissions();
    
    //----SETTERS----
    public void setName(String value);
    
}
