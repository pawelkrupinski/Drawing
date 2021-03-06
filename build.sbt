import AssemblyKeys._

assemblySettings

name := "Drawing"

scalaVersion := "2.10.2"

test in assembly := {}

mainClass in assembly := Some("net.pawel.drawing.Main")

jarName in assembly := "Drawing.jar"

libraryDependencies += "junit" % "junit" % "4.11" % "test" withSources()

libraryDependencies += "org.specs2" %% "specs2" % "2.1" % "test" withSources()

libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test" withSources()