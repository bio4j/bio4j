package com.bio4j.model.util;

import com.bio4j.model.nodes.City;

public interface CityRetriever extends NodeRetriever<City> {

  public City getCityNodeByName(String cityName);

}
