package com.ohnosequences.bio4j.model.nodes;

import java.util.List;

import com.ohnosequences.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface GoTerm extends Node{
    
    //----GETTERS---
    public String getId();
    public String getName();
    public String getDefinition();
    public String getNamespace();
    public Boolean getObsolete();
    public String getComment();
    public String[] getAlternativeIds();
    
    public List<Protein> getAssociatedProteins();
    public List<GoTerm> getIsAGoNodes();
    public List<GoTerm> getNegativelyRegulatesNodes();
    public List<GoTerm> getPositivelyRegulatesNodes();
    public List<GoTerm> getPartOfNodes();
    public List<GoTerm> getHasPartOfNodes();

    //----SETTERS----
    public void setId(String value);
    public void setName(String value);
    public void setDefinition(String value);
    public void setNamespace(String value);
    public void setObsolete(Boolean value);
    public void setComment(String value);
    public void setAlternativeIds(String[] value);
  
}
