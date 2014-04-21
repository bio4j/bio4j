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
  "com.thinkaurelius.titan"   % "titan-all"   % "0.4.2"     % "test",
  "org.scalatest"            %% "scalatest"   % "2.0"       % "test"
)

bucketSuffix := "era7.com"

dependencyOverrides ++= Set(
  "tomcat" % "jasper-compiler" % "5.5.23",
  "tomcat" % "jasper-runtime" % "5.5.23"
)

// scalaVersion := "2.11.0-M4"

// the new cool option for super-fast recompiling from sbt-0.13.2
// incOptions := incOptions.value.withNameHashing(true)
