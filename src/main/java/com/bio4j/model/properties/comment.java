package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface comment extends Property {

  public static enum type implements PropertyType<type, String> {
    comment;
    public type value() { return comment; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.comment;

  public String comment();
}