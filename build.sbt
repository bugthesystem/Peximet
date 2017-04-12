name := "Peximet"

version := "1.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.17",
  "com.typesafe.akka" %% "akka-cluster" % "2.4.17"
)

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.ziyasal",
  scalaVersion := "2.10.1",
  test in assembly := {}
)