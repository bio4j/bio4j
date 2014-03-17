package com.bio4j.model.util;

import com.bio4j.model.nodes.SubcellularLocation;

public interface SubcellularLocationRetriever extends NodeRetriever<SubcellularLocation> {

  public SubcellularLocation getSubcellularLocationByName(String subcellularLocationName);

}
