package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Note extends Property {

  public static enum type implements PropertyType<type, String> {
    note;
    public type value() { return note; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.note;

  public String note();
}