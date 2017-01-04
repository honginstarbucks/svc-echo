
lazy val akkaVersion = "2.4.11"
lazy val akkaCirceVersion = "1.10.0"
lazy val circeVersion = "0.6.0"
lazy val logbackVersion = "1.1.3"
lazy val scalatestVersion = "3.0.0"
lazy val scalacheckVersion = "1.13.4"
lazy val scalamockVersion = "3.4.2"

lazy val compileOptions = Seq(
  "-unchecked",
  "-deprecation",
  "-language:_"
)

// -----------------------------
// resolvers (source repositories)
// -----------------------------
val sharedSettings: Seq[Def.Setting[_]] = Defaults.coreDefaultSettings ++ Seq(
  organization := "starbucks",
  scalaVersion := "2.11.8",
  crossScalaVersions := Seq("2.10.6", "2.11.8"),
  resolvers ++= Seq(
    Resolver.jcenterRepo,
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
  ),
  scalacOptions in ThisBuild ++= Seq(
    "-language:_",
    "-Yinline-warnings",
    "-Xlint",
    "-deprecation",
    "-feature",
    "-unchecked"
  ),
  logLevel in ThisBuild := Level.Info,
  libraryDependencies ++= commonDependencies,
  fork in Test := true,
  javaOptions ++= Seq(
    "-Xmx1G",
    "-Djava.net.preferIPv4Stack=true",
    "-Dio.netty.resourceLeakDetection"
  ),
  parallelExecution in ThisBuild := false
)

lazy val commonDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,

  "ch.qos.logback" % "logback-classic" % logbackVersion,

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  // for testing
  "org.scalatest" %% "scalatest" % scalatestVersion % "test",
  "org.scalactic" %% "scalactic" % scalatestVersion,
  "org.scalamock" %% "scalamock-scalatest-support" % scalamockVersion % "test",
  "org.scalacheck" %% "scalacheck" % scalacheckVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion
)

lazy val akkaStreamDependencies = Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.13",

  "org.apache.kafka" %% "kafka" % "0.10.0.1" exclude("org.slf4j", "slf4j-log4j12")
    exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri"),
  "org.apache.kafka" % "kafka-clients" % "0.10.0.1"
)

lazy val macWireDependencies = Seq(
  "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided",
  "com.softwaremill.macwire" %% "util" % "2.2.5",
  "com.softwaremill.macwire" %% "proxy" % "2.2.5"
)

lazy val httpDependencies = Seq(
  "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "de.heikoseeberger" %% "akka-http-circe" % "1.11.0"
)

// ---------------------------------------------------------
// projects
// ---------------------------------------------------------

lazy val root = Project(id = "svc-echo", base = file("."))
  .settings(sharedSettings: _*)
  .settings(libraryDependencies ++= akkaStreamDependencies)
  .settings(libraryDependencies ++= macWireDependencies)
  .settings(libraryDependencies ++= httpDependencies)
  .enablePlugins(DockerPlugin)
  .settings(
    dockerExposedPorts := Seq(8080)
  )
 .settings(
    name := "svc-echo",
    version := "0.1"
  )
  .settings(aggregate in Docker := false)
