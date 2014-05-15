package com.bio4j.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.nodes.SubOntologies;

/**
 * A generic interface for sub-ontologies; they are relationships from terms to the SubOntologies singleton node.
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface SubOntology<
  GSO extends Relationship<Term,Term.Type, GSO,GSOT, SubOntologies, SubOntologies.Type>,
  GSOT extends Enum<GSOT> & RelationshipType<Term,Term.Type, GSO,GSOT, SubOntologies, SubOntologies.Type>
> extends Relationship <
  Term, Term.Type,
  GSO, GSOT,
  SubOntologies, SubOntologies.Type
> {

  @Override public Term source();
  @Override public SubOntologies target();

  public interface Type <
    GSO extends Relationship<Term,Term.Type, GSO,GSOT, SubOntologies,SubOntologies.Type>,
    GSOT extends Enum<GSOT> & RelationshipType<Term,Term.Type, GSO,GSOT, SubOntologies,SubOntologies.Type>
  > 
  extends RelationshipType.ManyToOne <
    Term, Term.Type,
    GSO, GSOT,
    SubOntologies, SubOntologies.Type
  >
  {

    @Override public default Term.Type sourceType() { return Term.TYPE; }
    @Override public default SubOntologies.Type targetType() { return SubOntologies.TYPE; }
  }
}
