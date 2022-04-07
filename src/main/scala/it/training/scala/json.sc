import io.circe.generic.auto._
import io.circe.parser._

/*
dato formato json:

{
  "id": 1,
  "firstName": "Mario",
  "lastName": "Cartia",
  "email": "mario.cartia@gmail.com"
}
 */

val jsonString =
  """
    |{
    |  "id": 1,
    |  "firstName": "Mario",
    |  "lastName": "Cartia",
    |  "email": "mario.cartia@gmail.com"
    |}
    |""".stripMargin

case class Person(id: Long,
                  firstName: String,
                  lastName: String,
                  email: Option[String])

val decodeResult = decode[Person](jsonString)

decodeResult match {
  case Left(err) => {
    println(s"Error: $err")
  }
  case Right(person) => {
    println(
      s"""
         |Person
         |------
         |id: ${person.id}
         |firstName: ${person.firstName}
         |lastName: ${person.lastName}
         |email: ${person.email.getOrElse("undefined")}
         |""".stripMargin)
  }
}
