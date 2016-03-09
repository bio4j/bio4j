/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.bio4j.model.enzymedb.programs;

import com.bio4j.model.enzymedb.EnzymeDBGraph;
import com.bio4j.model.enzymedb.vertices.Enzyme;
import com.bio4j.angulillos.UntypedGraph;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/*
  ## ENZYME data import

  This class will import [ENZYME](http://enzyme.expasy.org/) into Bio4j assuming an implementation of the ENZYME Bio4j graph.
*/
public abstract class ImportEnzymeDB<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET> {

  /*
    ### ENZYME raw data

    This code assumes a fixed-width-like format, corresponding to that of the `.dat` distribution of ENZYME data:

    - ftp://ftp.expasy.org/databases/enzyme/enzyme.dat

    This format is described in the ENZYME documentation:

    - ftp://ftp.expasy.org/databases/enzyme/enzuser.txt

    A sample entry looks like this:

    ``` txt
    ID   1.1.1.1
    DE   Alcohol dehydrogenase.
    AN   Aldehyde reductase.
    CA   (1) An alcohol + NAD(+) = an aldehyde or ketone + NADH.
    CA   (2) A secondary alcohol + NAD(+) = a ketone + NADH.
    CF   Zn(2+) or Fe cation.
    CC   -!- Acts on primary or secondary alcohols or hemi-acetals with very broad
    CC       specificity; however the enzyme oxidizes methanol much more poorly
    CC       than ethanol.
    CC   -!- The animal, but not the yeast, enzyme acts also on cyclic secondary
    CC       alcohols.
    PR   PROSITE; PDOC00058;
    PR   PROSITE; PDOC00059;
    PR   PROSITE; PDOC00060;
    DR   P07327, ADH1A_HUMAN;  P28469, ADH1A_MACMU;  Q5RBP7, ADH1A_PONAB;
    DR   P25405, ADH1A_SAAHA;  P00325, ADH1B_HUMAN;  Q5R1W2, ADH1B_PANTR;
    DR   P14139, ADH1B_PAPHA;  P25406, ADH1B_SAAHA;  P00327, ADH1E_HORSE;
    DR   P00326, ADH1G_HUMAN;  O97959, ADH1G_PAPHA;  P00328, ADH1S_HORSE;
    DR   P80222, ADH1_ALLMI ;  P30350, ADH1_ANAPL ;  P49645, ADH1_APTAU ;
    DR   P06525, ADH1_ARATH ;  P41747, ADH1_ASPFN ;  Q17334, ADH1_CAEEL ;
    DR   P43067, ADH1_CANAX ;  P85440, ADH1_CATRO ;  P48814, ADH1_CERCA ;
    DR   Q70UN9, ADH1_CERCO ;  P23991, ADH1_CHICK ;  P86883, ADH1_COLLI ;
    DR   P19631, ADH1_COTJA ;  P23236, ADH1_DROHY ;  P48586, ADH1_DROMN ;
    DR   P09370, ADH1_DROMO ;  P22246, ADH1_DROMT ;  P07161, ADH1_DROMU ;
    DR   P12854, ADH1_DRONA ;  P08843, ADH1_EMENI ;  P26325, ADH1_GADMC ;
    DR   Q9Z2M2, ADH1_GEOAT ;  Q64413, ADH1_GEOBU ;  Q64415, ADH1_GEOKN ;
    DR   P12311, ADH1_GEOSE ;  P05336, ADH1_HORVU ;  P20369, ADH1_KLULA ;
    DR   Q07288, ADH1_KLUMA ;  P00333, ADH1_MAIZE ;  P86885, ADH1_MESAU ;
    DR   P00329, ADH1_MOUSE ;  P80512, ADH1_NAJNA ;  Q9P6C8, ADH1_NEUCR ;
    DR   Q75ZX4, ADH1_ORYSI ;  Q2R8Z5, ADH1_ORYSJ ;  P12886, ADH1_PEA   ;
    DR   P22797, ADH1_PELPE ;  P14219, ADH1_PENAM ;  P41680, ADH1_PERMA ;
    DR   P25141, ADH1_PETHY ;  O00097, ADH1_PICST ;  Q03505, ADH1_RABIT ;
    DR   P06757, ADH1_RAT   ;  P14673, ADH1_SOLTU ;  P80338, ADH1_STRCA ;
    DR   P13603, ADH1_TRIRP ;  P00330, ADH1_YEAST ;  Q07264, ADH1_ZEALU ;
    DR   P20368, ADH1_ZYMMO ;  O45687, ADH2_CAEEL ;  O94038, ADH2_CANAL ;
    DR   P48815, ADH2_CERCA ;  Q70UP5, ADH2_CERCO ;  Q70UP6, ADH2_CERRO ;
    DR   P27581, ADH2_DROAR ;  P25720, ADH2_DROBU ;  P23237, ADH2_DROHY ;
    DR   P48587, ADH2_DROMN ;  P09369, ADH2_DROMO ;  P07160, ADH2_DROMU ;
    DR   P24267, ADH2_DROWH ;  P37686, ADH2_ECOLI ;  P54202, ADH2_EMENI ;
    DR   Q24803, ADH2_ENTHI ;  P42327, ADH2_GEOSE ;  P10847, ADH2_HORVU ;
    DR   P49383, ADH2_KLULA ;  Q9P4C2, ADH2_KLUMA ;  P04707, ADH2_MAIZE ;
    DR   Q4R1E8, ADH2_ORYSI ;  Q0ITW7, ADH2_ORYSJ ;  O13309, ADH2_PICST ;
    DR   P28032, ADH2_SOLLC ;  P14674, ADH2_SOLTU ;  F2Z678, ADH2_YARLI ;
    DR   P00331, ADH2_YEAST ;  F8DVL8, ADH2_ZYMMA ;  P0DJA2, ADH2_ZYMMO ;
    DR   P07754, ADH3_EMENI ;  P42328, ADH3_GEOSE ;  P10848, ADH3_HORVU ;
    DR   P49384, ADH3_KLULA ;  P14675, ADH3_SOLTU ;  P07246, ADH3_YEAST ;
    DR   P08319, ADH4_HUMAN ;  P49385, ADH4_KLULA ;  Q9QYY9, ADH4_MOUSE ;
    DR   Q64563, ADH4_RAT   ;  Q09669, ADH4_SCHPO ;  P80468, ADH4_STRCA ;
    DR   A6ZTT5, ADH4_YEAS7 ;  P10127, ADH4_YEAST ;  Q6XQ67, ADH5_SACPS ;
    DR   P38113, ADH5_YEAST ;  P28332, ADH6_HUMAN ;  P41681, ADH6_PERMA ;
    DR   Q5R7Z8, ADH6_PONAB ;  Q5XI95, ADH6_RAT   ;  P40394, ADH7_HUMAN ;
    DR   Q64437, ADH7_MOUSE ;  P41682, ADH7_RAT   ;  P9WQC0, ADHA_MYCTO ;
    DR   P9WQC1, ADHA_MYCTU ;  O31186, ADHA_RHIME ;  Q7U1B9, ADHB_MYCBO ;
    DR   P9WQC6, ADHB_MYCTO ;  P9WQC7, ADHB_MYCTU ;  P9WQB8, ADHD_MYCTO ;
    DR   P9WQB9, ADHD_MYCTU ;  P33744, ADHE_CLOAB ;  P0A9Q8, ADHE_ECO57 ;
    DR   P0A9Q7, ADHE_ECOLI ;  P81600, ADHH_GADMO ;  P72324, ADHI_RHOS4 ;
    DR   Q9SK86, ADHL1_ARATH;  Q9SK87, ADHL2_ARATH;  A1L4Y2, ADHL3_ARATH;
    DR   Q8VZ49, ADHL4_ARATH;  Q0V7W6, ADHL5_ARATH;  Q8LEB2, ADHL6_ARATH;
    DR   Q9FH04, ADHL7_ARATH;  P81601, ADHL_GADMO ;  P39451, ADHP_ECOLI ;
    DR   O46649, ADHP_RABIT ;  O46650, ADHQ_RABIT ;  Q96533, ADHX_ARATH ;
    DR   Q3ZC42, ADHX_BOVIN ;  Q17335, ADHX_CAEEL ;  Q54TC2, ADHX_DICDI ;
    DR   P46415, ADHX_DROME ;  P19854, ADHX_HORSE ;  P11766, ADHX_HUMAN ;
    DR   P93629, ADHX_MAIZE ;  P28474, ADHX_MOUSE ;  P80360, ADHX_MYXGL ;
    DR   P81431, ADHX_OCTVU ;  A2XAZ3, ADHX_ORYSI ;  Q0DWH1, ADHX_ORYSJ ;
    DR   P80572, ADHX_PEA   ;  O19053, ADHX_RABIT ;  P12711, ADHX_RAT   ;
    DR   P80467, ADHX_SAAHA ;  P86884, ADHX_SCYCA ;  P79896, ADHX_SPAAU ;
    DR   Q9NAR7, ADH_BACOL  ;  P14940, ADH_CUPNE  ;  Q0KDL6, ADH_CUPNH  ;
    DR   Q00669, ADH_DROAD  ;  P21518, ADH_DROAF  ;  P25139, ADH_DROAM  ;
    DR   Q50L96, ADH_DROAN  ;  P48584, ADH_DROBO  ;  P22245, ADH_DRODI  ;
    DR   Q9NG42, ADH_DROEQ  ;  P28483, ADH_DROER  ;  P48585, ADH_DROFL  ;
    DR   P51551, ADH_DROGR  ;  Q09009, ADH_DROGU  ;  P51549, ADH_DROHA  ;
    DR   P21898, ADH_DROHE  ;  Q07588, ADH_DROIM  ;  Q9NG40, ADH_DROIN  ;
    DR   Q27404, ADH_DROLA  ;  P10807, ADH_DROLE  ;  P07162, ADH_DROMA  ;
    DR   Q09010, ADH_DROMD  ;  P00334, ADH_DROME  ;  Q00671, ADH_DROMM  ;
    DR   P25721, ADH_DROMY  ;  Q00672, ADH_DRONI  ;  P07159, ADH_DROOR  ;
    DR   P84328, ADH_DROPB  ;  P37473, ADH_DROPE  ;  P23361, ADH_DROPI  ;
    DR   P23277, ADH_DROPL  ;  Q6LCE4, ADH_DROPS  ;  Q9U8S9, ADH_DROPU  ;
    DR   Q9GN94, ADH_DROSE  ;  Q24641, ADH_DROSI  ;  P23278, ADH_DROSL  ;
    DR   Q03384, ADH_DROSU  ;  P28484, ADH_DROTE  ;  P51550, ADH_DROTS  ;
    DR   B4M8Y0, ADH_DROVI  ;  Q05114, ADH_DROWI  ;  P26719, ADH_DROYA  ;
    DR   P17648, ADH_FRAAN  ;  P48977, ADH_MALDO  ;  P81786, ADH_MORSE  ;
    DR   P9WQC2, ADH_MYCTO  ;  P9WQC3, ADH_MYCTU  ;  P25988, ADH_SCAAL  ;
    DR   Q00670, ADH_SCACA  ;  P00332, ADH_SCHPO  ;  Q2FJ31, ADH_STAA3  ;
    DR   Q2G0G1, ADH_STAA8  ;  Q2YSX0, ADH_STAAB  ;  Q5HI63, ADH_STAAC  ;
    DR   Q99W07, ADH_STAAM  ;  Q7A742, ADH_STAAN  ;  Q6GJ63, ADH_STAAR  ;
    DR   Q6GBM4, ADH_STAAS  ;  Q8NXU1, ADH_STAAW  ;  Q5HRD6, ADH_STAEQ  ;
    DR   Q8CQ56, ADH_STAES  ;  Q4J781, ADH_SULAC  ;  P39462, ADH_SULSO  ;
    DR   P50381, ADH_SULSR  ;  Q96XE0, ADH_SULTO  ;  P51552, ADH_ZAPTU  ;
    DR   P32771, FADH_YEAST ;  A7ZIA4, FRMA_ECO24 ;  Q8X5J4, FRMA_ECO57 ;
    DR   A7ZX04, FRMA_ECOHS ;  A1A835, FRMA_ECOK1 ;  Q0TKS7, FRMA_ECOL5 ;
    DR   Q8FKG1, FRMA_ECOL6 ;  B1J085, FRMA_ECOLC ;  P25437, FRMA_ECOLI ;
    DR   B1LIP1, FRMA_ECOSM ;  Q1RFI7, FRMA_ECOUT ;  P44557, FRMA_HAEIN ;
    DR   P39450, FRMA_PHODP ;  Q3Z550, FRMA_SHISS ;  P73138, FRMA_SYNY3 ;
    DR   P71017, GBSB_BACSU ;  P33010, TERPD_PSESP;  O07737, Y1895_MYCTU;
    ```

    Again, these are the tags identifying the data found in each line:
  */
  public static final String IDENTIFICATION_LINE_CODE               = "ID";
  public static final String OFFICIAL_NAME_LINE_CODE                = "DE";
  public static final String ALTERNATE_NAME_LINE_CODE               = "AN";
  public static final String CATALYTIC_ACTIVITY_LINE_CODE           = "CA";
  public static final String COMMENTS_LINE_CODE                     = "CC";
  public static final String COFACTORS_LINE_CODE                    = "CF";
  public static final String PROSITE_CROSS_REFERENCES_LINE_CODE     = "PR";
  public static final String SWISS_PROT_CROSS_REFERENCES_LINE_CODE  = "DR";
  public static final String TERMINATION_LINE_CODE                  = "//";

