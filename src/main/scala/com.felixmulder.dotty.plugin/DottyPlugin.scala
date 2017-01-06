package com.felixmulder.dotty.plugin

import sbt._
import sbt.Keys._

object DottyPlugin extends AutoPlugin {
  override def requires: Plugins = plugins.JvmPlugin

  override def projectSettings: Seq[Setting[_]] = {
    
    val dottyVersion = sys.env.get("COMPILERVERSION") getOrElse {
      "0.1-20170105-42eb864-NIGHTLY"
    }

    val dottyBridgeVersion = sys.env.get("BRIDGEVERSION") getOrElse {
      "0.1.1-20170105-42eb864-NIGHTLY"
    }

    Seq(
      // Dotty version
      scalaVersion := dottyVersion,
      scalaOrganization := "ch.epfl.lamp",

      // Dotty is compatible with Scala 2.11, as such you can use 2.11
      // binaries. However, when publishing - this version number should be set
      // to 0.1 (the dotty version number)
      scalaBinaryVersion := "2.11",

      // Don't import the stdlib for "scalaBinaryVersion"
      autoScalaLibrary := false,

      libraryDependencies ++= Seq(
        // Dotty depends on stdlib 2.11.5, best use that too (dottyVersion is
        // actually 2.11.5, published under ch.epfl.lamp)
        "ch.epfl.lamp" % "scala-library_2.11" % dottyVersion,

        // Compiler on tool path
        "ch.epfl.lamp" % "dotty_2.11" % dottyVersion % "scala-tool"
      ),

      // Bridge which allows REPL and compilation via dotty
      scalaCompilerBridgeSource := ("ch.epfl.lamp" % "dotty-sbt-bridge" % dottyBridgeVersion % "component").sources()
    )
  }
}
