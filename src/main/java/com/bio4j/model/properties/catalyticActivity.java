package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface catalyticActivity extends Property {

  public static enum type implements PropertyType<type, String> {
    catalyticActivity;
    public type value() { return catalyticActivity; }
    public Class<String> valueClass() { return String.class; }
  }

  public static type TYPE = type.catalyticActivity;

  public String catalyticActivity();
}