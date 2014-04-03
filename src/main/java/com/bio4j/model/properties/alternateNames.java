package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface alternateNames extends Property {

  public static enum type implements PropertyType<type, String[]> {
    alternateNames;
    public type value() { return alternateNames; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static type TYPE = type.alternateNames;

  public String[] alternateNames();
}