Nice.scalaProject

organization := "bio4j"

name := "scala-model"

description := "scala-model project"

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "com.chuusai" % "shapeless" % "2.0.0-M1" cross CrossVersion.full
//  "com.chuusai" % "shapeless_2.10.3" % "2.0.0-M1" // alternatively ...
)

bucketSuffix := "era7.com"

// docsOutputDir := "docs/src/"
