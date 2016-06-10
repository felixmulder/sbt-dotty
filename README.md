sbt-dotty
=========

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


build.sbt
---------

```
name := "application"
version := "0.1"
enablePlugins(DottyPlugin)
```

build.properties
----------------

```
sbt.version=0.13.11
```

plugins.sbt
-----------

```
addSbtPlugin("com.felixmulder" % "sbt-dotty" % "0.1")
```

Troubleshooting
---------------

* **P:** I'm getting unresolved dependency errors.

  **A:** You've probably set `scalaVersion` in your `build.sbt` file. The
  plugin takes care of setting the correct `scalaVersion` so please don't
  overwrite this.
