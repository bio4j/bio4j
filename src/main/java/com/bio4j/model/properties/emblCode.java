package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface EmblCode extends Property {

  public static enum type implements PropertyType<type, String> {
    emblCode;
    public type value() { return emblCode; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.emblCode;

  public String emblCode();
}