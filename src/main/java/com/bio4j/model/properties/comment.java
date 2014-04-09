package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Comment extends Property {

  public static enum Type implements PropertyType<Type, String> {
    comment;
    public Type value() { return comment; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.comment;

  public String comment();
}