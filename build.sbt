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

// tmp for this PR
val srcPath = "src/main/java/com/bio4j/model"

excludeFilter in unmanagedSources :=
  (excludeFilter in unmanagedSources).value ||
  "*/programs/*.java"                       ||
  "*/vertices/*.java"                       ||
  "*/edges/*.java"                          ||
  s"${srcPath}/go/*"                        ||
  s"${srcPath}/unigene/*"                   ||
  s"${srcPath}/uniprot/*"                   ||
  s"${srcPath}/uniprot_enzymedb/*"          ||
  s"${srcPath}/uniprot_go/*"                ||
  s"${srcPath}/uniprot_ncbiTaxonomy/*"      ||
  s"${srcPath}/uniprot_uniref/*"            ||
  s"${srcPath}/uniref/*"
