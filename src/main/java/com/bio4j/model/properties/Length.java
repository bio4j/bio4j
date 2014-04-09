package com.bio4j.model.properties;

import com.bio4j.model.Property;
import com.bio4j.model.PropertyType;

public interface Length extends Property {

	  public static enum Type implements PropertyType<Type, Integer> {
	    length;
	    public Type value() { return length; }
	    public Class<Integer> valueClass() { return Integer.class; }
	  }

	  public static Type TYPE = Type.length;

	  public String name();
}