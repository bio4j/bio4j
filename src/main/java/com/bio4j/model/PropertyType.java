package com.bio4j.model;

/*
*  A Property type.
*
*  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public abstract class PropertyType <
  // the node type
  N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
  // the property (of that node)
  P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
  // the value type of this property
  V
> 
{
  
  protected PropertyType(NT nodeType, String name) {

    this.nodeType = nodeType;
    this.name = name;
  }

  private NT nodeType;
  // this is a strong hint for you to implement this as a singleton
  // public abstract PT value(NT nodeType);
  private String name;

  /*
    the name is by default the name of the node together with that of the unique value here
  */
  public String fullName() { return nodeType.name().concat(".").concat(name); }

  /*
    the node type which has this property type
  */
  public NT nodeType() { return this.nodeType; }

  // just in case
  public abstract Class<V> valueClass();
  public String name() { return this.name(); }
}
