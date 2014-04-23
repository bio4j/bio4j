Nice.scalaProject

organization := "bio4j"

name := "scala-model"

description := "scala-model project"

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "com.chuusai"               % "shapeless"   % "2.0.0"  cross CrossVersion.full,
  "com.thinkaurelius.titan"   % "titan-all"   % "0.4.4"     % "test",
  "org.scalatest"            %% "scalatest"   % "2.1.3"     % "test"
)

bucketSuffix := "era7.com"

dependencyOverrides ++= Set(
  "tomcat" % "jasper-compiler" % "5.5.23",
  "tomcat" % "jasper-runtime" % "5.5.23"
)

// 2.11.0-RC4 is the same as the released 2.11.0, but there is no shapeless artifact for it yet
scalaVersion := "2.11.0-RC4"
// scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.0-RC4")

// the new cool option for super-fast recompiling from sbt-0.13.2
// but it doesn't work, don't know why...
// incOptions := incOptions.value.withNameHashing(true)
