name := "training001"
version := "0.1"
scalaVersion := "2.13.8"

// https://mvnrepository.com/artifact/log4j/log4j
                    // group-id    artifact-id    version
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.36"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.36"
libraryDependencies += "log4j" % "log4j" % "1.2.17"
libraryDependencies += "com.h2database" % "h2" % "2.1.210"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "3.5.0"
libraryDependencies += "com.lihaoyi" %% "cask" % "0.8.0"
libraryDependencies += "io.circe" %% "circe-generic" % "0.14.1"
libraryDependencies += "com.softwaremill.sttp.client3" %% "core" % "3.5.1"
libraryDependencies += "com.softwaremill.sttp.client3" %% "circe" % "3.5.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.2.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.2.1"

libraryDependencies += "com.github.javafaker" % "javafaker" % "1.0.2"