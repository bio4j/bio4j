
```java
package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;

import static com.bio4j.model.uniprot.programs.XMLConstants.*;

import com.bio4j.angulillos.UntypedGraph;
import com.ohnosequences.xml.api.model.XMLElement;
import org.jdom2.Element;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class ImportProteinInteractions<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  private static final Logger logger = Logger.getLogger("ImportProteinInteractions");
  private static FileHandler fh;

  final int limitForPrintingOut = 10000;

  protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(File dbFolder);

  protected void importProteinInteractions(File inFile, File dbFolder) {

    final long initTime = System.nanoTime();

    final UniProtGraph<I,RV,RVT,RE,RET> graph = config(dbFolder);

    String currentAccessionId = "";

    BufferedWriter statsBuff = null;

    int proteinCounter = 0;

    try {

      // This block configures the logger with handler and formatter
      fh = new FileHandler("ImportProteinInteractions.log", false);

      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);

      //---creating writer for stats file-----
      statsBuff = new BufferedWriter(new FileWriter(new File("ImportProteinInteractionsStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

      final BufferedReader inFileReader = new BufferedReader(new FileReader(inFile));

      String line;
      final StringBuilder entryStBuilder = new StringBuilder();
```


Iterate over the input file lines. We'd like to replace this with a `Stream<String>`-based solution, but there's no easy lazy way of partitioning streams (going from lines to `XMLElement` entries).


```java
      while((line = inFileReader.readLine()) != null) {

        if(line.trim().startsWith("<"+ENTRY_TAG_NAME)) {

          while(!line.trim().startsWith("</"+ENTRY_TAG_NAME+">")) {

            entryStBuilder.append(line);
            line = inFileReader.readLine();
          }
          entryStBuilder.append(line);

          final XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
          entryStBuilder.delete(0, entryStBuilder.length());

          importProteinInteractionsWithSource(entryXMLElem, graph);

          proteinCounter++;

          if((proteinCounter % limitForPrintingOut) == 0) {

            logger.log(Level.INFO, (proteinCounter + " proteins updated with interactions!!"));
          }
        }
      }
    }
    catch(Exception e) {
      logger.log(Level.SEVERE, ("Exception retrieving protein " + currentAccessionId));
      logger.log(Level.SEVERE, e.getMessage());
      StackTraceElement[] trace = e.getStackTrace();
      for (StackTraceElement stackTraceElement : trace) {
        logger.log(Level.SEVERE, stackTraceElement.toString());
      }
    }
    finally {

      try {

        // shutdown, makes sure all changes are written to disk
        graph.raw().shutdown();

        // closing logger file handler
        fh.close();

        //-----------------writing stats file---------------------
        long elapsedTime    = System.nanoTime() - initTime;
        long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
        long hours          = elapsedSeconds / 3600;
        long minutes        = (elapsedSeconds % 3600) / 60;
        long seconds        = (elapsedSeconds % 3600) % 60;

        statsBuff.write("Statistics for program ImportProteinInteractions:\nInput file: " + inFile.getName()
        + "\nThere were " + proteinCounter + " proteins inserted.\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

        //---closing stats writer---
        statsBuff.close();

      } catch (IOException ex) {

        Logger.getLogger(ImportProteinInteractions.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
```


By direct inspection of UniProt XML file I have concluded that:

1. the source id is always a protein, I don't know why
2. target can be either an isoform or a protein, and the id is in `interactant/id`. Isoform ids *look* to be `${protein.id}-{number}`
3. the `intactId`s have nothing to do with UniProt, and should be dropped

Note that because of 1. it would be easier if we passed the protein as an argument.

#### Remark

Again based on manual inspection it looks like protein-protein interactions are **not** *duplicated* in the UniProt XML file. I do not know if this implies a semantic direction or not. So, if you want to get all with which a given protein interacts, you need to get both inV and outV of protein-protein interaction edges.


```java
  private void importProteinInteractionsWithSource(
    XMLElement entryXMLElem,
    UniProtGraph<I,RV,RVT,RE,RET> graph
  )
  {
```

We first get the protein from the entry xml element accession

