package com.bio4j.model.util;

import com.bio4j.model.nodes.Country;

public interface CountryRetriever extends NodeRetriever<Country> {

  public Country getCountryNodeByName(String countryName);

}
