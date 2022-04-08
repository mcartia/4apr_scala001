package it.training.scala.api

import cask.Request
import cask.main.MainRoutes
import org.apache.log4j.{BasicConfigurator, Level, Logger}
import scalikejdbc._
import io.circe.generic.semiauto._
import io.circe.syntax._
import io.circe.{Decoder, Encoder}

object MyApi extends MainRoutes {
  BasicConfigurator.configure()
  Logger.getRootLogger.setLevel(Level.INFO)

  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:tcp://localhost:9092/~/scalaDb", "sa", "password")
  implicit val session = AutoSession

  var logger = Logger.getLogger("MyApi")

  @cask.get("/hello")
  def hello(): String = {
    "Hello, World!"
  }

  @cask.get("/test")
  def test() = {
    var jsonOut = ""
    implicit val encoder: Encoder[People] = deriveEncoder[People]

    val peopleList = sql"""
         |select * from people
         |""".stripMargin.map(rs=>{rs2People(rs)}).list.apply

    cask.Response(peopleList.asJson.toString, headers = Seq(("Content-Type","application/json")))
  }

  def rs2People(rs: WrappedResultSet): People = {
    People(
      rs.long("id"),
      rs.string("first_name"),
      rs.string("last_name"),
      rs.string("email"),
      rs.string("created_at")
    )
  }

  logger.info("Starting web server: http://localhost:8080")
  initialize()
}

case class People(id: Long, firstName: String, lastName: String, email: String, created_at: String)


