curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot.xml.gz' -o uniprot_sprot.xml.gz
curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_trembl.xml.gz' -o uniprot_trembl.xml.gz
curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/current_release/knowledgebase/complete/uniprot_sprot_varsplic.fasta.gz' -o uniprot_sprot_varsplic.fasta.gz
curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref100/uniref100.xml.gz' -o uniref100.xml.gz
curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref90/uniref90.xml.gz' -o uniref90.xml.gz
curl 'ftp://ftp.uniprot.org/pub/databases/uniprot/uniref/uniref50/uniref50.xml.gz' -o uniref50.xml.gz
curl 'http://archive.geneontology.org/latest-termdb/go_daily-termdb.obo-xml.gz' -o go.xml.gz
curl 'ftp://ftp.ncbi.nih.gov/pub/taxonomy/gi_taxid_nucl.dmp.gz' -o gi_taxid_nucl.dmp.gz
curl 'ftp://ftp.expasy.org/databases/enzyme/enzyme.dat' -o enzyme.dat
gzip -d *.gz
curl 'ftp://ftp.ncbi.nih.gov/pub/taxonomy/taxdump.tar.gz' -o taxdump.tar.gz
tar -xvf taxdump.tar.gz
mkdir refseq_data
cd refseq_data
wget 'ftp://ftp.ncbi.nih.gov/refseq/release/complete/*.gbff.gz'
gzip -d *.gz