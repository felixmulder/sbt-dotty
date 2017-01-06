import sbt.Keys._
import sbt._

lazy val root = (project in file("."))
  .settings(commonSettings ++ publishingSettings)
  

lazy val commonSettings = Seq(
  organization := "com.felixmulder",
  name := "sbt-dotty",
  version := "0.1.5",
  scalacOptions ++= Seq("-feature", "-deprecation", "-encoding", "utf8"),
  scalaVersion := "2.10.6",
  sbtPlugin := true
)

lazy val publishingSettings = Seq(
  publishMavenStyle := true,
  publishArtifact := true,
  isSnapshot := version.value.contains("SNAPSHOT"),
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  homepage := Some(url("https://github.com/felixmulder/sbt-dotty")),
  licenses += ("BSD New",
    url("https://github.com/felixmulder/sbt-dotty/blob/master/LICENSE")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/felixmulder/sbt-dotty"),
      "scm:git:git@github.com:felixmulder/sbt-dotty.git"
    )
  ),
  developers += Developer(
    id = "felixmulder",
    name = "Felix Mulder",
    email = "felix.mulder@gmail.com",
    url = url("http://felixmulder.com")
  )
)
