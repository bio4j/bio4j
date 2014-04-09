package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface AlternativeIds extends Property {

  public static enum type implements PropertyType<type, String[]> {
    alternativeIds;
    public type value() { return alternativeIds; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static type TYPE = type.alternativeIds;

  public String[] alternativeIds();
}