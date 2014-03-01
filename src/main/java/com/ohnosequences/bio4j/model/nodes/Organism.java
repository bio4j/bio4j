package com.ohnosequences.bio4j.model.nodes;

import java.util.List;

import com.ohnosequences.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Organism extends Node{
    
    //----GETTERS---
    public String getScientificName();
    public String getCommonName();
    public String getSynonymName();
    public String getNcbiTaxonomyId();
    
    public List<Protein> getAssociatedProteins();

    //----SETTERS----
    public void setScientificName(String value);
    public void setCommonName(String value);
    public void setSynonymName(String value);
    public void setNcbiTaxonomyId(String value);   
    
}
