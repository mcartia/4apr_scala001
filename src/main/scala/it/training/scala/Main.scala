package it.training.scala

import it.training.scala.hier.SuperVeicolo
import org.apache.log4j.{BasicConfigurator, Level, Logger}

import java.util
import scala.collection.mutable

object Main {
  def main(args: Array[String]): Unit = {

    // Collegarsi alla chat:
    // https://tlk.io/scala_training

    // Github:
    // https://github.com/mcartia/4apr_scala001

    // https://www.scala-lang.org/api/2.13.8/

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
        case "1" => {
          exercise1_1()
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
        case "superv" => {
          val sv = new SuperVeicolo
          sv.cammina()
          sv.suonaCampanello()
          sv.vola()
          sv.aaa()
        }
        case "coll" => {
          val myColl = new util.ArrayList[String]()
          myColl.add("Ciao")
          myColl.add("Pippo")
          myColl.add("Pappo")

          //myColl = new util.ArrayList[String]()

          for ( i <- myColl.toArray() ) {
            println(s"Elem: $i")
          }
        }
        case "scalaColl" => {
          val myList1 = List("bla1","bla2","bla3")
          var myList2 = new mutable.ListBuffer[String]
          myList2.addOne("one")
          myList2.addOne("two")
          myList2.addOne("three")

          println("myList1\n-----")
          myList1.foreach( x => {
            println(s"elem $x")
          })

          println("myList2\n-----")
          myList2.foreach( x => {
            println(s"elem $x")
          })

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

  // Write a program that prints ‘Hello World’ to the screen.
  def exercise1_1(): Unit = {

  }

  // testo esercizio
  def exercise1_2(): Unit = {

  }


}
