package com.bio4j.model.experiment.superabstract;

public interface Thesis extends Node<Thesis, Thesis.type> {

  enum type implements NodeType<Thesis, Thesis.type> {

    thesis;

    public type value() { return thesis; }
  }
    
    //------GETTERS----
    public String getTitle();
    public String getDate();
    // public Institute getInstitute();
    // public Person getAuthor();
    // public List<Protein> getProteinCitations();

    // //------SETTERES-----
    // public void setTitle(String value);
    // public void setDate(String value);
    
    
}