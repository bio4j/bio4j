package com.bio4j.model;

/*
*  A Property type.
*
*  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public abstract class PropertyType <
  // the element type
  N extends Element<N,NT>, NT extends Enum<NT> & ElementType<N,NT>,
  // the property (of that element)
  P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
  // the value type of this property
  V
> 
{
  
  protected PropertyType(NT elementType, String name) {

    this.elementType = elementType;
    this.name = name;
  }

  private NT elementType;
  // this is a strong hint for you to implement this as a singleton
  // public abstract PT value(NT elementType);
  private String name;

  /*
    the name is by default the name of the element together with that of the unique value here
  */
  public String fullName() { return elementType.name().concat(".").concat(name); }

  /*
    the element type which has this property type
  */
  public NT elementType() { return this.elementType; }

  // just in case
  public abstract Class<V> valueClass();
  public String name() { return this.name(); }
}