```java
    final Optional<Protein<I,RV,RVT,RE,RET>> optionalSrcProtein = graph.proteinAccessionIndex()
      .getVertex(entryXMLElem.asJDomElement().getChildren(ENTRY.ACCESSION.element).get(0).getText());

    optionalSrcProtein.ifPresent(
      srcProtein -> {
        entryXMLElem.asJDomElement().getChildren(ENTRY.COMMENT.element)
          .stream()
          .filter(
            commentElem ->
              commentElem.getAttributeValue(ENTRY.COMMENT.TYPE.attribute).equals(ENTRY.COMMENT.TYPE.INTERACTION)
          )
          .forEach(
            commentElem -> {
```

these two elements are required by the schema

```java
              final Element srcInteractant  = commentElem
                .getChildren(ENTRY.COMMENT.INTERACTANT.element).get(0);
              final String srcInteractantId = srcInteractant
                .getAttributeValue(ENTRY.COMMENT.INTERACTANT.INTACTID.attribute);
              final Element tgtInteractant  = commentElem
                .getChildren(ENTRY.COMMENT.INTERACTANT.element).get(1);
              final String tgtInteractantId = tgtInteractant
                .getAttributeValue(ENTRY.COMMENT.INTERACTANT.INTACTID.attribute);
```

this is always there, but it is not documented

```java
              final String tgtId = tgtInteractant
                .getChildText(ENTRY.COMMENT.INTERACTANT.ID.element);
```

we now try to get the target protein from the accession index; if it's there, create a protein-protein interaction edge, otherwise a protein-isoform one

