package com.bio4j.model.go.relationships.goSlims;

import com.ohnosequences.typedGraphs.Relationship;



import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.nodes.GoSlims;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
// public interface GoSlim <
//   GSO extends Relationship<Term,Term.Type, GSO,GSOT, GoSlims, GoSlims.Type>,
//   GSOT extends Relationship.Type<Term,Term.Type, GSO,GSOT, GoSlims, GoSlims.Type>
// > extends Relationship <
//   Term, Term.Type,
//   GSO, GSOT,
//   GoSlims, GoSlims.Type
// > {

//   @Override public Term source();
//   @Override public GoSlims target();

//   public interface Type <
//     GSO extends Relationship<Term,Term.Type, GSO,GSOT, GoSlims,GoSlims.Type>,
//     GSOT extends Enum<GSOT> & Relationship.Type<Term,Term.Type, GSO,GSOT, GoSlims,GoSlims.Type>
//   > 
//   extends Relationship.Type.ManyToOne <
//     Term, Term.Type,
//     GSO, GSOT,
//     GoSlims, GoSlims.Type
//   >
//   {

//     @Override public default Term.Type sourceType() { return Term.TYPE; }
//     @Override public default GoSlims.Type targetType() { return GoSlims.TYPE; }
//   }
// }
