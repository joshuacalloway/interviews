import sbt._

object MyPlugins extends Build {
  lazy val root = Project("root", file(".")) dependsOn (oneJar)
  lazy val oneJar = uri("git://github.com/sbt/sbt-onejar.git#65a61c14d6bbbee0168fd96a5fc8d2b017eda8b9")
}
