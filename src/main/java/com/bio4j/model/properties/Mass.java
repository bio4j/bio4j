package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Mass extends Property {

	  public static enum Type implements PropertyType<Type, Float> {
	    mass;
	    public Type value() { return mass; }
	    public Class<Float> valueClass() { return Float.class; }
	  }

	  public static Type TYPE = Type.mass;

	  public String name();
}