
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Vertex;
import com.bio4j.model.nodes.City;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Book extends Vertex {
    
    //----GETTERS---
    public String getName();
    public String getDate();
    public List<Protein> getProteinCitations();
    public Publisher getPublisher();
    public City getCity();
    public List<Person> getAuthors();
    public List<Person> getEditors();

    //----SETTERS----
    public void setName(String value);
    public void setDate(String value);
    
}
