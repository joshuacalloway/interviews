
organization := "getco"

name := "getco"

version := "1.0"

scalaVersion := "2.9.2"

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.0.3",
  "commons-lang" % "commons-lang" % "2.6"
)
