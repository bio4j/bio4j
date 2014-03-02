
package com.bio4j.model.relationships.features;

import com.bio4j.model.nodes.FeatureType;
import com.bio4j.model.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface BasicFeature extends Relationship {
    
    //------------GETTERS----------------
    public String getDescription();
    public String getStatus();
    public String getEvidence();
    public String getId();
    public String getOriginal();
    public String getVariation();
    public String getRef();
    public String getBegin();
    public String getEnd();

    public FeatureType getFeatureType();
    public Protein getProtein();

            
    //------------SETTERS-------------------
    public void setDescription(String value);
    public void setId(String value);
    public void setEvidence(String value);
    public void setStatus(String value);
    public void setRef(String value);
    public void setBegin(String value);
    public void setEnd(String value);
    public void setOriginal(String value);
    public void setVariation(String value);
    
}
