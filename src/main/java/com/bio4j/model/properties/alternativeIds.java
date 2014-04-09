package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface AlternativeIds extends Property {

  public static enum Type implements PropertyType<Type, String[]> {
    alternativeIds;
    public Type value() { return alternativeIds; }
    public Class<String[]> valueClass() { return String[].class; }
  }

  public static Type TYPE = Type.alternativeIds;

  public String[] alternativeIds();
}