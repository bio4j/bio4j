
package com.bio4j.model.uniprot.nodes;

import com.bio4j.model.Node;
import com.bio4j.model.uniprot.nodes.Person;
import com.bio4j.model.uniprot.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Patent extends Node<Patent, Patent.type> {
    
  enum type implements NodeType<Patent, Patent.type> {

    patent;
    public type value() { return patent; }
  }
    
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
