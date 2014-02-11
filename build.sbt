import AssemblyKeys._

Nice.javaProject

Nice.fatArtifactSettings

name := "bio4j"

description := "Bio4j core project, importing classes plus API for traversals and access to the database"

organization := "ohnosequences"

scalaVersion := "2.10.2"

libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += "ohnosequences" % "bioinfo-utils" % "1.1.0"

libraryDependencies += "com.tinkerpop.blueprints" % "blueprints-core" % "2.4.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-berkeleyje" % "0.3.2"

dependencyOverrides += "commons-logging" % "commons-logging" % "1.1.3"

dependencyOverrides += "com.tinkerpop.blueprints" % "blueprints-core" % "2.4.0"

dependencyOverrides += "org.codehaus.jackson" % "jackson-core-asl" % "1.9.2"

dependencyOverrides += "commons-codec" % "commons-codec" % "1.7"

dependencyOverrides += "org.apache.lucene" % "lucene-core" % "4.2.1"

bucketSuffix := "era7.com"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("index.html") => MergeStrategy.first
    case x => old(x)
  }
}

