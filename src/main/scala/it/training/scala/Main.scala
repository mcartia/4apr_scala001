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

    val logger = Logger.getLogger("MyLogger")


    logger.info("+++ Cmdline arguments +++")
    for ( arg <- args ) {
      logger.info("Cmdline argument: "+arg)
    }

   //val myPerson = Person(1L,"Mario","Cartia", "mario.cartia@gmail.com")

    var lastCmd = ""

    while(lastCmd!="quit") {
      lastCmd = scala.io.StdIn.readLine("cmd> ")

      lastCmd match {
        case "hello" => {
          println("Hello, world")
        }
        case "help" => {
          println("Allowed commands: 'hello', 'quit', 'help'")
        }
        case "yield" => {
          val newColl = for (i <- 1 to 10) yield i*2

          for (x <- newColl) println("newColl item: "+x)
        }
        case "class" => {
          val p = new Person(1,"Mario","Cartia","mario.cartia@gmail.com")
          p.printFullName()
        }
        case "sum" => {
          val res = Utils.sum("sdfsdfsdfs",3)
          println(s"Result: $res")
        }
        case "sample" => {
          Utils.withOptional("Mario","Cartia")
          Utils.withOptional("Mario","Cartia",Some(20))
        }
        case "quit" => {
          println("Exiting...")
        }
        case _ => println("Unrecognized command!")
      }
    }

  }

  implicit def pippo(in: String): Int = {
    try {
    Integer.parseInt(in)
    } catch {
      case nfe: NumberFormatException => {
        println(s"Can't convert: $in, returning default value (0)")
        return 0
      }
      case _ => {
        println("Unknown error, returning default value (0)")
        return 0
      }
    }
  }

}
