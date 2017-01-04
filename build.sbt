name := "TypeAliasTest"

version := "1.0"

scalaVersion := "2.12.1"

scalacOptions += "-Ypartial-unification"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1"

libraryDependencies += "com.chuusai" % "shapeless_2.12.0-RC2" % "2.3.2"

parallelExecution in Test := false