package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface definition extends Property {

  public static enum type implements PropertyType<type, String> {
    definition;
    public type value() { return definition; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.definition;

  public String definition();
}