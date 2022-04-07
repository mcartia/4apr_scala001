package it.training.scala.db

import org.apache.log4j.Logger
import org.h2.tools.Server

import java.sql.{Connection, DriverManager}
import scala.util.{Failure, Success, Try}

class MyDBServer {

  val logger = Logger.getLogger(this.getClass)
  var server: Server = _
  var conn: Connection = _

  def startDb(): Boolean = {
    Try {
      server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start
      Class.forName("org.h2.Driver")
      conn = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/scalaDb;DB_CLOSE_ON_EXIT=FALSE", "sa", "password")
      conn.createStatement.execute("SELECT 1")
    } match {
      case Success(b) => {
        logger.info("Server started!")
        val thread = new Thread {
          override def run {
            Server.startWebServer(conn)
          }
        }
        thread.start
        true
      }
      case Failure(e) => {
        logger.error(e.getMessage,e)
        false
      }
    }
  }

  def stop(): Boolean = {
    Try {
      server.stop()
      //Server.shutdownTcpServer("tcp://localhost:9092", "password", true, true)
    } match {
      case Success(b) => true
      case Failure(e) => false
    }
  }

}
