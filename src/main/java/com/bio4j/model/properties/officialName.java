package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface officialName extends Property {

  public static enum type implements PropertyType<type, String> {
    officialName;
    public type value() { return officialName; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.officialName;

  public String officialName();
}