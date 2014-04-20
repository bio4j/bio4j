Nice.scalaProject

organization := "bio4j"

name := "scala-model"

description := "scala-model project"

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "com.chuusai"               % "shapeless"   % "2.0.0-M1"  cross CrossVersion.full,
  "com.thinkaurelius.titan"   % "titan-core"  % "0.4.2",
  "org.scalatest"            %% "scalatest"   % "2.0"       % "test"
)

bucketSuffix := "era7.com"

// scalaVersion := "2.11.0-M4"

// the new cool option for super-fast recompiling from sbt-0.13.2
// incOptions := incOptions.value.withNameHashing(true)
