val scala2Version = "2.13.14"

lazy val root = project
  .in(file("."))
  .settings(
    name := "apacheSparkTut",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala2Version,
    initialize := {
      val _ = initialize.value // run the previous initialization
      val required = "1.8"
      val current  = sys.props("java.specification.version")
      assert(current == required, s"Unsupported JDK: java.specification.version $current != $required")
    },

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test,
    
    libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.0",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.0",
    libraryDependencies += "org.mariadb.jdbc" % "mariadb-java-client" % "3.4.0"
  )
