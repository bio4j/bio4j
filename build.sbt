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
