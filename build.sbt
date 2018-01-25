name := "bitbay-client"
organization := "tech.block11"
version := "0.1.0-SNAPSHOT"


lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
  )

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ws" % "2.6.7",
  "com.typesafe.play" %% "play-json" % "2.6.7",
  "com.typesafe.play" %% "play-guice" % "2.6.7",
  "com.typesafe.play" %% "play-guice" % "2.6.7",
  "com.typesafe.play" %% "play-logback" % "2.6.7",
  "joda-time" % "joda-time" % "2.8.1",
  "org.joda" % "joda-convert" % "1.7",
  "com.typesafe.play" % "play-json-joda_2.12" % "2.6.0",
  "ca.mrvisser" %% "sealerate" % "0.0.5",
  "com.roundeights" %% "hasher" % "1.2.0",
  "commons-codec" % "commons-codec" % "1.10"
)

//test depenedencies
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ahc-ws" % "2.6.7" % "test,it",
  "org.scalatest" %% "scalatest" % "3.0.4" % "test,it",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test,it"
)

val excludeFileRegx = """(.*?)(dev.conf|otherExcluded)$""".r
mappings in (Compile, packageBin) ~= { (ms: Seq[(File, String)]) =>
  ms filter {
    case (file, toPath)  =>{
      val shouldExclude = excludeFileRegx.pattern.matcher(file.getName).matches
      // println("===========" + file + "  " + shouldExclude)
      !shouldExclude
    }
  }
}


//nexus config
publishTo := {
  val dixrad = "http://nexus.dixrad.com"
  if (isSnapshot.value)
    Some("Dixrad Nexus Repository" at dixrad + "/repository/maven-snapshots/")
  else
    Some("Dixrad Nexus Repository" at dixrad + "/repository/maven-releases/")
}
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
