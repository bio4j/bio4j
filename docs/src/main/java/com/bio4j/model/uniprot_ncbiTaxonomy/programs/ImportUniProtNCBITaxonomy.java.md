
```java
package com.bio4j.model.uniprot_ncbiTaxonomy.programs;

import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.ncbiTaxonomy.vertices.NCBITaxon;
import com.bio4j.model.uniprot.vertices.Protein;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.angulillos.UntypedGraph;
import com.bio4j.model.uniprot_ncbiTaxonomy.UniProtNCBITaxonomyGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import org.jdom2.Element;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public abstract class ImportUniProtNCBITaxonomy<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  private static final Logger logger = Logger.getLogger("ImportUniProtNCBITaxonomy");
  private static FileHandler fh;

  public static final String ENTRY_TAG_NAME = "entry";
  public static final String ENTRY_ACCESSION_TAG_NAME = "accession";

  public static final String ORGANISM_TAG_NAME = "organism";
  public static final String DB_REFERENCE_TAG_NAME = "dbReference";
  public static final String DB_REFERENCE_TYPE_ATTRIBUTE = "type";
  public static final String DB_REFERENCE_ID_ATTRIBUTE = "id";
  public static final String NCBI_TAXONOMY_TYPE = "NCBI Taxonomy";

  protected abstract UniProtNCBITaxonomyGraph<I,RV,RVT,RE,RET> config(String dbFolder, String propertiesFile);


  public void importUniProtNCBITaxonomy(String[] args) {

  if (args.length != 3) {
    System.out.println("This program expects the following parameters: \n"
      + "1. UniProt xml filename \n"
      + "2. Bio4j DB folder \n"
      + "3. DB Properties file (.properties)");
  } else {

    long initTime = System.nanoTime();

    File inFile = new File(args[0]);
    String dbFolder = args[1];
    String propertiesFile = args[2];

    UniProtNCBITaxonomyGraph<I,RV,RVT,RE,RET> uniprotNCBITaxonomyGraph = config(dbFolder, propertiesFile);

    BufferedWriter statsBuff = null;

    int proteinCounter = 0;
    int limitForPrintingOut = 10000;

    try {

    // This block configures the logger with handler and formatter
    fh = new FileHandler("ImportUniProtNCBITaxonomy" + args[0].split("\\.")[0].replaceAll("/", "_") + ".log", false);

    SimpleFormatter formatter = new SimpleFormatter();
    fh.setFormatter(formatter);
    logger.addHandler(fh);
    logger.setLevel(Level.ALL);

    //---creating writer for stats file-----
    statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtNCBITaxonomyStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

    BufferedReader reader = new BufferedReader(new FileReader(inFile));
    StringBuilder entryStBuilder = new StringBuilder();
    String line;

    while ((line = reader.readLine()) != null) {
      if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

      while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
        entryStBuilder.append(line);
        line = reader.readLine();
      }
      //linea final del organism
      entryStBuilder.append(line);
      XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
      entryStBuilder.delete(0, entryStBuilder.length());

      String accessionSt = entryXMLElem.asJDomElement().getChildText(ENTRY_ACCESSION_TAG_NAME);

      Optional<Protein<I,RV,RVT,RE,RET>> proteinOptional = uniprotNCBITaxonomyGraph.uniProtGraph().proteinAccessionIndex().getVertex(accessionSt);
      if(proteinOptional.isPresent()){

        Protein<I,RV,RVT,RE,RET> protein = proteinOptional.get();

        Element organismElement = entryXMLElem.asJDomElement().getChild(ORGANISM_TAG_NAME);
        if(organismElement != null){

        //-----db references-------------
        List<Element> dbReferenceList = organismElement.getChildren(DB_REFERENCE_TAG_NAME);

        for (Element dbReferenceElem : dbReferenceList) {

          //-------------------NCBI TAXONOMY -----------------------------
          if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(NCBI_TAXONOMY_TYPE)) {

          String taxonId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

          Optional<NCBITaxon<I,RV,RVT,RE,RET>> ncbiTaxonOptional = uniprotNCBITaxonomyGraph.ncbiTaxonomyGraph().nCBITaxonIdIndex().getVertex(taxonId);
          if(ncbiTaxonOptional.isPresent()){
            protein.addOutEdge(uniprotNCBITaxonomyGraph.ProteinNCBITaxon(), ncbiTaxonOptional.get());
          }

          }
        }
        //---------------------------------------------------------------------------------------
        }


        proteinCounter++;
        if ((proteinCounter % limitForPrintingOut) == 0) {
        String countProteinsSt = proteinCounter + " proteins updated!!";
        logger.log(Level.INFO, countProteinsSt);
        }
      }


      }
    }

    } catch (Exception e) {
    logger.log(Level.SEVERE, e.getMessage());
    StackTraceElement[] trace = e.getStackTrace();
    for (StackTraceElement stackTraceElement : trace) {
      logger.log(Level.SEVERE, stackTraceElement.toString());
    }
    } finally {

    try {
      //------closing writers-------

      // shutdown, makes sure all changes are written to disk
      uniprotNCBITaxonomyGraph.raw().shutdown();

      // closing logger file handler
      fh.close();

      //-----------------writing stats file---------------------
      long elapsedTime = System.nanoTime() - initTime;
      long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
      long hours = elapsedSeconds / 3600;
      long minutes = (elapsedSeconds % 3600) / 60;
      long seconds = (elapsedSeconds % 3600) % 60;

      statsBuff.write("Statistics for program ImportUniProtNCBITaxonomy:\nInput file: " + inFile.getName()
        + "\nThere were " + proteinCounter + " proteins inserted.\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

      //---closing stats writer---
      statsBuff.close();


    } catch (IOException ex) {
      Logger.getLogger(ImportUniProtNCBITaxonomy.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
  }

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
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java]: ../../ncbiTaxonomy_geninfo/programs/ImportGenInfoNCBITaxonIndex.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java]: ../../ncbiTaxonomy_geninfo/edges/GenInfoNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java]: ../../ncbiTaxonomy_geninfo/NCBITaxonomyGenInfoGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/UniProtNCBITaxonomyGraph.java]: ../UniProtNCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/programs/ImportUniProtNCBITaxonomy.java]: ImportUniProtNCBITaxonomy.java.md
[main/java/com/bio4j/model/uniprot_ncbiTaxonomy/edges/ProteinNCBITaxon.java]: ../edges/ProteinNCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/vertices/NCBITaxon.java]: ../../ncbiTaxonomy/vertices/NCBITaxon.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/NCBITaxonomyGraph.java]: ../../ncbiTaxonomy/NCBITaxonomyGraph.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/programs/ImportNCBITaxonomy.java]: ../../ncbiTaxonomy/programs/ImportNCBITaxonomy.java.md
[main/java/com/bio4j/model/ncbiTaxonomy/edges/NCBITaxonParent.java]: ../../ncbiTaxonomy/edges/NCBITaxonParent.java.md
[main/java/com/bio4j/model/geninfo/vertices/GenInfo.java]: ../../geninfo/vertices/GenInfo.java.md
[main/java/com/bio4j/model/geninfo/GenInfoGraph.java]: ../../geninfo/GenInfoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/tests/ImportUniProtGoTest.java]: ../../uniprot_go/tests/ImportUniProtGoTest.java.md
[main/java/com/bio4j/model/uniprot_go/UniProtGoGraph.java]: ../../uniprot_go/UniProtGoGraph.java.md
[main/java/com/bio4j/model/uniprot_go/programs/ImportUniProtGo.java]: ../../uniprot_go/programs/ImportUniProtGo.java.md
[main/java/com/bio4j/model/uniprot_go/edges/GoAnnotation.java]: ../../uniprot_go/edges/GoAnnotation.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/UniProtEnzymeDBGraph.java]: ../../uniprot_enzymedb/UniProtEnzymeDBGraph.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java]: ../../uniprot_enzymedb/programs/ImportUniProtEnzymeDB.java.md
[main/java/com/bio4j/model/uniprot_enzymedb/edges/EnzymaticActivity.java]: ../../uniprot_enzymedb/edges/EnzymaticActivity.java.md
[main/java/com/bio4j/model/uniprot/UniProtGraph.java]: ../../uniprot/UniProtGraph.java.md
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../../uniprot/vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../../uniprot/vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../../uniprot/vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: ../../uniprot/vertices/InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../../uniprot/vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../../uniprot/vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../../uniprot/vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../../uniprot/vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../../uniprot/vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../../uniprot/vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../../uniprot/vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../../uniprot/vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../../uniprot/vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../../uniprot/vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../../uniprot/vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../../uniprot/vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../../uniprot/vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../../uniprot/vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../../uniprot/vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../../uniprot/vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../../uniprot/vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../../uniprot/vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../../uniprot/vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../../uniprot/vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../../uniprot/vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../../uniprot/vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../../uniprot/vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../../uniprot/vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../../uniprot/vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../../uniprot/vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: ../../uniprot/vertices/GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../../uniprot/vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../../uniprot/vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../../uniprot/vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../../uniprot/vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../../uniprot/vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../../uniprot/vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../../uniprot/vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../../uniprot/vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../../uniprot/vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../../uniprot/vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ../../uniprot/programs/ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProt.java]: ../../uniprot/programs/ImportUniProt.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ../../uniprot/programs/ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ../../uniprot/programs/ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/XMLConstants.java]: ../../uniprot/programs/XMLConstants.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ../../uniprot/programs/ImportUniProtVertices.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinOrganism.java]: ../../uniprot/edges/ProteinOrganism.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinRefSeq.java]: ../../uniprot/edges/ProteinRefSeq.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSequenceCaution.java]: ../../uniprot/edges/ProteinSequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceArticle.java]: ../../uniprot/edges/ReferenceArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/BookPublisher.java]: ../../uniprot/edges/BookPublisher.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPIR.java]: ../../uniprot/edges/ProteinPIR.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEMBL.java]: ../../uniprot/edges/ProteinEMBL.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinUniGene.java]: ../../uniprot/edges/ProteinUniGene.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinProteinInteraction.java]: ../../uniprot/edges/ProteinProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKegg.java]: ../../uniprot/edges/ProteinKegg.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDisease.java]: ../../uniprot/edges/ProteinDisease.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinFeature.java]: ../../uniprot/edges/ProteinFeature.java.md
[main/java/com/bio4j/model/uniprot/edges/BookEditor.java]: ../../uniprot/edges/BookEditor.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoform.java]: ../../uniprot/edges/ProteinIsoform.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinSubcellularLocation.java]: ../../uniprot/edges/ProteinSubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformProteinInteraction.java]: ../../uniprot/edges/IsoformProteinInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinDataset.java]: ../../uniprot/edges/ProteinDataset.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorPerson.java]: ../../uniprot/edges/ReferenceAuthorPerson.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferencePatent.java]: ../../uniprot/edges/ReferencePatent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinIsoformInteraction.java]: ../../uniprot/edges/ProteinIsoformInteraction.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceBook.java]: ../../uniprot/edges/ReferenceBook.java.md
[main/java/com/bio4j/model/uniprot/edges/OnlineArticleOnlineJournal.java]: ../../uniprot/edges/OnlineArticleOnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceOnlineArticle.java]: ../../uniprot/edges/ReferenceOnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceAuthorConsortium.java]: ../../uniprot/edges/ReferenceAuthorConsortium.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticleJournal.java]: ../../uniprot/edges/ArticleJournal.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinEnsembl.java]: ../../uniprot/edges/ProteinEnsembl.java.md
[main/java/com/bio4j/model/uniprot/edges/ThesisInstitute.java]: ../../uniprot/edges/ThesisInstitute.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReactomeTerm.java]: ../../uniprot/edges/ProteinReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/edges/SubcellularLocationParent.java]: ../../uniprot/edges/SubcellularLocationParent.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinComment.java]: ../../uniprot/edges/ProteinComment.java.md
[main/java/com/bio4j/model/uniprot/edges/TaxonParent.java]: ../../uniprot/edges/TaxonParent.java.md
[main/java/com/bio4j/model/uniprot/edges/SubmissionDB.java]: ../../uniprot/edges/SubmissionDB.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinInterPro.java]: ../../uniprot/edges/ProteinInterPro.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceThesis.java]: ../../uniprot/edges/ReferenceThesis.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneName.java]: ../../uniprot/edges/ProteinGeneName.java.md
[main/java/com/bio4j/model/uniprot/edges/OrganismTaxon.java]: ../../uniprot/edges/OrganismTaxon.java.md
[main/java/com/bio4j/model/uniprot/edges/IsoformEventGenerator.java]: ../../uniprot/edges/IsoformEventGenerator.java.md
[main/java/com/bio4j/model/uniprot/edges/BookCity.java]: ../../uniprot/edges/BookCity.java.md
[main/java/com/bio4j/model/uniprot/edges/ArticlePubmed.java]: ../../uniprot/edges/ArticlePubmed.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceSubmission.java]: ../../uniprot/edges/ReferenceSubmission.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinKeyword.java]: ../../uniprot/edges/ProteinKeyword.java.md
[main/java/com/bio4j/model/uniprot/edges/ReferenceUnpublishedObservation.java]: ../../uniprot/edges/ReferenceUnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinPfam.java]: ../../uniprot/edges/ProteinPfam.java.md
[main/java/com/bio4j/model/uniprot/edges/InstituteCountry.java]: ../../uniprot/edges/InstituteCountry.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinReference.java]: ../../uniprot/edges/ProteinReference.java.md
[main/java/com/bio4j/model/uniprot/edges/ProteinGeneLocation.java]: ../../uniprot/edges/ProteinGeneLocation.java.md