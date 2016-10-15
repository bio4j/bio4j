enablePlugins(JavaOnlySettings)

name          := "bio4j"
organization  := "bio4j"
description   := "Bio4j data model and generic API"

javaVersion   := "1.8"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "bio4j"    % "angulillos" % "0.9.1"
)
