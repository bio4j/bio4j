## Steps for importing Bio4j

_If you are not using AWS please go directly to step 6_

#### 1. Launch a new AWS instance (preferably m2.2xlarge)

#### 2. Create a EBS volume of ~400 GB

#### 3. Attach the volume to the instance

#### 4. Create folder `/mnt/bio4j_volume` in the AWS instance

``` bash
mkdir /mnt/bio4j_volume 
```

#### 5. Mount volume

```  bash
mkfs -t ext3 /dev/sdh
mount /dev/sdh /mnt/bio4j_volume
```

#### 6. Download and install official Java 7 JDK
Here's the link to the official website describing how to [install JDK 7 for Linux platforms](http://docs.oracle.com/javase/7/docs/webnotes/install/linux/linux-jdk.html)

#### 7. Get these files:
- [ExecuteBio4jTool.jar](https://s3-eu-west-1.amazonaws.com/bio4j-public/releases/0.9/ExecuteBio4jTool.jar)
- [executionsBio4j.xml](https://github.com/bio4j/neo4jdb/blob/master/executionsBio4j.xml) _(this file can be customized in order to just import a sub-set of the data available, see for example [this one](https://github.com/bio4j/bio4j/blob/v0.7.0/executionsBio4j.xml))_
- [batchInserter.properties](https://github.com/bio4j/neo4jdb/blob/master/batchInserter.properties)  _**IMPORTANT** -->(this file should be changed according to the amount of RAM memory available in your machine)_
- [uniprotData.xml](https://github.com/bio4j/neo4jdb/blob/master/uniprotData.xml) _This file will only be used in the case where you want to import Uniprot module. (Set the boolean flags included in the XML file to true/false depending on your choice of data you want to import from Uniprot)_

#### 8. Import data

+ Download and execute the following bash script:
  - [DownloadAndPrepareBio4jSources.sh](resources/scripts/DownloadAndPrepareBio4jSources.sh)

  This script downloads and decompresses all the sources needed to build a full Bio4j DB (Swissprot, TrEMBL, GO, RefSeq, etc).
  (Once the script has finished, make sure that the final file names coincide with those specified in your XML file executionsBio4j.xml).

+ Tuning executions.xml file

  **Bio4j is divided in modules** and so it is the importing process, that way you don't have to import the whole thing in the case where you are interested only in some of the data sources _( **Gene Ontology**, **NCBI taxonomy tree**, etc...)_. However you must be coherent when importing a set modules, that's to say, for example it's not possible to import the **Uniref clusters** without previously importing **Uniprot KB** - otherwise there wouldn't be proteins to link to in the clusters!

  Here is a diagram showing what resources must be present before importing others:

  ![Bio4j modules dependencies](resources/images/ModuleDependencies.png)

  In order to customize the modules that will be imported you have to modify the file **executionsBio4j.xml**.
  Let's imagine that we want a database including only the Gene Ontology, NCBI taxonomy tree and Uniprot KB (only Swiss-prot entries). 
  The corresponding executions.xml file should look like this:

  **Note:** Don't forget to include InitBio4jDB program in the case where you want to have Uniprot KB module in your database.

  ``` xml
  <scheduled_executions>
    <execution>
      <class_full_name>com.era7.bioinfo.bio4j.programs.InitBio4jDB</class_full_name>
      <arguments>
        <argument>bio4jdb</argument>
        <argument>batchInserter.properties</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.era7.bioinfo.bio4j.programs.ImportEnzymeDB</class_full_name>
      <arguments>
        <argument>enzyme.dat</argument>
        <argument>bio4jdb</argument>
        <argument>batchInserter.properties</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.era7.bioinfo.bio4j.programs.ImportGeneOntology</class_full_name>
      <arguments>
        <argument>go.xml</argument>
        <argument>bio4jdb</argument>
        <argument>batchInserter.properties</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.era7.bioinfo.bio4j.programs.ImportUniprot</class_full_name>
      <arguments>
        <argument>uniprot_sprot.xml</argument>
        <argument>bio4jdb</argument>
        <argument>batchInserter.properties</argument>
      </arguments>
    </execution>
    <execution>
      <class_full_name>com.era7.bioinfo.bio4j.programs.ImportNCBITaxonomy</class_full_name>
      <arguments>
        <argument>nodes.dmp</argument>
        <argument>names.dmp</argument>
        <argument>merged.dmp</argument>
        <argument>bio4jdb</argument>
        <argument>true</argument>
      </arguments>
    </execution>
  </scheduled_executions>
  ```

  **Note**: Check how ImportEnzymeDB program was included in this example - following what was just shown in the module dependencies schema.

+ One more thing, since we are only interested in some of the data sources, it wouldn't make much sense to download the ones we were not going to include. We can avoid that simply removing the lines we don't need from the shell script _DownloadAndPrepareBio4jSources.sh_. For our example it should look something like this:

  ``` bash
  curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot.xml.gz' -o uniprot_sprot.xml.gz
  curl 'http://archive.geneontology.org/latest-termdb/go_daily-termdb.obo-xml.gz' -o go.xml.gz
  curl 'ftp://ftp.expasy.org/databases/enzyme/enzyme.dat' -o enzyme.dat
  gzip -d *.gz
  curl 'ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz' -o taxdump.tar.gz
  tar -xvf taxdump.tar.gz
  ```

#### 9. Launch importing process in background

```  bash
java -d64 -Xmx30G -jar ExecuteBio4jTool.jar executionsBio4j.xml &
```

Different log files will be created at jar folder level regarding the progress of the data importation
