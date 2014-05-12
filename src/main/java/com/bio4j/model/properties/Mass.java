package com.bio4j.model.properties;

import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.ElementType;

public interface Mass <N extends Element<N,NT>, NT extends Enum<NT> & ElementType<N,NT>> 
  extends Property<N, NT> {

  // the property method
  public Float mass();

  // static type method
  public static <
    N extends Element<N,NT> & Mass<N,NT>, 
    NT extends Enum<NT> & ElementType<N,NT>
  > Type<N,NT> TYPE(NT elementType) { return new Type<N,NT>(elementType); }

  // convenience type
  public class Type <N extends Element<N,NT> & Mass<N,NT>, NT extends Enum<NT> & ElementType<N,NT>> 
    extends PropertyType<N, NT, Mass<N,NT>, Type<N,NT>, Float> {

    public Type(NT elementType) {
      super(elementType, "mass");
    }

    public Class<Float> valueClass() { return Float.class; }
  }
}