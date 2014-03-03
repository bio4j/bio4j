resolvers ++= Seq(
  "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com",
  "Era7 maven snapshots" at "http://snapshots.era7.com.s3.amazonaws.com",
  "sbt-taglist-releases" at "http://johanandren.github.com/releases/",
  "laughedelic maven releases" at "http://dl.bintray.com/laughedelic/maven",
  Resolver.url("laughedelic sbt-plugins", url("http://dl.bintray.com/laughedelic/sbt-plugins"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("ohnosequences" % "nice-sbt-settings" % "0.4.0-M2")
