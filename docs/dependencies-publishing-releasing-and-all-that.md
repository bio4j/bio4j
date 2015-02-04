# Dependencies, publishing, releasing and all that

## Depending on Bio4j projects

### SBT

To add a Bio4j project to your dependencies, you need to add the resolver for the repository hosting the Bio4j artifacts, and then add those you need. For example, if you want `bio4j/bio4j` version `0.12.0` and `bio4j/bio4j-titan` version `0.4.0-SNAPSHOT`, add the following to your `build.sbt`

``` scala
// add this S3-backed https resolver
resolvers += "Era7 maven releases" at "https://s3-eu-west-1.amazonaws.com/releases.era7.com"

// the standard sbt way of adding deps
libraryDependencies += Seq(
  "bio4j" % "bio4j"       % "0.12.0"
  "bio4j" % "bio4j-titan" % "0.4.0-SNAPSHOT"
) 
```

## Developing Bio4j

We use [sbt](https://www.scala-sbt.org) for everything related with building code, publishing artifacts, writing github releases, etc. Almost everything is already set up and configured for you at the level of an sbt plugin from the fine folks at _oh no sequences!_

- [ohnosequences/nice-sbt-settings](https://github.com/ohnosequences/nice-sbt-settings)

## Naming conventions

**All** Bio4j projects are published with 

- `bio4j` as the organization name
- the github repository name as the artifact name

So for example for [bio4j/bio4j](https://github.com/bio4j/bio4j) we have `bio4j % bio4j`, while for [bio4j/bio4j-titan](https://github.com/bio4j/bio4j-titan) is `bio4j % bio4j-titan`. Or, if you prefer Mavenesse 

``` xml
<groupId>bio4j</groupId>
<artifactId>bio4j-titan</artifactId>
```

## Releasing

> Incomplete, feel free to expand it!

We have a pretty comprehensive and automated generic release process: type `release` inside the sbt shell!