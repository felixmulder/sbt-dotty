package com.felixmulder.dotty.plugin

import sbt._
import sbt.Keys._

object DottyPlugin extends AutoPlugin {
  override def requires: Plugins = plugins.JvmPlugin

  override def projectSettings: Seq[Setting[_]] = {
    
    // http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22ch.epfl.lamp%22%20dotty
    val dottyVersion = sys.env.get("COMPILERVERSION") getOrElse {
      "0.1.1-20170111-ba7e129-NIGHTLY"
    }

    Seq(
      // Dotty version
      scalaVersion := dottyVersion,
      scalaOrganization := "ch.epfl.lamp",

      // Dotty is compatible with Scala 2.11, as such you can use 2.11
      // binaries. However, when publishing - this version number should be set
      // to 0.1 (the dotty version number)
      scalaBinaryVersion := "2.11",

      // bug in sbt 0.13.13: https://github.com/sbt/sbt/issues/2867
      // should be fixed in 0.13.14
      ivyScala ~= (_ map (_ copy (overrideScalaVersion = false))),

      // Compiler on tool path
      libraryDependencies += "ch.epfl.lamp" % "dotty_2.11" % dottyVersion % "scala-tool",

      // Bridge which allows REPL and compilation via dotty
      scalaCompilerBridgeSource := ("ch.epfl.lamp" % "dotty-sbt-bridge" % scalaVersion.value % "component").sources()
    )
  }
}
