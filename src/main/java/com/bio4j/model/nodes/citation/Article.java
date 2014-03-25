
package com.bio4j.model.nodes.citation;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Consortium;
import com.bio4j.model.nodes.Person;
import com.bio4j.model.nodes.Protein;
import java.util.List;
import com.bio4j.model.NodeType;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Article extends Node<Article, Article.type> {
    
  enum type implements NodeType<Article, Article.type> {

    article;
    public type value() { return article; }
  }   
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
