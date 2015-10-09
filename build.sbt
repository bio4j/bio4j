Nice.javaProject

name          := "bio4j"
organization  := "bio4j"
description   := "Bio4j data model and generic API"

javaVersion   := "1.8"

libraryDependencies ++= Seq(
  "bio4j"         % "angulillos"    % "0.4.1",
  "ohnosequences" % "bioinfo-util"  % "1.5.0"
)

dependencyOverrides ++= Set(
  "com.fasterxml.jackson.core"  % "jackson-core"            % "2.1.2",
  "com.fasterxml.jackson.core"  % "jackson-databind"        % "2.1.2",
  "com.fasterxml.jackson.core"  % "jackson-annotations"     % "2.1.1",
  "commons-logging"             % "commons-logging"         % "1.2",
  "commons-codec"               % "commons-codec"           % "1.7",
  "commons-beanutils"           % "commons-beanutils"       % "1.8.3",
  "commons-beanutils"           % "commons-beanutils-core"  % "1.8.3",
  "net.sf.opencsv"              % "opencsv"                 % "2.3"
)

bucketSuffix := "era7.com"
