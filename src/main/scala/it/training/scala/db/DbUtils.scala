package it.training.scala.db

import org.apache.log4j.{BasicConfigurator, Level, Logger}

object DbUtils extends App {

  BasicConfigurator.configure()
  Logger.getRootLogger.setLevel(Level.INFO)

  val dbServer = new MyDBServer
  val h2running = dbServer.startDb()

  var lastCmd = ""

  while (lastCmd != "quit") {
    lastCmd = scala.io.StdIn.readLine("type 'quit' to stop DB...> ")
  }

  dbServer.stop()
}
