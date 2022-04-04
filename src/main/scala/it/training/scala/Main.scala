package it.training.scala

import org.apache.log4j.{BasicConfigurator, Logger}

object Main {
  def main(args: Array[String]): Unit = {

    // Collegarsi alla chat:
    // https://tlk.io/scala_training

    BasicConfigurator.configure()

    val myPerson = new Person(1L,
        "Mario",
      "Cartia",
      "mario.cartia@gmail.com")

    val logger = Logger.getLogger("MyLogger")

    logger.info("Person: "+myPerson)

  }
}
