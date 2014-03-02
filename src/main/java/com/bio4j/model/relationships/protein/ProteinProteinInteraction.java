
package com.bio4j.model.relationships.protein;

import com.bio4j.model.nodes.Protein;
import com.bio4j.model.Relationship;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface ProteinProteinInteraction extends Relationship {
    
    public String getOrganismsDiffer();
    public String getExperiments();
    public String getIntactId2();
    public String getIntactId1();

    public void setOrganismsDiffer(String value);
    public void setExperiments(String value);
    public void setIntactId2(String value);
    public void setIntactId1(String value);

    public Protein getProtein1();    
    public Protein getProtein2();
    
}
