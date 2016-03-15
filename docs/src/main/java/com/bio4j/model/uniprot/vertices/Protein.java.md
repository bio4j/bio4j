
```java
package com.bio4j.model.uniprot.vertices;

import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.edges.*;
import com.bio4j.model.uniprot_enzymedb.edges.EnzymaticActivity;
import com.bio4j.model.uniprot_go.edges.GoAnnotation;
import com.bio4j.model.uniprot_ncbiTaxonomy.edges.ProteinNCBITaxon;
import com.bio4j.model.uniprot_uniref.edges.*;
import com.bio4j.model.uniref.vertices.UniRef100Cluster;
import com.bio4j.model.uniref.vertices.UniRef50Cluster;
import com.bio4j.model.uniref.vertices.UniRef90Cluster;
import com.bio4j.angulillos.UntypedGraph;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public final class Protein <I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET>
extends UniProtGraph.UniProtVertex<
Protein<I, RV, RVT, RE, RET>,
UniProtGraph<I, RV, RVT, RE, RET>.ProteinType,
I, RV, RVT, RE, RET
> {

  public Protein(RV vertex, UniProtGraph<I, RV, RVT, RE, RET>.ProteinType type) {
    super(vertex, type);
  }

  @Override
  public Protein<I, RV, RVT, RE, RET> self() {
    return this;
  }

  // properties
  public String accession() {
    return get(type().accession);
  }
  public String shortName() {
    return get(type().shortName);
  }
  public String name() { return get(type().name); }
  public String sequence() {
    return get(type().sequence);
  }
  public String fullName() {
    return get(type().fullName);
  }
  public Date modifiedDate() {
    return get(type().modifiedDate);
  }
  public Date createdDate() {
    return get(type().createdDate);
  }
  public String mass() {
    return get(type().mass);
  }
  public Integer version() {
    return get(type().version);
  }
  public Integer length() {
    return get(type().length);
  }
  public String uniRef100ClusterId(){return get(type().uniRef100ClusterId);}
  public String uniRef90ClusterId(){return get(type().uniRef90ClusterId);}
  public String uniRef50ClusterId(){return get(type().uniRef50ClusterId);}

  //////////////////////////////////////////////////////////////////////////////////////////////

  ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // relationships
  ////////////////////////////////////////////////////////////////////////////////////////////////////////

  // proteinOrganism
  // outgoing
  public ProteinOrganism<I, RV, RVT, RE, RET> proteinOrganism_out(){
    return outOne(graph().ProteinOrganism());
  }
  public Organism<I, RV, RVT, RE, RET>  proteinOrganism_outV(){
    return outOneV(graph().ProteinOrganism());
  }

  // proteinComment
  // outgoing
  public Optional<Stream<ProteinComment<I, RV, RVT, RE, RET>>> proteinComment_out(){
    return outManyOptional(graph().ProteinComment());
  }
  public Optional<Stream<CommentType<I, RV, RVT, RE, RET>>>  proteinComment_outV(){
    return outManyOptionalV(graph().ProteinComment());
  }

  // proteinRefSeq
  // outgoing
  public Optional<Stream<ProteinRefSeq<I, RV, RVT, RE, RET>>> proteinRefSeq_out(){
    return outManyOptional(graph().ProteinRefSeq());
  }
  public Optional<Stream<RefSeq<I, RV, RVT, RE, RET>>>  proteinRefSeq_outV(){
    return outManyOptionalV(graph().ProteinRefSeq());
  }

  // proteinSequenceCaution
  // outgoing
  public Optional<Stream<ProteinSequenceCaution<I, RV, RVT, RE, RET>>> proteinSequenceCaution_out(){
    return outManyOptional(graph().ProteinSequenceCaution());
  }
  public Optional<Stream<SequenceCaution<I, RV, RVT, RE, RET>>>  proteinSequenceCaution_outV(){
    return outManyOptionalV(graph().ProteinSequenceCaution());
  }

  // proteinSubcellularLocation
  // outgoing
  public Optional<Stream<ProteinSubcellularLocation<I, RV, RVT, RE, RET>>> proteinSubcellularLocation_out(){
    return outManyOptional(graph().ProteinSubcellularLocation());
  }
  public Optional<Stream<SubcellularLocation<I, RV, RVT, RE, RET>>>  proteinSubcellularLocation_outV(){
    return outManyOptionalV(graph().ProteinSubcellularLocation());
  }

  // proteinPIR
  // outgoing
  public Optional<Stream<ProteinPIR<I, RV, RVT, RE, RET>>> proteinPIR_out(){
    return outManyOptional(graph().ProteinPIR());
  }
  public Optional<Stream<PIR<I, RV, RVT, RE, RET>>>  proteinPIR_outV(){
    return outManyOptionalV(graph().ProteinPIR());
  }

  // proteinFeature
  // outgoing
  public Optional<Stream<ProteinFeature<I, RV, RVT, RE, RET>>> proteinFeature_out(){
    return outManyOptional(graph().ProteinFeature());
  }
  public Optional<Stream<FeatureType<I, RV, RVT, RE, RET>>>  proteinFeature_outV(){
    return outManyOptionalV(graph().ProteinFeature());
  }

  // proteinEMBL
  // outgoing
  public Optional<Stream<ProteinEMBL<I, RV, RVT, RE, RET>>> proteinEMBL_out(){
    return outManyOptional(graph().ProteinEMBL());
  }
  public Optional<Stream<EMBL<I, RV, RVT, RE, RET>>>  proteinEMBL_outV(){
    return outManyOptionalV(graph().ProteinEMBL());
  }

  // proteinDisease
  // outgoing
  public Optional<Stream<ProteinDisease<I, RV, RVT, RE, RET>>> proteinDisease_out(){
    return outManyOptional(graph().ProteinDisease());
  }
  public Optional<Stream<Disease<I, RV, RVT, RE, RET>>>  proteinDisease_outV(){
    return outManyOptionalV(graph().ProteinDisease());
  }

  // proteinDataset
  // outgoing
  public ProteinDataset<I, RV, RVT, RE, RET> proteinDataset_out(){
    return outOne(graph().ProteinDataset());
  }
  public Dataset<I, RV, RVT, RE, RET>  proteinDataset_outV(){
    return outOneV(graph().ProteinDataset());
  }

  // proteinInterPro
  // outgoing
  public Optional<Stream<ProteinInterPro<I, RV, RVT, RE, RET>>> proteinInterPro_out(){
    return outManyOptional(graph().ProteinInterPro());
  }
  public Optional<Stream<InterPro<I, RV, RVT, RE, RET>>>  proteinInterPro_outV(){
    return outManyOptionalV(graph().ProteinInterPro());
  }

  // proteinReactomeTerm
  // outgoing
  public Optional<Stream<ProteinReactomeTerm<I, RV, RVT, RE, RET>>> proteinReactomeTerm_out(){
    return outManyOptional(graph().ProteinReactomeTerm());
  }
  public Optional<Stream<ReactomeTerm<I, RV, RVT, RE, RET>>>  proteinReactomeTerm_outV(){
    return outManyOptionalV(graph().ProteinReactomeTerm());
  }

  // proteinEnsembl
  // outgoing
  public Optional<Stream<ProteinEnsembl<I, RV, RVT, RE, RET>>> proteinEnsembl_out(){
    return outManyOptional(graph().ProteinEnsembl());
  }
  public Optional<Stream<Ensembl<I, RV, RVT, RE, RET>>>  proteinEnsembl_outV(){
    return outManyOptionalV(graph().ProteinEnsembl());
  }

  // proteinKeyword
  // outgoing
  public Optional<Stream<ProteinKeyword<I, RV, RVT, RE, RET>>> proteinKeyword_out(){
    return outManyOptional(graph().ProteinKeyword());
  }
  public Optional<Stream<Keyword<I, RV, RVT, RE, RET>>>  proteinKeyword_outV(){
    return outManyOptionalV(graph().ProteinKeyword());
  }

  // proteinKegg
  // outgoing
  public Optional<Stream<ProteinKegg<I, RV, RVT, RE, RET>>> proteinKegg_out(){
    return outManyOptional(graph().ProteinKegg());
  }
  public Optional<Stream<Kegg<I, RV, RVT, RE, RET>>>  proteinKegg_outV(){
    return outManyOptionalV(graph().ProteinKegg());
  }

  // proteinPfam
  // outgoing
  public Optional<Stream<ProteinPfam<I, RV, RVT, RE, RET>>> proteinPfam_out(){
    return outManyOptional(graph().ProteinPfam());
  }
  public Optional<Stream<Pfam<I, RV, RVT, RE, RET>>>  proteinPfam_outV(){
    return outManyOptionalV(graph().ProteinPfam());
  }

  // proteinUniGene
  // outgoing
  public Optional<Stream<ProteinUniGene<I, RV, RVT, RE, RET>>> proteinUniGene_out(){
    return outManyOptional(graph().ProteinUniGene());
  }
  public Optional<Stream<UniGene<I, RV, RVT, RE, RET>>>  proteinUniGene_outV(){
    return outManyOptionalV(graph().ProteinUniGene());
  }

  // enzymaticActivity
  // outgoing
  public Optional<Stream<EnzymaticActivity<I, RV, RVT, RE, RET>>>   enzymaticActivity_out(){
    return outManyOptional(graph().uniProtEnzymeDBGraph().EnzymaticActivity());
  }
  public Optional<Stream<Enzyme<I, RV, RVT, RE, RET>>>   enzymaticActivity_outV(){
    return outManyOptionalV(graph().uniProtEnzymeDBGraph().EnzymaticActivity());
  }

  // goAnnotation
  // outgoing
  public Optional<Stream<GoAnnotation<I, RV, RVT, RE, RET>>>  goAnnotation_out(){
    return outManyOptional(graph().uniProtGoGraph().GoAnnotation());
  }
  public Optional<Stream<GoTerm<I, RV, RVT, RE, RET>>>   goAnnotation_outV(){
    return outManyOptionalV(graph().uniProtGoGraph().GoAnnotation());
  }

  // uniref50Member
  // outgoing
  public Optional<UniRef50Member<I, RV, RVT, RE, RET>> uniref50Member_out(){
    return outOneOptional(graph().uniProtUniRefGraph().UniRef50Member());
  }
  public Optional<UniRef50Cluster<I, RV, RVT, RE, RET>> uniref50Member_outV(){
    return outOneOptionalV(graph().uniProtUniRefGraph().UniRef50Member());
  }

  // uniref50Representant
  // outgoing
  public Optional<UniRef50Representant<I, RV, RVT, RE, RET>> uniref50Representant_out(){
    return outOneOptional(graph().uniProtUniRefGraph().UniRef50Representant());
  }
  public Optional<UniRef50Cluster<I, RV, RVT, RE, RET>>  uniref50Representant_outV(){
    return outOneOptionalV(graph().uniProtUniRefGraph().UniRef50Representant());
  }

  // uniref90Member
  // outgoing
  public Optional<UniRef90Member<I, RV, RVT, RE, RET>> uniref90Member_out(){
    return outOneOptional(graph().uniProtUniRefGraph().UniRef90Member());
  }
  public Optional<UniRef90Cluster<I, RV, RVT, RE, RET>> uniref90Member_outV(){
    return outOneOptionalV(graph().uniProtUniRefGraph().UniRef90Member());
  }

  // uniref90Representant
  // outgoing
  public Optional<UniRef90Representant<I, RV, RVT, RE, RET>> uniref90Representant_out(){
    return outOneOptional(graph().uniProtUniRefGraph().UniRef90Representant());
  }
  public Optional<UniRef90Cluster<I, RV, RVT, RE, RET>> uniref90Representant_outV(){
    return outOneOptionalV(graph().uniProtUniRefGraph().UniRef90Representant());
  }

  // uniref100Member
  // outgoing
  public Optional<UniRef100Member<I, RV, RVT, RE, RET>> uniref100Member_out(){
    return outOneOptional(graph().uniProtUniRefGraph().UniRef100Member());
  }
  public Optional<UniRef100Cluster<I, RV, RVT, RE, RET>> uniref100Member_outV(){
    return outOneOptionalV(graph().uniProtUniRefGraph().UniRef100Member());
  }

  // uniref90Representant
  // outgoing
  public Optional<UniRef100Representant<I, RV, RVT, RE, RET>> uniref100Representant_out(){
    return outOneOptional(graph().uniProtUniRefGraph().UniRef100Representant());
  }
  public Optional<UniRef100Cluster<I, RV, RVT, RE, RET>> uniref100Representant_outV(){
    return outOneOptionalV(graph().uniProtUniRefGraph().UniRef100Representant());
  }

  // proteinReference
  // outgoing
  public Optional<Stream<ProteinReference<I, RV, RVT, RE, RET>>> proteinReference_out(){
    return outManyOptional(graph().ProteinReference());
  }
  public Optional<Stream<Reference<I, RV, RVT, RE, RET>>> proteinReference_outV(){
    return outManyOptionalV(graph().ProteinReference());
  }


  //----proteinNCBITaxon-------
  // outgoing
  public Optional<ProteinNCBITaxon<I, RV, RVT, RE, RET>> proteinNCBITaxon_out(){
    return outOneOptional(graph().uniProtNCBITaxonomyGraph().ProteinNCBITaxon());
  }
  public Optional<NCBITaxon<I, RV, RVT, RE, RET>> proteinNCBITaxon_outV(){
    return outOneOptionalV(graph().uniProtNCBITaxonomyGraph().ProteinNCBITaxon());
  }

  // proteinGeneLocation
  // outgoing
  public Optional<Stream<ProteinGeneLocation<I, RV, RVT, RE, RET>>> proteinGeneLocation_out(){
    return outManyOptional(graph().ProteinGeneLocation());
  }
  public Optional<Stream<GeneLocation<I, RV, RVT, RE, RET>>> proteinGeneLocation_outV(){
    return outManyOptionalV(graph().ProteinGeneLocation());
  }

  // proteinGeneName
  // outgoing
  public Optional<Stream<ProteinGeneName<I, RV, RVT, RE, RET>>> proteinGeneName_out(){
    return outManyOptional(graph().ProteinGeneName());
  }
  public Optional<Stream<GeneName<I, RV, RVT, RE, RET>>> proteinGeneName_outV(){
    return outManyOptionalV(graph().ProteinGeneName());
  }

  // proteinProteinInteraction
  // outgoing
  public Optional<Stream<ProteinProteinInteraction<I, RV, RVT, RE, RET>>> proteinProteinInteraction_out(){
    return outManyOptional(graph().ProteinProteinInteraction());
  }
  public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> proteinProteinInteraction_outV(){
    return outManyOptionalV(graph().ProteinProteinInteraction());
  }

  // proteinProteinInteraction
  // ingoing
  public Optional<Stream<ProteinProteinInteraction<I, RV, RVT, RE, RET>>> proteinProteinInteraction_in(){
    return inManyOptional(graph().ProteinProteinInteraction());
  }
  public Optional<Stream<Protein<I, RV, RVT, RE, RET>>> proteinProteinInteraction_inV(){
    return inManyOptionalV(graph().ProteinProteinInteraction());
  }

  // proteinIsoformInteraction
  // outgoing
  public Optional<Stream<ProteinIsoformInteraction<I, RV, RVT, RE, RET>>> proteinIsoformInteraction_out(){
    return outManyOptional(graph().ProteinIsoformInteraction());
  }
  public Optional<Stream<Isoform<I, RV, RVT, RE, RET>>> proteinIsoformInteraction_outV(){
    return outManyOptionalV(graph().ProteinIsoformInteraction());
  }
}

```




