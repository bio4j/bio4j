
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Institute;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Thesis extends Node {
    
    //------GETTERS----
    public String getTitle();
    public String getDate();
    public Institute getInstitute();
    public Person getAuthor();
    public List<Protein> getProteinCitations();

    //------SETTERES-----
    public void setTitle(String value);
    public void setDate(String value);
    
    
}
