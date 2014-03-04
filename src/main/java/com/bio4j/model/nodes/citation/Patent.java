
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Vertex;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Patent extends Vertex {
    
    //----GETTERS---
    public String getNumber();
    public String getDate();
    public String getTitle();
    public List<Person> getAuthors();      
    public List<Protein> getProteinCitations();

    //----SETTERS---
    public void setNumber(String value);
    public void setDate(String value);
    public void setTitle(String value);    
    
}
