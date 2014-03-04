package com.bio4j.model.relationships;

import com.bio4j.model.Edge;

import com.bio4j.model.nodes.Country;
import com.bio4j.model.nodes.Institute;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public interface InstituteCountry extends Edge {
    
  public Institute getInstitute();    
  public Country getCountry();    
}
