package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Obsolete extends Property {

  public static enum Type implements PropertyType<Type, Boolean> {
    obsolete;
    public Type value() { return obsolete; }
    public Class<Boolean> valueClass() { return Boolean.class; }
  }

  public static Type TYPE = Type.obsolete;

  public Boolean obsolete();
}