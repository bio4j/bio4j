package com.bio4j.model.experiment.superabstract;

public interface ThesisAuthoredBy extends Relationship<
  Thesis, Thesis.type,
  ThesisAuthoredBy, ThesisAuthoredBy.type,
  Person, Person.type
> {
    
  enum type implements RelationshipType<
    Thesis, Thesis.type,
    ThesisAuthoredBy, ThesisAuthoredBy.type,
    Person, Person.type
  > {

    thesisAuthoredBy;

    public type value() { return thesisAuthoredBy; }
  }

  // not needed, but it's here just so that you can see how this can only return `taxonParent`
  public type getType();

  public Thesis getSource();
  public Person getTarget();
}