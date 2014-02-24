Nice.javaProject

organization := "bio4j"

name := "bio4j"

description := "Bio4j abstract model"

bucketSuffix := "era7.com"

javacOptions in (Compile, doc) := Seq()

target in (Compile, doc) := baseDirectory.value / "gh-pages" / "docs" / "api" / version.value.stripSuffix("-SNAPSHOT")

cleanFiles += (target in (Compile, doc)).value
