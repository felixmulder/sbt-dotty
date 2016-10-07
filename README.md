sbt-dotty
=========

[![Build Status](https://travis-ci.org/felixmulder/sbt-dotty.svg?branch=master)](https://travis-ci.org/felixmulder/sbt-dotty)

This plugin allows you to easily create an sbt dotty project using the
standard structure:

```
.
├── build.sbt
├── project
│   ├── build.properties
│   └── plugins.sbt
└── src
```

Please see the
[example](https://github.com/felixmulder/sbt-dotty/tree/master/example)
project.

build.sbt
---------
```
name := "application"
version := "0.1"
enablePlugins(DottyPlugin)
```

If you want to migrate an existing library, it might be a good idea to start
out with the compatibility mode by adding

```
scalacOptions ++= Seq("-language:Scala2")
```

to `build.sbt`.

build.properties
----------------
```
sbt.version=0.13.11
```

plugins.sbt
-----------
```
addSbtPlugin("com.felixmulder" % "sbt-dotty" % "0.1.2")
```

Troubleshooting
---------------
* **P:** I'm getting unresolved dependency errors.

  **A:** You've probably set `scalaVersion` in your `build.sbt` file. The
  plugin takes care of setting the correct `scalaVersion` so please don't
  overwrite this.
