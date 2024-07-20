## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

SBT is the "Scala Build Tool", which is the tool that will be helping you build and run the application. See https://docs.scala-lang.org/getting-started/index.html to get started with Scala.

You can create a new Scala 2 project using 'sbt new scala/hello-world.g8'. Scala 2.13 is required for Apache Spark (as of today, the most recent version of Spark uses Scala 2.13).

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

### Dataset

The dataset has been taken from kaggle.com . I do not own this data.

### Setup

I used Visual Studio Code for this project. All I needed to do was follow the instructions on docs.scala-lang.org, and then download a Scala extension for syntax highlighting.

I did have an issue with SBT. I needed to have admin privileges to run SBT after the configuration. The sbt command was not being recognized when I wasn't an admin.
