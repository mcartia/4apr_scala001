package it.training.scala.db

import org.apache.log4j.{BasicConfigurator, Level, Logger}
import scalikejdbc._

import java.sql.Timestamp
import java.util.Date

object DbTest {
  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.INFO)

    /*val dbServer = new MyDBServer
    val h2running = dbServer.startDb()*/

    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost:9092/~/scalaDb", "sa", "password")

    implicit val session = AutoSession

    /*val records =
      sql"""
         select * from people
       """.map(_.toMap()).list.apply*/

    val records =
      sql"""
         select * from people
       """.map( rs => rs2People(rs) ).list.apply

    records.foreach( p => {
    println(
      s"""
         |People
         |------
         |id:         ${p.id}
         |first name: ${p.firstName}
         |last name:  ${p.lastName}
         |email:      ${p.email}
         |created:    ${p.created_at}
         |""".stripMargin)
    })

    /*records.foreach( println )

    records.foreach(r => {
      println(s"Record\n-----")
      r.foreach(kv => {
        println(s"${kv._1}: ${kv._2}")
      })
      println("-----\n")
    })

    dbServer.stop()
    System.exit(0)
  }*/
  }

  case class People(id: Long, firstName: String, lastName: String, email: String, created_at: Date)

  def rs2People(rs: WrappedResultSet): People = {
    People(
      rs.long("id"),
      rs.string("first_name"),
      rs.string("last_name"),
      rs.string("email"),
      rs.date("created_at")
    )
  }
}


