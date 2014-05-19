package com.bio4j.model.properties;

import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.ElementType;

public interface Name <N extends Element<N,NT>, NT extends ElementType<N,NT>> 
  extends Property<N, NT> {

  // the property method
  public String name();

  // static property type method
  public static <N extends Element<N,NT> & Name<N,NT>, NT extends ElementType<N,NT>> Type<N,NT> TYPE(NT elementType) { 

    return new Type<N,NT>(elementType); 
  }

  // convenience type
  public class Type <N extends Element<N,NT> & Name<N,NT>, NT extends ElementType<N,NT>> 
    extends PropertyType<N, NT, Name<N,NT>, Type<N,NT>, String> {

    public Type(NT elementType) {
      super(elementType, "name");
    }

    public Class<String> valueClass() { return String.class; }
  }
}