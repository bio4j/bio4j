package com.bio4j.model.relationships;

import com.bio4j.model.Relationship;

import com.bio4j.model.nodes.Country;
import com.bio4j.model.nodes.Institute;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface InstituteCountry extends Relationship{
    
    public Institute getInstitute();    
    public Country getCountry();
    
}
