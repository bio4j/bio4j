package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface obsolete extends Property {

  public static enum type implements PropertyType<type, Boolean> {
    obsolete;
    public type value() { return obsolete; }
    public Class<Boolean> getValueClass() { return Boolean.class; }
  }

  public static type TYPE = type.obsolete;

  public Boolean obsolete();
}