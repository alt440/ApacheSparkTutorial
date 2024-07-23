## sbt project compiled with Scala 2

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 2 REPL.

SBT is the "Scala Build Tool", which is the tool that will be helping you build and run the application. See https://docs.scala-lang.org/getting-started/index.html to get started with Scala.

You can create a new Scala 2 project using 'sbt new scala/hello-world.g8'. Scala 2.13 is required for Apache Spark (as of today, the most recent version of Spark uses Scala 2.13).

### Dataset

The dataset has been taken from kaggle.com . I do not own this data.

### Setup

I used Visual Studio Code for this project. All I needed to do was follow the instructions on docs.scala-lang.org, and then download a Scala extension for syntax highlighting.

I did have an issue with SBT. I needed to have admin privileges to run SBT after the configuration. The sbt command was not being recognized when I wasn't an admin.

If you have any issue relating to the Java version used with Scala, use the following command to force the runtime to use Java 8:
`sbt -java-home "C:\Program Files\Java\YYY" run`, where 'YYY' represents the JDK folder.

Note that this program does not completely work. Apache Spark does not allow for SQL query modification, which leaves me hanging when attempting to use MariaDB with Apache Spark. MariaDB does not allow to declare columns surrounded by double quotes, which Apache Spark generates by default. To correct this, one could switch to another database configuration (either Postgresql, MongoDB, SQL, etc.) and see if it works. Those are more popular choices than MariaDB.
