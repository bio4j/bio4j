Nice.javaProject

name          := "bio4j"
organization  := "bio4j"
description   := "Bio4j data model and generic API"

javaVersion   := "1.8"

libraryDependencies ++= Seq(
  "bio4j"         % "angulillos"    % "0.9.0",
  "org.jdom"      % "jdom2"         % "2.0.6"
)

bucketSuffix := "era7.com"

def pathContains(segment: String) = new FileFilter {
  def accept(file: File) = file.getPath.split("/").contains(segment)
}

excludeFilter in unmanagedSources :=
  (excludeFilter in unmanagedSources).value ||
  pathContains("vertices")                  ||
  pathContains("edges")                     ||
  pathContains("programs")                  ||
  pathContains("go")                        ||
  pathContains("unigene")                   ||
  pathContains("uniprot")                   ||
  pathContains("uniprot_enzymedb")          ||
  pathContains("uniprot_go")                ||
  pathContains("uniprot_ncbiTaxonomy")      ||
  pathContains("uniprot_uniref")            ||
  pathContains("uniref")                    ||
  pathContains("xml")
