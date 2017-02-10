name := "play2torial"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  cache,
  javaJdbc,
  javaWs
)

libraryDependencies += "org.webjars" % "jquery" % "1.11.2"
libraryDependencies += "org.webjars" % "bootstrap" % "2.1.1"
