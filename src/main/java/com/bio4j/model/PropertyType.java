package com.bio4j.model;

/*
*  A Property type. Implementing **concrete** classes should be singleton `Enum`s.
*
*  @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
*/
public interface PropertyType<PT extends Enum<PT> & PropertyType<PT,V>, V> {
 
  // this is a strong hint for you to implement this as a singleton
  public PT value();

  // just in case
  public Class<V> valueClass();
}