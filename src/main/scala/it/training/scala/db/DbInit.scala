package it.training.scala.db

import org.apache.log4j.{BasicConfigurator, Level, Logger}
import scalikejdbc._


object DbInit {
  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.INFO)

    val dbServer = new MyDBServer
    val h2running = dbServer.startDb()

    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:tcp://localhost:9092/~/scalaDb","sa","password")

    implicit val session = AutoSession
    sql"""
         drop table people
       """.update.apply

    sql"""
         create table people(
         id serial not null primary key,
         first_name varchar(255),
         last_name varchar(255),
         email varchar(255),
         created_at timestamp not null
         )
       """.execute.apply

    val sampleData = List(
      People("Mario","Cartia","mario.cartia@gmail.com"),
      People("Giuseppe","Rossi","g.rossi@yahoo.it"),
      People("Maria","Verdi","m.verdi@hotmail.com")
    )

    sampleData.foreach( p => {
      sql"""
           insert into people(first_name, last_name, email, created_at)
           values (${p.firstName}, ${p.lastName}, ${p.email}, current_timestamp)
         """.update.apply
    })

    while(true) {
    }

    dbServer.stop()
  }
}

case class People(firstName: String, lastName: String, email: String)
