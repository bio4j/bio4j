package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface OfficialName extends Property {

  public static enum Type implements PropertyType<Type, String> {
    officialName;
    public Type value() { return officialName; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.officialName;

  public String officialName();
}