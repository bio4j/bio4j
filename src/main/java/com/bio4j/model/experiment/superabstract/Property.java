package com.bio4j.model.experiment.superabstract;

public interface Property <H, T> {

  public T get();

  public H set(T value);

}
