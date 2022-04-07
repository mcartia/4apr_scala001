package it.training.scala.db

import org.apache.log4j.{BasicConfigurator, Level, Logger}
import scalikejdbc._

object DbTest {
  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.INFO)

    val dbServer = new MyDBServer
    val h2running = dbServer.startDb()

    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost:9092/~/scalaDb", "sa", "password")

    implicit val session = AutoSession

    val records: List[Map[String, Any]] =
      sql"""
         select * from people
       """.map(_.toMap).list.apply

    records.foreach(r => {
      println(s"Record\n-----")
      r.foreach(kv => {
        println(s"${kv._1}: ${kv._2}")
      })
      println("-----\n")
    })

    dbServer.stop()
  }
}


