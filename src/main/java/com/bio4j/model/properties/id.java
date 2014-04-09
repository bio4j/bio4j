package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Id extends Property {

  public static enum type implements PropertyType<type, String> {
    id;
    public type value() { return id; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.id;

  public String id();
}