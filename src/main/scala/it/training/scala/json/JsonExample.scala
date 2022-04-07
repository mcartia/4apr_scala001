package it.training.scala.json

import io.circe.generic.auto._, io.circe.parser._

object JsonExample {
  def main(args: Array[String]):Unit = {

    val sampleJson =
      """{
        |"id":10,
        |"firstName":"Mario",
        |"lastName":"Cartia"
        |}""".stripMargin

    val person = decode[PersonJson](sampleJson)

    person match {
      case Left(e) => {
        println(s"Si è verificato un errore: $e")      }
      case Right(pJson) => {
        println(s"""
        Oggetto decodificato
          id: ${pJson.id}
          firstName: ${pJson.firstName}
          lastName: ${pJson.lastName}
          email: ${pJson.email.getOrElse("non specificato")}
        """)
      }
    }
  }
}

case class PersonJson(id: Long, firstName: String, lastName: String, email: Option[String])
