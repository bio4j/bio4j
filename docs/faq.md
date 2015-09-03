## FAQ

### General questions

1. **What is Bio4j?** Bio4j is a bioinformatics graph based DB including most data available in UniProt KB(SwissProt + Trembl), Gene Ontology (GO), UniRef (50,90,100), RefSeq (complete release), NCBI taxonomy, and Expasy Enzyme database.
2. **Can I use Bio4j for commercial purposes?**
Bio4j is open source and is released under the **AGPLV3** license, please go [here](http://www.gnu.org/licenses/agpl.html) to get more information about this. The short answer is _Of course, as long as everything you do is AGPLv3 licensed too!_
3. **How can I add/integrate new data to Bio4j?** _TODO_

### Using Bio4j

1. **I cannot (or don't want to) use Amazon Web Services, can I still use Bio4j?** Yes! you will just need to run the import code on your own infrastructure.
2. **How much will it cost me to use Bio4j on Amazon Web Services?** It depends; if you are running your EC2 instances in the `eu-west` region, you'll only pay for the instance costs that you are using. As the preimported binary files are in a S3 bucket in that region, you will also pay for the inter-region S3 costs associated with retrieving objects from a different region.

### Contributing to Bio4j

1. **Bio4j looks cool; is there something I can do?** Sure. The fastest, most effective way is asking at the bio4j/bio4j gitter room: [![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/bio4j/bio4j?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

<!-- ### Building Bio4j

1. **What is the size of the finale files (including all indexes and so on) at the end of the build?** The size of the DB at the end of the build currently is around 75 - 80 G. This is without counting the sequences of the genome elements from RefSeq, (these ones are stored independently as AWS S3 objects). 
2. **Can a build be incremental ? _add or update only what has changed between 2 releases of UniProtKB for example_ ?** Incremental builds are not possible, it would be quite difficult to see what should be changed plus all the repercussions of these changes; so why bother doing that when releases are changed normally in a monthly basis and the whole build does not take much time. Besides, if you're an AWS user, you can get your own EBS volume from our latest Snapshot in just a few seconds!
3. **How long it will take to do a complete build?** It currently takes around 24 hours in a AWS m2.2xlarge machine which basically has: 34.2 GB of memory and 13 EC2 Compute Units (4 virtual cores with 3.25 EC2 Compute Units each).
 -->