```java
              final Optional<Protein<I,RV,RVT,RE,RET>> optionalTgtProtein = graph.proteinAccessionIndex()
                .getVertex(tgtId);
              // tgt is a protein
              if(optionalTgtProtein.isPresent()) {
                // create edge, set properties
                final ProteinProteinInteraction<I,RV,RVT,RE,RET> edge =
                  srcProtein.addOutEdge(graph.ProteinProteinInteraction(), optionalTgtProtein.get());
                Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.ORGANISMSDIFFER.element) ).ifPresent(
                  elem -> edge.set(
                    edge.type().organismsDiffer,
                    Boolean.parseBoolean(elem.getText())
                  )
                );
                Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.EXPERIMENTS.element) ).ifPresent(
                  elem -> edge.set(
                    edge.type().experiments,
                    Integer.parseInt(elem.getText())
                  )
                );
                edge.set(edge.type().intActId1, srcInteractantId);
                edge.set(edge.type().intActId2, tgtInteractantId);
              }
              // tgt is an isoform, or just crap
              else {
                graph.isoformIdIndex()
                  .getVertex(tgtInteractantId)
                  .ifPresent(
                    tgtIsoform -> {
                      // create edge, set properties
                      final ProteinIsoformInteraction<I,RV,RVT,RE,RET> edge =
                        optionalSrcProtein.get().addOutEdge(graph.ProteinIsoformInteraction(), tgtIsoform);
                      Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.ORGANISMSDIFFER.element) ).ifPresent(
                        elem -> edge.set(
                          edge.type().organismsDiffer,
                          Boolean.parseBoolean(elem.getText())
                        )
                      );
                      Optional.ofNullable( commentElem.getChild(ENTRY.COMMENT.EXPERIMENTS.element) ).ifPresent(
                        elem -> edge.set(
                          edge.type().experiments,
                          Integer.parseInt(elem.getText())
                        )
                      );
                      edge.set(edge.type().intActId1, srcInteractantId);
                      edge.set(edge.type().intActId2, tgtInteractantId);
                    }
                  );
              }
            }
        );
      }
    );
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
[main/java/com/bio4j/model/uniprot/vertices/SequenceCaution.java]: ../vertices/SequenceCaution.java.md
[main/java/com/bio4j/model/uniprot/vertices/Disease.java]: ../vertices/Disease.java.md
[main/java/com/bio4j/model/uniprot/vertices/UniGene.java]: ../vertices/UniGene.java.md
[main/java/com/bio4j/model/uniprot/vertices/InterPro.java]: ../vertices/InterPro.java.md
[main/java/com/bio4j/model/uniprot/vertices/RefSeq.java]: ../vertices/RefSeq.java.md
[main/java/com/bio4j/model/uniprot/vertices/Organism.java]: ../vertices/Organism.java.md
[main/java/com/bio4j/model/uniprot/vertices/Country.java]: ../vertices/Country.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineJournal.java]: ../vertices/OnlineJournal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Thesis.java]: ../vertices/Thesis.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pubmed.java]: ../vertices/Pubmed.java.md
[main/java/com/bio4j/model/uniprot/vertices/PIR.java]: ../vertices/PIR.java.md
[main/java/com/bio4j/model/uniprot/vertices/EMBL.java]: ../vertices/EMBL.java.md
[main/java/com/bio4j/model/uniprot/vertices/Institute.java]: ../vertices/Institute.java.md
[main/java/com/bio4j/model/uniprot/vertices/City.java]: ../vertices/City.java.md
[main/java/com/bio4j/model/uniprot/vertices/Reference.java]: ../vertices/Reference.java.md
[main/java/com/bio4j/model/uniprot/vertices/Submission.java]: ../vertices/Submission.java.md
[main/java/com/bio4j/model/uniprot/vertices/Protein.java]: ../vertices/Protein.java.md
[main/java/com/bio4j/model/uniprot/vertices/Journal.java]: ../vertices/Journal.java.md
[main/java/com/bio4j/model/uniprot/vertices/Dataset.java]: ../vertices/Dataset.java.md
[main/java/com/bio4j/model/uniprot/vertices/Publisher.java]: ../vertices/Publisher.java.md
[main/java/com/bio4j/model/uniprot/vertices/Patent.java]: ../vertices/Patent.java.md
[main/java/com/bio4j/model/uniprot/vertices/Pfam.java]: ../vertices/Pfam.java.md
[main/java/com/bio4j/model/uniprot/vertices/AlternativeProduct.java]: ../vertices/AlternativeProduct.java.md
[main/java/com/bio4j/model/uniprot/vertices/Keyword.java]: ../vertices/Keyword.java.md
[main/java/com/bio4j/model/uniprot/vertices/UnpublishedObservation.java]: ../vertices/UnpublishedObservation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Book.java]: ../vertices/Book.java.md
[main/java/com/bio4j/model/uniprot/vertices/DB.java]: ../vertices/DB.java.md
[main/java/com/bio4j/model/uniprot/vertices/Isoform.java]: ../vertices/Isoform.java.md
[main/java/com/bio4j/model/uniprot/vertices/Consortium.java]: ../vertices/Consortium.java.md
[main/java/com/bio4j/model/uniprot/vertices/ReactomeTerm.java]: ../vertices/ReactomeTerm.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneName.java]: ../vertices/GeneName.java.md
[main/java/com/bio4j/model/uniprot/vertices/Ensembl.java]: ../vertices/Ensembl.java.md
[main/java/com/bio4j/model/uniprot/vertices/OnlineArticle.java]: ../vertices/OnlineArticle.java.md
[main/java/com/bio4j/model/uniprot/vertices/CommentType.java]: ../vertices/CommentType.java.md
[main/java/com/bio4j/model/uniprot/vertices/GeneLocation.java]: ../vertices/GeneLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/FeatureType.java]: ../vertices/FeatureType.java.md
[main/java/com/bio4j/model/uniprot/vertices/Taxon.java]: ../vertices/Taxon.java.md
[main/java/com/bio4j/model/uniprot/vertices/Article.java]: ../vertices/Article.java.md
[main/java/com/bio4j/model/uniprot/vertices/Kegg.java]: ../vertices/Kegg.java.md
[main/java/com/bio4j/model/uniprot/vertices/SubcellularLocation.java]: ../vertices/SubcellularLocation.java.md
[main/java/com/bio4j/model/uniprot/vertices/Person.java]: ../vertices/Person.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportIsoformSequences.java]: ImportIsoformSequences.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportProteinInteractions.java]: ImportProteinInteractions.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtEdges.java]: ImportUniProtEdges.java.md
[main/java/com/bio4j/model/uniprot/programs/XMLConstants.java]: XMLConstants.java.md
[main/java/com/bio4j/model/uniprot/programs/ImportUniProtVertices.java]: ImportUniProtVertices.java.md
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