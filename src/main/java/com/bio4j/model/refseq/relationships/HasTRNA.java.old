package com.bio4j.model.refseq.relationships;


// source and target
import com.bio4j.model.refseq.nodes.GenomeElement;
import com.bio4j.model.refseq.nodes.TRNA;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
 */
public interface HasTRNA extends HasGenomicFeature <
  HasTRNA,  HasTRNA.Type,
  TRNA, TRNA.Type
> {

  public GenomeElement source();
  public TRNA target();

  public static Type TYPE = Type.hasTRNA;
  public static enum Type implements HasGenomicFeatureType <
    HasTRNA,  HasTRNA.Type,
    TRNA, TRNA.Type
  > {
    hasTRNA;
    public Type value() { return hasTRNA; }
    public Arity arity() { return Arity.manyToMany; } // TODO review this
    public GenomeElement.Type sourceType() { return GenomeElement.TYPE; }
    public TRNA.Type targetType() { return TRNA.TYPE; }
  }
}
