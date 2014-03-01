package com.bio4j.model.nodes;

import java.util.List;

import com.bio4j.model.Node;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface Enzyme extends Node{
    
    //----------GETTERS-------------
    public String getId();
    public String getOfficialName();
    public String[] getAlternateNames();
    public String getCatalyticActivity();
    public String[] getCofactors();
    public String getComments();
    public String[] getPrositeCrossReferences();
    
    public List<Protein> getAssociatedProteins();  

    //-----------SETTERS----------------------
    public void setId(String value);
    public void setOfficialName(String value);
    public void setAlternateNames(String[] value);
    public void setCatalyticActivity(String value);
    public void setCofactors(String[] value);
    public void setComments(String value);
    public void setPrositeCrossReferences(String[] value);   
      
    
}
