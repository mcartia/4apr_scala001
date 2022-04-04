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

    logger.info("command line args dump...")
    for (arg <- args) {
      logger.info("arg: "+arg)
    }

    logger.info("yelding example")
    val fruits = List("apple", "banana", "lime", "orange")

    val fruitLengths = for {
      f <- fruits
      //if f.length > 4
    } yield f.length

    fruitLengths.foreach(
      x => {
        logger.info("Item: "+x)
      })

    /*var i = 0

    while (i<100) {
      logger.info("i="+i)
      i+=1
    }*/

    (1 to 100).toList.foreach( x => logger.info("i="+x) )


  }
}
