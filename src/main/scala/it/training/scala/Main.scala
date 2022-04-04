package it.training.scala

import org.apache.log4j.{BasicConfigurator, Level, Logger}

object Main {
  def main(args: Array[String]): Unit = {

    // Collegarsi alla chat:
    // https://tlk.io/scala_training

    // Github:
    // https://github.com/mcartia/4apr_scala001

    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.INFO)

   val myPerson = Person(1L,"Mario","Cartia", "mario.cartia@gmail.com")

    val logger = Logger.getLogger("MyLogger")

    //logger.trace("Person: "+myPerson)
    //logger.debug("Person: "+myPerson)
    logger.info("Person: "+myPerson)
    //logger.warn("Person: "+myPerson)
    //logger.error("Person: "+myPerson)

  }
}
