package com.bio4j.model.uniprot.relationships;

// WARNING: moved to the enzymedb package

// import com.bio4j.model.Relationship;
// import com.bio4j.model.RelationshipType;

// import com.bio4j.model.enzymedb.nodes.Enzyme;
// import com.bio4j.model.uniprot.nodes.Protein;

// /**
//  *
//  * @author Pablo Pareja Tobes <ppareja@era7.com>
//  * @author Eduardo Pareja-Tobes <eparejatobes@ohnosequences.com>
//  */
// public interface EnzymaticActivity extends Relationship <
//   Protein, Protein.type,
//   EnzymaticActivity, EnzymaticActivity.type,
//   Enzyme, Enzyme.type
// > {

//   public static type TYPE = type.enzymaticActivity;
//   public static enum type implements RelationshipType <
//     Protein, Protein.type,
//     EnzymaticActivity, EnzymaticActivity.type,
//     Enzyme, Enzyme.type
//   > {
//     enzymaticActivity;
//     public type value() { return enzymaticActivity; }
//     public arity arity() { return arity.manyToMany; }
//   }

//   public Protein source();
//   public Enzyme target();
// }