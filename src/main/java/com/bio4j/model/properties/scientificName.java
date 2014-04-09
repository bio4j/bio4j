package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface ScientificName extends Property {

  public static enum type implements PropertyType<type, String> {
    scientificName;
    public type value() { return scientificName; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.scientificName;

  public String scientificName();
}