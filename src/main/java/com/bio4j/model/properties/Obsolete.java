package com.bio4j.model.properties;

import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.ElementType;

public interface Obsolete <N extends Element<N,NT>, NT extends Enum<NT> & ElementType<N,NT>> 
  extends Property<N, NT> {

  // the property method
  public Boolean obsolete();

  // static type method
  public static <
    N extends Element<N,NT> & Obsolete<N,NT>, 
    NT extends Enum<NT> & ElementType<N,NT>
  > Type<N,NT> TYPE(NT elementType) { return new Type<N,NT>(elementType); }

  // convenience type
  public class Type <N extends Element<N,NT> & Obsolete<N,NT>, NT extends Enum<NT> & ElementType<N,NT>> 
    extends PropertyType<N, NT, Obsolete<N,NT>, Type<N,NT>, Boolean> {


    public Type(NT elementType) {
      super(elementType, "obsolete");
    }

    public Class<Boolean> valueClass() { return Boolean.class; }
  }
}