package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface AlternateNames extends Property {

  public static enum Type implements PropertyType<Type, String[]> {
    alternateNames;
    public Type value() { return alternateNames; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static Type TYPE = Type.alternateNames;

  public String[] alternateNames();
}