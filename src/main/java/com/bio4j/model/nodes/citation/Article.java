
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Vertex;
import com.bio4j.model.nodes.Consortium;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Article extends Vertex {
    
    //----GETTERS---
    public String getTitle();
    public String getPubmedId();
    public String getMedlineId();
    public String getDoiId();
    public List<Protein> getProteinCitations();
    public Journal getJournal();
    public List<Consortium> getConsortiumAuthors();
    public List<Person> getPersonAuthors();
    
    //----SETTERS----
    public void setTitle(String value);
    public void setPubmedId(String value);
    public void setMedlineId(String value);
    public void setDoiId(String value);
    
    
    
}