[main/java/com/bio4j/model/enzymedb/vertices/Enzyme.java]: ../../enzymedb/vertices/Enzyme.java.md
[main/java/com/bio4j/model/enzymedb/programs/ImportEnzymeDB.java]: ../../enzymedb/programs/ImportEnzymeDB.java.md
[main/java/com/bio4j/model/enzymedb/EnzymeDBGraph.java]: ../../enzymedb/EnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_uniref/programs/ImportUniProtUniRef.java]: ../../uniprot_uniref/programs/ImportUniProtUniRef.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Member.java]: ../../uniprot_uniref/edges/UniRef50Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Representant.java]: ../../uniprot_uniref/edges/UniRef100Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef100Member.java]: ../../uniprot_uniref/edges/UniRef100Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef50Representant.java]: ../../uniprot_uniref/edges/UniRef50Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Representant.java]: ../../uniprot_uniref/edges/UniRef90Representant.java.md
[main/java/com/bio4j/model/uniprot_uniref/edges/UniRef90Member.java]: ../../uniprot_uniref/edges/UniRef90Member.java.md
[main/java/com/bio4j/model/uniprot_uniref/UniProtUniRefGraph.java]: ../../uniprot_uniref/UniProtUniRefGraph.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef100Cluster.java]: ../../uniref/vertices/UniRef100Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef50Cluster.java]: ../../uniref/vertices/UniRef50Cluster.java.md
[main/java/com/bio4j/model/uniref/vertices/UniRef90Cluster.java]: ../../uniref/vertices/UniRef90Cluster.java.md
[main/java/com/bio4j/model/uniref/UniRefGraph.java]: ../../uniref/UniRefGraph.java.md
[main/java/com/bio4j/model/uniref/programs/ImportUniRef.java]: ../../uniref/programs/ImportUniRef.java.md
[main/java/com/bio4j/model/go/vertices/SubOntologies.java]: ../../go/vertices/SubOntologies.java.md
[main/java/com/bio4j/model/go/vertices/GoTerm.java]: ../../go/vertices/GoTerm.java.md
[main/java/com/bio4j/model/go/vertices/GoSlims.java]: ../../go/vertices/GoSlims.java.md
[main/java/com/bio4j/model/go/programs/ImportGO.java]: ../../go/programs/ImportGO.java.md
[main/java/com/bio4j/model/go/edges/HasPartOf.java]: ../../go/edges/HasPartOf.java.md
[main/java/com/bio4j/model/go/edges/PositivelyRegulates.java]: ../../go/edges/PositivelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/Regulates.java]: ../../go/edges/Regulates.java.md
[main/java/com/bio4j/model/go/edges/SubOntology.java]: ../../go/edges/SubOntology.java.md
[main/java/com/bio4j/model/go/edges/IsA.java]: ../../go/edges/IsA.java.md
[main/java/com/bio4j/model/go/edges/NegativelyRegulates.java]: ../../go/edges/NegativelyRegulates.java.md
[main/java/com/bio4j/model/go/edges/PartOf.java]: ../../go/edges/PartOf.java.md
[main/java/com/bio4j/model/go/GoGraph.java]: ../../go/GoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../../uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ../../uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../../uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: City.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: Person.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ../programs/ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/XMLConstants.java]: ../programs/XMLConstants.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ../programs/ImportUniProtVertices.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]: ../edges/ProteinInterPro.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]: ../edges/ProteinGeneName.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../edges/ProteinGeneLocation.java.md