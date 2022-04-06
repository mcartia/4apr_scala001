package it.training.scala.json

import io.circe.generic.semiauto
import io.circe.{Decoder, jawn}

object JsonExample {
  def main(args: Array[String]):Unit = {

    val sampleJson =
      """{
        |"id":10,
        |"firstName":"Mario",
        |"lastName":"Cartia"
        |}""".stripMargin

    implicit val jsonDecoder: Decoder[PersonJson] = semiauto.deriveDecoder[PersonJson]

    val person = jawn.decode[PersonJson](sampleJson)
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
