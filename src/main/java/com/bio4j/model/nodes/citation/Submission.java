
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Consortium;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Submission extends Node {
    
    //--------GETTERS-------------
    public String getTitle();
    public String getDate();
    public DB getDB();    
    public List<Consortium> getConsortiumAuthors();
    public List<Person> getPersonAuthors();
    public List<Protein> getProteinCitations();

    //--------SETTERS---------
    public void setTitle(String value);
    public void setDate(String value);
    

}
