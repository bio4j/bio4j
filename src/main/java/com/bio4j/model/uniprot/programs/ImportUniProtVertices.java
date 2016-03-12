package com.bio4j.model.uniprot.programs;

import com.bio4j.model.uniprot.UniProtGraph;
import com.bio4j.model.uniprot.vertices.*;
import com.bio4j.model.uniprot.edges.*;

import static com.bio4j.model.uniprot.programs.XMLConstants.*;

import com.bio4j.angulillos.UntypedGraph;

import com.ohnosequences.xml.api.model.XMLElement;
import com.ohnosequences.xml.model.bio4j.UniprotDataXML;
import org.jdom2.Element;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public abstract class ImportUniProtVertices<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  protected abstract UniProtGraph<I,RV,RVT,RE,RET> config(File dbFolder);

  private static final Logger logger = Logger.getLogger("ImportUniProtVertices");
  private static FileHandler fh;

  final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  final HashSet<String> alternativeProductTypeNameSet = new HashSet<String>();
  final HashSet<String> articleTitleNameSet           = new HashSet<String>();
  final HashSet<String> bookNameSet                   = new HashSet<String>();
  final HashSet<String> cityNameSet                   = new HashSet<String>();
  final HashSet<String> commentTypeNameSet            = new HashSet<String>();
  final HashSet<String> consortiumNameSet             = new HashSet<String>();
  final HashSet<String> countryNameSet                = new HashSet<String>();
  final HashSet<String> datasetNameSet                = new HashSet<String>();
  final HashSet<String> dbNameSet                     = new HashSet<String>();
  final HashSet<String> diseaseIdSet                  = new HashSet<String>();
  final HashSet<String> eMBLIdSet                     = new HashSet<String>();
  final HashSet<String> ensemblIdSet                  = new HashSet<String>();
  final HashSet<String> featureTypeNameSet            = new HashSet<String>();
  final HashSet<String> geneLocationNameSet           = new HashSet<String>();
  final HashSet<String> geneNameSet                   = new HashSet<String>();
  final HashSet<String> instituteNameSet              = new HashSet<String>();
  final HashSet<String> interproIdSet                 = new HashSet<String>();
  final HashSet<String> isoformIdSet                  = new HashSet<String>();
  final HashSet<String> journalNameSet                = new HashSet<String>();
  final HashSet<String> keggIdSet                     = new HashSet<String>();
  final HashSet<String> keywordIdSet                  = new HashSet<String>();
  final HashSet<String> onlineArticleTitleSet         = new HashSet<String>();
  final HashSet<String> onlineJournalNameSet          = new HashSet<String>();
  final HashSet<String> organismScientificNameSet     = new HashSet<String>();
  final HashSet<String> patentNumberSet               = new HashSet<String>();
  final HashSet<String> personNameSet                 = new HashSet<String>();
  final HashSet<String> pfamIdSet                     = new HashSet<String>();
  final HashSet<String> pIRIdSet                      = new HashSet<String>();
  final HashSet<String> publisherNameSet              = new HashSet<String>();
  final HashSet<String> pubmedIdSet                   = new HashSet<String>();
  final HashSet<String> reactomeTermIdSet             = new HashSet<String>();
  final HashSet<String> refSeqIdSet                   = new HashSet<String>();
  final HashSet<String> sequenceCautionNameSet        = new HashSet<String>();
  final HashSet<String> subcellularLocationNameSet    = new HashSet<String>();
  final HashSet<String> submissionTitleSet            = new HashSet<String>();
  final HashSet<String> taxonNameSet                  = new HashSet<String>();
  final HashSet<String> thesisTitleSet                = new HashSet<String>();
  final HashSet<String> uniGeneIdSet                  = new HashSet<String>();

  protected void importUniProtVertices(File inFile, File dbFolder) {

    final long initTime = System.nanoTime();

    // String currentAccessionId = "";

    final UniProtGraph<I,RV,RVT,RE,RET> graph = config(dbFolder);

    Protein<I,RV,RVT,RE,RET> protein = null;

    BufferedWriter statsBuff = null;

    int proteinCounter      = 0;
    int limitForPrintingOut = 10000;

    try {

      // This block configures the logger with handler and formatter
      fh = new FileHandler("ImportUniProtVertices.log", false);

      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);

      //---creating writer for stats file-----
      statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniProtVerticesStats_" + inFile.getName().split("\\.")[0].replaceAll("/", "_") + ".txt")));

      BufferedReader reader = new BufferedReader(new FileReader(inFile));
      String line;
      StringBuilder entryStBuilder = new StringBuilder();

      while((line = reader.readLine()) != null) {

        if(line.trim().startsWith("<"+ENTRY_TAG_NAME)) {

          while(!line.trim().startsWith("</"+ENTRY_TAG_NAME+">")) {

            entryStBuilder.append(line);
            line = reader.readLine();
          }
          entryStBuilder.append(line);

          XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
          entryStBuilder.delete(0, entryStBuilder.length());

          /* Now we import the protein using the XML element and the graph */
          protein = importProteinFrom(entryXMLElem, graph);

          /* now protein crossrefs */
          importProteinReferences(entryXMLElem, graph);

          /* Protein comments */
          importProteinComments(entryXMLElem, graph, protein, protein.sequence());

          /* Protein features */
          importProteinFeatures(entryXMLElem, graph, protein);

          //--------------------------------datasets--------------------------------------------------
          String proteinDataSetSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_DATASET_ATTRIBUTE);

          if(!datasetNameSet.contains(proteinDataSetSt)) {

            datasetNameSet.add(proteinDataSetSt);

            if(!graph.datasetNameIndex().getVertex(proteinDataSetSt).isPresent()) {

              Dataset<I,RV,RVT,RE,RET> dataset = graph.addVertex(graph.Dataset());
              dataset.set(graph.Dataset().name, proteinDataSetSt);
            }
          }
          //---------------------------------------------------------------------------------------------

          importProteinCitations(entryXMLElem, graph, protein);

          //-------------------------------keywords------------------------------------------------------
          List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren(KEYWORD_TAG_NAME);
          for (Element keywordElem : keywordsList) {

            final String keywordId = keywordElem.getAttributeValue(KEYWORD_ID_ATTRIBUTE);

            if(!keywordIdSet.contains(keywordId)) {

              keywordIdSet.add(keywordId);

              if(graph.keywordIdIndex().getVertex(keywordId).isPresent()) {

                String keywordName = keywordElem.getText();
                Keyword<I,RV,RVT,RE,RET>  keyword = graph.addVertex(graph.Keyword());
                keyword.set(graph.Keyword().id, keywordId);
                keyword.set(graph.Keyword().name, keywordName);
              }
            }
          }

          //---------------------------------------------------------------------------------------
          //--------------------------------geneLocation-------------------------------------------
          List<Element> geneLocationElements = entryXMLElem.asJDomElement().getChildren(GENE_LOCATION_TAG_NAME);

          for(Element geneLocationElem: geneLocationElements){

            String geneLocationTypeSt = geneLocationElem.getAttributeValue("type");

            if(!geneLocationNameSet.contains(geneLocationTypeSt)) {

              geneLocationNameSet.add(geneLocationTypeSt);

              if(!graph.geneLocationNameIndex().getVertex(geneLocationTypeSt).isPresent()) {

                GeneLocation<I,RV,RVT,RE,RET> geneLocation = graph.addVertex(graph.GeneLocation());
                geneLocation.set(graph.GeneLocation().name, geneLocationTypeSt);
              }
            }
          }

          //---------------------------------------------------------------------------------------
          //--------------------------------gene names-------------------------------------------
          final Element geneElement = entryXMLElem.asJDomElement().getChild(GENE_TAG_NAME);

          if(geneElement != null) {

            final List<Element> geneNamesList = geneElement.getChildren(GENE_NAME_TAG_NAME);

            for(Element geneNameElem: geneNamesList) {

              final String geneNameSt = geneNameElem.getText();
              final String typeSt     = geneNameElem.getAttributeValue("type");

              if(!geneNameSet.contains(geneNameSt)) {

                geneNameSet.add(geneNameSt);

                if(!graph.geneNameNameIndex().getVertex(geneNameSt).isPresent()) {

                  GeneName<I,RV,RVT,RE,RET> geneName = graph.addVertex(graph.GeneName());
                  geneName.set(graph.GeneName().name, geneNameSt);
                }
              }
            }
          }
          //---------------------------------------------------------------------------------------

          //---------------------------------------------------------------------------------------
          //--------------------------------organism-----------------------------------------------

          String scName, commName, synName;
          scName    = "";
          commName  = "";
          synName   = "";

          final Element organismElem = entryXMLElem.asJDomElement().getChild(ORGANISM_TAG_NAME);

          List<Element> organismNames = organismElem.getChildren(ORGANISM_NAME_TAG_NAME);

          for(Element element: organismNames) {

            final String type = element.getAttributeValue(ORGANISM_NAME_TYPE_ATTRIBUTE);

            switch(type) {

              case ORGANISM_SCIENTIFIC_NAME_TYPE:
              scName = element.getText();
              break;
              case ORGANISM_COMMON_NAME_TYPE:
              commName = element.getText();
              break;
              case ORGANISM_SYNONYM_NAME_TYPE:
              synName = element.getText();
              break;
            }
          }

          if(!organismScientificNameSet.contains(scName)) {

            organismScientificNameSet.add(scName);

            if(!graph.organismScientificNameIndex().getVertex(scName).isPresent()) {

              final Organism<I,RV,RVT,RE,RET> organism = graph.addVertex(graph.Organism());
              organism.set(graph.Organism().scientificName, scName);
              organism.set(graph.Organism().commonName, commName);
              organism.set(graph.Organism().synonymName, synName);

              // TODO see what to do with the NCBI taxonomy ID, just link to the NCBI tax node or also store the id as an attribute
              //  List<Element> organismDbRefElems = organismElem.getChildren(DB_REFERENCE_TAG_NAME);
              //  boolean ncbiIdFound = false;
              //  if (organismDbRefElems != null) {
              //  for (Element dbRefElem : organismDbRefElems) {
              //    String t = dbRefElem.getAttributeValue("type");
              //    if (t.equals("NCBI Taxonomy")) {
              //    organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, dbRefElem.getAttributeValue("id"));
              //    ncbiIdFound = true;
              //    break;
              //    }
              //  }
              //  }
              //  if (!ncbiIdFound) {
              //  organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, "");
              //  }

              final Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
              List<Element> taxons = lineage.getChildren("taxon");

              final Element firstTaxonElem = taxons.get(0);

              if(!taxonNameSet.contains(firstTaxonElem.getText())) {

                taxonNameSet.add(firstTaxonElem.getText());

                if(!graph.taxonNameIndex().getVertex(firstTaxonElem.getText()).isPresent()) {

                  final String firstTaxonName = firstTaxonElem.getText();

                  final Taxon<I,RV,RVT,RE,RET> firstTaxon = graph.addVertex(graph.Taxon());
                  firstTaxon.set(graph.Taxon().name, firstTaxonName);
                }
              }

              for(int i = 1; i < taxons.size(); i++) {

                final String taxonName = taxons.get(i).getText();

                if(!taxonNameSet.contains(taxonName)) {

                  taxonNameSet.add(taxonName);

                  if(!graph.taxonNameIndex().getVertex(taxonName).isPresent()){
                    Taxon<I,RV,RVT,RE,RET> currentTaxon = graph.addVertex(graph.Taxon());
                    currentTaxon.set(graph.Taxon().name, taxonName);
                  }
                }
              }
            }
          }

          //---------------------------------------------------------------------------------------
          //---------------------------------------------------------------------------------------

          proteinCounter++;

          if((proteinCounter % limitForPrintingOut) == 0) {

            final String countProteinsSt = proteinCounter +" proteins inserted!!";

            logger.log(Level.INFO, countProteinsSt);
            graph.raw().commit();
          }
        }
      }
    }
    catch (Exception e) {

      logger.log(Level.SEVERE, ("Exception retrieving protein " + protein.accession()));
      logger.log(Level.SEVERE, e.getMessage());

      StackTraceElement[] trace = e.getStackTrace();

      for (StackTraceElement stackTraceElement: trace) {

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
        long elapsedTime        = System.nanoTime() - initTime;
        long elapsedSeconds     = Math.round((elapsedTime / 1000000000.0));
        long hours              = elapsedSeconds / 3600;
        long minutes            = (elapsedSeconds % 3600) / 60;
        long seconds            = (elapsedSeconds % 3600) % 60;

        statsBuff.write("Statistics for program ImportUniProtVertices:\nInput file: " + inFile.getName()
        + "\nThere were " + proteinCounter + " proteins inserted.\n"
        + "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

        //---closing stats writer---
        statsBuff.close();
      }
      catch (IOException ex) {

        Logger.getLogger(ImportUniProtVertices.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /*
  This method gets the protein data from the corresponding XML element and writes it to the graph
  */
  private Protein<I,RV,RVT,RE,RET> importProteinFrom(
  XMLElement entryXMLElem,
  UniProtGraph<I,RV,RVT,RE,RET> graph
  )
  throws ParseException {

    final String modifiedDateSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_MODIFIED_DATE_ATTRIBUTE);
    final String createdDateSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_CREATED_DATE_ATTRIBUTE);
    final Integer version = Integer.parseInt(entryXMLElem.asJDomElement().getAttributeValue(ENTRY_VERSION_ATTRIBUTE));

    final String accessionSt = entryXMLElem.asJDomElement().getChildText(ENTRY_ACCESSION_TAG_NAME);
    final String currentAccessionId = accessionSt;

    final String nameSt = entryXMLElem.asJDomElement().getChildText(ENTRY_NAME_TAG_NAME);

    String fullNameSt = getProteinFullName(entryXMLElem.asJDomElement().getChild(PROTEIN_TAG_NAME));
    if(fullNameSt == null) { fullNameSt = ""; }

    String shortNameSt = getProteinShortName(entryXMLElem.asJDomElement().getChild(PROTEIN_TAG_NAME));
    if(shortNameSt == null) { shortNameSt = ""; }

    final Element sequenceElem = entryXMLElem.asJDomElement().getChild(ENTRY_SEQUENCE_TAG_NAME);
    final String sequenceSt = sequenceElem.getText();
    final int seqLength = Integer.parseInt(sequenceElem.getAttributeValue(SEQUENCE_LENGTH_ATTRIBUTE));
    final float seqMass = Float.parseFloat(sequenceElem.getAttributeValue(SEQUENCE_MASS_ATTRIBUTE));

    Protein<I,RV,RVT,RE,RET> protein = graph.addVertex(graph.Protein());

    protein.set(graph.Protein().modifiedDate, parseDate(modifiedDateSt));
    protein.set(graph.Protein().createdDate, parseDate(createdDateSt));
    protein.set(graph.Protein().accession, accessionSt);
    protein.set(graph.Protein().name, nameSt);
    protein.set(graph.Protein().fullName, fullNameSt);
    protein.set(graph.Protein().shortName, shortNameSt);
    protein.set(graph.Protein().sequence, sequenceSt);
    protein.set(graph.Protein().length, seqLength);
    protein.set(graph.Protein().mass, String.valueOf(seqMass));
    protein.set(graph.Protein().version, version);

    return protein;
  }

  private void importProteinReferences(
  XMLElement entryXMLElem,
  UniProtGraph<I,RV,RVT,RE,RET> graph
  )
  {
    //-----db references-------------
    final List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(DB_REFERENCE_TAG_NAME);

    for(Element dbReferenceElem: dbReferenceList) {

      String refId = dbReferenceElem.getAttributeValue("id");

      switch(dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE)) {

        case "Ensembl": {

          // looking for Ensembl vertex
          if(!ensemblIdSet.contains(refId)) {

            ensemblIdSet.add(refId);

            if(!graph.ensemblIdIndex().getVertex(refId).isPresent()) {

              String moleculeIdSt = "";
              String proteinSequenceIdSt = "";
              String geneIdSt = "";

              List<Element> children = dbReferenceElem.getChildren("property");

              for (Element propertyElem: children) {

                if (propertyElem.getAttributeValue("type").equals("protein sequence ID")) {
                  proteinSequenceIdSt = propertyElem.getAttributeValue("value");
                }
                if (propertyElem.getAttributeValue("type").equals("gene ID")) {
                  geneIdSt = propertyElem.getAttributeValue("value");
                }
              }

              Element moleculeTag = dbReferenceElem.getChild("molecule");

              if(moleculeTag != null) {

                moleculeIdSt = moleculeTag.getAttributeValue("id");

                if(moleculeIdSt == null) {

                  moleculeTag.getText();

                  if(moleculeIdSt == null) {

                    moleculeIdSt = "";
                  }
                }
              }

              Ensembl<I,RV,RVT,RE,RET> ensembl = graph.addVertex(graph.Ensembl());
              ensembl.set(graph.Ensembl().id, refId);
              ensembl.set(graph.Ensembl().proteinSequenceId, proteinSequenceIdSt);
              ensembl.set(graph.Ensembl().moleculeId, moleculeIdSt);
              ensembl.set(graph.Ensembl().geneId, geneIdSt);
            }
          }

          break;
        }

        case "PIR": {

          if(!pIRIdSet.contains(refId)) {

            pIRIdSet.add(refId);

            if(!graph.pIRIdIndex().getVertex(refId).isPresent()) {

              String entryNameSt = "";
              List<Element> children = dbReferenceElem.getChildren("property");

              for(Element propertyElem: children) {

                if(propertyElem.getAttributeValue("type").equals("entry name")) {
                  entryNameSt = propertyElem.getAttributeValue("value");
                }
              }

              PIR<I,RV,RVT,RE,RET> pIR = graph.addVertex(graph.PIR());
              pIR.set(graph.PIR().entryName, entryNameSt);
              pIR.set(graph.PIR().id, refId);
            }
          }

          break;
        }

        case "UniGene": {

          // looking for UniGene vertex
          if(!uniGeneIdSet.contains(refId)) {

            uniGeneIdSet.add(refId);

            if(!graph.uniGeneIdIndex().getVertex(refId).isPresent()) {

              UniGene<I,RV,RVT,RE,RET> uniGene = graph.addVertex(graph.UniGene());
              uniGene.set(graph.UniGene().id, refId);
            }
          }

          break;
        }

        case "KEGG": {

          //looking for Kegg vertex
          if(!keggIdSet.contains(refId)) {

            keggIdSet.add(refId);

            if(!graph.keggIdIndex().getVertex(refId).isPresent()) {

              Kegg<I,RV,RVT,RE,RET> kegg = graph.addVertex(graph.Kegg());
              kegg.set(graph.Kegg().id, refId);
            }
          }

          break;
        }

        case "EMBL": {

          //looking for EMBL vertex
          if(!eMBLIdSet.contains(refId)) {

            eMBLIdSet.add(refId);

            if(!graph.eMBLIdIndex().getVertex(refId).isPresent()) {

              String moleculeTypeSt       = "";
              String proteinSequenceIdSt  = "";

              List<Element> children = dbReferenceElem.getChildren("property");

              for(Element propertyElem: children) {

                if(propertyElem.getAttributeValue("type").equals("protein sequence ID")) {

                  proteinSequenceIdSt = propertyElem.getAttributeValue("value");
                }

                if(propertyElem.getAttributeValue("type").equals("molecule type")) {

                  moleculeTypeSt = propertyElem.getAttributeValue("value");
                }
              }

              EMBL<I,RV,RVT,RE,RET> embl = graph.addVertex(graph.EMBL());
              embl.set(graph.EMBL().id, refId);
              embl.set(graph.EMBL().proteinSequenceId, proteinSequenceIdSt);
              embl.set(graph.EMBL().moleculeType, moleculeTypeSt);
            }
          }

          break;
        }

        case "RefSeq": {

          //looking for RefSeq vertex
          if(!refSeqIdSet.contains(refId)) {

            refSeqIdSet.add(refId);

            if(!graph.refSeqIdIndex().getVertex(refId).isPresent()) {

              String nucleotideSequenceIdSt = "";
              List<Element> children = dbReferenceElem.getChildren("property");

              for(Element propertyElem: children) {

                if(propertyElem.getAttributeValue("type").equals("nucleotide sequence ID")) {

                  nucleotideSequenceIdSt = propertyElem.getAttributeValue("value");
                }
              }

              RefSeq<I,RV,RVT,RE,RET> refSeq = graph.addVertex(graph.RefSeq());
              refSeq.set(graph.RefSeq().id, refId);
              refSeq.set(graph.RefSeq().nucleotideSequenceId, nucleotideSequenceIdSt);
            }
          }

          break;
        }

        case "Reactome": {

          if(!reactomeTermIdSet.contains(refId)) {

            reactomeTermIdSet.add(refId);

            if(!graph.reactomeTermIdIndex().getVertex(refId).isPresent()) {

              Element propertyElem = dbReferenceElem.getChild("property");
              String pathwayName = "";

              if(propertyElem.getAttributeValue("type").equals("pathway name")) {

                pathwayName = propertyElem.getAttributeValue("value");
              }

              ReactomeTerm<I,RV,RVT,RE,RET> reactomeTerm = graph.addVertex(graph.ReactomeTerm());
              reactomeTerm.set(graph.ReactomeTerm().id, refId);
              reactomeTerm.set(graph.ReactomeTerm().pathwayName, pathwayName);
            }
          }

          break;
        }

        case INTERPRO_DB_REFERENCE_TYPE: {

          final String interproId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

          if(!interproIdSet.contains(interproId)) {

            interproIdSet.add(interproId);

            if(!graph.interproIdIndex().getVertex(interproId).isPresent()) {

              String interproEntryNameSt = "";

              List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);

              for (Element prop: properties) {

                if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_ENTRY_NAME)) {

                  interproEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
                  break;
                }
              }

              final InterPro<I,RV,RVT,RE,RET> interpro = graph.addVertex(graph.InterPro());
              interpro.set(graph.InterPro().id, interproId);
              interpro.set(graph.InterPro().name, interproEntryNameSt);
            }
          }

          break;
        }

        case "Pfam": {

          final String pfamId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

          if(!pfamIdSet.contains(pfamId)) {

            pfamIdSet.add(pfamId);

            if(!graph.pfamIdIndex().getVertex(pfamId).isPresent()) {

              String pfamEntryNameSt = "";
              List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);

              for(Element prop: properties) {

                if(prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("entry name")) {

                  pfamEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
                  break;
                }
              }

              Pfam<I,RV,RVT,RE,RET> pfam = graph.addVertex(graph.Pfam());
              pfam.set(graph.Pfam().id, pfamId);
              pfam.set(graph.Pfam().name, pfamEntryNameSt);
            }
          }

          break;
        }
      }
    }
  }

  private void importProteinFeatures(
  XMLElement entryXMLElem,
  UniProtGraph<I,RV,RVT,RE,RET> graph,
  Protein<I,RV,RVT,RE,RET> protein
  )
  {

    //--------------------------------features----------------------------------------------------
    final List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(FEATURE_TAG_NAME);

    for(Element featureElem: featuresList) {

      final String featureTypeSt = featureElem.getAttributeValue(FEATURE_TYPE_ATTRIBUTE);

      if(!featureTypeNameSet.contains(featureTypeSt)) {

        featureTypeNameSet.add(featureTypeSt);

        if(!graph.featureTypeNameIndex().getVertex(featureTypeSt).isPresent()) {

          final FeatureType<I,RV,RVT,RE,RET> feature = graph.addVertex(graph.FeatureType());
          feature.set(graph.FeatureType().name, featureTypeSt);
        }
      }
    }
  }

  private void importProteinComments(
  XMLElement entryXMLElem,
  UniProtGraph<I,RV,RVT,RE,RET> graph,
  Protein<I,RV,RVT,RE,RET> protein,
  String proteinSequence
  )
  {

    final List<Element> comments = entryXMLElem.asJDomElement().getChildren(COMMENT_TAG_NAME);

    for(Element commentElem: comments) {

      final String commentTypeSt = commentElem.getAttributeValue(COMMENT_TYPE_ATTRIBUTE);

      if(!commentTypeNameSet.contains(commentTypeSt)) {

        commentTypeNameSet.add(commentTypeSt);

        if(!graph.commentTypeNameIndex().getVertex(commentTypeSt).isPresent()) {

          CommentType<I,RV,RVT,RE,RET> comment = graph.addVertex(graph.CommentType());
          comment.set(graph.CommentType().name, commentTypeSt);
        }
      }

      switch(commentTypeSt) {

        case COMMENT_TYPE_DISEASE: {

          final Element diseaseElement = commentElem.getChild("disease");

          if(diseaseElement != null) {

            final String diseaseId          = diseaseElement.getAttributeValue("id");
            final String diseaseName        = diseaseElement.getChildText("name");
            final String diseaseDescription = diseaseElement.getChildText("description");
            final String diseaseAcronym     = diseaseElement.getChildText("acronym");

            if(diseaseId != null) {

              if(!diseaseIdSet.contains(diseaseId)) {

                diseaseIdSet.add(diseaseId);

                if(!graph.diseaseIdIndex().getVertex(diseaseId).isPresent()) {

                  final Disease<I,RV,RVT,RE,RET> disease = graph.addVertex(graph.Disease());
                  disease.set(graph.Disease().name, diseaseName);
                  disease.set(graph.Disease().id, diseaseId);
                  disease.set(graph.Disease().acronym, diseaseAcronym);
                  disease.set(graph.Disease().description, diseaseDescription);
                }
              }
            }
          }

          break;
        }

        case COMMENT_TYPE_SUBCELLULAR_LOCATION: {

          final List<Element> subcLocations = commentElem.getChildren(SUBCELLULAR_LOCATION_TAG_NAME);

          for(Element subcLocation: subcLocations) {

            final List<Element> locations = subcLocation.getChildren(LOCATION_TAG_NAME);

            for(int i = 0; i < locations.size(); i++) {

              final String tempLocationSt = locations.get(i).getTextTrim();

              Optional<SubcellularLocation<I,RV,RVT,RE,RET>> tempLocationOptional =  graph.subcellularLocationNameIndex().getVertex(tempLocationSt);

              if(!subcellularLocationNameSet.contains(tempLocationSt)) {

                subcellularLocationNameSet.add(tempLocationSt);

                if(!graph.subcellularLocationNameIndex().getVertex(tempLocationSt).isPresent()) {

                  final SubcellularLocation<I,RV,RVT,RE,RET> tempLocation = graph.addVertex(graph.SubcellularLocation());
                  tempLocation.set(graph.SubcellularLocation().name, tempLocationSt);
                }
              }
            }
          }

          break;
        }

        case COMMENT_ALTERNATIVE_PRODUCTS_TYPE: {

          final List<Element> eventList   = commentElem.getChildren("event");
          final List<Element> isoformList = commentElem.getChildren("isoform");

          for(Element isoformElem: isoformList) {

            final String isoformIdSt = isoformElem.getChildText("id");

            String isoformNoteSt  = isoformElem.getChildText("note");
            String isoformNameSt  = isoformElem.getChildText("name");
            String isoformSeqSt   = "";

            Element isoSeqElem = isoformElem.getChild("sequence");

            if(isoSeqElem != null) {

              final String isoSeqTypeSt = isoSeqElem.getAttributeValue("type");

              if(isoSeqTypeSt.equals("displayed")) {

                isoformSeqSt = proteinSequence;
              }
            }

            if(isoformNoteSt == null) {

              isoformNoteSt = "";
            }

            if(isoformNameSt == null) {

              isoformNameSt = "";
            }

            if(!isoformIdSet.contains(isoformIdSt)) {

              isoformIdSet.add(isoformIdSt);

              if(!graph.isoformIdIndex().getVertex(isoformIdSt).isPresent()) {

                final Isoform<I,RV,RVT,RE,RET> isoform = graph.addVertex(graph.Isoform());
                isoform.set(graph.Isoform().name, isoformNameSt);
                isoform.set(graph.Isoform().note, isoformNoteSt);
                isoform.set(graph.Isoform().sequence, isoformSeqSt);
                isoform.set(graph.Isoform().id, isoformIdSt);
              }
            }

            for(Element eventElem: eventList) {

              final String eventTypeSt = eventElem.getAttributeValue("type");

              if(!alternativeProductTypeNameSet.contains(eventTypeSt)) {

                alternativeProductTypeNameSet.add(eventTypeSt);

                if(!graph.alternativeProductNameIndex().getVertex(eventTypeSt).isPresent()) {

                  final AlternativeProduct<I,RV,RVT,RE,RET> alternativeProduct = graph.addVertex(graph.AlternativeProduct());
                  alternativeProduct.set(graph.AlternativeProduct().name, eventTypeSt);
                }
              }
            }
          }

          break;
        }

        case COMMENT_SEQUENCE_CAUTION_TYPE: {

          final Element conflictElem = commentElem.getChild("conflict");

          if(conflictElem != null) {

            final String conflictTypeSt = conflictElem.getAttributeValue("type");

            if(!sequenceCautionNameSet.contains(conflictTypeSt)) {

              sequenceCautionNameSet.add(conflictTypeSt);

              if(!graph.sequenceCautionNameIndex().getVertex(conflictTypeSt).isPresent()) {

                SequenceCaution<I,RV,RVT,RE,RET> sequenceCaution = graph.addVertex(graph.SequenceCaution());
                sequenceCaution.set(graph.SequenceCaution().name, conflictTypeSt);
              }
            }
          }

          break;
        }
      }
    }
  }

  private void importProteinCitations(
  XMLElement entryXMLElem,
  UniProtGraph<I,RV,RVT,RE,RET> graph,
  Protein<I,RV,RVT,RE,RET> protein
  )
  {

    final List<Element> referenceList = entryXMLElem.asJDomElement().getChildren(REFERENCE_TAG_NAME);

    for(Element referenceElement: referenceList) {

      final List<Element> citationsList = referenceElement.getChildren(CITATION_TAG_NAME);

      for(Element citation: citationsList) {

        String citationType = citation.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE);

        List<Person<I,RV,RVT,RE,RET>> authorsPerson         = new ArrayList<>();
        List<Consortium<I,RV,RVT,RE,RET>> authorsConsortium = new ArrayList<>();

        List<Element> authorPersonElems     = citation.getChild("authorList").getChildren("person");
        List<Element> authorConsortiumElems = citation.getChild("authorList").getChildren("consortium");

        for(Element personElement: authorPersonElems) {

          final String personName = personElement.getAttributeValue("name");

          if(!personNameSet.contains(personName)) {

            personNameSet.add(personName);

            if(!graph.personNameIndex().getVertex(personName).isPresent()) {

              final Person<I,RV,RVT,RE,RET> person = graph.addVertex(graph.Person());
              person.set(graph.Person().name, personName);
            }
          }
        }

        for(Element consortiumElement: authorConsortiumElems) {

          final String consortiumName = consortiumElement.getAttributeValue("name");

          if(!consortiumNameSet.contains(consortiumName)) {

            consortiumNameSet.add(consortiumName);

            if(!graph.consortiumNameIndex().getVertex(consortiumName).isPresent()) {

              final Consortium<I,RV,RVT,RE,RET> consortium = graph.addVertex(graph.Consortium());
              consortium.set(graph.Consortium().name, consortiumName);
            }
          }
        }

        // start the dance on citation type
        switch(citationType) {

          case THESIS_CITATION_TYPE: {

            String titleSt = citation.getChildText("title");

            if(titleSt == null) {

              titleSt = "";
            }
            else {

              if(!thesisTitleSet.contains(titleSt)) {

                thesisTitleSet.add(titleSt);

                if(!graph.thesisTitleIndex().getVertex(titleSt).isPresent()) {

                  final Thesis<I,RV,RVT,RE,RET> thesis = graph.addVertex(graph.Thesis());
                  thesis.set(graph.Thesis().title, titleSt);

                  String dateSt = citation.getAttributeValue("date");

                  if(dateSt == null) {

                    dateSt = "";
                  }

                  Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
                  reference.set(graph.Reference().id, titleSt + graph.Thesis().name());
                  reference.set(graph.Reference().date, dateSt);
                  reference.addOutEdge(graph.ReferenceThesis(), thesis);
                }

                //-----------institute-----------------------------
                String instituteSt = citation.getAttributeValue("institute");
                String countrySt = citation.getAttributeValue("country");

                if (instituteSt != null) {

                  if(!instituteNameSet.contains(instituteSt)) {

                    instituteNameSet.add(instituteSt);

                    if(!graph.instituteNameIndex().getVertex(instituteSt).isPresent()) {
                      Institute<I,RV,RVT,RE,RET> institute = graph.addVertex(graph.Institute());
                      institute.set(graph.Institute().name, instituteSt);
                    }
                  }

                  if(countrySt != null) {

                    if(!countryNameSet.contains(countrySt)) {

                      countryNameSet.add(countrySt);

                      if(!graph.countryNameIndex().getVertex(countrySt).isPresent()) {

                        Country<I,RV,RVT,RE,RET> country = graph.addVertex(graph.Country());
                        country.set(graph.Country().name, countrySt);
                      }
                    }
                  }
                }
              }
            }
            break;
          }

          case PATENT_CITATION_TYPE: {

            String numberSt = citation.getAttributeValue("number");
            String titleSt  = citation.getChildText("title");

            if(titleSt == null) {
              titleSt = "";
            }
            if(numberSt == null) {
              numberSt = "";
            }

            if (!numberSt.equals("")) {

              if(!patentNumberSet.contains(numberSt)) {

                patentNumberSet.add(numberSt);

                if(!graph.patentNumberIndex().getVertex(numberSt).isPresent()) {

                  Patent<I,RV,RVT,RE,RET> patent = graph.addVertex(graph.Patent());
                  patent.set(graph.Patent().number, numberSt);
                  patent.set(graph.Patent().title, titleSt);

                  String dateSt = citation.getAttributeValue("date");
                  if (dateSt == null) {
                    dateSt = "";
                  }

                  Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
                  reference.set(graph.Reference().id, numberSt + graph.Patent().name());
                  reference.set(graph.Reference().date, dateSt);
                  reference.addOutEdge(graph.ReferencePatent(), patent);
                }
              }
            }

            break;
          }

          case SUBMISSION_CITATION_TYPE: {

            String titleSt = citation.getChildText("title");
            String dbSt = citation.getAttributeValue("db");
            if (titleSt == null) {
              titleSt = "";
            }
            else {

              if(!submissionTitleSet.contains(titleSt)) {

                submissionTitleSet.add(titleSt);

                if(!graph.submissionTitleIndex().getVertex(titleSt).isPresent()) {

                  Submission<I,RV,RVT,RE,RET> submission = graph.addVertex(graph.Submission());
                  submission.set(graph.Submission().title, titleSt);

                  String dateSt = citation.getAttributeValue("date");
                  if(dateSt == null) {
                    dateSt = "";
                  }

                  Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
                  reference.set(graph.Reference().id, titleSt + graph.Submission().name());
                  reference.set(graph.Reference().date, dateSt);
                  reference.addOutEdge(graph.ReferenceSubmission(), submission);
                }

                if(dbSt != null) {

                  if(!dbNameSet.contains(dbSt)) {

                    dbNameSet.add(dbSt);

                    if(!graph.dbNameIndex().getVertex(dbSt).isPresent()) {

                      DB<I,RV,RVT,RE,RET> db = graph.addVertex(graph.DB());
                      db.set(graph.DB().name, dbSt);
                    }
                  }
                }
              }
            }

            break;
          }

          case BOOK_CITATION_TYPE: {

            String nameSt = citation.getAttributeValue("name");
            String titleSt = citation.getChildText("title");
            String publisherSt = citation.getAttributeValue("publisher");
            String citySt = citation.getAttributeValue("city");

            if(nameSt == null) {
              nameSt = "";
            }

            if(titleSt == null) {
              titleSt = "";
            }

            if(publisherSt == null) {
              publisherSt = "";
            }

            if(citySt == null) {
              citySt = "";
            }

            if(!bookNameSet.contains(nameSt)) {

              bookNameSet.add(nameSt);

              if(!graph.bookNameIndex().getVertex(nameSt).isPresent()) {

                final Book<I,RV,RVT,RE,RET> book = graph.addVertex(graph.Book());
                book.set(graph.Book().name, nameSt);

                String dateSt = citation.getAttributeValue("date");

                if (dateSt == null) {
                  dateSt = "";
                }

                final Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
                reference.set(graph.Reference().id, nameSt + graph.Book().name());
                reference.set(graph.Reference().date, dateSt);
                reference.addOutEdge(graph.ReferenceBook(), book);

                //---editor association-----
                final Element editorListElem = citation.getChild("editorList");

                if (editorListElem != null) {

                  List<Element> editorsElems = editorListElem.getChildren("person");

                  for (Element personElement : editorsElems) {

                    final String personName = personElement.getAttributeValue("name");

                    if(!personNameSet.contains(personName)) {

                      personNameSet.add(personName);

                      if(!graph.personNameIndex().getVertex(personName).isPresent()) {

                        final Person<I,RV,RVT,RE,RET> editor = graph.addVertex(graph.Person());
                        editor.set(graph.Person().name, personName);
                      }
                    }
                  }
                }

                //----publisher--
                if (!publisherSt.equals("")) {

                  if(!publisherNameSet.contains(publisherSt)) {

                    publisherNameSet.add(publisherSt);

                    if(!graph.publisherNameIndex().getVertex(publisherSt).isPresent()) {

                      final Publisher<I,RV,RVT,RE,RET> publisher = graph.addVertex(graph.Publisher());
                      publisher.set(graph.Publisher().name, publisherSt);
                    }
                  }
                }

                //-----city-----
                if (!citySt.equals("")) {

                  if(!cityNameSet.contains(citySt)) {

                    cityNameSet.add(citySt);

                    if(!graph.cityNameIndex().getVertex(citySt).isPresent()) {

                      City<I,RV,RVT,RE,RET> city = graph.addVertex(graph.City());
                      city.set(graph.City().name, citySt);
                    }
                  }
                }
              }

            }

            break;
          }

          case ONLINE_ARTICLE_CITATION_TYPE: {

            String nameSt   = citation.getAttributeValue("name");
            String titleSt  = citation.getChildText("title");

            if (titleSt == null) {
              titleSt = "";
            }

            if (nameSt == null) {
              nameSt = "";
            }

            if(!titleSt.equals("")) {

              if(!onlineArticleTitleSet.contains(titleSt)) {

                onlineArticleTitleSet.add(titleSt);

                if(!graph.onlineArticleTitleIndex().getVertex(titleSt).isPresent()) {

                  final OnlineArticle<I,RV,RVT,RE,RET> onlineArticle = graph.addVertex(graph.OnlineArticle());
                  onlineArticle.set(graph.OnlineArticle().title, titleSt);

                  String dateSt = citation.getAttributeValue("date");
                  if (dateSt == null) {
                    dateSt = "";
                  }

                  final Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
                  reference.set(graph.Reference().id, titleSt + graph.OnlineArticle().name());
                  reference.set(graph.Reference().date, dateSt);
                  reference.addOutEdge(graph.ReferenceOnlineArticle(), onlineArticle);

                  //------online journal-----------
                  if (!nameSt.equals("")) {

                    if(!onlineJournalNameSet.contains(nameSt)){

                      onlineJournalNameSet.add(nameSt);

                      if(!graph.onlineJournalNameIndex().getVertex(nameSt).isPresent()) {

                        OnlineJournal<I,RV,RVT,RE,RET> onlineJournal = graph.addVertex(graph.OnlineJournal());
                        onlineJournal.set(graph.OnlineJournal().name, nameSt);
                      }
                    }
                  }
                }
              }
            }

            break;
          }

          case ARTICLE_CITATION_TYPE: {

            String journalNameSt = citation.getAttributeValue("name");
            String titleSt = citation.getChildText("title");
            String doiSt = "";
            String medlineSt = "";
            String pubmedId = "";

            if (journalNameSt == null) {
              journalNameSt = "";
            }

            if (titleSt == null) {
              titleSt = "";
            }

            final List<Element> dbReferences = citation.getChildren("dbReference");

            for(Element tempDbRef: dbReferences) {

              switch(tempDbRef.getAttributeValue("type")) {

                case "DOI":
                doiSt = tempDbRef.getAttributeValue("id");
                break;
                case "MEDLINE":
                medlineSt = tempDbRef.getAttributeValue("id");
                break;
                case "PubMed":
                pubmedId = tempDbRef.getAttributeValue("id");
                break;
              }
            }

            if (titleSt != "") {

              if(!articleTitleNameSet.contains(titleSt)) {

                articleTitleNameSet.add(titleSt);

                if(!graph.articleTitleIndex().getVertex(titleSt).isPresent()) {

                  Article<I,RV,RVT,RE,RET> article = graph.addVertex(graph.Article());
                  article.set(graph.Article().title, titleSt);
                  article.set(graph.Article().doId, doiSt);

                  String dateSt = citation.getAttributeValue("date");
                  if (dateSt == null) {
                    dateSt = "";
                  }

                  final Reference<I,RV,RVT,RE,RET> reference = graph.addVertex(graph.Reference());
                  reference.set(graph.Reference().id, titleSt + graph.Article().name());
                  reference.set(graph.Reference().date, dateSt);
                  reference.addOutEdge(graph.ReferenceArticle(), article);

                  if(pubmedId != "") {

                    if(!pubmedIdSet.contains(pubmedId)) {

                      pubmedIdSet.add(pubmedId);

                      if(!graph.pubmedIdIndex().getVertex(pubmedId).isPresent()) {

                        Pubmed<I,RV,RVT,RE,RET> pubmed = graph.addVertex(graph.Pubmed());
                        pubmed.set(graph.Pubmed().id, pubmedId);
                      }
                    }
                  }

                  //------journal-----------
                  if (!journalNameSt.equals("")) {

                    if(!journalNameSet.contains(journalNameSt)) {

                      journalNameSet.add(journalNameSt);

                      if(!graph.journalNameIndex().getVertex(journalNameSt).isPresent()) {

                        Journal<I,RV,RVT,RE,RET> journal = graph.addVertex(graph.Journal());
                        journal.set(graph.Journal().name, journalNameSt);
                      }
                    }
                  }
                }
              }
            }
            break;
          }
        }
      }
    }
  }

  /*
  ### Random helper methods
  */

  private Date parseDate(String date) throws ParseException {

    return dateFormat.parse(date);
  }

  private static String getProteinFullName(Element proteinElement) {

    if(proteinElement == null) {

      return "";
    }
    else {

      final Element recElem = proteinElement.getChild(PROTEIN_RECOMMENDED_NAME_TAG_NAME);

      if(recElem == null) {

        return "";
      }
      else {

        return recElem.getChildText(PROTEIN_FULL_NAME_TAG_NAME);
      }
    }
  }

  private static String getProteinShortName(Element proteinElement) {

    if(proteinElement == null) {

      return "";
    }
    else {

      final Element recElem = proteinElement.getChild(PROTEIN_RECOMMENDED_NAME_TAG_NAME);

      if(recElem == null) {

        return "";
      }
      else {

        return recElem.getChildText(PROTEIN_SHORT_NAME_TAG_NAME);
      }
    }
  }
}