  /* Configure the file used for logging */
  private static final Logger logger = Logger.getLogger("ImportEnzymeDB");
  private static FileHandler fh;

  /* Implementing classes need to provide a means of instantiating a ENZYME graph from a file */
  protected abstract EnzymeDBGraph<I,RV,RVT,RE,RET> config(String dbFolder, String propertiesFile);

  public final void importEnzymeDB(String[] args) {

    if (args.length != 3) {
      System.out.println(
          "This program expects the following parameters: \n"
        + "1. Enzyme DB data file (ftp://ftp.expasy.org/databases/enzyme/enzyme.dat)\n"
        + "2. Bio4j DB folder\n"
        + "3. DB Properties file (.properties)\n"
      );
    }
    else {

      /* get arguments, create variables */
      File inFile           = new File(args[0]);
      String dbFolder       = args[1];
      String propertiesFile = args[2];

      /* Create the graph instance */
      EnzymeDBGraph<I,RV,RVT,RE,RET> enzymeDBGraph = config(dbFolder, propertiesFile);


      BufferedWriter statsBuff = null;
      long initTime = System.nanoTime();
      int enzymeCounter = 0;

      try {

        /* logging configuration */
        fh = new FileHandler("ImportEnzymeDB.log", true);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);

        /* Initialize the stats writer */
        statsBuff = new BufferedWriter(new FileWriter(new File("ImportEnzymeDBStats.txt")));

        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        String line;

        /* These variables correspond to the current ENZYME entry */
        boolean enzymeFound           = false;
        String officialName           = "";
        String enzymeId               = "";
        String commentsSt             = "";
        String catalyticActivity      = "";
        List<String> cofactors        = new LinkedList<>();
        List<String> prositeCrossRefs = new LinkedList<>();
        boolean deletedEntry          = false;
        boolean transferredEntry      = false;

        System.out.println("Reading file "+inFile.toString());

        while( (line = reader.readLine()) != null ) {

          if(line.startsWith(IDENTIFICATION_LINE_CODE)) {
            enzymeFound = true;
            enzymeId = line.substring(5).trim();
          }
          else if(enzymeFound) {

            if( line.startsWith(OFFICIAL_NAME_LINE_CODE) ) {

              officialName += line.substring(5).trim();

              if(officialName.contains("Deleted entry.")) {
                deletedEntry = true;
              }
              else if(officialName.contains("Transferred entry:")) {
                transferredEntry = true;
              }
            }
            else if(line.startsWith(COFACTORS_LINE_CODE)) {

              String[] cofs = line.substring(5).trim().split(";");
              for (String cofactorSt : cofs) {
                cofactors.add(cofactorSt.trim());
              }
            }
            else if(line.startsWith(PROSITE_CROSS_REFERENCES_LINE_CODE)) {

              String[] proRefs = line.substring(5).trim().split(";");

              for (String prositeSt: proRefs) {
                if (!prositeSt.equals("PROSITE")) { prositeCrossRefs.add(prositeSt.trim()); }
              }
            }
            else if(line.startsWith(COMMENTS_LINE_CODE)) {

              commentsSt += line.substring(5).trim() + " ";
            }
            else if(line.startsWith(CATALYTIC_ACTIVITY_LINE_CODE)) {

              catalyticActivity += line.substring(5).trim() + " ";
            }
            else if(line.startsWith(TERMINATION_LINE_CODE)) {

              if(enzymeFound) {

                if(deletedEntry) {

                  logger.log(Level.INFO, ("Entry with id " + enzymeId + " was deleted. It won't be stored..."));
                  deletedEntry = false;
                }
                else if(transferredEntry) {

                  logger.log(Level.INFO, ("Entry with id " + enzymeId + " was transferred. It won't be stored..."));
                  transferredEntry = false;
                }

                /* at this point we know we have an enzyme. Let's create a new vertex */
                Enzyme<I,RV,RVT,RE,RET> enzyme = enzymeDBGraph.addVertex(enzymeDBGraph.Enzyme());
                /* and then set its properties */
                enzyme.set(enzymeDBGraph.Enzyme().id, enzymeId);
                enzyme.set(enzymeDBGraph.Enzyme().officialName, officialName);
                enzyme.set(enzymeDBGraph.Enzyme().cofactors, cofactors.toArray(new String[0]));
                enzyme.set(enzymeDBGraph.Enzyme().prositeCrossReferences, prositeCrossRefs.toArray(new String[0]));
                enzyme.set(enzymeDBGraph.Enzyme().catalyticActivity, catalyticActivity);
                enzyme.set(enzymeDBGraph.Enzyme().comment, commentsSt);

                /* we got an enzyme inserted, increase the counter */
                enzymeCounter++;
                /* println every 100 enzymes */
                if(enzymeCounter % 100 == 0) { System.out.println(enzymeCounter + " enzymes inserted"); }
              }

              /* reset loop state */
              enzymeFound         = false;
              officialName        = "";
              enzymeId            = "";
              commentsSt          = "";
              catalyticActivity   = "";
              cofactors.clear();
              prositeCrossRefs.clear();
            }
          }
        }

        /* close the file reader */
        reader.close();
      }
      catch (Exception e) {

        logger.log(Level.SEVERE, e.getMessage());
        StackTraceElement[] trace = e.getStackTrace();

        for (StackTraceElement stackTraceElement : trace) {
          logger.log(Level.SEVERE, stackTraceElement.toString());
        }
      }
      finally {

        try {
          //closing logger file handler
          fh.close();
          logger.log(Level.INFO, "Closing up graph service");
          // shutdown, makes sure all changes are written to disk
          enzymeDBGraph.raw().shutdown();

          //-----------------writing stats file---------------------
          long elapsedTime    = System.nanoTime() - initTime;
          long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));

          long hours    = elapsedSeconds/3600;
          long minutes  = (elapsedSeconds % 3600)/60;
          long seconds  = (elapsedSeconds % 3600)%60;

          statsBuff.write("Statistics for program ImportEnzymeDB:\nInput file: " + inFile.getName()
            + "\nThere were " + enzymeCounter + " enzymes inserted.\n" +
            "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

          //---closing stats writer---
          statsBuff.close();
        }
        catch (Exception e) {

          logger.log(Level.SEVERE, e.getMessage());
          StackTraceElement[] trace = e.getStackTrace();

          for (StackTraceElement stackTraceElement : trace) {
            logger.log(Level.SEVERE, stackTraceElement.toString());
          }
        }
      }
    }
  }
}
