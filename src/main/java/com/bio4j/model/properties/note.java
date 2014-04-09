package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Note extends Property {

  public static enum Type implements PropertyType<Type, String> {
    note;
    public Type value() { return note; }
    public Class<String> valueClass() { return String.class; }
  }

  public static Type TYPE = Type.note;

  public String note();
}