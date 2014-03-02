
package com.bio4j.model.nodes.ncbi;

import com.bio4j.model.Node;
import com.bio4j.model.nodes.Taxon;
import java.util.List;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface NCBITaxon extends Node {
    
    //----------------GETTERS---------------------
    public String getName();
    public String getTaxId();
    public String getRank();
    public String getEmblCode();
    public String getComments();
    public String getScientificName();
    public NCBITaxon getParent();
    public List<NCBITaxon> getChildren();    
    public Taxon getTaxon();
    
    //----------------SETTERS-------------------
    public void setTaxId(String value);
    public void setRank(String value);
    public void setEmblCode(String value);
    public void setComments(String value);
    public void setScientificName(String value);
    public void setName(String value);
        
}
